package src.Domain;

import java.util.ArrayList;

public class SubjectsSet {
    /**
     * SubjectsSet class implements a set of subjects to work with them together.
     * @author joaquimgomez
     */


    public enum typeSet {
        ofCurriculum,
        ofSchedule
    }


    // Members

    typeSet tySet;
    // DEFINICIÓN DEL SET


    // Constructors

    /**
     * Class constructor.s
     */
    public SubjectsSet() {

    }

    /**
     * Class constructor specifying the member's values.
     * @param subjects Array list with subjects.
     * @param tySet Type of set.
     */
    public SubjectsSet(ArrayList<SubjectsSet> subjects, typeSet tySet){
       this.tySet = tySet;
       ///
        //
        // DECIDIR IMPLEMENTACIÓN.
        //
       ///
    }

    // Methods
}
