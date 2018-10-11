package src.Domain;

public class Subject {

    // Members
    int totalWeekHours;
    int numberAlumnes;
    int level;
    int[] hoursClasses; // [0] -> Theory, [1] -> Laboratory, [2] -> Problems

    // Constructors
    public Subject(){

    }

    public Subject(int level, int numberAlumnes, int weekHours, int[] hoursClasses){
        this.level = level;
        this.totalWeekHours = weekHours;
        this.numberAlumnes = numberAlumnes;
        this.hoursClasses = hoursClasses;
        setHoursClasses(hoursClasses);
    }


    // Functions
    public void setWeekHours(int weekHours) {
        this.totalWeekHours = weekHours;
    }

    public int getWeekHours() {
        return totalWeekHours;
    }

    public void setNumberAlumnes(int numberAlumnes) {
        this.numberAlumnes = numberAlumnes;
    }

    public int getNumberAlumnes() {
        return numberAlumnes;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }

    public boolean setHoursClasses(int[] hoursClasses) {
        if (hoursClasses[0] + hoursClasses[1] + hoursClasses[2] == totalWeekHours){
            this.hoursClasses = hoursClasses;
            return true;
        } else {
            return false;
        }
    }

    public boolean setHoursClasses(int theoryHours, int laboratoryHours, int problemsHours){
        if (theoryHours + laboratoryHours + problemsHours == totalWeekHours){
            hoursClasses[0] = theoryHours; hoursClasses[1] = laboratoryHours; hoursClasses[2] = numberAlumnes;
            return true;
        } else {
            return false;
        }
    }

    public int[] getHoursClasses() {
        return hoursClasses;
    }
}
