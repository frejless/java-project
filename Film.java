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
    private int hodnotenie;

    
    public Film(String nazov, String reziser, int rok, int hodnotenie) {
        this.nazov = nazov;
        this.reziser = reziser;
        this.rok = rok;
        this.hodnotenie = hodnotenie;
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
