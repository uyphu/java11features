package com.java11.files;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * Demonstrates new file methods introduced in Java 11
 */
public class FileMethodsExample {
    
    public static void runExamples() {
        try {
            // 1. Path.of() - convenient way to create Path objects
            System.out.println("1. Path.of() - Creating Path objects:");
            Path currentDir = Path.of(".");
            Path userHome = Path.of(System.getProperty("user.home"));
            Path tempFile = Path.of(System.getProperty("java.io.tmpdir"), "test.txt");
            
            System.out.println("Current directory: " + currentDir.toAbsolutePath());
            System.out.println("User home: " + userHome);
            System.out.println("Temp file path: " + tempFile);
            System.out.println();
            
            // 2. Files.readString() - read entire file as string
            System.out.println("2. Files.readString() - Reading files:");
            
            // Create a test file
            String testContent = "Hello Java 11!\nThis is a test file.\nIt contains multiple lines.\n";
            Path testFile = Path.of("test_file.txt");
            Files.writeString(testFile, testContent, StandardCharsets.UTF_8);
            System.out.println("Created test file: " + testFile.toAbsolutePath());
            
            // Read the file using new method
            String content = Files.readString(testFile, StandardCharsets.UTF_8);
            System.out.println("File content:");
            System.out.println(content);
            System.out.println();
            
            // 3. Files.writeString() - write string to file
            System.out.println("3. Files.writeString() - Writing files:");
            String newContent = "Updated content!\nWritten with Java 11 Files.writeString()\n";
            Files.writeString(testFile, newContent, StandardCharsets.UTF_8);
            
            // Read back to verify
            String updatedContent = Files.readString(testFile, StandardCharsets.UTF_8);
            System.out.println("Updated file content:");
            System.out.println(updatedContent);
            System.out.println();
            
            // 4. Comparison with old way
            System.out.println("4. Comparison with old way:");
            
            // Old way
            Path oldWayFile = Path.of("old_way.txt");
            String oldWayContent = "Content written the old way";
            
            // Old way - writing
            Files.write(oldWayFile, oldWayContent.getBytes(StandardCharsets.UTF_8));
            
            // Old way - reading
            byte[] bytes = Files.readAllBytes(oldWayFile);
            String oldWayRead = new String(bytes, StandardCharsets.UTF_8);
            
            System.out.println("Old way - write: Files.write() with byte array");
            System.out.println("Old way - read: Files.readAllBytes() + String constructor");
            System.out.println("New way - write: Files.writeString()");
            System.out.println("New way - read: Files.readString()");
            System.out.println("Result: " + oldWayRead);
            System.out.println();
            
            // 5. Practical example - configuration file handling
            System.out.println("5. Practical example - Configuration file:");
            Path configFile = Path.of("app_config.properties");
            String configContent = "# Application Configuration\n" +
                "app.name=Java 11 Demo\n" +
                "app.version=1.0.0\n" +
                "database.url=jdbc:postgresql://localhost:5432/mydb\n" +
                "database.username=admin\n" +
                "logging.level=INFO\n" +
                "server.port=8080\n";
            
            // Write configuration
            Files.writeString(configFile, configContent, StandardCharsets.UTF_8);
            System.out.println("Created configuration file: " + configFile.toAbsolutePath());
            
            // Read and parse configuration
            String config = Files.readString(configFile, StandardCharsets.UTF_8);
            System.out.println("Configuration content:");
            System.out.println(config);
            
            // Process configuration lines
            System.out.println("Parsed configuration:");
            config.lines()
                    .filter(line -> !line.trim().startsWith("#") && !line.trim().isEmpty())
                    .forEach(line -> {
                        String[] parts = line.split("=", 2);
                        if (parts.length == 2) {
                            System.out.println("  " + parts[0].trim() + " = " + parts[1].trim());
                        }
                    });
            System.out.println();
            
            // 6. Error handling and encoding
            System.out.println("6. Error handling and encoding:");
            Path nonExistentFile = Path.of("non_existent_file.txt");
            
            try {
                String content2 = Files.readString(nonExistentFile);
                System.out.println("This should not print");
            } catch (IOException e) {
                System.out.println("Expected error: " + e.getMessage());
            }
            
            // Check if file exists before reading
            if (Files.exists(testFile)) {
                String safeContent = Files.readString(testFile, StandardCharsets.UTF_8);
                System.out.println("Safely read file: " + safeContent.length() + " characters");
            }
            System.out.println();
            
            // 7. Performance comparison
            System.out.println("7. Performance comparison:");
            
            // Create a larger file for testing
            StringBuilder largeContent = new StringBuilder();
            for (int i = 0; i < 1000; i++) {
                largeContent.append("Line ").append(i).append(": This is test content for performance comparison.\n");
            }
            Path largeFile = Path.of("large_test_file.txt");
            Files.writeString(largeFile, largeContent.toString(), StandardCharsets.UTF_8);
            
            // Time the new method
            long start = System.nanoTime();
            String newMethodResult = Files.readString(largeFile, StandardCharsets.UTF_8);
            long newMethodTime = System.nanoTime() - start;
            
            // Time the old method
            start = System.nanoTime();
            byte[] oldMethodBytes = Files.readAllBytes(largeFile);
            String oldMethodResult = new String(oldMethodBytes, StandardCharsets.UTF_8);
            long oldMethodTime = System.nanoTime() - start;
            
            System.out.println("File size: " + newMethodResult.length() + " characters");
            System.out.println("New method (readString): " + newMethodTime + " ns");
            System.out.println("Old method (readAllBytes): " + oldMethodTime + " ns");
            System.out.println("Performance difference: " + String.format("%.2f", (double) oldMethodTime / newMethodTime) + "x");
            System.out.println();
            
            // Cleanup
            System.out.println("8. Cleanup - Removing test files:");
            Files.deleteIfExists(testFile);
            Files.deleteIfExists(oldWayFile);
            Files.deleteIfExists(configFile);
            Files.deleteIfExists(largeFile);
            System.out.println("Test files cleaned up successfully");
            
        } catch (IOException e) {
            System.err.println("Error in file operations: " + e.getMessage());
            e.printStackTrace();
        }
    }
} 