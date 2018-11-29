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
                SubmitFiles submitFiles = new SubmitFiles();
                submitFiles.setVisible(true);
            }
        });
    }
}
