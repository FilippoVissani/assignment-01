package pcd.assignment01.concurrent.controller;

/**
 * Interface used to represent a chronometer
 */
public interface Chronometer {
    /**
     * Start the chronometer
     */
    void start();

    /**
     * Stop the chronometer
     */
    void stop();

    /**
     * @return The time passed between start() and stop() calls or between start() and now if stop() was not called
     */
    long getTime();
}
