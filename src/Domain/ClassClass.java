/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.Domain;


import java.util.Vector;

/**
 * ClassClass represents the set of students that share a same schedule
 * @author Sergio
 */
public abstract class ClassClass {



    // MEMBERS----------------------------------------------------------

    public enum ClassType {
        THEORY,     // ordinal value: 0
        LABORATORY, // ordinal value: 1
        PROBLEMS    // ordinal value: 2

    }
    private int group;
    private String identifier;
    private Subject subject;
    private ClassType type;



    // CONSTRUCTORS----------------------------------------------------
    /**
     * Class constructor specifying the member's values.
     * @param identifier Identification of the Class.
     * @param subject subject of the class.
     * @param group
     */
    public ClassClass(String identifier, Subject subject, int group, ClassType type){
        this.identifier = identifier;
        this.subject = subject;
        this.group = group;
        this.type = type;
    }

    /**
     * Class constructor specifying the member's values.
     * @param myStringVector Identification of the Class.
     * @param type subject of the class.
     */
    public ClassClass( Vector<String> myStringVector, ClassType type ){
        identifier  = myStringVector.get(0); // identifier
        group       = Integer.parseInt(myStringVector.get(1)); // group
        this.type   = type; // type

        // We start reading the last positions of the vector that are for the subject object
        Vector<String> subjectVector = new Vector<>(myStringVector.size() - 4);
        int j = 0;
        for ( int i = 4; i < myStringVector.size(); i++ ) {
            subjectVector.set(j, myStringVector.get(i)); //subject
            j++;
        }
        subject     = new Subject(subjectVector);
    }


    // METHODS--------------------------------------------------------

    /**
     * Set the Group number of a given class.
     * @param group group of the class.
     */
    public void setGroup(int group) {
        this.group = group;
    }
    
    
    /**
     * Set the identification identifier of a given class.
     * @param identifier Identification of the class.
     */
    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    /**
     * It returns the Group number of the class.
     * @return Group number of the class.
     */
    public int getGroup() {
        return group;
    }    
    
    /**
     * It returns the identification of the class.
     * @return Identification of the class.
     */
    public String getIdentifier() {
        return identifier;
    }
    
    /**
     * Set the subject of a given class.
     * @param subject subjecto of the class.
     */
    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    /**
     * It returns the identification of the class.
     * @return Identification of the class.
     */
    public Subject getSubject() {
        return subject;
    }

    /**
     * It returns the type of the class.
     * @return type of the class.
     */
    public ClassType getType() {
        return type;
    }

    /**
     * It returns a vector of strings with the members' values.
     * @return Vector of strings with the members' values.
     */
    public abstract Vector<String> toStr();

    static Vector<String> mergeStringVector(Vector<String> Va, Vector<String> Vb) {
        Vector<String> merge = new Vector<String>();
        merge.addAll(Va);
        merge.addAll(Vb);
        return merge;
    }

    /**
     * Class constructor for a given set of subjects in string format.
     * @param c
     */
    public static ClassClass fromStr( Vector<String> c ){
        ClassClass auxClass;

        // Ask for the type of class and then we create the class
        switch ( Integer.parseInt(c.get(3)) ) {
            case 0: // Theory
                auxClass = new TheoryClass(c);
                break;
            case 1: // Laboratory
                auxClass = new LaboratoryClass(c);
                break;
            case 2: // Problems
                auxClass = new ProblemsClass(c);
                break;
            default:
                auxClass = null; // If we cant' find the type of c
                break;
        }

        return auxClass;
    }
}

