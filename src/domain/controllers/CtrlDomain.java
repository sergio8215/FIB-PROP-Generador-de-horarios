/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.domain.controllers;

import src.domain.classes.*;
import src.persistence.DataManager;
import src.domain.utils.UtilsDomain;

import java.io.IOException;
import java.util.*;

/**
 * CtrlDomain Class, it's part of one of the layers of the program. On charge of controlling the domain and data classes
 * @author Sergio Mazzariol & Mireia Cano
 */
public class CtrlDomain {

    private DataManager dManager;
    private Schedule schedule;
    private ClassroomSet classroomsSet;
    private SubjectsSet subjectsSet;
    private Constraints constraints;
    private ClassSet classSet;
    private ClassroomSession classroomSession;
    private String classroomFile;
    private String subjectFile;


    // CONSTRUCTORS----------------------------------------------------

    /**
     *  Class constructor specifying the member's values, initialize the data manager.
     */
    public CtrlDomain(){
        dManager = new DataManager();
        constraints = new Constraints();
    }

    //PRIVATE METHODS--------------------------------------------------

    /**
     * Method to import Classrooms from JSON file
     * @param file path of the file to import
     * @return true if the import is successful
     */
    private boolean importClassroom(String file) throws Exception{

        Vector<Vector<String >> classrooms = dManager.importClassrooms(file);
        if (classrooms != null) {
            classroomsSet = new ClassroomSet( classrooms );
            return true;
        }
        else return false;
    }

    /**
     * Method to import Subjects from JSON file
     * @param file path of the file to import
     * @return true if the import is successful
     */
    private boolean importSubject(String file) throws Exception{
        Vector< Vector <String > > subjects = dManager.importSubjects(file);
        if (subjects != null){
            subjectsSet = new SubjectsSet (subjects);
            return true;
        }
        else return false;
    }

    //PUBLIC METHODS------------------------------------------------------

    /**
     * Creates the Scenario for the Schedule
     * @param classroomFile path of the Classroom file to import
     * @param subjectFile path of the subjects file to import
     * @return true if the import is successful
     */
    public boolean createScenario(String classroomFile, String subjectFile) throws Exception {
        boolean c = importClassroom(classroomFile);
        boolean s = importSubject(subjectFile);
        if (c && s ) {
            classSet = new ClassSet(subjectsSet);
            classroomSession = new ClassroomSession(classroomsSet);
            this.classroomFile = classroomFile;
            this.subjectFile = subjectFile;
            return true;
        }
        else return false;
    }

    public boolean setSubjects(String subjectsFile) throws Exception {
        boolean s = importSubject(subjectsFile);
        if (s) {
            subjectFile = subjectsFile;
            return true;
        } else return false;
    }

    public boolean setClassrooms(String classroomsFile) throws Exception {
        boolean c = importClassroom(classroomsFile);
        if (c) {
            classroomsFile = classroomsFile;
            return true;
        } else return false;
    }

    /**
     * List of all the subjects of the created Scenario, one subject per position
     * @return A list of subjects
     */
    public Vector<Vector <String>> showSubject() {
        return subjectsSet.toStr();
    }

    /**
     * List of all the Classrooms of the created Scenario
     * @return A list of Classrooms
     */
    public Vector <Vector< String>> showClassroom(){
        return classroomsSet.toStr();
    }


    /**
     *  Shows the generated schedule
     */
    public UtilsDomain.ResultOfQuery<HashMap<String, ArrayList<Vector<String>>>> showSchedule() {
        UtilsDomain.ResultOfQuery s = new UtilsDomain.ResultOfQuery();
        s.queryTest = !schedule.isEmpty();
        s.result = schedule.toHashMapString();
        return s;
     }

    /**
     * Generates the schedule based on the generated scenario
     */
    public void generateSchedule(){
        CtrlScheduleGeneration ctrlScheduleGeneration = new CtrlScheduleGeneration(classroomFile, subjectFile);
        LinkedList<MUS> linkedList = new LinkedList<MUS>();
        ArrayList<ClassClass> cc = classSet.unset();
        for(int i = 0; i < cc.size(); ++i) {
            MUS mus = new MUS();
            mus.setClassClass(cc.get(i));
            linkedList.add(mus);
        }
        schedule = ctrlScheduleGeneration.generateSchedule(linkedList, classroomSession);
        //return schedule.toHashMapString();
    }

    /**
     * Saves the generated schedule on a file with the name that the user wants.
     * @param newFileName Name of the file to create
     * @throws Exception If file can't be created
     */
    public void saveSchedule( String newFileName) throws Exception {

        dManager.saveSchedule(newFileName, schedule.toStr());
    }

    /**
     * Saves the Subjects Set on a JSON file with the name that the user wants.
     * @param newFileName Name of the file to create
     * @throws Exception If file can't be created
     */
    public void saveSubjectSet( String newFileName, Vector<Vector<String>> subjectSetModif) throws Exception {

        dManager.saveSubjects(newFileName, subjectSetModif);
    }

    /**
     * Saves the Classroom Set on a JSON file with the name that the user wants.
     * @param newFileName Name of the file to create
     * @throws Exception If file can't be created
     */
    public void saveClassroomSet( String newFileName, Vector<Vector<String>> classroomSetModif) throws Exception {

        dManager.saveClassrooms(newFileName, classroomSetModif);
    }

    /**
     * Load a schedule from a file
     * @param filePath path of the schedule file to import
     * @throws IOException if file it's not found
     */
    public HashMap<String, ArrayList<Vector<String>>> loadSchedule(String filePath) throws IOException {
        schedule = new Schedule(dManager.loadSchedule(filePath));
        return schedule.toHashMapString();
    }

    public int scheduleSize(){
        return schedule.timetableSize();
    }

    public void setConstraints(boolean[] c){
        constraints.setContraints(c);
    }

    public Vector<Vector<String>> getSubjectsString() {
        return subjectsSet.toStr();
    }

    public Vector<Vector<String>> getClassroomsString() {
        return classroomsSet.toStr();
    }

    public void cleanSubjectsSet(){
        subjectsSet = new SubjectsSet();
    }

    public void cleanClassroomsSet() {
        classroomsSet = new ClassroomSet();
    }

    public boolean moveSession(Vector<String> from, Vector<String> to) {
        boolean emptyBox = false;
        if(to.get(0).equals("")) emptyBox = true;

        Session sTo = new Session(UtilsDomain.Day.values()[Integer.parseInt(to.get(4))], Integer.parseInt(to.get(3)));
        Session sFrom = new Session(UtilsDomain.Day.values()[Integer.parseInt(from.get(4))], Integer.parseInt(from.get(3)));

        Schedule schedToCheck = new Schedule(schedule);
        schedToCheck.changeSession(schedToCheck.getMUS(from.get(1), from.get(0), sFrom), sTo);
        if (!emptyBox) schedToCheck.changeSession(schedToCheck.getMUS(to.get(1), to.get(0), sTo), sFrom);

        return valida(schedToCheck);
    }

    private boolean valida(Schedule schd){
        ArrayList<MUS> arrMUS = schd.unset();

        for(int i = 0; i < arrMUS.size(); ++i) {
            for(int j = i + 1; j < arrMUS.size(); ++j) {
                if(!Constraints.satisfiesConstraints(arrMUS.get(i), arrMUS.get(j)))
                    return false;
            }
        }
        return true;
    }
}