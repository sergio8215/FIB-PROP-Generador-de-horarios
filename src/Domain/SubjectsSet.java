package src.Domain;


import java.util.ArrayList;
import java.util.HashMap;


public final class SubjectsSet {
    /**
     * SubjectsSet class implements a set of subjects to work with them together.
     * @author joaquimgomez
     */


    // Auxiliary Structures

    public static enum typeSet {
        ofCurriculum,
        ofSchedule,
        unespecified
    }


    // Members

    typeSet tySet;
    HashMap<String,Subject> set;


    // Constructors

    /**
     * Class constructor
     */
    public SubjectsSet() {
        tySet = typeSet.unespecified;
        set = new HashMap<>();
    }

    /**
     * Class constructor specifying the member's values.
     * @param subjects Array list with subjects.
     * @param tySet Type of set.
     */
    public SubjectsSet(ArrayList<Subject> subjects, typeSet tySet){
       this.tySet = tySet;
       set = new HashMap<>(subjects.size());

       setSet(subjects);
    }


    // Methods

    /**
     * Set the type of the set.
     * @param tySet Type of set.
     */
    public void setTySet(typeSet tySet) {
        this.tySet = tySet;
    }

    /**
     * Returns the type of the set.
     * @return Type of the set.
     */
    public typeSet getTySet() {
        return tySet;
    }

    /**
     * Set the set of subjects.
     * @param subjects Set of subjects.
     */
    public void setSet(ArrayList<Subject> subjects) {
        for (Subject s : subjects)   set.put(s.getName(), s);
    }

    /**
     * Unset the set of subjects.
     * @return ArrayList with the subjects of the set (sorted).
     */
    public ArrayList unset() {
        ArrayList<Subject> tempSet = new ArrayList<Subject>(set.values());
        return subjectsSort(tempSet);
    }

    /**
     * Returns the requested subject.
     * @param name Name of the subject.
     * @return Return a ResoultSubjectPair with the resoult.
     */
    public UtilsDomain.ResoultSubjectPair getSubject(String name) {
        UtilsDomain.ResoultSubjectPair res;
        res.sub = Subject();
        res.res = false;

        if (set.containsKey(name)){
            res.sub = set.get(name);
            res.res = !res.res;
        }

        return res;
    }

    /**
     * Add subject, if possible, to the set.
     * @param s Subject to be added.
     * @return True if the subject could be added.
     */
    public boolean putSubject(Subject s) {
        if (set.containsKey(s.getName()))   return false;
        else if (!canPut(s))    return false;
        else {
            set.put(s.getName(), s);
            return true;
        }
    }

    /**
     * Remove a subject from the set.
     * @param name Name of the subject.
     * @return True if the subject could be deleted.
     */
    public boolean popSubject(String name) {
        if (!set.containsKey(name))     return false;

        set.remove(name);
        return true;
    }

    /**
     * Returns the size of the set.
     * @return Size of the set.
     */
    public int length() {
        return set.size();
    }

    /**
     * It indicates if a given subject is in the set.
     * @param s Subject to check
     * @return True if the set has s.
     */
    public boolean belongs(Subject s){
        return set.containsKey(s.getName());
    }

    /**
     *
     * @param sSubject Name of the subject to check.
     * @return True if the set has the subject.
     */
    public boolean belong(String sSubject){
        return set.containsKey(sSubject);
    }

    /**
     * It carries out the union of the sets.
     * @param set Set with which the union should be made.
     * @return True if the union could be made.
     */
    public boolean union(SubjectsSet set) {
        ArrayList<Subject> arraySet = set.unset();
        HashMap<String,Subject> auxSet = new HashMap<>();

        boolean allCompatible = true;
        for (Subject s : arraySet){
            if (this.canPut(s))     auxSet.put(s.getName(), s);
            else {
                allCompatible = !allCompatible;
                break;
            }
        }

        if (allCompatible){
            this.set.putAll(auxSet);
            return true;
        }

        return false;
    }

    /**
     * Compute the difference between two subjects set.
     * @param set Which the function compute the difference.
     * @return The difference
     */
    public SubjectsSet difference(SubjectsSet set) {
        // Definition of the difference: this - set
        ArrayList<Subject> set1 = this.unset();
        ArrayList<Subject> set2 = set.unset();

        SubjectsSet diff = new SubjectsSet();

        for (Subject s : set1) {
            if (!set2.contains(s))  diff.putSubject(s);
        }

        return diff;
    }

    /**
     * For a given subject return true if is possible to add it to the set.
     * @param s Subject to analize.
     * @return true if is possible to add the subject to the set
     */
    private boolean canPut(Subject s){
        return true;    // By the moment
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
     * Recursively implementation of the mergesort algorithm.
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
     * Sort of the subjects considering the level and the name of these.
     */
    public static void subjectsSort(ArrayList<Subject> set) {
        // Mergesort Implementation
        // Worst-case complexity: O(n log n) ; Worst-case space complexity: O(n)
        rSubjectsSort(set, 0, set.size());
    }
}
