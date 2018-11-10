package src.Domain;

import java.util.ArrayList;

public class SubjectsSet {
    /**
     * SubjectsSet class implements a set of subjects to work with them together.
     * @author joaquimgomez
     */


    public enum typeSet {
        ofCurriculum,
        ofSchedule
    }


    // Members

    typeSet tySet;
    // DEFINICIÓN DEL SET


    // Constructors

    /**
     * Class constructor.s
     */
    public SubjectsSet() {

    }

    /**
     * Class constructor specifying the member's values.
     * @param subjects Array list with subjects.
     * @param tySet Type of set.
     */
    public SubjectsSet(ArrayList<SubjectsSet> subjects, typeSet tySet){
       this.tySet = tySet;
       ///
        //
        // DECIDIR IMPLEMENTACIÓN.
        //
       ///
    }

    // Methods
    public int length(){
        return 3;
    }
    /**
     * Unset the set of subjects.
     * @return ArrayList with the subjects of the set (sorted).
     */
    public ArrayList<Subject> unset() {
        ArrayList<Subject> tempSet = new ArrayList<>(set.values());
        subjectsSort(tempSet);
        return tempSet;
    }
    /**
     * Recursively implementation of the mergesort algorithm.
     * @param set Set that must be ordered.
     * @param start Start point of the sort.
     * @param end End point of the sort.
     */
    private static void rSubjectsSort(ArrayList<Subject> set, int start, int end) {
        if (start < end){
            int mid = start + (end - start) / 2;

            rSubjectsSort(set, start, mid);
            rSubjectsSort(set,mid + 1, end);

            merge(set, start, mid, end);
        }
    }
    /**
     * Implementation of merge for the mergesort algorithm.
     * @param set Set that must be ordered.
     * @param start Start point of the sort.
     * @param mid Middle point of the sort.
     * @param end End point of the sort.
     */
    private static void merge(ArrayList<Subject> set, int start, int mid, int end) {
        ArrayList<Subject> aux = new ArrayList<>();

        for (int i = start; i <= end; i++)  aux.add(i, set.get(i));

        int i = start;
        int j = mid + 1;
        int k = start;

        while (i <= mid && j <= end) {
            if (compare(aux.get(i), "<=", aux.get(j)))   set.set(k++, aux.get(i++));
            else    set.set(k++, aux.get(j++));
        }

        while (i <= mid)
            set.set(k++, aux.get(i++));
    }

    /**
     * Implements the different comparisons between two subjects,
     * considering the level and the name of these.
     * @param s1 First subject to compare.
     * @param op Operator of the comparison.
     * @param s2 Second subject to compare.
     * @return Resoult of the comparison.
     */
    public static boolean compare(Subject s1, String op, Subject s2){
        if (op.equals("<"))     return s1.getLevel() < s2.getLevel() && s1.getName() < s2.getName();
        if (op.equals(">"))     return s1.getLevel() > s2.getLevel() && s1.getName() > s2.getName();
        if (op.equals("<="))    return s1.getLevel() <= s2.getLevel() && s1.getName() <= s2.getName();
        if (op.equals(">="))    return s1.getLevel() >= s2.getLevel() && s1.getName() >= s2.getName();
        if (op.equals("!="))    return !s1.equals(s2);
        if (op.equals("=="))    return s1.equals(s2);

        return false;
    }


}