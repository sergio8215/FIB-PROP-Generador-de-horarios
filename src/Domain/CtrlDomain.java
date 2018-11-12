/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.Domain;

import src.Data.DataManager;
import src.Data.MUS;

import java.util.LinkedList;
import java.util.Vector;


/**
 *
 * @author Sergio & mireia
 */
public class CtrlDomain {

    private DataManager dManager;
    private Schedule schedule;
    private ClassroomSetClass classroomsSet;
    private SubjectSetClass subjectsSet;
    private ClassSet classSet;
    private ClassroomSession classroomSession;
    private String classroomFile;
    private String subjectFile;

    public CtrlDomain(){
        dManager = new DataManager();
    }

    //PRIVATE METHODS

    private boolean importClassroom(String file){

        Vector<Vector<String >> classRooms = dManager.importClassrooms(file);
        if (classRooms == null) {
            classroomsSet = new ClassroomSetClass( classRooms );
            return true;
        }
        else return false;
    }

    private boolean importSubject(String file){
        Vector< Vector <String > > subjects = dManager.importSubjects(file);
        if (subjects != null){
            subjectsSet = new SubjectSetClass (subjects);
            return true;
        }
        else return false;
    }

    //PUBLIC METHODS

    public boolean createScenario(String classroomFile, String subjectFile) {
        boolean c = importClassroom(classroomFile);
        boolean s = importSubject(subjectFile);
        if (c && s ) {
            classSet = new ClassSet(subjectsSet);
            classroomSession = new ClassroomSession(classSet, new Session());
            this.classroomFile = classroomFile;
            this.subjectFile = subjectFile;
            return true;
        }
        else return false;
    }

    /**
     * list of all the subjects
     */
    public Vector<String> showSubject() {
        return subjectsSet.toString();
    }

    /**
     * list of all the classrooms
     */
    public void showClassroom(){
        return classroomsSet.toString();
    }


   /* creemos que es opcional. queremos mover una asignatura de una hora/aula a otra
    public void manageUMH() {}
    */

    public void loadSchedule(String scheduleFile) {
        schedule = new Schedule(dManager.loadSchedule(scheduleFile));
    }

    public void saveSchedule() {
        dManager.saveSchedule(schedule.toString());
    }

    public void showSchedule() {
        //hacer cuando tengamos el schedule implementado
        //filtrar por quatris, asignaturas, horarios, etc
    }

    public void generateSchedule(){
        CtrlScheduleGeneration ctrlScheduleGeneration = new CtrlScheduleGeneration(classroomFile, subjectFile);
        LinkedList<MUS> linkedList = new LinkedList<MUS>();
        ArrayList<ClassClass> cc = classSet.unset();
        for(int i = 0; i < cc.size(); ++i) {
            MUS mus = new MUS();
            mus.setClassClass(cc.get(i));
        }
        schedule = ctrlScheduleGeneration.generateSchedule(linkedList, classroomSession);
    }

}
