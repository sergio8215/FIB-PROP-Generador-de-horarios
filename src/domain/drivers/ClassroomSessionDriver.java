package src.domain.drivers;

import src.domain.classes.Classroom;
import src.domain.classes.ClassroomSession;
import src.domain.classes.ClassroomSet;
import src.domain.classes.Session;
import src.domain.utils.UtilsDomain;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Vector;

public class ClassroomSessionDriver {
    private static ClassroomSession classroomSession;
    private static Scanner sc;
    private static boolean fromFile = false;

    private static void printVector(Vector<String> v) {
        for(int i = 0; i < v.size(); ++i) {
            System.out.println(v.get(i));
        }
    }

    public static void testConstructor(){
        ArrayList<Classroom> arrC = new ArrayList<>();
        int quant = sc.nextInt();
        for(int i = 0; i < quant; ++i) {
            Vector<String> v = new Vector<> (5);
            v.add(sc.next()); //name
            v.add(sc.next()); //capacity
            v.add(sc.next()); //type
            v.add(sc.next()); //multimedia
            v.add(v.get(2).equals("LABORATORY")?sc.next():"0"); //nComp
            arrC.add(Classroom.fromStr(v));
        }
        ClassroomSet clSet = new ClassroomSet(arrC);
        classroomSession = new ClassroomSession(clSet);
    }
    public static void testConstructorByCopy(){
        ArrayList<Classroom> arrC = new ArrayList<>();
        int quant = sc.nextInt();
        for(int i = 0; i < quant; ++i) {
            Vector<String> v = new Vector<> (5);
            v.add(sc.next()); //name
            v.add(sc.next()); //capacity
            v.add(sc.next()); //type
            v.add(sc.next()); //multimedia
            v.add(v.get(2).equals("LABORATORY")?sc.next():"0"); //nComp
            arrC.add(Classroom.fromStr(v));
        }
        ClassroomSet clSet = new ClassroomSet(arrC);
        ClassroomSession clsess = new ClassroomSession(clSet);

        classroomSession = new ClassroomSession(clsess);
    }
    public static void testGetClassroomSessionSet(){
        ArrayList<UtilsDomain.Pair> clss = classroomSession.getClassroomSessionSet();
        for (UtilsDomain.Pair cls : clss) {
            Classroom c = (Classroom) cls.first;
            Session s = (Session) cls.second;
            printVector(c.toStr());
            printVector(s.toStr());
        }
    }
    public static void testSetClassroomSessionSet(){
        ArrayList<Classroom> arrC = new ArrayList<>();
        int quant = sc.nextInt();
        for(int i = 0; i < quant; ++i) {
            Vector<String> v = new Vector<> (5);
            v.add(sc.next()); //name
            v.add(sc.next()); //capacity
            v.add(sc.next()); //type
            v.add(sc.next()); //multimedia
            v.add(v.get(2).equals("LABORATORY")?sc.next():"0"); //nComp
            arrC.add(Classroom.fromStr(v));
        }
        ClassroomSet clSet = new ClassroomSet(arrC);
        ClassroomSession clsess = new ClassroomSession(clSet);
        classroomSession.setClassroomSessionSet(clsess.getClassroomSessionSet());
    }
    public static void testGetPair(){
        UtilsDomain.Pair p = classroomSession.getPair(sc.nextInt());
        Classroom c = (Classroom) p.first;
        Session s = (Session) p.second;
        System.out.println(c.toStr() + " " + s.toStr());
    }
    public static void testSize(){
        System.out.println(String.valueOf(classroomSession.size()));
    }
    public static void testDelete(){
        boolean b = classroomSession.delete(sc.nextInt());
        if(!b) System.out.println("Incorrect index");
    }

    public static void  main(String args[]) {
        classroomSession = new ClassroomSession();
        if(args.length > 0) {
            fromFile = true;

            try{
                sc = new Scanner(new FileReader("./data/" + args[0]));
            }catch(FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        else {
            sc = new Scanner(System.in);
        }
        if(!fromFile) write();
        do{

            int i = sc.nextInt();
            switch(i) {
                case 0:
                    testConstructor();
                    break;
                case 1:
                    testConstructorByCopy();
                    break;
                case 2:
                    testGetClassroomSessionSet();
                    break;
                case 3:
                    testSetClassroomSessionSet();
                    break;
                case 4:
                    testGetPair();
                    break;
                case 5:
                    testSize();
                    break;
                case 6:
                    testDelete();
                    break;
                default:
                    System.out.println("\tInput Error");
            }
            if(!fromFile) write();
        }while(sc.hasNextInt());

    }

    //TODO: treure això pels jocs de proves
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
