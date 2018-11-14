package src.domain.drivers;

import src.domain.classes.*;
import src.domain.utils.UtilsDomain;
import src.domain.utils.inout;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Vector;

public class ClassSetDriver {

    private static ClassSet cSet;
    private static inout i = new inout();

    public static void testConstructorSubjects( ) throws Exception{
        String subjectName;
        int num_students;
        int level;
        int[] hoursClasses = new int[3];
        int[] numberOfGroups = new int[2];
        UtilsDomain.typeShift shiftSubj;

        ArrayList<Subject> subjectsArray = new ArrayList<>();
        int exit = 0;

        while ( exit != 1 ) {

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

            subjectsArray.add(new Subject(subjectName, num_students, level, hoursClasses, numberOfGroups, shiftSubj));

            i.write("Press 1 to add another subject: ");
            exit = i.readint();
        }

        cSet = new ClassSet( new SubjectsSet(subjectsArray) );
        i.write("Set of Classes created successfully");
        System.in.read();
    }

    public static void testConstructorFromStr( ) throws Exception{
        Vector<Vector <String>> myMatrix = new Vector<>();
        int exit = 1;
        int ii = 0;

        while ( exit != 0 ){
            Vector<String> myVector = new Vector<>();
            myMatrix.add(new Vector<String>());

            i.write("ID: ");
            myVector.add(i.readword());
            i.write("Group: ");
            myVector.add(i.readword());
            i.write("subGroup: ");
            myVector.add(i.readword());
            i.write("Type of group 0.Theory, 1.Lab, 2.Problems: ");
            myVector.add(i.readword());
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

            myMatrix.add(ii, myVector);
            i.write("Press 1 to read add another class: ");
            exit = i.readint();
        }
        cSet = new ClassSet(myMatrix);
        i.write("Set of Classes created successfully");
        System.in.read();
    }


    public static void testExistsClass( ) throws Exception{ i.write(cSet.existsClass( i.readword(), i.readint())); }
    public static void testGetClass( ) throws Exception{
        ClassClass myClass = cSet.getClass(i.readword(), i.readint());
        i.write("ID: "+myClass.getIdentifier()+", ");
        i.write("Group: "+ myClass.getGroup()+", ");
        i.write("SubGroup: "+myClass.getSubGroup()+", ");
        i.write("Quantity Stud: "+myClass.getQuantityStudents()+", ");
        i.write("Type: "+myClass.getType().toString()+", ");
        i.write("Shift: "+myClass.getShift().toString()+", ");
        i.write("Subject name:"+myClass.getSubject().getName());

    }
    public static void testAddClass( ) throws Exception{

        String identifier;
        int group;
        int subGroup;
        UtilsDomain.ClassType typeG;
        UtilsDomain.typeShift shift;
        int quantityStudents;

        String subjectName;
        int num_students;
        int level;
        int[] hoursClasses = new int[3];
        int[] numberOfGroups = new int[2];
        UtilsDomain.typeShift shiftSubj;

        i.write("ID: ");
        identifier = i.readword();
        i.write("Group: ");
        group = i.readint();
        i.write("subGroup: ");
        subGroup = i.readint();
        i.write("Type of group 0.Theory, 1.Lab, 2.Problems: ");
        typeG = UtilsDomain.ClassType.values()[i.readint()];
        i.write("Shift of group 0.Morning, 1.Afternoon: ");
        shift = UtilsDomain.typeShift.values()[i.readint()];
        i.write("Quantity students: ");
        quantityStudents = i.readint();

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

        ClassClass auxClass;

        // Ask for the type of class and then we create the class
        switch ( typeG.ordinal() ) {
            case 0:
                auxClass = new TheoryClass(identifier, subject, group, quantityStudents, shift, subGroup);
                break;
            case 1:
                auxClass = new LaboratoryClass(identifier, subject, group, quantityStudents, shift, subGroup);
                break;
            case 2:
                auxClass = new ProblemsClass(identifier, subject, group, quantityStudents, shift, subGroup);
                break;
            default:
                auxClass = null; // If we cant' find the type of c
                break;
        }

        cSet.addClass( identifier, auxClass );

        i.write("Class added to the SetClass. New set size = "+ cSet.size());
    }

    public static void testUnset() throws Exception{
        ArrayList<ClassClass> classArray = cSet.unset();
        for (int ii = 0; ii<classArray.size(); ii++) {
            // Print the name of each subject
            i.write(classArray.get(ii).getSubject().getName()+ ", ");
        }
    }

