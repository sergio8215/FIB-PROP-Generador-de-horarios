/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.Domain;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.Vector;

/**
 *
 * @author Sergio
 */
public class ClassSet {
    
    // ATTRIBUTES -------------------------------------

    private HashMap<String, ClassClass> classSet;

    // CONSTRUCTOR -----------------------------------

    /**
    * Class constructor.
    */    
    public ClassSet() {
        classSet = new HashMap<>();
      //  constraints = new ArrayList<Constraints>;
    }

    /**
    * Class constructor specifying the member's values.
    * @param subjects
    */    
    public ClassSet( SubjectsSet subjects ) {

        classSet = new HashMap<>();
        createSetOfClasses(subjects);

    }

    /**
     * Class constructor for a given set of subjects in string format.
     * @param classS
     */
    public ClassSet( Vector< Vector<String> > classS ){

        for (Vector<String> classClass: classS) {

            ClassClass auxClass = ClassClass.fromStr(classClass);
            if ( !this.existsClass( auxClass.getSubject().getName(), auxClass.getGroup() ) ) {
                this.addClass(auxClass.getIdentifier(), auxClass);
            }
        }
    }

    // PRIVATE METHODS -------------------------------------------



    // METHODS ---------------------------------------------------

    public boolean existsClass( String subjectName, int group ) {
        return classSet.containsKey(""+subjectName+group);
    }

    public ClassClass getClass( String name, int group ){
        if (this.existsClass(name, group)) {
            return classSet.get(""+name+group);
        }
        return null;
    }

    /**
     * Add a class to the set
     * @param key that identifies the object.
     * @param newClass the new class that needs to be added.
     */
    public void addClass( String key, ClassClass newClass ){
        classSet.put( key, newClass);
    }

    /**
     * Creates a set of classes from a set of subjects
     * @param subjects Subject set for the classes set creation
     */
    private void createSetOfClasses( SubjectsSet subjects ){
        ArrayList<Subject> subjectsArray = subjects.unset();

        ClassClass c;
        String identifier;

        // for each subject
        for ( Subject subject : subjectsArray ){
            // if the quantity of students it's less than the max capacity it's just 1 group
            int groups = 1;
            // if quantity of students it's more than MaxCapacity we have to split the group
            while ( subject.getNumberStudents()/groups > subject.getMaxCapacity() ){
                // if we split the group and now it's less than MaxCapacity then we have the quantity of groups
                groups++;
            }

            // For each group of the subject we create the respective theory classes, labs and problems classes.
            for (int i = 1; i <= groups; i++){

                int subGroupCount = 0;
                int subgroup;
                int[] hoursOfClass = subject.getHoursClasses();

                // If there is TheoryHours we create the class
                if ( hoursOfClass[0] != 0 ) {
                    subgroup = i*10+subGroupCount;
                    identifier = ""+subject.getName()+subgroup;
                    c = new TheoryClass( identifier, subgroup, subject, i*10);
                    this.addClass( identifier, c);
                    subGroupCount++;
                }

                // If there is LaboratoryHours we create the class
                if ( hoursOfClass[1] != 0 ) {
                    subgroup = i*10+subGroupCount;
                    subGroupCount++;
                    identifier = ""+subject.getName()+subgroup;
                    c = new LaboratoryClass( identifier, subgroup, subject, i*10);
                    this.addClass( identifier, c);
                }

                // If there is ProblemsHours we create the class
                if ( hoursOfClass[2] != 0 ) {
                    subgroup = i*10+subGroupCount;
                    identifier = ""+subject.getName()+subgroup;
                    c = new ProblemsClass( identifier, subgroup, subject, i*10);
                    this.addClass( identifier, c);
                }
            }
        }
    }


    /**
     * Unset the set of classes.
     * @return ArrayList with the classes of the set (sorted).
     */
    public ArrayList<ClassClass> unset() {
        ArrayList<ClassClass> tempSet = new ArrayList<>(classSet.values());
        classSort(tempSet);
        return tempSet;
    }

    /**
     * Recursively implementation of the mergesort algorithm.
     * @param set that must be ordered.
     * @param start Start point of the sort.
     * @param end End point of the sort.
     */
    private static void rClassSort(ArrayList<ClassClass> set, int start, int end) {
        if (start < end){
            int mid = start + (end - start) / 2;

            rClassSort(set, start, mid);
            rClassSort(set,mid + 1, end);

            merge(set, start, mid, end);
        }
    }
    /**
     * Sort of the classes considering the group and the name of these.
     * @param set Set that must be ordered.
     */
    public static void classSort(ArrayList<ClassClass> set) {
        // Mergesort Implementation
        // Worst-case complexity: O(n log n) ; Worst-case space complexity: O(n)
        rClassSort(set, 0, set.size());
    }


    /**
     * Implementation of merge for the mergesort algorithm.
     * @param set Set that must be ordered.
     * @param start Start point of the sort.
     * @param mid Middle point of the sort.
     * @param end End point of the sort.
     */
    private static void merge(ArrayList<ClassClass> set, int start, int mid, int end) {
        ArrayList<ClassClass> aux = new ArrayList<>();

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
     * Implements the different comparisons between two classes,
     * considering the group and the name of these.
     * @param s1 First class to compare.
     * @param op Operator of the comparison.
     * @param s2 Second class to compare.
     * @return Result of the comparison.
     */
    public static boolean compare(ClassClass s1, String op, ClassClass s2){
        if (op.equals("<"))     return s1.getGroup() < s2.getGroup() && s1.getSubject().getName() < s2.getSubject().getName();
        if (op.equals(">"))     return s1.getGroup() > s2.getGroup() && s1.getSubject().getName() > s2.getSubject().getName();
        if (op.equals("<="))    return s1.getGroup() <= s2.getGroup() && s1.getSubject().getName() <= s2.getSubject().getName();
        if (op.equals(">="))    return s1.getGroup() >= s2.getGroup() && s1.getSubject().getName() >= s2.getSubject().getName();
        if (op.equals("!="))    return !s1.equals(s2);
        if (op.equals("=="))    return s1.equals(s2);

        return false;
    }


    /**
     * It returns the set as a vector of vectors (of strings) with the members of the elements of the set.
     * @return Vector of vectors (of strings) with the members of the elements of the set.
     */
    public Vector< Vector<String> > toStr() {
        ArrayList<ClassClass> cs = this.unset();
        Vector< Vector<String> > set = new Vector<>(cs.size());

        for (int i = 0; i < cs.size(); i++) set.set(i, cs.get(i).toStr());

        return set;
    }


}
