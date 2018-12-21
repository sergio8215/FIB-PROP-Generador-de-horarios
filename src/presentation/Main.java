package src.presentation;

import src.presentation.CtrlPresenter;

/**
 * Main Class.
 * @author Joaquim Gomez
 */
public class Main {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                CtrlPresenter ctrlPresenter = new CtrlPresenter();
            }
        });
    }
}
