import src.domain.classes.ClassClass;
import src.domain.classes.Subject;

import java.util.Vector;

public class StubClassClass extends ClassClass {

    private int subGroup;
    /**
     * Class constructor specifying the member's values.
     * @param identifier
     * @param subGroup Identificator of the SubClass.
     * @param subject
     * @param group
     */
    public StubClassClass(String identifier, int subGroup, Subject subject, int group){
        super( identifier, subject, group, ClassType.THEORY );
        this.subGroup = subGroup;
    }
    /**
     * It returns a vector of strings with the members' values.
     * @return Vector of strings with the members' values.
     */
    @Override
    public Vector<String> toStr() {
        Vector<String> myAttributes = new Vector<String>(4);

        myAttributes.set(0, super.getIdentifier());
        myAttributes.set(1, Integer.toString(super.getGroup()));
        myAttributes.set(2, Integer.toString(subGroup));
        myAttributes.set(3, Integer.toString(super.getType().ordinal()));
        return mergeStringVector(myAttributes, super.getSubject().toString());
    }
}
