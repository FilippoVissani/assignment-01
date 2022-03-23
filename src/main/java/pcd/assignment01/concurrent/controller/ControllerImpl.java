package pcd.assignment01.concurrent.controller;

import pcd.assignment01.concurrent.model.Model;
import pcd.assignment01.concurrent.util.Logger;
import pcd.assignment01.concurrent.view.View;

public class ControllerImpl implements Controller{
    private final Model model;
    private final Chronometer chronometer;
    private View view;
    private final SimulationManager simulationManager;

    public ControllerImpl(final Model model) {
        this.model = model;
        this.chronometer = new ChronometerImpl();
        this.simulationManager = new SimulationManagerImpl(model, this);
    }

    @Override
    public void setView(View view) {
        this.view = view;
    }

    @Override
    public void updateView(long currentIteration){
        view.display(model.getBodiesPositions(), model.getVirtualTime(), currentIteration, model.getBounds());
    }

    @Override
    public void startSimulationLoop(long nSteps) {
        Logger.logProgramStarted();
        this.chronometer.start();
        this.simulationManager.startNewSimulation(nSteps);
        this.chronometer.stop();
        Logger.logSimulationResult(model.getBodiesPositions().size(),
                nSteps,
                this.simulationManager.getSpeedup(),
                this.chronometer.getTime(),
                this.simulationManager.getWorkersNumber());
        Logger.logProgramTerminated();
    }
}
