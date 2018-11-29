package src.domain.classes;

import src.domain.utils.UtilsDomain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;


/**
 * SubjectsSet class implements a set of subjects to work with them together.
 * @author Joaquim Gomez
 */
public final class SubjectsSet {

    // Members

    private HashMap<String,Subject> set;


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
       // for (int i = 0; i < subjects.sizeTimetable(); i++)   set.put(subjects.get(i).getName(), subjects.get(i));
        for (Subject s : subjects)   set.put(s.getName(), s);
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
    public UtilsDomain.ResultOfQuery<Subject> getSubject(String name) {
        UtilsDomain.ResultOfQuery<Subject> res = new UtilsDomain.ResultOfQuery<>();
        res.result = new Subject();
        res.queryTest = false;

        if (set.containsKey(name)){
            res.result = set.get(name);
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
     * Returns the sizeTimetable of the set.
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
     * @param left Left part of the set.
     * @param right Right part of the set.
     * @param set Set.
     */
    private static void merge(ArrayList<Subject> left, ArrayList<Subject> right, ArrayList<Subject> set) {
        int leftInd = 0;
        int rightInd = 0;
        int setInd = 0;

        while (leftInd < left.size() && rightInd < right.size()) {
            if (SubjectsSet.compare(left.get(leftInd), "<=", right.get(rightInd)) /*(left.get(leftInd).compareTo(right.get(rightInd))) < 0*/) {
                set.set(setInd, left.get(leftInd));
                leftInd++;
            } else {
                set.set(setInd, right.get(rightInd));
                rightInd++;
            }
            setInd++;
        }

        ArrayList<Subject> rest;
        int restIndex;
        if (leftInd >= left.size()) {
            rest = right;
            restIndex = rightInd;
        } else {
            rest = left;
            restIndex = leftInd;
        }

        for (int i=restIndex; i<rest.size(); i++) {
            set.set(setInd, rest.get(i));
            setInd++;
        }
    }

    /**
     * Sort of the subjects considering the level and the name of these (implemented following Mergesort algorithm).
     * @param set Set that must be sorted.
     * @return Sorted set.
     */
    public static ArrayList<Subject> subjectsSort(ArrayList<Subject> set) {
        ArrayList<Subject> left = new ArrayList<Subject>();
        ArrayList<Subject> right = new ArrayList<Subject>();

        int mid;

        if (set.size() == 1)    return set;
        else {
            mid = set.size()/2;

            for (int i = 0; i < mid; i++) {
                left.add(set.get(i));
            }

            for (int i = mid; i < set.size(); i++) {
                right.add(set.get(i));
            }

            left = subjectsSort(left);
            right = subjectsSort(right);

            merge(left, right, set);
        }

        return set;
    }
}
