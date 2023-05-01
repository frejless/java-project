import java.io.*;
import java.sql.*;
import java.util.*;

public class Databaze {

    private Map<String,Film> prvkyDatabaze = new HashMap<>();
    private Map<String, ArrayList<Hodnotenie>> prvkyHodnotenia = new HashMap<>();
    private Map<String, List<String>> movieActors = new HashMap<>();

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
                System.out.println("Nazov: " + value.getNazov() + " Reziser: " + value.getReziser() + " Rok: " + value.getRok() + " Doporuceny Vek: " + ((AnimovanyFilm) value).getDoporucenyVek() + " Herci : " + getActors(value.getNazov()));
            else if (value instanceof HranyFilm)
                System.out.println("Nazov: " + value.getNazov() + " Reziser: " + value.getReziser() + " Rok: " + value.getRok() + " Herci : " + getActors(value.getNazov()));
        });
    }
    public boolean checkFilmExistence(String nazov) {
        return !prvkyDatabaze.containsKey(nazov);
    }
    public boolean setCiselneHodnotenie(String nazov, int hodnotenie) {
        if (prvkyDatabaze.get(nazov) instanceof HranyFilm)
            return ((HranyFilm) prvkyDatabaze.get(nazov)).sanitizeHodnotenie(hodnotenie);
        else if (prvkyDatabaze.get(nazov) instanceof AnimovanyFilm)
            return ((AnimovanyFilm) prvkyDatabaze.get(nazov)).sanitizeHodnotenie(hodnotenie);
        else
            return false;
    }
    public void getFilmInfo(String nazov) {
        if (prvkyDatabaze.get(nazov) instanceof HranyFilm){
            System.out.println("Nazov: " + prvkyDatabaze.get(nazov).getNazov() + " Reziser: " + prvkyDatabaze.get(nazov).getReziser() + " Rok: " + prvkyDatabaze.get(nazov).getRok() + " Herci : " + getActors(nazov));
        }
        else if (prvkyDatabaze.get(nazov) instanceof AnimovanyFilm){
            System.out.println("Nazov: " + prvkyDatabaze.get(nazov).getNazov() + " Reziser: " + prvkyDatabaze.get(nazov).getReziser() + " Rok: " + prvkyDatabaze.get(nazov).getRok() + " Doporuceny Vek: " + ((AnimovanyFilm) prvkyDatabaze.get(nazov)).getDoporucenyVek() + " Herci : " + getActors(nazov));
        }
        ArrayList<Hodnotenie> hodnotenia = this.getHodnotenia(nazov);
        if (hodnotenia == null || hodnotenia.size() == 0)
            System.out.println("Film este nemal ziadne hodnotenia");
        else {
            hodnotenia.sort(Comparator.comparing(Hodnotenie::getHodnotenie).reversed());
            for (Hodnotenie hodnotenie : hodnotenia)
                System.out.println("Hodnotenie: " + hodnotenie.getHodnotenie() + " Komentar: " + hodnotenie.getKomentar());
        }
    }
    public boolean typFilmu (String nazov) {
        if (prvkyDatabaze.get(nazov) instanceof HranyFilm){
            return true;
        }
        else if (prvkyDatabaze.get(nazov) instanceof AnimovanyFilm) {
            return false;
        }
        else
            return false;
    }
    public void vymazFilm(String nazov) {
        prvkyDatabaze.remove(nazov);
        prvkyHodnotenia.remove(nazov);
        movieActors.remove(nazov);
        System.out.println("film bol vymazany");
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
    public void addActor(String nazov, String actor) {
        List<String> actors = movieActors.get(nazov);
        if (actors == null) {
            actors = new ArrayList<String>();
        }
        actors.add(actor);
        movieActors.put(nazov, actors);
    }
    public String getActors(String nazov) {
        if (movieActors.get(nazov) == null)
            return "nezadani";
        else
            return String.join(", ", movieActors.get(nazov));
    }
    public void getActorsInMultipleFilms() {
        Map<String, List<String>> actors = new HashMap<>();
        for (Map.Entry<String, List<String>> entry : movieActors.entrySet()) {
            String movie = entry.getKey();
            for (String actor : entry.getValue()) {
                if (!actors.containsKey(actor)) {
                    actors.put(actor, new ArrayList<>());
                }
                actors.get(actor).add(movie);
            }
        }
        for (Map.Entry<String, List<String>> entry : actors.entrySet()) {
            String actor = entry.getKey();
            List<String> movies = entry.getValue();
            if (movies.size() > 1) {
                System.out.println(actor + " bol v týchto filmoch: " + String.join(", ", movies));
            }
        }
    }
    public void getFilmsOfActor(String meno) {
        for (Map.Entry<String, List<String>> entry : movieActors.entrySet()) {
            String movie = entry.getKey();
            for (String actor : entry.getValue()) {
                if (actor.equals(meno)) {
                    System.out.println("Film: " + movie);
                }
            }
        }
    }
    public void editHranyFilm (String nazov, String novyNazov, String novyReziser, int novyRok) {
        if(nazov.equals(novyNazov)){
            prvkyDatabaze.put(nazov, new HranyFilm(novyNazov, novyReziser, novyRok));
            System.out.println("Film bol upraveny");
        }
        else{
            prvkyDatabaze.put(novyNazov, new HranyFilm(novyNazov, novyReziser, novyRok));
            prvkyDatabaze.remove(nazov);
            prvkyHodnotenia.put(novyNazov, prvkyHodnotenia.get(nazov));
            prvkyHodnotenia.remove(nazov);
            movieActors.put(novyNazov, movieActors.get(nazov));
            movieActors.remove(nazov);
            System.out.println("Film bol upraveny");
        }
    }
    public String getReziser(String nazov) {
        return prvkyDatabaze.get(nazov).getReziser();
    }
    public String getNazov(String nazov) {
        return prvkyDatabaze.get(nazov).getNazov();
    }
    public int getRok(String nazov) {
        return prvkyDatabaze.get(nazov).getRok();
    }
    public int getVek(String nazov) {
        return ((AnimovanyFilm) prvkyDatabaze.get(nazov)).getDoporucenyVek();
    }
    public void editAnimovanyFilm(String nazov, String novyNazov, String novyReziser, int novyRok, int novyVek) {
        if(nazov.equals(novyNazov)){
            prvkyDatabaze.put(nazov, new AnimovanyFilm(novyNazov, novyReziser, novyRok, novyVek));
            System.out.println("Film bol upraveny");
        }
        else {
            prvkyDatabaze.put(novyNazov, new AnimovanyFilm(novyNazov, novyReziser, novyRok, novyVek));
            prvkyDatabaze.remove(nazov);
            prvkyHodnotenia.put(novyNazov, prvkyHodnotenia.get(nazov));
            prvkyHodnotenia.remove(nazov);
            movieActors.put(novyNazov, movieActors.get(nazov));
            movieActors.remove(nazov);
            System.out.println("Film bol upraveny");
        }
    }
    public void saveToFile(String nazov) {
        if (prvkyDatabaze.get(nazov) instanceof HranyFilm) {
            try {
                FileWriter fw = new FileWriter(nazov + ".txt");
                fw.write("Nazov: " + prvkyDatabaze.get(nazov).getNazov() + " Reziser: " + prvkyDatabaze.get(nazov).getReziser() + " Rok: " + prvkyDatabaze.get(nazov).getRok() + " Herci : " + getActors(nazov));
                hodnotenieForSavingToFile(nazov, fw);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if (prvkyDatabaze.get(nazov) instanceof AnimovanyFilm) {
            try {
                FileWriter fw = new FileWriter(nazov + ".txt");
                fw.write("Nazov: " + prvkyDatabaze.get(nazov).getNazov() + " Reziser: " + prvkyDatabaze.get(nazov).getReziser() + " Rok: " + prvkyDatabaze.get(nazov).getRok() + " Doporuceny Vek: " + ((AnimovanyFilm) prvkyDatabaze.get(nazov)).getDoporucenyVek() + " Herci : " + getActors(nazov));
                hodnotenieForSavingToFile(nazov, fw);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        else
            System.out.println("Nepodarilo sa ulozit film");
    }
    private void hodnotenieForSavingToFile(String nazov, FileWriter fw) throws IOException {
        ArrayList<Hodnotenie> hodnotenia = this.getHodnotenia(nazov);
        BufferedWriter bw = new BufferedWriter(fw);
        if (hodnotenia == null || hodnotenia.size() == 0){
            bw.newLine();
            bw.write("Film este nemal ziadne hodnotenia");
        }
        else {
            hodnotenia.sort(Comparator.comparing(Hodnotenie::getHodnotenie).reversed());
            for (Hodnotenie hodnotenie : hodnotenia) {
                bw.newLine();
                bw.write("Hodnotenie: " + hodnotenie.getHodnotenie() + " Komentar: " + hodnotenie.getKomentar());
            }
        }
        bw.close();
    }
    public void loadFromFile(String nazov) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(nazov + ".txt"));
            String line = br.readLine();
            String name = line.substring(line.indexOf("Nazov: ") + 7, line.indexOf(" Reziser: "));
            String director = line.substring(line.indexOf("Reziser: ") + 9, line.indexOf(" Rok: "));
            Film film;
            if (line.contains("Doporuceny Vek")) {
                int year = Integer.parseInt(line.substring(line.indexOf("Rok: ") + 5, line.indexOf(" Doporuceny Vek: ")));
                int recommendedAge = Integer.parseInt(line.substring(line.indexOf("Doporuceny Vek: ") + 16, line.indexOf(" Herci : ")));
                film = new AnimovanyFilm(name, director, year, recommendedAge);

            }
            else {
                int year = Integer.parseInt(line.substring(line.indexOf("Rok: ") + 5, line.indexOf(" Herci : ")));
                film = new HranyFilm(name, director, year);

            }
            prvkyDatabaze.put(nazov, film);
            String actors = line.substring(line.indexOf("Herci : ") + 8);
            String[] actorsArray = actors.split(", ");
            for (String actor : actorsArray) {
                addActor(nazov, actor);
            }
            while ((line = br.readLine()) != null) {
                if (line.startsWith("Hodnotenie: ")) {
                    int rating = Integer.parseInt(line.substring(line.indexOf("Hodnotenie: ") + 12, line.indexOf(" Komentar: ")));
                    String comment = line.substring(line.indexOf("Komentar: ") + 10);
                    addHodnotenie(nazov, rating, comment);
                }
            }
            br.close();
        }
        catch (IOException e) {
            System.out.println("nepodarilo sa nacitat film");
        }
    }
    public void deleteActors(String nazov) {
        movieActors.remove(nazov);
    }
    public Connection connectToDatabase(String s) {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection connection = DriverManager.getConnection("jdbc:sqlite:" + s);
            return connection;
        }
        catch (Exception e) {
            System.out.println("Nepodarilo sa pripojit k databaze");
            return null;
        }
    }
    public void createTable(Connection conn) {
        try {
            Statement statement = conn.createStatement();
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS movies (name TEXT, director TEXT, year INTEGER, recommendedAge INTEGER)");
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS actors (name TEXT, movie TEXT)");
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS ratings (movie TEXT, rating INTEGER, comment TEXT)");
        }
        catch (Exception e) {
            System.out.println("Nepodarilo sa vytvorit tabulku");
        }
    }
    public void saveDatabase(Connection conn) {
        try {
            Statement statement = conn.createStatement();
            statement.executeUpdate("DELETE FROM movies");
            statement.executeUpdate("DELETE FROM actors");
            statement.executeUpdate("DELETE FROM ratings");
            for (Film film : prvkyDatabaze.values()) {
                if (film instanceof HranyFilm) {
                    statement.executeUpdate("INSERT INTO movies (name, director, year) VALUES ('" + film.getNazov() + "', '" + film.getReziser() + "', " + film.getRok() + ")");
                }
                else {
                    statement.executeUpdate("INSERT INTO movies (name, director, year, recommendedAge) VALUES ('" + film.getNazov() + "', '" + film.getReziser() + "', " + film.getRok() + ", " + ((AnimovanyFilm) film).getDoporucenyVek() + ")");
                }
                if (movieActors.get(film.getNazov()) != null) {
                    for (String actor : movieActors.get(film.getNazov())) {
                        statement.executeUpdate("INSERT INTO actors (name, movie) VALUES ('" + actor + "', '" + film.getNazov() + "')");
                    }
                }
                if (prvkyHodnotenia.get(film.getNazov()) != null) {
                    for (Hodnotenie hodnotenie : prvkyHodnotenia.get(film.getNazov())) {
                        statement.executeUpdate("INSERT INTO ratings (movie, rating, comment) VALUES ('" + film.getNazov() + "', " + hodnotenie.getHodnotenie() + ", '" + hodnotenie.getKomentar() + "')");
                    }
                }
            }
        }
        catch (Exception e) {
            System.out.println("Nepodarilo sa ulozit databazu");
        }
    }
    public void loadDatabase(Connection conn) {
        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM movies");
            while (rs.next()) {
                String name = rs.getString("name");
                String director = rs.getString("director");
                int year = rs.getInt("year");
                int recommendedAge = rs.getInt("recommendedAge");
                Film film;
                if (recommendedAge == 0) {
                    film = new HranyFilm(name, director, year);
                }
                else {
                    film = new AnimovanyFilm(name, director, year, recommendedAge);
                }
                prvkyDatabaze.put(name, film);
            }
            rs = statement.executeQuery("SELECT * FROM actors");
            while (rs.next()) {
                String name = rs.getString("name");
                String movie = rs.getString("movie");
                addActor(movie, name);
            }
            rs = statement.executeQuery("SELECT * FROM ratings");
            while (rs.next()) {
                String movie = rs.getString("movie");
                int rating = rs.getInt("rating");
                String comment = rs.getString("comment");
                addHodnotenie(movie, rating, comment);
            }
        }
        catch (Exception e) {
            System.out.println("Nepodarilo sa nacitat databazu");
        }
    }
}
