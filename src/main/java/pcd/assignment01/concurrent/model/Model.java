package pcd.assignment01.concurrent.model;

import pcd.assignment01.concurrent.util.Boundary;
import pcd.assignment01.concurrent.util.Point2D;

import java.util.List;

public interface Model {

    /**
     * Executes an iteration of the simulation
     */
    void executeIteration();

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
}
