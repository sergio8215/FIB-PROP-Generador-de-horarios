package src.Domain;

public class LabClassroomClass extends ClassroomClass {
    private int numComputers;

    public LabClassroomClass (String name, int capacity, int type, boolean multimedia, int nComputers ) {
        super(name, capacity, type, multimedia);
        numComputers = nComputers;
    }

}
