package src.domain.drivers;

import src.domain.classes.ClassClass;
import src.domain.classes.LaboratoryClass;
import src.domain.classes.Subject;
import src.domain.utils.UtilsDomain;
import src.domain.utils.inout;

import java.io.*;
import java.util.Scanner;
import java.util.Vector;

public class LaboratoryClassDriver {

    public static ClassClass tc;
    private static Scanner sc = new Scanner(System.in);
    private static PrintStream ps;
    private static boolean interactive = false;
    
    private static void testConstructor() throws Exception {
        String identifier;
        int group;
        int subGroup;
        UtilsDomain.ClassType typeG;
        UtilsDomain.typeShift shift;
        int quantityStudents;


        //ID
        identifier = sc.next();
        //Group
        group = sc.nextInt();
        //subGroup
        subGroup = sc.nextInt();
        //Type of group 0.Theory, 1.Lab, 2.Problems
        //typeG = UtilsDomain.ClassType.values()[0];
        //Shift of group 0.Morning, 1.Afternoon
        shift = UtilsDomain.typeShift.valueOf(sc.next());
        //Quantity students
        quantityStudents = sc.nextInt();

        tc = new LaboratoryClass(identifier, readSubject(), group, quantityStudents, shift, subGroup);
        //-"
        //Theory Class successfully
    }

    public static void testConstructorFromStr () throws Exception {
        Vector<String> myVector = new Vector<>(15);

        //ID
        myVector.add(sc.next());
        //Group
        myVector.add(sc.next());
        //subGroup
        myVector.add(sc.next());
        //Type of group 0.Theory, 1.Lab, 2.Problems
        myVector.add("LABORATORY");
        //Shift of group 0.Morning, 1.Afternoon
        myVector.add(sc.next());
        //Quantity students
        myVector.add(sc.next());

        //Subject Name
        myVector.add(sc.next());
        //Num_students
        myVector.add(sc.next());
        //Level
        myVector.add(sc.next());
        //Theory_hours
        myVector.add(sc.next());
        //Laboratory_hours
        myVector.add(sc.next());
        //Problems_hours
        myVector.add(sc.next());
        //Number of groups
        myVector.add(sc.next());
        //Number of Subgroups
        myVector.add(sc.next());
        //Shift 0.Morning, 1.Afternoon, 2.Both
        myVector.add(sc.next());

        tc = new LaboratoryClass(myVector);
        //-"
        //Theory Class successfully
    }

    public static Subject readSubject() throws Exception{

        String subjectName;
        int num_students;
        int level;
        int[] hoursClasses = new int[3];
        int[] numberOfGroups = new int[2];
        UtilsDomain.typeShift shiftSubj;

        //Subject Name
        subjectName = sc.next();
        //Num_students
        num_students = sc.nextInt();
        //Level
        level = sc.nextInt();
        //Theory_hours
        hoursClasses[0] = sc.nextInt();
        //Laboratory_hours
        hoursClasses[1] = sc.nextInt();
        //Problems_hours
        hoursClasses[2] = sc.nextInt();
        //Number of groups
        numberOfGroups[0] = sc.nextInt();
        //Number of Subgroups
        numberOfGroups[1] = sc.nextInt();
        //Shift 0.Morning, 1.Afternoon, 2.Both
        shiftSubj = UtilsDomain.typeShift.valueOf(sc.next());

        Subject subject = new Subject(subjectName, num_students, level, hoursClasses, numberOfGroups, shiftSubj);
        return subject;

    }

    public static void testGetGroup() throws Exception {System.out.println( tc.getGroup() );}
    public static void testGetIdentifier() throws Exception {System.out.println( tc.getIdentifier() );}
    public static void testGetSubject() throws Exception {
        Subject subject = tc.getSubject();
        System.out.println( subject.getName() );
        System.out.println( subject.getNumberStudents() );
        System.out.println( subject.getLevel() );
        System.out.println( subject.getHoursClasses()[0] );
        System.out.println( subject.getHoursClasses()[1] );
        System.out.println( subject.getHoursClasses()[2] );
        System.out.println( subject.getNumberOfGroups()[0] );
        System.out.println( subject.getNumberOfGroups()[1] );
        System.out.println( subject.getTypeShift().toString() );

    }
    public static void testGetType() throws Exception {System.out.println( tc.getType().toString() );}
    public static void testGetShift() throws Exception {System.out.println( tc.getShift().toString() );}
    public static void testGetQuantityStudents() throws Exception {System.out.println( tc.getQuantityStudents() );}
    public static void testGetSubGroup() throws Exception {System.out.println(tc.getSubGroup()); }


