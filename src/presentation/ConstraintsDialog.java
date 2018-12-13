package src.presentation;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConstraintsDialog {
    private JCheckBox checkBox1;
    private JCheckBox checkBox2;
    private JCheckBox checkBox3;
    private JCheckBox restricción2CheckBox;
    private JCheckBox restricción1CheckBox;

    public ConstraintsDialog(){

        initComponents();

    }

    private void initComponents() {
        restricción1CheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(checkBox1.isSelected());
            }
        });
    }
}
