package src.domain.drivers;

import src.domain.classes.ClassroomSession;
import src.domain.classes.Schedule;

import java.io.*;
import java.util.Scanner;

public class ScheduleDriver {
    private static Schedule schedule;
    private static Scanner sc;
    private static PrintStream ps;
    private static boolean fromFile = false;

    public static void  main(String args[]) throws FileNotFoundException {
        final PrintStream oldStdout = System.out;
         schedule = new Schedule();
        if(args.length > 0) {
            fromFile = true;

            try{
                sc = new Scanner(new FileReader("./data/drivers/in/" + args[0]));
            }catch(FileNotFoundException e) {
                e.printStackTrace();
            }

            ps = new PrintStream(new BufferedOutputStream(new FileOutputStream(new File("./data/drivers/out/" + args[1]),true)),true);
            System.setOut(ps);
        }
        else {
            sc = new Scanner(System.in);
        }
        if(!fromFile) write();
        boolean eof = false;
        do{

            int i = sc.nextInt();
            switch(i) {
                case 0:
                    break;
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 99:
                    eof = true;
                default:
                    System.out.println("\tInput Error");
            }
            if(!fromFile) write();
        }while(sc.hasNextInt());

        if(fromFile) {
            System.setOut(oldStdout);
            ps.close();
        }
    }

    public static void write() {
        System.out.println("------------------------------------------");
        System.out.println("\nWrite the number of the function you want to test:");
        System.out.println("\t0 -> Basic constructor");
        System.out.println("\t1 -> Constructor by copy");
        System.out.println("\t2 -> ClassroomSessionSet getter");
        System.out.println("\t3 -> ClassroomSessionSet setter");
        System.out.println("\t4 -> Get a Pair");
        System.out.println("\t5 -> Size of classroomSet");
        System.out.println("\t6 -> Delete a Pair");
    }
}
