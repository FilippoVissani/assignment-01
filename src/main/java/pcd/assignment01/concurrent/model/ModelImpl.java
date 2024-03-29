package pcd.assignment01.concurrent.model;

import pcd.assignment01.concurrent.util.Boundary;
import pcd.assignment01.concurrent.util.Pair;
import pcd.assignment01.concurrent.util.Point2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Class that represents the model in MVC
 */
public class ModelImpl implements Model{
    private List<Body> bodies;
    private Boundary bounds;
    private double virtualTime;
    final double timeStep;

    public ModelImpl(final int bodiesNumber) {
        virtualTime = 0;
        timeStep = 0.001;
        generateBodies(bodiesNumber);
    }

    @Override
    public List<Point2D> getBodiesPositions() {
        List<Point2D> positions = new ArrayList<>();
        for (Body body : bodies){
            positions.add(body.getPosition());
        }
        return positions;
    }

    @Override
    public Boundary getBounds() {
        return this.bounds;
    }

    @Override
    public double getVirtualTime() {
        return this.virtualTime;
    }

    @Override
    public double getTimeStep() {
        return this.timeStep;
    }

    @Override
    public int getBodiesNumber(){
        return this.bodies.size();
    }

    @Override
    public void incrementVirtualTime(){
        this.virtualTime = this.virtualTime + this.timeStep;
    }

    @Override
    public void checkAndSolveBoundaryCollisionOnBodiesRange(final Pair<Integer, Integer> range){
        for(int i = range.getStart(); i < range.getStop(); i++){
            this.bodies.get(i).checkAndSolveBoundaryCollision(this.bounds);
        }
    }

    @Override
    public void updatePositionOnBodiesRange(final Pair<Integer, Integer> range){
        for(int i = range.getStart(); i < range.getStop(); i++){
            this.bodies.get(i).updatePosition(this.timeStep);
        }
    }

    @Override
    public void updateSpeedOnBodiesRange(final Pair<Integer, Integer> range, final List<Vector2D> acceleration){
        for(int i = range.getStart(); i < range.getStop(); i++){
            this.bodies.get(i).updateSpeed(acceleration.get(i - range.getStart()), this.timeStep);
        }
    }

    @Override
    public List<Vector2D> computeAccelerationOnBodiesRange(final Pair<Integer, Integer> range){
        List<Vector2D> acceleration = new ArrayList<>();
        for (Body body : this.bodies.subList(range.getStart(), range.getStop())){
            acceleration.add(computeAccelerationOnBody(body));
        }
        return acceleration;
    }

    private Vector2D computeAccelerationOnBody(final Body body) {
        Vector2D totalForce = new Vector2D(0, 0);
        for (Body otherBody : this.bodies) {
            if (!body.equals(otherBody)) {
                try {
                    Vector2D forceByOtherBody = body.computeRepulsiveForceBy(otherBody);
                    totalForce = totalForce.sum(forceByOtherBody);
                } catch (Exception ignored) {
                }
            }
        }
        totalForce = totalForce.sum(body.getCurrentFrictionForce());
        return totalForce.multiplyByScalar(1.0 / body.getMass());
    }

    private void generateBodies(final int bodies) {
        this.bounds = new Boundary(-6.0, -6.0, 6.0, 6.0);
        Random rand = new Random(System.currentTimeMillis());
        this.bodies = new ArrayList<>();
        for (int i = 0; i < bodies; i++) {
            double x = this.bounds.getX0() * 0.25 + rand.nextDouble() * (this.bounds.getX1() - this.bounds.getX0()) * 0.25;
            double y = this.bounds.getY0() * 0.25 + rand.nextDouble() * (this.bounds.getY1() - this.bounds.getY0()) * 0.25;
            Body b = new Body(i, new Point2D(x, y), new Vector2D(0, 0), 10);
            this.bodies.add(b);
        }
    }
}