    public static void testCompare() throws Exception{

        String identifier;
        int group;
        int subGroup;
        UtilsDomain.ClassType typeG;
        UtilsDomain.typeShift shift;
        int quantityStudents;

        String subjectName;
        int num_students;
        int level;
        int[] hoursClasses = new int[3];
        int[] numberOfGroups = new int[2];
        UtilsDomain.typeShift shiftSubj;

        i.write("ID: ");
        identifier = i.readword();
        i.write("Group: ");
        group = i.readint();
        i.write("subGroup: ");
        subGroup = i.readint();
        i.write("Type of group 0.Theory, 1.Lab, 2.Problems: ");
        typeG = UtilsDomain.ClassType.values()[i.readint()];
        i.write("Shift of group 0.Morning, 1.Afternoon: ");
        shift = UtilsDomain.typeShift.values()[i.readint()];
        i.write("Quantity students: ");
        quantityStudents = i.readint();

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

        ClassClass s1;

        // Ask for the type of class and then we create the class
        switch ( typeG.ordinal() ) {
            case 0:
                s1 = new TheoryClass(identifier, subject, group, quantityStudents, shift, subGroup);
                break;
            case 1:
                s1 = new LaboratoryClass(identifier, subject, group, quantityStudents, shift, subGroup);
                break;
            case 2:
                s1 = new ProblemsClass(identifier, subject, group, quantityStudents, shift, subGroup);
                break;
            default:
                s1 = null; // If we cant' find the type of c
                break;
        }

        i.write("Operator: ");
        String op = i.readword();

        i.write("ID: ");
        identifier = i.readword();
        i.write("Group: ");
        group = i.readint();
        i.write("subGroup: ");
        subGroup = i.readint();
        i.write("Type of group 0.Theory, 1.Lab, 2.Problems: ");
        typeG = UtilsDomain.ClassType.values()[i.readint()];
        i.write("Shift of group 0.Morning, 1.Afternoon: ");
        shift = UtilsDomain.typeShift.values()[i.readint()];
        i.write("Quantity students: ");
        quantityStudents = i.readint();

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

        Subject subject2 = new Subject(subjectName, num_students, level, hoursClasses, numberOfGroups, shiftSubj);

        ClassClass s2;

        // Ask for the type of class and then we create the class
        switch ( typeG.ordinal() ) {
            case 0:
                s2 = new TheoryClass(identifier, subject2, group, quantityStudents, shift, subGroup);
                break;
            case 1:
                s2 = new LaboratoryClass(identifier, subject2, group, quantityStudents, shift, subGroup);
                break;
            case 2:
                s2 = new ProblemsClass(identifier, subject2, group, quantityStudents, shift, subGroup);
                break;
            default:
                s2 = null; // If we cant' find the type of c
                break;
        }


        i.write( ClassSet.compare(s1, op, s2));

    }

    public static void testToStr() throws Exception{

        Vector< Vector<String> > myStringVector = cSet.toStr();
        for( Vector<String> c: myStringVector ){
            for (String r: c){
                i.write(r+", ");
            }
            i.writeln("\n");
        }
    }

    public static void main (String [] args) throws Exception {

        int option = 0;

        while( option != 9) {

            i.write("------------------------------------------ \n");
            i.write("------------------MENU-------------------- \n");
            i.write("------------------------------------------ \n");
            i.write("1 ->Test ConstructorSubjects \n");
            i.write("2 ->Test ConstructorFromStr \n");
            i.write("3 ->Test ExistsClass \n");
            i.write("4 ->Test GetClass \n");
            i.write("5 ->Test AddClass \n");
            i.write("6 ->Test Unset \n");
            i.write("7 ->Test Compare \n");
            i.write("8 ->Test ToStr \n");
            i.write("9 ->Exit  \n");

            option = i.readint();


            switch (option) {
                case 1:
                    testConstructorSubjects();
                    System.in.read();
                    break;
                case 2:
                    testConstructorFromStr();
                    System.in.read();
                    break;
                case 3:
                    testExistsClass();
                    System.in.read();
                    break;
                case 4:
                    testGetClass();
                    System.in.read();
                    break;
                case 5:
                    testAddClass();
                    System.in.read();
                    break;
                case 6:
                    testUnset();
                    System.in.read();
                    break;
                case 7:
                    testCompare();
                    System.in.read();
                    break;
                case 8:
                    testToStr();
                    System.in.read();
                    break;
            }
        }
    }
}
