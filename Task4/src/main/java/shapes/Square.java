package shapes;

public class Square implements Shape{
    private int side;

    public Square(int side) {
        this.side = side;
    }


    @Override
    public double getAreaSize() {
        return side*side;
    }

    @Override
    public String toString() {
        return "Square{" +
                "side=" + side +
                '}';
    }
}
