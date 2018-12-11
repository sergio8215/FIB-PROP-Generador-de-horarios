package src.domain.classes;

import src.domain.utils.UtilsDomain.*;

/**
 * Constraints Class.
 * @author Joaquim Gómez & Mireia Cano
 */
public class Constraints {

    // UNARY CONSTRAINTS

    /**
     * Unary Constraint: Size Classroom Constraint.
     * @param m MUS to try the constraint with the pair Classroom-Session.
     * @param cs Pair Classroo-Session to try the contraint with the MUS.
     * @return True if satisfied constraint.
     */
    public static boolean sizeClassroomUnaryConstraint(MUS m, Pair<Classroom, Session> cs) {
        return cs.first.getCapacity() >= m.getClassClass().getQuantityStudents();
    }

    /**
     * Unary Constraint: Type Classroom Constraint.
     * @param m MUS to try the constraint with the pair Classroom-Session.
     * @param cs Pair Classroo-Session to try the contraint with the MUS.
     * @return True if satisfied constraint.
     */
    public static boolean typeClassroomUnaryConstraint(MUS m, Pair<Classroom, Session> cs) {
        return (cs.first.getType() == m.getClassClass().getType() ||
                (m.getClassClass().getType().ordinal() == ClassType.PROBLEMS.ordinal()
                        && cs.first.getType().ordinal() == ClassType.LABORATORY.ordinal() ));
    }

    /**
     * Unary Constraint: Shift Class Constraint
     * @param m MUS to try the constraint with the pair Classroom-Session.
     * @param cs Pair Classroo-Session to try the contraint with the MUS.
     * @return True if satisfied constraint.
     */
    public static boolean shiftClassUnaryConstraint(MUS m, Pair<Classroom, Session> cs) {
        if (m.getClassClass().getShift() == typeShift.MORNING ) return cs.second.getHour() < 14;
        else if (m.getClassClass().getShift() == typeShift.AFTERNOON ) return cs.second.getHour() >= 14;
        return true;
    }

     //BINARY CONSTRAINTS


    // N-ARY CONSTRAINTS

    //MODIFIED LINE

    /**
     * Checks if m1 and m2 satisfy all the compulsory constraints
     * @param m1 First MUS to try the constraints.
     * @param m2 Second MUS to try the constraints.
     * @return returns true if m1 and m2 satisfy all constraints, false otherwise;
     */
    public static boolean satisfiesConstraints(MUS m1, MUS m2){
        if(!(notSameClassroomAndSession(m1, m2) &&
                classOfSameSubgroupAndLevelNoTogether(m1, m2) &&
                theorysOfSubjectsOfSameLevelNoTogether(m1, m2) &&
                theoryOfSubjectFromDifferentClassesNoTogether(m1, m2) &&
                labsAndProblemsFromDifferentSubjectsOfSameGroupNoTogether(m1, m2) &&
                labsAndTheoryOfSameGroupAndSubjectNotTogether(m1, m2)))
            return false;
        return true;
    }

    /**
     * checks if two MUSes with the same identifier and that are paired with each other, satisfy the constraints
     * @param m1 First MUS to try the constraint.
     * @param m2 Second MUS to try the constraint.
     * @return true if MUSes are in the same Classroom and consecutive Sessions
     */
    public static boolean satisfiesSameClassNotPairedConditions(MUS m1, MUS m2) {
        if(!(m1.getClassroom().getName().equals(m2.getClassroom().getName()) &&
                m1.getSession().neighbor(m2.getSession())))
            return false;
        return true;
    }

    /**
     * Checks if two MUSes with the same identifier and that are paired with other MUSes, satisfy the constraints
     * @param m1 First MUS to try the constraint.
     * @param m2 Second MUS to try the constraint.
     * @return true if MUSes are not in the same Day
     */
    public static boolean satisfiesSameClassPairedConditions(MUS m1, MUS m2) {
        if(!(notSameDay(m1, m2)))
            return false;
        return true;
    }

    /**
     * N-ary Constraint: Not Same Classroom snd Session.
     * @param m1 First MUS to try the constraint.
     * @param m2 Second MUS to try the constraint.
     * @return True if satisfied constraint.
     */
    public static boolean notSameClassroomAndSession(MUS m1, MUS m2) {
        if((Session.compare(m1.getSession(), "==", m2.getSession())) &&
           (m1.getClassroom().getName().equals(m2.getClassroom().getName())))
            return false;
        return true;
    }

