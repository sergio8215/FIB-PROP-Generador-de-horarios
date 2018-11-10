package src.Domain;

import java.util.LinkedList;

public class CtrlScheduleGeneration {

    Schedule schedule;
    ClassroomSession classroomSession;


    public CtrlScheduleGeneration();

    public CtrlScheduleGeneration(ClassroomSession classroomSession) {
        this.classroomSession = classroomSession;
    }

    public Schedule generateSchedule(LinkedList<MUS> vars) {
        schedule = chronologicalBacktracking(vars, schedule);
        return schedule;
    }

    private static Schedule chronologicalBacktracking(LinkedList<MUS> futureVars, Schedule solution) {
        if (futureVars.isEmpty()) 	return solution;
        else {
            MUS currentVar = futureVars.pollFirst();

            for (int i = 0; i < currentVar.values().size(); i++){ 	// i = id/posición pair classroom-sesion
                currentVar.assign(classroomSession.getPair(i));
                solution.add(currentVar);

                if (solution.valid()) {
                    solution = chronologicalBacktracking(futureVars.clone(), new Schedule(solution));

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
