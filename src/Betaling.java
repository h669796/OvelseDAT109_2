import java.time.LocalDate;


/**
 *  * Representerer en betalingsinformasjon for en kunde inkludert kundeID, kortnummer, utløpsdato for kortet,
 *  * og fakturaadresse.
 *  */

public class Betaling {
    private int kundeID;
    private String kortNummer;
    private String utlopsDato;
    private Adresse fakturaAdresse;

    /**
     * Oppretter en ny instans av Betaling.
     *
     * @param kundeID Kundeidentifikator
     * @param kortNummer Nummeret på betalingskortet
     * @param utlopsDato Utløpsdatoen for betalingskortet
     * @param fakturaAdresse Fakturaadressen for betalingen
     */

    public Betaling(int kundeID, String kortNummer, String utlopsDato, Adresse fakturaAdresse) {
        this.kundeID = kundeID;
        this.kortNummer = kortNummer;
        this.utlopsDato = utlopsDato;
        this.fakturaAdresse = fakturaAdresse;
    }

    public int getKundeID() {
        return kundeID;
    }

    public void setKundeID(int kundeID) {
        this.kundeID = kundeID;
    }

    public String getKortNummer() {
        return kortNummer;
    }

    public void setKortNummer(String kortNummer) {
        this.kortNummer = kortNummer;
    }

    public String getUtlopsDato() {
        return utlopsDato;
    }

    public void setUtlopsDato(String utlopsDato) {
        this.utlopsDato = utlopsDato;
    }

    public Adresse getFakturaAdresse() {
        return fakturaAdresse;
    }

    public void setFakturaAdresse(Adresse fakturaAdresse) {
        this.fakturaAdresse = fakturaAdresse;
    }

}
