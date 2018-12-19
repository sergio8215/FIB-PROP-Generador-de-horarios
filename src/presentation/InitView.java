package src.presentation;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InitView extends JFrame {
    private CtrlPresenter vCtrlPresenter;

    private SubmitFiles submitFiles;
    private ConstraintsDialog constraintsDialog;

    private JPanel rootPanel;
    private JPanel initViewPanel;
    private JPanel scheduleManagerMenu;

    private JButton scheduleManagerButton;
    private JButton newScheduleButton;
    private JButton loadScheduleButton;
    private JButton subjectsManagerButton;
    private JButton classroomsManagerButton;
    private JPanel subjectsManagerMenu;
    private JButton newSubjectsFileButton;
    private JButton loadSubjectsFileButton;
    private JButton newClassroomsFileButton;
    private JButton loadClassroomsFileButton;
    private JPanel classroomsManagerMenu;
    private JButton backButton1;
    private JButton backButton3;
    private JButton backButton2;

    private String scheduleFile;
    private String subjectsFile;
    private String classroomsFile;

    public InitView(CtrlPresenter ctrlPresenter) {
        vCtrlPresenter = ctrlPresenter;

        submitFiles = new SubmitFiles(vCtrlPresenter);
        constraintsDialog = new ConstraintsDialog(vCtrlPresenter);

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
                FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
                fc.setFileFilter(filter);
                int returnVal = fc.showOpenDialog(scheduleManagerMenu);
                if(returnVal == JFileChooser.APPROVE_OPTION) {
                    System.out.println("You chose to open this file: " +
                            fc.getCurrentDirectory().getAbsolutePath()+
                            fc.getSelectedFile().getName());
                    scheduleFile = fc.getCurrentDirectory().getAbsolutePath()+ "/" + fc.getSelectedFile().getName();

                    try {
                        vCtrlPresenter.loadSchedule(scheduleFile);
                    } catch (Exception exc) {
                        System.out.println(exc);
                    }
                }
            }
        });

        subjectsManagerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                initViewPanel.setVisible(false);
                initViewPanel.setEnabled(false);

                add(subjectsManagerMenu);

                subjectsManagerMenu.setEnabled(true);
                subjectsManagerButton.setEnabled(true);
            }
        });

        classroomsManagerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                initViewPanel.setVisible(false);
                initViewPanel.setEnabled(false);

                add(classroomsManagerMenu);

                classroomsManagerButton.setEnabled(true);
                classroomsManagerButton.setEnabled(true);
            }
        });

        backButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                scheduleManagerMenu.setEnabled(false);
                scheduleManagerMenu.setEnabled(false);

                remove(scheduleManagerMenu);

                initViewPanel.setVisible(true);
                initViewPanel.setEnabled(true);
            }
        });

        backButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                subjectsManagerMenu.setEnabled(false);
                subjectsManagerMenu.setEnabled(false);

                remove(subjectsManagerMenu);

                initViewPanel.setVisible(true);
                initViewPanel.setEnabled(true);
            }
        });

        backButton3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                classroomsManagerMenu.setEnabled(false);
                classroomsManagerMenu.setEnabled(false);

                remove(classroomsManagerMenu);

                initViewPanel.setVisible(true);
                initViewPanel.setEnabled(true);
            }
        });

        loadSubjectsFileButton.addActionListener(new ActionListener() {
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
                    subjectsFile = fc.getCurrentDirectory().getAbsolutePath()+ "/" + fc.getSelectedFile().getName();

                    try {
                        vCtrlPresenter.loadSubjects(subjectsFile);
                    } catch (Exception exc) {
                        System.out.println(exc);
                    }
                }
            }

        });

        loadClassroomsFileButton.addActionListener(new ActionListener() {
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
                    classroomsFile = fc.getCurrentDirectory().getAbsolutePath()+ "/" + fc.getSelectedFile().getName();

                    try {
                        vCtrlPresenter.loadClassrooms(classroomsFile);
                    } catch (Exception exc) {
                        System.out.println(exc);
                    }
                }
            }
        });
    }

    public void setVisibleF(boolean b) {
        constraintsDialog.setVisible(false);
        constraintsDialog.setEnabled(false);

        super.setVisible(false);
    }

    public void selectConstraints(){
        submitFiles.setVisible(false);
        submitFiles.setEnabled(false);

        constraintsDialog.setEnabled(true);
        constraintsDialog.setVisible(true);
    }
}
