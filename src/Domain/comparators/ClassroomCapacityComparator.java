package Domain.comparators;

import Domain.ClassroomClass;

import java.util.Comparator;

public class ClassroomCapacityComparator implements Comparator<ClassroomClass> {

    /**
     * Comparator-type class to sort ClassroomClass-Object lists by capacity
     * @author mireia
     */

    /**
     * Compares its two arguments for order
     * @param o1 ClassroomClass Object
     * @param o2 ClassroomClass Object
     * @return returns a -1, 0 or 1 as the first argument is less than, equal to, or greater than the second
     */
    @Override
    public int compare(ClassroomClass o1, ClassroomClass o2) {
        if (o1.getCapacity() > o2.getCapacity()) {
            return 1;
        }
        else if (o1.getCapacity() < o2.getCapacity()) {
            return -1;
        }
        return 0;
    }
}