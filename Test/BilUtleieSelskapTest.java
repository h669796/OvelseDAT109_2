import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

class BilUtleieSelskapTest {
    private BilUtleieSelskap selskap;
    private Adresse adresse;


    @BeforeEach
    void setUp() {
        adresse = new Adresse("gate 1", "123", "Testby");
        //addresse og selskap blir opprettet på nytt for hver test pga @beforeeach taggen
        selskap = new BilUtleieSelskap("selskap", "12345", adresse);
    }

    @Test
    void addUtleieKontor() {
        //oppretter utleiekontor og legger den til selskapet.
        UtleieKontor kontor = new UtleieKontor("kontor", adresse, "12345");
        selskap.addUtleieKontor(kontor);
        assertEquals(1, selskap.getUtleieKontor().size(), "Kontor bør være lagt til");
    }

    @Test
    void finnLedigBil() {
        //lag utleiekontor og legg en bil til selskapet.
        UtleieKontor kontor = new UtleieKontor("kontor", adresse, "12345");
        Bil bil1 = new Bil("ABC12345", "BMW", "M5", "Svart", UtleieGruppe.LITEN, 0);
        kontor.addBil(bil1);

        selskap.addUtleieKontor(kontor);

        //sjekker om vi kan finne bilen i selskapet
        var availableCars = selskap.finnLedigBil(LocalDateTime.now(), LocalDateTime.now().plusDays(1), "LITEN");
        assertFalse(availableCars.isEmpty(), "Må finne en ledig bil i det minste");
        //sjekker om vi fant bilen vi la til (samme regnr)
        assertEquals("ABC12345", availableCars.get(0).getRegnr(), "Bilen som ble funnet bør være den som ble lagt til");
    }

    @Test
    void leggTilReservasjon() {
        //opprett parameterne vi trenger
        Kunde kunde = new Kunde("kundenavn", "etternavn", adresse, "12345");
        LocalDateTime start = LocalDateTime.now();
        LocalDateTime slutt = start.plusDays(3);
        UtleieGruppe gruppe = UtleieGruppe.LITEN;
        UtleieKontor utleieSted = new UtleieKontor("Bergen", adresse, "12345");
        Bil bil = new Bil("ABC12345", "BMW", "M5", "Svart", gruppe, 0);
        utleieSted.addBil(bil);
        selskap.addUtleieKontor(utleieSted);

        //lag reservasjon
        Reservasjon reservasjon = selskap.leggTilReservasjon(kunde, start, slutt, gruppe, utleieSted, utleieSted);
        assertNotNull(reservasjon, "reservasjon bør være opprettet");

        //Reservasjon når det ikke finnes en bil
        //ingen biler er lagt til STOR gruppen, så vi bare bruker den
        UtleieGruppe tomGruppe = UtleieGruppe.STOR;
        //oppretter reservasjonen der den er tom
        Reservasjon tomReservasjon = selskap.leggTilReservasjon(kunde, start, slutt, tomGruppe, utleieSted, utleieSted);
        assertNull(tomReservasjon, "Ingen reservasjon bor vere lagd når det ikke finnes noen bil");
    }

    @Test
    void beregnPris() {

        //nødvendige parametre
        LocalDateTime start = LocalDateTime.now();
        LocalDateTime slutt = start.plusDays(3);
        UtleieGruppe gruppe = UtleieGruppe.LITEN;
        UtleieKontor utleieSted = new UtleieKontor("Bergen", adresse, "12345");
        UtleieKontor returSted = new UtleieKontor("Oslo", adresse, "54321");

        //utleie i samme sted
        int sammeSted = selskap.beregnPris(start, slutt, gruppe, utleieSted, utleieSted);
        assertEquals(gruppe.getGrunnPris() * 3, sammeSted, "Prisen bør være lik");
        //utleie i forskjellige steder
        //hvis det er ulike steder får du en ekstrakostnad på 500kr
        int ulikSted = selskap.beregnPris(start, slutt, gruppe, utleieSted, returSted);
        assertEquals(gruppe.getGrunnPris() * 3 + 500, ulikSted, "Prisen bør være ulik");
    }

