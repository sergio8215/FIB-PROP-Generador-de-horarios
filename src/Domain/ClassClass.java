/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain;

import java.util.ArrayList;

/**
 *
 * @author Sergio
 */
public class ClassClass {
    
    // Members
    private int identifier;
    private Subject subject;
    private ArrayList<Constraints> constraints; 
    

    // Constructors

    /**
     * Class constructor.
     */
    public ClassClass(){

    }

    /**
     * Class constructor specifying the member's values.
     * @param identifier Identificator of the Class.
     * @param subject subject of the class.
     * @param constraints class restrictions.
     */
    public ClassClass(int identifier, Subject subject){
        this.identifier = identifier;
        this.subject = subject;
        this.constraints = constraints;
    }


    // Methods

    /**
     * Set the identification identifier of a given class.
     * @param identifier Identificator of the class.
     */
    public void setNumber(int identifier) {
        this.identifier = identifier;
    }

    /**
     * It returns the identificator of the class.
     * @return Identificator of the class.
     */
    public int getNumber() {
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
     * It returns the identificator of the class.
     * @return Identificator of the class.
     */
    public Subject getSubject() {
        return subject;
    }

}
