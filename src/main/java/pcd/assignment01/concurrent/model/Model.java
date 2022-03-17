package pcd.assignment01.concurrent.model;

import java.util.List;

public interface Model {

    /**
     * Executes an iteration of the simulation
     */
    void executeIteration();

    /**
     * @return a list containing the bodies
     */
    List<Body> getBodies();

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
