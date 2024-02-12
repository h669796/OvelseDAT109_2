import java.time.LocalDateTime;

/**
 * representerer en utleiekontrakt for bilutleie
 */
public class UtleieKontrakt {

    private static int teller = 0;
    private int kontraktID;
    private LocalDateTime henteDato;
    private LocalDateTime returDato;
    private int hentetKm;
    private int returnertKm;
    private int totalPris;

    public BetalingsKort getKort() {
        return kort;
    }

    private BetalingsKort kort;
    private Reservasjon reservasjon;

    /**
     * opretter en ny utleiekontrakt
     * @param henteDato
     * @param returDato
     * @param hentetKm
     * @param returnertKm
     * @param reservasjon
     * @param kort
     */
    public UtleieKontrakt(LocalDateTime henteDato, LocalDateTime returDato, int hentetKm, int returnertKm, Reservasjon reservasjon, BetalingsKort kort) {
        this.kontraktID = ++teller;
        this.henteDato = henteDato;
        this.returDato = returDato;
        this.returnertKm = returnertKm;
        this.reservasjon = reservasjon;
        this.kort = kort;
    }

    public Reservasjon getReservasjon() {
        return reservasjon;
    }
    public int getKontraktId() {
        return kontraktID;
    }

    public void setKontraktId(int kontraktId) {
        kontraktId = kontraktId;
    }

    public LocalDateTime getHenteDato() {
        return henteDato;
    }

    public void setHenteDato(LocalDateTime henteDato) {
        henteDato = henteDato;
    }

    public LocalDateTime getReturDato() {
        return returDato;
    }

    public void setReturDato(LocalDateTime returDato) {
        returDato = returDato;
    }

    public int getHentetKm() {
        return hentetKm;
    }

    public void setHentetKm(int hentetKm) {
        this.hentetKm = hentetKm;
    }
    public int getReturnertKmKm() {
        return returnertKm;
    }

    public void setReturnertKm(int returnertKm) {
        this.returnertKm = returnertKm;
    }

}
