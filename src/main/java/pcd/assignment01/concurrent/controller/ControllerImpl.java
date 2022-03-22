package pcd.assignment01.concurrent.controller;

import pcd.assignment01.concurrent.model.Model;
import pcd.assignment01.concurrent.util.Logger;
import pcd.assignment01.concurrent.view.View;

import java.io.FileNotFoundException;

public class ControllerImpl implements Controller{
    private final Model model;
    private final Chronometer chronometer;
    private View view;
    private final WorkerManager workerManager;

    public ControllerImpl(final Model model) {
        this.model = model;
        this.chronometer = new Chronometer();
        this.workerManager = new WorkerManager();
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
            //view.display(model.getBodiesPositions(), model.getVirtualTime(), iteration, model.getBounds());
            iteration++;
        }
        this.chronometer.stop();
        try {
            Logger.logSimulationResult(model.getBodiesPositions().size(),
                    nSteps,
                    this.workerManager.getSpeedup(),
                    this.chronometer.getTime(),
                    this.workerManager.getWorkersNumber());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("PROGRAM TERMINATED");
    }
}
