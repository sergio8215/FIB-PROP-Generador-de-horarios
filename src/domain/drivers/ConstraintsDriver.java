package src.domain.drivers;

import java.io.*;
import java.util.Scanner;

public class ConstraintsDriver {

    private static Scanner sc;
    private static PrintStream ps;
    private static boolean interactive = false;

    public static void main(String args[]) throws FileNotFoundException {
        final PrintStream oldStdout = System.out;

        if (args.length > 0) {
            interactive = true;

            try {
                sc = new Scanner(new FileReader("./data/drivers/in/" + args[0]));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            ps = new PrintStream(new BufferedOutputStream(new FileOutputStream(new File("./data/drivers/in/" + args[1]),true)),true);
            System.setOut(ps);

        } else {
            sc = new Scanner(System.in);
        }


        if (!interactive)  menu();

        boolean eof = false;

        do {
            switch (sc.nextInt()) {
                case 0:
                    testBasicConstructor();
                    break;
                case 1:
                    testConstructorFromString();
                    break;
                case 2:
                    testSetName();
                    break;
                case 3:
                    testGetName();
                    break;
                case 4:
                    testSetNumberStudents();
                    break;
                case 5:
                    testGetNumberStudents();
                    break;
                case 6:
                    testSetLevel();
                    break;
                case 7:
                    testGetLevel();
                    break;
                case 8:
                    testSetHoursClasses();
                    break;
                case 9:
                    testGetHoursClasses();
                    break;
                case 10:
                    testSetTheoryHours();
                    break;
                case 11:
                    testGetTheoryHours();
                    break;
                case 12:
                    testSetLaboratoryHours();
                    break;
                case 13:
                    testGetLaboratoryHours();
                    break;
                case 14:
                    testSetProblemsHours();
                    break;
                case 15:
                    testGetProblemsHours();
                    break;
                case 16:
                    testSetNumberOfGroups();
                    break;
                case 17:
                    testGetNumberOfGroups();
                    break;
                case 18:
                    testSetTypeShift();
                    break;
                case 19:
                    testGetTypeShift();
                    break;
                case 20:
                    testToStr();
                    break;
                case 99:
                    eof = true;
                    break;
                default:
                    System.out.println("Input error!\n");
            }

            if (!interactive) {
                clearConsole();
                menu();
            }

        } while (!eof && sc.hasNextInt());

        if (interactive) {
            System.setOut(oldStdout);
            ps.close();
        }
    }

    private static void menu() {
        System.out.println("------------------------------------------");
        System.out.println("\nWrite the number of the function you want to test:");
        System.out.println("\t0 -> Basic constructor");
        System.out.println("\t1 -> Constructor from String");
        System.out.println("\t2 -> Name setter");
        System.out.println("\t3 -> Name getter");
        System.out.println("\t4 -> Number of students setter");
        System.out.println("\t5 -> Number of students getter");
        System.out.println("\t6 -> Level setter");
        System.out.println("\t7 -> Level getter");
        System.out.println("\t8 -> Hours classes setter");
        System.out.println("\t9 -> Hours classes getter");
        System.out.println("\t10 -> Theory hours setter");
        System.out.println("\t11 -> Theory hours getter");
        System.out.println("\t12 -> Laboratory hours setter");
        System.out.println("\t13 -> Laboratory hours getter");
        System.out.println("\t14 -> Problems hours setter");
        System.out.println("\t15 -> Problems hours getter");
        System.out.println("\t16 -> Number of groups setter");
        System.out.println("\t17 -> Number of groups getter");
        System.out.println("\t18 -> Type shift setter");
        System.out.println("\t19 -> Type shift getter");
        System.out.println("\t20 -> To string function");
    }

    private static void clearConsole() {
        final String os = System.getProperty("os.name");

        try {
            if (os.contains("Windows"))     Runtime.getRuntime().exec("cls");
            else    Runtime.getRuntime().exec("clear");
        } catch (Exception e) {
            System.out.println(e);
        }

    }

}
