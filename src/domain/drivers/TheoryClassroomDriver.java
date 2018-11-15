package src.domain.drivers;

import src.domain.classes.Classroom;
import src.domain.classes.LabClassroom;
import src.domain.classes.TheoryClassroom;
import src.domain.utils.UtilsDomain;

import java.io.*;
import java.util.Scanner;
import java.util.Vector;

public class TheoryClassroomDriver {
    private static TheoryClassroom theo;
    private static Scanner sc;
    private static PrintStream ps;
    private static boolean fromFile = false;

    private static void writeClassroom(Classroom c){
        String s = c.getName() + " "
                + String.valueOf(c.getCapacity()) + " "
                + String.valueOf(c.getType().name()) + " ";
        if(c.isMultimedia()) s += "true ";
        else s += "false ";
        if(c.getType() == UtilsDomain.ClassType.LABORATORY) {
            LabClassroom l = (LabClassroom) c;
            s += String.valueOf(l.getNumComputers());
        }
        else s += "0";

        System.out.println(s);
    }

    public static void testConstructor(){
        String name = sc.next();
        int cap = sc.nextInt();
        boolean multimedia = sc.nextBoolean();
        theo = new TheoryClassroom(name, cap, multimedia);
    }
    public static void testConstructorFromString(){
        Vector<String> vec = new Vector<String> (4);

        vec.add(sc.next()); //name
        vec.add(String.valueOf(sc.nextInt())); //capacity
        vec.add(sc.next()); //type (Theory)
        vec.add(sc.next()); //multimedia
        vec.add("0"); //nComp

        theo = new TheoryClassroom(vec);
    }
    public static void testFromStr(){
        Vector<String> v = new Vector<> (5);
        v.add(sc.next()); //name
        v.add(sc.next()); //capacity
        v.add("THEORY"); //type
        v.add(sc.next()); //multimedia
        v.add("0"); //nComp

        writeClassroom(Classroom.fromStr(v));
    }
    public static void testGetName(){
        System.out.println(theo.getName());
    }
    public static void testGetCapacity(){
        System.out.println(theo.getCapacity());
    }
    public static void testGetType(){
        System.out.println(theo.getType().name());
    }
    public static void testGetMultimedia(){
        if(theo.isMultimedia()) System.out.println("true");
        else System.out.println("false");
    }
    public static void testSetName(){
        theo.setName(sc.next());
    }
    public static void testSetCapacity(){
        theo.setCapacity(sc.nextInt());
    }
    public static void testSetMultimedia(){
        theo.setMultimedia(sc.next().equals("true"));
    }
    public static void testToStr(){
        Vector<String> v = theo.toStr();
        System.out.println(v.get(0)
                + " " + v.get(1)
                + " " + v.get(2)
                + " " + v.get(3)
                + " " + v.get(4));
    }


    public static void  main(String args[]) throws FileNotFoundException {
        theo = new TheoryClassroom();
        if(args.length > 0) {
            fromFile = true;

            try{
                sc = new Scanner(new FileReader("./data/" + args[0]));
            }catch(FileNotFoundException e) {
                e.printStackTrace();
            }

            ps = new PrintStream(new BufferedOutputStream(new FileOutputStream(new File("./data/testing/" + args[1]),true)),true);
            System.setOut(ps);
        }
        else {
            sc = new Scanner(System.in);
        }
        if(!fromFile) write();
        boolean eof = false;
        do{
            switch(sc.nextInt()) {
                case 0:
                    testConstructor();
                    break;
                case 1:
                    testConstructorFromString();
                    break;
                case 2:
                    testFromStr();
                    break;
                case 3:
                    testGetName();
                    break;
                case 4:
                    testGetCapacity();
                    break;
                case 5:
                    testGetType();
                    break;
                case 6:
                    testGetMultimedia();
                    break;
                case 7:
                    testSetName();
                    break;
                case 8:
                    testSetCapacity();
                    break;
                case 9:
                    testSetMultimedia();
                    break;
                case 10:
                    testToStr();
                    break;

                default:
                    System.out.println("Input error\n");
            }
            if(!fromFile) write();
        }while(!eof && sc.hasNextInt());

    }

    //TODO: treure això pels jocs de proves
    public static void write() {
        System.out.println("------------------------------------------");
        System.out.println("\nWrite the number of the function you want to test:");
        System.out.println("\t0 -> Basic constructor");
        System.out.println("\t1 -> Constructor from String");
        System.out.println("\t2 -> From String");
        System.out.println("\t3 -> Name getter");
        System.out.println("\t4 -> Capacity getter");
        System.out.println("\t5 -> Type getter");
        System.out.println("\t6 -> Multimedia getter");
        System.out.println("\t7 -> Name setter");
        System.out.println("\t8 -> Capacity setter");
        System.out.println("\t9 -> Multimedia setter");
        System.out.println("\t10 -> Convert Object to String");
    }
}
