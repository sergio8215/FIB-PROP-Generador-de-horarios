package src.presentation;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * ConstraintsDialog allows the user to select the restrictions to comply with.
 */
public class ConstraintsDialog extends JDialog {
    private CtrlPresenter ctrlPresenter;

    private JCheckBox constraint3;
    private JCheckBox constraint2;
    private JCheckBox constraint1;
    private JCheckBox constraint0;
    private JButton continueButton;
    private JPanel rootPanel;
    private JCheckBox constraint4;
    private JButton selectAllButton;

    private boolean[] enb = new boolean[5]; // TODO: NUMERO A CONCRETAR

    /**
     * ConstraintsDialog class constructor.
     * @param ctrlPresenter CtrlPresenter object, that makes the connection with the other classes through.
     */
    public ConstraintsDialog(CtrlPresenter ctrlPresenter){
        this.ctrlPresenter = ctrlPresenter;

        setSize(400,500);

        add(rootPanel);

        initComponents();

    }

    /**
     * Initialize the components.
     */
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

        constraint4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enb[4] = constraint4.isSelected();
            }
        });


        selectAllButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                constraint0.setSelected(true);
                constraint1.setSelected(true);
                constraint2.setSelected(true);
                constraint3.setSelected(true);
                constraint4.setSelected(true);
                enb[0] = constraint0.isSelected();
                enb[1] = constraint1.isSelected();
                enb[2] = constraint2.isSelected();
                enb[3] = constraint3.isSelected();
                enb[4] = constraint4.isSelected();
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
