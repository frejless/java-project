public class HranyFilm extends Film {


    public HranyFilm(String nazov, String reziser, int rok) {
        super(nazov, reziser, rok);
    }

    public boolean setHodnotenie(int hodnotenie) {
        if (hodnotenie >= 1 && hodnotenie <= 5) {
            super.hodnotenie = hodnotenie;
            return true;
        } else
            return false;
    }


}

