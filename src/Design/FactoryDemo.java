package Design;

public class FactoryDemo {
    private final ShapeFactory shapeFactory = new ShapeFactory();

    public void demo(String[] args) {
        // get an object of Circle and call its draw method.
        Shape shape1 = shapeFactory.getShape("CIRCLE");
        // call draw method of Circle
        shape1.draw(10);

        // get an object of RECTANGLE and call its draw method.
        Shape shape2 = shapeFactory.getShape("RECTANGLE");
        // call draw method of Circle
        shape1.draw(10);

        // get an object of Circle and call its draw method.
        Shape shape3 = shapeFactory.getShape("SQUARE");
        // call draw method of Circle
        shape1.draw(10);
    }
}
