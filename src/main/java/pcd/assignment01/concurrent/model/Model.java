package pcd.assignment01.concurrent.model;

import pcd.assignment01.concurrent.util.Boundary;
import pcd.assignment01.concurrent.util.Pair;
import pcd.assignment01.concurrent.util.Point2D;

import java.util.List;

public interface Model {

    /**
     * @return a list containing the bodies
     */
    List<Point2D> getBodiesPositions();

    /**
     * @return the boundary of the field where bodies move
     */
    Boundary getBounds();

    /**
     * @return the current virtual time (time of the simulation)
     */
    double getVirtualTime();

    /**
     * @return the step used to increment the virtual time
     */
    double getTimeStep();

    /**
     * @return the number of the bodies in the simulation
     */
    int getBodiesNumber();

    /**
     * Increment virtual time by time step
     */
    void incrementVirtualTime();

    /**
     * @param range of bodies to check and solve boundary collision
     */
    void checkAndSolveBoundaryCollisionOnBodiesRange(Pair<Integer, Integer> range);

    /**
     * @param range of bodies to update position
     */
    void updatePositionOnBodiesRange(Pair<Integer, Integer> range);

    /**
     * @param range of bodies to update speed
     * @param acceleration of the bodies
     */
    void updateSpeedOnBodiesRange(Pair<Integer, Integer> range, List<Vector2D> acceleration);

    /**
     * @param range of bodies to compute the acceleration
     * @return the acceleration of the bodies
     */
    List<Vector2D> computeAccelerationOnBodiesRange(Pair<Integer, Integer> range);
}
