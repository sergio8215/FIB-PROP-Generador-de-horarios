package src.presentation;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NotPossibleSchedule extends JDialog {
    CtrlPresenter ctrlPresenter;

    private JPanel rootPanel;
    private JButton relaxConstraintsButton;

    public NotPossibleSchedule(CtrlPresenter ctrlPresenter) {
        this.ctrlPresenter = ctrlPresenter;

        add(rootPanel);
        setSize(400,500);
        initComponents();
    }

    private void initComponents(){
        relaxConstraintsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ctrlPresenter.notPossibleSchedule();
                setVisible(false);
                setEnabled(false);
            }
        });
    }
}
