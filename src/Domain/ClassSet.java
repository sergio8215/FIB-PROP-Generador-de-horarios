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
    
    // Attributes
    
    //private ArrayList<Constraints> constraints; 
    private HashMap<Integer, ClassClass> classSet;

    
    // Constructors

    /**
    * Class constructor.
    */    
    public ClassSet() {
        classSet = new HashMap<>();
      //  constraints = new ArrayList<Constraints>;
    }

    /**
    * Class constructor specifying the member's values.
     * @param classes
    */    
    public ClassSet(ArrayList<ClassClass> classes) {
       this.classSet = new HashMap<>(classes.size());
       createSet(classes);
    }

    // Methods

    /**
    *
    */
    private void createSet( ArrayList<ClassClass> classes ) {
        for ( ClassClass c : classes ) classSet.put(c.getIdentifier(), c);
    }

    /**
     * @param constraints
         
    public void setConstraints(ArrayList<Constraints> constraints) {
        this.constraints = constraints;
    }*/

    /**
     * @param classSet
     */    
    public void setClassSet(HashMap classSet) {
        this.classSet = classSet;
    }
    
    /**
     * @return    
        
    public ArrayList<Constraints> getConstraints() {
        return constraints;
    }*/ 
    
    /**
     * @return    
     */ 
    public HashMap getClassSet() {
        return classSet;
    }
}
