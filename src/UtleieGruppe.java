/**
 * Enum som representerer de ulike gruppene av utleiebiler basert på størrelse og pris.
 * Hver utleiegruppe er definert med en kode og en grunnpris for utleie.
 */
public enum UtleieGruppe {
    LITEN("A", 500),
    MELLOMSTOR("B", 750),
    STOR("C", 900),
    STASJONSVOGN("D", 750);
    private final String code;
    private final int grunnPris;

    UtleieGruppe(String code, int grunnPris){
        this.code = code;
        this.grunnPris = grunnPris;
    }
    public String getCode(){
        return code;
    }
    public int getGrunnPris(){
        return grunnPris;
    }
}
