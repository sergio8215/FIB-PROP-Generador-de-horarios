package src.domain.drivers;

import src.domain.classes.Subject;
import src.domain.classes.SubjectsSet;
import src.domain.utils.UtilsDomain;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Vector;

public class SubjectsSetDriver {

    private static SubjectsSet ss = new SubjectsSet();
    private static Scanner sc;
    private static PrintStream ps;
    private static boolean interactive = false;

    public static void testConstructorFromArray() {
        System.out.println("Indicates the number of subjects you want to generate:");
        int n = sc.nextInt();

        ArrayList<Subject> sa = new ArrayList<>();

        for (int i = 0; i < n; ++i) {
            System.out.print("Data subject " + i + ": ");
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

            sa.add(i, new Subject(name, numberStudents, level, hoursClasses, numberOfGroups, tyShift));
        }

        ss = new SubjectsSet(sa);
    }

    public static void testConstructorFromString() {
        System.out.println("Indicates the number of subjects you want to generate:");
        int n = sc.nextInt();

        Vector< Vector<String> > vv = new Vector<>(n);

        for (int i = 0; i < n; i++) {
            System.out.print("Data subject " + i + ": ");

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

            vv.add(i, v);
        }

        ss = new SubjectsSet(vv);
    }

    public static void testSetSet() {
        System.out.println("Indicates the number of subjects you want to generate:");
        int n = sc.nextInt();

        ArrayList<Subject> sa = new ArrayList<>();

        for (int i = 0; i < n; ++i) {
            System.out.print("Data subject " + i + ": ");
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

            sa.add(i, new Subject(name, numberStudents, level, hoursClasses, numberOfGroups, tyShift));
        }

        ss.setSet(sa);
    }

    public static void testUnset() {
        ArrayList<Subject> subjectsArray = ss.unset();

        for (Subject s : subjectsArray) {
            Vector<String> v = s.toStr();
            for (String str : v)    System.out.print(str + " ");
            System.out.print("\n");
        }
    }

    public static void testToStr() {
        Vector< Vector<String> > set = ss.toStr();

        for (Vector<String> v : set) {
            for (String s : v) {
                System.out.print(s + " ");
            }
            System.out.print("\n");
        }
    }

    public static void testGetSubject() {
        System.out.println("Name of the subject: ");
        String name = sc.next();

        UtilsDomain.ResoultOfQuery<Subject> res = ss.getSubject(name);

        System.out.println(res.queryTest);
    }

    public static void testPutSuject() {
        System.out.print("Data of the subject: ");
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

        Subject s = new Subject(v);

        ss.putSubject(s);
    }

    public static void testPopSubject() {
        System.out.print("Name of the subject: ");
        String name = sc.next();

        System.out.println(ss.popSubject(name));
    }

    public static void testLength() {
        System.out.println(ss.length());
    }

    public static void testBelongsFromSubject() {
        System.out.print("Data of the subject: ");
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

        Subject s = new Subject(v);

        System.out.println(ss.belongs(s));
    }

    public static void testBelongsFromName() {
        System.out.print("Name of the subject: ");
        String name = sc.next();

        System.out.println(ss.belongs(name));
    }

    public static void testCompare() {
        System.out.print("Data of the subject 1: ");
        Vector<String> v1 = new Vector<>(9);
        v1.add(0, sc.next());
        v1.add(1, sc.next());
        v1.add(2, sc.next());
        v1.add(3, sc.next());
        v1.add(4, sc.next());
        v1.add(5, sc.next());
        v1.add(6, sc.next());
        v1.add(7, sc.next());
        v1.add(8, sc.next());

        Subject s1 = new Subject(v1);

        System.out.print("Data of the subject 2: ");
        Vector<String> v2 = new Vector<>(9);
        v2.add(0, sc.next());
        v2.add(1, sc.next());
        v2.add(2, sc.next());
        v2.add(3, sc.next());
        v2.add(4, sc.next());
        v2.add(5, sc.next());
        v2.add(6, sc.next());
        v2.add(7, sc.next());
        v2.add(8, sc.next());

        Subject s2 = new Subject(v2);

        System.out.print("Comparator: ");
        String op = sc.next();

        System.out.println(SubjectsSet.compare(s1, op, s2));
    }

    public static void testSubjectsSort() {
        System.out.println("Indicates the number of subjects you want to generate:");
        int n = sc.nextInt();

        ArrayList<Subject> subjectsArray = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            System.out.println("Data subject " + i + ":");
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
            subjectsArray.add(new Subject(v));
        }

        subjectsArray = SubjectsSet.subjectsSort(subjectsArray);

        for (Subject s : subjectsArray) {
            Vector<String> v = s.toStr();
            for (String str : v)    System.out.print(str + " ");
            System.out.print("\n");
        }
    }

    public static void main(String args[]) throws FileNotFoundException {
        final PrintStream oldStdout = System.out;

        if (args.length > 0) {
            interactive = true;

            try {
                sc = new Scanner(new FileReader("./data/testing" + args[0]));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            ps = new PrintStream(new BufferedOutputStream(new FileOutputStream(new File(args[1]),true)),true);
            System.setOut(ps);

        } else {
            sc = new Scanner(System.in);
        }


        if (!interactive)  menu();

        boolean eof = false;

        do {
            switch (sc.nextInt()) {
                case 0:
                    testConstructorFromArray();
                    break;
                case 1:
                    testConstructorFromString();
                    break;
                case 2:
                    testSetSet();
                    break;
                case 3:
                    testUnset();
                    break;
                case 4:
                    testToStr();
                    break;
                case 5:
                    testGetSubject();
                    break;
                case 6:
                    testPutSuject();
                    break;
                case 7:
                    testPopSubject();
                    break;
                case 8:
                    testLength();
                    break;
                case 9:
                    testBelongsFromSubject();
                    break;
                case 10:
                    testBelongsFromName();
                    break;
                case 11:
                    testCompare();
                    break;
                case 12:
                    testSubjectsSort();
                    break;
                case 99:
                    eof = true;
                    break;
                default:
                    System.out.println("Input error!");
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
        System.out.println("\t0 -> Constructor from Array");
        System.out.println("\t1 -> Constructor from Strings");
        System.out.println("\t2 -> Set set");
        System.out.println("\t3 -> Unset");
        System.out.println("\t4 -> To String");
        System.out.println("\t5 -> Subject getter");
        System.out.println("\t6 -> Put Subject");
        System.out.println("\t7 -> Pop subject");
        System.out.println("\t8 -> Length");
        System.out.println("\t9 -> Belongs for a Subject Object");
        System.out.println("\t10 -> Belongs for a Name of Subject ");
        System.out.println("\t11 -> Compare");
        System.out.println("\t12 -> Subjects Sort");
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
