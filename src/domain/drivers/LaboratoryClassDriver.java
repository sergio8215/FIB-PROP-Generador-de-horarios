package src.domain.drivers;

import src.domain.classes.ClassClass;
import src.domain.classes.LaboratoryClass;
import src.domain.classes.Subject;
import src.domain.utils.UtilsDomain;
import src.domain.utils.inout;

import java.util.Vector;

public class LaboratoryClassDriver {

    public static ClassClass tc;
    public static inout i = new inout();

    private static void testConstructor() throws Exception {
        String identifier;
        int group;
        int subGroup;
        UtilsDomain.ClassType typeG;
        UtilsDomain.typeShift shift;
        int quantityStudents;


        i.write("ID: ");
        identifier = i.readword();
        i.write("Group: ");
        group = i.readint();
        i.write("subGroup: ");
        subGroup = i.readint();
        //i.write("Type of group 0.Theory, 1.Lab, 2.Problems: ");
        //typeG = UtilsDomain.ClassType.values()[0];
        i.write("Shift of group 0.Morning, 1.Afternoon: ");
        shift = UtilsDomain.typeShift.values()[i.readint()];
        i.write("Quantity students: ");
        quantityStudents = i.readint();

        tc = new LaboratoryClass(identifier, readSubject(), group, quantityStudents, shift, subGroup);
        i.write("-", 20);
        i.writeln("Theory Class successfully created");
    }

    public static void testConstructorFromStr () throws Exception {
        Vector<String> myVector = new Vector<>();

        i.write("ID: ");
        myVector.add(i.readword());
        i.write("Group: ");
        myVector.add(i.readword());
        i.write("subGroup: ");
        myVector.add(i.readword());
        //i.write("Type of group 0.Theory, 1.Lab, 2.Problems: ");
        myVector.add("LABORATORY");
        i.write("Shift of group 0.Morning, 1.Afternoon: ");
        myVector.add(i.readword());
        i.write("Quantity students: ");
        myVector.add(i.readword());

        i.write("Subject Name: ");
        myVector.add(i.readword());
        i.write("Num_students: ");
        myVector.add(i.readword());
        i.write("Level: ");
        myVector.add(i.readword());
        i.write("Theory_hours: ");
        myVector.add(i.readword());
        i.write("Laboratory_hours: ");
        myVector.add(i.readword());
        i.write("Problems_hours: ");
        myVector.add(i.readword());
        i.write("Number of groups: ");
        myVector.add(i.readword());
        i.write("Number of Subgroups: ");
        myVector.add(i.readword());
        i.write("Shift 0.Morning, 1.Afternoon, 2.Both ");
        myVector.add(i.readword());

        tc = new LaboratoryClass(myVector);
        i.write("-", 20);
        i.writeln("Theory Class successfully created");
    }

    public static Subject readSubject() throws Exception{

        String subjectName;
        int num_students;
        int level;
        int[] hoursClasses = new int[3];
        int[] numberOfGroups = new int[2];
        UtilsDomain.typeShift shiftSubj;

        i.write("Subject Name: ");
        subjectName = i.readword();
        i.write("Num_students: ");
        num_students = i.readint();
        i.write("Level: ");
        level = i.readint();
        i.write("Theory_hours: ");
        hoursClasses[0] = i.readint();
        i.write("Laboratory_hours: ");
        hoursClasses[1] = i.readint();
        i.write("Problems_hours: ");
        hoursClasses[2] = i.readint();
        i.write("Number of groups: ");
        numberOfGroups[0] = i.readint();
        i.write("Number of Subgroups: ");
        numberOfGroups[1] = i.readint();
        i.write("Shift 0.Morning, 1.Afternoon, 2.Both ");
        shiftSubj = UtilsDomain.typeShift.values()[i.readint()];

        Subject subject = new Subject(subjectName, num_students, level, hoursClasses, numberOfGroups, shiftSubj);
        return subject;

    }

    public static void testGetGroup() throws Exception {i.writeln( tc.getGroup() );}
    public static void testGetIdentifier() throws Exception {i.writeln( tc.getIdentifier() );}
    public static void testGetSubject() throws Exception {
        Subject subject = tc.getSubject();
        i.writeln( subject.getName() );
        i.writeln( subject.getLevel() );
        i.writeln( subject.getNumberStudents() );
        i.writeln( subject.getTypeShift().toString() );

    }
    public static void testGetType() throws Exception {i.writeln( tc.getType().toString() );}
    public static void testGetShift() throws Exception {i.writeln( tc.getShift().toString() );}
    public static void testGetQuantityStudents() throws Exception {i.writeln( tc.getQuantityStudents() );}
    public static void testGetSubGroup() throws Exception {i.writeln(tc.getSubGroup()); }


