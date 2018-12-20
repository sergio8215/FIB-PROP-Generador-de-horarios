package src.presentation;

import src.domain.controllers.CtrlDomain;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.util.List;

/**
 * SubmitFiles Class allow us to submit files to the program
 */
public class SubmitFiles extends JDialog {
    private CtrlPresenter vCtrlPresenter;
    private DisplaySchedule dS;
    private List<String> schedule;
    private JButton selectClassroomsFileButton;
    private JButton selectSubjectsFileButton;
    private JLabel SelectScenarioLabel;
    private JLabel ClassroomsJSONFileLabel;
    private JLabel SubjectsJSONFileLabel;
    private JPanel rootPanel;
    private JButton nextbutton;

    private String classroomsFile;
    private String subjectsFile;

    /**
     * SubmitFiles constructor for the class
     * @param ctrlPresenter CtrlPresenter object, that makes the connection with the other classes through CtrlDomain
     */
    public SubmitFiles(CtrlPresenter ctrlPresenter){

        vCtrlPresenter = ctrlPresenter;

        add(rootPanel);
        setTitle("Submit Files");
        setSize(400,500);

        // We add a Listener to make the button do something
        selectClassroomsFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fc = new JFileChooser();
                fc.setCurrentDirectory(new java.io.File("./data/import"));
                FileNameExtensionFilter filter = new FileNameExtensionFilter(
                        "JSON Files", "json");
                fc.setFileFilter(filter);
                int returnVal = fc.showOpenDialog(rootPanel);
                if(returnVal == JFileChooser.APPROVE_OPTION) {
                    System.out.println("You chose to open this file: " +
                            fc.getCurrentDirectory().getAbsolutePath()+ "/" +
                            fc.getSelectedFile().getName());
                    classroomsFile = fc.getCurrentDirectory().getAbsolutePath()+ "/" + fc.getSelectedFile().getName();
                    selectClassroomsFileButton.setForeground(Color.green);
                } else {
                    selectClassroomsFileButton.setForeground(Color.red);
                }

            }
        });
        selectSubjectsFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fc = new JFileChooser();
                fc.setCurrentDirectory(new java.io.File("./data/import"));
                FileNameExtensionFilter filter = new FileNameExtensionFilter(
                        "JSON Files", "json");
                fc.setFileFilter(filter);
                int returnVal = fc.showOpenDialog(rootPanel);
                if(returnVal == JFileChooser.APPROVE_OPTION) {
                    System.out.println("You chose to open this file: " +
                            fc.getCurrentDirectory().getAbsolutePath()+ "/" +
                            fc.getSelectedFile().getName());
                    subjectsFile = fc.getCurrentDirectory().getAbsolutePath()+ "/" + fc.getSelectedFile().getName();
                    selectSubjectsFileButton.setForeground(Color.green);
                }else {
                    selectClassroomsFileButton.setForeground(Color.red);
                }
            }
        });



        nextbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ctrlPresenter.setScenario(classroomsFile, subjectsFile);
                    ctrlPresenter.selectConstraints();
                    //ctrlPresenter.scheduleGeneration();


                } catch (Exception exc) {
                    System.out.println(exc);
                }
            }
        });
    }
}