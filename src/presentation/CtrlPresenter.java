package src.presentation;

import src.domain.controllers.CtrlDomain;
import src.domain.utils.UtilsDomain;

import javax.swing.*;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;


/**
 * Presentation Controller
 * @author Joaquim Gomez
 */
public class CtrlPresenter {

    // Members

    private CtrlDomain ctrlDomain;
    private InitView initView;
    private DisplaySchedule dS;
    private NotPossibleSchedule nps;
    private SubjectsManager sm;
    private ClassroomsManager cm;

    private Vector<Vector<String>> subjectsSet = new Vector<>();
    private Vector<Vector<String>> classroomsSet = new Vector<>();


    // Methods

    /**
     * CtrlPresenter class constructor.
     */
    public CtrlPresenter() {
        ctrlDomain = new CtrlDomain();
        initView = new InitView(this);
        initView.setVisible(true);
    }

    /**
     * Set scenario in the CtrlDomain.
     * @param classroomsFile Classrooms file.
     * @param subjectsFile Subjects File.
     * @throws Exception
     */
    public Boolean setScenario(String classroomsFile, String subjectsFile) throws Exception {
        if (!ctrlDomain.createScenario(classroomsFile, subjectsFile)){
            JDialog parentFrame = new JDialog();
            JOptionPane.showMessageDialog(parentFrame, "Classroom or Subject File empty, please try again.");
            return false;
        }
        return true;
    }

    /**
     * Set subjects in the CtrlDomain.
     * @param subjectsFile Subjects File.
     * @throws Exception
     */
    public void loadSubjects(String subjectsFile) throws Exception {
        ctrlDomain.setSubjects(subjectsFile);
    }

    /**
     * Set classrooms in the CtrlDomain.
     * @param classroomsFile Classrooms File.
     * @throws Exception
     */
    public void loadClassrooms(String classroomsFile) throws Exception {
        ctrlDomain.setClassrooms(classroomsFile);
    }

    /**
     * Shows the constraints panel.
     */
    public void selectConstraints() {
        initView.selectConstraints();
    }

    /**
     * Set constraints in the CtrlDomain.
     * @param sc Constraints.
     */
    public void setConstraints(boolean[] sc){
        ctrlDomain.setConstraints(sc);
    }

    /**
     * Set schedule in the CtrlDomain.
     * @param scheduleFile Schedule File.
     * @throws Exception
     */
    public void loadSchedule(String scheduleFile) throws Exception {
        initView.setVisibleF(false);
        initView.setEnabled(false);

        HashMap<String, ArrayList<Vector<String>>> h = ctrlDomain.loadSchedule(scheduleFile);

        UIManager.put("swing.boldMetal", Boolean.FALSE);
        new DisplaySchedule(this,h);
    }

