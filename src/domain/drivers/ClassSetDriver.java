package src.domain.drivers;

import src.domain.classes.ClassClass;
import src.domain.classes.ClassSet;
import src.domain.classes.Subject;
import src.domain.classes.SubjectsSet;
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


    boolean testExistsClass( String subjectName, int group )
    ClassClass testGetClass(String name, int group )
    void testAddClass( String key, ClassClass newClass )
    ArrayList<ClassClass> testUnset()
    void testClassSort(ArrayList<ClassClass> set)
    boolean testCompare(ClassClass s1, String op, ClassClass s2)
    Vector< Vector<String> > testToStr()

}
