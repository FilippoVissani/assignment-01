package pcd.assignment01.concurrent.controller;

import pcd.assignment01.concurrent.model.Model;

import java.util.Optional;

public class SimulationManagerImpl implements SimulationManager {

    private final Model model;
    private final WorkerManager workerManager;
    private final ViewController controller;
    private Optional<Double> speedup;

    public SimulationManagerImpl(final Model model, final ViewController controller) {
        this.model = model;
        this.controller = controller;
        this.workerManager = new WorkerManagerImpl();
        this.speedup = Optional.empty();
    }

    @Override
    public double getSpeedup(){
        if (this.speedup.isEmpty()){
            throw new IllegalStateException("First simulation not executed");
        }
        return this.speedup.get();
    }

    @Override
    public int getWorkersNumber(){
        return this.workerManager.getWorkersNumber();
    }

    @Override
    public void startNewSimulation(final long nSteps) {
        long iteration = 0;
        /* simulation loop */
        while (iteration < nSteps) {
            this.workerManager.startWorkers();
            //TODO this.model.executeIteration(); deve essere eseguita nei worker
            this.model.executeIteration();
            this.workerManager.joinWorkers();
            /* display current stage */
            this.controller.updateView(iteration);
            iteration = iteration + 1;
        }
    }
}
