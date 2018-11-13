package src.Domain;

public class Constraints {

    // UNARY CONSTRAINTS

    protected static boolean sizeClassroomUnaryConstraint(MUS m, Pair<Classroom, Session> cs) {
        return cs.first.getCapacity() >= m.getClassclass.studentsSize();       // FALTA studentsSize()
    }

    protected static boolean typeClassroomUnaryConstraint(MUS m, Pair<Classroom, Session> cs) {
        return cs.first.getType == m.getClassclass.getType();           // COMPATIBILIDAD DE TIPOS AULA Y TIPOS CLASE
                                                                        //NO ES TÉ EN COMPTE CLASSE DE TIPUS PROBLEMES
    }

    protected static boolean shiftClassUnaryConstraint(MUS m, Pair<Classroom, Session> cs) {
        if (m.getTimeZone() == UtilsDomain.TimeZone.MORNING) return cs.second.getHour() < 14;
        else return cs.second.getHour() >= 14;
    }

     //BINARY CONSTRAINTS


    // N-ARY CONSTRAINTS

    protected static boolean theoryAndLabsOfClassNoTogether(MUS m1, MUS m2) {
        // Si m1 de un tipo(t/p/l) igual que el tipo de m2 (t/p/l) y en la misma sesion => false
        if (m1.getClassClass().getType() == m2.getClassClass().getType() &&
                m1.getClassClass().getGroup() == m2.getClassClass().getGroup() &&
                Session.compare(m1.getSession(), "==", m2.getSession()))
            return false;
        return true;
    }

    protected static boolean theorysOfSubjectsOfSameLevelNoTogether(MUS m1, MUS m2) {
        if (m1.getClassCass().getType() == ClassClass.ClassType.THEORY &&
                m2.getClassClass().getType() == ClassClass.ClassType.THEORY &&
                !m1.getClassClass.getSubject().getName().equals(m2.getClassClass.getSubject().getName()) &&
                m1.getClassClass().getSubject().getLevel() == m2.getClassClass().getSubject().getLevel() &&
                Session.compare(m1.getSession(), "==", m2.getSession()))
            return false;
        return true;
    }

    protected static boolean theoryOfSubjectFromDifferentClassesNoTogether(MUS m1, MUS m2) {
        if (m1.getClassClass().getType() == ClassClass.ClassType.THEORY &&
                m2.getClassClass().getType() == ClassClass.ClassType.THEORY &&
                m1.getClassClass.getSubject().getName().equals(m2.getClassClass.getSubject().getName()) &&
                Session.compare(m1.getSession(), "==", m2.getSession()))
            return false;
        return true;
    }

    protected static boolean LabsAndProblemsFromDifferentSubjectsOfSameGroupNoTogether(MUS m1, MUS m2) {
        if ((m1.getClassClass().getType() == ClassClass.ClassType.LABORATORY ||
                m1.getClassClass().getType() == ClassClass.ClassType.PROBLEMS) &&
                (m2.getClassClass().getType() == ClassClass.ClassType.LABORATORY ||
                m2.getClassClass().getType() == ClassClass.ClassType.PROBLEMS) &&
                !m1.getClassClass().getSubject().getName().equals(m2.getClassClass().getSubject().getName()) &&
                m1.getClassClass().getGroup() == m2.getClassClass().getGroup() &&
                m1.getClassClass().getSubject().getLevel() == m2.getClassClass().getSubject().getLevel() &&
                Session.compare(m1.getSession(), "==", m2.getSession()))
            return false;
        return true;
    }

}
