package pcd.assignment01.concurrent.controller;

public interface WorkerManager {
    int getWorkersNumber();

    void startWorkers();

    void joinWorkers();
}
