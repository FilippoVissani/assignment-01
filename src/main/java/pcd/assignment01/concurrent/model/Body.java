package pcd.assignment01.concurrent.model;

import pcd.assignment01.concurrent.model.exception.InfiniteForceException;

public class Body {
    
	private static final double REPULSIVE_CONST = 0.01;
	private static final double FRICTION_CONST = 1;
    private Point2D position;
    private Vector2D speed;
    private final double mass;
    private final int id;
    
    public Body(final int id, final Point2D position, final Vector2D speed, final double mass){
    	this.id = id;
        this.position = position;
        this.speed = speed;
        this.mass = mass;
    }

    public Point2D getPosition() {
        return position;
    }

    public Vector2D getSpeed() {
        return speed;
    }

    public double getMass() {
        return mass;
    }

    public int getId() {
        return id;
    }

    public boolean equals(final Object body) {
    	return ((Body)body).id == id;
    }
    
    public void updatePosition(final double dt){
    	position = position.sum(speed.scalarMul(dt));
    }
    
    public void updateSpeed(final Vector2D acceleration, final double dt){
    	speed = speed.sum(acceleration.scalarMul(dt));
    }
    
    public void changeSpeed(final double vx, final double vy){
    	speed = new Vector2D(vx, vy);
    }
    
    public double getDistanceFrom(final Body body) {
    	double dx = position.getX() - body.getPosition().getX();
    	double dy = position.getY() - body.getPosition().getY();
    	return Math.sqrt(dx * dx + dy * dy);
    }
    
    public Vector2D computeRepulsiveForceBy(final Body body) throws InfiniteForceException {
		double distance = getDistanceFrom(body);
		if (distance > 0) {
			try {
				return new Vector2D(body.getPosition(), position)
                        .normalize()
                        .scalarMul(body.getMass() * REPULSIVE_CONST / (distance * distance));
			} catch (Exception ex) {
				throw new InfiniteForceException();
			}
		} else {
			throw new InfiniteForceException();
		}
    }
    
    public Vector2D getCurrentFrictionForce() {
        return speed.scalarMul(-FRICTION_CONST);
    }
    
    public void checkAndSolveBoundaryCollision(final Boundary bounds){
    	final double x = position.getX();
        final double y = position.getY();
        if (x > bounds.getX1()){
            position = new Point2D(bounds.getX1(), position.getY());
            speed = new Vector2D(-speed.getX(), speed.getY());
        } else if (x < bounds.getX0()){
            position = new Point2D(bounds.getX0(), position.getY());
            speed = new Vector2D(-speed.getX(), speed.getY());
        } else if (y > bounds.getY1()){
            position = new Point2D(position.getX(), bounds.getY1());
            speed = new Vector2D(speed.getX(), -speed.getY());
        } else if (y < bounds.getY0()){
            position = new Point2D(position.getX(), bounds.getY0());
            speed = new Vector2D(speed.getX(), -speed.getY());
        }
    }
}
