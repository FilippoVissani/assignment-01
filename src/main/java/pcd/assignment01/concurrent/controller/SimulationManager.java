package pcd.assignment01.concurrent.controller;

public interface SimulationManager extends Runnable {
    double getSpeedup();

    int getWorkersNumber();
}
