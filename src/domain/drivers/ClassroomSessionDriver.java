package src.domain.drivers;

import src.domain.classes.ClassroomSession;

import java.util.Scanner;
import java.util.Vector;

public class ClassroomSessionDriver {
    private static ClassroomSession cs;

    public static void testConstructor(){

    }
    public static void testGetClassroomSessionSet(){

    }
    public static void testSetClassroomSessionSet(){

    }
    public static void testGetPair(){

    }

    public static void  main(String args[]) {
        cs = new ClassroomSession();
        Scanner sc = new Scanner(System.in);
        write();
        do{

            int i = sc.nextInt();
            switch(i) {
                case 0:
                    testConstructor();
                    break;
                case 1:
                    testGetClassroomSessionSet();
                    break;
                case 2:
                    testSetClassroomSessionSet();
                    break;
                case 3:
                    testGetPair();
                    break;
                default:
                    System.out.println("\tEXIT");
            }
            write();
        }while(sc.hasNextInt());

    }

    //TODO: treure això pels jocs de proves
    public static void write() {
        System.out.println("------------------------------------------");
        System.out.println("\nWrite the number of the function you want to test:");
        System.out.println("\t0 -> Basic constructor");
        System.out.println("\t1 -> ClassroomSessionSet getter");
        System.out.println("\t2 -> ClassroomSessionSet setter");
        System.out.println("\t3 -> Get a Pair");
    }
}
