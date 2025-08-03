package com.java11.process;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Demonstrates improved Process API introduced in Java 11
 */
public class ProcessApiExample {
    
    public static void runExamples() {
        try {
            // 1. Basic process creation and execution
            System.out.println("1. Basic process execution:");
            ProcessBuilder pb = new ProcessBuilder("echo", "Hello from Java 11 Process API!");
            Process process = pb.start();
            
            // Wait for completion
            int exitCode = process.waitFor();
            System.out.println("Process exit code: " + exitCode);
            System.out.println();
            
            // 2. Process information
            System.out.println("2. Process information:");
            ProcessHandle currentProcess = ProcessHandle.current();
            System.out.println("Current process PID: " + currentProcess.pid());
            System.out.println("Current process info: " + currentProcess.info());
            System.out.println("Current process is alive: " + currentProcess.isAlive());
            System.out.println();
            
            // 3. Process tree
            System.out.println("3. Process tree:");
            ProcessHandle parent = currentProcess.parent().orElse(null);
            if (parent != null) {
                System.out.println("Parent process PID: " + parent.pid());
                System.out.println("Parent process info: " + parent.info());
            } else {
                System.out.println("No parent process found");
            }
            
            // Get all children
            List<ProcessHandle> children = currentProcess.children().collect(java.util.stream.Collectors.toList());
            System.out.println("Number of child processes: " + children.size());
            System.out.println();
            
            // 4. Process lifecycle management
            System.out.println("4. Process lifecycle management:");
            
            // Start a long-running process (sleep)
            ProcessBuilder sleepPb = new ProcessBuilder("sleep", "5");
            Process sleepProcess = sleepPb.start();
            ProcessHandle sleepHandle = sleepProcess.toHandle();
            
            System.out.println("Started sleep process with PID: " + sleepHandle.pid());
            System.out.println("Process is alive: " + sleepHandle.isAlive());
            
            // Wait for a short time
            Thread.sleep(1000);
            System.out.println("After 1 second - Process is alive: " + sleepHandle.isAlive());
            
            // Terminate the process
            sleepHandle.destroy();
            System.out.println("Process terminated. Is alive: " + sleepHandle.isAlive());
            System.out.println();
            
            // 5. Process timeout handling
            System.out.println("5. Process timeout handling:");
            ProcessBuilder timeoutPb = new ProcessBuilder("sleep", "10");
            Process timeoutProcess = timeoutPb.start();
            
            boolean completed = timeoutProcess.waitFor(3, TimeUnit.SECONDS);
            if (completed) {
                System.out.println("Process completed within timeout");
            } else {
                System.out.println("Process did not complete within timeout, destroying...");
                timeoutProcess.destroy();
                System.out.println("Process destroyed");
            }
            System.out.println();
            
            // 6. Process output handling
            System.out.println("6. Process output handling:");
            ProcessBuilder outputPb = new ProcessBuilder("java", "-version");
            outputPb.redirectErrorStream(true);
            Process outputProcess = outputPb.start();
            
            // Read output
            String output = new String(outputProcess.getInputStream().readAllBytes());
            int outputExitCode = outputProcess.waitFor();
            
            System.out.println("Java version output:");
            System.out.println(output);
            System.out.println("Exit code: " + outputExitCode);
            System.out.println();
            
            // 7. Process comparison with old way
            System.out.println("7. Comparison with old way:");
            
            // Old way - limited process information
            Process oldProcess = Runtime.getRuntime().exec("echo 'Old way'");
            int oldExitCode = oldProcess.waitFor();
            System.out.println("Old way exit code: " + oldExitCode);
            
            // New way - rich process information
            ProcessBuilder newPb = new ProcessBuilder("echo", "New way");
            Process newProcess = newPb.start();
            ProcessHandle newHandle = newProcess.toHandle();
            int newExitCode = newProcess.waitFor();
            
            System.out.println("New way exit code: " + newExitCode);
            System.out.println("New way PID: " + newHandle.pid());
            System.out.println("New way info: " + newHandle.info());
            System.out.println();
            
            // 8. Practical example - system monitoring
            System.out.println("8. Practical example - System monitoring:");
            
            // Get all processes
            List<ProcessHandle> allProcesses = ProcessHandle.allProcesses()
                    .limit(10)
                    .collect(java.util.stream.Collectors.toList());
            
            System.out.println("First 10 processes:");
            allProcesses.forEach(handle -> {
                ProcessHandle.Info info = handle.info();
                System.out.println("PID: " + handle.pid() + 
                                 ", Command: " + info.command().orElse("N/A") +
                                 ", Arguments: " + info.arguments().orElse(new String[0]).length +
                                 ", Start time: " + info.startInstant().orElse(null));
            });
            System.out.println();
            
            // 9. Process destruction modes
            System.out.println("9. Process destruction modes:");
            
            // Start a process that can be terminated gracefully
            ProcessBuilder gracefulPb = new ProcessBuilder("sleep", "30");
            Process gracefulProcess = gracefulPb.start();
            ProcessHandle gracefulHandle = gracefulProcess.toHandle();
            
            System.out.println("Started process with PID: " + gracefulHandle.pid());
            
            // Try graceful termination first
            boolean destroyed = gracefulHandle.destroy();
            System.out.println("Graceful destruction successful: " + destroyed);
            
            // Wait a bit and check if still alive
            Thread.sleep(1000);
            if (gracefulHandle.isAlive()) {
                System.out.println("Process still alive, forcing destruction...");
                gracefulHandle.destroyForcibly();
                System.out.println("Forced destruction completed");
            }
            System.out.println();
            
            // 10. Benefits of new Process API
            System.out.println("10. Benefits of new Process API:");
            System.out.println("- Rich process information (PID, command, arguments, start time)");
            System.out.println("- Process tree navigation (parent/children)");
            System.out.println("- Better process lifecycle management");
            System.out.println("- Improved timeout handling");
            System.out.println("- More efficient process destruction");
            System.out.println("- Better integration with modern Java features");
            System.out.println("- Enhanced monitoring capabilities");
            
        } catch (IOException | InterruptedException e) {
            System.err.println("Error in process operations: " + e.getMessage());
            e.printStackTrace();
        }
    }
} 