package src.presentation;

import src.domain.controllers.CtrlDomain;


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
    }

    public void setScenario(String classroomsFile, String subjectsFile) throws Exception {
        //ctrlDomain.createScenario(classroomsFile, subjectsFile);
    }

    public void loadSchedule(String scheduleFile) throws Exception {
        ctrlDomain.loadSchedule(scheduleFile);
    }

    public void scheduleGeneration() {
        initView.setVisible(false);
        initView.setEnabled(false);
        ctrlDomain.generateSchedule();
        // TODO: VENTANA/BARRA DE PROGRESO/ALGO DE "GENERANDO HORARO"
        // TODO: GESTIONAR SCHEDUL GENERADO
        //Schedule sch = ctrlDomain.showSchedule().result;
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