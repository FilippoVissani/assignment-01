package pcd.assignment01.concurrent.controller;

import pcd.assignment01.concurrent.view.View;

public interface Controller extends ViewController {

    void setView(View view);

    void startSimulation();
}
