package pcd.assignment01.concurrent.common;

import pcd.assignment01.concurrent.model.Vector2D;

/**
 * This class represents a 2D point
 */
public class Point2D {

    private final double x;
    private final double y;

    /**
     * @param x position of the Point2D on the X axis
     * @param y position of the Point2D on the Y axis
     */
    public Point2D(final double x, final double y){
        this.x = x;
        this.y = y;
    }

    /**
     * @param v vector to sum so the Point2D
     * @return
     */
    public Point2D sum(final Vector2D v) {
        return new Point2D(x + v.getX(), y + v.getY());
    }

    /**
     * @return x of the Point2D
     */
    public double getX() {
    	return x;
    }


    /**
     * @return y of the Point2D
     */
    public double getY() {
    	return y;
    }
}
