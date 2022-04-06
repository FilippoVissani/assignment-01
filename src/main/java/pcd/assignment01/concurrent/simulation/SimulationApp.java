package pcd.assignment01.concurrent.simulation;

import pcd.assignment01.concurrent.controller.ControllerImpl;
import pcd.assignment01.concurrent.model.ModelImpl;
import java.util.Optional;

public class SimulationApp {
    public static void main(String[] args) {
        ModelImpl model = new ModelImpl(100);
        ControllerImpl controller = new ControllerImpl(model, 1000, Optional.of(1));
        controller.startSimulation();
    }
}
