package src.Domain;

import java.util.LinkedList;

public class CtrlScheduleGeneration {

    Schedule schedule;


    public CtrlScheduleGeneration() {

    }

    public Schedule generateSchedule(/**/) {
        schedule = chronologicalBacktracking(/* VARS*/);
        return schedule;
    }

    private static Schedule chronologicalBacktracking(LinkedList<MUS> futureVars, Schedule solution) {
        if (futureVars.isEmpty()) 	return solution;
        else {
            MUS currentVar = futureVars.pollFirst();

            for (int i = 0; i < currentVar.values().size(); i++){ 	// i = id/posición pair classroom-sesion
                currentVar.assign(i);
                solution.add(currentVar);

                if (solution.valid()) {
                    solution = chronologicalBacktracking(futureVars, solution);

                    if (!solution.isFail()) {
                        return solution;
                    } else {
                        solution.delete(currentVar);
                    }

                } else {
                    solution.delete(currentVar);
                }

            }

            return solution.fail();
        }
    }
}
