package pcd.assignment01.concurrent.controller;

import pcd.assignment01.concurrent.controller.monitors.Barrier;
import pcd.assignment01.concurrent.model.Model;
import pcd.assignment01.concurrent.util.Pair;
import pcd.assignment01.concurrent.model.Vector2D;
import java.util.List;

public class Worker extends Thread {

    private final Pair<Barrier, Barrier> barriers;
    private final Model model;
    private final Pair<Integer, Integer> range;
    private final long stepNumber;
    private long actualStepNumber;

    public Worker(final Pair<Barrier, Barrier> barriers,
                  final Model model,
                  final Pair<Integer, Integer> range,
                  final long stepNumber) {
        this.barriers = barriers;
        this.model = model;
        this.range = range;
        this.stepNumber = stepNumber;
        this.actualStepNumber = 0;
    }

    @Override
    public void run() {
        try {
            while (this.actualStepNumber < this.stepNumber){
                List<Vector2D> acceleration = model.computeAccelerationOnBodiesRange(range);
                this.model.updateSpeedOnBodiesRange(range, acceleration);
                this.barriers.getStart().hitAndWaitAll();
                this.model.updatePositionOnBodiesRange(range);
                this.model.checkAndSolveBoundaryCollisionOnBodiesRange(range);
                this.barriers.getStop().hitAndWaitAll();
                this.actualStepNumber = this.actualStepNumber + 1;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
