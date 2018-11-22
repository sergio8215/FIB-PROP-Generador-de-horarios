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
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Vector;

/**
 * CtrlDomain Class, it's part of one of the layers of the program. On charge of controlling the domain and data classes
 * @author Sergio Mazzariol & Mireia Cano
 */
public class CtrlDomain {

    private DataManager dManager;
    private Schedule schedule;
    private ClassroomSet classroomsSet;
    private SubjectsSet subjectsSet;
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
    }

    //PRIVATE METHODS--------------------------------------------------

    /**
     * Method to import Classrooms from JSON file
     * @param file name of the file to import
     * @return true if the import is successful
     */
    private boolean importClassroom(int file) throws Exception{

        Vector<Vector<String >> classrooms = dManager.importClassrooms(file);
        if (classrooms != null) {
            classroomsSet = new ClassroomSet( classrooms );
            return true;
        }
        else return false;
    }

    /**
     * Method to import Subjects from JSON file
     * @param file name of the file to import
     * @return true if the import is successful
     */
    private boolean importSubject(int file) throws Exception{
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
     * @param classroomFile name of the Classroom file to import
     * @param subjectFile name of the subjects file to import
     * @return true if the import is successful
     */
    public boolean createScenario(int classroomFile, int subjectFile) throws Exception {
        boolean c = importClassroom(classroomFile);
        boolean s = importSubject(subjectFile);
        if (c && s ) {
            classSet = new ClassSet(subjectsSet);
            classroomSession = new ClassroomSession(classroomsSet);
            this.classroomFile = listImportFiles().get(classroomFile);
            this.subjectFile = listImportFiles().get(subjectFile);
            return true;
        }
        else return false;
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
    public UtilsDomain.ResultOfQuery<Schedule> showSchedule() {
        UtilsDomain.ResultOfQuery s = new UtilsDomain.ResultOfQuery();
        s.queryTest = !schedule.isEmpty();
        s.result = schedule;
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
    }

    /**
     * Saves the generated schedule on a file with the name that the user wants.
     * @param newFileName Name of the file to create
     * @param schedule Schedule to save
     * @throws Exception If file can't be created
     */
    public void saveSchedule( String newFileName, Schedule schedule ) throws Exception {

        dManager.saveSchedule(newFileName, schedule);
    }

    /**
     * List of schedule saved files.
     * @return A list of files
     */
    public ArrayList<String> listScheduleFiles(){
        return dManager.listScheduleFiles();
    }

    /**
     * List of import files.
     * @return A list of files
     */
    public ArrayList<String> listImportFiles(){
        return dManager.listImportFiles();
    }


    /**
     * Load a schedule from a file
     * @param fileNum Number of the schedule file to import
     * @throws IOException if file it's not found
     */
    public void loadSchedule(int fileNum) throws IOException {
        schedule = dManager.loadSchedule(fileNum);
    }
}






