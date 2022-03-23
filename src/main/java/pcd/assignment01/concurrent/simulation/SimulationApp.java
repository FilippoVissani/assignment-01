package pcd.assignment01.concurrent.simulation;

import pcd.assignment01.concurrent.controller.ControllerImpl;
import pcd.assignment01.concurrent.model.ModelImpl;
import pcd.assignment01.concurrent.view.GraphicalView;

public class SimulationApp {
    public static void main(String[] args) {
        ModelImpl model = new ModelImpl();
        ControllerImpl controller = new ControllerImpl(model);
        GraphicalView view = new GraphicalView(controller, 620,620);
        controller.setView(view);
        controller.startSimulation(1000);
    }
}
