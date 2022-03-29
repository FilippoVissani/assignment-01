package pcd.assignment01.concurrent.controller;

import pcd.assignment01.concurrent.model.Model;
import pcd.assignment01.concurrent.model.Pair;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WorkerManagerImpl implements WorkerManager {
    private final Set<Worker> workers;

    public WorkerManagerImpl(final Model model) {
        this(Runtime.getRuntime().availableProcessors() + 1, model);
    }

    public WorkerManagerImpl(final int workersNumber, final Model model) {
        List<Barrier> barrier = new ArrayList<>();
        barrier.add(new BarrierImpl(workersNumber));
        barrier.add(new BarrierImpl(workersNumber));
        barrier.add(new BarrierImpl(workersNumber));
        this.workers = new HashSet<>();
        int range = model.getBodiesNumber() / workersNumber;
        for (int i = 0; i < model.getBodiesNumber(); i = i + range){
            this.workers.add(new Worker(barrier, model, new Pair<>(i, i + range)));
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
    public void waitTaskTerminated(){
        //TODO
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
