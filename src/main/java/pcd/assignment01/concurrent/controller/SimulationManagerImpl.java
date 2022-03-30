package pcd.assignment01.concurrent.controller;

import pcd.assignment01.concurrent.controller.monitors.Barrier;
import pcd.assignment01.concurrent.controller.monitors.BarrierImpl;
import pcd.assignment01.concurrent.model.Model;
import pcd.assignment01.concurrent.util.Logger;
import pcd.assignment01.concurrent.util.Pair;
import java.util.*;

public class SimulationManagerImpl implements SimulationManager {

    private final Model model;
    private final Set<Worker> workers;
    private final ViewController controller;
    private final long stepNumber;
    private final Chronometer chronometer;
    private final Barrier barrier;

    public SimulationManagerImpl(final Model model, final ViewController controller, final long stepNumber) {
        this.model = model;
        this.controller = controller;
        this.stepNumber = stepNumber;
        //int workersNumber = Runtime.getRuntime().availableProcessors() + 1;
        int workersNumber = 1;
        List<Barrier> barriers = new ArrayList<>();
        this.barrier = new BarrierImpl(workersNumber + 1);
        barriers.add(this.barrier);
        barriers.add(new BarrierImpl(workersNumber));
        barriers.add(new BarrierImpl(workersNumber));
        barriers.add(new BarrierImpl(workersNumber));
        barriers.add(new BarrierImpl(workersNumber));
        this.workers = new HashSet<>();
        int range = model.getBodiesNumber() / workersNumber;
        for (int i = 0; i < model.getBodiesNumber(); i = i + range){
            this.workers.add(new Worker(barriers,
                    model,
                    new Pair<>(i, i + range),
                    this.stepNumber));
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
        Logger.logSimulationStarted();
        long iteration = 0;
        workers.forEach(Thread::start);
        this.chronometer.start();
        while (iteration < stepNumber) {
            try {
                //this.controller.updateView(iteration);
                this.barrier.hitAndWaitAll();
                this.model.incrementVirtualTime();
                iteration = iteration + 1;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.chronometer.stop();
        Logger.logSimulationResult(model.getBodiesPositions().size(),
                stepNumber,
                0,
                this.chronometer.getTime(),
                this.workers.size());
        Logger.logProgramTerminated();
    }
}
