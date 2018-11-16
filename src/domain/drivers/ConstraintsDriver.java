package src.domain.drivers;

import src.domain.classes.Classroom;
import src.domain.classes.Constraints;
import src.domain.classes.MUS;
import src.domain.classes.Session;
import src.domain.utils.UtilsDomain;

import java.io.*;
import java.util.Scanner;
import java.util.Vector;

public class ConstraintsDriver {

    private static Scanner sc;
    private static PrintStream ps;
    private static boolean interactive = false;

    private static MUS constructorMUS() {
        Vector<Vector<String>> mus = new Vector<>(3);

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

        return new MUS(mus);
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

    private static UtilsDomain.Pair<Classroom, Session> classroomSessionConstructor() {
        UtilsDomain.Pair<Classroom, Session> p = new UtilsDomain.Pair(classroomConstructor(), sessionConstructor());
        return p;
    }


    public static void testsizeClassroomUnaryConstraint() {
        System.out.println(Constraints.sizeClassroomUnaryConstraint(constructorMUS(), classroomSessionConstructor()));
    }

    public static void testtypeClassroomUnaryConstraint() {
        System.out.println(Constraints.typeClassroomUnaryConstraint(constructorMUS(), classroomSessionConstructor()));

    }

    public static void testshiftClassUnaryConstraint() {
        System.out.println(Constraints.shiftClassUnaryConstraint(constructorMUS(), classroomSessionConstructor()));

    }

    public static void testnotSameClassroomAndSession() {
        System.out.println(Constraints.notSameClassroomAndSession(constructorMUS(), constructorMUS()));
    }

    public static void testtheoryAndLabsOfClassNoTogether() {
        System.out.println(Constraints.theoryAndLabsOfClassNoTogether(constructorMUS(), constructorMUS()));
    }

    public static void testtheorysOfSubjectsOfSameLevelNoTogether() {
        System.out.println(Constraints.theoryOfSubjectFromDifferentClassesNoTogether(constructorMUS(), constructorMUS()));
    }

    public static void testtheoryOfSubjectFromDifferentClassesNoTogether() {
        System.out.println(Constraints.theoryOfSubjectFromDifferentClassesNoTogether(constructorMUS(), constructorMUS()));
    }

    public static void testLabsAndProblemsFromDifferentSubjectsOfSameGroupNoTogether() {
        System.out.println(Constraints.LabsAndProblemsFromDifferentSubjectsOfSameGroupNoTogether(constructorMUS(), constructorMUS()));
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
                    testsizeClassroomUnaryConstraint();
                    break;
                case 1:
                    testtypeClassroomUnaryConstraint();
                    break;
                case 2:
                    testshiftClassUnaryConstraint();
                    break;
                case 3:
                    testnotSameClassroomAndSession();
                    break;
                case 4:
                    testtheoryAndLabsOfClassNoTogether();
                    break;
                case 5:
                    testtheorysOfSubjectsOfSameLevelNoTogether();
                    break;
                case 6:
                    testtheoryOfSubjectFromDifferentClassesNoTogether();
                    break;
                case 7:
                    testLabsAndProblemsFromDifferentSubjectsOfSameGroupNoTogether();
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
        System.out.println("\t0 -> Size Classroom Unary Constraint");
        System.out.println("\t1 -> Type Classroom Unary Constraint");
        System.out.println("\t2 -> Shift Class Unary Constraint");
        System.out.println("\t3 -> Not Same Classroom And Session");
        System.out.println("\t4 -> Theory and Labs of Class no Together");
        System.out.println("\t5 -> Theory of Subject of Same Leve no Together");
        System.out.println("\t6 -> Theory of Subject From Different Classes no Together");
        System.out.println("\t7 -> Labs and Problems From Different Subjects of Same Group no Together");

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
