public class AnimovanyFilm extends Film {

    private int doporucenyVek;

    public AnimovanyFilm(String nazov, String reziser, int rok, int doporucenyVek) {
        super(nazov, reziser, rok);
        this.doporucenyVek = doporucenyVek;
    }

    public int getDoporucenyVek() {
        return doporucenyVek;
    }

    public boolean sanitizeHodnotenie(int hodnotenie) {
        return hodnotenie >= 1 && hodnotenie <= 10;
    }
}
