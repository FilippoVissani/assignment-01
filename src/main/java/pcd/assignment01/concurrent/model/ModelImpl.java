package pcd.assignment01.concurrent.model;

import pcd.assignment01.concurrent.util.Boundary;
import pcd.assignment01.concurrent.util.Point2D;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class ModelImpl implements Model{
    /* bodies in the field */
    private List<Body> bodies;
    /* boundary of the field */
    private Boundary bounds;
    /* virtual time */
    private double virtualTime;
    /* virtual time step */
    final double timeStep;

    public ModelImpl() {
        /* init virtual time */
        virtualTime = 0;
        timeStep = 0.001;
        /* initializing boundary and bodies */
        testBodySetManyBodies();
    }

    @Override
    public List<Point2D> getBodiesPositions() {
        return bodies.stream().map(Body::getPosition).collect(Collectors.toList());
    }

    @Override
    public Boundary getBounds() {
        return bounds;
    }

    @Override
    public double getVirtualTime() {
        return virtualTime;
    }

    @Override
    public double getTimeStep() {
        return timeStep;
    }

    @Override
    public void executeIteration() {
        /* update bodies velocity */
        for (Body body : bodies) {
            /* compute total force on bodies */
            Vector2D totalForce = computeTotalForceOnBody(body);
            /* compute instant acceleration */
            Vector2D acceleration = totalForce.multiplyByScalar(1.0 / body.getMass());
            /* update velocity */
            body.updateSpeed(acceleration, timeStep);
        }
        /* compute bodies new pos */
        bodies.forEach(body -> body.updatePosition(timeStep));
        /* check collisions with boundaries */
        bodies.forEach(body -> body.checkAndSolveBoundaryCollision(bounds));
        /* update virtual time */
        virtualTime = virtualTime + timeStep;
    }

    private Vector2D computeTotalForceOnBody(final Body body) {
        Vector2D totalForce = new Vector2D(0, 0);
        /* compute total repulsive force */
        for (Body otherBody : bodies) {
            if (!body.equals(otherBody)) {
                try {
                    Vector2D forceByOtherBody = body.computeRepulsiveForceBy(otherBody);
                    totalForce = totalForce.sum(forceByOtherBody);
                } catch (Exception ignored) {
                }
            }
        }
        /* add friction force */
        totalForce = totalForce.sum(body.getCurrentFrictionForce());
        return totalForce;
    }

    private void testBodySetTwoBodies() {
        bounds = new Boundary(-4.0, -4.0, 4.0, 4.0);
        bodies = new ArrayList<>();
        bodies.add(new Body(0, new Point2D(-0.1, 0), new Vector2D(0,0), 1));
        bodies.add(new Body(1, new Point2D(0.1, 0), new Vector2D(0,0), 2));
    }

    private void testBodySetThreeBodies() {
        bounds = new Boundary(-1.0, -1.0, 1.0, 1.0);
        bodies = new ArrayList<>();
        bodies.add(new Body(0, new Point2D(0, 0), new Vector2D(0,0), 10));
        bodies.add(new Body(1, new Point2D(0.2, 0), new Vector2D(0,0), 1));
        bodies.add(new Body(2, new Point2D(-0.2, 0), new Vector2D(0,0), 1));
    }

    private void testBodySetSomeBodies() {
        bounds = new Boundary(-4.0, -4.0, 4.0, 4.0);
        int nBodies = 100;
        generateBodies(nBodies);
    }

    private void testBodySetManyBodies() {
        bounds = new Boundary(-6.0, -6.0, 6.0, 6.0);
        int nBodies = 1000;
        generateBodies(nBodies);
    }

    private void generateBodies(int nBodies) {
        Random rand = new Random(System.currentTimeMillis());
        bodies = new ArrayList<>();
        for (int i = 0; i < nBodies; i++) {
            double x = bounds.getX0() * 0.25 + rand.nextDouble() * (bounds.getX1() - bounds.getX0()) * 0.25;
            double y = bounds.getY0() * 0.25 + rand.nextDouble() * (bounds.getY1() - bounds.getY0()) * 0.25;
            Body b = new Body(i, new Point2D(x, y), new Vector2D(0, 0), 10);
            bodies.add(b);
        }
    }
}
