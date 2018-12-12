package src.presentation;

import javax.swing.*;

public class MainSubmitFiles {
    public static void main(String[] args) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        // Set system look and feel
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

        // Always run GUI code on the EDT (= Event Dispatch Thread)
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                CtrlPresenter ctrlPresenter = new CtrlPresenter();
                SubmitFiles submitFiles = new SubmitFiles(ctrlPresenter);
                submitFiles.setVisible(true);
            }
        });
    }
}
