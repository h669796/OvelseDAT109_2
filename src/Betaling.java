import java.time.LocalDate;

public class Betaling {
    private int kundeID;
    private String kortNummer;
    private LocalDate utlopsDato;
    private Adresse fakturaAdresse;

    public Betaling(int kundeID, String kortNummer, LocalDate utlopsDato, Adresse fakturaAdresse) {
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

    public LocalDate getUtlopsDato() {
        return utlopsDato;
    }

    public void setUtlopsDato(LocalDate utlopsDato) {
        this.utlopsDato = utlopsDato;
    }

    public Adresse getFakturaAdresse() {
        return fakturaAdresse;
    }

    public void setFakturaAdresse(Adresse fakturaAdresse) {
        this.fakturaAdresse = fakturaAdresse;
    }

}
