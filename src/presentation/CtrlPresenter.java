package src.presentation;

import src.domain.classes.MUS;
import src.domain.classes.Schedule;
import src.domain.controllers.CtrlDomain;

import java.util.*;


/**
 * Presentation Controller
 * @author Joaquim Gomez
 */
public class CtrlPresenter {

    // Members

    private CtrlDomain ctrlDomain;
    private InitView initView;


    // Methods

    public CtrlPresenter() {
        ctrlDomain = new CtrlDomain();
        initView = new InitView(this);
        initView.setVisible(true);
        //scheduleManagerMenu = new ScheduleManagerMenu(this);
    }


    public void setScheduleManagerMenu() {
        initView.setVisible(false);

    }




    public void setScenario() {
        //ArrayList<String> filesList = ctrlDomain.listImportFiles();
        //ctrlDomain.createScenario(/*FICHEROS DE ESCENARIO*/);
    }

    public void scheduleGeneration() {
        ctrlDomain.generateSchedule();
        Schedule sch = ctrlDomain.showSchedule().result;
    }



    /**
     * Print the schedule on the screen.
     * @param s Schedule to be printed.
     */
    /*private void showSchedule(Schedule s) {
        HashMap<String, ArrayList<MUS> > timetable = s.getTimetable();

        Set<String> keys = timetable.keySet();

        for (String nameSubject : keys) {
            System.out.println("\n SUBJECT - " + nameSubject.toUpperCase());

            ArrayList<MUS> elems = timetable.get(nameSubject);

            for (MUS m : elems) {
                System.out.println(m.getClassClass().getIdentifier() + ": " + m.getClassroom().getName() + " - " + m.getSession().getDay() + " " + m.getSession().getHour());
            }

            System.out.print("\n");
        }
    }*/

}