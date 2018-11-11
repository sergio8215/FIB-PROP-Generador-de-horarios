package src.Domain;

import src.Domain.comparators.ClassroomCapacityComparator;

import java.awt.*;
import java.util.*;

public class ClassroomSetClass {

    //constructror con solo Classrooms
    //

    /**
     * Represents an association of classrooms of type LABORATORY and THEORY
     * @author mireia
     */

    //ATTRIBUTES

    private HashMap<String, TheoryClassroomClass> theoryClassroomSet;
    private HashMap<String, LabClassroomClass> labClassroomSet;

    //CONSTRUCTOR

    /**
     * ClassroomSetClass Constructor
     * @param cc
     */
    public ClassroomSetClass(ArrayList<ClassroomClass> cc) {
        theoryClassroomSet = new HashMap<>();
        labClassroomSet = new HashMap<>();

        for(int i = 0; i < cc.size(); i++) {
            ClassroomClass classroom = cc.get(i);
            if(classroom.getType() == ClassroomClass.ClassroomType.LABORATORY) {

                LabClassroomClass lab = (LabClassroomClass) classroom;
                labClassroomSet.put(lab.getName(), lab);
            }
            else {
                TheoryClassroomClass theo = (TheoryClassroomClass) classroom;
                theoryClassroomSet.put(theo.getName(), theo);
            }
        }
    }


    /**
     * ClassroomSetClass constructor
     * @param theory list which contains all the theory classrooms
     * @param lab list which contains all the laboratory classrooms
     */
    public ClassroomSetClass(ArrayList<TheoryClassroomClass> theory, ArrayList<LabClassroomClass> lab) {
        theoryClassroomSet = new HashMap<>();
        labClassroomSet = new HashMap<>();

        boolean finishLab = false;
        int i = 0;
        while (i < theory.size() && i < lab.size()) {
            theoryClassroomSet.put(theory.get(i).getName(), theory.get(i));
            labClassroomSet.put(lab.get(i).getName(), lab.get(i));
            ++i;
            if (lab.size() == i) finishLab = true;
        }

        if (finishLab) {
            while (i < theory.size()) {
                theoryClassroomSet.put(theory.get(i).getName(), theory.get(i));
                ++i;
            }
        } else {
            while (i < lab.size()) {
                labClassroomSet.put(lab.get(i).getName(), lab.get(i));
                ++i;
            }
        }
    }

    /**
     * ClassroomSetClass constructor from String
     * @param vec matrix of Strings that encodes the ClassroomSetClass Object
     */
    public ClassroomSetClass(Vector< Vector<String> > vec) {
        theoryClassroomSet = new HashMap<>();
        labClassroomSet = new HashMap<>();

        for(int i = 0; i < vec.size(); ++i) {
            Vector<String> v = vec.get(i);
            if(v.get(2).equals("Laboratory")) {
                labClassroomSet.put(v.get(0), new LabClassroomClass(v));
            }
            else{
                theoryClassroomSet.put(v.get(0), new TheoryClassroomClass(v));
            }
        }
    }

    //GETTERS & SETTERS

    /**
     * Getter of the theoryClassroomSet attribute
     * @return returns an ArrayList with all the theory classrooms
     */
    public ArrayList<TheoryClassroomClass> getTheoryClassroomSet() {
        return new ArrayList<TheoryClassroomClass>(theoryClassroomSet.values());
    }

    /**
     * Setter of the theoryClassroomSet attribute
     * @param theory ArrayList with all the theory classrooms
     */
    public void setTheoryClassroomSet(ArrayList<TheoryClassroomClass> theory) {
        for (TheoryClassroomClass aTheory : theory) {
            theoryClassroomSet.put(aTheory.getName(), aTheory);
        }
    }

    /**
     * Getter of the labClassroomSet attribute
     * @return returns a HashMap with all the laboratory classrooms
     */
    public ArrayList<LabClassroomClass> getLabClassroomSet() {
        return new ArrayList<LabClassroomClass>(labClassroomSet.values());
    }

    /**
     * Setter of the labClassroomSet attribute
     * @param lab ArrayList with all the laboratory classrooms
     */
    public void setLabClassroomSet(ArrayList<LabClassroomClass> lab) {
        for (LabClassroomClass aLab : lab) {
            labClassroomSet.put(aLab.getName(), aLab);
        }
    }

    //PRIVATE METHODS

    /**
     * Checks if there is a LabClassroom Object with name name
     * @param name name of the laboratory classroom to check
     * @return returns true if the LabClassroom Object name exists, false otherwise
     */
    private boolean labExists(String name) {
        return labClassroomSet.containsKey(name);
    }

    /**
     * Checks if there is a TheoryClassroom Object with name name
     * @param name name of the theory classroom to check
     * @return returns true if the TheoryClassroom Object name exists, false otherwise
     */
    private boolean theoryExists(String name){
        return theoryClassroomSet.containsKey(name);
    }

    /**
     * Getter of a LabClassroom Object by its name
     * @param name name of a classroom
     * @return returns the LabClassroom Object with that name, or null if it doesn't exist
     */
    private LabClassroomClass getLabClassroom (String name) {
        return labClassroomSet.get(name);
    }

    /**
     *  Getter of a Theory Classroom Object by its name
     *  @param name name of a classroom
     *  @return returns the TheoryClassroom Object with that name, or null if it doesn't exist
     */
    private TheoryClassroomClass getTheoryClassroom (String name) {
        return theoryClassroomSet.get(name);
    }

    /**
     * Adds a LabClassroom Object to the HashMap of LabClassroom Objects
     * @param lcc LabClassroom Object
     */
    private void addClassroom (LabClassroomClass lcc) {
        labClassroomSet.put(lcc.getName(), lcc);
    }

