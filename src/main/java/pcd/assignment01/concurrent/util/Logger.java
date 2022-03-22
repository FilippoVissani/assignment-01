package pcd.assignment01.concurrent.util;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class Logger {

    public static void logSimulationResult(int nBodies, int nSteps, double speedup, int nThreads) throws FileNotFoundException {
        PrintWriter out = new PrintWriter("executionLog.log");
        out.println("--- TEST  " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")) + " ---");
        out.println("[BODIES] => " + nBodies);
        out.println("[STEPS] => " + nSteps);
        out.println("[SPEEDUP] => " + speedup);
        out.println("[THREADS] => " + nThreads);
        out.println();
        out.close();
        System.out.println("--- TEST  " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")) + " ---");
        System.out.println("[BODIES] => " + nBodies);
        System.out.println("[STEPS] => " + nSteps);
        System.out.println("[SPEEDUP] => " + speedup);
        System.out.println("[THREADS] => " + nThreads);
        System.out.println();
    }
}
