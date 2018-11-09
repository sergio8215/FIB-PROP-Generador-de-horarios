package src.Presenter;

import src.Domain.CtrlDomain;

import java.util.Scanner;

public class CtrlPresenter {

    CtrlDomain ctrlD;

    public static void main(String[] args) throws Exception {

        System.out.println("SCHEDULE GENERATOR\n");

        ctrlD = CtrlDomain();

        Scanner s = new Scanner(System.in);

        boolean end = false;
        while (!end){
            menuPrinter();

            int opt = s.nextInt();

            switch (opt){
                case 1:
                    scheduleGeneration();
                    clearConsole();
                    break;

                case 2:
                    // LOAD SCHEDULE
                case 3:
                    end = !end;
                    break;

                default:
                    clearConsole();
                    System.out.println("Input error. Try it again!");
                    menuPrinter();
                    opt = s.nextInt();
            }
        }


    }

    private static void menuPrinter() {
        System.out.println("1. Generate Schedule.\n");
        System.out.println("2. Load schedule.\n");
        System.out.println("3. Exit.\n");
        System.out.println("\n Which option do you want choose? \n");
        System.out.println("Option: ");
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

    private static void scheduleGeneration() throws Exception{ /// MODIFICAR PARA PASAR ESCENARIO

        final int numFilesSubjects = 2;
        final int numFilesClassroms = 2;

        Scanner s = new Scanner(System.in);

        menuScheduleGeneration();

        int subjectsFile;
        int classromsFile;

        boolean inputCorrect = false;
        while (!inputCorrect) {
            subjectsFile = s.nextInt();
            classromsFile = s.nextInt();

            if (subjectsFile < numFilesSubjects && classromsFile < numFilesClassroms)   inputCorrect = true;
            else {
                clearConsole();
                System.out.println("Input error. Try it again. \n");
                s.nextLine();
                menuScheduleGeneration();
            }
        }

        ctrlD.defaultScennario();
        // RESTRICCIONES

    }

    private static void menuScheduleGeneration() {
        System.out.println("To generate the schedule it is necessary to indicate: \n " +
                "- Set of Subjects: \n" +
                "  1. ArchivoSubjects1 \n" +
                "  2. ArchivoSubjects2 \n" +
                "- Set of Classroms.\n" +
                "  1. ArchivoClassroms1\n" +
                "  2. ArchivoClassrom2\n");
        System.out.println("Indicates following the scheme: numOfSubjectsFile numOfClassromsFile");
        System.out.println("Option: ");
    }

}