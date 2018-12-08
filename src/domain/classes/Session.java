/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.domain.classes;

import src.domain.utils.UtilsDomain;

import java.util.Vector;

/**
 * SessionClass represent the hour and day of a Session
 * @author Sergio Mazzariol
 */
public class Session {

    // MEMBERS----------------------------------------------------------

    public static final int hoursPerDay = 12;
    public static final int startHour = 8;
    public static final int daysOfTheWeek = 5;

    private int hour;
    private UtilsDomain.Day day;

    // CONSTRUCTORS----------------------------------------------------

    /**
     * Empty Class constructor.
     */
    public Session() {
    }

    /**
     * Class constructor specifying the member's values.
     * @param day Day of the week
     * @param hour Hour of the day
     */
    public Session(UtilsDomain.Day day, int hour) {
        this.hour = hour;
        this.day = day;
    }

    /**
     * Class constructor specifying the member's values.
     * @param myVector Vector with all session information, one attribute per position.
     */
    public Session( Vector<String> myVector ) {
        hour = Integer.parseInt(myVector.get(0));
        day = UtilsDomain.Day.valueOf(myVector.get(1));
    }

    // METHODS--------------------------------------------------------
    /**
     * It returns the hour of the session.
     * @return hour of the session.
     */
    public int getHour() {
        return hour;
    }

    /**
     * Set the hour of the session.
     * @param hour Hour of the session
     */
    public void setHour(int hour) { this.hour = hour; }

    /**
     * It returns the day of the session.
     * @return day of the session.
     */
    public UtilsDomain.Day getDay() {
        return day;
    }

    /**
     * Set the Day of the Session.
     * @param day Day to set
     */
    public void setDay(UtilsDomain.Day day) {
        this.day = day;
    }

    /**
     * It returns a vector of strings with the members' values.
     * @return Vector of strings with the members' values.
     */
    public Vector<String> toStr(){
        Vector<String> myVector = new Vector<>(2);
        myVector.add(0, String.valueOf(hour));
        myVector.add(1, day.toString());
        return myVector;
    }

    /**
     * Implements the different comparisons between two classes,
     * considering the group and the name of these.
     * @param s1 First class to compare.
     * @param op Operator of the comparison.
     * @param s2 Second class to compare.
     * @return Result of the comparison.
     */
    public static boolean compare( Session s1, String op, Session s2  ) {
        if (op.equals("<"))     return ( s1.getDay().ordinal() < s2.getDay().ordinal() ) || ( s1.getHour() < s2.getHour() && s1.getDay().ordinal() == s2.getDay().ordinal() );
        if (op.equals(">"))     return ( s1.getDay().ordinal() > s2.getDay().ordinal() ) || ( s1.getHour() > s2.getHour() && s1.getDay().ordinal() == s2.getDay().ordinal() );
        if (op.equals("<="))    return ( s1.getDay().ordinal() < s2.getDay().ordinal() ) || ( s1.getHour() <= s2.getHour() && s1.getDay().ordinal() == s2.getDay().ordinal() );
        if (op.equals(">="))    return ( s1.getDay().ordinal() > s2.getDay().ordinal() ) || ( s1.getHour() >= s2.getHour() && s1.getDay().ordinal() == s2.getDay().ordinal() );
        if (op.equals("!="))    return s1.getDay().ordinal() != s2.getDay().ordinal() || s1.getHour() != s2.getHour();
        if (op.equals("=="))    return s1.getDay().ordinal() == s2.getDay().ordinal() && s1.getHour() == s2.getHour();

        return false;
    }

    /**
     * Check if to sessions are consecutive, same day an one hour of difference between them.
     * @param s1 Second Session to compare whit
     * @return true if the session are consecutive with 1 hour of difference, false if they are equal or not consecutive
     */
    public boolean neighbor(Session s1){
        return day.ordinal() == s1.getDay().ordinal() && ( (hour-1) == s1.getHour() || (hour+1) == s1.getHour() );
    }
}
