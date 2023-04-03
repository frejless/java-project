public class AnimovanyFilm  extends Film {

    private int doporucenyVek;

    public AnimovanyFilm(String nazov, String reziser, int rok, int doporucenyVek) {
        super(nazov, reziser, rok, 0);
        this.doporucenyVek = doporucenyVek;
    }

    public int getDoporucenyVek() {
        return doporucenyVek;
    }
}
