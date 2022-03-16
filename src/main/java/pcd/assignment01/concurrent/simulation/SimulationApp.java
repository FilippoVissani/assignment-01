package pcd.assignment01.concurrent.simulation;

import pcd.assignment01.concurrent.controller.ControllerImpl;
import pcd.assignment01.concurrent.model.ModelImpl;
import pcd.assignment01.concurrent.view.ViewImpl;

public class SimulationApp {
    public static void main(String[] args) {
        ModelImpl model = new ModelImpl();
        ViewImpl view = new ViewImpl(620,620);
        ControllerImpl controller = new ControllerImpl(model, view);
        controller.startSimulation(50000);
    }
}
