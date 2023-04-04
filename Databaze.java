import java.util.HashMap;
import java.util.Map;

public class Databaze {

    private Map<String,Film> prvkyDatabaze;
    Databaze() {
        prvkyDatabaze = new HashMap<String,Film>();
    }

    public boolean setHranyFilm(String nazov, String reziser, int rok) {
        if (prvkyDatabaze.put(nazov, new HranyFilm(nazov, reziser, rok)) == null)
            return true;
        else
            return false;
    }

    public boolean setAnimovanyFilm(String nazov, String reziser, int rok, int doporucenyVek) {
        if (prvkyDatabaze.put(nazov, new AnimovanyFilm(nazov, reziser, rok, doporucenyVek)) == null)
            return true;
        else
            return false;
    }

    public void vypisDatabaze() {

        prvkyDatabaze.forEach((key, value) -> {
            if (value instanceof AnimovanyFilm)
                System.out.println("Nazov: " + value.getNazov() + " Reziser: " + value.getReziser() + " Rok: " + value.getRok() + " Doporuceny Vek: " + ((AnimovanyFilm) value).getDoporucenyVek() + " Hodnotenie: " + value.getHodnotenie());
            else if (value instanceof HranyFilm)
                System.out.println("Nazov: " + value.getNazov() + " Reziser: " + value.getReziser() + " Rok: " + value.getRok() + " Hodnotenie: " + value.getHodnotenie());
        });

    }

    public boolean setHodnotenieFilmu(String nazov, int hodnotenie) {
        if (prvkyDatabaze.get(nazov) instanceof HranyFilm)
            return ((HranyFilm) prvkyDatabaze.get(nazov)).setHodnotenie(hodnotenie);
        else if (prvkyDatabaze.get(nazov) instanceof AnimovanyFilm)
            return ((AnimovanyFilm) prvkyDatabaze.get(nazov)).setHodnotenie(hodnotenie);
        else
            return false;
    }

    public void FilmInfo(String nazov) {
        if (prvkyDatabaze.get(nazov) instanceof HranyFilm)
            System.out.println("Nazov: " + prvkyDatabaze.get(nazov).getNazov() + " Reziser: " + prvkyDatabaze.get(nazov).getReziser() + " Rok: " + prvkyDatabaze.get(nazov).getRok() + " Hodnotenie: " + prvkyDatabaze.get(nazov).getHodnotenie());
        else if (prvkyDatabaze.get(nazov) instanceof AnimovanyFilm)
            System.out.println("Nazov: " + prvkyDatabaze.get(nazov).getNazov() + " Reziser: " + prvkyDatabaze.get(nazov).getReziser() + " Rok: " + prvkyDatabaze.get(nazov).getRok() + " Doporuceny Vek: " + ((AnimovanyFilm) prvkyDatabaze.get(nazov)).getDoporucenyVek() + " Hodnotenie: " + prvkyDatabaze.get(nazov).getHodnotenie());
        else if (prvkyDatabaze.get(nazov) == null)
            System.out.println("Film nie je v databaze");
    }

}
