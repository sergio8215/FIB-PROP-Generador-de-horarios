package src.presentation;

import src.domain.classes.Schedule;
import src.domain.controllers.CtrlDomain;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Vector;

public class CtrlPresenter {

    private static CtrlDomain ctrlDomain;

    public static void main(String[] args) throws Exception {

        System.out.println("SCHEDULE GENERATOR\n");

        ctrlDomain = new CtrlDomain();

        Scanner s = new Scanner(System.in);

        boolean end = false;
        while (!end){
            mainMenu();

            int opt = s.nextInt();

            switch (opt){
                case 1:
                    scheduleGeneration();
                    //clearConsole();
                    break;

                case 2:
                    ArrayList<String> filesList = ctrlDomain.listScheduleFiles();
                    System.out.println("Please input the number of one saved files: ");
                    int i = 0;
                    for (String f : filesList ){
                        System.out.println(i+". "+f);
                        i++;
                    }
                    opt = s.nextInt();
                    ctrlDomain.loadSchedule(opt);

                case 3:
                    end = !end;
                    break;

                default:
                    //clearConsole();
                    System.out.println("Input error. Try it again!");
                    mainMenu();
                    opt = s.nextInt();
            }
        }

    }

    private static void mainMenu() {
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

    private static void scheduleGeneration() throws Exception { /// MODIFICAR PARA PASAR ESCENARIO

        final int numFilesSubjects = 2;
        final int numFilesClassroms = 2;

        Scanner s = new Scanner(System.in);

        scheduleGenerationMenu();

        int subjectsFile = 0;
        int classromsFile = 0;

        boolean inputCorrect = false;
        while (!inputCorrect) {
            subjectsFile = s.nextInt();
            classromsFile = s.nextInt();

            if (subjectsFile < numFilesSubjects && classromsFile < numFilesClassroms)   inputCorrect = true;
            else {
                //clearConsole();
                System.out.println("Input error. Try it again. \n");
                s.next();
                scheduleGenerationMenu();
            }
        }

        String strSubjectsFile = "file" + subjectsFile + ".json";
        String classroomsFile = "file" + classromsFile + ".json";

        ctrlDomain.createScenario(classroomsFile, strSubjectsFile);

        System.out.println("List of restrictions that will apply:\n");
        System.out.println("1. \n");
        System.out.println("\n Do you want to continue with the schedule generation? [S/N]");

        switch (s.next().toUpperCase()) {
            case "S":
                Schedule schedule = ctrlDomain.generateSchedule();

                System.out.print("Save schedule?: [S/N]");
                String ss = s.next();

                if (ss.contentEquals("S")){
                    System.out.print("Name of the file: ");
                    String fileName = s.next();
                    ctrlDomain.saveSchedule(fileName, schedule);
                }
                break;

            case "N":
                break;

            default:
                System.out.println("Input error. Try it again!");
        }

    }

    private static void scheduleGenerationMenu() {
        System.out.println("To generate the schedule it is necessary to indicate: \n " +
                "- Set of Subjects: \n" +
                "  1. ArchivoSubjects1 \n" +
                "  2. ArchivoSubjects2 \n" +
                "- Set of Classrooms.\n" +
                "  1. ArchivoClassrooms1\n" +
                "  2. ArchivoClassroom2\n");
        System.out.println("Indicates following the scheme: numOfSubjectsFile numOfClassromsFile");
        System.out.println("Option: ");
    }

    private static void showSchedule() {
        
    }

}