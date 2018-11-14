package src.domain.classes;

import jdk.jshell.execution.Util;
import src.domain.utils.UtilsDomain;

import java.util.Vector;

public abstract class Classroom {

    /**
     * Classroom represents a classroom of type LABORATORY or THEORY
     * @author mireia
     */

    //ATTRIBUTES
    private String name;
    private int capacity;
    private UtilsDomain.ClassType type; //crear ints pels tipus (LABORATORI, TEORIA)
    private boolean multimedia;

    //CONSTRUCTOR

    /**
     * Empty Classroom constructor
     */
    public Classroom() {

    }

    /**
     * Classroom constructor
     * @param n name of the classroom
     * @param cap capacity of the classroom
     * @param t type of the classroom (Laboratory or Theory)
     * @param m if the classroom has a multimedia system
     */
    public Classroom(String n, int cap, UtilsDomain.ClassType t, boolean m) {
        name = n;
        capacity = cap;
        type = t;
        multimedia = m;
    }

    /**
     * Classroom constructor from String
     * @param parse Vector of String Objects which encodes the attributes of this Classroom instance
     */
    public Classroom(Vector<String> parse) {
        name = parse.get(0);
        capacity = Integer.parseInt(parse.get(1));
        type = UtilsDomain.ClassType.valueOf(parse.get(2));
        multimedia = (parse.get(3).equals("true"));
    }

    /**
     * Constructor from String
     * @param c Vector of Strings which represents the Classroom Object
     * @return returns a, instance of Classroom
     */
    public static Classroom fromStr(Vector<String> c) {
        Classroom aux;
        switch(c.get(2).toUpperCase()) {
            case "LABORATORY":
                aux = new LabClassroom(c);
                break;
            case "THEORY":
                aux = new TheoryClassroom(c);
                break;
            default:
                aux = null;
                break;
        }
        return aux;
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
    public UtilsDomain.ClassType getType() {
        return type;
    }

    /**
     * Setter of the type attribute
     * @param type the type of the classroom
     */
    public void setType(UtilsDomain.ClassType type) {
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
     * @param multimedia indicates if the class has multimedia system
     */
    public void setMultimedia(boolean multimedia) {
        this.multimedia = multimedia;
    }

    //PUBLIC METHODS

    /**
     * Transforms the Classroom Object into a Vector of Strings
     * @return returns a Vector of Strings with the attributes encoded
     */
    public Vector<String> toStr() {

        Vector<String> vec = new Vector<String> (5);
        vec.add(name);
        vec.add(String.valueOf(capacity));
        vec.add(type.toString());
        vec.add((multimedia?"true":"false"));
        vec.add("0");
        return vec;
    }
    

}
