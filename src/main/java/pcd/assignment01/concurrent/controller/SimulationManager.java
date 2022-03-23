package pcd.assignment01.concurrent.controller;

public interface SimulationManager {
    double getSpeedup();

    int getWorkersNumber();

    void startNewSimulation(long nSteps);
}
