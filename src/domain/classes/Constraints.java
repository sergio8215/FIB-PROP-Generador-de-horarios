package src.domain.classes;

import src.domain.utils.UtilsDomain;
import src.domain.utils.UtilsDomain.*;

public class Constraints {

    // UNARY CONSTRAINTS

    public static boolean sizeClassroomUnaryConstraint(MUS m, Pair<Classroom, Session> cs) {
        return cs.first.getCapacity() >= m.getClassClass().getQuantityStudents();       // FALTA studentsSize()
    }

    public static boolean typeClassroomUnaryConstraint(MUS m, Pair<Classroom, Session> cs) {
        return cs.first.getType() == m.getClassClass().getType();           // COMPATIBILIDAD DE TIPOS AULA Y TIPOS CLASE
                                                                        //NO ES TÉ EN COMPTE CLASSE DE TIPUS PROBLEME
    }

    public static boolean shiftClassUnaryConstraint(MUS m, Pair<Classroom, Session> cs) {
        if (m.getClassClass().getShift() == typeShift.MORNING ) return cs.second.getHour() < 14;
        else if (m.getClassClass().getShift() == typeShift.AFTERNOON ) return cs.second.getHour() >= 14;
        return true;
    }

     //BINARY CONSTRAINTS


    // N-ARY CONSTRAINTS

    public static boolean notSameClassroomAndSession(MUS m1, MUS m2) {
        if((m1.getSession().getDay() == m1.getSession().getDay()) &&
           (m1.getSession().getHour() == m2.getSession().getHour()) &&
           (m1.getClassroom().getName().equals(m2.getClassroom().getName())))
            return false;
        return true;
    }

    public static boolean theoryAndLabsOfClassNoTogether(MUS m1, MUS m2) {
        // Si m1 de un tipo(t/p/l) igual que el tipo de m2 (t/p/l) y en la misma sesion => false
        if (m1.getClassClass().getType() == m2.getClassClass().getType() &&
                m1.getClassClass().getSubGroup() == m2.getClassClass().getSubGroup() &&
                m1.getClassClass().getSubject().getLevel() == m2.getClassClass().getSubject().getLevel() &&
                Session.compare(m1.getSession(), "==", m2.getSession()))
            return false;
        return true;
    }

    public static boolean theorysOfSubjectsOfSameLevelNoTogether(MUS m1, MUS m2) {
        if (m1.getClassClass().getType() == ClassType.THEORY &&
                m2.getClassClass().getType() == ClassType.THEORY &&
                !m1.getClassClass().getSubject().getName().equals(m2.getClassClass().getSubject().getName()) &&
                m1.getClassClass().getSubject().getLevel() == m2.getClassClass().getSubject().getLevel() &&
                Session.compare(m1.getSession(), "==", m2.getSession()))
            return false;
        return true;
    }

    public static boolean theoryOfSubjectFromDifferentClassesNoTogether(MUS m1, MUS m2) {
        if (m1.getClassClass().getType() == ClassType.THEORY &&
                m2.getClassClass().getType() == ClassType.THEORY &&
                m1.getClassClass().getSubject().getName().equals(m2.getClassClass().getSubject().getName()) &&
                Session.compare(m1.getSession(), "==", m2.getSession()))
            return false;
        return true;
    }

    protected static boolean LabsAndProblemsFromDifferentSubjectsOfSameGroupNoTogether(MUS m1, MUS m2) {
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

}