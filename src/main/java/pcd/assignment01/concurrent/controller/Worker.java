package pcd.assignment01.concurrent.controller;

import pcd.assignment01.concurrent.model.Model;
import pcd.assignment01.concurrent.model.Pair;
import pcd.assignment01.concurrent.model.Vector2D;
import java.util.List;

public class Worker extends Thread {

    private final List<Barrier> barrier;
    private final Model model;
    private final Pair<Integer, Integer> range;
    private boolean stop;

    public Worker(final List<Barrier> barrier, final Model model, final Pair<Integer, Integer> range) {
        this.barrier = barrier;
        this.model = model;
        this.range = range;
        this.stop = false;
    }

    public synchronized void setStop(boolean stop){
        this.stop = stop;
    }

    @Override
    public void run() {
        try {
            while (!stop){
                List<Vector2D> acceleration = model.computeAccelerationOnBodiesRange(range);
                barrier.get(0).hitAndWaitAll();
                model.updateSpeedOnBodiesRange(range, acceleration);
                barrier.get(1).hitAndWaitAll();
                model.updatePositionOnBodiesRange(range);
                barrier.get(2).hitAndWaitAll();
                model.checkAndSolveBoundaryCollisionOnBodiesRange(range);
                wait();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
