package src.domain.drivers;

import src.domain.classes.Subject;
import src.domain.utils.UtilsDomain;

import java.io.*;
import java.util.Scanner;
import java.util.Vector;

public class SubjectDriver {

    private static Subject s = new Subject();
    private static Scanner sc;
    private static PrintStream ps;
    private static boolean interactive = false;


    public static void testBasicConstructor() {
        String name = sc.next();
        int numberStudents = sc.nextInt();
        int level = sc.nextInt();
        int[] hoursClasses = new int[3];
        hoursClasses[0] = sc.nextInt();
        hoursClasses[1] = sc.nextInt();
        hoursClasses[2] = sc.nextInt();
        int[] numberOfGroups = new int[2];
        numberOfGroups[0] = sc.nextInt();
        numberOfGroups[1] = sc.nextInt();
        UtilsDomain.typeShift tyShift = UtilsDomain.typeShift.valueOf(sc.next());
        s = new Subject(name, numberStudents, level, hoursClasses, numberOfGroups, tyShift);
    }

    public static void testConstructorFromString() {
        Vector<String> v = new Vector<>(9);
        v.add(0, sc.next());
        v.add(1, sc.next());
        v.add(2, sc.next());
        v.add(3, sc.next());
        v.add(4, sc.next());
        v.add(5, sc.next());
        v.add(6, sc.next());
        v.add(7, sc.next());
        v.add(8, sc.next());
        s = new Subject(v);
    }

    public static void testSetName() {
        String name = sc.next();
        s.setName(name);
    }

    public static void testGetName() {
        System.out.println(s.getName());
    }

    public static void testSetNumberStudents() {
        int numberStudents = sc.nextInt();
        s.setNumberStudents(numberStudents);
    }

    public static void testGetNumberStudents() {
        System.out.println(s.getNumberStudents());
    }

    public static void testSetLevel() {
        int level = sc.nextInt();
        s.setLevel(level);
    }

    public static void testGetLevel() {
        System.out.println(s.getLevel());
    }

    public static void testSetHoursClasses() {
        int theoryHours = sc.nextInt();
        int laboratoryHours = sc.nextInt();
        int problemsHours = sc.nextInt();
        s.setHoursClasses(theoryHours, laboratoryHours, problemsHours);
    }

    public static void testGetHoursClasses() {
        int[] hoursClasses = s.getHoursClasses();
        System.out.println(hoursClasses[0] + " " + hoursClasses[1] + " " + hoursClasses[2]);
    }

    public static void testGetTheoryHours() {
        System.out.println(s.getTheoryHours());
    }

    public static void testGetLaboratoryHours() {
        System.out.println(s.getLaboratoryHours());
    }

    public static void testGetProblemsHours() {
        System.out.println(s.getProblemsHours());
    }

    public static void testSetTheoryHours() {
        int theoryHours = sc.nextInt();
        s.setTheoryHours(theoryHours);
    }

    public static void testSetLaboratoryHours() {
        int laboratoryHours = sc.nextInt();
        s.setLaboratoryHours(laboratoryHours);
    }

    public static void testSetProblemsHours() {
        int problemsHours = sc.nextInt();
        s.setProblemsHours(problemsHours);
    }

    public static void testSetNumberOfGroups() {
        int groups = sc.nextInt();
        int subgroups = sc.nextInt();
        s.setNumberOfGroups(groups, subgroups);
    }

    public static void testGetNumberOfGroups() {
        int[] numberOfGroups = s.getNumberOfGroups();
        System.out.println(numberOfGroups[0] + " " + numberOfGroups[1]);
    }

    public static void testSetTypeShift() {
        int tyShift = sc.nextInt();
        s.setTypeShift(UtilsDomain.typeShift.values()[tyShift]);
    }

    public static void testGetTypeShift() {
        System.out.println(s.getTypeShift().ordinal());
    }

    public static void testToStr() {
        Vector<String> v = s.toStr();
        System.out.println(v.get(0) +
                " " + v.get(1) +
                " " + v.get(2) +
                " " + v.get(3) +
                " " + v.get(4) +
                " " + v.get(5) +
                " " + v.get(6) +
                " " + v.get(7) +
                " " + v.get(8) + "\n");
    }

    public static void main(String args[]) {
        if (args.length > 0) {
            interactive = true;

            try {
                sc = new Scanner(new FileReader("./data/" + args[0]));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            ps = new PrintStream(new BufferedOutputStream(new FileOutputStream(new File([args[0] + ".out"]),true)),true);
            set

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

        } while (!eof && sc.hasNextInt());  // SI EOF ACABAR PROGRAMA

        OutputStream os = new BufferedOutputStream();


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
