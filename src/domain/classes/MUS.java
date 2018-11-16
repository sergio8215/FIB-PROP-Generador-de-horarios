package src.domain.classes;

import src.domain.utils.UtilsDomain;

import java.util.Vector;


/**
 * Minimum Unit of Schedule (MUS)
 * @author Joaquim Gomez
 */

public class MUS {

    // Members
    ClassClass classclass;
    Classroom classroom;
    Session session;
    ClassroomSession domain; // No added in constructors and toStr because it's not mandatory.


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
        domain = new ClassroomSession();
    }

    /**
     * Class constructor specifying the member's values (classroom and session as a pair.).
     * @param classclass ClassClass of the MUS.
     * @param classroomSessionpair Pair with the Classroom and the Session of the MUS.
     */
    public MUS(ClassClass classclass, UtilsDomain.Pair<Classroom, Session> classroomSessionpair) {
        this.classclass = classclass;
        this.classroom = classroomSessionpair.first;
        this.session = classroomSessionpair.second;
        domain = new ClassroomSession();
    }

    /**
     * Class constructor for a given vector of vectors with the members of the class (and their members).
     * @param mus Vector of vector (of strings) with the members to construct the class.
     */
    public MUS(Vector< Vector<String> > mus) {
        classclass = ClassClass.fromStr(mus.get(0));
        classroom = Classroom.fromStr(mus.get(1));
        session = new Session(mus.get(2));
        domain = new ClassroomSession();
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
    public void setClassClass(ClassClass classclass) {
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
     * It returns the subject associated with the MUS.
     * @return Subject associated with the MUS.
     */
    public Subject getSubject() {
        return classclass.getSubject();
    }

    /**
     * It returns the domain of the MUS.
     * @return domain of the MUS.
     */
    public ClassroomSession getDomain() {
        return domain;
    }

    /**
     * Set the domain of the MUS.
     * @param domain domain of the MUS.
     */
    public void setDomain(ClassroomSession domain) {
        this.domain = domain;
    }

    /**
     * It returns the size of the domain.
     * @return domain of the size.
     */
    public int domainSize() {
        return domain.size();
    }

    /**
     * Assign classroom and session to the MUS.
     * @param csPair Pair with the Classroom and the Session.
     */
    public void assign(UtilsDomain.Pair<Classroom, Session> csPair) {
        classroom = csPair.first;
        session = csPair.second;
    }

    /**
     * It returns the value i of the domain.
     * @param i Id of the pair classroom-session inside the domain.
     * @return Value i of the domain.
     */
    public UtilsDomain.Pair<Classroom, Session> getValueDomain(int i){
        return domain.getPair(i);
    }

    /**
     * It deletes a value from domain.
     * @param i Id of the pair classroom-session to be deleted inside the domain.
     */
    public void deleteFromDomain(int i) {
        domain.delete(i);
    }

    /**
     * It returns the class as a vector of vectos (one per member).
     * @return Vector of vector (of strings) with the members of the class.
     */
    public Vector< Vector<String> > toStr() {
        Vector< Vector<String> > mus = new Vector<>(3);

        mus.add(0, classclass.toStr());
        mus.add(1, classroom.toStr());
        mus.add(2, session.toStr());

        return mus;
    }
}
