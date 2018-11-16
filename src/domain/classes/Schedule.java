package src.domain.classes;

import java.util.ArrayList;
import java.util.HashMap;

public class Schedule {

    //ATTRIBUTES

    private String classroomFile;
    private String subjectFile;
    private boolean correct;
    //ArrayList< ArrayList<MUS> > timetable;
    private HashMap<String, ArrayList<MUS> > timetable;
    public int countSchedule = 0;

    //matriz de asignaturas. Cada fila son los UMH de una asignatura concreta (ordenada por horas/dia)

    //CONSTRUCTOR

    /**
     * Empty Schedule constructor
     */
    public Schedule(){}

    /**
     * Simple schedule constructor
     * @param classroomFile name of the file with the classroom information
     * @param subjectFile name of the file with the subject information
     */
    public Schedule(String classroomFile, String subjectFile) {
        this.classroomFile = classroomFile;
        this.subjectFile = subjectFile;
        correct = true;
        timetable = new HashMap<String, ArrayList<MUS>>();
    }

    /**
     * Basic schedule constructor
     * @param classroomFile name of the file with the classroom information
     * @param subjectFile name of the file with the subject information
     * @param timetable HashMap with all the MUS values
     */
    public Schedule(String classroomFile, String subjectFile, HashMap<String, ArrayList<MUS>> timetable) {
        this.classroomFile = classroomFile;
        this.subjectFile = subjectFile;
        this.timetable = timetable;
    }

    /**
     * Schedule constructor by copy
     * @param sched Schedule Object we want to replicate
     */
    public Schedule(Schedule sched) {
        this.classroomFile = sched.getClassroomFile();
        this.subjectFile = sched.getSubjectFile();
        this.correct = !sched.isFail();
        this.timetable = new HashMap<String, ArrayList<MUS>>(sched.getTimetable());
    }

    //GETTERS & SETTERS

    /**
     * Getter of the classroomFile attribute
     * @return returns the name of the file with the classrooms' information
     */
    public String getClassroomFile() {
        return classroomFile;
    }

    /**
     * Setter of the classroomFile attribute
     * @param classroomFile name of a file with classrooms information
     */
    public void setClassroomFile(String classroomFile) {
        this.classroomFile = classroomFile;
    }

    /**
     * Getter of the subjectFile attribute
     * @return returns the name of the file with the subjects' information
     */
    public String getSubjectFile() {
        return subjectFile;
    }

    /**
     * Setter of the subjectFile attribute
     * @param subjectFile name of a file with subjects information
     */
    public void setSubjectFile(String subjectFile) {
        this.subjectFile = subjectFile;
    }

    /**
     * Getter of the correct attribute
     * @return returns if the Schedule is correct(false) or not(false)
     */
    public boolean isFail(){
        return !correct;
    }

    /**
     * Basic setter of the correct attribute
     * @param correct boolean that informs if the Schedule is correct(true) or not(false)
     */
    public void setFail(boolean correct) {
        this.correct = correct;
    }

    /**
     * Setter to false of the correct attribute
     */
    public Schedule fail(){
        correct = false;
        return this;
    }

    /**
     * Getter of the timetable attribute
     * @return returns the timetable of this schedule
     */
    public HashMap<String, ArrayList<MUS>> getTimetable() {
        return timetable;
    }

    /**
     * Setter of the timetable attribute
     * @param timetable hashmap that represents the timetable of a schedule
     */
    public void setTimetable(HashMap<String, ArrayList<MUS>> timetable) {
        this.timetable = timetable;
    }


    //PRIVATE METHODS

    /**
     * Adds the mus object into the correct position (sorted chronologically) fo the MUS ArrayList
     * @param v MUS ArrayList with MUSes sorted chronologically
     * @param mus mus that we want to add to v
     */
    private void addOrdered(ArrayList<MUS> v, MUS mus) {

        boolean found = false;
        for(int i = 0; i < v.size() && !found; ++i) {
            if(Session.compare(v.get(i).getSession(), ">=", mus.getSession())) {
                v.add(i, mus);  //si mus és més petit o igual que el element i, el poso a aquesta posició
                found = true;
            }
        }
    }

