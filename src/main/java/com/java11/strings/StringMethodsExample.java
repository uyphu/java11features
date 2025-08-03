package com.java11.strings;

/**
 * Demonstrates new String methods introduced in Java 11
 */
public class StringMethodsExample {
    
    public static void runExamples() {
        // 1. isBlank() - checks if string is empty or contains only whitespace
        System.out.println("1. isBlank() method:");
        String empty = "";
        String whitespace = "   \t\n  ";
        String normal = "Hello World";
        
        System.out.println("Empty string isBlank(): " + empty.isBlank());
        System.out.println("Whitespace string isBlank(): " + whitespace.isBlank());
        System.out.println("Normal string isBlank(): " + normal.isBlank());
        System.out.println();
        
        // 2. strip(), stripLeading(), stripTrailing() - removes whitespace
        System.out.println("2. strip() methods:");
        String text = "  Hello World  ";
        System.out.println("Original: '" + text + "'");
        System.out.println("strip(): '" + text.strip() + "'");
        System.out.println("stripLeading(): '" + text.stripLeading() + "'");
        System.out.println("stripTrailing(): '" + text.stripTrailing() + "'");
        System.out.println();
        
        // 3. repeat() - repeats string n times
        System.out.println("3. repeat() method:");
        String star = "*";
        String line = "-";
        System.out.println("Star repeated 5 times: " + star.repeat(5));
        System.out.println("Line repeated 10 times: " + line.repeat(10));
        System.out.println("Hello repeated 3 times: " + "Hello ".repeat(3));
        System.out.println();
        
        // 4. lines() - returns stream of lines
        System.out.println("4. lines() method:");
        String multiLine = "Line 1\nLine 2\nLine 3\nLine 4";
        System.out.println("Multi-line text:");
        System.out.println(multiLine);
        System.out.println("Lines count: " + multiLine.lines().count());
        System.out.println("Lines content:");
        multiLine.lines().forEach(lineContent -> System.out.println("  - " + lineContent));
        System.out.println();
        
        // 5. Practical example combining multiple methods
        System.out.println("5. Practical example - processing user input:");
        String userInput = "  \n  John Doe  \n  ";
        System.out.println("Raw input: '" + userInput + "'");
        
        if (!userInput.isBlank()) {
            String cleaned = userInput.strip();
            System.out.println("Cleaned input: '" + cleaned + "'");
            
            // Split into lines and process each
            userInput.lines()
                    .map(String::strip)
                    .filter(lineContent -> !lineContent.isBlank())
                    .forEach(lineContent -> System.out.println("Processed line: '" + lineContent + "'"));
        } else {
            System.out.println("Input is blank!");
        }
        System.out.println();
        
        // 6. Performance comparison with old methods
        System.out.println("6. Performance comparison:");
        String testString = "  test  ";
        
        // Old way
        long start = System.nanoTime();
        boolean oldIsEmpty = testString.trim().isEmpty();
        long oldTime = System.nanoTime() - start;
        
        // New way
        start = System.nanoTime();
        boolean newIsBlank = testString.isBlank();
        long newTime = System.nanoTime() - start;
        
        System.out.println("Old way (trim().isEmpty()): " + oldIsEmpty + " (" + oldTime + " ns)");
        System.out.println("New way (isBlank()): " + newIsBlank + " (" + newTime + " ns)");
        System.out.println("Performance improvement: " + String.format("%.2f", (double) oldTime / newTime) + "x faster");
    }
} 