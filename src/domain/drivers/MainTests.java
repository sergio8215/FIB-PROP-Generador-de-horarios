package src.domain.drivers;


import java.util.Scanner;

public class MainTests {

    private static Scanner sc = new Scanner(System.in);

    private static void menu() {
        System.out.println("------------------------------------------");
        System.out.println("\nWrite the number of the class you want to test:");
        System.out.println("\t0 -> Subject");
        System.out.println("\t1 -> Session");
        System.out.println("\t2 -> TheoryClassroom");
        System.out.println("\t3 -> LaboratoryClassroom");
        System.out.println("\t4 -> TheoryClass");
        System.out.println("\t5 -> LaboratoryClass");
        System.out.println("\t6 -> ProblemsClass");
        System.out.println("\t7 -> SubjectsSet");
        System.out.println("\t8 -> ClassSet");
        System.out.println("\t9 -> ClassroomSet");
        System.out.println("\t10 -> MUS");
        System.out.println("\t11 -> ClassroomSession");
        System.out.println("\t12 -> Constraints");
        System.out.println("\t13 -> Schedule");
        System.out.print("Option: ");
    }

    public static void main(String[] args) throws Exception {   // TODO: IMPLEMENTAR DIFF

        menu();

        do {

            switch (sc.nextInt()) {
                case 0:
                    subjectTest();
                    break;
                case 1:
                    sessionTest();
                    break;
                case 2:
                    theoryClassroomTest();
                    break;
                case 3:
                    laboratoryClassroomTest();
                    break;
                case 4:
                    theoryClassTest();
                    break;
                case 5:
                    laboratoryClassTest();
                    break;
                case 6:
                    problemsClassTest();
                    break;
                case 7:
                    subjectsSetTest();
                    break;
                case 8:
                    classSetTest();
                    break;
                case 9:
                    classroomSetTest();
                    break;
                case 10:
                    MUSTest();
                    break;
                case 11:
                    classroomSessionTest();
                    break;
                case 12:
                    constraintsTest();
                    break;
                case 13:
                    scheduleTest();
                    break;
                default:
                    System.out.println("Input error!\n");
            }

            clearConsole();
            menu();

        } while (sc.hasNextInt());
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

    private static boolean subjectTest() throws Exception {
        System.out.print("Name of input file: ");
        String fileIn = sc.next();

        System.out.print("Name of output file: ");
        String fileOut = sc.next();

        final String args[] = {fileIn, fileOut};

        SubjectDriver.main(args);

        return true;
    }

    private static boolean sessionTest() throws Exception {
        System.out.print("Name of input file: ");
        String fileIn = sc.next();

        System.out.print("Name of output file: ");
        String fileOut = sc.next();

        final String args[] = {fileIn, fileOut};

        SessionDriver.main(args);

        return true;
    }

    private static boolean theoryClassroomTest() throws  Exception {
        System.out.print("Name of input file: ");
        String fileIn = sc.next();

        System.out.print("Name of output file: ");
        String fileOut = sc.next();

        final String args[] = {fileIn, fileOut};

        TheoryClassroomDriver.main(args);

        return true;
    }

    private static boolean laboratoryClassroomTest() throws Exception {
        System.out.print("Name of input file: ");
        String fileIn = sc.next();

        System.out.print("Name of output file: ");
        String fileOut = sc.next();

        final String args[] = {fileIn, fileOut};

        LabClassroomDriver.main(args);

        return true;
    }

    private static boolean theoryClassTest() throws Exception {
        System.out.print("Name of input file: ");
        String fileIn = sc.next();

        System.out.print("Name of output file: ");
        String fileOut = sc.next();

        final String args[] = {fileIn, fileOut};

        TheoryClassDriver.main(args);

        return true;
    }

    private static boolean laboratoryClassTest() throws Exception {
        System.out.print("Name of input file: ");
        String fileIn = sc.next();

        System.out.print("Name of output file: ");
        String fileOut = sc.next();

        final String args[] = {fileIn, fileOut};

        LaboratoryClassDriver.main(args);

        return true;
    }

    private static boolean problemsClassTest() throws Exception {
        System.out.print("Name of input file: ");
        String fileIn = sc.next();

        System.out.print("Name of output file: ");
        String fileOut = sc.next();

        final String args[] = {fileIn, fileOut};

        ProblemsClassDriver.main(args);

        return true;
    }

    private static boolean subjectsSetTest() throws Exception {
        System.out.print("Name of input file: ");
        String fileIn = sc.next();

        System.out.print("Name of output file: ");
        String fileOut = sc.next();

        final String args[] = {fileIn, fileOut};

        SubjectsSetDriver.main(args);

        return true;
    }

    private static boolean classSetTest() throws Exception {
        System.out.print("Name of input file: ");
        String fileIn = sc.next();

        System.out.print("Name of output file: ");
        String fileOut = sc.next();

        final String args[] = {fileIn, fileOut};

        ClassSetDriver.main(args);

        return true;
    }

    private static boolean classroomSetTest() throws Exception {
        System.out.print("Name of input file: ");
        String fileIn = sc.next();

        System.out.print("Name of output file: ");
        String fileOut = sc.next();

        final String args[] = {fileIn, fileOut};

        ClassroomSetDriver.main(args);

        return true;
    }

    private static boolean MUSTest() throws Exception {
        System.out.print("Name of input file: ");
        String fileIn = sc.next();

        System.out.print("Name of output file: ");
        String fileOut = sc.next();

        final String args[] = {fileIn, fileOut};

        MUSDriver.main(args);

        return true;
    }

    private static boolean classroomSessionTest() throws Exception {
        System.out.print("Name of input file: ");
        String fileIn = sc.next();

        System.out.print("Name of output file: ");
        String fileOut = sc.next();

        final String args[] = {fileIn, fileOut};

        ClassroomSessionDriver.main(args);

        return true;
    }

    private static boolean constraintsTest() throws Exception {
        System.out.print("Name of input file: ");
        String fileIn = sc.next();

        System.out.print("Name of output file: ");
        String fileOut = sc.next();

        final String args[] = {fileIn, fileOut};

        ConstraintsDriver.main(args);

        return true;
    }

    private static boolean scheduleTest() throws Exception {
        System.out.print("Name of input file: ");
        String fileIn = sc.next();

        System.out.print("Name of output file: ");
        String fileOut = sc.next();

        final String args[] = {fileIn, fileOut};

        src.domain.drivers.ScheduleDriver.main(args);

        return true;
    }
}
