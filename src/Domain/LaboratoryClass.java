/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.Domain;

import java.util.Vector;

/**
 *
 * @author Sergio
 */
public class LaboratoryClass extends ClassClass {

    // MEMBERS------------------------------------------------------------------

    private int subGroup;

    // CONSTRUCTORS--------------------------------------------------------------

    /**
     * Class constructor specifying the member's values.
     * @param identifier
     * @param subGroup Identificator of the SubClass.
     * @param subject
     * @param group
     */
    public LaboratoryClass(String identifier, int subGroup, Subject subject, int group){
        super(identifier, subject, group, ClassType.LABORATORY);
        this.subGroup = subGroup;
    }

    /**
     * Class constructor specifying the member's values.
     * @param myStringVector Identification of the Class.
     */
    public LaboratoryClass( Vector<String> myStringVector ) {
        super(myStringVector, ClassType.LABORATORY);
        int sg = Integer.parseInt(myStringVector.get(2)); // subgroup
        subGroup = sg;
    }

    // METHODS--------------------------------------------------------------------

    /**
     * @return 
     */
    public int getsubGroup() {
        return subGroup;
    }

    /**
     * @param subGroup
     */
    public void setsubGroup(int subGroup) {
        this.subGroup = subGroup;
    }

    /**
     * It returns a vector of strings with the members' values.
     * @return Vector of strings with the members' values.
     */
    @Override
    public Vector<String> toStr() {
        Vector<String> myAttributes = new Vector<String>(4);

        myAttributes.set(0, super.getIdentifier());
        myAttributes.set(1, Integer.toString(super.getGroup()));
        myAttributes.set(2, Integer.toString(subGroup));
        myAttributes.set(3, Integer.toString(super.getType().ordinal()));
        return mergeStringVector(myAttributes, super.getSubject().toString());
    }

}
