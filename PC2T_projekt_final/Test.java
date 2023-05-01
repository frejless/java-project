import java.sql.Connection;
import java.util.Scanner;
public class Test {
    static Databaze mojeDatabaze=new Databaze();
    static Scanner sc1 = new Scanner(System.in);
    static Scanner sc2 = new Scanner(System.in);

    public static int pouzeCelaCisla(Scanner sc) {
        int cislo;
        try {
            cislo = sc.nextInt();
        } catch (Exception e) {
            System.out.println("zadajte prosim cislo ");
            sc.nextLine();
            cislo = pouzeCelaCisla(sc);
        }
        return cislo;
    }

    public static int overenieVeku(Scanner sc) {
        int vek;
        try {
            vek = sc.nextInt();
            if (vek <= 0 || vek >= 100) {
                System.out.println("zadajte prosim platne cislo (1-99)");
                vek = overenieVeku(sc);
            }
        } catch (Exception e) {
            System.out.println("zadajte prosim cislo");
            sc.nextLine();
            vek = overenieVeku(sc);
        }
        return vek;
    }

    public static int overenieRoku(Scanner sc) {
        int vek;
        try {
            vek = sc.nextInt();
            if (vek < 1885 || vek > 2023) {
                System.out.println("zadajte prosim platny rok (1885-2023)");
                vek = overenieRoku(sc);
            }
        } catch (Exception e) {
            System.out.println("zadajte prosim cislo");
            sc.nextLine();
            vek = overenieRoku(sc);
        }
        return vek;
    }



