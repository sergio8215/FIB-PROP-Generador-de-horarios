/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.domain.controllers;

import src.domain.classes.*;
import src.persistence.DataManager;
import src.persistence.MUS;
import src.domain.utils.UtilsDomain;

import java.util.LinkedList;
import java.util.Vector;


/**
 *
 * @author Sergio & Mireia
 */
public class CtrlDomain {

    private DataManager dManager;
    private Schedule schedule;
    private ClassroomSet classroomsSet;
    private SubjectSetClass subjectsSet;
    private ClassSet classSet;
    private ClassroomSession classroomSession;
    private String classroomFile;
    private String subjectFile;


    /**
     * CtrlDomain constructor
     */
    public CtrlDomain(){
        dManager = new DataManager();
    }

    //PRIVATE METHODS

    /**
     * Method to import Classrooms from JSON file
     * @param file name of the file to import
     * @return true if the import is successful
     */
    private boolean importClassroom(String file){

        Vector<Vector<String >> classRooms = dManager.importClassrooms(file);
        if (classRooms == null) {
            classroomsSet = new ClassroomSet( classRooms );
            return true;
        }
        else return false;
    }

    /**
     * Method to import Subject from JSON file
     * @param file name of the file to import
     * @return true if the import is successful
     */
    private boolean importSubject(String file){
        Vector< Vector <String > > subjects = dManager.importSubjects(file);
        if (subjects != null){
            subjectsSet = new SubjectSetClass (subjects);
            return true;
        }
        else return false;
    }

    //PUBLIC METHODS

    /**
     * Creates the Scenario for the Schedule
     * @param classroomFile name of the Classroom file to import
     * @param subjectFile name of the subjects file to import
     * @return true if the import is successful
     */
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
     * List of all the subjects of the created Scenario
     * @return A list of subjects
     */
    public Vector<String> showSubject() {
        return subjectsSet.toString();
    }

    /**
     * List of all the Classrooms of the created Scenario
     * @return A list of Classrooms
     */
    public void showClassroom(){
        return classroomsSet.toString();
    }


   /* creemos que es opcional. queremos mover una asignatura de una hora/aula a otra
    public void manageUMH() {}
    */

    /**
     * Load a schedule from a file
     * @param scheduleFile name of the schedule file to import
     */
    public void loadSchedule(String scheduleFile) {
        schedule = new Schedule(dManager.loadSchedule(scheduleFile));
    }

    /**
     * Saves the generated schedule
     */
    public void saveSchedule() {
        dManager.saveSchedule(schedule.toString());
    }

    /**
     *  Show the generated schedule
     */
    public UtilsDomain.ResultOfQuery<> showSchedule() {
        UtilsDomain.ResultOfQuery s = new UtilsDomain.ResultOfQuery();
        s.queryTest = !schedule.empty();
        s.result = schedule;
        return s;
        //hacer cuando tengamos el schedule implementado
        //filtrar por quatris, asignaturas, horarios, etc
    }

    /**
     * Generates the scheduler based on the generated scenario
     */
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
