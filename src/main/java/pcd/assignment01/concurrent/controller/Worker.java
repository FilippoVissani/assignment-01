package pcd.assignment01.concurrent.controller;

import pcd.assignment01.concurrent.controller.monitors.Barrier;
import pcd.assignment01.concurrent.model.Model;
import pcd.assignment01.concurrent.util.Pair;
import pcd.assignment01.concurrent.model.Vector2D;
import java.util.List;

public class Worker extends Thread {

    private final List<Barrier> barriers;
    private final Model model;
    private final Pair<Integer, Integer> range;
    private final long stepNumber;
    private long actualStepNumber;

    public Worker(final List<Barrier> barrier,
                  final Model model,
                  final Pair<Integer, Integer> range,
                  final long stepNumber) {
        this.barriers = barrier;
        this.model = model;
        this.range = range;
        this.stepNumber = stepNumber;
        this.actualStepNumber = 0;
    }

    @Override
    public void run() {
        try {
            while (this.actualStepNumber < this.stepNumber){
                this.barriers.get(0).hitAndWaitAll();
                List<Vector2D> acceleration = model.computeAccelerationOnBodiesRange(range);
                this.barriers.get(1).hitAndWaitAll();
                this.model.updateSpeedOnBodiesRange(range, acceleration);
                this.barriers.get(2).hitAndWaitAll();
                this.model.updatePositionOnBodiesRange(range);
                this.barriers.get(3).hitAndWaitAll();
                this.model.checkAndSolveBoundaryCollisionOnBodiesRange(range);
                this.barriers.get(4).hitAndWaitAll();
                this.actualStepNumber = this.actualStepNumber + 1;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
