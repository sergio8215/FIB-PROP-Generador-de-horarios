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

        ArrayList<Subject> subjects = new ArrayList<>();
        int exit = 0;

        while ( exit != 1 ) {

            System.out.print("Subject Name: ");
            subjectName = i.readword();
            System.out.print("Num_students: ");
            num_students = i.readint();
            System.out.print("Level: ");
            level = i.readint();
            System.out.print("Theory_hours: ");
            hoursClasses[0] = i.readint();
            System.out.print("Laboratory_hours: ");
            hoursClasses[1] = i.readint();
            System.out.print("Problems_hours: ");
            hoursClasses[2] = i.readint();
            System.out.print("Number of groups: ");
            numberOfGroups[0] = i.readint();
            System.out.print("Number of Subgroups: ");
            numberOfGroups[1] = i.readint();
            System.out.print("Shift 0.Morning, 1.Afternoon, 2.Both ");
            shiftSubj = UtilsDomain.typeShift.values()[i.readint()];

            subjects.add(new Subject(subjectName, num_students, level, hoursClasses, numberOfGroups, shiftSubj));

            System.out.print("Press 1 to add another subject: ");
            exit = i.readint();
        }

    }
    testConstructorFromStr( Vector<Vector<String>> classS )
    boolean testExistsClass( String subjectName, int group )
    ClassClass testGetClass(String name, int group )
    void testAddClass( String key, ClassClass newClass )
    ArrayList<ClassClass> testUnset()
    void testClassSort(ArrayList<ClassClass> set)
    boolean testCompare(ClassClass s1, String op, ClassClass s2)
    Vector< Vector<String> > testToStr()

}
