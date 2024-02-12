import javax.swing.text.DateFormatter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * * Hovedklassen for simulering av leieprosessen i et bilutleieselskap.
   * Denne klassen initialiserer bilutleieselskapet, legger til utleiekontorer og biler,
   * og behandler kundeinndata for å starte leieprosessen.
    *
    */
public class Main {
    public static void main(String[] args) {

        // Dette er klassen for leien foregår, simulert

        BilUtleieSelskap selskap = new BilUtleieSelskap("Norge Bilutleie", "55984400", new Adresse("Fritzveien 55", "0665", "Oslo Sentrum"));

        UtleieKontor oslo = new UtleieKontor("Oslo", new Adresse("Olahaugen 12", "1364", "Fornebu"), "55982200");
        selskap.addUtleieKontor(oslo);

        UtleieKontor bergen = new UtleieKontor("Bergen", new Adresse("Fleslandsvein 55", "5237", "Ytrebygda"), "33984400");
        selskap.addUtleieKontor(bergen);

        Bil bil1 = new Bil("BT53331", "BMW", "X3", "svart", UtleieGruppe.STOR, 100);
        Bil bil2 = new Bil("EL12345", "Tesla", "Model 3", "hvit", UtleieGruppe.MELLOMSTOR, 50);
        Bil bil3 = new Bil("SU67890", "Suzuki", "Swift", "rød", UtleieGruppe.LITEN, 300);
        Bil bil4 = new Bil("VO24680", "Volvo", "V60", "blå", UtleieGruppe.STASJONSVOGN, 600);

        oslo.addBil(bil1);
        oslo.addBil(bil2);
        bergen.addBil(bil3);
        oslo.addBil(bil4);

        Kunde kunde1; // gjør oss klar til å ta imot en ny kunde, enten online eller i butikken

        System.out.println("En kunde vil starte leieprosessen.");

        System.out.println("Skriv inn fornavn");
        Scanner scanner = new Scanner(System.in);
        String fornavn = scanner.nextLine();
        System.out.println("Skriv inn etternavn");
        String etternavn = scanner.nextLine();
        System.out.println("Skriv inn telefonnummeret ditt");
        String tlfnr = scanner.nextLine();

        System.out.println("Skriv inn gateaddressen din");
        String gateaddresse = scanner.nextLine();
        System.out.println("Skriv inn postnummeret ditt");
        String postnr = scanner.nextLine();
        System.out.println("Skriv inn poststedet ditt");
        String poststed = scanner.nextLine();

        Adresse kunde1_adresse = new Adresse(gateaddresse, postnr, poststed);

        kunde1 = new Kunde(fornavn, etternavn, kunde1_adresse, tlfnr);

        //kunde1 vil leie en stor bil, hente og levere i oslo med en dags mellomrom
        //Vi skal nå finne de ledige bilene som matcher dette

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        LocalDateTime start = LocalDateTime.parse("2024-02-12 15:00:00", formatter);
        LocalDateTime slutt = LocalDateTime.parse("2024-02-13 16:00:00", formatter);

        List<Bil> kandidater = selskap.finnLedigBil(start, slutt, "C");

        //Kunden skal nå gjennomføre reservasjonen

        Reservasjon kunde1res = selskap.leggTilReservasjon(kunde1, start, slutt, UtleieGruppe.STOR, oslo, oslo);

        //Utleiekontrakt må nå opprettes mellom utleier og leier, og vi trenger kortet til kunden til fremtidig faktura

        System.out.println("Skriv inn kortnummeret ditt for å initiere kontrakten");
        String kortnr = scanner.nextLine();
        System.out.println("Skriv inn utlopsdatoen til kortet");
        String utlopsdato = "2026:05:10";

        UtleieKontrakt kunde1kontrakt = selskap.opprettUtleieKontrakt(kunde1res, kunde1res.getKunde(), kunde1res.getBil(), start, slutt, kunde1res.getBil().getAntallKmKjort(), kortnr, utlopsdato, kunde1_adresse);

        //Kunden har brukt bilen i et døgn, og vil nå levere den tilbake

        selskap.returAvBil(kunde1kontrakt, 50, slutt);

        //Kunden må nå betale for leien

        selskap.betalForLeien(kunde1, kunde1kontrakt);

    }

}