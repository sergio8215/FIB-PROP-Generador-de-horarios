package src.Domain;

import src.Domain.comparators.ClassroomCapacityComparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;

public class ClassroomSetClass {

    /**
     * Represents an association of classrooms of type LABORATORY of THEORY
     * @author mireia
     */

    //ATTRIBUTES
    private HashMap<String, ClassroomClass> classroomSet;
    private HashMap<String, TheoryClassroomClass> theoryClassroomSet;
    private HashMap<String, LabClassroomClass> labClassroomSet;

    //CONSTRUCTOR

    /**
     * ClassroomSetClass constructor
     * @param classroomSet
     */
    public ClassroomSetClass (HashMap<String, ClassroomClass> classroomSet){
        //TODO: puc copiar així el HashMap?? .clone() ??
        this.classroomSet = classroomSet;
        theoryClassroomSet = new HashMap<>();
        labClassroomSet = new HashMap<>();

        Iterator<ClassroomClass> iterator = classroomSet.values().iterator();
        while(iterator.hasNext()) {
            ClassroomClass cc = (ClassroomClass) iterator.next();
            if (cc.getClass().isAssignableFrom(TheoryClassroomClass.class)) {
                TheoryClassroomClass t = (TheoryClassroomClass) cc;
                theoryClassroomSet.put(t.getName(), t);
            }
            else {
                LabClassroomClass l = (LabClassroomClass) cc;
                labClassroomSet.put(l.getName(), l);
            }
        }
    }

    //TODO: fer més constructors

    //METHODS
    //TODO: obtenir una aula concreta pel nom
    //TODO: ajuntar una llista o un altre hashmap amb el que tinc (i actualitzar hashmaps de lab i teoria)
    //TODO: add a new classroom object (and add it to lab or theory set too)

    /**
     * Getter of the classroomSet attribute
     * @return returns a HashMap with all the classrooms sorted by name
     */
    public HashMap<String, ClassroomClass> getClassroomSet() {
        return classroomSet;
    }

    /**
     * Setter of the classroomSet attribute
     * @param classroomSet HashMap with all the classrooms sorted by name
     */
    public void setClassroomSet(HashMap<String, ClassroomClass> classroomSet) {
        this.classroomSet = classroomSet;
    }

    /**
     * Getter of the theoryClassroomSet attribute
     * @return returns a HashMap with all the theory classrooms sorted by name
     */
    public HashMap<String, TheoryClassroomClass> getTheoryClassroomSet() {
        return theoryClassroomSet;
    }

    /**
     * Setter of the theoryClassroomSet attribute
     * @param theoryClassroomSet HashMap with all the theory classrooms sorted by name
     */
    public void setTheoryClassroomSet(HashMap<String, TheoryClassroomClass> theoryClassroomSet) {
        this.theoryClassroomSet = theoryClassroomSet;
    }

    /**
     * Getter of the labClassroomSet attribute
     * @return returns a HashMap with all the laboratory classrooms sorted by name
     */
    public HashMap<String, LabClassroomClass> getLabClassroomSet() {
        return labClassroomSet;
    }

    /**
     * Setter of the labClassroomSet attribute
     * @param labClassroomSet HashMap with all the laboratory classrooms sorted by name
     */
    public void setLabClassroomSet(HashMap<String, LabClassroomClass> labClassroomSet) {
        this.labClassroomSet = labClassroomSet;
    }

    /**
     * It indicates if there is a classroom Object with name name
     * @param name name of a classroom that exists
     * @return returns true if there is a classroom Object with name name, false otherwise
     */
    public boolean exists (String name) {
        return classroomSet.containsKey(name);
    }

    /**
     * It indicates if there is a specific type of classroom Object with name name
     * @param name name of a classroom that exists
     * @param typeFlag indicates the type of the classroom
     * @return returns true if there is a classroom Object of type typeFlag and name name, false otherwise
     */
    public boolean exists (String name, int typeFlag){
        //TODO:enumeration o constants globals??
        if (typeFlag == UtilsDomain.ClassType.LABORATORY) {
            return labClassroomSet.containsKey(name);
        }
        else {
            return theoryClassroomSet.containsKey(name);
        }
    }

    /**
     * Getter of a classroom Object by its name
     * @param name name of a classroom that exists
     * @return returns the classroom Object with that name
     */
    public ClassroomClass getClassroom(String name) {
        return classroomSet.get(name);
    }

    /**
     * Getter of a specific type of classroom Object by its name
     * @param name name of a classroom that exists
     * @param typeFlag indicates the type of the classroom
     * @return returns the classroom Object with that name
     */
    public ClassroomClass getClassroom(String name, int typeFlag) {
        //TODO:enumeration o constants globals??
        if (typeFlag == UtilsDomain.ClassType.LABORATORY) {
            return labClassroomSet.get(name);
        }
        else {
            return theoryClassroomSet.get(name);
        }
    }

    /**
     *
     * @return List of all the classroom Objects sorted by ascendent capacity
     */
    public ArrayList<ClassroomClass> getCapacitySortSet(){
        ArrayList<ClassroomClass> classroomValues = new ArrayList<ClassroomClass>(classroomSet.values());
        Collections.sort(classroomValues, new ClassroomCapacityComparator());
        return classroomValues;
    }

    /**
     *
     * @return List of all the LabClassroom objects sorted by ascendent capacity
     */
    public ArrayList<ClassroomClass> getCapacitySortLabSet(){
        ArrayList<ClassroomClass> classroomValues = new ArrayList<ClassroomClass>(labClassroomSet.values());
        Collections.sort(classroomValues, new ClassroomCapacityComparator());
        return classroomValues;
    }

    /**
     *
     * @return List of all the TheoryClassroom objects sorted by ascendent capacity
     */
    public ArrayList<ClassroomClass> getCapacitySortTheorySet(){
        ArrayList<ClassroomClass> classroomValues = new ArrayList<ClassroomClass>(theoryClassroomSet.values());
        Collections.sort(classroomValues, new ClassroomCapacityComparator());
        return classroomValues;
    }

}
