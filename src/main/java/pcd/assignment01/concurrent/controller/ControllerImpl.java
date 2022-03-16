package pcd.assignment01.concurrent.controller;

import pcd.assignment01.concurrent.model.Model;
import pcd.assignment01.concurrent.view.View;

public class ControllerImpl implements Controller{
    private final Model model;
    private final View view;

    public ControllerImpl(Model model, View view) {
        this.model = model;
        this.view = view;
    }

    @Override
    public void startSimulation(long nSteps) {
        long iter = 0;
        /* simulation loop */
        while (iter < nSteps) {
            model.executeIteration();
            /* display current stage */
            view.display(model.getBodies(), model.getVt(), iter, model.getBounds());
            iter++;
        }
    }
}
