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
            System.out.println("zadajte prosim cele cislo ");
            sc.nextLine();
            cislo = pouzeCelaCisla(sc);
        }
        return cislo;
    }



    public static void main(String[] args) {


        String nazov;

        int volba;
        boolean run = true;

        while(run) {
            System.out.println("Vyberte pozadovanu cinnost:");
            System.out.println("1 .. pridat novy film");
            System.out.println("2 .. pridat hodnotenie");
            System.out.println("3 .. informacie o filme");
            System.out.println("4 .. odstranenie filmu");
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
                    System.out.println("Zadajte nazov filmu :");
                    nazov = sc2.nextLine();
                    mojeDatabaze.FilmInfo(nazov);
                    break;

                case 4 :
                    deleteFilm();
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
                mojeDatabaze.setAnimovanyFilm(nazov, reziser, rok, doporucenyVek);
                break;
            }
        }
    }

    static void addRating() {
        System.out.println("Zadajte nazov filmu :");
        String nazov = sc2.nextLine();
        mojeDatabaze.typFilmu(nazov);
        if (!mojeDatabaze.checkFilmExistence(nazov)) {
            System.out.println("Film neexistuje");
            return;
        }
        System.out.println("Zadajte hodnotenie filmu :");
        int hodnotenie = pouzeCelaCisla(sc1);
        if (mojeDatabaze.setHodnotenieFilmu(nazov, hodnotenie) == true)
            System.out.println("Hodnotenie filmu bolo nastavene");
        else
            System.out.println("Hodnotenie filmu sa nepodarilo nastavit");
        System.out.println("Chcete pridat komentar k filmu ? (y/n)");
        String odpoved = sc2.nextLine();
        if (odpoved.equals("y")) {
            System.out.println("Zadajte komentar :");
            String komentar = sc2.nextLine();
            mojeDatabaze.setKomentarFilmu(nazov, komentar);
        }
        else if (odpoved.equals("n"))
            System.out.println("Komentar nebol pridany");
        else
            System.out.println("Neplatna odpoved");
    }

    static void deleteFilm() {
        System.out.println("Zadajte nazov filmu :");
        String nazov = sc2.nextLine();
        mojeDatabaze.vymazFilm(nazov);
    }
}
