import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * En representaskon av et utleiekontor
 */
public class UtleieKontor {
    private static int teller = 0;
    private String navn;
    private int kontorNummer;
    private Adresse adresse;
    private String telefonNummer;
    private List<Bil> biler;

    /**
     * oppretter et utleiekontor
     * @param navn
     * @param adresse
     * @param telefonNummer
     */
    public UtleieKontor(String navn, Adresse adresse, String telefonNummer) {
        this.kontorNummer = ++teller;
        this.adresse = adresse;
        this.telefonNummer = telefonNummer;
        this.biler = new ArrayList<>();
    }

    public void addBil(Bil bil){
        biler.add(bil);
    }

    /**
     * viser ledige biler
     * @return en liste av biler
     */
    public List<Bil> visLedigeBiler(){
        return biler.stream().filter(Bil::getStatus).collect(Collectors.toList());
    }


    /**
     * returnerer en bil og gjør den tilgjengelig for utlån
     * @param regnr
     */
    public void returnerBil(String regnr){
        for(Bil bil : biler){
            if(bil.getRegnr().equals(regnr)){
                bil.setStatus(true);
                // kmstand etc ?
                break;
            }
        }
    }

    public int getKontorNummer() {
        return kontorNummer;
    }

    public void setKontorNummer(int kontorNummer) {
        kontorNummer = kontorNummer;
    }

    public Adresse getAdresse() {
        return adresse;
    }

    public void setAdresse(Adresse adresse) {
        adresse = adresse;
    }

    public String getTelefonNummer() {
        return telefonNummer;
    }

    public void setTelefonNummer(String telefonNummer) {
        this.telefonNummer = telefonNummer;
    }

    @Override
    public String toString() {
        return "Rental Office Number: " + kontorNummer +
                "\nAddress: " + adresse +
                "\nPhone Number: " + telefonNummer +
                "\nNumber of Cars: " + biler.size();
    }

}
