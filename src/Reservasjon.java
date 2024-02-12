import java.time.LocalDateTime;

public class Reservasjon {

private static int teller = 0;
private int reservasjonsID;
private String utleieSted;
private String returSted;
private LocalDateTime utleieStart;
private LocalDateTime utleieSlutt;
private String bilKategori;
private int pris;
private Bil bil;

public Reservasjon(String utleiested, String returSted, LocalDateTime utleieStart, LocalDateTime utleieSlutt, String bilKategori, int pris, Bil bil) {
    this.reservasjonsID = ++teller;
    this.utleieSted = utleiested;
    this.returSted = returSted;
    this.utleieStart = utleieStart;
    this.utleieSlutt = utleieSlutt;
    this.bilKategori = bilKategori;
    this.pris = pris;
    this.bil = bil;
}

public Bil getBil(){
    return bil;
}
public int getReservasjonsID() {
    return reservasjonsID;
}

public void setReservasjonsID(int reservasjonsID) {
    this.reservasjonsID = reservasjonsID;
}

public String getUtleiested() {
    return utleieSted;
}

public void setUtleiested(String utleiested) {
    utleieSted = utleiested;
}

public String getRetursted() {
    return returSted;
}

public void setRetursted(String retursted) {
    returSted = retursted;
}

public LocalDateTime getUtleieStart() {
    return utleieStart;
}

public void setUtleieStart(LocalDateTime utleieStart) {
    utleieStart = utleieStart;
}

public LocalDateTime getUtleieSlutt() {
    return utleieSlutt;
}

public void setUtleieSlutt(LocalDateTime utleieSlutt) {
    utleieSlutt = utleieSlutt;
}

    public String getBilKategori() {
        return bilKategori;
    }

    public void setBilKategori(String bilKategori) {
        this.bilKategori = bilKategori;
    }

public int getPris() {
    return pris;
}

public void setPris(int pris) {
    pris = pris;
}
}
