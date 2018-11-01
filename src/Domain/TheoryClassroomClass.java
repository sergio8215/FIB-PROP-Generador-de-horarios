package src.Domain;

public class TheoryClassroomClass extends ClassroomClass {

    /**
     * Represents a theory classroom
     * @author mireia
     */

    //CONSTRUCTOR
    /**
     * TheoryClassroomClass constructor
     * @param n name of the classroom
     * @param cap capacity of the classroom
     * @param t type of the classroom
     * @param m tells us if the classroom has multimedia system
     */
    TheoryClassroomClass(String n, int cap, int t, boolean m) {
        super(n, cap, t, m);
    }
}
