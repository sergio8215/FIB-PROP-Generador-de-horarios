package src.Domain;

import java.util.ArrayList;

public class ClassroomSession {

    private ArrayList<UtilsDomain.Pair> classroomSessionSet;

    /**
     * ClassroomSession constructor
     * @param crSet Set of all the Classroom Objects
     */
    public ClassroomSession(ClassroomSetClass crSet) {
        ArrayList<ClassroomClass> classroomValues = crSet.getClassroomValues(); //arraylist de les classrooms
        Session s = new Session(); //instancia de sessio per pillar tota la info general que necessiti
        UtilsDomain.Day arr[] = UtilsDomain.Day.values(); //enum posat en array. aixo no se molt be com gestionar-ho

        for(int i = 0; i <classroomValues.size(); ++i) { //per cada classroom...
            for(int j = 0; j < arr.length; ++j) {   //...cada dia de la setmana...
                //TODO: aquests mètodes no estan a la classr Session
                for(int k = s.getStartHour(); k < s.getStartHour() + s.getHoursPerDay(); ++k) { //... i cada hora del dia tinc una sessio
                    UtilsDomain.Pair pair = new UtilsDomain.Pair();
                    pair.first = classroomValues.get(i); //el primer valor del pair es una classroom
                    //el segon valor del pair es un altre pair que conte firs t= dia de la setmana (enum que he posat al Utils) i second = hora(int)
                    UtilsDomain.Pair sessionPair = new UtilsDomain.Pair(arr[j], k);
                }
            }
        }
    }

    /**
     * Getter of the classroomSessionSet attribute
     * @return returns the classroomSessionSet attribute
     */
    public ArrayList<UtilsDomain.Pair> getClassroomSessionSet() {
        return classroomSessionSet;
    }

    /**
     * Setter of the classroomSessionSet attribute
     * @param classroomSessionSet set of classroomSessions Pairs
     */
    public void setClassroomSessionSet(ArrayList<UtilsDomain.Pair> classroomSessionSet) {
        this.classroomSessionSet = classroomSessionSet;
    }

    /**
     * Getter of an specific classroomSesson from the classroomSessionSet attribute
     * @param id position of the pair we want
     * @return returns a the pair that was at the id position
     */
    public UtilsDomain.Pair getPair(int id) {
        return classroomSessionSet.get(id);
    }
}
