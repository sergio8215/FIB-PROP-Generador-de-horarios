package src.Domain;

public class Subject {
    /**
     * Subject class represents a subject of a given Curriculum.
     * @author joaquimgomez
     */


    // Members

    private static int totalWeekHours;
    private static int numberAlumnes;
    private static int level;
    private static int[] hoursClasses; // [0] -> Theory, [1] -> Laboratory, [2] -> Problems


    // Constructors

    /**
     * Class constructor.
     * @pre
     * @post
     */
    public Subject(){

    }

    /**
     * Class constructor specifying the member's values.
     * @pre
     * @post
     * @param level
     * @param numberAlumnes
     * @param weekHours
     * @param hoursClasses
     */
    public Subject(int level, int numberAlumnes, int weekHours, int[] hoursClasses){
        this.level = level;
        this.totalWeekHours = weekHours;
        this.numberAlumnes = numberAlumnes;
        this.hoursClasses = hoursClasses;
        setHoursClasses(hoursClasses);
    }


    // Methods

    /**
     *
     * @pre
     * @post
     * @param weekHours
     */
    public void setWeekHours(int weekHours) {
        this.totalWeekHours = weekHours;
    }

    /**
     *
     *
     * @pre
     * @post
     * @return
     */
    public int getWeekHours() {
        return totalWeekHours;
    }

    /**
     *
     * @pre
     * @post
     * @param numberAlumnes
     */
    public void setNumberAlumnes(int numberAlumnes) {
        this.numberAlumnes = numberAlumnes;
    }

    /**
     *
     * @pre
     * @post
     * @return
     */
    public int getNumberAlumnes() {
        return numberAlumnes;
    }

    /**
     *
     * @pre
     * @post
     * @param level
     */
    public void setLevel(int level) {
        this.level = level;
    }

    /**
     *
     * @pre
     * @post
     * @return
     */
    public int getLevel() {
        return level;
    }

    /**
     *
     * @pre
     * @post
     * @param hoursClasses
     * @return
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
     *
     * @pre
     * @post
     * @param theoryHours
     * @param laboratoryHours
     * @param problemsHours
     * @return
     */
    public boolean setHoursClasses(int theoryHours, int laboratoryHours, int problemsHours){
        if (theoryHours + laboratoryHours + problemsHours == totalWeekHours){
            hoursClasses[0] = theoryHours; hoursClasses[1] = laboratoryHours; hoursClasses[2] = numberAlumnes;
            return true;
        } else {
            return false;
        }
    }

    /**
     *
     * @pre
     * @post
     * @return
     */
    public int[] getHoursClasses() {
        return hoursClasses;
    }
}
