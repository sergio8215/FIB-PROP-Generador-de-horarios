package src.presentation;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NotPossibleSchedule extends JDialog {
    CtrlPresenter ctrlPresenter;

    private JPanel rootPanel;
    private JButton relaxConstraintsButton;

    /**
     * NotPossibleSchedule class constructor.
     * @param ctrlPresenter CtrlPresenter object, that makes the connection with the other classes through.
     */
    public NotPossibleSchedule(CtrlPresenter ctrlPresenter) {
        this.ctrlPresenter = ctrlPresenter;

        add(rootPanel);
        setSize(400,500);
        initComponents();
    }

    /**
     * Initialize the components.
     */
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
