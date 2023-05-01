public class HranyFilm extends Film {


    public HranyFilm(String nazov, String reziser, int rok) {
        super(nazov, reziser, rok);
    }

    public boolean sanitizeHodnotenie(int hodnotenie) {
        return hodnotenie >= 1 && hodnotenie <= 5;
    }


}

