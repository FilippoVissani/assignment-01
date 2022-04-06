package pcd.assignment01.concurrent.controller;

import pcd.assignment01.concurrent.controller.monitors.Barrier;
import pcd.assignment01.concurrent.model.Model;
import pcd.assignment01.concurrent.model.Vector2D;
import pcd.assignment01.concurrent.util.Pair;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Class that represents a worker thread
 */
public class Worker extends Thread {

    private final List<Barrier> barriers;
    private final Model model;
    private final Pair<Integer, Integer> range;
    private final AtomicBoolean stop;

    public Worker(final List<Barrier> barriers,
                  final Model model,
                  final Pair<Integer, Integer> range,
                  final AtomicBoolean stop) {
        this.barriers = barriers;
        this.model = model;
        this.range = range;
        this.stop = stop;
    }

    @Override
    public void run() {
        try {
            while (!stop.get()){
                List<Vector2D> acceleration = model.computeAccelerationOnBodiesRange(range);
                this.model.updateSpeedOnBodiesRange(range, acceleration);
                this.barriers.get(0).await();
                this.model.updatePositionOnBodiesRange(range);
                this.model.checkAndSolveBoundaryCollisionOnBodiesRange(range);
                this.barriers.get(1).await();
                this.barriers.get(2).await();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
