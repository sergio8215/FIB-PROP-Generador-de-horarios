package src.Domain;

import java.util.Vector;

public class LabClassroomClass extends ClassroomClass {

    /**
     * Represents a laboratory classroom
     * @author mireia
     */

    //ATTRIBUTES

    private int numComputers;

    //CONSTRUCTOR

    /**
     * Empty LabClassroomClass constructor
     */
    public LabClassroomClass(){

    }

    /**
     * LabClassroomClass constructor
     * @param name name of the classroom
     * @param capacity maximum capacity of the classroom
     * @param multimedia indicates if the classroom has multimedia system
     * @param nComputers number of computers in the classroom
     */
    public LabClassroomClass (String name, int capacity, boolean multimedia, int nComputers ) {
        super(name, capacity, ClassroomType.LABORATORY, multimedia);
        numComputers = nComputers;
    }

    /**
     * LabClassroomClass constructor from String
     * @param parse Vector of String Objects which encode the attributes of this LabClassroomClass instance
     */
    public LabClassroomClass(Vector<String> parse) {
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
