package src.domain.comparators;

import src.domain.classes.Classroom;

import java.util.Comparator;

public class ClassroomCapacityComparator implements Comparator<Classroom> {

    /**
     * Comparator-type class to sort Classroom-Object lists by capacity
     * @author mireia
     */

    /**
     * Compares its two arguments for order
     * @param o1 Classroom Object
     * @param o2 Classroom Object
     * @return returns a -1, 0 or 1 as the first argument is less than, equal to, or greater than the second
     */
    @Override
    public int compare(Classroom o1, Classroom o2) {
        if (o1.getCapacity() > o2.getCapacity()) {
            return 1;
        }
        else if (o1.getCapacity() < o2.getCapacity()) {
            return -1;
        }
        return 0;
    }
}
