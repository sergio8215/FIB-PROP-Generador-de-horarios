package src.domain.controllers;

import src.domain.classes.ClassroomSession;
import src.domain.classes.Constraints;
import src.domain.classes.MUS;
import src.domain.classes.Schedule;
import src.domain.utils.UtilsDomain;

import java.util.LinkedList;


/**
 * Controller for the generation of schedules.
 * @author Joaquim Gomez y Mireia Cano
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
     * @param classroomSession Classroom-Session pairs.
     * @return Generates Schedule.
     */
    public Schedule generateSchedule(LinkedList<MUS> vars, ClassroomSession classroomSession) {
        this.vars = new LinkedList<MUS>(vars);
        this.classroomSession = classroomSession;

        UtilsDomain.ResultOfQuery<MUS> notPossible = filterUnaryConstraints(this.vars);

        if(!notPossible.queryTest) schedule = forwardChecking(new LinkedList<>(vars), schedule);
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
     * Forward Checking Algorithm.
     * @param futureVars Future variables.
     * @param solution Solution.
     * @return Solution Schedule.
     */
    private Schedule forwardChecking(LinkedList<MUS> futureVars, Schedule solution) {
        if (futureVars.isEmpty()) 	return solution;
        else {
            MUS currentVar = futureVars.pollFirst();
            for (int i = 0; i < currentVar.domainSize(); i++) {
                LinkedList<MUS> copy =  duplicateFutureVars(futureVars);
                currentVar.assign(currentVar.getValueDomain(i));
                solution.add(currentVar);
                propagateConstraints(futureVars, currentVar);

                if (!someDomainEmpty(futureVars)) {
                    solution = forwardChecking(futureVars, solution);

                    if (!solution.isFail()) 	return solution;
                    else {
                        solution.delete(currentVar);
                        solution.setFail(false);
                    }
                } else {
                    solution.delete(currentVar);
                    currentVar.setPaired(false);
                    futureVars = copy;
                }
            }

            solution.fail();
            futureVars.add(0, currentVar);
            return solution;
        }
    }

    /**
     * It propagates the constraints.
     * @param futureVars Future variables.
     * @param currentVar Current variable.
     */
    private void propagateConstraints(LinkedList<MUS> futureVars, MUS currentVar) {
        for (MUS var : futureVars) {
            if (!var.getClassClass().getIdentifier().equals(currentVar.getClassClass().getIdentifier())) {
                int i = 0;
                while (i < var.domainSize()) {
                    var.assign(var.getValueDomain(i));
                    if (!Constraints.satisfiesConstraints(currentVar, var)) {
                        var.deleteFromDomain(i);
                    } else ++i;
                }
            }
            else {
                int i = 0;
                while (i < var.domainSize()) {
                    var.assign(var.getValueDomain(i));
                    if(!currentVar.isPaired() && !var.isPaired()){
                        if (!Constraints.satisfiesSameClassNotPairedConditions(currentVar, var)) {
                            var.deleteFromDomain(i);
                        } else ++i;
                    }
                    else{
                        if (!Constraints.satisfiesSameClassPairedConditions(currentVar, var)) {
                            var.deleteFromDomain(i);
                        } else ++i;
                    }
                }
                if(!currentVar.isPaired() && !var.isPaired() && var.domainSize() != 0) {
                    currentVar.setPaired(true);
                    var.setPaired(true);
                }
            }
        }
    }

    /**
     * True if some variable is empty.
     * @param vars Variables to check.
     * @return True if some variable is empty.
     */
    private boolean someDomainEmpty(LinkedList<MUS> vars){
        int i = 0;
        boolean oneEmpty = false;
        while (i < vars.size() && !oneEmpty){
            if (vars.get(i).domainSize() == 0)  oneEmpty = !oneEmpty;
            else    i++;
        }

        return oneEmpty;
    }

    /**
     * It duplicates the future variables.
     * @param fV Future variables.
     * @return Duplicated Resoult.
     */
    private LinkedList<MUS> duplicateFutureVars(LinkedList<MUS> fV) {
        LinkedList<MUS> l = new LinkedList<>();
        for(MUS m : fV) {
            MUS aux = new MUS(m, 1);
            l.add(aux);
        }
        return l;
    }
}
