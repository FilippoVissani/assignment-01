package pcd.assignment01.concurrent.view;

import pcd.assignment01.concurrent.controller.Controller;
import pcd.assignment01.concurrent.util.Body;
import pcd.assignment01.concurrent.util.Boundary;

public class GraphicalView implements View {

    private final Controller controller;
    private final SimulationGUI frame;

    public GraphicalView(Controller controller, int w, int h){
        this.controller = controller;
        frame = new SimulationGUI(w,h);
    }

    @Override
    public void display(java.util.List<Body> bodies, double vt, long iter, Boundary bounds){
        frame.display(bodies, vt, iter, bounds);
    }
}
