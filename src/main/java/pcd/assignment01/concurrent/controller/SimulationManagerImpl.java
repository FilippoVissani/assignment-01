package pcd.assignment01.concurrent.controller;

import pcd.assignment01.concurrent.model.Model;
import pcd.assignment01.concurrent.model.Pair;
import java.util.*;

public class SimulationManagerImpl implements SimulationManager {

    private final Model model;
    private final Set<Worker> workers;
    private final ViewController controller;
    private final long nSteps;
    private Optional<Double> speedup;
    private final Barrier stepBarrier;
    private final Chronometer chronometer;

    public SimulationManagerImpl(final Model model, final ViewController controller, final long nSteps) {
        this.model = model;
        this.controller = controller;
        this.nSteps = nSteps;
        this.speedup = Optional.empty();
        List<Barrier> barrier = new ArrayList<>();
        int workersNumber = Runtime.getRuntime().availableProcessors() + 1;
        barrier.add(new BarrierImpl(workersNumber));
        barrier.add(new BarrierImpl(workersNumber));
        barrier.add(new BarrierImpl(workersNumber));
        stepBarrier = new BarrierImpl(workersNumber + 1);
        barrier.add(stepBarrier);
        this.workers = new HashSet<>();
        int range = model.getBodiesNumber() / workersNumber;
        for (int i = 0; i < model.getBodiesNumber(); i = i + range){
            this.workers.add(new Worker(barrier, model, new Pair<>(i, i + range)));
        }
        chronometer = new ChronometerImpl();
    }

    @Override
    public double getSpeedup(){
/*        if (this.speedup.isEmpty()){
            throw new IllegalStateException("First simulation not executed");
        }
        return this.speedup.get();*/
        return 0;
    }

    @Override
    public int getWorkersNumber(){
        return this.workers.size();
    }

    @Override
    public void run() {
        long iteration = 0;
        workers.forEach(Thread::start);
        this.chronometer.start();
        while (iteration < nSteps) {
            try {
                // TODO synchronization with threads
                // TODO threads execute task
                // synchronization with threads
                this.stepBarrier.hitAndWaitAll();
                this.model.incrementVirtualTime();
                this.controller.updateView(iteration);
                iteration = iteration + 1;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.chronometer.stop();
        System.out.println(this.chronometer.getTime());
    }
}
