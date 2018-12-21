package src.domain.classes;

import src.domain.controllers.CtrlDomain;

import java.util.*;

public class Schedule {

    //ATTRIBUTES

    private String classroomFile;
    private String subjectFile;
    private boolean fail;
    //ArrayList< ArrayList<MUS> > timetable;
    private HashMap<String, ArrayList<MUS> > timetable;

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
        fail = false;
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
        this.fail = sched.isFail();
        duplicateTimetable(sched.timetable);
    }



    /**
     * Schedule constructor from String
     * @param stringSchedule a list of Strings that represent the Schedule Object
     */
    public Schedule(List<String> stringSchedule){

        classroomFile = stringSchedule.get(5);
        subjectFile = stringSchedule.get(6);


        timetable = new HashMap<>();
        String[] separated;
        MUS myMUS;
        ArrayList<MUS> arrayMUS = new ArrayList<>();

        // We read the first class
        separated = stringSchedule.get(7).split("\\*");
        String subjectName = ClassClass.fromStr(new Vector(Arrays.asList(separated))).getSubject().getName();

        for( int i = 7; i < stringSchedule.size(); i+=3 ){

            // We read the class
            separated = stringSchedule.get(i).split("\\*");
            ClassClass classClass = ClassClass.fromStr(new Vector(Arrays.asList(separated)));

            // We read the classroom
            separated = stringSchedule.get(i+1).split("\\*");
            Classroom classroom = Classroom.fromStr(new Vector(Arrays.asList(separated)));

            // We read the session
            separated = stringSchedule.get(i+2).split("\\*");
            Session session = new Session(new Vector(Arrays.asList(separated)));
            myMUS = new MUS( classClass, classroom, session );

            // If the class before it's same subject we add it to the array list
            if ( subjectName.equalsIgnoreCase(classClass.getSubject().getName()) ){

                arrayMUS.add(myMUS);

            }else{  // if not we add it to the hashmap
                timetable.put(subjectName, arrayMUS );
                arrayMUS = new ArrayList<>();
                arrayMUS.add(myMUS);
                subjectName = classClass.getSubject().getName();
            }
        }
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
        return fail;
    }

    /**
     * Basic setter of the correct attribute
     * @param fail boolean that informs if the Schedule is correct(true) or not(false)
     */
    public void setFail(boolean fail) {
        this.fail = fail;
    }

    /**
     * Setter to false of the correct attribute
     */
    public void fail() {
        fail = true;
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

    /**
     * Getter of an specific MUS from the timetable
     * @param musID ID of the subgroup of the MUS we want
     * @param musSubject name of the Subject of the MUS we want
     * @param s session of the MUS we want
     * @return returns a MUS with the name and sessions specified
     */
    public MUS getMUS(String musID, String musSubject, Session s) {
        ArrayList<MUS> a = timetable.get(musSubject);
        for(int i = 0; i < a.size(); ++i) {
            if(a.get(i).getClassClass().getIdentifier().equals(musSubject+musID)
               && Session.compare(s, "==", a.get(i).getSession()))
                return a.get(i);
        }
        return null;
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
        if(!found) v.add(mus);
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

    /**
     * initializes the timetable attribute as a clone of t
     * @param t HashMap we want to recreate
     */
    private void duplicateTimetable(HashMap<String, ArrayList<MUS>> t) {
        timetable = new HashMap<String, ArrayList<MUS>> ();
        for(String s : t.keySet()) {
            ArrayList<MUS> aux = new ArrayList<>();
            for(int i = 0; i < t.get(s).size(); ++i) {
                aux.add( new MUS(t.get(s).get(i)));
            }
            timetable.put(s, aux);
        }
    }

    //PUBLIC METHODS

    /**
     * Indicates if the timetable attribute is empty(true) or not(false)
     * @return returns a boolean that indicates is the timetable is empty
     */
    public boolean isEmpty() {
        for (String k : timetable.keySet()){
            if(!timetable.get(k).isEmpty()) return false;
        }
        return true;
    }

    /**
     * Returns the timetableSize of the timetable attribute
     * @return returns the timetableSize of the timetable attribute
     */
    public int timetableSize(){
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
    }

    /**
     * Deletes a MUS Object from the timetable attribute
     * @param mus MUS OBject we need to delete
     * @return returns true if the element exists and false if it doesn't exists
     */
    public boolean delete(MUS mus){
        ArrayList<MUS> a = timetable.get(mus.getSubject().getName());
        int index = findPosition(a, mus);
        if (index == -1) return false;
        else {
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
     * Changes the session of a MUS from the timetable and adds it again in the correct position
     * @param m MUS we want to modify
     * @param s new Session for the MUS
     */
    public void changeSession(MUS m, Session s) {
        delete(m);
        m.setSession(s);
        add(m);
    }

    /**
     * It returns the set as a list (of strings) with the members of the elements of the set.
     * @return list (of strings) with the members of the elements of the set.
     */
    public List<String> toStr() {

        List<String> lines = new ArrayList<>();

        lines.add(classroomFile);
        lines.add(subjectFile);

        // I get an array list of MUS's
        for ( MUS mus: this.unset() ) {

            // For each mus I pass all his attributes to string

            Vector<Vector<String>> musAttributes;
            musAttributes = mus.toStr();
            StringBuilder result;

            // for each attribute of the MUS
            for (Vector<String> eachAttribute : musAttributes) {

                result = new StringBuilder();

                for (String objectAttribute : eachAttribute) {
                    result.append(objectAttribute).append("*");
                }
                lines.add(result.toString());

            }
        }
        return lines;
    }

    /**
     *
     * @return
     */
    public HashMap< String, ArrayList<Vector<String>> > toHashMapString() { // TODO: IMPLEMENTAR EN CTRLDOMAIN
        HashMap<String, ArrayList<Vector<String>>> timetable = new HashMap<>();

        Set<String> keys = this.timetable.keySet();

        for (String k : keys) {

            ArrayList<Vector<String>> setSubject = new ArrayList<>();
            int i = 0;
            for (MUS m : this.timetable.get(k)){
                Vector<String> vec = new Vector<>(5);

                vec.add(0, m.getClassClass().getSubject().getName());               // Subject name
                vec.add(1, Integer.toString(m.getClassClass().getSubGroup()));      // Subgroup (if it's theory will be same as group)
                vec.add(2, m.getClassroom().getName());                             // Classroom ID
                vec.add(3, Integer.toString(m.getSession().getHour()));             // Hour
                vec.add(4, Integer.toString(m.getSession().getDay().ordinal()));    // Day (ordinal)

                setSubject.add(i, vec);
                i++;
            }

            timetable.put(k, setSubject);
        }

        return timetable;
    }
}
