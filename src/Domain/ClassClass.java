/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain;


/**
 * ClassClass represents the set of students that share a same schedule
 * @author Sergio
 */
public abstract class ClassClass {
    
    // Members
    private int identifier;
    private int group;
    private Subject subject;
    
    

    // Constructors

    /**
     * Class constructor specifying the member's values.
     * @param identifier Identificator of the Class.
     * @param subject subject of the class.
     * @param group
     */
    public ClassClass(int identifier, Subject subject, int group){
        this.identifier = identifier;
        this.subject = subject;
        this.group = group;
    }


    // Methods

    /**
     * Set the Group number of a given class.
     * @param group group of the class.
     */
    public void setGroup(int group) {
        this.group = group;
    }
    
    
    /**
     * Set the identification identifier of a given class.
     * @param identifier Identificator of the class.
     */
    public void setIdentifier(int identifier) {
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
     * It returns the identificator of the class.
     * @return Identificator of the class.
     */
    public int getIdentifier() {
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
