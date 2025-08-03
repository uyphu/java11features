package com.java11;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Simple test to verify Java 11 features examples
 */
public class MainTest {
    
    @Test
    public void testMainClassExists() {
        // This test verifies that the main class can be instantiated
        assertDoesNotThrow(() -> {
            // Just verify the class exists and can be loaded
            Class<?> mainClass = Class.forName("com.java11.Main");
            assertNotNull(mainClass);
        });
    }
    
    @Test
    public void testStringMethods() {
        // Test Java 11 String methods
        String testString = "  Hello World  ";
        
        // Test isBlank()
        assertTrue("".isBlank());
        assertTrue("   \t\n  ".isBlank());
        assertFalse("Hello".isBlank());
        
        // Test strip()
        assertEquals("Hello World", testString.strip());
        assertEquals("Hello World  ", testString.stripLeading());
        assertEquals("  Hello World", testString.stripTrailing());
        
        // Test repeat()
        assertEquals("***", "*".repeat(3));
        assertEquals("Hello Hello ", "Hello ".repeat(2));
        
        // Test lines()
        String multiLine = "Line 1\nLine 2\nLine 3";
        assertEquals(3, multiLine.lines().count());
    }
    
    @Test
    public void testCollectionFactoryMethods() {
        // Test List.of()
        var list = java.util.List.of("a", "b", "c");
        assertEquals(3, list.size());
        assertThrows(UnsupportedOperationException.class, () -> list.add("d"));
        
        // Test Set.of()
        var set = java.util.Set.of("x", "y", "z");
        assertEquals(3, set.size());
        assertTrue(set.contains("x"));
        
        // Test Map.of()
        var map = java.util.Map.of("key1", "value1", "key2", "value2");
        assertEquals(2, map.size());
        assertEquals("value1", map.get("key1"));
    }
    
    @Test
    public void testOptionalEnhancements() {
        // Test isEmpty()
        var present = java.util.Optional.of("test");
        var empty = java.util.Optional.empty();
        
        assertFalse(present.isEmpty());
        assertTrue(empty.isEmpty());
        assertTrue(present.isPresent());
        assertFalse(empty.isPresent());
    }
    
    @Test
    public void testPathOf() {
        // Test Path.of()
        var path = java.nio.file.Path.of("test", "file.txt");
        assertNotNull(path);
        assertTrue(path.toString().contains("test"));
        assertTrue(path.toString().contains("file.txt"));
    }
} 