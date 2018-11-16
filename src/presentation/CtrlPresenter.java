package src.presentation;

import src.domain.classes.MUS;
import src.domain.classes.Schedule;
import src.domain.controllers.CtrlDomain;
import src.domain.utils.UtilsDomain;

import java.util.*;


/**
 * Presentation Controller
 * @author Joaquim Gomez
 */
public class CtrlPresenter {

    // Members

    private static CtrlDomain ctrlDomain;


    // Methods

    /**
     * Main method of the program.
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {

        System.out.println("\t\t\t============ SCHEDULE GENERATOR ============");

        ctrlDomain = new CtrlDomain();

        Scanner s = new Scanner(System.in);

        boolean end = false;
        while (!end){
            mainMenu();


            switch (s.nextInt()){
                case 1:
                    scheduleGeneration();
                    break;

                case 2:
                    ArrayList<String> filesList = ctrlDomain.listScheduleFiles();

                    int i = 0;
                    for (String f : filesList ){
                        System.out.println(i+". "+f);
                        i++;
                    }

                    System.out.print("Please input the number of one saved files: ");

                    ctrlDomain.loadSchedule(s.nextInt());

                    UtilsDomain.ResultOfQuery<Schedule> rq = ctrlDomain.showSchedule();
                    showSchedule(rq.result);    // TODO: PROBAR RECUPERAR HORARIO

                    System.out.println("\n Please press one key to continue");
                    System.in.read();

                case 3:
                    end = !end;
                    break;

                default:
                    clearConsole();

                    System.out.println("Input error. Try it again!");
                    mainMenu();

                    break;
            }

            clearConsole();
        }

    }

    /**
     * Print the menu on the screen.
     */
    private static void mainMenu() {
        System.out.println("1. Generate Schedule.");
        System.out.println("2. Load schedule.");
        System.out.println("3. Exit.");
        System.out.print("Option: ");
    }

    /**
     * Clear the console.
     */
    private static void clearConsole() {
        final String os = System.getProperty("os.name");

        try {
            if (os.contains("Windows"))     Runtime.getRuntime().exec("cls");
            else    Runtime.getRuntime().exec("clear");
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    /**
     * Interaction with the user to pass to CtrlDomain the information to generate the schedule and start the generation.
     * @throws Exception
     */
    private static void scheduleGeneration() throws Exception {

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
                System.out.println("Input error. Try it again. \n");
                s.next();
                scheduleGenerationMenu();
            }
        }

        String strSubjectsFile = "file" + subjectsFile + ".json";
        String classroomsFile = "file" + classromsFile + ".json";

        ctrlDomain.createScenario(classroomsFile, strSubjectsFile);

        System.out.println("List of restrictions that will apply:\n");
        System.out.println("1. \n\n");                                    // TODO: LISTAR LAS RESTRICCIONES
        System.out.print("\n Do you want to continue with the schedule generation? [S/N]: ");

        switch (s.next().toUpperCase()) {
            case "S":
                ctrlDomain.generateSchedule();
                Schedule sch = ctrlDomain.showSchedule().result;
                showSchedule(sch);

                System.out.print("Save schedule?: [S/N]"); // TODO: PROBAR GUARDAR HORARIO
                String ss = s.next().toUpperCase();

                if (ss.contentEquals("S")){
                    System.out.print("Name of the file: ");
                    String fileName = s.next();
                    ctrlDomain.saveSchedule(fileName, sch);
                }
                break;

            case "N":
                break;

            default:
                System.out.println("Input error. Try it again!");
        }

    }

    /**
     * Print the menu of the Schedule Generation on the screen.
     */
    private static void scheduleGenerationMenu() {
        System.out.println("To generate the schedule it is necessary to indicate: \n " +
                "\tSet of Subjects:\n" +
                "\t\t1. subjects1.json\n" +
                "\t\t2. subjects2.json\n" +
                "\tSet of Classrooms.\n" +
                "\t\t1. classrooms1.json\n" +
                "\t\t2. classrooms2.json\n");
        System.out.println("Indicates following the schema: numOfSubjectsFile numOfClassromsFile; i.e: 1 1");
        System.out.print("Option: ");
    }

    /**
     * Print the schedule on the screen.
     * @param s Schedule to be printed.
     */
    private static void showSchedule(Schedule s) {
        HashMap<String, ArrayList<MUS> > timetable = s.getTimetable();

        Set<String> keys = timetable.keySet();

        for (String nameSubject : keys) {
            System.out.println("\n SUBJECT - " + nameSubject.toUpperCase());

            ArrayList<MUS> elems = timetable.get(nameSubject);

            for (MUS m : elems) {
                System.out.println(m.getClassClass().getIdentifier() + ": " + m.getClassroom().getName() + " - " + m.getSession().getDay() + " " + m.getSession().getHour());
            }

            System.out.print("\n");
        }
    }

}