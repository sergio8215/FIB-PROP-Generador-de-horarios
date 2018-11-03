/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain;

/**
 *
 * @author Sergio
 */
public class ProblemsClass extends ClassClass{

    // Members
    private int Subidentifier;
    

    // Constructors

    /**
     * Class constructor specifying the member's values.
     * @param identifier
     * @param Subidentifier Identificator of the SubClass.
     * @param subject
     * @param group
     */
    public ProblemsClass(int identifier, int Subidentifier, Subject subject, int group){
        super( identifier, subject, group);
        this.Subidentifier = Subidentifier;
    }


    // Methods

    /**
     * 
     * @param Subidentifier 
     */
    public void setSubidentifier(int Subidentifier) {
        this.Subidentifier = Subidentifier;
    }

    /**
     * 
     * @return 
     */
    public int getSubidentifier() {
        return Subidentifier;
    }

   
}
