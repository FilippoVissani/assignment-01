package pcd.assignment01.concurrent.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class Logger {

    public static void logSimulationResult(int nBodies, long nSteps, double speedup, long executionTime, int nThreads) throws FileNotFoundException {
        log("--- TEST  " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")) + " ---");
        log("[BODIES] => " + nBodies);
        log("[STEPS] => " + nSteps);
        log("[THREADS] => " + nThreads);
        log("[SPEEDUP] => " + speedup);
        log("[EXECUTION_TIME] => " + executionTime);
        log("");
    }

    private static void log(String text) throws FileNotFoundException {
        PrintWriter out = new PrintWriter(new FileOutputStream(new File("simulation.log"), true));
        out.println(text);
        System.out.println(text);
        out.close();
    }
}
