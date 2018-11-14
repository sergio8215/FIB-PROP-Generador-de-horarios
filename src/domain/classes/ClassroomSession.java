package src.domain.classes;

import src.domain.utils.UtilsDomain;

import java.util.ArrayList;

public class ClassroomSession {

    //ATTRIBUTES

    private ArrayList<UtilsDomain.Pair> classroomSessionSet;

    //CONSTRUCTORS

    /**
     * Empty ClassroomSession constructor
     */
    public ClassroomSession(){}

    /**
     * ClassroomSession constructor
     * @param crSet Set of all the Classroom Objects
     */
    public ClassroomSession(ClassroomSet crSet) {
        classroomSessionSet = new ArrayList<UtilsDomain.Pair>();
        ArrayList<Classroom> classroomValues = crSet.getClassroomValues(); //arraylist de les classrooms
        UtilsDomain.Day arr[] = UtilsDomain.Day.values(); //enum posat en array. aixo no se molt be com gestionar-ho

        for(int i = 0; i <classroomValues.size(); ++i) { //per cada classroom...
            for(int j = 0; j < Session.daysOfTheWeek; ++j) {   //...cada dia de la setmana...
                //TODO: aquests mètodes no estan a la classr Session
                for(int k = Session.startHour; k < Session.startHour + Session.hoursPerDay; ++k) { //... i cada hora del dia tinc una sessio
                    UtilsDomain.Pair pair = new UtilsDomain.Pair();
                    pair.first = classroomValues.get(i); //el primer valor del pair es una classroom
                    pair.second = new Session(arr[j], k);

                    classroomSessionSet.add(pair);
                }
            }
        }
    }

    /**
     * ClassroomSession constructor by copy
     * @param cs ClassroomSession Object we want to replicate
     */
    public ClassroomSession(ClassroomSession cs) {
        this.classroomSessionSet = new ArrayList<>(cs.getClassroomSessionSet());
    }

    //GETTERS & SETTERS

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

    //METHODS

    /**
     * Getter of an specific classroomSesson from the classroomSessionSet attribute
     * @param id position of the pair we want
     * @return returns a the pair that was at the id position
     */
    public UtilsDomain.Pair getPair(int id) {
        return classroomSessionSet.get(id);
    }

    /**
     * returns the size of the classroomSessionSet
     * @return returns an int with the size of the classroomSessionSet
     */
    public int size(){
        return classroomSessionSet.size();
    }

    /**
     * Removes an specific Pair from classroomSessionSet
     * @param id of the specific Pair we want to delete
     */
    public void delete(int id) {
        classroomSessionSet.remove(id);
    }
}