    public static void testSetGroup() throws Exception{ tc.setGroup( sc.nextInt()); }
    public static void testSetIdentifier() throws Exception{ tc.setIdentifier( sc.next() ); }
    public static void testSetSubject() throws Exception{ tc.setSubject( readSubject() ); }
    public static void testSetShift() throws Exception{ tc.setShift( UtilsDomain.typeShift.valueOf(sc.next())); }
    public static void testSetQuantityStudents() throws Exception{ tc.setQuantityStudents(sc.nextInt()); }
    public static void testSetSubGroup() throws Exception{ tc.setSubGroup(sc.nextInt()); }


    public static void testToStr() throws Exception {
        Vector<String> myVector = tc.toStr();

        for (int ii = 0; ii < myVector.size(); ii++) {
            System.out.println( myVector.get(ii) );
        }
    }

    public static void testFromStr() throws Exception{
        Vector<String> myVector = new Vector<>(15);
        //ID
        myVector.add(sc.next());
        //Group
        myVector.add(sc.next());
        //subGroup
        myVector.add(sc.next());
        //Type of group 0.Theory, 1.Lab, 2.Problems
        myVector.add("LABORATORY");
        //Type of group 0.Morning, 1.Afternoon
        myVector.add(sc.next());
        //Quantity students
        myVector.add(sc.next());

        //Subject Name
        myVector.add(sc.next());
        //Num_students
        myVector.add(sc.next());
        //Level
        myVector.add(sc.next());
        //Theory_hours
        myVector.add(sc.next());
        //Laboratory_hours
        myVector.add(sc.next());
        //Problems_hours
        myVector.add(sc.next());
        //Number of groups
        myVector.add(sc.next());
        //Number of Subgroups
        myVector.add(sc.next());
        //Shift 0.Morning, 1.Afternoon, 2.Both
        myVector.add(sc.next());

        tc = ClassClass.fromStr(myVector);
    }
    public static void menu(){
        System.out.print("------------------------------------------ \n");
        System.out.print("------------------MENU-------------------- \n");
        System.out.print("------------------------------------------ \n");
        System.out.print("1 ->Test Constructor \n");
        System.out.print("2 ->Test Constructor from String Vector \n");
        System.out.print("3 ->Test GetGroup \n");
        System.out.print("4 ->Test GetIdentifier \n");
        System.out.print("5 ->Test GetSubject \n");
        System.out.print("6 ->Test GetType \n");
        System.out.print("7 ->Test GetShift \n");
        System.out.print("8 ->Test GetQuantityStudents \n");
        System.out.print("9 ->Test GetSubGroup \n");
        System.out.print("10->Test ToStr  \n");
        System.out.print("11->Test SetGroup  \n");
        System.out.print("12->Test SetIdentifier  \n");
        System.out.print("13->Test SetSubject  \n");
        System.out.print("14->Test SetShift  \n");
        System.out.print("15->Test SetQuantityStudents  \n");
        System.out.print("16->Test SetSubGroup  \n");
        System.out.print("17->Exit  \n");

    }
    public static void main (String [] args) throws Exception {

        interactive = true;

        try {
            sc = new Scanner(new FileReader("./data/drivers/in/LaboratoryClassFile.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        ps = new PrintStream(new BufferedOutputStream(new FileOutputStream(new File("./data/drivers/out/LaboratoryClassFile.out"),false)),true);
        System.setOut(ps);


        if (!interactive) menu();

        boolean eof = false;

        do{

            switch (sc.nextInt()) {
                case 1:
                    testConstructor();
                    break;
                case 2:
                    testConstructorFromStr();
                    break;
                case 3:
                    testGetGroup();
                    break;
                case 4:
                    testGetIdentifier();
                    break;
                case 5:
                    testGetSubject();
                    break;
                case 6:
                    testGetType();
                    break;
                case 7:
                    testGetShift();
                    break;
                case 8:
                    testGetQuantityStudents();
                    break;
                case 9:
                    testGetSubGroup();
                    break;
                case 10:
                    testToStr();
                    break;
                case 11:
                    testSetGroup();
                    break;
                case 12:
                    testSetIdentifier();
                    break;
                case 13:
                    testSetSubject();
                    break;
                case 14:
                    testSetShift();
                    break;
                case 15:
                    testSetQuantityStudents();
                    break;
                case 16:
                    testSetSubGroup();
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
