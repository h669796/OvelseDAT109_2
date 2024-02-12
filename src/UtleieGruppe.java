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
