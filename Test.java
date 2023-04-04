import java.util.Scanner;

public class Test {

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
        Scanner sc1 = new Scanner(System.in);
        Scanner sc2 = new Scanner(System.in);
        Databaze mojeDatabaze=new Databaze();

        String nazov;
        String reziser;
        int rok;
        int doporucenyVek;
        int hodnotenie;

        int volba;
        boolean run = true;

        while(run) {
            System.out.println("Vyberte pozadovanu cinnost:");
            System.out.println("1 .. pridanie noveho filmu");
            System.out.println("2 .. nastavenie hodnotenia filmu");
            System.out.println("3 .. vypis informacie o filme");
            System.out.println("8 .. vypis filmov");
            System.out.println("9 .. koniec programu");

            volba = pouzeCelaCisla(sc1);

            switch (volba) {
                case 1 :
                    while (true) {
                        System.out.println("Vyberte druh filmu :\n1 .. Hrany film\n2 .. Animovany film");
                        volba = pouzeCelaCisla(sc1);

                        if (volba == 1){
                            System.out.println("Zadajte nazov filmu :");
                            nazov = sc2.nextLine();
                            System.out.println("Zadajte rezisera filmu :");
                            reziser = sc2.nextLine();
                            System.out.println("Zadajte rok filmu :");
                            rok = pouzeCelaCisla(sc1);
                            if (mojeDatabaze.setHranyFilm(nazov, reziser, rok) == true)
                                System.out.println("Film bol pridany");
                            else
                                System.out.println("Film sa nepodarilo pridat");

                            break;
                        }
                        else if (volba == 2){
                            System.out.println("Zadajte nazov filmu :");
                            nazov = sc2.nextLine();
                            System.out.println("Zadajte rezisera filmu :");
                            reziser = sc2.nextLine();
                            System.out.println("Zadajte rok filmu :");
                            rok = pouzeCelaCisla(sc1);
                            System.out.println("Zadajte doporuceny vek divaka :");
                            doporucenyVek = pouzeCelaCisla(sc1);
                            if (mojeDatabaze.setAnimovanyFilm(nazov, reziser, rok, doporucenyVek) == true)
                                System.out.println("Film bol pridany");
                            else
                                System.out.println("Film sa nepodarilo pridat");

                            break;
                        }
                    }
                    break;

                case 2 :
                    System.out.println("(hrane filmy mozu mat hodnotenie 0-5, animovane filmy 0-10)\n");
                    System.out.println("Zadajte nazov filmu :");
                    nazov = sc2.nextLine();
                    System.out.println("Zadajte hodnotenie filmu :");
                    hodnotenie = pouzeCelaCisla(sc1);
                    if (mojeDatabaze.setHodnotenieFilmu(nazov, hodnotenie) == true)
                        System.out.println("Hodnotenie filmu bolo nastavene");
                    else
                        System.out.println("Hodnotenie filmu sa nepodarilo nastavit");
                    break;

                case 3 :
                    System.out.println("Zadajte nazov filmu :");
                    nazov = sc2.nextLine();
                    mojeDatabaze.FilmInfo(nazov);
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

}
