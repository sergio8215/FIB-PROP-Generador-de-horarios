package src.Domain;

import java.util.Vector;

public class TheoryClassroomClass extends ClassroomClass {

    /**
     * Represents a theory classroom
     * @author mireia
     */

    //CONSTRUCTOR

    /**
     * Empty TheoryClassroomClass constructor
     */
    public TheoryClassroomClass(){

    }

    /**
     * TheoryClassroomClass constructor
     * @param n name of the classroom
     * @param cap capacity of the classroom
     * @param m indicates if the classroom has multimedia system
     */
    public TheoryClassroomClass(String n, int cap, boolean m) {
        super(n, cap, ClassroomType.THEORY, m);
    }

    /**
     * TheoryClassroomClass constructor from String
     * @param parse Vector of String Objects which encodes the attributes of this TheoryClassroomClass instance
     */
    public TheoryClassroomClass(Vector<String> parse) {
        super(parse);
    }

    //METHODS

    /**
     * Transforms the TheoryClassroom Object into a Vector of Strings
     * @return returns a Vector of Strings with the attributes encoded
     */
    @Override
    public Vector<String> toStr() {
        return super.toStr();
    }
}
