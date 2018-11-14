package src.domain.classes;


import src.domain.utils.UtilsDomain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;


public final class SubjectsSet {
    /**
     * SubjectsSet class implements a set of subjects to work with them together.
     * @author joaquimgomez
     */


    // Members

    HashMap<String,Subject> set;


    // Constructors

    /**
     * Class constructor
     */
    public SubjectsSet() {
        set = new HashMap<>();
    }

    /**
     * Class constructor specifying the member's values.
     * @param subjects Array list with subjects.
     */
    public SubjectsSet(ArrayList<Subject> subjects){
       set = new HashMap<>(subjects.size());

       setSet(subjects);
    }

    /**
     * Class constuctor for a given set of subjects in string format.
     * @param subjectsSet Matrix with the differents subjects (with string format) to be added to the set.
     */
    public SubjectsSet(Vector< Vector<String> > subjectsSet){
        set = new HashMap<>(subjectsSet.size());
        for (Vector<String> subject : subjectsSet) {
            Subject auxSubject = new Subject(subject);
            this.putSubject(auxSubject);
        }
    }


    // Methods

    /**
     * Set the set of subjects.
     * @param subjects Set of subjects.
     */
    public void setSet(ArrayList<Subject> subjects) {
       // for (int i = 0; i < subjects.size(); i++)   set.put(subjects.get(i).getName(), subjects.get(i));
        for (Subject s : subjects)   set.put(s.getName(), s);
    }

    /**
     * Unset the set of subjects.
     * @return ArrayList with the subjects of the set (sorted).
     */
    public ArrayList<Subject> unset() {
        ArrayList<Subject> tempSet = new ArrayList<>(set.values());
        //subjectsSort(tempSet);
        return tempSet;
    }

    /**
     * It returns the set as a vector of vectors (of strings) with the members of the elements of the set.
     * @return Vector of vectors (of strings) with the members of the elements of the set.
     */
    public Vector< Vector<String> > toStr() {
        ArrayList<Subject> ss = this.unset();

        Vector< Vector<String> > set = new Vector<>(ss.size());

        for (int i = 0; i < ss.size(); i++)     set.add(i, ss.get(i).toStr());

        return set;
    }

    /**
     * Returns the requested subject.
     * @param name Name of the subject.
     * @return Return a ResoultSubjectPair with the resoult.
     */
    public UtilsDomain.ResoultOfQuery<Subject> getSubject(String name) {
        UtilsDomain.ResoultOfQuery<Subject> res = new UtilsDomain.ResoultOfQuery<>();
        res.resoult = new Subject();
        res.queryTest = false;

        if (set.containsKey(name)){
            res.resoult = set.get(name);
            res.queryTest = !res.queryTest;
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
     * It indicates if a given subject (by the name) is in the set.
     * @param sSubject Name of the subject to check.
     * @return True if the set has the subject.
     */
    public boolean belongs(String sSubject){
        return set.containsKey(sSubject);
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
        if (op.equals("<"))     return s1.getLevel() < s2.getLevel() && s1.getName().compareTo(s2.getName()) > 0;
        if (op.equals(">"))     return s1.getLevel() > s2.getLevel() && s1.getName().compareTo(s2.getName()) < 0;
        if (op.equals("<="))    return s1.getLevel() <= s2.getLevel() && (s1.getName().compareTo(s2.getName()) < 0 || s1.getName().contentEquals(s2.getName()));
        if (op.equals(">="))    return s1.getLevel() >= s2.getLevel() && (s1.getName().compareTo(s2.getName()) > 0 || s1.getName().contentEquals(s2.getName()));
        if (op.equals("!="))    return s1.getLevel() != s2.getLevel() && !s1.getName().contentEquals(s2.getName());
        if (op.equals("=="))    return s1.getLevel() == s2.getLevel() && s1.getName().contentEquals(s2.getName());

        return false;
    }

    /**
     * Implementation of merge for the mergesort algorithm.
     * @param set Set that must be ordered.
     * @param start Start point of the sort.
     * @param mid Middle point of the sort.
     * @param end End point of the sort.
     */
    private static void merge(ArrayList<Subject> set, int start, int mid, int end) {
        int n1 = mid - start + 1;
        int n2 = end - mid;

        ArrayList<Subject> aux1 = new ArrayList<>(n1);
        ArrayList<Subject> aux2 = new ArrayList<>(n2);

        for (int i = 0; i < n1; ++i)    aux1.add(i, set.get(start + i));
        for (int j = 0; j < n2; ++j)    aux2.add(j, set.get(mid + 1 + j));

        int i = 0;
        int j = 0;

        int k = start;
        while (i < n1 && j < n2) {
            if (compare(aux1.get(i), "<=", aux2.get(j))){
                set.set(k, aux1.get(i));
                i++;
            } else {
                set.set(k, aux2.get(j));
                j++;
            }
        }

        while (i < n1){
            set.set(k, aux1.get(i));
            i++;
            k++;
        }

        while (j < n2) {
            set.set(k, aux2.get(j));
            j++;
            k++;
        }
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

            merge(new ArrayList<>(set), start, mid, end);
        }
    }

    /**
     * Sort of the subjects considering the level and the name of these.
     * @param set Set that must be ordered.
     */
    public static void subjectsSort(ArrayList<Subject> set) {
        // Mergesort Implementation
        // Worst-case complexity: O(n log n) ; Worst-case space complexity: O(n)
        rSubjectsSort(set, 0, set.size()-1);
    }
}
