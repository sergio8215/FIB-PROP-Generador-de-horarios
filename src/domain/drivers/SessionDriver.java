package src.domain.drivers;

import src.domain.classes.Session;
import src.domain.utils.UtilsDomain;
import src.domain.utils.inout;

import java.util.Vector;

public class SessionDriver {
    private static Session s;
    private static inout i = new inout();

    public static void testConstructor() throws Exception{

        int hour;
        UtilsDomain.Day day;

        i.writeln("Hour: ");
        hour = i.readint();
        i.writeln("Day of the week 0.MONDAY, 1.TUESDAY, 2.WEDNESDAY, 3.THURSDAY, 4.FRIDAY: ");
        day = UtilsDomain.Day.values()[i.readint()];

        s = new Session(day, hour);
        i.writeln("Session successfully created");
    }

    public static void testConstructorFromVector( ) throws Exception{
        Vector<String> myVector = new Vector<>(2);

        i.writeln("Hour: ");
        myVector.add(i.readword());
        i.writeln("Day of the week 0.MONDAY, 1.TUESDAY, 2.WEDNESDAY, 3.THURSDAY, 4.FRIDAY: ");
        myVector.add(UtilsDomain.Day.values()[i.readint()].toString());

        s = new Session(myVector);
        i.writeln("Session successfully created");
    }

    public static void testGetHour() throws Exception{ i.writeln(s.getHour()); }
    public static void testSetHour() throws Exception{
        i.writeln("Hour: ");
        s.setHour(i.readint());
    }
    public static void testGetDay () throws Exception{ i.writeln(s.getDay().toString()); }
    public static void testSetDay() throws Exception {
        i.writeln("Day of the week 0.MONDAY, 1.TUESDAY, 2.WEDNESDAY, 3.THURSDAY, 4.FRIDAY: ");
        s.setDay(UtilsDomain.Day.values()[i.readint()]);
    }
    public static void testToStr()throws Exception{
        Vector<String> myStringVector = s.toStr();
        for(int ii=0; ii<myStringVector.size(); ii++){
            i.writeln(myStringVector.get(ii));
        }
    }

    public static void testCompare( ) throws Exception{

        int hour;
        UtilsDomain.Day day;

        i.writeln("Hour: ");
        hour = i.readint();
        i.writeln("Day of the week 0.MONDAY, 1.TUESDAY, 2.WEDNESDAY, 3.THURSDAY, 4.FRIDAY: ");
        day = UtilsDomain.Day.values()[i.readint()];
        Session s1 = new Session(day, hour);

        i.write("Operator:  ");
        String op = i.readword();

        i.writeln("Hour: ");
        hour = i.readint();
        i.writeln("Day of the week 0.MONDAY, 1.TUESDAY, 2.WEDNESDAY, 3.THURSDAY, 4.FRIDAY: ");
        day = UtilsDomain.Day.values()[i.readint()];

        Session s2 = new Session(day, hour);
        i.write(Session.compare(s1,op,s2));
    }


    public static void main (String [] args) throws Exception {

        int option = 0;

        while( option != 9) {

            i.write("------------------------------------------ \n");
            i.write("------------------MENU-------------------- \n");
            i.write("------------------------------------------ \n");
            i.write("1->Test Constructor \n");
            i.write("2->Test Constructor from String Vector \n");
            i.write("3->Test GetHour \n");
            i.write("4->Test GetDay \n");
            i.write("5->Test SetHour \n");
            i.write("6->Test SetDay \n");
            i.write("7->Test ToStr \n");
            i.write("8->Test Compare \n");
            i.write("9->Exit  \n");

            option = i.readint();


            switch (option) {
                case 1:
                    testConstructor();
                    i.writeln("\n Please press one key to continue");
                    System.in.read();
                    break;
                case 2:
                    testConstructorFromVector();
                    i.writeln("\n Please press one key to continue");
                    System.in.read();
                    break;
                case 3:
                    testGetHour();
                    i.writeln("\n Please press one key to continue");
                    System.in.read();
                    break;
                case 4:
                    testGetDay();
                    i.writeln("\n Please press one key to continue");
                    System.in.read();
                    break;
                case 5:
                    testSetHour();
                    i.writeln("\n Please press one key to continue");
                    System.in.read();
                    break;
                case 6:
                    testSetDay();
                    i.writeln("\n Please press one key to continue");
                    System.in.read();
                    break;
                case 7:
                    testToStr();
                    i.writeln("\n Please press one key to continue");
                    System.in.read();
                    break;
                case 8:
                    testCompare();
                    i.writeln("\n Please press one key to continue");
                    System.in.read();
                    break;
            }
        }
    }
}
