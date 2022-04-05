package pcd.assignment01.concurrent.controller;

import pcd.assignment01.concurrent.controller.monitors.Barrier;
import pcd.assignment01.concurrent.model.Model;
import pcd.assignment01.concurrent.model.Vector2D;
import pcd.assignment01.concurrent.util.Pair;
import java.util.List;

/**
 * Class that represents a worker thread
 */
public class Worker extends Thread {

    private final Pair<Barrier, Barrier> barriers;
    private final Model model;
    private final Pair<Integer, Integer> range;
    private Boolean stop;

    public Worker(final Pair<Barrier, Barrier> barriers,
                  final Model model,
                  final Pair<Integer, Integer> range) {
        this.barriers = barriers;
        this.model = model;
        this.range = range;
        this.stop = false;
    }

    @Override
    public void run() {
        try {
            while (!stop){
                List<Vector2D> acceleration = model.computeAccelerationOnBodiesRange(range);
                this.model.updateSpeedOnBodiesRange(range, acceleration);
                this.barriers.getStart().hitAndWaitAll();
                this.model.updatePositionOnBodiesRange(range);
                this.model.checkAndSolveBoundaryCollisionOnBodiesRange(range);
                this.barriers.getStop().hitAndWaitAll();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Stop this worker
     */
    public synchronized void stopWorker() {
        this.stop = true;
    }
}
