package pcd.assignment01.concurrent.controller;

public interface Barrier {
    void hitAndWaitAll() throws InterruptedException;
}
