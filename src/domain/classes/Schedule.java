package src.domain.classes;

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
        this.timetable = new HashMap<String, ArrayList<MUS>>(sched.getTimetable());
    }


    public Schedule(List<String> stringSchedule){

        classroomFile = stringSchedule.get(0);
        subjectFile = stringSchedule.get(1);


        timetable = new HashMap<>();
        String[] separated;
        MUS myMUS;
        ArrayList<MUS> arrayMUS = new ArrayList<>();

        // We read the first class
        separated = stringSchedule.get(2).split("\\*");
        String subjectName = ClassClass.fromStr(new Vector(Arrays.asList(separated))).getSubject().getName();

        for( int i = 2; i < stringSchedule.size(); i+=3 ){

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

    //PUBLIC METHODS
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
     *
     * @return
     */
    public boolean valid(){

        ArrayList<MUS> arrMUS = this.unset();

        for(int i = 0; i < arrMUS.size();++i) {
            for (int j = i+1; j < arrMUS.size(); ++j) {
                if(!(Constraints.notSameClassroomAndSession(arrMUS.get(i), arrMUS.get(j)) &&
                        Constraints.theoryAndLabsOfClassNoTogether(arrMUS.get(i), arrMUS.get(j)) &&
                        Constraints.theorysOfSubjectsOfSameLevelNoTogether(arrMUS.get(i), arrMUS.get(j)) &&
                        Constraints.theoryOfSubjectFromDifferentClassesNoTogether(arrMUS.get(i), arrMUS.get(j)) &&
                        Constraints.LabsAndProblemsFromDifferentSubjectsOfSameGroupNoTogether(arrMUS.get(i), arrMUS.get(j))))
                    return false;
            }
        }
        return true;
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
    public HashMap< String, ArrayList<Vector<Vector<String>>> > toHashMapString() { // TODO: IMPLEMENTAR EN CTRLDOMAIN
        HashMap<String, ArrayList<Vector<Vector<String>>>> timetable = new HashMap<>();

        Set<String> keys = this.timetable.keySet();

        for (String k : keys) {
            ArrayList<Vector<Vector<String>>> setSubject = new ArrayList<>();

            for (MUS m : this.timetable.get(k)){
                setSubject.add(m.toStr());
            }

            timetable.put(k, setSubject);
        }

        return timetable;
    }

    /**
     *
     * @param from
     * @param to
     * @return
     */
    public boolean moveSession(Vector<String> from, Vector<String> to) { // TODO: FORMA MAS EFECTIVA
        MUS f = getMUSOf(from);
        MUS t = getMUSOf(to);

        this.delete(f);
        this.add(t);

        if (!this.valid()){
            delete(t);
            add(f);
            return false;
        }

        return true;
    }

    /**
     *
     * @param vs
     * @return
     */
    private MUS getMUSOf(Vector<String> vs) {
        // TODO: IMPLEMENTAR SEGUN COMO ESTAN LOS DATOS EN EL from Y EL to
    }
}
