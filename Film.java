interface FilmInterface {

    String getNazov();

    String getReziser();

    int getRok();

    int getHodnotenie();

    String getKomentar();

}

public class Film implements FilmInterface {
    private String nazov;
    private String reziser;
    private int rok;
    int hodnotenie;

    private String komentar;


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

    @Override
    public String getKomentar() {
        if (komentar == null) {
            return "";
        }
        else {
            return komentar;
        }
    }

    public void setKomentar(String komentar) {
        this.komentar = komentar;
    }
}

