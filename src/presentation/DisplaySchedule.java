package src.presentation;

/*
 * DisplaySchedule.java requires no other files.
 */

import javafx.event.EventHandler;
import src.domain.classes.Schedule;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.Vector;

public class DisplaySchedule extends JPanel {

    private JCheckBox checkValidate, checkReValidate, checkRepaint, checkPack;
    private static JFrame f;
    private int daysOfTheWeek = 5;
    private HashMap<String, ArrayList<Vector<String>>> schedule;
    HashMap<String, ArrayList<Vector< String>>> filter;
    private boolean DEBUG = false;

    public DisplaySchedule(HashMap<String, ArrayList<Vector<String>>> schedule) {
        super(new GridLayout(0,1));
        this.schedule = schedule;
        filter = schedule;
        setPreferredSize(new Dimension(900, 500));

        //Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(makeSchedule(schedule));

        //Add the scroll pane to this panel.
        add(scrollPane,"Center");
        add(getCheckBoxPanel(this.schedule.keySet() ),"South");
    }

    private JPanel getCheckBoxPanel(Set<String> subjects) {

        JPanel panel2 = new JPanel();

        for ( String s:  subjects){
            checkValidate = new JCheckBox(s);
            checkValidate.setSelected(true);
            panel2.add(checkValidate);

            checkValidate.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent event) {
                    JCheckBox cb = (JCheckBox) event.getSource();
                    if (!cb.isSelected()) {
                        removeFilter(s);
                    } else {
                        
                    }
                }
            });
        }
        return panel2;
    }

    private JTable makeSchedule(HashMap<String, ArrayList<Vector<String>>> schedule){

        String[] week = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};

        String[] header = new String[daysOfTheWeek + 1];
        header[0] = "Hour";

        // Initialize days of the week
        for (int i = 0; i < daysOfTheWeek; i++) {
            header[i + 1] = week[i];
        }

        int i = 0;
        Object[][] data = new Object[200][daysOfTheWeek + 1];  // PONER CANTIDAD DE FILAS DINAMICAS

        for (ArrayList<Vector<String>> subject : schedule.values()) {
            for (Vector<String> m : subject) {

                data[i][0] = m.get(3);                              // Hour
                int day = Integer.parseInt(m.get(4));               // Day (ordinal)
                data[i][day + 1] = m.get(0) + " " + m.get(1) + " " + m.get(2); // Subject name, subgroup, classroom
                i++;
            }
        }

        JTable table = new JTable(data, header);
        table.setPreferredScrollableViewportSize(new Dimension(500, 70));
        table.setFillsViewportHeight(true);

        if (DEBUG) {
            table.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    printDebugData(table);
                }
            });
        }
        return table;
    }

    private void removeFilter(String subject){
        filter.remove(subject);
        createAndShowGUI(filter);
    }

    private void addFilter(String subject){
        filter.put(subject, schedule.get(subject));
        createAndShowGUI(filter);
    }

    private void printDebugData(JTable table) {
        int numRows = table.getRowCount();
        int numCols = table.getColumnCount();
        javax.swing.table.TableModel model = table.getModel();

        System.out.println("Value of data: ");
        for (int i=0; i < numRows; i++) {
            System.out.print("    row " + i + ":");
            for (int j=0; j < numCols; j++) {
                System.out.print("  " + model.getValueAt(i, j));
            }
            System.out.println();
        }
        System.out.println("--------------------------");
    }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    protected static void createAndShowGUI(HashMap<String, ArrayList<Vector<String>>> schedule) {
        //Create and set up the window.
        JFrame frame = new JFrame("DisplaySchedule");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f = frame;
        //Create and set up the content pane.
        DisplaySchedule newContentPane = new DisplaySchedule(schedule);
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                //createAndShowGUI();
            }
        });
    }
}