import java.util.HashMap;
import java.util.Map;

public class Databaze {

    private Map<String,Film> prvkyDatabaze;
    Databaze() {
        prvkyDatabaze = new HashMap<String,Film>();
    }

    public void setHranyFilm(String nazov, String reziser, int rok) {
        if (prvkyDatabaze.put(nazov, new HranyFilm(nazov, reziser, rok)) == null)
            System.out.println("Film bol pridany");
        else
            System.out.println("Film sa nepodarilo pridat");
    }

    public void setAnimovanyFilm(String nazov, String reziser, int rok, int doporucenyVek) {
        if (prvkyDatabaze.put(nazov, new AnimovanyFilm(nazov, reziser, rok, doporucenyVek)) == null)
            System.out.println("Film bol pridany");
        else
            System.out.println("Film sa nepodarilo pridat");
    }

    public void vypisDatabaze() {
        prvkyDatabaze.forEach((key, value) -> {
            if (value instanceof AnimovanyFilm)
                System.out.println("Nazov: " + value.getNazov() + " Reziser: " + value.getReziser() + " Rok: " + value.getRok() + " Doporuceny Vek: " + ((AnimovanyFilm) value).getDoporucenyVek() + " Hodnotenie: " + value.getHodnotenie() + " " + value.getKomentar());
            else if (value instanceof HranyFilm)
                System.out.println("Nazov: " + value.getNazov() + " Reziser: " + value.getReziser() + " Rok: " + value.getRok() + " Hodnotenie: " + value.getHodnotenie() + " " + value.getKomentar());
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
            System.out.println("Nazov: " + prvkyDatabaze.get(nazov).getNazov() + " Reziser: " + prvkyDatabaze.get(nazov).getReziser() + " Rok: " + prvkyDatabaze.get(nazov).getRok() + " Hodnotenie: " + prvkyDatabaze.get(nazov).getHodnotenie() + " " + prvkyDatabaze.get(nazov).getKomentar());
        else if (prvkyDatabaze.get(nazov) instanceof AnimovanyFilm)
            System.out.println("Nazov: " + prvkyDatabaze.get(nazov).getNazov() + " Reziser: " + prvkyDatabaze.get(nazov).getReziser() + " Rok: " + prvkyDatabaze.get(nazov).getRok() + " Doporuceny Vek: " + ((AnimovanyFilm) prvkyDatabaze.get(nazov)).getDoporucenyVek() + " Hodnotenie: " + prvkyDatabaze.get(nazov).getHodnotenie() + " " + prvkyDatabaze.get(nazov).getKomentar());
        else if (prvkyDatabaze.get(nazov) == null)
            System.out.println("Film nie je v databaze");
    }

    public void typFilmu (String nazov) {
        if (prvkyDatabaze.get(nazov) instanceof HranyFilm)
            System.out.println(prvkyDatabaze.get(nazov).getNazov() + " je hrany film, mozete mu dat 1-5 hviezdiciek");
        else if (prvkyDatabaze.get(nazov) instanceof AnimovanyFilm)
            System.out.println(prvkyDatabaze.get(nazov).getNazov() + " je animovany film, mozete mu dat 1-10 bodov");
    }

    public void vymazFilm(String nazov) {
        if (prvkyDatabaze.remove(nazov) != null)
            System.out.println("Film bol vymazany");
        else
            System.out.println("Film sa nepodarilo vymazat");
    }

    public void setKomentarFilmu(String nazov, String komentar) {
        prvkyDatabaze.get(nazov).setKomentar(komentar);

    }
}
