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




    public static void main(String[] args) {

        int volba;
        boolean run = true;

        while(run) {

            System.out.println("Vyberte pozadovanu cinnost:");
            System.out.println("1 .. pridat novy film");
            System.out.println("2 .. pridat hodnotenie");
            System.out.println("3 .. informacie o filme");
            System.out.println("4 .. odstranenie filmu");
            System.out.println("5 .. herci vo viacerych filmoch ");
            System.out.println("6 .. vypis filmov daneho herca");
            System.out.println("7 .. upravit film");
            System.out.println("8 .. vypis vsetkych filmov");
            System.out.println("9 .. koniec programu");

            volba = pouzeCelaCisla(sc1);

            switch (volba) {
                case 1 :
                    addFilm();
                    break;

                case 2 :
                    addRating();
                    break;

                case 3 :
                    FilmInfo();
                    break;

                case 4 :
                    deleteFilm();
                    break;

                case 5 :
                    actorsInMultipleFilms();
                    break;

                case 6 :
                    FilmsOfActor();
                    break;

                case 7 :
                    editFilm();
                    break;

                case 8 :
                    mojeDatabaze.vypisDatabaze();
                    break;

                case 9 :
                    run = false;
                    break;
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
                if (mojeDatabaze.checkFilmExistence(nazov) == false) {
                    System.out.println("Film uz je v databaze, mozete ho upravit");
                    return;
                }
                System.out.println("Zadajte rezisera filmu :");
                String reziser = sc2.nextLine();
                System.out.println("Zadajte rok filmu :");
                int rok = pouzeCelaCisla(sc1);
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
            else if (volba == 2)
                {System.out.println("Zadajte nazov filmu :");
                String nazov = sc2.nextLine();
                if (mojeDatabaze.checkFilmExistence(nazov) == false) {
                    System.out.println("Film uz je v databaze, mozete ho upravit");
                    return;
                }
                System.out.println("Zadajte rezisera filmu :");
                String reziser = sc2.nextLine();
                System.out.println("Zadajte rok filmu :");
                int rok = pouzeCelaCisla(sc1);
                System.out.println("Zadajte doporuceny vek divaka :");
                int doporucenyVek = pouzeCelaCisla(sc1);
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
        mojeDatabaze.typFilmu(nazov);
        System.out.println("Zadajte hodnotenie filmu :");
        while(true) {
            hodnotenie = pouzeCelaCisla(sc1);
            if (mojeDatabaze.setCiselneHodnotenie(nazov, hodnotenie) == true) {
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
        if (mojeDatabaze.typFilmu(nazov) == true){
            System.out.println("Chcete zmenit nazov filmu ? (y/n)");
            while(true) {
                String odpoved = sc2.nextLine();
                if (odpoved.equals("y")) {
                    System.out.println("Zadajte novy nazov filmu :");
                    novyNazov = sc2.nextLine();
                    if (mojeDatabaze.checkFilmExistence(novyNazov) == false) {
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
                    novyRok = pouzeCelaCisla(sc1);
                    break;
                } else if (odpoved.equals("n")) {
                    novyRok = mojeDatabaze.getRok(nazov);
                    break;
                } else
                    System.out.println("Neplatna odpoved, zadajte y/n");
            }
            mojeDatabaze.editHranyFilm(nazov, novyNazov, novyReziser, novyRok);
        }
        else {
        }

    }


}
