package Design;

public class SingletonExample {
    private static final SingletonExample INSTANCE = null;

    private SingletonExample() {
    }

    private static SingletonExample getInstance() {
        if (INSTANCE == null) {
            // INSTANCE = new SingletonExample();
        }
        return INSTANCE;
    }

    // other fields and methods
}
