package src.domain.drivers;

import src.domain.classes.*;
import src.domain.utils.UtilsDomain;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Vector;

public class ClassSetDriver {

    private static ClassSet cSet;

    private static Scanner sc = new Scanner(System.in);
    private static PrintStream ps;
    private static boolean interactive = false;

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

            subjectsArray.add(new Subject(subjectName, num_students, level, hoursClasses, numberOfGroups, shiftSubj));

            exit = sc.nextInt();
        }

        cSet = new ClassSet( new SubjectsSet(subjectsArray) );

    }

    public static void testConstructorFromStr( ) throws Exception{

        int size = sc.nextInt();
        Vector<Vector <String>> myMatrix = new Vector<>(size);

        int ii = 0;

        for (;ii<size; ii++){

            Vector<String> myVector = new Vector<>(15);

            //ID
            myVector.add(sc.next());
            //Group
            myVector.add(sc.next());
            //subGroup
            myVector.add(sc.next());
            //Type of group 0.Theory, 1.Lab, 2.Problems
            myVector.add(sc.next());
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

            myMatrix.add(ii, myVector);

        }
        cSet = new ClassSet(myMatrix);
    }


    public static void testExistsClass( ){ System.out.println(Boolean.valueOf(cSet.existsClass( sc.next(), sc.nextInt()))); }
    public static void testGetClass( ) {
        ClassClass myClass = cSet.getClass(sc.next(), sc.nextInt());
        System.out.println(myClass.toStr());

        /*System.out.print("ID: "+myClass.getIdentifier()+", ");
        System.out.print("Group: "+ myClass.getGroup()+", ");
        System.out.print("SubGroup: "+myClass.getSubGroup()+", ");
        System.out.print("Quantity Stud: "+myClass.getQuantityStudents()+", ");
        System.out.print("Type: "+myClass.getType().toString()+", ");
        System.out.print("Shift: "+myClass.getShift().toString()+", ");
        System.out.print("Subject name:"+myClass.getSubject().getName());*/

    }
    public static void testAddClass( ){

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

        //ID
        identifier = sc.next();
        //Group
        group = sc.nextInt();
        //subGroup
        subGroup = sc.nextInt();
        //Type of group 0.Theory, 1.Lab, 2.Problems
        typeG = UtilsDomain.ClassType.valueOf(sc.next());
        //Shift of group 0.Morning, 1.Afternoon
        shift = UtilsDomain.typeShift.valueOf(sc.next());
        //Quantity students
        quantityStudents = sc.nextInt();

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
    }

    public static void testUnset() throws Exception{
        ArrayList<ClassClass> classArray = cSet.unset();
        for (int ii = 0; ii<classArray.size(); ii++) {
            // Print the name of each subject
            System.out.print(classArray.get(ii).getSubject().getName()+ ", ");
        }
        System.out.println("\n");
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

        //ID
        identifier = sc.next();
        //Group
        group = sc.nextInt();
        //subGroup
        subGroup = sc.nextInt();
        //Type of group 0.Theory, 1.Lab, 2.Problems
        typeG = UtilsDomain.ClassType.valueOf(sc.next());
        //Shift of group 0.Morning, 1.Afternoon
        shift = UtilsDomain.typeShift.valueOf(sc.next());
        //Quantity students
        quantityStudents = sc.nextInt();

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

        //Operator
        String op = sc.next();

        //ID
        identifier = sc.next();
        //Group
        group = sc.nextInt();
        //subGroup
        subGroup = sc.nextInt();
        //Type of group 0.Theory, 1.Lab, 2.Problems
        typeG = UtilsDomain.ClassType.valueOf(sc.next());
        //Shift of group 0.Morning, 1.Afternoon
        shift = UtilsDomain.typeShift.valueOf(sc.next());
        //Quantity students
        quantityStudents = sc.nextInt();

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


        System.out.println( ClassSet.compare(s1, op, s2));

    }

    public static void testToStr() throws Exception{

        Vector< Vector<String> > myStringVector = cSet.toStr();
        for( Vector<String> c: myStringVector ){
            for (String r: c){
                System.out.print(r+", ");
            }
            System.out.println("\n");
        }
    }
    public static void menu(){
        System.out.print("------------------------------------------ \n");
        System.out.print("------------------MENU-------------------- \n");
        System.out.print("------------------------------------------ \n");
        System.out.print("1 ->Test ConstructorSubjects \n");
        System.out.print("2 ->Test ConstructorFromStr \n");
        System.out.print("3 ->Test ExistsClass \n");
        System.out.print("4 ->Test GetClass \n");
        System.out.print("5 ->Test AddClass \n");
        System.out.print("6 ->Test Unset \n");
        System.out.print("7 ->Test Compare \n");
        System.out.print("8 ->Test ToStr \n");
        System.out.print("9 ->Exit  \n");
    }
    public static void main (String [] args) throws Exception {

        interactive = true;

        try {
            sc = new Scanner(new FileReader("./data/drivers/in/ClassSetFile.in"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        ps = new PrintStream(new BufferedOutputStream(new FileOutputStream(new File("./data/drivers/out/ClassSetFile.out"),false)),true);
        System.setOut(ps);


        if (!interactive) menu();

        boolean eof = false;

        do{

            switch (sc.nextInt()) {
                case 1:
                    testConstructorSubjects();
                    break;
                case 2:
                    testConstructorFromStr();
                    break;
                case 3:
                    testExistsClass();
                    break;
                case 4:
                    testGetClass();
                    break;
                case 5:
                    testAddClass();
                    break;
                case 6:
                    testUnset();
                    break;
                case 7:
                    testCompare();
                    break;
                case 8:
                    testToStr();
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
            if (os.contains("Windows")) {String[] cls = new String[] {"cmd.exe", "/c", "cls"};
                Runtime.getRuntime().exec(cls); }
            else    Runtime.getRuntime().exec("clear");
        } catch (Exception e) {
            System.out.println(e);
        }

    }
}
