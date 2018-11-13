/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.domain;

import java.util.Vector;

/**
 * Session class
 *
 * @author Sergio
 */
public class Session {

    //Members
    //store days of the week

    public enum Day {
        MONDAY,     // ordinal value: 0
        TUESDAY,    // ordinal value: 1
        WEDNESDAY,  // ordinal value: 2
        THURSDAY,   // ordinal value: 3
        FRIDAY,     // ordinal value: 4
        SATURDAY,   // ordinal value: 5
        SUNDAY      // ordinal value: 6
    }

    public static final int hoursPerDay = 12;
    public static final int startHour = 8;
    public static final int daysOfTheWeek = 5;



    private int hour;
    private Day day;
    private Vector<Vector<Integer>> week;

    //Constructor
    /**
     * Class constructor.
     */
    public Session() {
        /*week = new Vector<Vector<Integer>>(daysOfTheWeek);

        // For each day of the week
        for ( int i = 0; i < daysOfTheWeek; i++){

            // We create a vector for each day of the week
            week.add( i, new Vector<>(hoursPerDay));
            for ( int j = 0 ; j < hoursPerDay; j++ ) {
                // For each hour we initialize the vector
                week.elementAt(i).add(startHour + j);
            }
        }*/
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
     * It returns the days and hours of the week inside a matrix.
     * @return a matrix with days of the week(first column) and hours.
     */
    public Vector<Vector < Integer>> getWeek() {
        return week;
    }


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

    public static boolean compare( Session s1, String op, Session s2  ) {
        if (op.equals("<"))     return ( s1.getDay().ordinal() < s2.getDay().ordinal() ) || ( s1.getHour() < s2.getHour() && s1.getDay().ordinal() == s2.getDay().ordinal() );
        if (op.equals(">"))     return ( s1.getDay().ordinal() > s2.getDay().ordinal() ) || ( s1.getHour() > s2.getHour() && s1.getDay().ordinal() == s2.getDay().ordinal() );
        if (op.equals("<="))    return ( s1.getDay().ordinal() <= s2.getDay().ordinal() ) || ( s1.getHour() <= s2.getHour() && s1.getDay().ordinal() == s2.getDay().ordinal() );
        if (op.equals(">="))    return ( s1.getDay().ordinal() >= s2.getDay().ordinal() ) || ( s1.getHour() >= s2.getHour() && s1.getDay().ordinal() == s2.getDay().ordinal() );
        if (op.equals("!="))    return !s1.equals(s2);
        if (op.equals("=="))    return s1.equals(s2);

        return false;
    }
}
