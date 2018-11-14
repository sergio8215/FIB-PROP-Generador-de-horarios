/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.domain.classes;

import src.domain.utils.UtilsDomain;
import sun.plugin.com.Utils;

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
    public LaboratoryClass(String identifier, int subGroup, Subject subject, int group, int quantityStudents, UtilsDomain.TimeZone shift){
        super(identifier, subject, group, quantityStudents, shift, UtilsDomain.ClassType.LABORATORY);
        this.subGroup = subGroup;
    }

    /**
     * Class constructor specifying the member's values.
     * @param myStringVector Identification of the Class.
     */
    public LaboratoryClass( Vector<String> myStringVector ) {
        super(myStringVector, UtilsDomain.ClassType.LABORATORY);
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
