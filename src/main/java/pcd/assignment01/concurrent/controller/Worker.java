package pcd.assignment01.concurrent.controller;

import pcd.assignment01.concurrent.controller.monitors.Barrier;
import pcd.assignment01.concurrent.model.Model;
import pcd.assignment01.concurrent.util.Pair;
import pcd.assignment01.concurrent.model.Vector2D;
import java.util.List;

/**
 * Class that represents a worker thread
 */
public class Worker extends Thread {

    private final Pair<Barrier, Barrier> barriers;
    private final Model model;
    private final Pair<Integer, Integer> range;
    private final long iterations;
    private long currentIteration;

    public Worker(final Pair<Barrier, Barrier> barriers,
                  final Model model,
                  final Pair<Integer, Integer> range,
                  final long iterations) {
        this.barriers = barriers;
        this.model = model;
        this.range = range;
        this.iterations = iterations;
        this.currentIteration = 0;
    }

    @Override
    public void run() {
        try {
            while (this.currentIteration < this.iterations){
                List<Vector2D> acceleration = model.computeAccelerationOnBodiesRange(range);
                this.model.updateSpeedOnBodiesRange(range, acceleration);
                this.barriers.getStart().hitAndWaitAll();
                this.model.updatePositionOnBodiesRange(range);
                this.model.checkAndSolveBoundaryCollisionOnBodiesRange(range);
                this.barriers.getStop().hitAndWaitAll();
                this.currentIteration = this.currentIteration + 1;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
