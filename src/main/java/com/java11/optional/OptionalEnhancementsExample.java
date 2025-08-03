package com.java11.optional;

import java.util.Optional;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Demonstrates Optional enhancements introduced in Java 11
 */
public class OptionalEnhancementsExample {
    
    public static void runExamples() {
        // 1. isEmpty() - check if Optional is empty
        System.out.println("1. isEmpty() method:");
        Optional<String> presentOptional = Optional.of("Hello World");
        Optional<String> emptyOptional = Optional.empty();
        
        System.out.println("Present optional isEmpty(): " + presentOptional.isEmpty());
        System.out.println("Empty optional isEmpty(): " + emptyOptional.isEmpty());
        System.out.println("Present optional isPresent(): " + presentOptional.isPresent());
        System.out.println("Empty optional isPresent(): " + emptyOptional.isPresent());
        System.out.println();
        
        // 2. Comparison with old way
        System.out.println("2. Comparison with old way:");
        
        // Old way
        if (!presentOptional.isPresent()) {
            System.out.println("Old way - Optional is empty");
        } else {
            System.out.println("Old way - Optional has value: " + presentOptional.get());
        }
        
        // New way
        if (presentOptional.isEmpty()) {
            System.out.println("New way - Optional is empty");
        } else {
            System.out.println("New way - Optional has value: " + presentOptional.get());
        }
        System.out.println();
        
        // 3. Practical example - user validation
        System.out.println("3. Practical example - User validation:");
        
        User user1 = new User("john.doe@example.com", "John Doe");
        User user2 = new User("", "Jane Smith");
        User user3 = new User(null, "Bob Johnson");
        
        validateUser(user1);
        validateUser(user2);
        validateUser(user3);
        System.out.println();
        
        // 4. Stream processing with Optional
        System.out.println("4. Stream processing with Optional:");
        List<Optional<String>> optionalList = List.of(
            Optional.of("Apple"),
            Optional.empty(),
            Optional.of("Banana"),
            Optional.empty(),
            Optional.of("Cherry")
        );
        
        System.out.println("Original list: " + optionalList);
        
        // Filter out empty optionals
        List<String> presentValues = optionalList.stream()
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
        
        System.out.println("Present values (old way): " + presentValues);
        
        // Using isEmpty() for filtering
        List<String> presentValues2 = optionalList.stream()
                .filter(opt -> !opt.isEmpty())
                .map(Optional::get)
                .collect(Collectors.toList());
        
        System.out.println("Present values (new way): " + presentValues2);
        System.out.println();
        
        // 5. Configuration processing
        System.out.println("5. Configuration processing:");
        List<Optional<String>> configs = List.of(
            Optional.of("database.url=jdbc:postgresql://localhost:5432/mydb"),
            Optional.empty(),
            Optional.of("server.port=8080"),
            Optional.of("logging.level=INFO"),
            Optional.empty()
        );
        
        System.out.println("Processing configurations:");
        configs.stream()
                .filter(Optional::isPresent)
                .map(Optional::get)
                .forEach(config -> {
                    String[] parts = config.split("=", 2);
                    if (parts.length == 2) {
                        System.out.println("  " + parts[0] + " = " + parts[1]);
                    }
                });
        System.out.println();
        
        // 6. Error handling patterns
        System.out.println("6. Error handling patterns:");
        
        // Simulate different scenarios
        Optional<String> result1 = processData("valid-data");
        Optional<String> result2 = processData("");
        Optional<String> result3 = processData(null);
        
        System.out.println("Processing 'valid-data':");
        handleResult(result1);
        
        System.out.println("Processing empty string:");
        handleResult(result2);
        
        System.out.println("Processing null:");
        handleResult(result3);
        System.out.println();
        
        // 7. Performance comparison
        System.out.println("7. Performance comparison:");
        
        Optional<String> testOptional = Optional.of("test");
        
        // Time isEmpty()
        long start = System.nanoTime();
        for (int i = 0; i < 1000000; i++) {
            boolean isEmpty = testOptional.isEmpty();
        }
        long isEmptyTime = System.nanoTime() - start;
        
        // Time !isPresent()
        start = System.nanoTime();
        for (int i = 0; i < 1000000; i++) {
            boolean isEmpty = !testOptional.isPresent();
        }
        long isPresentTime = System.nanoTime() - start;
        
        System.out.println("isEmpty() time: " + isEmptyTime + " ns");
        System.out.println("!isPresent() time: " + isPresentTime + " ns");
        System.out.println("Performance difference: " + String.format("%.2f", (double) isPresentTime / isEmptyTime) + "x");
        System.out.println();
        
        // 8. Best practices
        System.out.println("8. Best practices:");
        System.out.println("- Use isEmpty() for better readability when checking for empty optionals");
        System.out.println("- isEmpty() is more intuitive than !isPresent()");
        System.out.println("- Both methods have similar performance");
        System.out.println("- isEmpty() makes code more self-documenting");
        System.out.println("- Consider using isEmpty() in new code for consistency");
    }
    
    // Helper method for user validation
    private static void validateUser(User user) {
        Optional<String> email = Optional.ofNullable(user.getEmail());
        
        if (email.isEmpty()) {
            System.out.println("User '" + user.getName() + "' has no email (null)");
        } else if (email.get().trim().isEmpty()) {
            System.out.println("User '" + user.getName() + "' has empty email");
        } else {
            System.out.println("User '" + user.getName() + "' has valid email: " + email.get());
        }
    }
    
    // Helper method for processing data
    private static Optional<String> processData(String data) {
        if (data == null || data.trim().isEmpty()) {
            return Optional.empty();
        }
        return Optional.of("Processed: " + data.toUpperCase());
    }
    
    // Helper method for handling results
    private static void handleResult(Optional<String> result) {
        if (result.isEmpty()) {
            System.out.println("  No result available");
        } else {
            System.out.println("  Result: " + result.get());
        }
    }
    
    // Simple User class for examples
    static class User {
        private final String email;
        private final String name;
        
        public User(String email, String name) {
            this.email = email;
            this.name = name;
        }
        
        public String getEmail() {
            return email;
        }
        
        public String getName() {
            return name;
        }
    }
} 