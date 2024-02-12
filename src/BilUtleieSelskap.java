import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class BilUtleieSelskap {

    private String navn;
    private String phoneNumber;
    private Adresse adresse;
    private List<UtleieKontor> utleieKontor = new ArrayList<>();

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

    public List<Bil> finnLedigeBiler(LocalDateTime start, LocalDateTime slutt, String kategori) {
        List<Bil> ledigeBiler = new ArrayList<>();
        for (UtleieKontor kontor : utleieKontor) {
            ledigeBiler.addAll(kontor.visLedigeBiler().stream()
                    .filter(bil -> bil.getUtleieGruppe().getCode().equals(kategori) && bil.getStatus())
                    .toList());
        }
        return ledigeBiler;
    }

    public Reservasjon leggTilReservasjon(Kunde kunde, LocalDateTime start, LocalDateTime slutt, UtleieGruppe kategori
            , UtleieKontor utleieSted, UtleieKontor returSted) {
        List<Bil> ledigeBiler = finnLedigeBiler(start, slutt, kategori.getCode());
        if (!ledigeBiler.isEmpty()) {
            Bil valgtBil = ledigeBiler.get(0);
            valgtBil.setStatus(false);
            int pris = beregnPris(start, slutt, kategori, utleieSted, returSted);
            Reservasjon nyRes = new Reservasjon(utleieSted.getAdresse().getPoststed(), returSted.getAdresse().getPoststed(), start, slutt, kategori.getCode(), pris, valgtBil);
            return nyRes;
        } else {
            System.out.println("ingen ledige biler funnet");
            return null;
            // mjau test
        }
    }

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

    public UtleieKontrakt opprettUtleieKontrakt(Reservasjon reservasjon, Kunde kunde, Bil bil, LocalDateTime henteDato, LocalDateTime returDato, int hentetKm, String kortnummer, String utlopsDato, Adresse adresse) {
        if (!bil.getStatus() || !reservasjon.getBil().getRegnr().equals(bil.getRegnr())) {
            System.out.println("Bilen er ikke tilgjengelig for utleie");
            return null;
        }

        BetalingsKort kort = new BetalingsKort(kunde.getKundeID(), kortnummer, utlopsDato, adresse);

        UtleieKontrakt kontrakt = new UtleieKontrakt(reservasjon.getUtleieStart(),
                reservasjon.getUtleieSlutt(),
                bil.getAntallKmKjort(),
                0,
                reservasjon,
                kort);

        return kontrakt;
    }

    public void returAvBil(UtleieKontrakt kontrakt, int kmKjort, LocalDateTime returDato) {
        Bil bil = kontrakt.getReservasjon().getBil();

        int totalKmKjort = kontrakt.getHentetKm() + kmKjort;
        bil.setAntallKmKjort(totalKmKjort);
    }

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
