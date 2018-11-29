package src.domain.controllers;

import src.domain.classes.ClassroomSession;
import src.domain.classes.Constraints;
import src.domain.classes.MUS;
import src.domain.classes.Schedule;
import src.domain.utils.UtilsDomain;

import java.util.LinkedList;


/**
 * Controller for the generation of schedules.
 * @author Joaquim Gomez & Mireia Cano
 */
public class CtrlScheduleGeneration {

    // Members

    private Schedule schedule;
    private ClassroomSession classroomSession;
    private LinkedList<MUS> vars; // PARA CADA MUS UNA VARIABLE DOMINIO (== classroomSesion correspondiente (flitrado))


    // Constructors

    /**
     * Basic Class constructor
     * @param crFile name of the file that is going to generate the Schedule's classrooms
     * @param sFile name of the file that is going to generate the Schedule's subjects
     */
    public CtrlScheduleGeneration(String crFile, String sFile) {
        schedule = new Schedule(crFile, sFile);
    }


    // Methods

    /**
     * Generates the schedule for a given set of MUSs (the variables of the assign a value from the domain).
     * @param vars Variables to assign a value from the domain.
     * @return Generates Schedule.
     */
    public Schedule generateSchedule(LinkedList<MUS> vars, ClassroomSession classroomSession) {
        this.vars = new LinkedList<MUS>(vars);
        this.classroomSession = classroomSession;

        UtilsDomain.ResultOfQuery<MUS> notPossible = filterUnaryConstraints(this.vars);

        if(!notPossible.queryTest) schedule = chronologicalBacktracking(new LinkedList<>(vars), schedule);
        return schedule;
    }

    /**
     * It filter unary restrictions.
     * @param vars Variables to be filtered.
     */
    private UtilsDomain.ResultOfQuery<MUS> filterUnaryConstraints(LinkedList<MUS> vars) {

        UtilsDomain.ResultOfQuery<MUS> notPossible = new UtilsDomain.ResultOfQuery<MUS>();

        for (int i = 0; i < vars.size(); i++) {

            vars.get(i).setDomain(new ClassroomSession(classroomSession));

            int j = 0;
            while (j < vars.get(i).domainSize()) {

                if (!(Constraints.sizeClassroomUnaryConstraint(vars.get(i), vars.get(i).getValueDomain(j)) &&
                        Constraints.typeClassroomUnaryConstraint(vars.get(i), vars.get(i).getValueDomain(j)) &&
                        Constraints.shiftClassUnaryConstraint(vars.get(i), vars.get(i).getValueDomain(j)))){
                    vars.get(i).deleteFromDomain(j);
                } else      ++j;

            }
            if(vars.get(i).domainSize() == 0) {
                notPossible.queryTest = true;
                notPossible.result = vars.get(i);
                return notPossible;
            }
        }
        notPossible.queryTest = false;
        return notPossible;
    }


    /**
     * Implementation of the Chronological Backtracking Algorithm for satisfaction of constraints.
     * @param futureVars Variables to which a domain value must be assigned.
     * @param solution Partial solution of the assignment of values to the variables.
     * @return Final solution (successful or not).
     */
    private Schedule chronologicalBacktracking(LinkedList<MUS> futureVars, Schedule solution) {
        int sum = futureVars.size() + solution.sizeTimetable();
        if (futureVars.isEmpty()) 	return solution;
        else {
            MUS currentVar = futureVars.pollFirst();

            for (int i = 0; i < currentVar.domainSize(); i++){ 	// i = id/posición pair classroom-sesion
                currentVar.assign(currentVar.getValueDomain(i));
                solution.add(currentVar);
                if (solution.valid()) {
                    solution = chronologicalBacktracking(futureVars, solution);

                    if (!solution.isFail()) {
                        return solution;
                    } else {
                        solution.delete(currentVar);
                        solution.setFail(false);
                    }

                } else {
                    solution.delete(currentVar);
                }
            }

            solution.fail();
            futureVars.add(0, currentVar);
            return solution;
        }
    }
}
