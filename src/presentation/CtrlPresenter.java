package src.presentation;

import src.domain.controllers.CtrlDomain;
import src.domain.utils.UtilsDomain;

import javax.swing.*;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

import static javax.swing.JFrame.EXIT_ON_CLOSE;


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


    // Methods

    public CtrlPresenter() {
        ctrlDomain = new CtrlDomain();
        initView = new InitView(this);
        initView.setVisible(true);
    }

    public void setScenario(String classroomsFile, String subjectsFile) throws Exception {
        ctrlDomain.createScenario(classroomsFile, subjectsFile);
    }

    public void loadSubjects(String subjectsFile) throws Exception {
        ctrlDomain.setSubjects(subjectsFile);
    }

    public void loadClassrooms(String classroomsFile) throws Exception {
        ctrlDomain.setClassrooms(classroomsFile);
    }

    public void selectConstraints() {
        initView.selectConstraints();
    }

    public void setConstraints(boolean[] sc){
        ctrlDomain.setConstraints(sc);
    }

    public void loadSchedule(String scheduleFile) throws Exception {
        initView.setVisibleF(false);
        initView.setEnabled(false);

        HashMap<String, ArrayList<Vector<String>>> h = ctrlDomain.loadSchedule(scheduleFile);

        UIManager.put("swing.boldMetal", Boolean.FALSE);
        new DisplaySchedule(this,h);
    }

    public void saveSchedule() throws Exception {
        // parent component of the dialog
        JFrame parentFrame = new JFrame();

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Specify a file to save");

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

    public void saveSubjectSet(Vector<Vector<String>> subjectSet) throws Exception {
        // parent component of the dialog
        JFrame parentFrame = new JFrame();

        JFileChooser fileChooser = new JFileChooser();
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

    public void scheduleGeneration() {
        initView.setVisibleF(false);
        initView.setEnabled(false);

        ctrlDomain.generateSchedule();

        UtilsDomain.ResultOfQuery<HashMap<String, ArrayList<Vector<String>>>> h = ctrlDomain.showSchedule();

        if (h.queryTest) {
            UIManager.put("swing.boldMetal", Boolean.FALSE);
            new DisplaySchedule(this, h.result);


            //DisplaySchedule frame = new DisplaySchedule(this, h);
            //frame.setVisible(true);

            // TODO: VENTANA/BARRA DE PROGRESO/ALGO DE "GENERANDO HORARO"
            // TODO: GESTIONAR SCHEDUL GENERADO
            //Schedule sch = ctrlDomain.showSchedule().result;
        } else {
            nps = new NotPossibleSchedule();
            nps.setEnabled(true);
            nps.setVisible(true);
        }

    }

    public void subjectsManagerEnabled() {
        sm = new SubjectsManager(this, ctrlDomain.getSubjectsString());
        // TODO: RECIBIR DATOS Y ENVIAR
        sm.setEnabled(true);
        sm.setVisible(true);
    }

    public boolean moveSession(Vector<String> from, Vector<String> to) {
        return ctrlDomain.moveSession(from, to);
    }

}