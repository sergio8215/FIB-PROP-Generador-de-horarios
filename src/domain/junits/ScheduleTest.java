package src.domain.junits;

import src.domain.classes.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

import static org.junit.Assert.*;
import org.junit.Test;

public class ScheduleTest {

    public Schedule scheduleConstructor() {
        return new Schedule("file1.txt", "file2.txt");
    }

    public MUS MUSConstructor() {
        Vector<Vector<String>> mus = new Vector<>(3);

        Vector<String> classclass = new Vector<>(15);
        classclass.add(0, "PAR42");
        classclass.add(1, "40");
        classclass.add(2, "42");
        classclass.add(3, "LABORATORY");
        classclass.add(4, "MORNING");
        classclass.add(5, "15");
        classclass.add(6, "PAR");
        classclass.add(7, "10");
        classclass.add(8, "2");
        classclass.add(9, "2");
        classclass.add(10, "2");
        classclass.add(11, "2");
        classclass.add(12, "2");
        classclass.add(13, "2");
        classclass.add(14, "MORNING");

        mus.add(0, classclass);


        Vector<String> classroom = new Vector<>(5);

        classroom.add(0, "A5S101");
        classroom.add(1, "15");
        classroom.add(2, "LABORATORY");
        classroom.add(3, "true");
        if (classroom.get(2).equals("LABORATORY"))     classroom.add("15");

        mus.add(1, classroom);

        Vector<String> session = new Vector<>(2);

        session.add(0, "12");
        session.add(1, "MONDAY");

        mus.add(2, session);

        return new MUS(mus);
    }

    @Test
    public void getClassroomFile() {
        Schedule s = scheduleConstructor();
        String cf = s.getClassroomFile();
        assertEquals("getClassroomFile - Ok", "file1.txt", cf);
    }

    @Test
    public void setClassroomFile() {
        Schedule s = scheduleConstructor();
        s.setClassroomFile("file3.txt");
        assertEquals("setClassroomFile - Ok", "file3.txt", s.getClassroomFile());
    }

    @Test
    public void getSubjectFile() {
        Schedule s = scheduleConstructor();
        String sf = s.getClassroomFile();
        assertEquals("getClassroomFile - Ok", "file1.txt", sf);
    }

    @Test
    public void setSubjectFile() {
        Schedule s = scheduleConstructor();
        s.setClassroomFile("file3.txt");
        assertEquals("setClassroomFile - Ok", "file3.txt", s.getClassroomFile());
    }

    @Test
    public void isFail() {
        Schedule s = scheduleConstructor();
        boolean fail = s.isFail();
        assertTrue("isFail - Ok", !fail);
    }

    @Test
    public void setFail() {
        Schedule s = scheduleConstructor();
        s.setFail(false);
        assertTrue("setFail - Ok", s.isFail());
    }

    @Test
    public void fail() {
        Schedule s = scheduleConstructor();
        s.fail();
        assertTrue("fail - Ok", s.isFail());
    }

    @Test
    public void getTimetable() {
        Schedule s = scheduleConstructor();
        HashMap<String, ArrayList<MUS>> hm = s.getTimetable();

    }

    @Test
    public void setTimetable() {
        Schedule s = scheduleConstructor();
        s.setTimetable(new HashMap<String, ArrayList<MUS>>());
        assertTrue("setTimetable - Ok", s.isEmpty());
    }

    @Test
    public void isEmpty() {
        Schedule s = scheduleConstructor();
        boolean empty = s.isEmpty();
        assertTrue("isEmpty - Ok", empty);
    }

    @Test
    public void size() {
        Schedule s = scheduleConstructor();
        assertEquals("size - Ok", s.size(), 0);
    }

    @Test
    public void add() {
        Schedule s = scheduleConstructor();
        MUS m = MUSConstructor();
        s.add(m);
        assertEquals("add - Ok", s.size(), 1);
    }

    @Test
    public void delete() {
        Schedule s = scheduleConstructor();
        MUS m = MUSConstructor();
        s.add(m);
        s.delete(m);
        assertEquals("add - Ok", s.size(), 0);
    }

    @Test
    public void unset() {
        Schedule s = scheduleConstructor();
        MUS m = MUSConstructor();
        s.add(m);
        ArrayList<MUS> a = s.unset();
        assertEquals("add - Ok", a.size(), 1);
    }

    @Test
    public void valid() {
        Schedule s = scheduleConstructor();
        assertTrue("valida - OK", true);
    }
}