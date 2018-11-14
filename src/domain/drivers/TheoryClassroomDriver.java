package src.domain.drivers;

import src.domain.classes.TheoryClassroom;

import java.util.Scanner;
import java.util.Vector;

public class TheoryClassroomDriver {
    private static TheoryClassroom theo;

    public static void testConstructor(){
        Scanner sc = new Scanner(System.in);
        String name = sc.next();
        int cap = sc.nextInt();
        boolean multimedia = sc.nextBoolean();
        theo = new TheoryClassroom(name, cap, multimedia);
    }
    public static void testConstructorFromString(){
        Vector<String> vec = new Vector<String> (4);
        Scanner sc = new Scanner(System.in);

        vec.add(sc.next()); //name
        vec.add(String.valueOf(sc.nextInt())); //capacity
        vec.add(sc.next()); //type (Theory)
        vec.add(sc.next()); //multimedia
        vec.add("0"); //nComp

        theo = new TheoryClassroom(vec);
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
    public static void testToStr(){
        Vector<String> v = theo.toStr();
        System.out.println(v.get(0)
                + " " + v.get(1)
                + " " + v.get(2)
                + " " + v.get(3)
                + " " + v.get(4));
    }


    public static void  main(String args[]) {
        theo = new TheoryClassroom();
        Scanner sc = new Scanner(System.in);
        write();
        do{

            int i = sc.nextInt();
            switch(i) {
                case 0:
                    testConstructor();
                    break;
                case 1:
                    testConstructorFromString();
                    break;
                case 2:
                    testGetName();
                    break;
                case 3:
                    testGetCapacity();
                    break;
                case 4:
                    testGetType();
                    break;
                case 5:
                    testGetMultimedia();
                    break;
                case 6:
                    testToStr();
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
        System.out.println("\t1 -> Constructor from String");
        System.out.println("\t2 -> Name getter");
        System.out.println("\t3 -> Capacity getter");
        System.out.println("\t4 -> Type getter");
        System.out.println("\t5 -> Multimedia getter");
        System.out.println("\t6 -> Convert Object to String");
    }
}
