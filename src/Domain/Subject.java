package src.Domain;

public class Subject {

    public enum typeShift {
        morningShift,
        afternoonShift,
        bothShifts
    }

    private static String name;
    private static int numberStudents;
    private static int level;
    private static int[] hoursClasses; // [0] -> Theory, [1] -> Laboratory, [2] -> Problems
    private static int maxCapacity;
    private static typeShift tyShift;

    public Subject(String name, int totalWeekHours, int numberStudents, int level, int[] hoursClasses, int[] numberOfGroups, int maxCapacity, typeShift tyShift) {
        this.name = name;
        this.numberStudents = numberStudents;
        this.level = level;
        this.hoursClasses = hoursClasses;
        this.maxCapacity = maxCapacity;
        this.tyShift = tyShift;
    }

    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        Subject.name = name;
    }

    public static int getNumberStudents() {
        return numberStudents;
    }

    public static void setNumberStudents(int numberStudents) {
        Subject.numberStudents = numberStudents;
    }

    public static int getLevel() {
        return level;
    }

    public static void setLevel(int level) {
        Subject.level = level;
    }

    public static int[] getHoursClasses() {
        return hoursClasses;
    }

    public static void setHoursClasses(int[] hoursClasses) {
        Subject.hoursClasses = hoursClasses;
    }

    public static int getMaxCapacity() {
        return maxCapacity;
    }

    public static void setMaxCapacity(int maxCapacity) {
        Subject.maxCapacity = maxCapacity;
    }

    public static typeShift getTyShift() {
        return tyShift;
    }

    public static void setTyShift(typeShift tyShift) {
        Subject.tyShift = tyShift;
    }
}
