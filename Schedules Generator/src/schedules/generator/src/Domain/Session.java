/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schedules.generator.src.Domain;

/**
 * Session class
 *
 * @author Sergio
 */
public class Session {

    //Members
    //store days of the week
    private static final int MONDAY = 1;
    private static final int TUESDAY = 2;
    private static final int WEDNESDAY = 3;
    private static final int THURSDAY = 4;
    private static final int FRIDAY = 5;
    private static final int SATURDAY = 6;
    private static final int SUNDAY = 7;
    private int day;
    private int hour;

    //Constructor
    /**
     * Class constructor.
     */
    public Session() {
    }

    /**
     * Class constructor specifying the member's values.
     *
     * @param Day of the week
     * @param Hour of the session
     */
    public Session(int day, int hour) {
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
     * @param name Name of the curriculum.
     */
    public void setHour(int hour) {
        this.hour = hour; // Poner expeción si el valor no esta entre 0 y 23
    }

    /**
     * It returns the day of the session.
     *
     * @return day of the session.
     */
    public int getDay() {
        return day;
    }

    /**
     * Set the name of the curriculum.
     *
     * @param name Name of the curriculum.
     */
    public void setDay(int day) {
        this.day = day; // Poner expeción si el valor no esta entre 1 y 7
    }

}
