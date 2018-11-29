package src.presentation;

import src.domain.controllers.CtrlDomain;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SubmitFiles extends JFrame{
    private JButton selectClassroomsFileButton;
    private JButton selectSubjectsFileButton;
    private JLabel SelectScenarioLabel;
    private JLabel ClassroomsJSONFileLabel;
    private JLabel SubjectsJSONFileLabel;
    private JPanel rootPanel;

    public  SubmitFiles(){

        CtrlDomain controlDomain = new CtrlDomain();

        
        add(rootPanel);
        setTitle("Submit Files");
        setSize(400,500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // We add a Listener to make the button do something
        selectClassroomsFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fc = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter(
                        "JSON Files", "json");
                fc.setFileFilter(filter);
                int returnVal = fc.showOpenDialog(rootPanel);
                if(returnVal == JFileChooser.APPROVE_OPTION) {
                    System.out.println("You chose to open this file: " +
                            fc.getCurrentDirectory().getAbsolutePath()+
                            fc.getSelectedFile().getName());

                }
                controlDomain.createScenario();
            }
        });
        selectSubjectsFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fc = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter(
                        "JSON Files", "json");
                fc.setFileFilter(filter);
                int returnVal = fc.showOpenDialog(rootPanel);
                if(returnVal == JFileChooser.APPROVE_OPTION) {
                    System.out.println("You chose to open this file: " +
                            fc.getCurrentDirectory().getAbsolutePath()+
                            fc.getSelectedFile().getName());
                }
            }
        });
    }
}
