#!/bin/bash

# Java 11 Features Examples Runner
# This script compiles and runs the Java 11 examples

echo "=== Java 11 Features Examples ==="
echo ""

# Check if Java 11+ is available
java_version=$(java -version 2>&1 | head -n 1 | cut -d'"' -f2 | cut -d'.' -f1)
if [ "$java_version" -lt 11 ]; then
    echo "Error: Java 11 or higher is required. Current version: $java_version"
    exit 1
fi

echo "Java version: $(java -version 2>&1 | head -n 1)"
echo ""

# Check if Maven is available
if command -v mvn &> /dev/null; then
    echo "Using Maven to build and run examples..."
    echo ""
    
    # Clean and compile
    echo "1. Cleaning and compiling..."
    mvn clean compile
    
    if [ $? -eq 0 ]; then
        echo "Compilation successful!"
        echo ""
        
        # Run all examples
        echo "2. Running all Java 11 examples..."
        echo ""
        mvn exec:java -Dexec.mainClass="com.java11.Main"
        
        echo ""
        echo "3. Running tests..."
        mvn test
        
    else
        echo "Compilation failed!"
        exit 1
    fi
    
else
    echo "Maven not found, using direct Java compilation..."
    echo ""
    
    # Create target directory
    mkdir -p target/classes
    
    # Compile
    echo "1. Compiling Java files..."
    javac -d target/classes -cp ".:lib/*" src/main/java/com/java11/**/*.java
    
    if [ $? -eq 0 ]; then
        echo "Compilation successful!"
        echo ""
        
        # Run main class
        echo "2. Running Java 11 examples..."
        echo ""
        java -cp target/classes com.java11.Main
        
    else
        echo "Compilation failed!"
        exit 1
    fi
fi

echo ""
echo "=== Examples completed ===" 