package src.Domain;

import javax.management.loading.ClassLoaderRepository;
import java.util.Vector;

public abstract class ClassroomClass {

    /**
     * ClassroomClass represents a classroom of type LABORATORY or THEORY
     * @author mireia
     */

    public enum ClassroomType {
        THEORY,
        LABORATORY
    }

    //ATTRIBUTES
    private String name;
    private int capacity;
    private ClassroomType type; //crear ints pels tipus (LABORATORI, TEORIA)
    private boolean multimedia;

    //CONSTRUCTOR

    /**
     * ClassroomClass constructor
     * @param n name of the classroom
     * @param cap capacity of the classroom
     * @param t type of the classroom (Laboratory or Theory)
     * @param m if the classroom has a multimedia system
     */
    ClassroomClass(String n, int cap, ClassroomType t, boolean m) {
        name = n;
        capacity = cap;
        type = t;
        multimedia = m;
    }

    public ClassroomClass(Vector<String> parse) {
        name = parse.get(0);
        capacity = Integer.parseInt(parse.get(1));
        typeFromString(parse.get(2));
        multimediaFromString(parse.get(3));
    }

    //GETTERS & SETTERS

    /**
     * Getter of the name attribute
     * @return returns the name of the classroom
     */
    public String getName() {
        return name;
    }

    /**
     * Setter of the name attribute
     * @param name the name of the classroom
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter of the capacity attribute
     * @return returns the capacity of the classroom
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * Setter of the capacity attribute
     * @param capacity the maximum number of students per class
     */
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    /**
     * Getter of the type attribute
     * @return returns the type of the classroom
     */
    public ClassroomType getType() {
        return type;
    }

    /**
     * Setter of the type attribute
     * @param type
     */
    public void setType(ClassroomType type) {
        this.type = type;
    }

    /**
     * Getter of the multimedia attribute
     * @return returns true if the class has multimedia system, false otherwise
     */
    public boolean isMultimedia() {
        return multimedia;
    }

    /**
     * Setter of the multimedia attribute
     * @param multimedia
     */
    public void setMultimedia(boolean multimedia) {
        this.multimedia = multimedia;
    }

    //PRIVATE METHODS

    private String typeToString() {
        if(type == ClassroomType.LABORATORY) return "Laboratory";
        else return "Theaory";
    }

    private String multimediaToString(){
        if(multimedia) return "true";
        else return "false";
    }

    private void multimediaFromString(String m){
        if(m.equals("true")) multimedia = true;
        else multimedia = false;
    }

    private void typeFromString(String t){
        if(t.equals("Laboratory")) type = ClassroomType.LABORATORY;
        else type = ClassroomType.THEORY;
    }

    //PUBLIC METHODS

    public Vector<String> toStr() {

        Vector<String> vec = new Vector<String> (4,1);
        vec.add(name);
        vec.add(String.valueOf(capacity));
        vec.add(typeToString());
        vec.add(multimediaToString());

        return vec;
    }
    

}
