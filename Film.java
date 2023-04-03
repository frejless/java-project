interface FilmInterface {

    public String getNazov();

    public String getReziser();

    public int getRok();

    public int getHodnotenie();

}

public class Film implements FilmInterface {
    private String nazov;
    private String reziser;
    private int rok;
    int hodnotenie;


    public Film(String nazov, String reziser, int rok) {
        this.nazov = nazov;
        this.reziser = reziser;
        this.rok = rok;
    }

    @Override
    public String getNazov() {
        return nazov;
    }

    @Override
    public String getReziser() {
        return reziser;
    }

    @Override
    public int getRok() {
        return rok;
    }

    @Override
    public int getHodnotenie() {
        return hodnotenie;
    }

}
