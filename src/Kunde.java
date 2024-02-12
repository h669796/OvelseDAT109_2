
/**
 * Representerer en kunde med informasjon som kundeID, fornavn, etternavn, adresse og telefonnummer.
 * KundeID genereres automatisk og inkrementeres for hver ny instans av klassen.
 */
public class Kunde {

    private static int nesteID = 1;
    private int kundeID;
    private String fornavn;
    private String etternavn;
    private Adresse adresse;
    private String telefonnummer;

    /**
     * opretter en ny kunde
     * @param fornavn
     * @param etternavn
     * @param adresse
     * @param telefonnummer
     */

    public Kunde(String fornavn, String etternavn, Adresse adresse, String telefonnummer) {
        this.kundeID = nesteID++;
        this.fornavn = fornavn;
        this.etternavn = etternavn;
        this.adresse = adresse;
        this.telefonnummer = telefonnummer;
        System.out.println("Ny Kunde ID: " + this.kundeID); // Legger til en utskrift for debugging
    }

    public int getKundeID(){
            return kundeID;

    }
    public String getFornavn() {
        return fornavn;
    }

    public void setFornavn(String fornavn) {
        this.fornavn = fornavn;
    }

    public String getEtternavn() {
        return etternavn;
    }

    public void setEtternavn(String etternavn) {
        this.etternavn = etternavn;
    }

    public Adresse getAdresse() {
        return adresse;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }

    public String getTelefonnummer() {
        return telefonnummer;
    }

    public void setTelefonnummer(String telefonnummer) {
        this.telefonnummer = telefonnummer;
    }
}
