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

import pcd.assignment01.concurrent.model.exception.NullVectorException;

public class Vector2D {

    private final double x;
    private final double y;

    public Vector2D(final double x, final double y){
        this.x = x;
        this.y = y;
    }

    public Vector2D(final Vector2D v){
        this.x = v.x;
        this.y = v.y;
    }

    public Vector2D(final Point2D from, final Point2D to){
        this.x = to.getX() - from.getX();
        this.y = to.getY() - from.getY();
    }

    public Vector2D scalarMul(final double k) {
        return new Vector2D(x * k, y * k);
    }
    
    public Vector2D sum(final Vector2D v) {
        return new Vector2D(x + v.x, y + v.y);
    }
    
    public Vector2D normalize() throws NullVectorException {
    	double mod =  Math.sqrt(x * x + y * y);
    	if (mod > 0) {
            return new Vector2D(x / mod, y / mod);
    	} else {
    		throw new NullVectorException();
    	}

    }
    
    public double getX() {
    	return x;
    }

    public double getY() {
    	return y;
    }
    
    
}
