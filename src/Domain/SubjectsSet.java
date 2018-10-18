package src.Domain;


import java.util.ArrayList;


public class SubjectsSet {
    /**
     * SubjectsSet class implements a set of subjects to work with them together.
     * This set all the time keeps the sorting.
     * @author joaquimgomez
     */


    // Auxiliary Structures

    public enum typeSet {
        ofCurriculum,
        ofSchedule,
        nothing
    }


    // Members

    typeSet tySet;
    ArrayList<Subject> set; // By the moment.


    // Constructors

    /**
     * Class constructor
     */
    public SubjectsSet() {
        tySet = typeSet.nothing;
        set = new ArrayList<>();
    }

    /**
     * Class constructor specifying the member's values.
     * @param subjects Array list with subjects.
     * @param tySet Type of set.
     */
    public SubjectsSet(ArrayList<Subject> subjects, typeSet tySet){
       this.tySet = tySet;
       set = subjects;
       subjectsSort();
    }


    // Methods

    /**
     * Unset the set of subjects.
     * @return ArrayList with the subjects of the set.
     */
    public ArrayList unset() {
        return set;
    }

    /**
     * Returns the requested subject.
     * @param name Name of the subject.
     * @return Return a ResoultSubjectPair with the resoult.
     */
    public UtilsDomain.ResoultSubjectPair getSubject(String name) {
        // Binary Search Implementation
        int start = 0;
        int end = set.size() - 1;

        UtilsDomain.ResoultSubjectPair res;
        res.sub = Subject();
        res.res = false;

        while (start <= end) {
            int mid = start + (end - start) / 2;

            if (set.get(mid).getName().equals(name)) {
                res.sub = set.get(mid);
                res.res = true;
                return res;
            }

            if (name < set.get(mid).getName())  end = mid - 1;

            if (name > set.get(mid).getName())  start = mid + 1;
        }

        return res;
    }

    /**
     * Add subject, if possible, to the set.
     * @param s Subject to be added.
     * @return True if the subject could be added.
     */
    public boolean putSubject(Subject s) {
        // Binary Search Implementation
        if (canPut(s)) {
            int start = 0;
            int end = set.size() - 1;

            while (start <= end) {
                int mid = start + (end - start) / 2;

                if (compare(s, ">", set.get(mid - 1)) && compare(s, "<", set.get(mid + 1))) {
                    set.add(mid s);
                    return true;
                }

                if (compare(s, "<", set.get(mid)))  end = mid - 1;

                if (compare(s, ">", set.get(mid)))  start = mid + 1;
            }
        }

        return false;
    }

    /**
     * Remove a subject from the set.
     * @param name Name of the subject.
     * @return True if the subject could be deleted.
     */
    public boolean popSubject(String name) {
        // Binary Search Implementation
        int start = 0;
        int end = set.size() - 1;

        while (start <= end) {
            int mid = start + (end - start) / 2;

            if (set.get(mid).getName().equals(name)) {
                set.remove(mid);
                return true;
            }

            if (name < set.get(mid).getName())  end = mid - 1;

            if (name > set.get(mid).getName())  start = mid + 1;
        }

        return false;
    }

    /**
     * Returns the size of the set.
     * @return Size of the set.
     */
    public int length() {
        return set.size();
    }

    /**
     * It carries out the union of the sets.
     * @param set Set with which the union should be made.
     * @return True if the union could be made.
     */
    public boolean union(SubjectsSet set) {
        ArrayList<Subject> arraySet = (this.difference(set)).unset();
        ArrayList<Subject> aux = new ArrayList<>();

        boolean allCompatible = true;
        for (Subject s : arraySet) {
            if (this.canPut(s))     aux.add(s);
            else {
                allCompatible = !allCompatible;
                break;
            }
        }

        if (allCompatible) {
            // putSubject check again the compatibility but it doesn't matter.
            for (int i = 0; i < aux.size(); i++)    this.putSubject(aux.get(i));
            return true;
        }

        return false;
    }

    public SubjectsSet difference(SubjectsSet set) {
        //
        // LACK TO IMPLEMENT.
        //
    }

    /**
     * It indicates if a given subject is in the set.
     * @param s Subject to check
     * @return True if the set has s.
     */
    public boolean belongs(Subject s){
        // Binary Search Implementation
        int start = 0;
        int end = set.size() - 1;

        while (start <= end) {
            int mid = start + (end - start) / 2;

            if (s.equals(set.get(mid)))     return true;

            if (compare(s, "<", set.get(mid)))  end = mid - 1;

            if (compare(s, ">", set.get(mid)))  start = mid + 1;
        }

        return false;
    }

    private boolean canPut(Subject s){
        //
        // LACK TO IMPLEMENT.
        //
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

    /**
     * Implementation of merge for the mergesort algorithm.
     * @param start Start point of the sort.
     * @param mid Middle point of the sort.
     * @param end End point of the sort.
     */
    private void merge(int start, int mid, int end) {
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
     * Recursively implementation of the mergesort algorithm.
     * @param start Start point of the sort.
     * @param end End point of the sort.
     */
    private void rSubjectsSort(int start, int end) {
        if (start < end){
            int mid = start + (end - start) / 2;

            rSubjectsSort(start, mid);
            rSubjectsSort(mid + 1, end);

            merge(start, mid, end);
        }
    }

    /**
     * Sort of the subjects considering the level and the name of these.
     */
    private void subjectsSort() {
        // Mergesort Implementation
        // Worst-case complexity: O(n log n) ; Worst-case space complexity: O(n)
        rSubjectsSort(0, set.size());
    }
}
