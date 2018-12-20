package src.presentation;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Vector;

/**
 * Classrooms Manager UI Class.
 */
public class ClassroomsManager extends JDialog implements ListSelectionListener {
    private CtrlPresenter ctrlPresenter;

    private ArrayList<Vector<String>> classrooms;

    private JPanel rootPanel;
    private JPanel atributtesPanel;

    DefaultListModel<String> model = new DefaultListModel<>();
    private JList<String> list;

    private JTextArea identifierArea;
    private JTextArea typeOfClassroomArea;
    private JTextArea audiovisualsArea;
    private JTextArea numberOfComputersArea;
    private JTextArea maxNumberOfStudentsArea;
    private JButton saveSetOfClassroomsButton;
    private JButton deleteButton;
    private JButton addButton;
    private JPanel listPanel;

    private static final String identifierAreaString = "Identifier";
    private static final String numStudentsAreaString = "Max Number of Students";
    private static final String typeOfClassroomAreaString = "THEORY/LABORATORY";
    private static final String audiovisualAreaString = "true/false";
    private static final String numberOfComputersAreaString = "Number of Computers";


    /**
     * ClassroomsManager class constructor.
     * @param ctrlPresenter CtrlPresenter object, that makes the connection with the other classes through.
     * @param classrooms Set of classrooms loaded.
     */
    public ClassroomsManager(CtrlPresenter ctrlPresenter, Vector<Vector<String>> classrooms) {
        this.ctrlPresenter = ctrlPresenter;

        this.classrooms = new ArrayList<>(classrooms);

        if (classrooms.isEmpty()){
            Vector<String> v = new Vector<>(9);
            classrooms.add(0, v);
            for (int i = 0; i < 5; i++) {
                classrooms.get(0).add(i, "");
            }

            model.add(0,"CLASSROOM");
            list.setModel(model);
            list.setSelectedIndex(0);
        } else {
            initList(this.classrooms);
        }

        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.addListSelectionListener(this);

        initComponents();

        setSize(600,500);
        setTitle("CLASSROOMS MANAGER");

        add(rootPanel);

        rootPanel.setEnabled(true);
        rootPanel.setVisible(true);

    }

    /**
     * Initialize the list of classrooms.
     * @param classroom Set of classrooms.
     */
    public void initList(ArrayList<Vector<String>> classroom) {
        for(Vector<String> v : classroom) {
            model.addElement(v.get(0));
        }
        list.setModel(model);
    }

    /**
     * Initialize the components.
     */
    public void initComponents() {
        saveSetOfClassroomsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    JDialog d = new JDialog();

                    if (classrooms.isEmpty()) {
                        JOptionPane.showMessageDialog(d, "You don't have any classroom to save.");
                    } else {
                        if (valid()){
                            ctrlPresenter.saveClassroomSet(new Vector<>(classrooms));
                        } else {
                            JOptionPane.showMessageDialog(d, "Error.");
                        }
                    }
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
                classrooms.add(index, v);
                for (int i = 0; i < 9; i++) {
                    classrooms.get(index).add(i, "");
                }

                model.add(index,"CLASSROOM");
                list.setSelectedIndex(index);

                identifierArea.setText(identifierAreaString);
                maxNumberOfStudentsArea.setText(numStudentsAreaString);
                typeOfClassroomArea.setText(typeOfClassroomAreaString);
                audiovisualsArea.setText(audiovisualAreaString);
                numberOfComputersArea.setText(numberOfComputersAreaString);


            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = list.getSelectedIndex();
                list.setSelectedIndex(index+1);
                if (!classrooms.isEmpty()){
                    classrooms.remove(index);
                    model.remove(index);
                }

            }
        });

        identifierArea.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                if (!identifierArea.getText().equals(identifierAreaString)) {
                    classrooms.get(list.getSelectedIndex()).set(0, identifierArea.getText());
                    model.set(list.getSelectedIndex(), identifierArea.getText());
                }
            }
        });

        maxNumberOfStudentsArea.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                if (!maxNumberOfStudentsArea.getText().equals(numStudentsAreaString)) {
                    classrooms.get(list.getSelectedIndex()).set(1, maxNumberOfStudentsArea.getText());
                }
            }
        });

        typeOfClassroomArea.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                if (!typeOfClassroomArea.getText().equals(typeOfClassroomAreaString)) {
                    classrooms.get(list.getSelectedIndex()).set(2, typeOfClassroomArea.getText());
                }
            }
        });

        audiovisualsArea.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                if (!audiovisualsArea.getText().equals(audiovisualAreaString)) {
                    classrooms.get(list.getSelectedIndex()).set(3, audiovisualsArea.getText());
                }
            }
        });

        numberOfComputersArea.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                if (!numberOfComputersArea.getText().equals(numberOfComputersAreaString)) {
                    classrooms.get(list.getSelectedIndex()).set(4, numberOfComputersArea.getText());
                }
            }
        });

        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                ctrlPresenter.cleanClassroomsSet();
            }
        });

    }

    /**
     * Detects changes in the input data.
     * @param e
     */
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting() == false) {
            int index = list.getSelectedIndex();

            if (model.get(index).equals("CLASSROOM")){
                identifierArea.setText(identifierAreaString);
                maxNumberOfStudentsArea.setText(numStudentsAreaString);
                typeOfClassroomArea.setText(typeOfClassroomAreaString);
                audiovisualsArea.setText(audiovisualAreaString);
                numberOfComputersArea.setText(numberOfComputersAreaString);
            } else {
                identifierArea.setText(classrooms.get(index).get(0));
                maxNumberOfStudentsArea.setText(classrooms.get(index).get(1));
                typeOfClassroomArea.setText(classrooms.get(index).get(2));
                audiovisualsArea.setText(classrooms.get(index).get(3));
                numberOfComputersArea.setText(classrooms.get(index).get(4));
            }
        }
    }

    /**
     * Check the validity of the subject vectors.
     * @return True if valid.
     */
    public boolean valid(){
        for(Vector<String> v : classrooms) {
            if (v.get(0).equals("") ||
                    v.get(1).equals("") ||
                    v.get(2).equals("") ||
                    v.get(3).equals("") ||
                    v.get(4).equals(""))
                return false;
        }
        return true;
    }


}
