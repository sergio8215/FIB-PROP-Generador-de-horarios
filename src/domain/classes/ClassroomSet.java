package src.domain.classes;

import src.domain.comparators.ClassroomCapacityComparator;
import src.domain.utils.UtilsDomain;

import java.util.*;

public class ClassroomSet {

    //constructror con solo Classrooms
    //

    /**
     * Represents an association of classrooms of type LABORATORY and THEORY
     * @author mireia
     */

    //ATTRIBUTES

    private HashMap<String, TheoryClassroom> theoryClassroomSet;
    private HashMap<String, LabClassroom> labClassroomSet;

    //CONSTRUCTOR

    /**
     * Empty ClassroomSet constructor
     */
    public ClassroomSet(){}

    /**
     * ClassroomSet Constructor
     * @param cc
     */
    public ClassroomSet(ArrayList<Classroom> cc) {
        theoryClassroomSet = new HashMap<>();
        labClassroomSet = new HashMap<>();

        for(int i = 0; i < cc.size(); i++) {
            Classroom classroom = cc.get(i);
            if(classroom.getType().ordinal() == UtilsDomain.ClassType.LABORATORY.ordinal()) {

                LabClassroom lab = (LabClassroom) classroom;
                labClassroomSet.put(lab.getName(), lab);
            }
            else {
                TheoryClassroom theo = (TheoryClassroom) classroom;
                theoryClassroomSet.put(theo.getName(), theo);
            }
        }
    }


    /**
     * ClassroomSet constructor
     * @param theory list which contains all the theory classrooms
     * @param lab list which contains all the laboratory classrooms
     */
    public ClassroomSet(ArrayList<TheoryClassroom> theory, ArrayList<LabClassroom> lab) {
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
     * ClassroomSet constructor from String
     * @param vec matrix of Strings that encodes the ClassroomSet Object
     */
    public ClassroomSet(Vector< Vector<String> > vec) {
        theoryClassroomSet = new HashMap<>();
        labClassroomSet = new HashMap<>();

        for(int i = 0; i < vec.size(); ++i) {
            Vector<String> v = vec.get(i);
            if(v.get(2).equals(UtilsDomain.ClassType.LABORATORY.toString())) {
                labClassroomSet.put(v.get(0), new LabClassroom(v));
            }
            else{
                theoryClassroomSet.put(v.get(0), new TheoryClassroom(v));
            }
        }
    }

    //GETTERS & SETTERS

    /**
     * Getter of the classroom values
     * @return returns the classroom Objects of the set
     */
    public ArrayList<Classroom> getClassroomValues(){
        ArrayList<Classroom> cc1 = new ArrayList<>(labClassroomSet.values());
        ArrayList<Classroom> cc2 = new ArrayList<>(theoryClassroomSet.values());
        return classroomUnion(cc1, cc2);
    }

    /**
     * Adds new Classroom Objects to our HashMaps
     * @param cc ArrayList of Classrooms to add
     */
    public void addClassroomSet(ArrayList<Classroom> cc) {
        for (Classroom aCc : cc) {
            if (aCc.getType() == UtilsDomain.ClassType.LABORATORY) {
                LabClassroom lab = (LabClassroom) aCc;
                labClassroomSet.put(lab.getName(), lab);
            } else {
                TheoryClassroom theo = (TheoryClassroom) aCc;
                theoryClassroomSet.put(theo.getName(), theo);
            }
        }
    }

    /**
     * Getter of the theoryClassroomSet attribute
     * @return returns an ArrayList with all the theory classrooms
     */
    public ArrayList<TheoryClassroom> getTheoryClassroomSet() {
        return new ArrayList<TheoryClassroom>(theoryClassroomSet.values());
    }

    /**
     * Setter of the theoryClassroomSet attribute
     * @param theory ArrayList with all the theory classrooms
     */
    public void addTheoryClassroomSet(ArrayList<TheoryClassroom> theory) {
        for (TheoryClassroom aTheory : theory) {
            theoryClassroomSet.put(aTheory.getName(), aTheory);
        }
    }

    /**
     * Getter of the labClassroomSet attribute
     * @return returns a HashMap with all the laboratory classrooms
     */
    public ArrayList<LabClassroom> getLabClassroomSet() {
        return new ArrayList<LabClassroom>(labClassroomSet.values());
    }

    /**
     * Setter of the labClassroomSet attribute
     * @param lab ArrayList with all the laboratory classrooms
     */
    public void addLabClassroomSet(ArrayList<LabClassroom> lab) {
        for (LabClassroom aLab : lab) {
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
    private LabClassroom getLabClassroom (String name) {
        return labClassroomSet.get(name);
    }

    /**
     *  Getter of a Theory Classroom Object by its name
     *  @param name name of a classroom
     *  @return returns the TheoryClassroom Object with that name, or null if it doesn't exist
     */
    private TheoryClassroom getTheoryClassroom (String name) {
        return theoryClassroomSet.get(name);
    }

    /**
     * Unites two ArrayList
     * @param cc1 ArrayList of Classroom Objects
     * @param cc2 ArrayList of Classroom Objects
     * @return returns a union of cc1 and cc2
     */
    private ArrayList<Classroom> classroomUnion(ArrayList<Classroom> cc1, ArrayList<Classroom> cc2) {
        ArrayList<Classroom> res = new ArrayList<Classroom>();
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

        Classroom lcc = getLabClassroom(name);
        if(lcc != null) {
            res.queryTest = true;
            res.result = lcc;
        }
        Classroom tcc = getTheoryClassroom(name);
        if(tcc != null) {
            res.queryTest = true;
            res.result = tcc;
        }

        return res;
    }

    /**
     * Transforms the ClassroomSet Object into a matrix of Strings
     * @return returns a Vector of Vectors of Strings with all the classroomSet attributes transformed into Strings
     */
    public Vector< Vector< String> > toStr() {
        int size = getNumClassrooms();
        Vector< Vector< String> > vec = new Vector< Vector< String> > (size);
        Iterator itLab = labClassroomSet.values().iterator();
        while(itLab.hasNext()) {
            LabClassroom lab = (LabClassroom)itLab.next();
            vec.add(lab.toStr());
        }

        Iterator itTheo = theoryClassroomSet.values().iterator();
        while(itTheo.hasNext()) {
            TheoryClassroom theo = (TheoryClassroom) itTheo.next();
            vec.add(theo.toStr());
        }
        return vec;
    }

}
