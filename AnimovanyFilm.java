public class AnimovanyFilm  implements Film{
    private String nazov;
    private String reziser;
    private int rok;
    private int hodnotenie;

    private int doporucenyVek;

    public AnimovanyFilm(String nazov, String reziser, int rok, int hodnotenie, int doporucenyVek) {
        this.nazov = nazov;
        this.reziser = reziser;
        this.rok = rok;
        this.hodnotenie = hodnotenie;
    }

    @Override
    public String getNazov() {
        return null;
    }

    @Override
    public String getReziser() {
        return null;
    }

    @Override
    public int getRok() {
        return 0;
    }

    @Override
    public int getHodnotenie() {
        return 0;
    }

    public int getDoporucenyVek() {
        return doporucenyVek;
    }
}
