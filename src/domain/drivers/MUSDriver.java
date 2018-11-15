package src.domain.drivers;

import src.domain.classes.*;
import src.domain.utils.UtilsDomain;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Vector;

public class MUSDriver {

    private static MUS m = new MUS();
    private static Scanner sc;
    private static PrintStream ps;
    private static boolean interactive = false;


    private static Subject subjectConstructor() {
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
        return new Subject(name, numberStudents, level, hoursClasses, numberOfGroups, tyShift);
    }

    private static ClassClass classClassConstructor() {
        if (!interactive)   System.out.print("Data classClass: ");

        // First data of the classClass, Subjects data before.

        String identifier = sc.next();
        int group = sc.nextInt();
        int subGroup = sc.nextInt();
        UtilsDomain.ClassType typeG = UtilsDomain.ClassType.valueOf(sc.next());
        UtilsDomain.typeShift shift = UtilsDomain.typeShift.valueOf(sc.next());
        int quantityStudents = sc.nextInt();

        return new ProblemsClass(identifier, subjectConstructor(), group, quantityStudents, shift, subGroup);
    }

    private static Classroom classroomConstructor() {
        if (!interactive)   System.out.print("Data classroom: ");

        Vector<String> v = new Vector<>(5);

        v.add(0, sc.next());
        v.add(1, sc.next());
        v.add(2, sc.next());
        v.add(3, sc.next());
        if (v.get(2).equals("LABORATORY"))   v.add(4, sc.next());

        return Classroom.fromStr(v);
    }

    private static Session sessionConstructor() {
        if (!interactive)   System.out.print("Data session: ");

        Vector<String> v = new Vector<>(2);

        v.add(0, sc.next());
        v.add(1, sc.next());

        return new Session(v);
    }

    public static void testBasicConstructor() {
        m = new MUS(classClassConstructor(), classroomConstructor(), sessionConstructor());
    }

    public static void testConstructorWithMembers() {
        ClassClass cc = classClassConstructor();
        UtilsDomain.Pair<Classroom, Session> p = new UtilsDomain.Pair<>(classroomConstructor(), sessionConstructor());

        m = new MUS(cc, p);
    }

    public static void testConstructorFromString() {
        // Class class
        Vector< Vector<String> > mus = new Vector<>(3);

        Vector<String> classclass = new Vector<>(15);
        classclass.add(0, sc.next());
        classclass.add(1, sc.next());
        classclass.add(2, sc.next());
        classclass.add(3, sc.next());
        classclass.add(4, sc.next());
        classclass.add(5, sc.next());
        classclass.add(6, sc.next());
        classclass.add(7, sc.next());
        classclass.add(8, sc.next());
        classclass.add(9, sc.next());
        classclass.add(10, sc.next());
        classclass.add(11, sc.next());
        classclass.add(12, sc.next());
        classclass.add(13, sc.next());
        classclass.add(14, sc.next());

        mus.add(0, classclass);


        Vector<String> classroom = new Vector<>(5);

        classroom.add(0, sc.next());
        classroom.add(1, sc.next());
        classroom.add(2, sc.next());
        classroom.add(3, sc.next());
        if (classroom.get(2).equals("LABORATORY"))     classroom.add(4, sc.next());

        mus.add(1, classroom);

        Vector<String> session = new Vector<>(2);

        session.add(0, sc.next());
        session.add(1, sc.next());

        mus.add(2, session);

        m = new MUS(mus);
    }

    public static void testSetClassClass() {
        m.setClassClass(classClassConstructor());
    }

    public static void testSetClassroom() {
        m.setClassroom(classroomConstructor());
    }

    public static void testSetSession() {
        m.setSession(sessionConstructor());
    }

    public static void testSetDomain() {
        ClassroomSession cs = new ClassroomSession();

        if (!interactive)   System.out.print("Numbers of ClassroomSessions to be added: ");
        int n = sc.nextInt();

        ArrayList<UtilsDomain.Pair> a = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            UtilsDomain.Pair<Classroom, Session> p = new UtilsDomain.Pair(classroomConstructor(), sessionConstructor());
            a.add(p);
        }

        cs.setClassroomSessionSet(a);

        m.setDomain(cs);
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
        ClassroomSession cs = m.getDomain();
    }

    public static void testDomainSize() {
        System.out.println(m.domainSize());
    }

    public static void testAssign() {
        m.assign(new UtilsDomain.Pair<Classroom, Session>(classroomConstructor(), sessionConstructor()));
    }

    public static void testGetValueDomain() {
        if (!interactive)   System.out.print("Id of the value: ");
        UtilsDomain.Pair<Classroom, Session> p = m.getValueDomain(sc.nextInt());

        Vector<String> vc = p.first.toStr();
        Vector<String> vs = p.second.toStr();

        for (String s : vc) {
            System.out.print(s + " ");
        }

        for (String s : vs) {
            System.out.print(s + " ");
        }
    }

    public static void testDeleteFromDomain() {
        if (!interactive)    System.out.print("Id of the value: ");
        m.deleteFromDomain(sc.nextInt());
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

    public static void main(String args[]) throws FileNotFoundException {
        final PrintStream oldStdout = System.out;

        if (args.length > 0) {
            interactive = true;

            try {
                sc = new Scanner(new FileReader("./data/drivers/in/" + args[0]));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            ps = new PrintStream(new BufferedOutputStream(new FileOutputStream(new File("./data/drivers/out/" + args[1]),true)),true);
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
                    testConstructorWithMembers();
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
        System.out.println("\t0 -> Basic Constructor");
        System.out.println("\t1 -> Constructor with Members");
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
