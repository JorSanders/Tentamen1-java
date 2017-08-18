package tentamen1jorsanders;

/**
 * tentamen 1 programma dat die resultaten van een vak bekijkt
 *
 * @author jor sanders 500688218
 */
import java.util.*;

public class Tentamen1jorSanders {

    static Scanner stdin = new Scanner(System.in);

    static String vakNaam; // naam van het vak
    static int aantalStudenten; // aantal studenten dat dit vak volgt
    static double totaal = 0; // totaal opgetelde cijfer van alle studenten bij elkaar
    static double gemiddelde = 0; // gemiddelde cijfer van de studenten
    static int aantalGehaald = 0; // aantal student dat het vak gehaald heeft
    static boolean verkeerdeInput; // boolean om te kijken of de input correct is

    static String[] namen; // array met alle namen uit de klas
    static double[] cijfers; // array met alle cijfers uit de klas
    static char[] practicumresultaat; // array met ale practicumresultaten uit de kas
    static String[] gehaald; // array die bijhoud welke studenten zijn geslaagd

    public static void main(String[] args) {

        System.out.println("Dit programma is gemaakt door Jor Sanders, IG102, 500688218 \n");

        System.out.print("hoe heet dit vak? ");
        vakNaam = stdin.next();

        // leest het aantal studenten dat dit vak volgt en zorgt dat het groter is dan 0
        verkeerdeInput = true;
        do {
            try {
                System.out.print("hoeveel studenten doen aan dit vak? ");
                aantalStudenten = stdin.nextInt();

                //geeft een error als het aantal te laag is
                if (aantalStudenten < 1) {
                    System.out.println('\t' + "aantal moet boven de 0 liggen!");

                } else {
                    verkeerdeInput = false; // stopt de loop
                }

                // zorgt ervoor dat het programma niet crashed als de gebruiker text invoert
            } catch (Exception e) {
                System.out.println('\t' + "Dat is geen getal!");
                stdin.next();
            }
        } while (verkeerdeInput);

        System.out.println(""); // witregel voor na de eerste inputs

        // geeft alle arrays de lengte van het aantal studenten
        namen = new String[aantalStudenten];
        cijfers = new double[aantalStudenten];
        practicumresultaat = new char[aantalStudenten];
        gehaald = new String[aantalStudenten];

        for (int i = 0; i < aantalStudenten; i++) {
            leesGegevens(i); // roept de methode aan die de gegevens van de studenten leest
            kijkOfGehaald(i); // roept de methode aan die kijk of de studenten het vak gehaald hebben
        }
        gemiddelde = totaal / aantalStudenten; // berekent het gemiddle cijfer

        for (int i = 0; i < aantalStudenten; i++) {
            resultaten(i); // roept de methode aan die de resultaten van de studenten print
        }

        System.out.println('\n' + "het gemiddelde cijfer = " + gemiddelde);

        // kijkt of meer dan de helft van de studenten het vak hebben gehaald
        if (aantalGehaald >= aantalStudenten / 2) {
            System.out.println("Dit vak is goed gemaakt!");
        } else {
            System.out.println("Dit vak is niet goed gemaakt");
        }

    }

    // methode die de gegevens van een leerling leest

    public static void leesGegevens(int counter) {
        System.out.print("naam: ");
        namen[counter] = stdin.next();

        // leest het cijfer van een student en zorgt dat die tussen de 1 en 10 ligt
        verkeerdeInput = true;
        do {
            try {
                System.out.print("Cijfer: ");
                cijfers[counter] = stdin.nextDouble();

                if (cijfers[counter] < 1 || cijfers[counter] > 10) {
                    // geeft een error als het cijfer niet tussen de 1 en 10 ligt
                    System.out.println('\t' + "Cijfers liggen tussen de 1 en 10!");
                } else {
                    // rond het cijfer af op 2 decimalen
                    cijfers[counter] = (double) ((int) (cijfers[counter] * 100)) / 100;
                    // voegt het cijfer toe aan het totaal van behaalde cijfers gebruikt voor het gemiddelde
                    totaal += cijfers[counter];
                    verkeerdeInput = false; // stopt de loop
                }

                // geeft een error als de gebruiker iets anders dan een getal invoert
            } catch (Exception e) {
                System.out.println('\t' + "Dat is geen getal!");
                stdin.next();
            }
        } while (verkeerdeInput);

        //leest het practicum resultaat en zorgt dat het een v of een o word
        do {
            System.out.print("practicumresultaat (v/o): ");
            practicumresultaat[counter] = stdin.next().charAt(0);

        } while (practicumresultaat[counter] != 'o' && practicumresultaat[counter] != 'v');

        System.out.println(""); // witregel na de input van een student te hebben gelezen
    }

    // methode die kijkt of een student het vak heeft gehaald
    public static void kijkOfGehaald(int counter) {
        if (cijfers[counter] >= 5.5 && practicumresultaat[counter] == 'v') {
            gehaald[counter] = "geslaagd";
            aantalGehaald++;
        } else {
            gehaald[counter] = "gezakt";
        }
    }

    // methode die de behaalde resultaten print
    public static void resultaten(int counter) {
        System.out.println(namen[counter] + "heeft voor " + vakNaam + " een "
                + cijfers[counter] + " en " + practicumresultaat[counter]
                + " --> " + gehaald[counter]);
    }

}
