/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.domain.classes;

import src.domain.utils.UtilsDomain;

import java.util.Vector;

/**
 * TheoryCLass represents the set of students that share a same schedule on a theory class
 * @author Sergio Mazzariol
 */
public class TheoryClass extends ClassClass{

    // MEMBERS----------------------------------------------------------
    private int subGroup;

    // CONSTRUCTORS----------------------------------------------------

    /**
     * Class constructor specifying the member's values.
     * @param identifier Identification of the Class.
     * @param subject subject of the class.
     * @param group Number of the class for unique for each subject
     * @param quantityStudents Quantity of students that are enroll
     * @param shift Shift of the class, Morning or Afternoon
     * @param subGroup SubGroup identification
     */
    public TheoryClass(String identifier, Subject subject, int group, int quantityStudents, UtilsDomain.typeShift shift, int subGroup){
        super(identifier, subject, group, quantityStudents, shift, UtilsDomain.ClassType.THEORY );
        this.subGroup = subGroup;
    }

    /**
     * Class constructor specifying the member's values.
     * @param myStringVector Vector with all class information, one attribute per position.
     */
    public TheoryClass( Vector<String> myStringVector ) {
        super(myStringVector, UtilsDomain.ClassType.THEORY);
        subGroup = Integer.parseInt(myStringVector.get(2)); // subgroup
    }

    // METHODS--------------------------------------------------------

    /**
     * Set the Group number of a given class.
     * @param subGroup Subgroup identification of the class.
     */
    @Override
    public void setSubGroup(int subGroup) {
        this.subGroup = subGroup;
    }

    /**
     * It returns the SubGroup number of the class.
     * @return SubGroup number of the class.
     */
    @Override
    public int getSubGroup() {
        return subGroup;
    }

    /**
     * It returns a vector of strings with the members' values.
     * @return Vector of strings with the members' values.
     */
    @Override
    public Vector<String> toStr() {
        Vector<String> myAttributes = new Vector<String>(6);

        myAttributes.add(0, super.getIdentifier());                             //identifier
        myAttributes.add(1, Integer.toString(super.getGroup()));                //group
        myAttributes.add(2, Integer.toString(subGroup));                        //subGroup
        myAttributes.add(3, super.getType().toString());                        //type of group
        myAttributes.add(4, super.getShift().toString());                       //shift
        myAttributes.add(5, Integer.toString(super.getQuantityStudents()));     //quantityStudents
        return mergeStringVector(myAttributes, super.getSubject().toStr());          // result vector
    }
}
