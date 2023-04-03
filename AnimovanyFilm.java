public class AnimovanyFilm extends Film {

    private int doporucenyVek;

    public AnimovanyFilm(String nazov, String reziser, int rok, int doporucenyVek) {
        super(nazov, reziser, rok);
        this.doporucenyVek = doporucenyVek;
    }

    public int getDoporucenyVek() {
        return doporucenyVek;
    }

    public boolean setHodnotenie(int hodnotenie) {
        if (hodnotenie >= 1 && hodnotenie <= 10) {
            super.hodnotenie = hodnotenie;
            return true;
        } else
            return false;
    }
}
