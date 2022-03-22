package pcd.assignment01.concurrent.view;

import pcd.assignment01.concurrent.util.Boundary;
import pcd.assignment01.concurrent.util.Point2D;

import java.util.List;

public interface View {
    void display(List<Point2D> bodiesPositions, double virtualTime, long currentIteration, Boundary bounds);
}
