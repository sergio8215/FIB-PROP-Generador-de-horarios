package src.Domain;

public class LabClassroomClass extends ClassroomClass {

    /**
     *
     * @author mireia
     */

    //ATTRIBUTES
    private int numComputers;

    //CONSTRUCTOR

    /**
     *
     * @param name
     * @param capacity
     * @param type
     * @param multimedia
     * @param nComputers
     */
    public LabClassroomClass (String name, int capacity, int type, boolean multimedia, int nComputers ) {
        super(name, capacity, type, multimedia);
        numComputers = nComputers;
    }
}
