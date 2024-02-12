public class Adresse {
    private String gateAdresse;
    private String postNummer;
    private String postSted;

    public Adresse(String gateadresse, String postnummer, String poststed) {
        this.gateAdresse = gateadresse;
        this.postNummer = postnummer;
        this.postSted = poststed;
    }

    public String getGateadresse() {
        return gateAdresse;
    }

    public void setGateadresse(String gateadresse) {
        this.gateAdresse = gateadresse;
    }

    public String getPostnummer() {
        return postNummer;
    }

    public void setPostnummer(String postnummer) {
        this.postNummer = postnummer;
    }

    public String getPoststed() {
        return postSted;
    }

    public void setPoststed(String poststed) {
        this.postSted = poststed;
    }
}
