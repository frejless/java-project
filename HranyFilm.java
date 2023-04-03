public class HranyFilm implements Film {
    private String nazov;
    private String reziser;
    private int rok;
    private int hodnotenie;


    public HranyFilm(String nazov, String reziser, int rok) {
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

