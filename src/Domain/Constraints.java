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

    }

     //BINARY CONSTRAINTS

}
