package pcd.assignment01.concurrent.controller;

import java.util.Optional;

public class WorkerManager {
    private final int workersNumber;
    private Optional<Double> speedup = Optional.empty();

    public WorkerManager() {
        this.workersNumber = Runtime.getRuntime().availableProcessors() + 1;
    }

    public WorkerManager(int workersNumber) {
        this.workersNumber = workersNumber;
    }

    public int getWorkersNumber() {
        return this.workersNumber;
    }

    public double getSpeedup(){
        if (this.speedup.isEmpty()){
            return 0;
            //throw new IllegalStateException("Speedup is not set");
        }
        return speedup.get();
    }
}
