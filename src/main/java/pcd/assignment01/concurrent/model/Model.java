package pcd.assignment01.concurrent.model;

import java.util.List;

public interface Model {

    /**
     * Executes an iteration of the simulation
     */
    void executeIteration();

    /**
     *
     * @return a list containing the bodies
     */
    List<Body> getBodies();

    Boundary getBounds();

    double getVirtualTime();

    double getTimeStep();
}
