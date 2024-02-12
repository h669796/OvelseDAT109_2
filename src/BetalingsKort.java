import java.time.LocalDate;

public class BetalingsKort {

    private int kundeID;
    private String kortnummer;
    private String utlopsdato;
    private Adresse fakturaAdresse;

    public BetalingsKort(int kundeID, String kortnummer, String utlopsdato, Adresse adresse){
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
    public String getUtlopsdato() {
        return utlopsdato;
    }
    public Adresse getFakturaAdresse() {
        return fakturaAdresse;
    }


}
