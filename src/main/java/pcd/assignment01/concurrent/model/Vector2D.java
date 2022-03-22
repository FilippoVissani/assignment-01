/*
 *   V2d.java
 *
 * Copyright 2000-2001-2002  aliCE team at deis.unibo.it
 *
 * This software is the proprietary information of deis.unibo.it
 * Use is subject to license terms.
 *
 */
package pcd.assignment01.concurrent.model;

import pcd.assignment01.concurrent.util.Point2D;
import pcd.assignment01.concurrent.model.exception.NullVectorException;

/**
 * This class represents a 2D vector
 */
public class Vector2D {

    private final double x;
    private final double y;

    /**
     * @param x of the new Vector2D
     * @param y of the new Vector2D
     */
    public Vector2D(final double x, final double y){
        this.x = x;
        this.y = y;
    }

    /**
     * @param from Point2D that represent the start of the new Vector2D
     * @param to Point2Dthat represent the end of the new Vector2D
     */
    public Vector2D(final Point2D from, final Point2D to){
        this.x = to.getX() - from.getX();
        this.y = to.getY() - from.getY();
    }

    /**
     * @param k scalar by which the vector must be multiplied
     * @return a new Vector2D
     */
    public Vector2D multiplyByScalar(final double k) {
        return new Vector2D(x * k, y * k);
    }

    /**
     * @param v vector to sum
     * @return new Vector2D
     */
    public Vector2D sum(final Vector2D v) {
        return new Vector2D(x + v.x, y + v.y);
    }

    /**
     * @return new normalized Vector2D starting from the current
     * @throws NullVectorException
     */
    public Vector2D normalize() throws NullVectorException {
    	double mod = Math.sqrt(x * x + y * y);
    	if (mod > 0) {
            return new Vector2D(x / mod, y / mod);
    	} else {
    		throw new NullVectorException();
    	}
    }

    /**
     * @return x of the Vector2D
     */
    public double getX() {
    	return x;
    }

    /**
     * @return y of the Vector2D
     */
    public double getY() {
    	return y;
    }
}
