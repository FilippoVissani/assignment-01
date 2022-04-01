package pcd.assignment01.concurrent.controller;

import pcd.assignment01.concurrent.view.View;

/**
 * Interface used to represent the controller in MVC
 */
public interface Controller extends ViewController {

    /**
     * @param view
     * Set the view of the controller
     */
    void setView(View view);

    /**
     * start the simulation
     */
    void startSimulation();
}
