package pcd.assignment01.concurrent.view;

import pcd.assignment01.concurrent.controller.Controller;
import pcd.assignment01.concurrent.util.Boundary;
import pcd.assignment01.concurrent.util.Point2D;

import java.util.List;

public class GraphicalView implements View {

    private final Controller controller;
    private final SimulationGUI frame;

    public GraphicalView(Controller controller, int w, int h){
        this.controller = controller;
        frame = new SimulationGUI(w,h);
    }

    @Override
    public void display(List<Point2D> bodiesPositions, double virtualTime, long currentIteration, Boundary bounds) {
        frame.display(bodiesPositions, virtualTime, currentIteration, bounds);
    }
}
