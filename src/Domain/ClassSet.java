/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Sergio
 */
public class ClassSet {
    
    // Members
    private ArrayList<Constraints> constraints; 
    private HashMap classSet;

    
    // Constructors

    /**
    * Class constructor.
    */    
    public ClassSet() {
    }

    /**
    * Class constructor specifying the member's values.
    * @param constraints
    * @param classSet
    */    
    public ClassSet(ArrayList<Constraints> constraints, HashMap classSet) {
        this.constraints = constraints;
        this.classSet = classSet;
    }

    // Methods
    /**
     * @param constraints
     */    
    public void setConstraints(ArrayList<Constraints> constraints) {
        this.constraints = constraints;
    }

    /**
     * @param classSet
     */    
    public void setClassSet(HashMap classSet) {
        this.classSet = classSet;
    }
    
    /**
     * @return    
     */    
    public ArrayList<Constraints> getConstraints() {
        return constraints;
    }
    
    /**
     * @return    
     */ 
    public HashMap getClassSet() {
        return classSet;
    }
}
