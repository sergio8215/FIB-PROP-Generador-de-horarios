/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.domain.classes;

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
     * @param subject
     * @param group
     * @param quantityStudents
     * @param shift
     * @param subGroup
     */
    public TheoryClass(String identifier, Subject subject, int group, int quantityStudents, UtilsDomain.typeShift shift, int subGroup){
        super(identifier, subject, group, quantityStudents, shift, UtilsDomain.ClassType.THEORY );
        this.subGroup = subGroup;
    }

    /**
     * Class constructor specifying the member's values.
     * @param myStringVector Identification of the Class.
     */
    public TheoryClass( Vector<String> myStringVector ) {
        super(myStringVector, UtilsDomain.ClassType.THEORY);
        int sg = Integer.parseInt(myStringVector.get(2)); // subgroup
        subGroup = sg;
    }

    // Methods

    /**
     * 
     * @param subGroup
     */
    @Override
    public void setSubGroup(int subGroup) {
        this.subGroup = subGroup;
    }
    
    /**
     * 
     * @return 
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
        return mergeStringVector(myAttributes, super.getSubject().toStr());     // result vector
    }
}
