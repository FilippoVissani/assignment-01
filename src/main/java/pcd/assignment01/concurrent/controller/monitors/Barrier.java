package pcd.assignment01.concurrent.controller.monitors;

public interface Barrier {
    void hitAndWaitAll() throws InterruptedException;
}
