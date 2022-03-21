package pcd.assignment01.concurrent.controller;

import pcd.assignment01.concurrent.view.View;

public interface Controller {

    void setView(View view);

    void startSimulation(long nSteps);
}
