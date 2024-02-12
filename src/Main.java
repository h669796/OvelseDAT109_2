import java.util.Scanner;

public class Main { //I love black people testest
    //beger
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

    }

}