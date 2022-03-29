package pcd.assignment01.concurrent.controller;

import pcd.assignment01.concurrent.model.Model;
import pcd.assignment01.concurrent.util.Logger;
import pcd.assignment01.concurrent.view.View;

public class ControllerImpl implements Controller{
    private final Model model;
    private View view;
    private final SimulationManager simulationManager;

    public ControllerImpl(final Model model) {
        this.model = model;
        this.simulationManager = new SimulationManagerImpl(model, this, 1000);
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
    public void startSimulation(long nSteps) {
        Logger.logProgramStarted();
        Thread simulation = new Thread(this.simulationManager, "MASTERRRRRRRRRRRRRRRR");
        simulation.start();
/*        Logger.logSimulationResult(model.getBodiesPositions().size(),
                nSteps,
                this.simulationManager.getSpeedup(),
                this.chronometer.getTime(),
                this.simulationManager.getWorkersNumber());
        Logger.logProgramTerminated();*/
    }
}
