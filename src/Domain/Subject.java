package src.Domain;

import java.util.Vector;

public class Subject {

    private static String name;
    private static int numberStudents;
    private static int level;

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

    /**
     * It returns the level of the subject.
     * @return Level of the subject in the curriculum.
     */
    public int getLevel() {
        return level;
    }

    /**
     * It returns the name of the subject.
     * @return Name of the subject.
     */
    public String getName(){
        return name;
    }

    /**
     * It returns a vector of strings with the members' values.
     * @return Vector of strings with the members' values.
     */
    public Vector<String> toString() {
        Vector<String> c = new Vector<>(11);

        c.set(0, name);
        c.set(1, Integer.toString(totalWeekHours));
        c.set(2, Integer.toString(numberStudents));
        c.set(3, Integer.toString(level));
        c.set(4, Integer.toString(hoursClasses[0]));
        c.set(5, Integer.toString(hoursClasses[1]));
        c.set(6, Integer.toString(hoursClasses[2]));
        c.set(7, Integer.toString(numberOfGroups[0]));
        c.set(8, Integer.toString(numberOfGroups[1]));
        c.set(9, Integer.toString(maxCapacity));
        c.set(10, Integer.toString(tyShift.ordinal()));

        return c;
    }
    /**
     * It returns the number of students that can be enrolled.
     * @return Number of students that can be enrolled.
     */
    public int getNumberStudents() {
        return numberStudents;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }
    /**
     * It returns the distribution of hours.
     * @return Vector with the distribution of hours in Theory, Laboratory and Problems.
     */
    public int[] getHoursClasses() {
        return hoursClasses;
    }
}