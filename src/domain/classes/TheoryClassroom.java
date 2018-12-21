package src.domain.classes;

import src.domain.utils.UtilsDomain;

import java.util.Vector;

/**
 * Represents a theory classroom
 * @author mireia
 */
public class TheoryClassroom extends Classroom {

    //CONSTRUCTOR

    /**
     * Empty TheoryClassroom constructor
     */
    public TheoryClassroom(){

    }

    /**
     * TheoryClassroom constructor
     * @param name name of the classroom
     * @param capacity capacity of the classroom
     * @param multimedia indicates if the classroom has multimedia system
     */
    public TheoryClassroom(String name, int capacity, boolean multimedia) {
        super(name, capacity, UtilsDomain.ClassType.THEORY, multimedia);
    }

    /**
     * TheoryClassroom constructor from String
     * @param parse Vector of String Objects which encodes the attributes of this TheoryClassroom instance
     */
    public TheoryClassroom(Vector<String> parse) {
        super(parse);
    }

    //METHODS

    /**
     * Transforms the TheoryClassroom Object into a Vector of Strings
     * @return returns a Vector of Strings with the attributes encoded
     */
    @Override
    public Vector<String> toStr() {
        Vector<String> vec = super.toStr();
        vec.add("0");
        return vec;
    }
}
