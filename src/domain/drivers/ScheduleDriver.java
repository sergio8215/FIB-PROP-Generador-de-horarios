package src.domain.drivers;

import src.domain.classes.*;
import src.domain.utils.UtilsDomain;

import java.io.*;
import java.util.*;

public class ScheduleDriver {
    private static Schedule schedule;
    private static Scanner sc;
    private static PrintStream ps;
    private static boolean fromFile = false;

    private static Subject enterSubject() {
        if (!fromFile)   System.out.print("Data subject: ");
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
    private static ClassClass enterClass(){
        if (!fromFile)   System.out.print("Data class: ");
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

        if (!fromFile)   System.out.print("Data subject: ");
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

        return ClassClass.fromStr(myVector);
    }
    private static Classroom enterClassroom(){
        if (!fromFile)   System.out.print("Data classroom: ");
        Vector<String> v = new Vector<>(5);

        v.add(0, sc.next());
        v.add(1, sc.next());
        v.add(2, sc.next());
        v.add(3, sc.next());
        v.add(v.get(2).equals("LABORATORY")?sc.next():"0");

        return Classroom.fromStr(v);
    }
    private static Session enterSession() {
        if (!fromFile)   System.out.print("Data session: ");

        Vector<String> v = new Vector<>(2);

        v.add(0, sc.next());
        v.add(1, sc.next());

        return new Session(v);
    }
    private static MUS enterMUS(){
        return new MUS(enterClass(), enterClassroom(), enterSession());
    }

    public static void testSimpleConstructor(){
        schedule = new Schedule(sc.next(), sc.next());
    }
    public static void testBasicConstructor(){
        String cf = sc.next();
        String sf = sc.next();

        HashMap<String, ArrayList<MUS>> timetable = new HashMap<>();

        int quant = sc.nextInt();
        if(!fromFile) System.out.println("Enter MUSes from different Subjects: ");
        for(int i = 0; i < quant; ++i) {
            ArrayList<MUS> arr = new ArrayList<>();
            MUS m = enterMUS();
            arr.add(m);
            timetable.put(m.getSubject().getName(), arr);
        }

        schedule = new Schedule(cf, sf, timetable);
    }
    public static void testConstructorByCopy() {
        String cf = sc.next();
        String sf = sc.next();

        HashMap<String, ArrayList<MUS>> timetable = new HashMap<>();

        int quant = sc.nextInt();
        if (!fromFile) System.out.println("Enter MUSes from different Subjects: ");
        for (int i = 0; i < quant; ++i) {
            ArrayList<MUS> arr = new ArrayList<>();
            arr.add(enterMUS());
        }

        Schedule sch = new Schedule(cf, sf);
        schedule = new Schedule(sch);
    }
    public static void testGetClassroomFile(){
        System.out.println(schedule.getClassroomFile());
    }
    public static void testSetClassroomFile(){
        schedule.setClassroomFile(sc.next());
    }
    public static void testGetSubjectFile(){
        System.out.println(schedule.getSubjectFile());
    }
    public static void testSetSubjectFile(){
        schedule.setSubjectFile(sc.next());
    }
    public static void testIsFail(){
        System.out.println(schedule.isFail()?"fail":"not fail");
    }
    public static void testSetFail(){
        schedule.setFail(sc.nextBoolean());
    }
    public static void testFail(){
        schedule.fail();
    }
    public static void testGetTimetable(){
        HashMap<String, ArrayList<MUS> > timetable = schedule.getTimetable();

        Set<String> keys = timetable.keySet();
        for (String nameSubject : keys) {
            ArrayList<MUS> elems = timetable.get(nameSubject);

            for (MUS m : elems) {
                System.out.println(m.getClassClass().getIdentifier() + ": " + m.getClassroom().getName() + " - " + m.getSession().getDay() + " " + m.getSession().getHour());
            }

            System.out.print("\n");
        }
    }
    public static void testSetTimetable(){
        HashMap<String, ArrayList<MUS>> timetable = new HashMap<>();

        int quant = sc.nextInt();
        if(!fromFile) System.out.println("Enter MUSes from different Subjects: ");
        for(int i = 0; i < quant; ++i) {
            ArrayList<MUS> arr = new ArrayList<>();
            MUS m = enterMUS();
            arr.add(m);
            timetable.put(m.getSubject().getName(), arr);
        }
        schedule.setTimetable(timetable);
    }
    public static void testIsEmpty(){
        System.out.println(schedule.isEmpty()?"empty":"not empty");
    }
    public static void testSize(){
        System.out.println(schedule.size());
    }
    public static void testAdd(){
        schedule.add(enterMUS());
    }
    public static void testDelete(){
        schedule.delete(enterMUS());
    }
    public static void testUnset(){
        ArrayList<MUS> arr = schedule.unset();
        for (MUS m : arr) {
            System.out.println(m.getClassClass().getIdentifier()
                    + ": " + m.getClassroom().getName()
                    + " - " + m.getSession().getDay()
                    + " " + m.getSession().getHour());
        }
    }
    public static void testValid(){
        System.out.println(schedule.valid()?"valid":"not valid");
    }


    public static void  main(String args[]) throws FileNotFoundException {
        final PrintStream oldStdout = System.out;
         schedule = new Schedule();
        if(args.length > 0) {
            fromFile = true;

            try{
                sc = new Scanner(new FileReader("./data/drivers/in/" + args[0]));
            }catch(FileNotFoundException e) {
                e.printStackTrace();
            }

            ps = new PrintStream(new BufferedOutputStream(new FileOutputStream(new File("./data/drivers/out/" + args[1]),true)),true);
            System.setOut(ps);
        }
        else {
            sc = new Scanner(System.in);
        }
        if(!fromFile) write();
        boolean eof = false;
        do{

            int i = sc.nextInt();
            switch(i) {
                case 0:
                    testSimpleConstructor();
                    break;
                case 1:
                    testBasicConstructor();
                    break;
                case 2:
                    testConstructorByCopy();
                    break;
                case 3:
                    testGetClassroomFile();
                    break;
                case 4:
                    testSetClassroomFile();
                    break;
                case 5:
                    testGetSubjectFile();
                    break;
                case 6:
                    testSetSubjectFile();
                    break;
                case 7:
                    testIsFail();
                    break;
                case 8:
                    testSetFail();
                    break;
                case 9:
                    testFail();
                    break;
                case 10:
                    testGetTimetable();
                    break;
                case 11:
                    testSetTimetable();
                    break;
                case 12:
                    testIsEmpty();
                    break;
                case 13:
                    testSize();
                    break;
                case 14:
                    testAdd();
                    break;
                case 15:
                    testDelete();
                    break;
                case 16:
                    testUnset();
                    break;
                case 17:
                    testValid();
                    break;
                case 99:
                    eof = true;
                default:
                    System.out.println("\tInput Error");
            }
            if(!fromFile) write();
        }while(!eof && sc.hasNextInt());

        if(fromFile) {
            System.setOut(oldStdout);
            ps.close();
        }
    }

    public static void write() {
        System.out.println("------------------------------------------");
        System.out.println("\nWrite the number of the function you want to test:");
        System.out.println("\t0 -> Basic constructor");
        System.out.println("\t1 -> Constructor by copy");
        System.out.println("\t2 -> ClassroomSessionSet getter");
        System.out.println("\t3 -> ClassroomSessionSet setter");
        System.out.println("\t4 -> Get a Pair");
        System.out.println("\t5 -> Size of classroomSet");
        System.out.println("\t6 -> Delete a Pair");
    }
}
