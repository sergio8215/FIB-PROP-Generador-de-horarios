package src.Domain;

import java.util.ArrayList;
import java.util.HashMap;

public class Schedule {

    //ATTRIBUTES

    private String classroomFile;
    private String subjectFile;
    private boolean fail;
    //ArrayList< ArrayList<MUS> > timetable;
    private HashMap<String, ArrayList<MUS> > timetable;

    //matriz de asignaturas. Cada fila son los UMH de una asignatura concreta (ordenada por horas/dia)

    //CONSTRUCTOR

    public Schedule(String classroomFile, String subjectFile) {
        this.classroomFile = classroomFile;
        this.subjectFile = subjectFile;
        fail = true;
        timetable = new HashMap<String, ArrayList<MUS>>();
    }
    public Schedule(Schedule sched) {
        this.classroomFile = sched.getClassroomFile();
        this.subjectFile = sched.getSubjectFile();
        this.fail = !sched.isFail();
        this.timetable =  (HashMap<String, ArrayList<MUS> >)sched.getTimetable().clone();
    }

    //GETTERS & SETTERS

    public String getClassroomFile() {
        return classroomFile;
    }

    public void setClassroomFile(String classroomFile) {
        this.classroomFile = classroomFile;
    }

    public String getSubjectFile() {
        return subjectFile;
    }

    public void setSubjectFile(String subjectFile) {
        this.subjectFile = subjectFile;
    }

    public void setFail(boolean fail) {
        this.fail = fail;
    }

    public HashMap<String, ArrayList<MUS>> getTimetable() {
        return timetable;
    }

    public void setTimetable(HashMap<String, ArrayList<MUS>> timetable) {
        this.timetable = timetable;
    }


    //PRIVATE METHODS

    private void addOrdered(ArrayList<MUS> v, MUS mus) {

        for(int i = 0; i < v.size(); ++i) {
            //TODO: arreglar quan tinguis la funcio de la classe Session
            if(mus.compare(v.get(i))) v.add(i, mus);  //si mus és més petit o igual que el element i, el poso a aquesta posició
        }
    }

    //PUBLIC METHODS

    public void add(MUS mus) {
        ArrayList<MUS> values = timetable.get(mus.getSubject().getName());
        if(values == null) {
            values = new ArrayList<MUS>();
            timetable.put(mus.getSubject().getName(), values);
        }
        addOrdered(values, mus);
    }

    public void delete(MUS mus){
        //TODO: comprovar que no funciona pel mus, no per altres merdes
        timetable.get(mus.getSubject().getName()).remove(mus);
    }

    public boolean isFail(){
        return !fail;
    }

    public void fail(){
        fail = false;
    }

    public boolean valid(){

    }

}
