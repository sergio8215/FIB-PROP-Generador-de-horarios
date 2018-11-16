package src.domain.junits;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class TestSchedule {

    public static void main (String[] args) {
        Result result = JUnitCore.runClasses(ScheduleTest.class);

        for (Failure f : result.getFailures()) {
            System.out.println(f.toString());
        }

        if (result.wasSuccessful()) {
            System.out.println("OK!");
        }
    }
}
