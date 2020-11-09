package Design;

public class Circle implements Shape {
    // attributes, constructor method ...
    public Circle() {
        // ...
    }

    @Override
    public void draw(int i) {
        System.out.println("Inside Circle::draw() method.");
    }
}