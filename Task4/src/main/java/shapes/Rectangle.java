package shapes;

public class Rectangle implements Shape{
    private int width;
    private int height;


    public Rectangle(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public double getAreaSize(){
        return width*height;
    }

    @Override
    public String toString() {
        return "Rectangle{" +
                "width=" + width +
                ", height=" + height +
                '}';
    }
}
