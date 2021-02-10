import shapes.*;
import sorting.SortShapes;
import java.util.ArrayList;
import java.util.List;

public class Main   {
    public static void main(String[] args) {
        List<Shape> shapes = new ArrayList<>();
        shapes.add(new Circle(3));
        shapes.add(new Square(1));
        shapes.add(new Square(12));
        shapes.add(new Rectangle(2,3));
        shapes.add(new Triangle(3,1));
        shapes.add(new Square(4));

        SortShapes.sortShapesByAreaSize(shapes);
        System.out.println(shapes);
    }
}
