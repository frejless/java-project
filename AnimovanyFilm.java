public class AnimovanyFilm  extends Film {

    private int doporucenyVek;

    public AnimovanyFilm(String nazov, String reziser, int rok, int hodnotenie, int doporucenyVek) {
        super(nazov, reziser, rok, hodnotenie);
        this.doporucenyVek = doporucenyVek;
    }

    public int getDoporucenyVek() {
        return doporucenyVek;
    }
}
