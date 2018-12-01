package src.presentation;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InitView extends JFrame {
    private CtrlPresenter vCtrlPresenter;

    private JPanel rootPanel;
    private JPanel initViewPanel;
    private JPanel scheduleManagerMenu;

    private JButton scheduleManagerButton;
    private JButton newScheduleButton;
    private JButton loadScheduleButton;

    public InitView(CtrlPresenter ctrlPresenter) {
        vCtrlPresenter = ctrlPresenter;

        initComponents();

        add(rootPanel);
        add(initViewPanel);

        rootPanel.setEnabled(true);
        initViewPanel.setEnabled(true);

        rootPanel.setVisible(true);
        initViewPanel.setVisible(true);
    }

    private void initComponents() {
        scheduleManagerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                initViewPanel.setVisible(false);
                initViewPanel.setEnabled(false);

                add(scheduleManagerMenu);

                scheduleManagerMenu.setEnabled(true);
                scheduleManagerMenu.setEnabled(true);
            }
        });
        newScheduleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        loadScheduleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fc = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter("JSON Files", "json");
                fc.setFileFilter(filter);
                int returnVal = fc.showOpenDialog(scheduleManagerMenu); // TODO: A VARIABLE PARA EL CtrlPresenter
                if(returnVal == JFileChooser.APPROVE_OPTION) {
                    System.out.println("You chose to open this file: " +
                            fc.getCurrentDirectory().getAbsolutePath()+
                            fc.getSelectedFile().getName());

                }
            }
        });
    }
}
