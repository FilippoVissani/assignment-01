package pcd.assignment01.concurrent.controller.monitors;

/**
 * Implementation of Barrier interface
 */
public class BarrierImpl implements Barrier {

    private final int workersNumber;
    private int actualWorkers;
    private boolean exit;
    private boolean isBarrierLower;

    public BarrierImpl(final int workersNumber, final boolean isBarrierLower) {
        this.workersNumber = workersNumber;
        this.isBarrierLower = isBarrierLower;
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
            while (this.actualWorkers < this.workersNumber
                    && !this.exit
                    && !this.isBarrierLower) {
                wait();
            }
        }
        this.actualWorkers = this.actualWorkers - 1;
        if (this.actualWorkers == 0) this.exit = false;
    }

    @Override
    public synchronized void lowerBarrier(){
        this.isBarrierLower = true;
        notifyAll();
    }
}
