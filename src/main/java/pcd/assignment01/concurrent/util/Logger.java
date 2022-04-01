package pcd.assignment01.concurrent.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Class used to log the simulation result on console and on file
 */
public abstract class Logger {

    /**
     * @param nBodies number of bodies
     * @param nSteps number of iterations
     * @param executionTime execution time in milliseconds
     * @param nThreads number of threads
     * Log the result of the simulation
     */
    public static void logSimulationResult(int nBodies, long nSteps, long executionTime, int nThreads) {
        log("--- TEST " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")) + " ---");
        log("[BODIES] => " + nBodies);
        log("[STEPS] => " + nSteps);
        log("[THREADS] => " + nThreads);
        log("[EXECUTION_TIME_MS] => " + executionTime);
        int milliseconds = (int) (executionTime) % 1000 ;
        int seconds = (int) (executionTime / 1000) % 60 ;
        int minutes = (int) ((executionTime / (1000*60)) % 60);
        int hours   = (int) ((executionTime / (1000*60*60)) % 24);
        log("[EXECUTION_TIME] => " + hours + "h " + minutes + "m " + seconds + "s " + milliseconds + "ms");

    }

    /**
     * Log the start of the simulation
     */
    public static void logSimulationStarted() {
        log("SIMULATION STARTED " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")));
    }

    /**
     * Log the end of the simulation
     */
    public static void logProgramTerminated() {
        log("SIMULATION TERMINATED " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")));
        log("");
    }

    private static void log(String text) {
        try {
            PrintWriter out = new PrintWriter(new FileOutputStream(new File("simulation.log"), true));
            out.println(text);
            System.out.println(text);
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
