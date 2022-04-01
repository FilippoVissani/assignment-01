package pcd.assignment01.concurrent.controller.monitors;

/**
 * Implementation of Barrier interface
 */
public class BarrierImpl implements Barrier {

    private final int workersNumber;
    private int actualWorkers;
    private boolean exit;

    public BarrierImpl(final int workersNumber) {
        this.workersNumber = workersNumber;
        this.actualWorkers = 0;
        this.exit = false;
    }

    @Override
    public synchronized void hitAndWaitAll() throws InterruptedException {
        this.actualWorkers = this.actualWorkers + 1;
        if (this.actualWorkers == this.workersNumber) {
            this.exit = true;
            notifyAll();
        } else {
            while (this.actualWorkers < this.workersNumber && !this.exit) {
                wait();
            }
        }
        this.actualWorkers = this.actualWorkers - 1;
        if (this.actualWorkers == 0) this.exit = false;
    }
}
