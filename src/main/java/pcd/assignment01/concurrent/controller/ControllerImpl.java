package pcd.assignment01.concurrent.controller;

import pcd.assignment01.concurrent.model.Model;
import pcd.assignment01.concurrent.view.View;
import java.util.Optional;

/**
 * Implementation of Controller
 */
public class ControllerImpl implements Controller{
    private final Model model;
    private View view;
    private final SimulationManager simulationManager;

    public ControllerImpl(final Model model, final long iterations, final Optional<Integer> workersNumber) {
        this.model = model;
        this.simulationManager = new SimulationManager(model, this, iterations, workersNumber);
    }

    @Override
    public void setView(final View view) {
        this.view = view;
    }

    @Override
    public void updateView(long currentIteration){
        view.display(model.getBodiesPositions(), model.getVirtualTime(), currentIteration, model.getBounds());
    }

    @Override
    public void startSimulation() {
        new Thread(this.simulationManager, "MASTER").start();
    }

    @Override
    public void stopSimulation() {
        this.simulationManager.stopSimulation();
    }
}