    @Test
    void opprettUtleieKontrakt() {
        //parametre vi trenger
        Kunde kunde = new Kunde("kundenavn", "etternavn", adresse, "12345");
        Bil bil = new Bil("ABC12345", "BMW", "M5", "Svart", UtleieGruppe.LITEN, 0);
        LocalDateTime henteDato = LocalDateTime.now();
        LocalDateTime returDato = henteDato.plusDays(3);
        Reservasjon reservasjon = new Reservasjon("Bergen", "Oslo", henteDato, returDato, "LITEN", 1000, bil);

        String utlopsDatoSomStreng = LocalDate.now().plusYears(1).format(DateTimeFormatter.ISO_LOCAL_DATE);

        //opprette en kontrakt
        UtleieKontrakt kontrakt = selskap.opprettUtleieKontrakt(reservasjon, kunde, bil, henteDato, returDato, 100, "1111111", utlopsDatoSomStreng, adresse);
        assertNotNull(kontrakt, "Utleiekontrakten bør opprettes");

        //opprette kontrakt hvis ikke bil er ledig
        bil.setStatus(false);
        UtleieKontrakt ugyldigKontrakt = selskap.opprettUtleieKontrakt(reservasjon, kunde, bil, henteDato, returDato, 100, "1111111", utlopsDatoSomStreng, adresse);
        assertNull(ugyldigKontrakt, "bør ikke kunne opprettes hvis bil ikke er ledig");
    }

    @Test
    void returAvBil() {
        //nødvendige parametre
        Kunde kunde = new Kunde("kundenavn", "etternavn", adresse, "12345");
        Bil bil = new Bil("ABC12345", "BMW", "M5", "Svart", UtleieGruppe.LITEN, 0);
        UtleieKontor kontor = new UtleieKontor("Bergen", adresse, "12345");
        int kmstand = bil.getAntallKmKjort();
        int kmKjort = 100;
        LocalDateTime start = LocalDateTime.now();
        LocalDateTime slutt = start.plusDays(3);
        kontor.addBil(bil);
        selskap.addUtleieKontor(kontor);
        Reservasjon reservasjon = selskap.leggTilReservasjon(kunde, start, slutt, UtleieGruppe.LITEN, kontor, kontor);
        String utlopsDatoSomStreng = LocalDate.now().plusYears(1).format(DateTimeFormatter.ISO_LOCAL_DATE);


        //opprett kontrakten
        UtleieKontrakt kontrakt = selskap.opprettUtleieKontrakt(reservasjon, kunde, bil, start, slutt, 100, "1111111", utlopsDatoSomStreng, adresse);

        selskap.returAvBil(kontrakt, kmKjort, LocalDateTime.now());

        //
        assertTrue(bil.getStatus(), "Bilen bør være tilgjengelig etter retur");
        assertEquals(kmstand + kmKjort, bil.getAntallKmKjort(), "Kilometerstanden bør være oppdatert etter retur");
    }

    @Test
    void betalForLeien() {

        //nødvendige parametre
        Kunde kunde = new Kunde("kundenavn", "etternavn", adresse, "12345");
        Bil bil = new Bil("ABC12345", "BMW", "M5", "Svart", UtleieGruppe.LITEN, 0);
        LocalDateTime start = LocalDateTime.now();
        LocalDateTime slutt = start.plusDays(3);
        UtleieKontor kontor = new UtleieKontor("Bergen", adresse, "12345");
        kontor.addBil(bil);
        selskap.addUtleieKontor(kontor);
        String utlopsDatoSomStreng = LocalDate.now().plusYears(1).format(DateTimeFormatter.ISO_LOCAL_DATE);
        Reservasjon reservasjon = selskap.leggTilReservasjon(kunde, start, slutt, UtleieGruppe.LITEN, kontor, kontor);
        UtleieKontrakt kontrakt = selskap.opprettUtleieKontrakt(reservasjon, kunde, bil, start, slutt, 100, "1111111", utlopsDatoSomStreng, adresse);

        //betale med gyldig parametre
        boolean resultat = selskap.betalForLeien(kunde, kontrakt);
        assertTrue(resultat, "Betalingen bør virke for gyldig parametre");

        //hvis en parameter er ugyldig
        boolean ugyldigResultat = selskap.betalForLeien(kunde, null);
        assertFalse(ugyldigResultat, "Betalingen bør feile med ugyldig parametre");
    }
}