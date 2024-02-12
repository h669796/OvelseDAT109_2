import java.time.LocalDate;

public class BetalingsKort {

    private int kundeID;
    private String kortnummer;
    private LocalDate utlopsdato;
    private Adresse fakturaAdresse;

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
