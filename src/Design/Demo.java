package Design;

public class Demo {
    public void demo(String[] args) {
        Shape shape;
        String shapeType = parseFromArgs(args);
        if (shapeType.equalsIgnoreCase("CIRCLE")) {
            Circle circle = new Circle();
            circle.draw(100);
        } else if (shapeType.equalsIgnoreCase("RECTANGLE")) {
            Rectangle rect = new Rectangle();
            rect.draw(100);
        } else if (shapeType.equalsIgnoreCase("SQUARE")) {
            Square square = new Square();
            square.draw(100);
        }
    }

    private String parseFromArgs(String[] args) {
        return args[0];
    }
}
