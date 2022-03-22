package pcd.assignment01.concurrent.controller;

import pcd.assignment01.concurrent.model.Model;
import pcd.assignment01.concurrent.view.View;

public class ControllerImpl implements Controller{
    private final Model model;
    private final Chronometer chronometer;
    private View view;
    private final int nWorkers;

    public ControllerImpl(final Model model) {
        this.model = model;
        this.chronometer = new Chronometer();
        nWorkers = Runtime.getRuntime().availableProcessors() + 1;
    }

    @Override
    public void setView(View view) {
        this.view = view;
    }

    @Override
    public void startSimulation(final long nSteps) {
        long iteration = 0;
        this.chronometer.start();
        /* simulation loop */
        while (iteration < nSteps) {
            model.executeIteration();
            /* display current stage */
            view.display(model.getBodiesPositions(), model.getVirtualTime(), iteration, model.getBounds());
            iteration++;
        }
        this.chronometer.stop();
        System.out.println("PROGRAM TERMINATED");
        System.out.println("EXECUTION TIME: " + this.chronometer.getTime() + " MILLISECONDS");
    }
}
