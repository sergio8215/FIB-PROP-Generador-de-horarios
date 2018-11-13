package src.Domain;

public class UtilsDomain {
    public enum ClassType {
        THEORY,
        LABORATORY,
        PROBLEMS
    }

    public static final class Pair<T,U> {
        public T first;
        public U second;

        public  Pair(T first, U second){
            this.first = first;
            this.second = second;
        }
    }

}
