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
     * Class constructor.
     */
    public ProblemsClass(){

    }

    /**
     * Class constructor specifying the member's values.
     * @param Subidentifier Identificator of the SubClass.
     */
    public ProblemsClass(int Subidentifier){
        this.Subidentifier = Subidentifier;
    }


    // Methods

    /**
     * Set the identification of a given class.
     * @param Subidentifier Identificator of the Subclass.
     */
    public void setIdentifier (int Subidentifier) {
        this.Subidentifier = Subidentifier;
    }

    /**
     * It returns the identificator of the class.
     * @return Identificator of the Subclass.
     */
    public int getIdentifier() {
        return Subidentifier;
    }

}
