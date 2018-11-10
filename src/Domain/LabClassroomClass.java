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

    //GETTERS & SETTERS

    public int getNumComputers() {
        return numComputers;
    }

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

    @Override
    public Vector<String> toStr() {
        Vector<String> vec = super.toStr();
        vec.add(String.valueOf(numComputers));
        return vec;
    }
}
