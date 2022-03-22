package pcd.assignment01.concurrent.controller;

import pcd.assignment01.concurrent.model.Model;
import pcd.assignment01.concurrent.view.View;

public class ControllerImpl implements Controller{
    private final Model model;
    private View view;

    public ControllerImpl(final Model model) {
        this.model = model;
    }

    @Override
    public void setView(View view) {
        this.view = view;
    }

    @Override
    public void startSimulation(final long nSteps) {
        long iteration = 0;
        /* simulation loop */
        while (iteration < nSteps) {
            model.executeIteration();
            /* display current stage */
            view.display(model.getBodiesPositions(), model.getVirtualTime(), iteration, model.getBounds());
            iteration++;
        }
    }
}
