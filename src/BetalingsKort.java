import java.time.LocalDate;


/**
 * Representerer et betalingskort for en kunde, inkludert kundeID, kortnummer, utløpsdato, og fakturaadresse.
 */

public class BetalingsKort {

    private int kundeID;
    private String kortnummer;
    private LocalDate utlopsdato;
    private Adresse fakturaAdresse;

/**
        * Oppretter en ny instans av BetalingsKort.
            *
            * @param kundeID Kundeidentifikator
     * @param kortnummer Nummeret på betalingskortet
     * @param utlopsdato Utløpsdatoen for betalingskortet
     * @param adresse Fakturaadressen for betalingen
     */

    public BetalingsKort(int kundeID, String kortnummer, LocalDate utlopsdato, Adresse adresse){
        this.kundeID = kundeID;
        this.kortnummer = kortnummer;
        this.utlopsdato = utlopsdato;
        this.fakturaAdresse = adresse;
    }

    public int getKundeID() {
        return kundeID;
    }
    public String getKortnummer() {
        return kortnummer;
    }
    public LocalDate getUtlopsdato() {
        return utlopsdato;
    }
    public Adresse getFakturaAdresse() {
        return fakturaAdresse;
    }


}
