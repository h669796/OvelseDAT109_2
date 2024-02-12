public class Bil {

    private UtleieGruppe utleieGruppe;
    private String regnr;
    private String merke;
    private String modell;
    private String farge;
    private int antallKmKjort;
    private Boolean status;

    public int getAntallKmKjort() {
        return antallKmKjort;
    }
    public void setAntallKmKjort(int km){
        antallKmKjort = km;
    }
    public UtleieGruppe getUtleieGruppe() {
        return UtleieGruppe;
    }

    public Bil(String regnr, String merke, String modell, String farge, UtleieGruppe utleiegruppe, int antallKmKjort) {
        this.regnr = regnr;
        this.merke = merke;
        this.modell = modell;
        this.farge = farge;
        this.UtleieGruppe = utleiegruppe;
        this.antallKmKjort = antallKmKjort;
        this.status = true;
    }

    public String getRegnr() {
        return regnr;
    }

    public void setRegnr(String regnr) {
        regnr = regnr;
    }

    public String getMerke() {
        return merke;
    }

    public void setMerke(String merke) {
        merke = merke;
    }

    public String getModell() {
        return modell;
    }

    public void setModell(String modell) {
        modell = modell;
    }

    public String getFarge() {
        return farge;
    }

    public void setFarge(String farge) {
        farge = farge;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        status = status;
    }
    @Override
    public String toString() {
        return "Bil{" +
                "registreringsnummer='" + regnr + "\n" +
                ", merke='" + merke + "\n" +
                ", modell='" + modell + "\n" +
                ", utleieGruppe=" + utleieGruppe + "\n" +
                ", ledig=" + status +
                '}';
    }
}
