package com.java11;

import com.java11.httpclient.HttpClientExample;
import com.java11.strings.StringMethodsExample;
import com.java11.collections.CollectionFactoryExample;
import com.java11.files.FileMethodsExample;
import com.java11.optional.OptionalEnhancementsExample;
import com.java11.process.ProcessApiExample;

/**
 * Main class to demonstrate Java 11 features
 */
public class Main {
    
    public static void main(String[] args) {
        System.out.println("=== Java 11 Features Demonstration ===\n");
        
        try {
            // 1. String Methods
            System.out.println("1. String Methods Examples:");
            System.out.println("=".repeat(50));
            StringMethodsExample.runExamples();
            System.out.println();
            
            // 2. Collection Factory Methods
            System.out.println("2. Collection Factory Methods Examples:");
            System.out.println("=".repeat(50));
            CollectionFactoryExample.runExamples();
            System.out.println();
            
            // 3. File Methods
            System.out.println("3. File Methods Examples:");
            System.out.println("=".repeat(50));
            FileMethodsExample.runExamples();
            System.out.println();
            
            // 4. Optional Enhancements
            System.out.println("4. Optional Enhancements Examples:");
            System.out.println("=".repeat(50));
            OptionalEnhancementsExample.runExamples();
            System.out.println();
            
            // 5. Process API
            System.out.println("5. Process API Examples:");
            System.out.println("=".repeat(50));
            ProcessApiExample.runExamples();
            System.out.println();
            
            // 6. HTTP Client (requires internet connection)
            System.out.println("6. HTTP Client Examples:");
            System.out.println("=".repeat(50));
            HttpClientExample.runExamples();
            System.out.println();
            
        } catch (Exception e) {
            System.err.println("Error running examples: " + e.getMessage());
            e.printStackTrace();
        }
        
        System.out.println("=== Java 11 Features Demonstration Complete ===");
    }
} 