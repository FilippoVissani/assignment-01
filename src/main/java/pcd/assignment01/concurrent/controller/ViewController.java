package pcd.assignment01.concurrent.controller;

/**
 * Interface used to expose only controller methods for managing the view
 */
public interface ViewController {
    /**
     * @param currentIteration
     * Update the view of the simulation
     */
    void updateView(long currentIteration);
}
