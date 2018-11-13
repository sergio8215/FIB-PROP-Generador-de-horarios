package src.Domain;

import sun.plugin.com.Utils;

import java.util.Scanner;
import java.util.Vector;

public class DriverClassClass {

    private static void TestContructorClassClass (String identifier, Subject subject, int group, ClassClass.ClassType type)
    {
    }

    TestContructorClassClass2 (Vector<String> myStringVector, ClassClass.ClassType type );
    TestfromStr( Vector<String> c );

    public abstract Vector<String> toStr();

    public static void main (String [] args){
        Scanner s = new Scanner(System.in);

        int continuar = 1;

        String identifier;
        int group;
        int subGroup;
        UtilsDomain.ClassType type;
        UtilsDomain.TimeZone shift;
        int quantityStudents;

        String subjectName;
        int num_students;
        int level;
        int[] hoursClasses = new int[3];
        int[] numberOfGroups;
        Subject.typeShift shift;

        String input;

        while( continuar != 5) {

            System.out.print("------------------------------------------ \n");
            System.out.print("------------------MENU-------------------- \n");
            System.out.print("------------------------------------------ \n");
            System.out.print("Create a new Class press 1: \n");
            System.out.print("Test method 2: \n");
            System.out.print("Test method 3: \n");
            System.out.print("Test method 4: \n");
            System.out.print("finish 5: \n");
            continuar = Integer.parseInt(s.nextLine());


            switch( continuar) {
                case 1:
                    System.out.print("ID: ");
                    identifier = s.nextLine();
                    System.out.print("subGroup: ");
                    subGroup = Integer.parseInt(s.nextLine());
                    System.out.print("Subject Name: ");
                    subjectName = s.nextLine();
                    System.out.print("Num_students: ");
                    num_students = Integer.parseInt(s.nextLine());
                    System.out.print("Level: ");
                    level = Integer.parseInt(s.nextLine());
                    System.out.print("Max_capacity: ");
                    max_capacity = Integer.parseInt(s.nextLine());
                    System.out.print("Theory_hours: ");
                    hoursClasses[0] = Integer.parseInt(s.nextLine());
                    System.out.print("Laboratory_hours: ");
                    hoursClasses[1] = Integer.parseInt(s.nextLine());
                    System.out.print("Problems_hours: ");
                    hoursClasses[2] = Integer.parseInt(s.nextLine());
                    System.out.print("Shift: ");
                    shift = s.nextLine();
                    System.out.print("Class Type: ");
                    type = Integer.parseInt(s.nextLine());
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
            }
        }



            Subjet subject = new Subject(subjectName,num_students,level,max_capacity,hoursClasses,shift);
        }




        switch (Integer.parseInt(input)) {

        StubClassClass testSubClass = new StubClassClass(identifier, subGroup, subject, group);
    }
}
