package src.Domain;

import java.util.LinkedList;

public class CtrlScheduleGeneration {
    /**
     *
     * @author joaquimgomez & mireiacano
     */

    // Members

    Schedule schedule;
    ClassroomSession classroomSession;


    // Constructors

    /**
     * Basic Class constructor
     * @param crFile name of the file that is going to generate the Schedule's classrooms
     * @param sFile name of the file that is going to generate the Schedule's subjects
     */
    public CtrlScheduleGeneration(String crFile, String sFile) {
        schedule = new Schedule(crFile, sFile);
    }

    /**
     * Class constructor specifying the member value of classroomSession..
     * @param crFile name of the file that is going to generate the Schedule's classrooms
     * @param sFile name of the file that is going to generate the Schedule's subjects
     * @param classroomSession classroomSession Classroom-Sessions.
     */
    public CtrlScheduleGeneration(String crFile, String sFile, ClassroomSession classroomSession) {
        schedule = new Schedule(crFile, sFile);
        this.classroomSession = classroomSession;
    }

    // Methods

    /**
     * Generates the schedule for a given set of MUSs (the variables of the assign a value from the domain).
     * @param vars Variables to assign a value from the domain.
     * @return Generates Schedule.
     */
    public Schedule generateSchedule(LinkedList<MUS> vars) {
        LinkedList<MUS> aux = vars.clone();
        schedule = chronologicalBacktracking(aux, schedule);
        return schedule;
    }

    /**
     * Implementation of the Chronological Backtracking Algorithm for satisfaction of constraints.
     * @param futureVars Variables to which a domain value must be assigned.
     * @param solution Partial solution of the assignment of values to the variables.
     * @return Final solution (successful or not).
     */
    private static Schedule chronologicalBacktracking(LinkedList<MUS> futureVars, Schedule solution) {
        if (futureVars.isEmpty()) 	return solution;
        else {
            MUS currentVar = futureVars.pollFirst();

            for (int i = 0; i < classroomSession.size(); i++){ 	// i = id/posición pair classroom-sesion
                currentVar.assign(classroomSession.getPair(i));
                solution.add(currentVar);

                if (solution.valid()) {
                    LinkedList<MUS> aux = futureVars.clone();
                    solution = chronologicalBacktracking(aux, new Schedule(solution));

                    if (!solution.isFail()) {
                        return solution;
                    } else {
                        solution.delete(currentVar);
                    }

                } else {
                    solution.delete(currentVar);
                }

            }

            solution.fail();
            return solution;
        }
    }
}
