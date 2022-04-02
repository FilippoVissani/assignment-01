package pcd.assignment01.concurrent.controller;

import pcd.assignment01.concurrent.controller.monitors.Barrier;
import pcd.assignment01.concurrent.controller.monitors.BarrierImpl;
import pcd.assignment01.concurrent.model.Model;
import pcd.assignment01.concurrent.util.Logger;
import pcd.assignment01.concurrent.util.Pair;
import java.util.*;

/**
 * Class used to manage the simulation
 */
public class SimulationManager implements Runnable {

    private final Model model;
    private final Set<Worker> workers;
    private final ViewController controller;
    private final long iterations;
    private final Chronometer chronometer;
    private final Pair<Barrier, Barrier> barriers;

    public SimulationManager(final Model model, final ViewController controller, final long iterations, final Optional<Integer> workersNumber) {
        this.model = model;
        this.controller = controller;
        this.iterations = iterations;
        this.chronometer = new ChronometerImpl();
        int threadsNumber = Runtime.getRuntime().availableProcessors() + 1;
        if (workersNumber.isPresent()){
            threadsNumber = workersNumber.get();
        }
        this.barriers = new Pair<>(new BarrierImpl(threadsNumber + 1), new BarrierImpl(threadsNumber + 1));
        this.workers = new HashSet<>();
        int range = model.getBodiesNumber() / threadsNumber;
        int last = 0;
        for (int i = 0; i < threadsNumber - 1; i++){
            last = i * range + range;
            this.workers.add(new Worker(barriers, model, new Pair<>(i * range, last), iterations));
        }
        this.workers.add(new Worker(barriers, model, new Pair<>(last, this.model.getBodiesNumber()), iterations));
    }

    @Override
    public void run() {
        Logger.logSimulationStarted();
        long iteration = 0;
        for (Worker worker : workers){
            worker.start();
        }
        this.chronometer.start();
        while (iteration < iterations) {
            try {
                this.barriers.getStart().hitAndWaitAll();
                this.barriers.getStop().hitAndWaitAll();
                this.model.incrementVirtualTime();
                this.controller.updateView(iteration);
                iteration = iteration + 1;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.chronometer.stop();
        Logger.logSimulationResult(model.getBodiesPositions().size(),
                iterations,
                this.chronometer.getTime(),
                this.workers.size());
        Logger.logProgramTerminated();
    }
}
