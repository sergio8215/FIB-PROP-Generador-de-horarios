package src.presentation;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConstraintsDialog extends JDialog {
    private CtrlPresenter ctrlPresenter;

    private JCheckBox constraint3;
    private JCheckBox constraint2;
    private JCheckBox constraint1;
    private JCheckBox constraint0;
    private JButton continueButton;
    private JPanel rootPanel;

    private boolean[] enb = new boolean[4]; // TODO: NUMERO A CONCRETAR

    public ConstraintsDialog(CtrlPresenter ctrlPresenter){
        this.ctrlPresenter = ctrlPresenter;

        setSize(400,500);

        add(rootPanel);

        initComponents();
    }

    private void initComponents() {
        constraint0.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enb[0] = constraint0.isSelected();
            }
        });

        constraint1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enb[1] = constraint1.isSelected();
            }
        });

        constraint2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enb[2] = constraint2.isSelected();
            }
        });

        constraint3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enb[3] = constraint3.isSelected();
            }
        });


        continueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ctrlPresenter.setConstraints(enb);
                ctrlPresenter.scheduleGeneration();
            }
        });
    }
}
