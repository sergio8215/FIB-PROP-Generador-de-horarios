package src.presentation;

import src.domain.controllers.CtrlDomain;
import src.domain.utils.UtilsDomain;

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
    private NotPossibleSchedule nps;


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
        int size = ctrlDomain.scheduleSize();

        UIManager.put("swing.boldMetal", Boolean.FALSE);
        new DisplaySchedule(h, size);
    }

    public void scheduleGeneration() {
        initView.setVisibleF(false);
        initView.setEnabled(false);

        ctrlDomain.generateSchedule();

        UtilsDomain.ResultOfQuery<HashMap<String, ArrayList<Vector<String>>>> h = ctrlDomain.showSchedule();

        if (h.queryTest) {
            UIManager.put("swing.boldMetal", Boolean.FALSE);
            int size = ctrlDomain.scheduleSize();
            new DisplaySchedule(h.result, size);


            //DisplaySchedule frame = new DisplaySchedule(this, h);
            //frame.setVisible(true);

            // TODO: VENTANA/BARRA DE PROGRESO/ALGO DE "GENERANDO HORARO"
            // TODO: GESTIONAR SCHEDUL GENERADO
            //Schedule sch = ctrlDomain.showSchedule().result;
        } else {
            nps = new NotPossibleSchedule();
            nps.setEnabled(true);
            nps.setVisible(true);
        }


    }

    public boolean moveSession(Vector<String> from, Vector<String> to) {
        return ctrlDomain.moveSession(from, to);
    }

}