
import java.util.ArrayList;
import java.util.List;

interface FilmInterface {

    String getNazov();

    String getReziser();

    int getRok();


}

public class Film implements FilmInterface {

    private String nazov;
    private String reziser;
    private int rok;



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


}