    public static void testSetGroup() throws Exception{ tc.setGroup( i.readint()); }
    public static void testSetIdentifier() throws Exception{ tc.setIdentifier( i.readword() ); }
    public static void testSetSubject() throws Exception{ tc.setSubject( readSubject() ); }
    public static void testSetShift() throws Exception{ tc.setShift( UtilsDomain.typeShift.values()[i.readint()]); }
    public static void testSetQuantityStudents() throws Exception{ tc.setQuantityStudents(i.readint()); }
    public static void testSetSubGroup() throws Exception{ tc.setSubGroup(i.readint()); }


    public static void testToStr() throws Exception {
        Vector<String> myVector = tc.toStr();

        for (int ii = 0; ii < myVector.size(); ii++) {
            i.writeln( myVector.get(ii) );
        }
    }

    public static void testFromStr() throws Exception{
        Vector<String> myVector = new Vector<>();
        i.write("ID: ");
        myVector.add(i.readword());
        i.write("Group: ");
        myVector.add(i.readword());
        i.write("subGroup: ");
        myVector.add(i.readword());
        //i.write("Type of group 0.Theory, 1.Lab, 2.Problems: ");
        myVector.add("LABORATORY");
        i.write("Type of group 0.Morning, 1.Afternoon: ");
        myVector.add(i.readword());
        i.write("Quantity students: ");
        myVector.add(i.readword());

        i.write("Subject Name: ");
        myVector.add(i.readword());
        i.write("Num_students: ");
        myVector.add(i.readword());
        i.write("Level: ");
        myVector.add(i.readword());
        i.write("Theory_hours: ");
        myVector.add(i.readword());
        i.write("Laboratory_hours: ");
        myVector.add(i.readword());
        i.write("Problems_hours: ");
        myVector.add(i.readword());
        i.write("Number of groups: ");
        myVector.add(i.readword());
        i.write("Number of Subgroups: ");
        myVector.add(i.readword());
        i.write("Shift 0.Morning, 1.Afternoon, 2.Both ");
        myVector.add(i.readword());

        tc = ClassClass.fromStr(myVector);
    }

    public static void main (String [] args) throws Exception {

        int option = 0;

        while( option != 17) {

            i.write("------------------------------------------ \n");
            i.write("------------------MENU-------------------- \n");
            i.write("------------------------------------------ \n");
            i.write("1 ->Test Constructor: \n");
            i.write("2 ->Test Constructor from String Vector: \n");
            i.write("3 ->Test GetGroup: \n");
            i.write("4 ->Test GetIdentifier: \n");
            i.write("5 ->Test GetSubject: \n");
            i.write("6 ->Test GetType: \n");
            i.write("7 ->Test GetShift: \n");
            i.write("8 ->Test GetQuantityStudents: \n");
            i.write("9 ->Test GetSubGroup: \n");
            i.write("10->Test ToStr:  \n");
            i.write("11->Test SetGroup:  \n");
            i.write("12->Test SetIdentifier:  \n");
            i.write("13->Test SetSubject:  \n");
            i.write("14->Test SetShift:  \n");
            i.write("15->Test SetQuantityStudents:  \n");
            i.write("16->Test SetSubGroup:  \n");
            i.write("17->Exit:  \n");

            option = i.readint();


            switch (option) {
                case 1:
                    testConstructor();
                    System.in.read();
                    break;
                case 2:
                    testConstructorFromStr();
                    System.in.read();
                    break;
                case 3:
                    testGetGroup();
                    System.in.read();
                    break;
                case 4:
                    testGetIdentifier();
                    System.in.read();
                    break;
                case 5:
                    testGetSubject();
                    System.in.read();
                    break;
                case 6:
                    testGetType();
                    System.in.read();
                    break;
                case 7:
                    testGetShift();
                    System.in.read();
                    break;
                case 8:
                    testGetQuantityStudents();
                    System.in.read();
                    break;
                case 9:
                    testGetSubGroup();
                    System.in.read();
                    break;
                case 10:
                    testToStr();
                    System.in.read();
                    break;
                case 11:
                    testSetGroup();
                    System.in.read();
                    break;
                case 12:
                    testSetIdentifier();
                    System.in.read();
                    break;
                case 13:
                    testSetSubject();
                    System.in.read();
                    break;
                case 14:
                    testSetShift();
                    System.in.read();
                    break;
                case 15:
                    testSetQuantityStudents();
                    System.in.read();
                    break;
                case 16:
                    testSetSubGroup();
                    System.in.read();
                    break;
            }
        }
    }
}