    public static void main(String[] args) {
        Connection conn = mojeDatabaze.connectToDatabase("filmy.db");
        mojeDatabaze.loadDatabase(conn);
        int volba;
        boolean run = true;

        while(run) {
            System.out.println("Vyberte pozadovanu cinnost:");
            System.out.println("1 .. pridat novy film");
            System.out.println("2 .. upravit film");
            System.out.println("3 .. zmazat film");
            System.out.println("4 .. pridat hodnotenie");
            System.out.println("5 .. vypis filmov");
            System.out.println("6 .. vyhladat film");
            System.out.println("7 .. herci vo viacerych filmoch");
            System.out.println("8 .. vyhladat filmy herca");
            System.out.println("9 .. ulozit film do suboru");
            System.out.println("10 .. nacitat film zo suboru");
            System.out.println("11 .. koniec programu");

            volba = pouzeCelaCisla(sc1);

            switch (volba) {
                case 1 -> addFilm();
                case 2 -> editFilm();
                case 3 -> deleteFilm();
                case 4 -> addRating();
                case 5 -> mojeDatabaze.vypisDatabaze();
                case 6 -> FilmInfo();
                case 7 -> actorsInMultipleFilms();
                case 8 -> FilmsOfActor();
                case 9 -> FilmToFile();
                case 10 -> FilmFromFile();
                case 11 -> {
                    mojeDatabaze.createTable(conn);
                    mojeDatabaze.saveDatabase(conn);
                    run = false;
                }

            }
        }
    }
    static void addFilm() {
        while (true) {
            System.out.println("Vyberte druh filmu :\n1 .. Hrany film\n2 .. Animovany film");
            int volba = pouzeCelaCisla(sc1);
            if (volba == 1){
                System.out.println("Zadajte nazov filmu :");
                String nazov = sc2.nextLine();
                if (!mojeDatabaze.checkFilmExistence(nazov)) {
                    System.out.println("Film uz je v databaze, mozete ho upravit");
                    return;
                }
                System.out.println("Zadajte rezisera filmu :");
                String reziser = sc2.nextLine();
                System.out.println("Zadajte rok filmu :");
                int rok = overenieRoku(sc1);
                while (true) {
                    System.out.println("Zadajte meno herca alebo 'koniec' pre ukončenie: ");
                    String herec = sc2.nextLine();
                    if (herec.equals("koniec")) {
                        break;
                    } else
                        mojeDatabaze.addActor(nazov, herec);
                }
                mojeDatabaze.setHranyFilm(nazov, reziser, rok);
                break;
            }
            else if (volba == 2) {
                System.out.println("Zadajte nazov filmu :");
                String nazov = sc2.nextLine();
                if (!mojeDatabaze.checkFilmExistence(nazov)) {
                    System.out.println("Film uz je v databaze, mozete ho upravit");
                    return;
                }
                System.out.println("Zadajte rezisera filmu :");
                String reziser = sc2.nextLine();
                System.out.println("Zadajte rok filmu :");
                int rok = overenieRoku(sc1);
                System.out.println("Zadajte doporuceny vek divaka :");
                int doporucenyVek = overenieVeku(sc1);
                while (true) {
                    System.out.println("Zadajte meno animatora alebo 'koniec' pre ukončenie: ");
                    String herec = sc2.nextLine();
                    if (herec.equals("koniec")) {
                        break;
                    } else
                        mojeDatabaze.addActor(nazov, herec);
                }
                mojeDatabaze.setAnimovanyFilm(nazov, reziser, rok, doporucenyVek);
                break;
            }
        }
    }
    static void addRating() {
        System.out.println("Zadajte nazov filmu :");
        String nazov = sc2.nextLine();
        if (mojeDatabaze.checkFilmExistence(nazov)) {
            System.out.println("Film neexistuje");
            return;
        }
        int hodnotenie;
        if (mojeDatabaze.typFilmu(nazov))
            System.out.println(nazov + " je hrany film, mozete mu dat 1-5 hviezdiciek");
        else
            System.out.println(nazov + " je animovany film, mozete mu dat 1-10 bodov");
        mojeDatabaze.typFilmu(nazov);
        System.out.println("Zadajte hodnotenie filmu :");
        while(true) {
            hodnotenie = pouzeCelaCisla(sc1);
            if (mojeDatabaze.setCiselneHodnotenie(nazov, hodnotenie)) {
                System.out.println("Hodnotenie filmu bolo nastavene");
                break;
            }
            else
                System.out.println("zadajte platne cislo");
        }
        System.out.println("Chcete pridat komentar k filmu ? (y/n)");
        while(true) {
            String odpoved = sc2.nextLine();
            if (odpoved.equals("y")) {
                System.out.println("Zadajte komentar :");
                String komentar = sc2.nextLine();
                mojeDatabaze.addHodnotenie(nazov, hodnotenie, komentar);
                break;
            } else if (odpoved.equals("n")) {
                System.out.println("Komentar nebol pridany");
                mojeDatabaze.addHodnotenie(nazov, hodnotenie, null);
                break;
            } else
                System.out.println("Neplatna odpoved, zadajte y/n");
        }
    }
    static void deleteFilm() {
        System.out.println("Zadajte nazov filmu :");
        String nazov = sc2.nextLine();
        if (mojeDatabaze.checkFilmExistence(nazov)) {
            System.out.println("Film neexistuje");
            return;
        }
        mojeDatabaze.vymazFilm(nazov);
    }
    static void FilmInfo() {
        System.out.println("Zadajte nazov filmu :");
        String nazov = sc2.nextLine();
        if (mojeDatabaze.checkFilmExistence(nazov)) {
            System.out.println("Film neexistuje");
            return;
        }
        mojeDatabaze.getFilmInfo(nazov);
    }
    static void actorsInMultipleFilms() {
        mojeDatabaze.getActorsInMultipleFilms();
    }
    static void FilmsOfActor() {
        System.out.println("Zadajte meno herca :");
        String meno = sc2.nextLine();
        mojeDatabaze.getFilmsOfActor(meno);
    }
    static void editFilm() {
        System.out.println("Zadajte nazov filmu :");
        String nazov = sc2.nextLine();
        if (mojeDatabaze.checkFilmExistence(nazov)) {
            System.out.println("Film neexistuje");
            return;
        }
        String novyNazov;
        String novyReziser;
        int novyRok;
        int novyVek;
        if (mojeDatabaze.typFilmu(nazov)){
            System.out.println("Chcete zmenit nazov filmu ? (y/n)");
            while(true) {
                String odpoved = sc2.nextLine();
                if (odpoved.equals("y")) {
                    System.out.println("Zadajte novy nazov filmu :");
                    novyNazov = sc2.nextLine();
                    if (!mojeDatabaze.checkFilmExistence(novyNazov)) {
                        System.out.println("Film s takymto nazvom uz existuje");
                        return;
                    }
                    break;
                } else if (odpoved.equals("n")) {
                    novyNazov = mojeDatabaze.getNazov(nazov);
                    break;
                } else
                    System.out.println("Neplatna odpoved, zadajte y/n");
            }
            System.out.println("Chcete zmenit rezisera filmu ? (y/n)");
            while(true) {
                String odpoved = sc2.nextLine();
                if (odpoved.equals("y")) {
                    System.out.println("Zadajte novy rezisera filmu :");
                    novyReziser = sc2.nextLine();
                    break;
                } else if (odpoved.equals("n")) {
                    novyReziser = mojeDatabaze.getReziser(nazov);
                    break;
                } else
                    System.out.println("Neplatna odpoved, zadajte y/n");
            }
            System.out.println("Chcete zmenit rok filmu ? (y/n)");
            while(true) {
                String odpoved = sc2.nextLine();
                if (odpoved.equals("y")) {
                    System.out.println("Zadajte novy rok filmu :");
                    novyRok = overenieRoku(sc1);
                    break;
                } else if (odpoved.equals("n")) {
                    novyRok = mojeDatabaze.getRok(nazov);
                    break;
                } else
                    System.out.println("Neplatna odpoved, zadajte y/n");
            }
            System.out.println("Chcete zmenit zoznam hercov ? (y/n)");
            while(true) {
                String odpoved = sc2.nextLine();
                if (odpoved.equals("y")) {
                    while (true) {
                        System.out.println("1 .. pridat k aktualnemu zoznamu\n2 .. novy zoznam");
                        int odpoved2 = pouzeCelaCisla(sc1);
                        if (odpoved2 == 1) {
                            while (true) {
                                System.out.println("Zadajte meno herca alebo 'koniec' pre ukončenie: ");
                                String herec = sc2.nextLine();
                                if (herec.equals("koniec")) {
                                    break;
                                } else
                                    mojeDatabaze.addActor(nazov, herec);
                            }
                            break;
                        }
                        else if (odpoved2 == 2) {
                            mojeDatabaze.deleteActors(nazov);
                            while (true) {
                                System.out.println("Zadajte meno herca alebo 'koniec' pre ukončenie: ");
                                String herec = sc2.nextLine();
                                if (herec.equals("koniec")) {
                                    break;
                                } else
                                    mojeDatabaze.addActor(nazov, herec);
                            }
                            break;
                        }
                    }
                    break;
                }
                else if (odpoved.equals("n")) {
                    break;
                } else
                    System.out.println("Neplatna odpoved, zadajte y/n");
            }
            mojeDatabaze.editHranyFilm(nazov, novyNazov, novyReziser, novyRok);

        }
        else {
            System.out.println("Chcete zmenit nazov filmu ? (y/n)");
            while(true) {
                String odpoved = sc2.nextLine();
                if (odpoved.equals("y")) {
                    System.out.println("Zadajte novy nazov filmu :");
                    novyNazov = sc2.nextLine();
                    if (!mojeDatabaze.checkFilmExistence(novyNazov)) {
                        System.out.println("Film s takymto nazvom uz existuje");
                        return;
                    }
                    break;
                } else if (odpoved.equals("n")) {
                    novyNazov = mojeDatabaze.getNazov(nazov);
                    break;
                } else
                    System.out.println("Neplatna odpoved, zadajte y/n");
            }
            System.out.println("Chcete zmenit rezisera filmu ? (y/n)");
            while(true) {
                String odpoved = sc2.nextLine();
                if (odpoved.equals("y")) {
                    System.out.println("Zadajte novy rezisera filmu :");
                    novyReziser = sc2.nextLine();
                    break;
                } else if (odpoved.equals("n")) {
                    novyReziser = mojeDatabaze.getReziser(nazov);
                    break;
                } else
                    System.out.println("Neplatna odpoved, zadajte y/n");
            }
            System.out.println("Chcete zmenit rok filmu ? (y/n)");
            while(true) {
                String odpoved = sc2.nextLine();
                if (odpoved.equals("y")) {
                    System.out.println("Zadajte novy rok filmu :");
                    novyRok = overenieRoku(sc1);
                    break;
                } else if (odpoved.equals("n")) {
                    novyRok = mojeDatabaze.getRok(nazov);
                    break;
                } else
                    System.out.println("Neplatna odpoved, zadajte y/n");
            }
            System.out.println("Chcete zmenit doporuceny vek filmu ? (y/n)");
            while(true) {
                String odpoved = sc2.nextLine();
                if (odpoved.equals("y")) {
                    System.out.println("Zadajte novy doporuceny vek filmu :");
                    novyVek = overenieVeku(sc1);
                    break;
                } else if (odpoved.equals("n")) {
                    novyVek = mojeDatabaze.getVek(nazov);
                    break;
                } else
                    System.out.println("Neplatna odpoved, zadajte y/n");
            }
            System.out.println("Chcete zmenit zoznam animatorov ? (y/n)");
            while(true) {
                String odpoved = sc2.nextLine();
                if (odpoved.equals("y")) {
                    while (true) {
                        System.out.println("1 .. pridat k aktualnemu zoznamu\n2 .. novy zoznam");
                        int odpoved2 = pouzeCelaCisla(sc1);
                        if (odpoved2 == 1) {
                            while (true) {
                                System.out.println("Zadajte meno herca alebo 'koniec' pre ukončenie: ");
                                String herec = sc2.nextLine();
                                if (herec.equals("koniec")) {
                                    break;
                                } else
                                    mojeDatabaze.addActor(nazov, herec);
                            }
                            break;
                        }
                        else if (odpoved2 == 2) {
                            mojeDatabaze.deleteActors(nazov);
                            while (true) {
                                System.out.println("Zadajte meno animatora alebo 'koniec' pre ukončenie: ");
                                String herec = sc2.nextLine();
                                if (herec.equals("koniec")) {
                                    break;
                                } else
                                    mojeDatabaze.addActor(nazov, herec);
                            }
                            break;
                        }
                    }
                    break;
                }
                else if (odpoved.equals("n")) {
                    break;
                } else
                    System.out.println("Neplatna odpoved, zadajte y/n");
            }
            mojeDatabaze.editAnimovanyFilm(nazov, novyNazov, novyReziser, novyRok, novyVek);
        }
    }
    static void FilmToFile() {
        System.out.println("Zadajte nazov filmu :");
        String nazov = sc2.nextLine();
        if (mojeDatabaze.checkFilmExistence(nazov)) {
            System.out.println("Film neexistuje");
            return;
        }
        mojeDatabaze.saveToFile(nazov);
    }
    static void FilmFromFile(){
        System.out.println("Zadajte nazov filmu :");
        String nazov = sc2.nextLine();
        mojeDatabaze.loadFromFile(nazov);
    }
}
