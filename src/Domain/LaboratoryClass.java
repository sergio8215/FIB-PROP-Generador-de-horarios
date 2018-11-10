/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.Domain;

/**
 *
 * @author Sergio
 */
public class LaboratoryClass extends ClassClass {

    // Members
    private int Subgroup;
    

    // Constructors

    /**
     * Class constructor specifying the member's values.
     * @param identifier
     * @param Subgroup Identificator of the SubClass.
     * @param subject
     * @param group
     */
    public LaboratoryClass(String identifier, int Subgroup, Subject subject, int group){
        super(identifier, subject, group, ClassType.LABORATORY);
        this.Subgroup = Subgroup;
    }


    // Methods

    /**
     * @return 
     */
    public int getSubgroup() {
        return Subgroup;
    }

    /**
     * @param Subgroup
     */
    public void setSubgroup(int Subgroup) {
        this.Subgroup = Subgroup;
    }



}
