package src.Domain;

public class Subject {
    /**
     * Subject class represents a subject of a given Curriculum.
     * @author joaquimgomez
     */


    // Members

    private static String name;
    private static int totalWeekHours;
    private static int numberStudents;
    private static int level;
    private static int[] hoursClasses; // [0] -> Theory, [1] -> Laboratory, [2] -> Problems


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
     */
    public Subject(String name, int level, int numberStudents, int weekHours, int[] hoursClasses){
        this.name = name;
        this.level = level;
        this.totalWeekHours = weekHours;
        this.numberStudents = numberStudents;
        this.hoursClasses = hoursClasses;
        setHoursClasses(hoursClasses);
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
    public int getWeekHours() {
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
            hoursClasses[0] = theoryHours; hoursClasses[1] = laboratoryHours; hoursClasses[2] = numberStudents;
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
}
