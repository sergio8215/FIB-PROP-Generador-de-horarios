package src.presentation;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Vector;

public class SubjectsManager extends JDialog implements ListSelectionListener {
    private CtrlPresenter ctrlPresenter;

    private ArrayList<Vector<String>> subjects;

    private JPanel rootPanel;
    private JPanel atributtesPanel;

    DefaultListModel<String> model = new DefaultListModel<>();
    private JList<String> list;

    private JLabel Name;
    private JTextArea nameArea;
    private JTextArea numStudentsArea;
    private JTextArea theoryHoursArea;
    private JTextArea laboratoryHoursArea;
    private JTextArea problemsHoursArea;
    private JTextArea numberOfGroupsArea;
    private JTextArea numberOfSubgrupsArea;
    private JTextArea shiftArea;
    private JPanel listPanel;
    private JTextArea levelArea;
    private JButton saveSubjectButton;
    private JButton saveSetOfSubjectsButton;
    private JButton deleteButton;
    private JButton addButton;

    private static final String nameAreaString = "Name";
    private static final String levelAreaString = "Level";
    private static final String numStudentsAreaString = "Number of Students";
    private static final String numberOfTheoryHoursAreaString = "Number of Theory Hours";
    private static final String numberOfLaboratoryHoursAreaString = "Number of Laboratory Hours";
    private static final String numberOfProblemsHoursAreaString = "Number of Problems Hours";
    private static final String numberOfGroupsAreaString = "Number of Groups";
    private static final String numberOfSubgroupsAreaString = "Number of Subgroups per Group";
    private static final String shiftAreaString = "MORNING/AFTERNOON/BOTH";


    public SubjectsManager(CtrlPresenter ctrlPresenter, Vector<Vector<String>> subjects) {
        this.ctrlPresenter = ctrlPresenter;

        this.subjects = new ArrayList<>(subjects);

        initList(this.subjects);

        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.addListSelectionListener(this);

        initComponents();

        setSize(600,500);
        setTitle("SUBJECTS MANAGER");

        add(rootPanel);

        rootPanel.setEnabled(true);
        rootPanel.setVisible(true);

    }

    public void initList(ArrayList<Vector<String>> subjects) {
        for(Vector<String> v : subjects) {
            model.addElement(v.get(0));
            list.setModel(model);
        }
    }


    public void initComponents() {
        saveSubjectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog soonDialog = new JDialog();

                int index = list.getSelectedIndex();

                if (!nameArea.getText().equals(nameAreaString) &&
                        !levelArea.getText().equals(levelAreaString) &&
                        !numStudentsArea.getText().equals(numStudentsAreaString) &&
                        !theoryHoursArea.getText().equals(numberOfTheoryHoursAreaString) &&
                        !laboratoryHoursArea.getText().equals(numberOfLaboratoryHoursAreaString) &&
                        !problemsHoursArea.getText().equals(numberOfProblemsHoursAreaString) &&
                        !numberOfGroupsArea.getText().equals(numberOfGroupsAreaString) &&
                        !numberOfSubgrupsArea.getText().equals(numberOfSubgroupsAreaString) &&
                        !shiftArea.getText().equals(shiftAreaString)) {
                    subjects.get(index).set(0, nameArea.getText());
                    subjects.get(index).set(1, levelArea.getText());
                    subjects.get(index).set(2, numStudentsArea.getText());
                    subjects.get(index).set(3, theoryHoursArea.getText());
                    subjects.get(index).set(4, laboratoryHoursArea.getText());
                    subjects.get(index).set(5, problemsHoursArea.getText());
                    subjects.get(index).set(6, numberOfGroupsArea.getText());
                    subjects.get(index).set(7, numberOfSubgrupsArea.getText());
                    subjects.get(index).set(8, shiftArea.getText());
                    JOptionPane.showMessageDialog(soonDialog, "Subject saved.");
                } else {
                    JOptionPane.showMessageDialog(soonDialog, "Change default fields.");
                }
            }
        });

        saveSetOfSubjectsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ctrlPresenter.saveSubjectSet(new Vector<>(subjects));
                } catch (Exception exc) {
                    System.out.println(exc);
                }

            }
        });

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = list.getSelectedIndex();


                Vector<String> v = new Vector<>(9);
                subjects.add(index, v);
                for (int i = 0; i < 9; i++) {
                    subjects.get(index).add(i, "");
                }

                model.add(index,"SUBJECT");
                list.setSelectedIndex(index);

                nameArea.setText(nameAreaString);
                levelArea.setText(levelAreaString);
                numStudentsArea.setText(numStudentsAreaString);
                theoryHoursArea.setText(numberOfTheoryHoursAreaString);
                laboratoryHoursArea.setText(numberOfLaboratoryHoursAreaString);
                problemsHoursArea.setText(numberOfProblemsHoursAreaString);
                numberOfGroupsArea.setText(numberOfGroupsAreaString);
                numberOfSubgrupsArea.setText(numberOfSubgroupsAreaString);
                shiftArea.setText(shiftAreaString);


            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = list.getSelectedIndex();
                list.setSelectedIndex(index+1);
                subjects.remove(index);
                model.remove(index);
            }
        });

        nameArea.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                if (!nameArea.getText().equals("Name")) {
                    model.set(list.getSelectedIndex(), nameArea.getText());
                }
            }
        });


    }

    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting() == false) {
            int index = list.getSelectedIndex();

            if (model.get(index).equals("SUBJECT")){
                nameArea.setText(nameAreaString);
                levelArea.setText(levelAreaString);
                numStudentsArea.setText(numStudentsAreaString);
                theoryHoursArea.setText(numberOfTheoryHoursAreaString);
                laboratoryHoursArea.setText(numberOfLaboratoryHoursAreaString);
                problemsHoursArea.setText(numberOfProblemsHoursAreaString);
                numberOfGroupsArea.setText(numberOfGroupsAreaString);
                numberOfSubgrupsArea.setText(numberOfSubgroupsAreaString);
                shiftArea.setText(shiftAreaString);
            } else {
                nameArea.setText(subjects.get(index).get(0));
                levelArea.setText(subjects.get(index).get(1));
                numStudentsArea.setText(subjects.get(index).get(2));
                theoryHoursArea.setText(subjects.get(index).get(3));
                laboratoryHoursArea.setText(subjects.get(index).get(4));
                problemsHoursArea.setText(subjects.get(index).get(5));
                numberOfGroupsArea.setText(subjects.get(index).get(6));
                numberOfSubgrupsArea.setText(subjects.get(index).get(7));
                shiftArea.setText(subjects.get(index).get(8));
            }
        }
    }
}
