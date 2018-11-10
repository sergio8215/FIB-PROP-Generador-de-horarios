package src.Domain;

import java.util.Vector;

public class Subject {
    /**
     * Subject class represents a subject of a given Curriculum.
     * @author joaquimgomez
     */

    // Auxiliary structures

    public static enum typeShift {
        morningShift,
        afternoonShift,
        bothShifts
    }


    // Members

    private static String name;
    private static int totalWeekHours;
    private static int numberStudents;
    private static int level;
    private static int[] hoursClasses; // [0] -> Theory, [1] -> Laboratory, [2] -> Problems
    private static int[] numberOfGroups; // [0] -> Number of groups, [1] -> Number of subgroups  |||  SetDeclases genera los grupos correspondientes
    private static int maxCapacity;
    private static typeShift tyShift;


    // Constructors

    /**
     * Class constructor.
     */
    public Subject(){

    }

    /**
     * Class constructor specifying the member's values.
     * @param name Name of the subject.
     * @param level Level of the subject in the curriculum.
     * @param numberStudents Number of students that can be enrolled.
     * @param weekHours Total of hours required for this subject.
     * @param hoursClasses Distribution of class hours in a vector as follow: [Theory, Laboratory, Problems].
     * @param numberOfGroups Number of groups and subgroups.
     * @param maxCapacity The max capacity of the theory groups for the subject.
     * @param tyShift Type of shift.
     */
    public Subject(String name, int level, int numberStudents, int weekHours, int[] hoursClasses, int[] numberOfGroups, int maxCapacity, typeShift tyShift){
        this.name = name;
        this.level = level;
        this.totalWeekHours = weekHours;
        this.numberStudents = numberStudents;
        this.hoursClasses = hoursClasses;
        setHoursClasses(hoursClasses);
        setNumberOfGroups(numberOfGroups);
        this.maxCapacity = maxCapacity;
        this.tyShift = tyShift;
    }


    /**
     * Class constructor for a given vector with the members of the class.
     * @param vectorMembers Vector with the members of the class subject.
     */
    public Subject(Vector<String> vectorMembers) {
        name = vectorMembers.get(0);
        totalWeekHours = Integer.parseInt(vectorMembers.get(1));
        numberStudents = Integer.parseInt(vectorMembers.get(2));
        level = Integer.parseInt(vectorMembers.get(3));
        hoursClasses = new int[3];
        hoursClasses[0] = Integer.parseInt(vectorMembers.get(4));
        hoursClasses[1] = Integer.parseInt(vectorMembers.get(5));
        hoursClasses[2] = Integer.parseInt(vectorMembers.get(6));
        numberOfGroups = new int[2];
        numberOfGroups[0] = Integer.parseInt(vectorMembers.get(7));
        numberOfGroups[1] = Integer.parseInt(vectorMembers.get(8));
        maxCapacity = Integer.parseInt(vectorMembers.get(9));
        tyShift = typeShift.values()[Integer.parseInt(vectorMembers.get(10))];
    }

    // Methods

    /**
     * Set the name of the subject.
     * @param name Name of the subject.
     */
    public void setName(String name){
        this.name = name;
    }

    /**
     * It returns the name of the subject.
     * @return Name of the subject.
     */
    public String getName(){
        return name;
    }

    /**
     * Set the total of hours required for a given subject.
     * @param weekHours Total of hours required for this subject.
     */
    public void setWeekHours(int weekHours) {
        this.totalWeekHours = weekHours;
    }

    /**
     * It returns the total subject's hours.
     * @return Total of hours required.
     */
    public float getWeekHours() {
        return totalWeekHours;
    }

    /**
     * Set the number of students that can be enrolled.
     * @param numberStudents Number of students that can be enrolled.
     */
    public void setNumberStudents(int numberStudents) {
        this.numberStudents = numberStudents;
    }

