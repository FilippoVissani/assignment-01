package pcd.assignment01.concurrent.model;

import pcd.assignment01.concurrent.model.exception.InfiniteForceException;

public class Body {
    
	private static final double REPULSIVE_CONST = 0.01;
	private static final double FRICTION_CONST = 1;
    private Point2D position;
    private Vector2D speed;
    private final double mass;
    private final int id;

    /**
     * @param id of the new Body
     * @param position of the new Body
     * @param speed of the new Body
     * @param mass of the new Body
     */
    public Body(final int id, final Point2D position, final Vector2D speed, final double mass){
    	this.id = id;
        this.position = position;
        this.speed = speed;
        this.mass = mass;
    }

    /**
     * @return the current position of the body
     */
    public Point2D getPosition() {
        return position;
    }

    /**
     * @return the current speed of the body
     */
    public Vector2D getSpeed() {
        return speed;
    }

    /**
     * @return the mass of the body
     */
    public double getMass() {
        return mass;
    }

    /**
     * @return the ID of the body
     */
    public int getId() {
        return id;
    }

    public boolean equals(final Object body) {
    	return ((Body)body).id == id;
    }

    /**
     * @param dt to be multiplied by speed and added to position
     */
    public void updatePosition(final double dt){
    	position = position.sum(speed.multiplyByScalar(dt));
    }

    /**
     * @param acceleration
     * @param dt to be multiplied by acceleration and added to speed
     */
    public void updateSpeed(final Vector2D acceleration, final double dt){
    	speed = speed.sum(acceleration.multiplyByScalar(dt));
    }

    /**
     * @param body to calculate the distance from this body
     * @return the distance
     */
    public double getDistanceFrom(final Body body) {
    	double dx = position.getX() - body.getPosition().getX();
    	double dy = position.getY() - body.getPosition().getY();
    	return Math.sqrt(dx * dx + dy * dy);
    }

    /**
     * @param body to calculate the repulsive force
     * @return the repulsive force under form of Vector2D
     * @throws InfiniteForceException
     */
    public Vector2D computeRepulsiveForceBy(final Body body) throws InfiniteForceException {
		double distance = getDistanceFrom(body);
		if (distance > 0) {
			try {
				return new Vector2D(body.getPosition(), position)
                        .normalize()
                        .multiplyByScalar(body.getMass() * REPULSIVE_CONST / (distance * distance));
			} catch (Exception ex) {
				throw new InfiniteForceException();
			}
		} else {
			throw new InfiniteForceException();
		}
    }

    /**
     * @return the current friction force
     */
    public Vector2D getCurrentFrictionForce() {
        return speed.multiplyByScalar(-FRICTION_CONST);
    }

    /**
     * @param bounds used to check if a collision has occurred
     */
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
