package pcd.assignment01.concurrent.model;

public class Point2D {

    private final double x;
    private final double y;

    public Point2D(final double x, final double y){
        this.x = x;
        this.y = y;
    }

    public Point2D sum(final Vector2D v) {
        return new Point2D(x + v.getX(), y + v.getY());
    }

    public double getX() {
    	return x;
    }

    public double getY() {
    	return y;
    }
}
