package src.domain.classes;

import src.domain.utils.UtilsDomain;

import java.util.Vector;

public class Subject {
    /**
     * Subject class represents a subject of a given Curriculum.
     * @author joaquimgomez
     */


    // Members

    private String name;
    private int numberStudents;
    private int level;
    private int[] hoursClasses = new int[3]; // [0] -> Theory, [1] -> Laboratory, [2] -> Problems
    private int[] numberOfGroups = new int[2]; // [0] -> Number of groups, [1] -> Number of subgroups  |||  SetDeclases genera los grupos correspondientes
    private UtilsDomain.typeShift tyShift;


    // Constructors

    /**
     * Empty constructor.
     */
    public Subject() {

    }

    /**
     * Class constructor specifying the member's values.
     * @param name Name of the subject.
     * @param level Level of the subject in the curriculum.
     * @param numberStudents Number of students that can be enrolled.
     * @param hoursClasses Distribution of class hours in a vector as follow: [Theory, Laboratory, Problems].
     * @param numberOfGroups Number of groups and subgroups.
     * @param tyShift Type of shift.
     */
    public Subject(String name, int numberStudents, int level, int[] hoursClasses, int[] numberOfGroups, UtilsDomain.typeShift tyShift){
        this.name = name;
        this.numberStudents = numberStudents;
        this.level = level;
        setHoursClasses(hoursClasses[0], hoursClasses[1], hoursClasses[2]);
        setNumberOfGroups(numberOfGroups[0], numberOfGroups[1]);
        this.tyShift = tyShift;
    }


    /**
     * Class constructor for a given vector with the members of the class.
     * @param vectorMembers Vector with the members of the class subject.
     */
    public Subject(Vector<String> vectorMembers) {
        name = vectorMembers.get(0);
        numberStudents = Integer.parseInt(vectorMembers.get(1));
        level = Integer.parseInt(vectorMembers.get(2));
        hoursClasses[0] = Integer.parseInt(vectorMembers.get(3));
        hoursClasses[1] = Integer.parseInt(vectorMembers.get(4));
        hoursClasses[2] = Integer.parseInt(vectorMembers.get(5));
        numberOfGroups[0] = Integer.parseInt(vectorMembers.get(6));
        numberOfGroups[1] = Integer.parseInt(vectorMembers.get(7));
        tyShift = UtilsDomain.typeShift.values()[Integer.parseInt(vectorMembers.get(8))];
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
     * @param theoryHours Hours of theory.
     * @param laboratoryHours Hours of Laboratory.
     * @param problemsHours Hours of problems.
     */
    public void setHoursClasses(int theoryHours, int laboratoryHours, int problemsHours){
        hoursClasses[0] = theoryHours; hoursClasses[1] = laboratoryHours; hoursClasses[2] = problemsHours;
    }

    /**
     * It returns the distribution of hours.
     * @return Vector with the distribution of hours in Theory, Laboratory and Problems.
     */
    public int[] getHoursClasses() {
        return hoursClasses;
    }

    /**
     * It returns the theory hours.
     * @return Theory hours.
     */
    public int getTheoryHours() {
        return hoursClasses[0];
    }

    /**
     * It returns the laboratory hours.
     * @return Laboratory hours.
     */
    public int getLaboratoryHours() {
        return hoursClasses[1];
    }

    /**
     * It returns the problems hours.
     * @return Problems hours.
     */
    public int getProblemsHours() {
        return hoursClasses[2];
    }

    /**
     * Set the theory hours.
     * @param theoryHours Theory hours.
     */
    public void setTheoryHours(int theoryHours) {
        hoursClasses[0] = theoryHours;
    }

    /**
     * Set the laboratory hours.
     * @param laboratoryHours Laboratory hours.
     */
    public void setLaboratoryHours(int laboratoryHours) {
        hoursClasses[1] = laboratoryHours;
    }

    /**
     * Set the problems hours.
     * @param problemsHours Problems hours.
     */
    public void setProblemsHours(int problemsHours) {
        hoursClasses[2] = problemsHours;
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
    public void setTypeShift(UtilsDomain.typeShift tyShift) {
        tyShift = tyShift;
    }

    /**
     * It returns the type of shift.
     * @return Type of the shift.
     */
    public UtilsDomain.typeShift getTypeShift() {
        return tyShift;
    }

    /**
     * It returns a vector of strings with the members' values.
     * @return Vector of strings with the members' values.
     */
    public Vector<String> toStr() {
        Vector<String> c = new Vector<>(9);

        c.add(0, name);
        c.add(1, Integer.toString(numberStudents));
        c.add(2, Integer.toString(level));
        c.add(3, Integer.toString(hoursClasses[0]));
        c.add(4, Integer.toString(hoursClasses[1]));
        c.add(5, Integer.toString(hoursClasses[2]));
        c.add(6, Integer.toString(numberOfGroups[0]));
        c.add(7, Integer.toString(numberOfGroups[1]));
        c.add(8, Integer.toString(tyShift.ordinal()));

        return c;
    }
}
