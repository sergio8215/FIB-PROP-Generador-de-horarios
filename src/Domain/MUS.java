package src.Domain;

import java.util.Vector;

public class MUS {
    /**
     * Minimum Unit of Schedule (MUS)
     * @author joaquimgomez
     */


    // Members
    Subject subject;
    ClassroomSesion classroomSesion;


    // Constructors

    /**
     * Empty Class constructor.
     */
    public MUS() {

    }

    /**
     * Class constructor specifying the member's values (and time zones).
     * @param subject Subject of the MUS.
     * @param classroomSesion ClassroomSesion of the MUS.

     */
    public MUS(Subject subject, ClassroomSesion classroomSesion) {
        this.subject = subject;
        this.classroomSesion = classroomSesion;

    }

    /**
     * Class constructor for a given vector of vectors with the members of the class (and their members).
     * @param mus Vector of vector (of strings) with the members to construct the class.
     */
    public MUS(Vector< Vector<String> > mus) {
        subject = new Subject(mus.get(0));
        classroomSesion = new ClassroomSesion(mus.get(1));
    }


    // Methods

    /**
     * It returns the subject of the MUS.
     * @return Subject of the MUS.
     */
    public Subject getSubject() {
        return subject;
    }

    /**
     * It returns the classroom-sesion of the MUS.
     * @return ClassroomSesion of the MUS.
     */
    public ClassroomSesion getClassroomSesion() {
        return classroomSesion;
    }

    /**
     * Set the subject of the MUS.
     * @param subject Subject of the MUS.
     */
    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    /**
     * Set the ClassroomSesion of the MUS.
     * @param classroomSesion Classroom of the MUS.
     */
    public void setClassroomSesion(ClassroomSesion classroomSesion) {
        this.classroomSesion = classroomSesion;
    }


    /**
     * It returns the class as a vector of vectos (one per member).
     * @return Vector of vector (of strings) with the members of the class.
     */
    public Vector< Vector<String> > toStr() {
        Vector< Vector<String> > mus = new Vector<>(2);

        mus.set(0,subject.toStr());
        mus.set(1,classroomSesion.toStr());

        return mus;
    }
}
