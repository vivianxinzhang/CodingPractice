package Design;

public class Rectangle implements Shape {
    public Rectangle() {
        // ...
    }

    @Override
    public void draw(int i) {
        System.out.println("Inside Rectangle::draw() method.");
    }
}
