package src.presentation;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

public class DisplaySchedule extends JFrame{

    private CtrlPresenter ctrlPresenter;

    private JPanel rootPanel;
    private JPanel headerPanel;
    private JTable panelTable;


    public DisplaySchedule(CtrlPresenter ctrlPresenter, HashMap<String, ArrayList<Vector<String>>> schedule) {
        this.ctrlPresenter = ctrlPresenter;

        int hoursPerDay = 12;
        int startHour = 8;
        int daysOfTheWeek = 5;
        String[] week = { "Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday" };

        add(rootPanel);
        rootPanel.add(panelTable,0);

        setTitle("Schedule");
        setSize(1000,1000);
        setLocationRelativeTo(null);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        // Just for the header we create another panel
        headerPanel = new JPanel();


        String[] header = new String[daysOfTheWeek+1];
        header[0] = "Hour";

        // Initialize days of the week
        for(int i = 0; i < daysOfTheWeek ; i++ ){
            header[i+1] = week[i];
        }

        int j;
        int i=0;
        Object[][] data = new Object[70][daysOfTheWeek+1];

        for (ArrayList<Vector< String>> subject : schedule.values()){
            for ( Vector< String> m : subject ){

                data[i][0] = m.get(3);                              // Hour
                int day = Integer.parseInt(m.get(4));               // Day (ordinal)
                data[i][day+1] = m.get(0) + " " + m.get(1) + " " + m.get(2); // Subject name, subgroup, classroom
                i++;
            }
        }

        TableModel model;
        model = new DefaultTableModel(data, header);

        panelTable = new JTable(model);
        headerPanel.add(new JScrollPane(panelTable, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS), BorderLayout.CENTER);
        getContentPane().add(headerPanel);
        pack();
        setVisible(true);

    }

}