    /**
     * Binary search algorithm to find mus on an ArrayList
     * @param v ArrayList which contains(or not) the MUS Object
     * @param mus MUS Object to find
     * @return returns the index of the MUS Object, or -1 if it doesn't exists
     */
    private int findPosition(ArrayList<MUS> v, MUS mus) {
        for(int i = 0; i < v.size(); ++i) {
            if(v.get(i) == mus) return i;
        }
        return -1;
    }

    //PUBLIC METHODS

    /**
     * Indicates if the timetable attribute is empty(true) or not(false)
     * @return returns a boolean that indicates is the timetable is empty
     */
    public boolean isEmpty() {
        return timetable.isEmpty();
    }

    /**
     * Returns the size of the timetable attribute
     * @return returns the size of the timetable attribute
     */
    public int size(){
        int sum = 0;
        ArrayList< ArrayList<MUS> > musArray = new ArrayList<>(timetable.values());
        for (ArrayList<MUS> subArr : musArray) {
            sum += subArr.size();
        }
        return sum;
    }

    /**
     * Adds(and sorts) a MUS Object to the timetable attributed
     * @param mus MUS Object we need to add
     */
    public void add(MUS mus) {
        ArrayList<MUS> values = timetable.get(mus.getSubject().getName());
        if(values == null) {
            values = new ArrayList<MUS>();
            timetable.put(mus.getSubject().getName(), values);
            values.add(mus);
        }
        else if(values.size() == 0) values.add(mus);
        else addOrdered(values, mus);
        countSchedule++;
    }

    /**
     * Deletes a MUS Object from the timetable attribute
     * @param mus MUS OBject we need to delete
     * @return returns true if the element exists and false if it doesn't exists
     */
    public boolean delete(MUS mus){
        //TODO: comprovar que no funciona pel mus, no per altres merdes
        ArrayList<MUS> a = timetable.get(mus.getSubject().getName());
        int index = findPosition(a, mus);
        if (index == -1) return false;
        else {
            countSchedule--;
            a.remove(index);
            return true;
        }

    }

    /** Unset the HashMap
     * @return returns ArrayList with the classes of the ArrayList.
     * */
    public ArrayList<MUS> unset() {
        ArrayList< ArrayList<MUS> > musArray = new ArrayList<>(timetable.values());
        ArrayList<MUS> arrMUS = new ArrayList<>();
        for (ArrayList<MUS> subArr : musArray) {
            arrMUS.addAll(subArr);
        }
        return arrMUS;
    }

    /**
     *
     * @return
     */
    public boolean valid(){
        /*

        for(int i = 0; i < arrMUS.size();++i) {
            for (int j = i+1; j < arrMUS.size(); ++j) {
                if(!(Constraints.theoryAndLabsOfClassNoTogether(arrMUS.get(i), arrMUS.get(j)))
                ) return false;
            }
        }
        return true;
        */

        ArrayList<MUS> arrMUS = this.unset();

        for(int i = 0; i < arrMUS.size();++i) {
            for (int j = i+1; j < arrMUS.size(); ++j) {
                if(!(Constraints.notSameClassroomAndSession(arrMUS.get(i), arrMUS.get(j))))
                    return false;
            }
        }
        return true;
    }


/**
 &&
 Constraints.theorysOfSubjectsOfSameLevelNoTogether(arrMUS.get(i), arrMUS.get(j)) &&
 Constraints.theoryOfSubjectFromDifferentClassesNoTogether(arrMUS.get(i), arrMUS.get(j)) &&
 Constraints.LabsAndProblemsFromDifferentSubjectsOfSameGroupNoTogether(arrMUS.get(i), arrMUS.get(j))
 */
}
