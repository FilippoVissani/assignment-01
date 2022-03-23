package pcd.assignment01.concurrent.controller;

import java.util.HashSet;
import java.util.Set;

public class WorkerManagerImpl implements WorkerManager {
    private final Set<Worker> workers;

    public WorkerManagerImpl() {
        this(Runtime.getRuntime().availableProcessors() + 1);
    }

    public WorkerManagerImpl(int workersNumber) {
        this.workers = new HashSet<>();
        for (int i = 0; i < workersNumber; i = i + 1){
            this.workers.add(new Worker());
        }
    }

    @Override
    public int getWorkersNumber() {
        return this.workers.size();
    }

    @Override
    public void startWorkers() {
        workers.forEach(Thread::start);
    }

    @Override
    public void joinWorkers() {
        workers.forEach(worker -> {
            try {
                worker.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }
}