    /**
     * Adds a TheoryClassroom Object to the HashMap of TheoryClassroom Objects
     * @param tcc TheoryClassroom Object
     */
    private void addClassroom (TheoryClassroomClass tcc) {
        theoryClassroomSet.put(tcc.getName(), tcc);
    }

    /**
     * Sorts a ClassroomClass ArrayList by its capacity
     * @param classroomValues ArrayList that has to be sorted
     * @return returns a sorted-by-capacity version of classroomValues
     */
    private ArrayList<ClassroomClass> sortByCapacity(ArrayList<ClassroomClass>classroomValues){
        Collections.sort(classroomValues, new ClassroomCapacityComparator());
        return classroomValues;
    }

    /**
     * Unites two ArrayList
     * @param cc1 ArrayList of Classroom Objects
     * @param cc2 ArrayList of Classroom Objects
     * @return returns a union of cc1 and cc2
     */
    private ArrayList<ClassroomClass> classroomUnion(ArrayList<ClassroomClass> cc1, ArrayList<ClassroomClass> cc2) {
        ArrayList<ClassroomClass> res = new ArrayList<ClassroomClass>();
        res.addAll(cc1);
        res.addAll(cc2);
        return res;
    }

    //PUBLIC METHODS

    /**
     * Counts how may classrooms do we have
     * @return returns the number of classrooms
     */
    public int getNumClassrooms(){
        return labClassroomSet.size() + theoryClassroomSet.size();
    }

    /**
     * It checks if there is a Classroom Object with name name
     * @param name name of a classroom to check
     * @return returns true if there is a classroom Object with name name, false otherwise
     */
    public boolean exists (String name) {
        return labExists(name) || theoryExists(name);
    }

    /**
     * Getter of a Classroom Object by its name
     * @param name name of a classroom
     * @return returns an Object with two elements. If the first is true, name exists and it's returned in the second element. If the first element is false, there's no such classroom object and the second element is irrelevant.
     */
    public UtilsDomain.ResultOfQuery getClassroom(String name) {
        UtilsDomain.ResultOfQuery res = new UtilsDomain.ResultOfQuery();
        res.queryTest = false;

        ClassroomClass lcc = getLabClassroom(name);
        if(lcc != null) {
            res.queryTest = true;
            res.resoult = lcc;
        }
        ClassroomClass tcc = getTheoryClassroom(name);
        if(tcc != null) {
            res.queryTest = true;
            res.resoult = tcc;
        }

        return res;
    }

    /**
     * Adds new Classroom Objects to our HashMaps
     * @param cc ArrayList of Classrooms to add
     */
    public void addClassroomSet(ArrayList<ClassroomClass> cc) {
        for (ClassroomClass aCc : cc) {
            if (aCc.getType() == ClassroomClass.ClassroomType.LABORATORY) {
                LabClassroomClass lab = (LabClassroomClass) aCc;
                labClassroomSet.put(lab.getName(), lab);
            } else {
                TheoryClassroomClass theo = (TheoryClassroomClass) aCc;
                theoryClassroomSet.put(theo.getName(), theo);
            }
        }
    }

    public ArrayList<ClassroomClass> getClassroomValues(){
        ArrayList<ClassroomClass> cc1 = new ArrayList<>(labClassroomSet.values());
        ArrayList<ClassroomClass> cc2 = new ArrayList<>(theoryClassroomSet.values());
        return classroomUnion(cc1, cc2);
    }

    /**
     * Sorts all the Classroom Objects by their capacity
     * @return List of all the classroom Objects sorted by ascendent capacity
     */
    public ArrayList<ClassroomClass> getCapacitySortSet(){
        ArrayList<ClassroomClass> cc1 = new ArrayList<>(labClassroomSet.values());
        ArrayList<ClassroomClass> cc2 = new ArrayList<>(theoryClassroomSet.values());
        ArrayList<ClassroomClass> classroomValues = classroomUnion(cc1, cc2);
        return sortByCapacity(classroomValues);
    }

    /**
     * Sorts the LabClassroom Objects by their capacity
     * @return List of all the LabClassroom objects sorted by ascendent capacity
     */
    public ArrayList<ClassroomClass> getCapacitySortLabSet(){
        ArrayList<ClassroomClass> classroomValues = new ArrayList<ClassroomClass>(labClassroomSet.values());
        return sortByCapacity(classroomValues);
    }

    /**
     * Sorts the TheoryClassroom Objects by their capacity
     * @return List of all the TheoryClassroom objects sorted by ascendent capacity
     */
    public ArrayList<ClassroomClass> getCapacitySortTheorySet(){
        ArrayList<ClassroomClass> classroomValues = new ArrayList<ClassroomClass>(theoryClassroomSet.values());
        return sortByCapacity(classroomValues);
    }

    /**
     * Transforms the ClassroomSetClass Object into a matrix of Strings
     * @return returns a Vector of Vectors of Strings with all the classroomSet attributes transformed into Strings
     */
    public Vector< Vector< String> > toStr() {
        int size = getNumClassrooms();
        Vector< Vector< String> > vec = new Vector< Vector< String> > (size);
        Iterator itLab = labClassroomSet.values().iterator();
        while(itLab.hasNext()) {
            LabClassroomClass lab = (LabClassroomClass)itLab.next();
            vec.add(lab.toStr());
        }

        Iterator itTheo = theoryClassroomSet.values().iterator();
        while(itTheo.hasNext()) {
            TheoryClassroomClass theo = (TheoryClassroomClass) itTheo.next();
            vec.add(theo.toStr());
        }
        return vec;
    }

}
