package src.domain.drivers;

import java.util.Scanner;

public class SubjectDriver {

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);

        menu()

        do {
            switch (sc.nextInt()) {
                case 0:
                    break;
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    break;
                case 8:
                    break;
                case 9:
                    break;
                default:
                    System.out.println("Input error!\n");
            }

        } while (sc.hasNextInt());
    }

    private static void menu() {
        System.out.println("------------------------------------------");
        System.out.println("\nWrite the number of the function you want to test:");
        System.out.println("\t0 -> Basic constructor");
        System.out.println("\t1 -> Constructor from String");
        System.out.println("\t2 -> Name getter");
        System.out.println("\t3 -> Capacity getter");
        System.out.println("\t4 -> Capacity getter");
        System.out.println("\t5 -> Type getter");
        System.out.println("\t5 -> Multimedia getter");
        System.out.println("\t6 -> NumComputers getter");
        System.out.println("\t7 -> Convert Object to String");
    }


    public void testBasicConstructor() {

    }

    public void testConstructorFromString() {

    }

    public void testSetName() {

    }

    public void testGetName() {

    }

    public void testSetNumberStudents() {

    }

    public void testGetNumberStudents() {

    }

    public void testSetLevel() {

    }

    public void testGetLevel() {

    }

    public void testSetHoursClasses() {

    }

    public void testGetHoursClasses() {

    }



    

    public void testSetNumberOfGroups1() {

    }

    public void testSetNumberOfGroups2() {

    }

    public void testGetNumberOfGroups() {

    }

    public void testSetTypeShift() {

    }

    public void testGetTypeShift() {

    }

    public void testGetMaxCapacity() {

    }

    public void testSetMaxCapacity() {

    }

    public void testGetTheoryHours() {

    }

    public void testGetLaboratoryHours() {

    }

    public void testGetProblemsHours() {

    }

    public void testSetTheoryHours() {

    }

    public void testSetLaboratoryHours() {

    }

    public void testSetProblemsHours() {

    }

    public void testToStr() {

    }

}
