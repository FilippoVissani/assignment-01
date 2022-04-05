package pcd.assignment01.concurrent.simulation;

import pcd.assignment01.concurrent.controller.ControllerImpl;
import pcd.assignment01.concurrent.model.ModelImpl;
import pcd.assignment01.concurrent.view.GraphicalView;
import java.util.Optional;

public class SimulationAppGUI {
    public static void main(String[] args) {
        ModelImpl model = new ModelImpl(100);
        ControllerImpl controller = new ControllerImpl(model, 1000, Optional.empty());
        GraphicalView view = new GraphicalView(controller, 820, 820);
        controller.setView(view);
    }
}
