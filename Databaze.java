import java.util.HashMap;
import java.util.Map;
import java.util.Set;
public class Databaze {
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
                System.out.println("Nazov: " + value.getNazov() + " Reziser: " + value.getReziser() + " Rok: " + value.getRok() + " Doporuceny Vek: " + ((AnimovanyFilm) value).getDoporucenyVek());
            else if (value instanceof HranyFilm)
                System.out.println("Nazov: " + value.getNazov() + " Reziser: " + value.getReziser() + " Rok: " + value.getRok());
        });

    }

    private Map<String,Film> prvkyDatabaze;


}
