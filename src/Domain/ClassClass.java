/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.Domain;


/**
 * ClassClass represents the set of students that share a same schedule
 * @author Sergio
 */
public abstract class ClassClass {
    
    // Members
        
    public enum ClassType {
        LABORATORY, THEORY, PROBLEMS
    }
    
    private String identifier;
    private int group;
    private Subject subject;
    private ClassType type;
    

    // Constructors

    /**
     * Class constructor specifying the member's values.
     * @param identifier Identification of the Class.
     * @param subject subject of the class.
     * @param group
     */
    public ClassClass(String identifier, Subject subject, int group, ClassType type){
        this.identifier = identifier;
        this.subject = subject;
        this.group = group;
        this.type = type;
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
     * @param identifier Identification of the class.
     */
    public void setIdentifier(String identifier) {
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
     * It returns the identification of the class.
     * @return Identification of the class.
     */
    public String getIdentifier() {
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
     * It returns the identification of the class.
     * @return Identification of the class.
     */
    public Subject getSubject() {
        return subject;
    }

    /**
     * It returns the type of the class.
     * @return type of the class.
     */
    public ClassType getType() {
        return type;
    }
}
