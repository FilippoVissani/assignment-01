package pcd.assignment01.concurrent.controller;

public interface Chronometer {
    void start();

    void stop();

    long getTime();
}
