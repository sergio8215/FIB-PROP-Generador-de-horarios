package src.domain.classes;

import src.domain.utils.UtilsDomain;

import java.util.Vector;

/**
 * Represents a laboratory classroom
 * @author mireia
 */
public class LabClassroom extends Classroom {

    //ATTRIBUTES

    private int numComputers;

    //CONSTRUCTOR

    /**
     * Empty LabClassroom constructor
     */
    public LabClassroom(){

    }

    /**
     * LabClassroom constructor
     * @param name name of the classroom
     * @param capacity maximum capacity of the classroom
     * @param multimedia indicates if the classroom has multimedia system
     * @param nComputers number of computers in the classroom
     */
    public LabClassroom(String name, int capacity, boolean multimedia, int nComputers ) {
        super(name, capacity, UtilsDomain.ClassType.LABORATORY, multimedia);
        numComputers = nComputers;
    }

    /**
     * LabClassroom constructor from String
     * @param parse Vector of String Objects which encode the attributes of this LabClassroom instance
     */
    public LabClassroom(Vector<String> parse) {
        super(parse);
        numComputers = Integer.parseInt(parse.get(4));
    }

    //GETTERS & SETTERS

    /**
     * Getter of the numComputers attribute
     * @return returns the numComputers attribute
     */
    public int getNumComputers() {
        return numComputers;
    }

    /**
     * Setter of the numCOmputers attribute
     * @param numComputers the number of computers of the classroom
     */
    public void setNumComputers(int numComputers) {
        this.numComputers = numComputers;
    }

    //METHODS

    /**
     * calculates the real capacity considering groups on the same computers
     * @param ppc persons per computer
     * @return returns the real capacity of the laboratory classroom
     */
    public int realCapacity(int ppc) {
        return Math.min(this.getCapacity(), numComputers*ppc);
    }

    /**
     * Converts the labClassroom Object into a Vector of Strings
     * @return returns a Vector of Strings that represents the labClassroom instance
     */
    @Override
    public Vector<String> toStr() {
        Vector<String> vec = super.toStr();
        vec.add(4, String.valueOf(numComputers));
        return vec;
    }
}
