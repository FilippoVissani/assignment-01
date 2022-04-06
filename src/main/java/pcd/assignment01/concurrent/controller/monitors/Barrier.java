package pcd.assignment01.concurrent.controller.monitors;

/**
 * Interface used to represent a barrier
 */
public interface Barrier {
    /**
     * Hit the barrier and wait until the last worker hits the same barrier, calling notifyAll()
     * @throws InterruptedException
     */
    void await() throws InterruptedException;
}
