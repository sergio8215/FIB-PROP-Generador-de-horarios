package src.domain.classes;

import src.domain.utils.UtilsDomain.*;

import java.util.Vector;


/**
 * Constraints Class.
 * @author Joaquim Gomez & Mireia Cano
 */
public class Constraints {

    /**
     * Special class to maintain the status of restrictions.
     */
    private static class ConstraintsSet {
        // Structure to save the enabled constraints

        static boolean notSameClassroomAndSessionEnabled                                   = false;
        static boolean labsAndTheoryOfSameGroupAndSubjectNotTogetherEnabled                = false;
        static boolean classOfSameSubgroupAndLevelNoTogetherEnabled                        = false;
        static boolean theoryOfSubjectFromDifferentClassesNoTogetherEnabled                = false;
        static boolean theorysOfSameLevelNoTogetherEnabled                                 = false;

        /**
         * Set the constraints to the intern state.
         * @param sc Constraints.
         */
        private static void setContraints(boolean[] sc){
            notSameClassroomAndSessionEnabled = sc[0];
            labsAndTheoryOfSameGroupAndSubjectNotTogetherEnabled = sc[1];
            classOfSameSubgroupAndLevelNoTogetherEnabled = sc[2];
            theoryOfSubjectFromDifferentClassesNoTogetherEnabled = sc[3];
            theorysOfSameLevelNoTogetherEnabled = sc[4];
        }
    }

    /**
     * Set the constraints.
     * @param sc Constraints.
     */
    public void setContraints(boolean[] sc){
        ConstraintsSet.setContraints(sc);
    }

    /**
     * Returns the constraints.
     * @return Constraints.
     */
    public Boolean[] getConstraints(){
        Boolean[] sc = new Boolean[5];
        sc[0] = ConstraintsSet.notSameClassroomAndSessionEnabled;
        sc[1] = ConstraintsSet.labsAndTheoryOfSameGroupAndSubjectNotTogetherEnabled;
        sc[2] = ConstraintsSet.classOfSameSubgroupAndLevelNoTogetherEnabled;
        sc[3] = ConstraintsSet.theoryOfSubjectFromDifferentClassesNoTogetherEnabled;
        sc[4] = ConstraintsSet.theorysOfSameLevelNoTogetherEnabled;
        return sc;
    }

    /**
     * Convert the restrictions to strings.
     * @return Constraints formated to string.
     */
    public Vector<String> toStr(){
        Vector<String> c = new Vector<>(5);
        c.add(Boolean.toString(ConstraintsSet.notSameClassroomAndSessionEnabled));
        c.add(Boolean.toString(ConstraintsSet.labsAndTheoryOfSameGroupAndSubjectNotTogetherEnabled));
        c.add(Boolean.toString(ConstraintsSet.classOfSameSubgroupAndLevelNoTogetherEnabled));
        c.add(Boolean.toString(ConstraintsSet.theoryOfSubjectFromDifferentClassesNoTogetherEnabled));
        c.add(Boolean.toString(ConstraintsSet.theorysOfSameLevelNoTogetherEnabled));
        return c;
    }

    // UNARY CONSTRAINTS

    /**
     * Unary Constraint: Size Classroom Constraint.
     * @param m MUS to try the constraint with the pair Classroom-Session.
     * @param cs Pair Classroo-Session to try the contraint with the MUS.
     * @return True if satisfied constraint.
     */
    public static boolean sizeClassroomUnaryConstraint(MUS m, Pair<Classroom, Session> cs) {
        /* Una clase debe tener un aula de tamaño mayor o igual que el número de alumnos de la clase. */
        return cs.first.getCapacity() >= m.getClassClass().getQuantityStudents();
    }

    /**
     * Unary Constraint: Type Classroom Constraint.
     * @param m MUS to try the constraint with the pair Classroom-Session.
     * @param cs Pair Classroo-Session to try the contraint with the MUS.
     * @return True if satisfied constraint.
     */
    public static boolean typeClassroomUnaryConstraint(MUS m, Pair<Classroom, Session> cs) {
        /* Una clase de una asignatura de un tipo solo puede ir a un aula de dicho tipo. */
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
        /* Las clases de asignaturas /del mismo nivel/ han de ser de mañanas o de tarde, pero no de ambas. */
        if (m.getClassClass().getShift() == typeShift.MORNING ) return cs.second.getHour() < 14;
        else if (m.getClassClass().getShift() == typeShift.AFTERNOON ) return cs.second.getHour() >= 14;
        return true;
    }



    //CONSTRAINTS CHECKERS

