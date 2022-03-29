package pcd.assignment01.concurrent.controller;

import pcd.assignment01.concurrent.model.Model;
import pcd.assignment01.concurrent.model.Pair;
import pcd.assignment01.concurrent.model.Vector2D;
import java.util.List;

public class Worker extends Thread {

    private final List<Barrier> barrier;
    private final Model model;
    private final Pair<Integer, Integer> range;

    public Worker(final List<Barrier> barrier, final Model model, final Pair<Integer, Integer> range) {
        this.barrier = barrier;
        this.model = model;
        this.range = range;
    }

    @Override
    public void run() {
        try {
            while (true){
                this.barrier.get(3).hitAndWaitAll();
                List<Vector2D> acceleration = model.computeAccelerationOnBodiesRange(range);
                this.barrier.get(0).hitAndWaitAll();
                this.model.updateSpeedOnBodiesRange(range, acceleration);
                this.barrier.get(1).hitAndWaitAll();
                this.model.updatePositionOnBodiesRange(range);
                this.barrier.get(2).hitAndWaitAll();
                this.model.checkAndSolveBoundaryCollisionOnBodiesRange(range);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
