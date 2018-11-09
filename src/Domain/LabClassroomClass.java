package Domain;

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
        super(name, capacity, UtilsDomain.ClassroomType.LABORATORY, multimedia);
        numComputers = nComputers;
    }


}