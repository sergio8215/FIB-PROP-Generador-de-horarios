package src.Domain;

public final class UtilsDomain {

    public static final class ResoultOfQuery<T> {
        public boolean queryTest;
        public T resoult;
    }

    public enum ClassType {
        THEORY,
        LABORATORY,
        PROBLEMS
    }
}
