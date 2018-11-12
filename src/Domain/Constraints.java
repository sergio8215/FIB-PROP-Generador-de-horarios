package src.Domain;

public class Constraints {

    // UNARY CONSTRAINTS

    protected static boolean sizeClassroomUnaryConstraint(MUS m, Pair<Classroom, Session> cs) {
        if (cs.first.getCapacity() < m.getClassclass.studentsSize())    return false;       // FALTA studentsSize()
        return true;
    }

    protected static boolean typeClassroomUnaryConstraint(MUS m, Pair<Classroom, Session> cs) {
        if (cs.first.getType != m.getClassclass.getType())      return false;           // COMPATIBILIDAD DE TIPOS AULA Y TIPOS CLASE
        return true;
    }

    protected static boolean shiftClassUnaryConstraint(MUS m, Pair<Classroom, Session> cs) {

    }


    //



}
