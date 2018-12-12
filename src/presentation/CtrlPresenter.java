package src.presentation;

import src.domain.controllers.CtrlDomain;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

import static javax.swing.JFrame.EXIT_ON_CLOSE;


/**
 * Presentation Controller
 * @author Joaquim Gomez
 */
public class CtrlPresenter {

    // Members

    private CtrlDomain ctrlDomain;
    private InitView initView;
    private DisplaySchedule dS;


    // Methods

    public CtrlPresenter() {
        ctrlDomain = new CtrlDomain();
        initView = new InitView(this);
        initView.setVisible(true);
    }

    public void setScenario(String classroomsFile, String subjectsFile) throws Exception {
        ctrlDomain.createScenario(classroomsFile, subjectsFile);
    }

    public void loadSchedule(String scheduleFile) throws Exception {
        initView.setVisibleF(false);
        initView.setEnabled(false);

        HashMap<String, ArrayList<Vector<String>>> h = ctrlDomain.loadSchedule(scheduleFile);

        UIManager.put("swing.boldMetal", Boolean.FALSE);
        DisplaySchedule.createAndShowGUI(h);
    }

    public void scheduleGeneration() {
        initView.setVisibleF(false);
        initView.setEnabled(false);

        HashMap<String, ArrayList<Vector<String>>> h = ctrlDomain.generateSchedule();

        UIManager.put("swing.boldMetal", Boolean.FALSE);
        DisplaySchedule.createAndShowGUI(h);


        //DisplaySchedule frame = new DisplaySchedule(this, h);
        //frame.setVisible(true);

        // TODO: VENTANA/BARRA DE PROGRESO/ALGO DE "GENERANDO HORARO"
        // TODO: GESTIONAR SCHEDUL GENERADO
        //Schedule sch = ctrlDomain.showSchedule().result;
    }

    public boolean moveSession(Vector<String> from, Vector<String> to) {
        return ctrlDomain.moveSession(from, to);
    }

}