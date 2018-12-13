package src.presentation;

/*
 * DisplaySchedule.java requires no other files.
 */

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

public class DisplaySchedule extends JPanel {

    private int size;
    private int daysOfTheWeek = 5;
    private int startHour = 8;
    private static JFrame frame;
    private JTable table;
    private JCheckBox checkValidate;
    private HashMap<String, ArrayList<Vector< String>>> filter;
    private HashMap<String, ArrayList<Vector< String>>> schedule;


    public DisplaySchedule(HashMap<String, ArrayList<Vector<String>>> schedule, int size) {
        super(new GridLayout(0,1));
        this.schedule = schedule;
        this.size = size;
        filter = (HashMap<String, ArrayList<Vector<String>>>) schedule.clone();

        //Create and set up the window.
        frame = new JFrame("DisplaySchedule");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.

        setPreferredSize(new Dimension(900, 500));

        //Create the scroll pane and add the table to it.
        table = new JTable(makeSchedule(schedule));
        table.setPreferredScrollableViewportSize(new Dimension(500, 70));
        table.setFillsViewportHeight(true);
        table.setAutoCreateRowSorter(true);

        TableColumn column = table.getColumnModel().getColumn(0);
        column.setPreferredWidth(5); //hour column is smaller

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        table.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );

        sortTable();

        JScrollPane scrollPane = new JScrollPane(table);

        //Add the scroll pane to this panel.
        add(scrollPane,"Center");
        add(getCheckBoxPanel(this.schedule.keySet() ),"South");

        this.setOpaque(true); //content panes must be opaque
        frame.setContentPane(this);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
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
                        addFilter(s);
                    }
                }
            });
        }
        return panel2;
    }

    private DefaultTableModel makeSchedule(HashMap<String, ArrayList<Vector<String>>> schedule){

        String[] week = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};

        String[] header = new String[daysOfTheWeek + 1];
        header[0] = "Hour";

        // Initialize days of the week
        for (int i = 0; i < daysOfTheWeek; i++) {
            header[i + 1] = week[i];
        }

        int i = 0;

        Object[][] data = new Object[scheduleSize(schedule.values())][daysOfTheWeek + 1];

        for (ArrayList<Vector<String>> subject : schedule.values()) {
            for (Vector<String> m : subject) {

                if ( startHour+i == Integer.parseInt(m.get(3)) ){

                }

                data[i][0] = Integer.parseInt(m.get(3));            // Hour
                int day = Integer.parseInt(m.get(4));               // Day (ordinal)
                data[i][day + 1] = m.get(0) + " " + m.get(1) + " " + m.get(2); // Subject name, subgroup, classroom
                i++;
            }
        }

        DefaultTableModel table = new DefaultTableModel(data, header) {
            @Override
            public Class getColumnClass(int column) {
                switch (column) {
                    case 0:
                        return Integer.class;
                    default:
                        return String.class;
                }
            }
        };
        return table;
    }

    private void removeFilter(String subject){
        filter.remove(subject);
        table.setModel(makeSchedule(filter));
        sortTable();
    }

    private void addFilter(String subject){
        filter.put(subject, schedule.get(subject));
        table.setModel(makeSchedule(filter));
        sortTable();
    }

    private void sortTable(){
        TableRowSorter<TableModel> sorter = new TableRowSorter<>(table.getModel());
        table.setRowSorter(sorter);
        List<RowSorter.SortKey> sortKeys = new ArrayList<>();

        int columnIndexToSort = 0;
        sortKeys.add(new RowSorter.SortKey(columnIndexToSort, SortOrder.ASCENDING));

        sorter.setSortKeys(sortKeys);
        sorter.sort();
    }

    private int scheduleSize(Collection<ArrayList<Vector< String>>> schedule){
        int count = 0;
        for ( ArrayList<Vector<String>> s: schedule) count += s.size();
        return count;
    }
}