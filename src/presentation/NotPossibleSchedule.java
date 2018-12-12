package src.presentation;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NotPossibleSchedule extends JDialog {
    private JPanel rootPanel;
    private JButton okButton;

    public NotPossibleSchedule() {
        add(rootPanel);
        setSize(400,500);
        initComponents();

    }


    private void initComponents(){
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }
}
