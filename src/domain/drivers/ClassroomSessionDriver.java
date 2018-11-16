package src.domain.drivers;

import src.domain.classes.Classroom;
import src.domain.classes.ClassroomSession;
import src.domain.classes.ClassroomSet;
import src.domain.classes.Session;
import src.domain.utils.UtilsDomain;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Vector;

public class ClassroomSessionDriver {
    private static ClassroomSession classroomSession;
    private static Scanner sc;
    private static PrintStream ps;
    private static boolean fromFile = false;

    private static String vectorToString(Vector<String> v) {
        String s = "[";
        for(int i = 0; i < v.size(); ++i) {
            s = s + " " + v.get(i);
        }
        s += " ]";
        return s;
    }

    public static void testConstructor(){
        ArrayList<Classroom> arrC = new ArrayList<>();
        int quant = sc.nextInt();
        for(int i = 0; i < quant; ++i) {
            Vector<String> v = new Vector<> (5);
            v.add(sc.next()); //name
            v.add(sc.next()); //capacity
            v.add(sc.next().toUpperCase()); //type
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
            v.add(sc.next().toUpperCase()); //type
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
            String sol = vectorToString(c.toStr()) + " " + vectorToString(s.toStr());
            System.out.println(sol);
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

    public static void  main(String args[]) throws FileNotFoundException{
        final PrintStream oldStdout = System.out;
        classroomSession = new ClassroomSession();
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
