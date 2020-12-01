package ClassType;

public class SharedBikeUser {
    String name;
    SharedBikeUser(String name) {
        this.name = name;
    }
    static class Bike {
        // String owner = name;    // compile error
        String key;
        void lock() {
        }
    }
}