    /**
     * Save the generated schedule.
     * @throws Exception
     */
    public void saveSchedule() throws Exception {
        // parent component of the dialog
        JFrame parentFrame = new JFrame();

        JFileChooser fileChooser = new JFileChooser();

        fileChooser.setDialogTitle("Specify a file to save");
        fileChooser.setCurrentDirectory(new java.io.File("./data/load"));
        int userSelection = fileChooser.showSaveDialog(parentFrame);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            System.out.println("Save as file: " + fileToSave.getAbsolutePath());
            ctrlDomain.saveSchedule(fileToSave.getAbsolutePath());
            JOptionPane.showMessageDialog(parentFrame, "Schedule saved.");
        }else{
            JOptionPane.showMessageDialog(parentFrame, "Error.");
        }
    }

    /**
     * Save the subjects.
     * @param subjectSet Set of subjects.
     * @throws Exception
     */
    public void saveSubjectSet(Vector<Vector<String>> subjectSet) throws Exception {
        // parent component of the dialog
        JFrame parentFrame = new JFrame();

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new java.io.File("./data/import"));
        fileChooser.setDialogTitle("Specify a file to save");

        int userSelection = fileChooser.showSaveDialog(parentFrame);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            System.out.println("Save as file: " + fileToSave.getAbsolutePath());
            ctrlDomain.saveSubjectSet(fileToSave.getAbsolutePath(), subjectSet);
            JOptionPane.showMessageDialog(parentFrame, "Schedule saved.");
        }else{
            JOptionPane.showMessageDialog(parentFrame, "Error.");
        }
    }

    /**
     * Save the classrooms.
     * @param classroomSet Set of classrooms.
     * @throws Exception
     */
    public void saveClassroomSet(Vector<Vector<String>> classroomSet) throws Exception {
        // parent component of the dialog
        JFrame parentFrame = new JFrame();

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new java.io.File("./data/import"));
        fileChooser.setDialogTitle("Specify a file to save");

        int userSelection = fileChooser.showSaveDialog(parentFrame);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            System.out.println("Save as file: " + fileToSave.getAbsolutePath());
            ctrlDomain.saveClassroomSet(fileToSave.getAbsolutePath(), classroomSet);
            JOptionPane.showMessageDialog(parentFrame, "Schedule saved.");
        }else{
            JOptionPane.showMessageDialog(parentFrame, "Error.");
        }
    }

    /**
     * Start the schedule generation.
     */
    public void scheduleGeneration() {
        initView.setVisibleF(false);
        initView.setEnabled(false);

        ctrlDomain.generateSchedule();

        UtilsDomain.ResultOfQuery<HashMap<String, ArrayList<Vector<String>>>> h = ctrlDomain.showSchedule();

        if (h.queryTest) {
            UIManager.put("swing.boldMetal", Boolean.FALSE);
            dS = new DisplaySchedule(this, h.result);
        } else {
            nps = new NotPossibleSchedule(this);
            nps.setEnabled(true);
            nps.setVisible(true);
        }

    }

    /**
     * Set the subjects set.
     */
    public void setSubjectsSet() {
        subjectsSet = ctrlDomain.getSubjectsString();
    }

    /**
     * Set the classrooms set.
     */
    public void setClassroomsSet() {
        classroomsSet = ctrlDomain.getClassroomsString();
    }

    /**
     * Enables the Subjects Manager
     */
    public void subjectsManagerEnabled() {
        sm = new SubjectsManager(this, subjectsSet);

        sm.setEnabled(true);
        sm.setVisible(true);
    }

    /**
     * Enables the Classrooms Manager
     */
    public void classroomsManagerEnabled() {
        cm = new ClassroomsManager(this, classroomsSet);

        cm.setEnabled(true);
        cm.setVisible(true);
    }

    /**
     * Cleans the subjects set.
     */
    public void cleanSubjectsSet() {
        subjectsSet = new Vector<>();
        ctrlDomain.cleanSubjectsSet();
    }

    /**
     * Cleans the classrooms set.
     */
    public void cleanClassroomsSet() {
        classroomsSet = new Vector<>();
        ctrlDomain.cleanClassroomsSet();
    }

    /**
     * It informs the view that the schedule is not possible.
     */
    public void notPossibleSchedule() {
        initView.selectConstraints();
    }

    /**
     * Back to the initial menu.
     */
    public void backToInit() {
        initView.setEnabled(true);
        initView.setVisible(true);
        dS.setEnabled(false);
        dS.setVisible(false);
    }

    /**
     * Report a movement.
     * @param from Session of origin.
     * @param to Destination session.
     * @return True if possible.
     */
    public boolean swapSession(Vector<String> from, Vector<String> to) {
        return ctrlDomain.swapSession(from, to);
    }

    /**
     * Report a movement.
     * @param from Session of origin.
     * @param to Destination session.
     * @return True if possible.
     */
    public boolean moveSession(Vector<String> from, Vector<String> to) {
        return ctrlDomain.moveSession(from, to);
    }

}