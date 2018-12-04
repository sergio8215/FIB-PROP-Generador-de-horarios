package src.presentation;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

public class DisplaySchedule extends JFrame{

    private CtrlPresenter ctrlPresenter;

    private JPanel rootPanel;
    private JPanel headerPanel;
    private JTable panelTable;
    private JCheckBox checkBox1;
    private JCheckBox checkBox2;
    private JCheckBox checkBox3;
    private JButton guardarButton;
    private JPanel panelCheckbox;
    private JPanel panelButton;


    public DisplaySchedule(CtrlPresenter ctrlPresenter, HashMap<String, ArrayList<Vector<Vector<String>>>> schedule) {
        this.ctrlPresenter = ctrlPresenter;

        int hoursPerDay = 12;
        int startHour = 8;
        int daysOfTheWeek = 5;
        String[] week = { "Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday" };

        add(rootPanel);
        add(panelCheckbox);
        add(panelButton);

        pack();

        panelCheckbox.setEnabled(true);
        panelCheckbox.setVisible(true);

        panelButton.setEnabled(true);
        panelButton.setVisible(true);

        setTitle("Schedule");
        setSize(800,600);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        // Just for the header we create another panel
        headerPanel = new JPanel();

        String[] header = new String[daysOfTheWeek+1];
        header[0] = "Hour";

        // Initialize days of the week
        for(int i = 0; i < daysOfTheWeek ; i++ ){
            header[i+1] = week[i];
        }

        Object[][] data = new Object[][]{{"A","1","B","2","C","D"}};
        panelTable = new JTable(data, header);
        headerPanel.add(new JScrollPane(panelTable));
        getContentPane().add(headerPanel);
    }


}
