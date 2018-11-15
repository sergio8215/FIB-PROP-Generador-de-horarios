package src.domain.drivers;

import src.domain.classes.ClassClass;
import src.domain.classes.Classroom;
import src.domain.classes.MUS;
import src.domain.classes.Session;
import src.domain.utils.UtilsDomain;

import java.util.Scanner;
import java.util.Vector;

public class MUSDriver {

    private static MUS m = new MUS();
    private static Scanner sc = new Scanner(System.in);

    private static ClassClass classClassConstructor() {
        System.out.print("Data classClass: ");
        // TODO
    }

    private static Classroom classroomConstructor() {
        System.out.print("Data classroom: ");

        Vector<String> v = new Vector<>(4);

        v.add(0, sc.next());
        v.add(1, sc.next());
        v.add(2, sc.next());
        v.add(3, sc.next());

        return Classroom.fromStr(v);
    }

    private static Session sessionConstructor() {
        System.out.print("Data session: ");

        Vector<String> v = new Vector<>(2);

        v.add(0, sc.next());
        v.add(1, sc.next());

        return new Session(v);
    }

    public static void testBasicConstructor() {
        m = new MUS(classClassConstructor(), classroomConstructor(), sessionConstructor());
    }

    public static void testConstructorWithMembers1() {
        ClassClass cc = classClassConstructor();
        UtilsDomain.Pair<Classroom, Session> p = new UtilsDomain.Pair<>(classroomConstructor(), sessionConstructor());

        m = new MUS(cc, p);
    }

    public static void testConstructorFromString() {
        // TODO
    }

    public static void testSetClassClass() {
        // TODO
    }

    public static void testSetClassroom() {
        m.setClassroom(classroomConstructor());
    }

    public static void testSetSession() {
        m.setSession(sessionConstructor());
    }

    public static void testSetDomain() {
        // TODO
    }

    public static void testGetClassClass() {
        Vector<String> v = m.getClassClass().toStr();
        for (String s : v) {
            System.out.print(s + " ");
        }
        System.out.print("\n");
    }

    public static void testGetClassroom() {
        Vector<String> v = m.getClassroom().toStr();
        for (String s : v) {
            System.out.print(s + " ");
        }
        System.out.print("\n");
    }

    public static void testGetSession() {
        Vector<String> v = m.getSession().toStr();
        for (String s : v) {
            System.out.print(s + " ");
        }
        System.out.print("\n");
    }

    public static void testGetClassroomSessionPair() {
        UtilsDomain.Pair<Classroom, Session> p = m.getClassroomSessionPair();

        Vector<String> vC = p.first.toStr();
        Vector<String> vS = p.second.toStr();

        for (String s : vC) {
            System.out.print(s + " ");
        }

        System.out.print("\n");

        for (String s : vS) {
            System.out.print(s + " ");
        }

        System.out.print("\n");
    }

    public static void testGetSubject() {
        Vector<String> v = m.getSubject().toStr();
        for (String s : v) {
            System.out.print(s + " ");
        }
        System.out.print("\n");
    }

    public static void testGetDomain() {
        // TODO
    }

    public static void testDomainSize() {
        System.out.println(m.domainSize());
    }

    public static void testAssign() {
        // TODO
    }

    public static void testGetValueDomain() {
        // TODO
    }

    public static void testDeleteFromDomain() {
        // TODO
    }

    public static void testToStr() {
        Vector< Vector<String> > vv = m.toStr();
        for (Vector<String> v : vv) {
            for (String s : v) {
                System.out.print(s + " ");
            }
            System.out.print("\n");
        }
    }

    public static void main(String args[]) {
        menu();

        do {
            switch (sc.nextInt()) {
                case 0:
                    testBasicConstructor();
                    break;
                case 1:
                    testConstructorWithMembers1();
                    break;
                case 2:
                    testConstructorFromString();
                    break;
                case 3:
                    testSetClassClass();
                    break;
                case 4:
                    testSetClassroom();
                    break;
                case 5:
                    testSetSession();
                    break;
                case 6:
                    testSetDomain();
                    break;
                case 7:
                    testGetClassClass();
                    break;
                case 8:
                    testGetClassroom();
                    break;
                case 9:
                    testGetSession();
                    break;
                case 10:
                    testGetClassroomSessionPair();
                    break;
                case 11:
                    testGetSubject();
                    break;
                case 12:
                    testGetDomain();
                    break;
                case 13:
                    testDomainSize();
                    break;
                case 14:
                    testAssign();
                    break;
                case 15:
                    testGetValueDomain();
                    break;
                case 16:
                    testDeleteFromDomain();
                    break;
                case 17:
                    testToStr();
                    break;
                default:
                    System.out.println("Input error!");
            }

            clearConsole();
            menu();

        } while (sc.hasNextInt());
    }

    private static void menu() {
        System.out.println("------------------------------------------");
        System.out.println("\nWrite the number of the function you want to test:");
        System.out.println("\t0 -> Basic Constructor");
        System.out.println("\t1 -> Constructor with Members 1");
        System.out.println("\t2 -> Constructor from String");
        System.out.println("\t3 -> Classclass setter");
        System.out.println("\t4 -> Classroom setter");
        System.out.println("\t5 -> Session setter");
        System.out.println("\t6 -> Domain setter");
        System.out.println("\t7 -> Classclass getter");
        System.out.println("\t8 -> Classroom getter");
        System.out.println("\t9 -> Session getter");
        System.out.println("\t10 -> Classroom-Session Pair Getter");
        System.out.println("\t11 -> Subject getter");
        System.out.println("\t12 -> Domain getter");
        System.out.println("\t13 -> Domain size");
        System.out.println("\t14 -> Assign");
        System.out.println("\t15 -> Value from domain getter");
        System.out.println("\t16 -> Delete value from domain");
        System.out.println("\t17 -> To String");
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
