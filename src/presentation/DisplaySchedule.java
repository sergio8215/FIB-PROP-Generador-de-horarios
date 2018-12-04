package src.presentation;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

public class DisplaySchedule extends JFrame{

    private CtrlPresenter ctrlPresenter;

    private JPanel rootPanel;
    private JPanel tablePanel;
    private JTable jTable;


    public DisplaySchedule(CtrlPresenter ctrlPresenter, HashMap<String, ArrayList<Vector<Vector<String>>>> schedule) {
        this.ctrlPresenter = ctrlPresenter;

        int hoursPerDay = 12;
        int startHour = 8;
        int daysOfTheWeek = 5;
        String[] week = { "Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday" };

        add(rootPanel);
        setTitle("Schedule");
        setSize(800,600);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        tablePanel = new JPanel();

        String[] header = new String[daysOfTheWeek+1];
        header[0] = "Hour";

        // Initialize days of the week
        for(int i = 0; i < daysOfTheWeek ; i++ ){
            header[i+1] = week[i];
        }



        Object[][] data = new Object[][]{{"A","1"},{"B","2"},{"C","3"}};
        jTable = new JTable(data, header);
        tablePanel.add(new JScrollPane(jTable));
        getContentPane().add(tablePanel);
    }


}
