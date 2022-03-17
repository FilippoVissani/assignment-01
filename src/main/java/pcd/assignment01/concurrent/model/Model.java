package pcd.assignment01.concurrent.model;

import java.util.ArrayList;

public interface Model {
    void executeIteration();

    ArrayList<Body> getBodies();

    Boundary getBounds();

    double getVirtualTime();

    double getTimeStep();
}