    /**
     * Checks if m1 and m2 satisfy all the compulsory constraints
     * @param m1 First MUS to try the constraints.
     * @param m2 Second MUS to try the constraints.
     * @return returns true if m1 and m2 satisfy all constraints, false otherwise;
     */
    public static boolean satisfiesConstraints(MUS m1, MUS m2){
        if(!((!ConstraintsSet.notSameClassroomAndSessionEnabled || notSameClassroomAndSession(m1, m2)) &&
                (!ConstraintsSet.labsAndTheoryOfSameGroupAndSubjectNotTogetherEnabled || labsAndTheoryOfSameGroupAndSubjectNotTogether(m1, m2)) &&
                (!ConstraintsSet.classOfSameSubgroupAndLevelNoTogetherEnabled || classOfSameSubgroupAndLevelNoTogether(m1, m2)) &&
                (!ConstraintsSet.theoryOfSubjectFromDifferentClassesNoTogetherEnabled || theoryOfSubjectFromDifferentClassesNoTogether(m1, m2))) &&
                (!ConstraintsSet.theorysOfSameLevelNoTogetherEnabled || theorysOfSameLevelNoTogether(m1, m2)))
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
        if((m1.getSession().getDay().ordinal() == m2.getSession().getDay().ordinal()))
            return false;
        return true;
    }



    // N-ARY CONSTRAINTS

    /**
     * N-ary Constraint: Not Same Classroom and Session.
     * @param m1 First MUS to try the constraint.
     * @param m2 Second MUS to try the constraint.
     * @return True if satisfied constraint.
     */
    public static boolean notSameClassroomAndSession(MUS m1, MUS m2) {
        /* Dos sesiones no pueden coincidir en la misma aula. */
        if((Session.compare(m1.getSession(), "==", m2.getSession())) &&
           (m1.getClassroom().getName().equals(m2.getClassroom().getName())))
            return false;
        return true;
    }

    /**
     * N-ary Constraint: Theory sessions can't collapse with laboratory/problems sessions of the same group.
     * @param m1 First MUS to try the constraint.
     * @param m2 Second MUS to try the constraint.
     * @return True if satisfied constraint.
     */
    public static boolean labsAndTheoryOfSameGroupAndSubjectNotTogether(MUS m1, MUS m2) {
        /* La teoría de los grupos de una asignatura no puede coincidir con sus laboratorios/problemas. */
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

    /**
     * N-ary Constraint: Classes of the same subgroup and lever can't take place at the same time.
     * @param m1 First MUS to try the constraint.
     * @param m2 Second MUS to try the constraint.
     * @return True if satisfied constraint.
     */
    public static boolean classOfSameSubgroupAndLevelNoTogether(MUS m1, MUS m2) {
        /* Clases del mismo subgrupo y mismo nivel no pueden coincidir. */
        if (m1.getClassClass().getSubGroup() == m2.getClassClass().getSubGroup() &&
                m1.getClassClass().getSubject().getLevel() == m2.getClassClass().getSubject().getLevel() &&
                Session.compare(m1.getSession(), "==", m2.getSession()))
            return false;
        return true;
    }

    /**
     * N-ary Constraint: Theory Classes of Same Subject no Together.
     * @param m1 First MUS to try the constraint.
     * @param m2 Second MUS to try the constraint.
     * @return True if satisfied constraint.
     */
    public static boolean theoryOfSubjectFromDifferentClassesNoTogether(MUS m1, MUS m2) {
        /* Teorías de una misma asignatura no pueden coincidir. */
        if (m1.getClassClass().getType() == ClassType.THEORY &&
                m2.getClassClass().getType() == ClassType.THEORY &&
                m1.getClassClass().getSubject().getName().equals(m2.getClassClass().getSubject().getName()) &&
                Session.compare(m1.getSession(), "==", m2.getSession()))
            return false;
        return true;
    }



    // SPECIAL CONSTRAINTS

    /**
     * Special Constraint: Theories of the Same Level no Together.
     * @param m1 First MUS to try the constraint.
     * @param m2 Second MUS to try the constraint.
     * @return True if satisfied constraint.
     */
    public static boolean theorysOfSameLevelNoTogether(MUS m1, MUS m2) {
        /* Teorias del mismo nivel no pueden coincidir. */
        if (m1.getClassClass().getType() == ClassType.THEORY &&
                m2.getClassClass().getType() == ClassType.THEORY &&
                m1.getSubject().getLevel() == m2.getSubject().getLevel() &&
                Session.compare(m1.getSession(), "==", m2.getSession()))
            return false;
        return true;
    }

}
