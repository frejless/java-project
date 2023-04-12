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
            System.out.println("5 .. herci v filmoch");
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
                    getFilmInfo();
                    break;

                case 4 :
                    deleteFilm();
                    break;

                case 5 :
                    System.out.println("Zadajte nazov filmu :");
                    String nazov = sc2.nextLine();
                    mojeDatabaze.getActors(nazov);
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
                System.out.println("Zadajte rezisera filmu :");
                String reziser = sc2.nextLine();
                System.out.println("Zadajte rok filmu :");
                int rok = pouzeCelaCisla(sc1);
                System.out.println("Zadajte doporuceny vek divaka :");
                int doporucenyVek = pouzeCelaCisla(sc1);
                while (true) {
                    System.out.println("Zadajte meno herca alebo 'koniec' pre ukončenie: ");
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
        int hodnotenie;
        mojeDatabaze.typFilmu(nazov);
        if (!mojeDatabaze.checkFilmExistence(nazov)) {
            System.out.println("Film neexistuje");
            return;
        }
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
        mojeDatabaze.vymazFilm(nazov);
    }

    static void getFilmInfo() {
        System.out.println("Zadajte nazov filmu :");
        String nazov = sc2.nextLine();
        mojeDatabaze.FilmInfo(nazov);
    }



}
