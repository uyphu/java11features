package com.java11.collections;

import java.util.List;
import java.util.Set;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.HashMap;

/**
 * Demonstrates collection factory methods introduced in Java 11
 */
public class CollectionFactoryExample {
    
    public static void runExamples() {
        // 1. List.of() - creates immutable lists
        System.out.println("1. List.of() - Immutable Lists:");
        List<String> immutableList = List.of("Apple", "Banana", "Cherry");
        System.out.println("Immutable list: " + immutableList);
        System.out.println("List size: " + immutableList.size());
        System.out.println("First element: " + immutableList.get(0));
        
        // Try to modify (will throw UnsupportedOperationException)
        try {
            immutableList.add("Date");
        } catch (UnsupportedOperationException e) {
            System.out.println("Cannot modify immutable list: " + e.getMessage());
        }
        System.out.println();
        
        // 2. Set.of() - creates immutable sets
        System.out.println("2. Set.of() - Immutable Sets:");
        Set<String> immutableSet = Set.of("Red", "Green", "Blue");
        System.out.println("Immutable set: " + immutableSet);
        System.out.println("Set size: " + immutableSet.size());
        System.out.println("Contains 'Red': " + immutableSet.contains("Red"));
        
        // Try to modify (will throw UnsupportedOperationException)
        try {
            immutableSet.add("Yellow");
        } catch (UnsupportedOperationException e) {
            System.out.println("Cannot modify immutable set: " + e.getMessage());
        }
        System.out.println();
        
        // 3. Map.of() - creates immutable maps
        System.out.println("3. Map.of() - Immutable Maps:");
        Map<String, Integer> immutableMap = Map.of("One", 1, "Two", 2, "Three", 3);
        System.out.println("Immutable map: " + immutableMap);
        System.out.println("Map size: " + immutableMap.size());
        System.out.println("Value for 'Two': " + immutableMap.get("Two"));
        
        // Try to modify (will throw UnsupportedOperationException)
        try {
            immutableMap.put("Four", 4);
        } catch (UnsupportedOperationException e) {
            System.out.println("Cannot modify immutable map: " + e.getMessage());
        }
        System.out.println();
        
        // 4. Map.ofEntries() - for more than 10 key-value pairs
        System.out.println("4. Map.ofEntries() - Large Maps:");
        Map<String, String> largeMap = Map.ofEntries(
            Map.entry("Name", "John Doe"),
            Map.entry("Age", "30"),
            Map.entry("City", "New York"),
            Map.entry("Country", "USA"),
            Map.entry("Email", "john@example.com"),
            Map.entry("Phone", "+1-555-0123"),
            Map.entry("Occupation", "Developer"),
            Map.entry("Company", "Tech Corp"),
            Map.entry("Department", "Engineering"),
            Map.entry("Salary", "75000"),
            Map.entry("StartDate", "2023-01-15"),
            Map.entry("Skills", "Java, Spring, Docker")
        );
        System.out.println("Large immutable map created with " + largeMap.size() + " entries");
        System.out.println("Sample entries:");
        largeMap.entrySet().stream()
                .limit(5)
                .forEach(entry -> System.out.println("  " + entry.getKey() + ": " + entry.getValue()));
        System.out.println();
        
        // 5. Comparison with old way
        System.out.println("5. Comparison with old way:");
        
        // Old way - mutable collections
        List<String> oldList = new ArrayList<>();
        oldList.add("Apple");
        oldList.add("Banana");
        oldList.add("Cherry");
        
        Set<String> oldSet = new HashSet<>();
        oldSet.add("Red");
        oldSet.add("Green");
        oldSet.add("Blue");
        
        Map<String, Integer> oldMap = new HashMap<>();
        oldMap.put("One", 1);
        oldMap.put("Two", 2);
        oldMap.put("Three", 3);
        
        System.out.println("Old way (mutable):");
        System.out.println("  List: " + oldList);
        System.out.println("  Set: " + oldSet);
        System.out.println("  Map: " + oldMap);
        
        System.out.println("New way (immutable):");
        System.out.println("  List: " + immutableList);
        System.out.println("  Set: " + immutableSet);
        System.out.println("  Map: " + immutableMap);
        System.out.println();
        
        // 6. Practical example - configuration data
        System.out.println("6. Practical example - Configuration:");
        Map<String, String> config = Map.of(
            "database.url", "jdbc:postgresql://localhost:5432/mydb",
            "database.username", "admin",
            "database.password", "secret",
            "server.port", "8080",
            "server.host", "localhost",
            "logging.level", "INFO",
            "cache.enabled", "true",
            "cache.ttl", "3600"
        );
        
        System.out.println("Application configuration:");
        config.forEach((key, value) -> {
            if (key.contains("password")) {
                System.out.println("  " + key + ": " + "*".repeat(value.length()));
            } else {
                System.out.println("  " + key + ": " + value);
            }
        });
        System.out.println();
        
        // 7. Performance and memory benefits
        System.out.println("7. Benefits of immutable collections:");
        System.out.println("- Thread-safe by design");
        System.out.println("- No defensive copying needed");
        System.out.println("- Can be safely shared between components");
        System.out.println("- Prevents accidental modifications");
        System.out.println("- More efficient memory usage for small collections");
        System.out.println("- Clear intent in code (immutable vs mutable)");
    }
} 