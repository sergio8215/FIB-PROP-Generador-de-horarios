/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.domain;

import src.domain.utils.UtilsDomain;

import java.util.Vector;

/**
 *
 * @author Sergio
 */
public class TheoryClass extends ClassClass{

    // Members
    private int subGroup;
    

    // Constructors

    /**
     * Class constructor specifying the member's values.
     * @param identifier
     * @param subGroup Identificator of the SubClass.
     * @param subject
     * @param group
     */
    public TheoryClass(String identifier, int subGroup, Subject subject, int group, int quantityStudents, UtilsDomain.TimeZone shift){
        super(identifier, subject, group, quantityStudents, shift, ClassType.THEORY );
        this.subGroup = subGroup;
    }

    /**
     * Class constructor specifying the member's values.
     * @param myStringVector Identification of the Class.
     */
    public TheoryClass( Vector<String> myStringVector ) {
        super(myStringVector, ClassType.THEORY);
        int sg = Integer.parseInt(myStringVector.get(2)); // subgroup
        subGroup = sg;
    }

    // Methods

    /**
     * 
     * @param subGroup
     */
    public void setsubGroup(int subGroup) {
        this.subGroup = subGroup;
    }
    
    /**
     * 
     * @return 
     */
    public int getsubGroup() {
        return subGroup;
    }

    /**
     * It returns a vector of strings with the members' values.
     * @return Vector of strings with the members' values.
     */
    @Override
    public Vector<String> toStr() {
        Vector<String> myAttributes = new Vector<String>(5);

        myAttributes.set(0, super.getIdentifier());                             //identifier
        myAttributes.set(1, Integer.toString(super.getGroup()));                //group
        myAttributes.set(2, Integer.toString(subGroup));                        //subGroup
        myAttributes.set(3, Integer.toString(super.getType().ordinal()));       //type of group
        myAttributes.set(4, Integer.toString(super.getShift().ordinal()));      //shift
        myAttributes.set(5, Integer.toString(super.getQuantityStudents()));     //quantityStudents
        return mergeStringVector(myAttributes, super.getSubject().toStr());     // result vector
    }
}
