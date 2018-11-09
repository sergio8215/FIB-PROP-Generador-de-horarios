/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain;

/**
 * Session class
 *
 * @author Sergio
 */
public class Session {

    //Members
    //store days of the week
    
    public enum Day {
        MONDAY, TUESDAY, WEDNESDAY,
        THURSDAY, FRIDAY, SATURDAY
    }
    
    private int hour;
    private Day day;
    
    //Constructor
    /**
     * Class constructor.
     */
    public Session() {
    }

    /**
     * Class constructor specifying the member's values.
     *
     * @param day
     * @param hour
     */
    public Session(Day day, int hour) {
        this.hour = hour; 
        this.day = day;
    }

    //Methods
    /**
     * It returns the hour of the session.
     *
     * @return hour of the session.
     */
    public int getHour() {
        return hour;
    }

    /**
     * Set the name of the curriculum.
     *
     * @param hour
     */
    public void setHour(int hour) {
        this.hour = hour; // Poner expeción si el valor no esta entre 0 y 23
    }

    /**
     * It returns the day of the session.
     *
     * @return day of the session.
     */
    public Day getDay() {
        return day;
    }

    /**
     * Set the name of the curriculum.
     *
     * @param day
     */
    public void setDay(Day day) {
        this.day = day; // Poner expeción si el valor no esta entre 1 y 7
    }

}
