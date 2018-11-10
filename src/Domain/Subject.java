package src.Domain;

public class Subject {

    private static String name;
    private static int numberStudents;
    private static int level;
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
}