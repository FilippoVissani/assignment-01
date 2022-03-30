package pcd.assignment01.concurrent.controller.monitors;

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
            //System.out.println(Thread.currentThread().getName() + " NOTIFY");
            notifyAll();
        } else {
            while (this.actualWorkers < this.workersNumber && !this.exit) {
                //System.out.println(Thread.currentThread().getName() + " WAIT");
                wait();
            }
        }
        this.actualWorkers = this.actualWorkers - 1;
        if (this.actualWorkers == 0) this.exit = false;
    }
}
