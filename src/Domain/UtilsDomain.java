package src.Domain;

public class UtilsDomain {
    public enum ClassType {
        THEORY,
        LABORATORY,
        PROBLEMS
    }

    public static final class ResultOfQuery<T> {
        public boolean queryTest;
        public T resoult;
    }
}
