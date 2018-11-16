package src.domain.drivers;

import src.domain.classes.Session;
import src.domain.utils.UtilsDomain;

import java.io.*;
import java.util.Scanner;
import java.util.Vector;

public class SessionDriver {
    private static Session s;
    private static Scanner sc = new Scanner(System.in);
    private static PrintStream ps;
    private static boolean interactive = false;

    public static void testConstructor() {

        int hour;
        UtilsDomain.Day day;

        //Hour
        hour = sc.nextInt();
        //Day of the week 0.MONDAY, 1.TUESDAY, 2.WEDNESDAY, 3.THURSDAY, 4.FRIDAY
        day = UtilsDomain.Day.valueOf(sc.next());

        s = new Session(day, hour);
        //Session successfully
    }

    public static void testConstructorFromVector( ) {
        Vector<String> myVector = new Vector<>(2);

        //Hour
        myVector.add(sc.next());
        //Day of the week 0.MONDAY, 1.TUESDAY, 2.WEDNESDAY, 3.THURSDAY, 4.FRIDAY
        myVector.add(sc.next());

        s = new Session(myVector);

    }

    public static void testGetHour() { System.out.println(s.getHour()); }
    public static void testSetHour() {
        //Hour
        s.setHour(sc.nextInt());
    }
    public static void testGetDay () { System.out.println(s.getDay().toString()); }
    public static void testSetDay()  {
        //Day of the week 0.MONDAY, 1.TUESDAY, 2.WEDNESDAY, 3.THURSDAY, 4.FRIDAY
        s.setDay(UtilsDomain.Day.valueOf(sc.next()));
    }
    public static void testToStr(){
        Vector<String> myStringVector = s.toStr();
        for(int ii=0; ii<myStringVector.size(); ii++){
            System.out.println(myStringVector.get(ii));
        }
    }

    public static void testCompare( ) {

        int hour;
        UtilsDomain.Day day;

        //Hour
        hour = sc.nextInt();
        //Day of the week 0.MONDAY, 1.TUESDAY, 2.WEDNESDAY, 3.THURSDAY, 4.FRIDAY
        day = UtilsDomain.Day.valueOf(sc.next());
        Session s1 = new Session(day, hour);

        //Operator
        String op = sc.next();

        //Hour
        hour = sc.nextInt();
        //Day of the week 0.MONDAY, 1.TUESDAY, 2.WEDNESDAY, 3.THURSDAY, 4.FRIDAY
        day = UtilsDomain.Day.valueOf(sc.next());

        Session s2 = new Session(day, hour);
        System.out.println(Session.compare(s1,op,s2));
    }
    public static void menu(){
        System.out.print("------------------------------------------ \n");
        System.out.print("------------------MENU-------------------- \n");
        System.out.print("------------------------------------------ \n");
        System.out.print("1->Test Constructor \n");
        System.out.print("2->Test Constructor from String Vector \n");
        System.out.print("3->Test GetHour \n");
        System.out.print("4->Test GetDay \n");
        System.out.print("5->Test SetHour \n");
        System.out.print("6->Test SetDay \n");
        System.out.print("7->Test ToStr \n");
        System.out.print("8->Test Compare \n");
        System.out.print("9->Exit  \n");
    }

    public static void main (String [] args) throws FileNotFoundException {

        interactive = true;

        try {
            sc = new Scanner(new FileReader("./data/drivers/in/SessionFile.in"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        ps = new PrintStream(new BufferedOutputStream(new FileOutputStream(new File("./data/drivers/out/SessionFile.out"),false)),true);
        System.setOut(ps);


        if (!interactive) menu();

        boolean eof = false;

        do{

            switch (sc.nextInt()) {
                case 1:
                    testConstructor();
                    break;
                case 2:
                    testConstructorFromVector();
                    break;
                case 3:
                    testGetHour();
                    break;
                case 4:
                    testGetDay();
                    break;
                case 5:
                    testSetHour();
                    break;
                case 6:
                    testSetDay();
                    break;
                case 7:
                    testToStr();
                    break;
                case 8:
                    testCompare();
                    break;
                default:
                    System.out.println("Input error!\n");
            }
            if (!interactive) {
                clearConsole();
                menu();
            }
        } while (!eof && sc.hasNextInt());
        ps.close();
        sc.close();
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
}
