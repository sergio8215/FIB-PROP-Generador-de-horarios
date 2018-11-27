/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.domain.classes;

import src.domain.utils.UtilsDomain;
import java.util.Vector;

/**
 * ClassClass represents the set of students that share a same schedule
 * @author Sergio Mazzariol
 */
public abstract class ClassClass {


    // MEMBERS----------------------------------------------------------

    private int group;
    private String identifier;
    private Subject subject;
    private UtilsDomain.ClassType type;
    private int quantityStudents;
    private UtilsDomain.typeShift shift;

    // CONSTRUCTORS----------------------------------------------------

    /**
     * Class constructor specifying the member's values.
     * @param identifier Identification of the Class.
     * @param subject subject of the class.
     * @param group Number of the class for unique for each subject
     * @param quantityStudents Quantity of students that are enroll
     * @param shift Shift of the class, Morning or Afternoon
     * @param type Type of class, theory, laboratory or problems
     */
    public ClassClass(String identifier, Subject subject, int group, int quantityStudents, UtilsDomain.typeShift shift, UtilsDomain.ClassType type){
        this.identifier = identifier;
        this.subject = subject;
        this.group = group;
        this.quantityStudents = quantityStudents;
        this.type = type;
        this.shift = shift;
    }


    /**
     * Class constructor specifying the member's values.
     * @param myStringVector Vector with all class information, one attribute per position.
     * @param type Type of class.
     */
    public ClassClass( Vector<String> myStringVector, UtilsDomain.ClassType type ){
        identifier  = myStringVector.get(0);                    // identifier
        group       = Integer.parseInt(myStringVector.get(1));  // group
        this.type   = type;                                     // type
        this.shift  = UtilsDomain.typeShift.valueOf(myStringVector.get(4));  // Shift;
        this.quantityStudents = Integer.parseInt(myStringVector.get(5));  // quantityStudents

        // We initialize the vector to the subject string vector sizeTimetable:
        // [0]identifier => [1]group => [2]subGroup => [3]type of group => [4]shift => [5]quantityStudents
        Vector<String> subjectVector = new Vector<>(myStringVector.size() - 6);
        int j = 0;
        // We start reading the last positions of the vector that are for the subject object
        for ( int i = 6; i < myStringVector.size(); i++ ) {
            subjectVector.add(j, myStringVector.get(i)); //subject
            j++;
        }
        subject = new Subject(subjectVector);
    }

    // METHODS--------------------------------------------------------

    /**
     * Set the Group number of a given class.
     * @param group Group of the class.
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
     * @param subject subject of the class.
     */
    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    /**
     * It returns the subject of the class.
     * @return Subject of the class.
     */
    public Subject getSubject() {
        return subject;
    }

    /**
     * It returns the type of the class.
     * @return type of the class.
     */
    public UtilsDomain.ClassType getType() {
        return type;
    }

    /**
     * Gets the shift of the class
     * @return shift of the class
     */
    public UtilsDomain.typeShift getShift() {
        return shift;
    }

    /**
     * Sets the class shift
     * @param shift Specifies if the class is given in the morning or afternoon
     */
    public void setShift(UtilsDomain.typeShift shift) {
        this.shift = shift;
    }

    /**
     * Gets the quantity of students
     * @return Quantity of students on the clas
     */
    public int getQuantityStudents() {
        return quantityStudents;
    }

    /**
     * Set a quantity of students
     * @param quantityStudents Quantity of students of given class
     */
    public void setQuantityStudents(int quantityStudents) {
        this.quantityStudents = quantityStudents;
    }

    /**
     * Sets a subgroup for the class
     * @param subGroup Subgroup identification
     */
    public abstract void setSubGroup(int subGroup);

    /**
     * Gets the subgroup identification
     * @return subgroup identification
     */
    public abstract int getSubGroup();

    /**
     * It returns a vector of strings with the members' values.
     * @return Vector of strings with the members' values.
     */
    public abstract Vector<String> toStr();

    /**
     * Merge two vectors Vb at the last position of Va
     * @param Va First part of the merged vector
     * @param Vb Second part of the merged vector
     * @return A vector Va + Vb
     */
    static Vector<String> mergeStringVector(Vector<String> Va, Vector<String> Vb) {
        Vector<String> merge = new Vector<>();
        merge.addAll(Va);
        merge.addAll(Vb);
        return merge;
    }

    /**
     * Class that creates an specific type class extracting the information from a vector string
     * @param c Vector with member's values
     */
    public static ClassClass fromStr( Vector<String> c ){
        ClassClass auxClass;

        // Ask for the type of class and then we create the class
        switch ( UtilsDomain.ClassType.valueOf(c.get(3)).ordinal() ) {
            case 0:
                auxClass = new TheoryClass(c);
                break;
            case 1:
                auxClass = new LaboratoryClass(c);
                break;
            case 2:
                auxClass = new ProblemsClass(c);
                break;
            default:
                auxClass = null; // If we cant' find the type of c
                break;
        }
        return auxClass;
    }
}
