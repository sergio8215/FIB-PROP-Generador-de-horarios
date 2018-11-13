package src.Domain.drivers;
import src.Domain.LabClassroomClass;

import java.util.Scanner;

public class LabClassroomDriver {

    public static void  main(String args[]) {
        Scanner sc = new Scanner(System.in);
        LabClassroomClass lab = new LabClassroomClass();
        write();
        do{

            int i = sc.nextInt();
            switch(i) {
                case 0:
                    System.out.println("BASIC CONSTRUCTOR");
                    System.out.println("Enter the name of the laboratory classroom:");
                    String name = sc.next();
                    //while (name.equals("")) name = sc.nextLine();
                    System.out.println("Enter the capacity of the laboratory classroom:");
                    int cap = sc.nextInt();
                    System.out.println("Enter if the laboratory classroom has(true) or doesn't have(false) a multimedia system?");
                    boolean multimedia = sc.nextBoolean();
                    System.out.println("Enter the number of computers of the laboratory classroom");
                    int nComp = sc.nextInt();

                    lab = new LabClassroomClass(name, cap, multimedia, nComp);

                    break;
                case 1:
                    System.out.println("\t1 -> CONSTRUCTOR FROM STRING");
                    break;
                case 2:
                    System.out.println("\t2 -> NAME GETTER");
                    System.out.println(lab.getName());
                    break;
                case 3:
                    System.out.println("\t1 -> NAME SETTER");
                    lab.setName(sc.next());
                    break;
                case 4:
                    System.out.println("\t2 -> NAME GETTER");
                    System.out.println(lab.getName());
                    break;
                default:
                    System.out.println("\t3 -> EXIT");

            }

            write();
        }while(sc.hasNextInt());

    }

    public static void write() {
        System.out.println("------------------------------------------");
        System.out.println("\nWrite the number of the function you want to test:");
        System.out.println("\t0 -> Basic constructor");
        System.out.println("\t1 -> Constructor from String");
    }

}
