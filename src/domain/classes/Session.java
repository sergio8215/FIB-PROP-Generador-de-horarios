/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.domain.classes;

import src.domain.utils.UtilsDomain;

import java.util.Vector;

/**
 * Session class
 *
 * @author Sergio
 */
public class Session {

    //Members

    public static final int hoursPerDay = 12;
    public static final int startHour = 8;
    public static final int daysOfTheWeek = 5;

    private int hour;
    private UtilsDomain.Day day;

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
    public Session(UtilsDomain.Day day, int hour) {
        this.hour = hour;
        this.day = day;
    }


    public Session( Vector<String> myVector ) {
        hour = Integer.parseInt(myVector.get(0));
        day = UtilsDomain.Day.valueOf(myVector.get(1));
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
    public UtilsDomain.Day getDay() {
        return day;
    }

    /**
     * Set the name of the curriculum.
     *
     * @param day
     */
    public void setDay(UtilsDomain.Day day) {
        this.day = day;
    }

    public Vector<String> toStr(){
        Vector<String> myVector = new Vector<>(2);
        myVector.add(0, String.valueOf(hour));
        myVector.add(1, day.toString());
        return myVector;
    }

    public static boolean compare( Session s1, String op, Session s2  ) {
        if (op.equals("<"))     return ( s1.getDay().ordinal() < s2.getDay().ordinal() ) || ( s1.getHour() < s2.getHour() && s1.getDay().ordinal() == s2.getDay().ordinal() );
        if (op.equals(">"))     return ( s1.getDay().ordinal() > s2.getDay().ordinal() ) || ( s1.getHour() > s2.getHour() && s1.getDay().ordinal() == s2.getDay().ordinal() );
        if (op.equals("<="))    return ( s1.getDay().ordinal() <= s2.getDay().ordinal() ) || ( s1.getHour() <= s2.getHour() && s1.getDay().ordinal() == s2.getDay().ordinal() );
        if (op.equals(">="))    return ( s1.getDay().ordinal() >= s2.getDay().ordinal() ) || ( s1.getHour() >= s2.getHour() && s1.getDay().ordinal() == s2.getDay().ordinal() );
        if (op.equals("!="))    return s1.getDay().ordinal() != s2.getDay().ordinal() || s1.getHour() != s2.getHour();
        if (op.equals("=="))    return s1.getDay().ordinal() == s2.getDay().ordinal() && s1.getHour() == s2.getHour();

        return false;
    }
}
