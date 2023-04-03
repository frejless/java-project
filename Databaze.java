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

    public void vypisDatabaze() {

        prvkyDatabaze.forEach((key, value) -> {
            System.out.println("Nazov: " + value.getNazov() + " Reziser: " + value.getReziser() + " Rok: " + value.getRok());
        });

    }



    private Map<String,Film> prvkyDatabaze;


}
