package pcd.assignment01.concurrent.view;

import pcd.assignment01.concurrent.model.Body;
import pcd.assignment01.concurrent.model.Boundary;
import java.util.List;

public interface View {
    void display(List<Body> bodies, double vt, long iter, Boundary bounds);
}