    /**
     * It returns the number of students that can be enrolled.
     * @return Number of students that can be enrolled.
     */
    public int getNumberStudents() {
        return numberStudents;
    }

    /**
     * Set the level of the subject in the curriculum.
     * @param level Level of the subject in the curriculum.
     */
    public void setLevel(int level) {
        this.level = level;
    }

    /**
     * It returns the level of the subject.
     * @return Level of the subject in the curriculum.
     */
    public int getLevel() {
        return level;
    }

    /**
     * Set the distribution of class hours.
     * @param hoursClasses Distribution of class hours in a vector as follow: [Theory, Laboratory, Problems].
     * @return True if the summation of the hours distribution is equal to the total of hours required for this subject.
     */
    public boolean setHoursClasses(int[] hoursClasses) {
        if (hoursClasses[0] + hoursClasses[1] + hoursClasses[2] == totalWeekHours){
            this.hoursClasses = hoursClasses;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Set the distribution of class hours.
     * @param theoryHours Hours of theory.
     * @param laboratoryHours Hours of Laboratory.
     * @param problemsHours Hours of problems.
     * @return True if the summation of the hours distribution is equal to the total of hours required for this subject.
     */
    public boolean setHoursClasses(int theoryHours, int laboratoryHours, int problemsHours){
        if (theoryHours + laboratoryHours + problemsHours == totalWeekHours){
            hoursClasses[0] = theoryHours; hoursClasses[1] = laboratoryHours; hoursClasses[2] = problemsHours;
            return true;
        } else {
            return false;
        }
    }

    /**
     * It returns the distribution of hours.
     * @return Vector with the distribution of hours in Theory, Laboratory and Problems.
     */
    public int[] getHoursClasses() {
        return hoursClasses;
    }

    /**
     * Set the number of groups and subgroups.
     * @param numberOfGroups Vector with the number of groups and subgroups.
     */
    public void setNumberOfGroups(int[] numberOfGroups) {
        this.numberOfGroups = numberOfGroups;
    }

    /**
     * Set the number of groups and subgroups.
     * @param groups Number of groups.
     * @param subgroups Number of subgroups.
     */
    public void setNumberOfGroups(int groups, int subgroups){
        numberOfGroups[0] = groups; numberOfGroups[1] = subgroups;
    }

    /**
     * It returns a vector with the number of groups and subgroups.
     * @return Vector with the number of groups and subgroups.
     */
    public int[] getNumberOfGroups() {
        return numberOfGroups;
    }

    /**
     * Set the type of shift.
     * @param tyShift Type of shift.
     */
    public void setTypeShift(typeShift tyShift) {
        Subject.tyShift = tyShift;
    }

    /**
     * It returns the type of shift.
     * @return Type of the shift.
     */
    public typeShift getTypeShift() {
        return tyShift;
    }

    /**
     * It returns the max capacity of the subject.
     * @return Max capacity of the subject.
     */
    public static int getMaxCapacity() {
        return maxCapacity;
    }

    /**
     * Set the max capacity of the subject.
     * @param maxCapacity Max capacity of the subject.
     */
    public static void setMaxCapacity(int maxCapacity) {
        Subject.maxCapacity = maxCapacity;
    }

    /**
     * It returns a vector of strings with the members' values.
     * @return Vector of strings with the members' values.
     */
    public Vector<String> toString() {
        Vector<String> c = new Vector<>();

        c.add(name);
        c.add(Integer.toString(totalWeekHours));
        c.add(Integer.toString(numberStudents));
        c.add(Integer.toString(level));
        c.add(Integer.toString(hoursClasses[0]));
        c.add(Integer.toString(hoursClasses[1]));
        c.add(Integer.toString(hoursClasses[2]));
        c.add(Integer.toString(numberOfGroups[0]));
        c.add(Integer.toString(numberOfGroups[1]));
        c.add(Integer.toString(maxCapacity));
        c.add(Integer.toString(tyShift.ordinal()));

        return c;
    }
}
