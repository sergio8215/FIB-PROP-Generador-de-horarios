package src.Domain;

public abstract class ClassroomClass {

    private String name;
    private int capacity;
    private int type; //crear ints pels tipus (LABORATORI, TEORIA)
    private boolean multimedia;


    ClassroomClass(String n, int cap, int t, boolean m) {
        name = n;
        capacity = cap;
        type = t;
        multimedia = m;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public boolean isMultimedia() {
        return multimedia;
    }

    public void setMultimedia(boolean multimedia) {
        this.multimedia = multimedia;
    }
}
