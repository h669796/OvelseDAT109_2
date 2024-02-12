import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Representerer et bilutleieselskap med informasjon om navn, telefonnummer, adresse og en liste av utleiekontorer.
 */
public class BilUtleieSelskap {

    private String navn;
    private String phoneNumber;
    private Adresse adresse;
    private List<UtleieKontor> utleieKontor = new ArrayList<>();

    /**
     * Oppretter et nytt bilutleieselskap.
     *
     * @param navn Navnet på bilutleieselskapet
     * @param phoneNumber Telefonnummeret til bilutleieselskapet
     * @param address Adressen til hovedkontoret for bilutleieselskapet
     */
    public BilUtleieSelskap(String navn, String phoneNumber, Adresse address) {
        this.navn = navn;
        this.phoneNumber = phoneNumber;
        this.adresse = address;
    }

    public void addUtleieKontor(UtleieKontor kontor) {
        utleieKontor.add(kontor);
    }

    public void vilUtleieKontor() {
        for (UtleieKontor kontor : utleieKontor) {
            System.out.println(kontor.toString());
        }
    }

    /**
     * Finner ledige biler basert på angitt start- og sluttidspunkt og bilkategori.
     *
     * @param start Starttidspunktet for leieperioden
     * @param slutt Sluttidspunktet for leieperioden
     * @param kategori Kategorien av bil som ønskes leid
     * @return En liste av ledige biler som matcher kriteriene
     */
    public List<Bil> finnLedigBil(LocalDateTime start, LocalDateTime slutt, String kategori) {
        List<Bil> ledigeBiler = new ArrayList<>();
        for (UtleieKontor kontor : utleieKontor) {
            ledigeBiler.addAll(kontor.visLedigeBiler().stream()
                    .filter(bil -> bil.getUtleieGruppe().getCode().equals(kategori) && bil.getStatus())
                    .toList());
        }
        return ledigeBiler;
    }

    /**
     *  legger til en reservasjon
     * @param kunde
     * @param start
     * @param slutt
     * @param kategori
     * @param utleieSted
     * @param returSted
     * @return reservasonen
     */
    public Reservasjon leggTilReservasjon(Kunde kunde, LocalDateTime start, LocalDateTime slutt, UtleieGruppe kategori
            , UtleieKontor utleieSted, UtleieKontor returSted) {
        List<Bil> ledigeBiler = finnLedigBil(start, slutt, kategori.getCode());
        if (!ledigeBiler.isEmpty()) {
            Bil valgtBil = ledigeBiler.get(0);
            int pris = beregnPris(start, slutt, kategori, utleieSted, returSted);
            Reservasjon nyRes = new Reservasjon(utleieSted.getAdresse().getPoststed(), returSted.getAdresse().getPoststed(), start, slutt, kategori.getCode(), pris, valgtBil);
            return nyRes;
        } else {
            System.out.println("ingen ledige biler funnet");
            return null;
            // mjau test
        }
    }

    /**
     * regner ut prisen på en bil
     * @param start
     * @param slutt
     * @param kategori
     * @param utleieSted
     * @param returSted
     * @return totalprisen på bilen
     */

    public int beregnPris(LocalDateTime start, LocalDateTime slutt, UtleieGruppe kategori, UtleieKontor utleieSted, UtleieKontor returSted) {
        int grunnPris = kategori.getGrunnPris();
        long rentalDays = ChronoUnit.DAYS.between(start, slutt);
        int totalPris = (int) rentalDays * grunnPris;

        if (!utleieSted.equals(returSted)) {
            int ekstraKostnad = 500;
            totalPris += ekstraKostnad;
        }
        return totalPris;
    }

    /**
     * oppretter en utleiekontrakt
     *
     * @param reservasjon
     * @param kunde
     * @param bil
     * @param henteDato
     * @param returDato
     * @param hentetKm
     * @param kortnummer
     * @param utlopsDato
     * @param adresse
     * @return en utleiekontrakt
     */
    public UtleieKontrakt opprettUtleieKontrakt(Reservasjon reservasjon, Kunde kunde, Bil bil, LocalDateTime henteDato, LocalDateTime returDato, int hentetKm, String kortnummer, String utlopsDato, Adresse adresse) {
        if (!bil.getStatus() || !reservasjon.getBil().getRegnr().equals(bil.getRegnr())) {
            System.out.println("Bilen er ikke tilgjengelig for utleie");
            return null;
        }

        if (kunde == null) {
            System.out.println("Kundeobjektet er null i opprettUtleieKontrakt.");
            return null;
        }
        System.out.println("Oppretter BetalingsKort med Kunde ID: " + kunde.getKundeID());

        BetalingsKort kort = new BetalingsKort(kunde.getKundeID(), kortnummer, utlopsDato, adresse);

        UtleieKontrakt kontrakt = new UtleieKontrakt(reservasjon.getUtleieStart(),
                reservasjon.getUtleieSlutt(),
                bil.getAntallKmKjort(),
                0,
                reservasjon,
                kort);

        bil.setStatus(false);

        return kontrakt;
    }

    /**
     * tar imot en retur av bil
     * @param kontrakt
     * @param kmKjort
     * @param returDato
     */
    public void returAvBil(UtleieKontrakt kontrakt, int kmKjort, LocalDateTime returDato) {

        Bil bil = kontrakt.getReservasjon().getBil();

        int totalKmKjort = kontrakt.getHentetKm() + kmKjort;
        bil.setAntallKmKjort(totalKmKjort);
        bil.setStatus(true);
    }


    /**
     * betaler for leien
     * @param kunde
     * @param kontrakt
     * @return true ller false, om det er betalt
     */

    public boolean betalForLeien(Kunde kunde, UtleieKontrakt kontrakt) {
        try {
            String kortnummer = kontrakt.getKort().getKortnummer();
            String utlopsdato = kontrakt.getKort().getUtlopsdato();
            Adresse adresse1 = kontrakt.getKort().getFakturaAdresse();

            Betaling betaling = new Betaling(kunde.getKundeID(), kortnummer, utlopsdato, adresse1);

            System.out.println("Betalings beløp = " + kontrakt.getReservasjon().getPris() + " NOK " + " ble prosessert for kundeID: " + kunde.getKundeID());
            return true;
        } catch (Exception e) {
            System.err.println("Betalings prosesseringen feilet: " + e.getMessage());
            return false;
        }
    }

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Adresse getAddress() {
        return adresse;
    }

    public void setAddress(Adresse address) {
        this.adresse = address;
    }

    public List<UtleieKontor> getUtleieKontor() {
        return utleieKontor;
    }

    public void setUtleieKontor(List<UtleieKontor> utleieKontor) {
        this.utleieKontor = utleieKontor;
    }
}
