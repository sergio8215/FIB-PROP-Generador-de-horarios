package src.presentation;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InitView extends JFrame {
    private CtrlPresenter vCtrlPresenter;

    private SubmitFiles submitFiles;

    private JPanel rootPanel;
    private JPanel initViewPanel;
    private JPanel scheduleManagerMenu;

    private JButton scheduleManagerButton;
    private JButton newScheduleButton;
    private JButton loadScheduleButton;

    private String scheduleFile;

    public InitView(CtrlPresenter ctrlPresenter) {
        vCtrlPresenter = ctrlPresenter;

        submitFiles = new SubmitFiles(vCtrlPresenter);

        initComponents();

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400,500);
        setTitle("SCHEDULE GENERATOR");

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
                submitFiles.setEnabled(true);

                submitFiles.setVisible(true);
            }
        });

        loadScheduleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fc = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter("JSON Files", "json");
                fc.setFileFilter(filter);
                int returnVal = fc.showOpenDialog(scheduleManagerMenu);
                if(returnVal == JFileChooser.APPROVE_OPTION) {
                    System.out.println("You chose to open this file: " +
                            fc.getCurrentDirectory().getAbsolutePath()+
                            fc.getSelectedFile().getName());
                    scheduleFile = fc.getCurrentDirectory().getAbsolutePath() + fc.getSelectedFile().getName();

                    try {
                        vCtrlPresenter.loadSchedule(scheduleFile);
                    } catch (Exception exc) {
                        System.out.println(exc);
                    }
                }
            }
        });
    }

    public void setVisibleF(boolean b) {
        submitFiles.setVisible(false);
        submitFiles.setEnabled(false);

        super.setVisible(false);
    }
}
