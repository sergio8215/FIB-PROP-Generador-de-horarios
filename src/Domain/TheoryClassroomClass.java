package src.Domain;

import java.util.Vector;

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
     * @param m indicates if the classroom has multimedia system
     */
    TheoryClassroomClass(String n, int cap, boolean m) {
        super(n, cap, ClassroomType.THEORY, m);
    }

    //METHODS

    @Override
    public Vector<String> toStr() {
        return super.toStr();
    }
}
