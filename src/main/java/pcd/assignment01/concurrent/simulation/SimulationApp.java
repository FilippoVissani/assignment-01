package pcd.assignment01.concurrent.simulation;

import pcd.assignment01.concurrent.controller.ControllerImpl;
import pcd.assignment01.concurrent.model.ModelImpl;
import pcd.assignment01.concurrent.view.GraphicalView;
import java.util.Optional;

public class SimulationApp {
    public static void main(String[] args) {
        ModelImpl model = new ModelImpl(Integer.parseInt(args[0]));
        if (args.length == 3){
            ControllerImpl controller = new ControllerImpl(model, Long.parseLong(args[1]), Optional.of(Integer.parseInt(args[2])));
            //GraphicalView view = new GraphicalView(controller, 620, 620);
            //controller.setView(view);
            controller.startSimulation();
        }else {
            ControllerImpl controller = new ControllerImpl(model, Long.parseLong(args[1]), Optional.empty());
            //GraphicalView view = new GraphicalView(controller, 620, 620);
            //controller.setView(view);
            controller.startSimulation();
        }
    }
}
