import java.util.*;

public class Databaze {

    private Map<String,Film> prvkyDatabaze = new HashMap<>();
    private Map<String, ArrayList<Hodnotenie>> prvkyHodnotenia = new HashMap<>();

    Databaze() {}

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
                System.out.println("Nazov: " + value.getNazov() + " Reziser: " + value.getReziser() + " Rok: " + value.getRok() + " Doporuceny Vek: " + ((AnimovanyFilm) value).getDoporucenyVek());
            else if (value instanceof HranyFilm)
                System.out.println("Nazov: " + value.getNazov() + " Reziser: " + value.getReziser() + " Rok: " + value.getRok());
        });
    }

    public boolean checkFilmExistence(String nazov) {
        return prvkyDatabaze.containsKey(nazov);
    }

    public boolean setCiselneHodnotenie(String nazov, int hodnotenie) {
        if (prvkyDatabaze.get(nazov) instanceof HranyFilm)
            return ((HranyFilm) prvkyDatabaze.get(nazov)).sanitizeHodnotenie(hodnotenie);
        else if (prvkyDatabaze.get(nazov) instanceof AnimovanyFilm)
            return ((AnimovanyFilm) prvkyDatabaze.get(nazov)).sanitizeHodnotenie(hodnotenie);
        else
            return false;
    }

    public void FilmInfo(String nazov) {
        if (prvkyDatabaze.get(nazov) instanceof HranyFilm){
            System.out.println("Nazov: " + prvkyDatabaze.get(nazov).getNazov() + " Reziser: " + prvkyDatabaze.get(nazov).getReziser() + " Rok: " + prvkyDatabaze.get(nazov).getRok());
        }
        else if (prvkyDatabaze.get(nazov) instanceof AnimovanyFilm){
            System.out.println("Nazov: " + prvkyDatabaze.get(nazov).getNazov() + " Reziser: " + prvkyDatabaze.get(nazov).getReziser() + " Rok: " + prvkyDatabaze.get(nazov).getRok() + " Doporuceny Vek: " + ((AnimovanyFilm) prvkyDatabaze.get(nazov)).getDoporucenyVek());
        }
        ArrayList<Hodnotenie> hodnotenia = this.getHodnotenia(nazov);
        hodnotenia.sort(Comparator.comparing(Hodnotenie::getHodnotenie).reversed());
        for (Hodnotenie hodnotenie : hodnotenia)
            System.out.println("Hodnotenie: " + hodnotenie.getHodnotenie() + " Komentar: " + hodnotenie.getKomentar());
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

    public void addHodnotenie(String nazov, int hodnotenie, String komentar) {
        ArrayList<Hodnotenie> hodnotenia = this.prvkyHodnotenia.get(nazov);
        if (hodnotenia == null) {
            hodnotenia = new ArrayList<Hodnotenie>();
        }
        hodnotenia.add(new Hodnotenie(hodnotenie, komentar));
        this.prvkyHodnotenia.put(nazov, hodnotenia);


    }

    public ArrayList<Hodnotenie> getHodnotenia(String nazov) {
        return this.prvkyHodnotenia.get(nazov);
    }

}
