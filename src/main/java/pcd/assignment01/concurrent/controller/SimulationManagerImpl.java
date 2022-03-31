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
    private final Pair<Barrier, Barrier> barrier;

    public SimulationManagerImpl(final Model model, final ViewController controller, final long stepNumber, Optional<Integer> workersNumber) {
        this.model = model;
        this.controller = controller;
        this.stepNumber = stepNumber;
        this.chronometer = new ChronometerImpl();
        int threadsNumber = Runtime.getRuntime().availableProcessors() + 1;
        if (workersNumber.isPresent()){
            threadsNumber = workersNumber.get();
        }
        List<Barrier> barriers = new ArrayList<>();
        this.barrier = new Pair<>(new BarrierImpl(threadsNumber + 1), new BarrierImpl(threadsNumber + 1));
        barriers.add(barrier.getStart());
        barriers.add(new BarrierImpl(threadsNumber));
        barriers.add(new BarrierImpl(threadsNumber));
        barriers.add(new BarrierImpl(threadsNumber));
        barriers.add(barrier.getStop());
        this.workers = new HashSet<>();
        int range = model.getBodiesNumber() / threadsNumber;
        int last = 0;
        for (int i = 0; i < threadsNumber - 1; i++){
            last = i * range + range;
            this.workers.add(new Worker(barriers, model, new Pair<>(i * range, last), stepNumber));
        }
        this.workers.add(new Worker(barriers, model, new Pair<>(last, this.model.getBodiesNumber()), stepNumber));
    }

    @Override
    public int getWorkersNumber(){
        return this.workers.size();
    }

    @Override
    public void run() {
        Logger.logSimulationStarted();
        long iteration = 0;
        for (Worker worker : workers){
            worker.start();
        }
        this.chronometer.start();
        while (iteration < stepNumber) {
            try {
                this.barrier.getStart().hitAndWaitAll();
                this.barrier.getStop().hitAndWaitAll();
                this.model.incrementVirtualTime();
                //this.controller.updateView(iteration);
                iteration = iteration + 1;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.chronometer.stop();
        Logger.logSimulationResult(model.getBodiesPositions().size(),
                stepNumber,
                this.chronometer.getTime(),
                this.workers.size());
        Logger.logProgramTerminated();
    }
}
