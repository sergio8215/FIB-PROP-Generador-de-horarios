package src.Domain;

import java.util.Vector;

public class MUS {
    /**
     * Minimum Unit of Schedule (MUS)
     * @author joaquimgomez
     */


    // Members
    ClassClass classclass;
    Classroom classroom;
    Session session;
    ClassroomSession domain;


    // Constructors

    /**
     * Empty Class constructor.
     */
    public MUS() {

    }

    /**
     * Class constructor specifying the member's values.
     * @param classclass Classclass of the MUS.
     * @param classroom Classroom of the MUS.
     * @param session Session of the MUS.
     */
    public MUS(ClassClass classclass, Classroom classroom, Session session) {
        this.classclass = classclass;
        this.classroom = classroom;
        this.session = session;
    }

    /**
     * Class constructor specifying the member's values (classroom and session as a pair.).
     * @param classclass
     * @param classroomSessionpair
     */
    public MUS(ClassClass classclass, UtilsDomain.Pair<Classroom, Session> classroomSessionpair) {
        this.classclass = classclass;
        this.classroom = classroomSessionpair.first;
        this.session = classroomSessionpair.second;
    }

    /**
     * Class constructor for a given vector of vectors with the members of the class (and their members).
     * @param mus Vector of vector (of strings) with the members to construct the class.
     */
    public MUS(Vector< Vector<String> > mus) {
        classclass = new ClassClass(mus.get(0));
        classroom = new Classroom(mus.get(1));
        session = new Session(mus.get(2));
    }


    // Methods

    /**
     * It returns the classclass of the MUS.
     * @return Classclass of the MUS.
     */
    public ClassClass getClassClass() {
        return classclass;
    }

    /**
     * It return the classroom of the MUS.
     * @return Classrooom of the MUS.
     */
    public Classroom getClassroom() {
        return classroom;
    }

    /**
     * It returns the session of the MUS:
     * @return Session of the MUS.
     */
    public Session getSession() {
        return session;
    }

    /**
     * Set the classclass of the MUS.
     * @param classclass Classclass of the MUS.
     */
    public void setClassclass(ClassClass classclass) {
        this.classclass = classclass;
    }

    /**
     * Set the classroom of the MUS.
     * @param classroom Classroom of the MUS.
     */
    public void setClassroom(Classroom classroom) {
        this.classroom = classroom;
    }

    /**
     * Set the session of the MUS:
     * @param session Session of the mus
     */
    public void setSession(Session session) {
        this.session = session;
    }

    /**
     * It return a pair with the Classroom and the Session.
     * @return Pair with the Classroom and the Session.
     */
    public UtilsDomain.Pair<Classroom, Session> getClassroomSessionPair() {
        UtilsDomain.Pair<Classroom, Session> res = new UtilsDomain.Pair<Classroom, Session>(classroom, session);
        return res;
    }


    /**
     * It returns the domain of the MUS.
     * @return Domain of the MUS.
     */
    public ClassroomSession getDomain() {
        return domain;
    }

    /**
     * Set the domain of the MUS.
     * @param domain Domain of the MUS.
     */
    public void setDomain(ClassroomSession domain) {
        this.domain = domain;
    }

    /**
     * It returns the size of the domain.
     * @return Domain of the size.
     */
    public void int domainSize() {
        return domain.size();
    }

    /**
     * Assign classroom and session to the MUS.
     * @param csPair Pair with the Classroom and the Session.
     */
    public void assign(Pair<Classroom, Session> csPair) {
        classroom = csPair.first;
        session = csPair.second;
    }

    /**
     * It returns the class as a vector of vectos (one per member).
     * @return Vector of vector (of strings) with the members of the class.
     */
    public Vector< Vector<String> > toStr() {
        Vector< Vector<String> > mus = new Vector<>(3);

        mus.set(0, classclass.toStr());
        mus.set(1, classroom.toStr());
        mus.set(2, session.toStr());

        return mus;
    }
}