    /**
     * Checks if the MUSes take place on the same Day
     * @param m1 First MUS to try the constraint.
     * @param m2 Second MUS to try the constraint.
     * @return true if the MUSes not in the same Day
     */
    public static boolean notSameDay(MUS m1, MUS m2) {
        if(m1.getSession().getDay().ordinal() == m2.getSession().getDay().ordinal())
            return false;
        return true;
    }

    /**
     * N-ary Constraint: Classes of the same subgroup and lever can't take place at the same time.
     * @param m1 First MUS to try the constraint.
     * @param m2 Second MUS to try the constraint.
     * @return True if satisfied constraint.
     */
    public static boolean classOfSameSubgroupAndLevelNoTogether(MUS m1, MUS m2) {
        // Si m1 de un tipo(t/p/l) igual que el tipo de m2 (t/p/l) y en la misma sesion => false
        if (m1.getClassClass().getType() == m2.getClassClass().getType() &&
                m1.getClassClass().getSubGroup() == m2.getClassClass().getSubGroup() &&
                m1.getClassClass().getSubject().getLevel() == m2.getClassClass().getSubject().getLevel() &&
                Session.compare(m1.getSession(), "==", m2.getSession()))
            return false;
        return true;
    }

    /**
     * N-ary Constraint: Theorys of Subjects Of Same Level no Together.
     * @param m1 First MUS to try the constraint.
     * @param m2 Second MUS to try the constraint.
     * @return True if satisfied constraint.
     */
    public static boolean theorysOfSubjectsOfSameLevelNoTogether(MUS m1, MUS m2) {
        if (m1.getClassClass().getType() == ClassType.THEORY &&
                m2.getClassClass().getType() == ClassType.THEORY &&
                !m1.getClassClass().getSubject().getName().equals(m2.getClassClass().getSubject().getName()) &&
                m1.getClassClass().getSubject().getLevel() == m2.getClassClass().getSubject().getLevel() &&
                m1.getClassClass().getGroup() == m2.getClassClass().getGroup() &&
                Session.compare(m1.getSession(), "==", m2.getSession()))
            return false;
        return true;
    }

    /**
     * N-ary Constraint: Theory of Subject From Different Classes no Together
     * @param m1 First MUS to try the constraint.
     * @param m2 Second MUS to try the constraint.
     * @return True if satisfied constraint.
     */
    public static boolean theoryOfSubjectFromDifferentClassesNoTogether(MUS m1, MUS m2) {
        if (m1.getClassClass().getType() == ClassType.THEORY &&
                m2.getClassClass().getType() == ClassType.THEORY &&
                m1.getClassClass().getSubject().getName().equals(m2.getClassClass().getSubject().getName()) &&
                Session.compare(m1.getSession(), "==", m2.getSession()))
            return false;
        return true;
    }

    /**
     * N-ary Constraint: Labs and Problems From Different Subjects of Same Group no Together
     * @param m1 First MUS to try the constraint.
     * @param m2 Second MUS to try the constraint.
     * @return True if satisfied constraint.
     */
    public static boolean labsAndProblemsFromDifferentSubjectsOfSameGroupNoTogether(MUS m1, MUS m2) {
        if ((m1.getClassClass().getType() == ClassType.LABORATORY ||
                m1.getClassClass().getType() == ClassType.PROBLEMS) &&
                (m2.getClassClass().getType() == ClassType.LABORATORY ||
                m2.getClassClass().getType() == ClassType.PROBLEMS) &&
                !m1.getClassClass().getSubject().getName().equals(m2.getClassClass().getSubject().getName()) &&
                m1.getClassClass().getGroup() == m2.getClassClass().getGroup() &&
                m1.getClassClass().getSubject().getLevel() == m2.getClassClass().getSubject().getLevel() &&
                Session.compare(m1.getSession(), "==", m2.getSession()))
            return false;
        return true;
    }

    public static boolean labsAndTheoryOfSameGroupAndSubjectNotTogether(MUS m1, MUS m2) {
        if(m1.getClassClass().getSubject().getName().equals(m2.getClassClass().getSubject().getName()) &&
                ((m1.getClassClass().getType() == ClassType.THEORY &&
                        (m2.getClassClass().getType() == ClassType.LABORATORY ||
                         m2.getClassClass().getType() == ClassType.PROBLEMS)) ||
                  (m2.getClassClass().getType() == ClassType.THEORY &&
                        (m1.getClassClass().getType() == ClassType.LABORATORY ||
                         m1.getClassClass().getType() == ClassType.PROBLEMS))) &&
                m1.getClassClass().getGroup() == m2.getClassClass().getGroup() &&
                Session.compare(m1.getSession(), "==", m2.getSession()))
            return false;
        return true;
    }

}
