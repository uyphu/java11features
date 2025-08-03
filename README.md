# Java 11 Features Samples

This repository contains comprehensive examples demonstrating the key features introduced in Java 11.

## Java 11 Key Features Covered

### 1. **HTTP Client (Standard)**
- Modern HTTP client API
- Support for HTTP/2
- Asynchronous requests
- WebSocket support

### 2. **String Methods**
- `isBlank()`, `strip()`, `stripLeading()`, `stripTrailing()`
- `repeat()`, `lines()`

### 3. **Collection Factory Methods**
- `List.of()`, `Set.of()`, `Map.of()`
- Immutable collections

### 4. **Local Variable Syntax for Lambda Parameters**
- `var` in lambda expressions

### 5. **File Methods**
- `Files.readString()`, `Files.writeString()`
- `Path.of()`

### 6. **Optional Enhancements**
- `isEmpty()`

### 7. **Process API Improvements**
- Better process handling and control

## Project Structure

```
src/
├── main/
│   └── java/
│       └── com/
│           └── java11/
│               ├── httpclient/
│               ├── strings/
│               ├── collections/
│               ├── files/
│               ├── optional/
│               └── process/
├── test/
│   └── java/
│       └── com/
│           └── java11/
└── resources/
```

## Requirements

- Java 11 or higher
- Maven 3.6+ (for build management)

## Running the Samples

### Using Maven
```bash
# Compile
mvn compile

# Run specific examples
mvn exec:java -Dexec.mainClass="com.java11.httpclient.HttpClientExample"
mvn exec:java -Dexec.mainClass="com.java11.strings.StringMethodsExample"
mvn exec:java -Dexec.mainClass="com.java11.collections.CollectionFactoryExample"
mvn exec:java -Dexec.mainClass="com.java11.files.FileMethodsExample"
mvn exec:java -Dexec.mainClass="com.java11.optional.OptionalEnhancementsExample"
mvn exec:java -Dexec.mainClass="com.java11.process.ProcessApiExample"

# Run all examples
mvn exec:java -Dexec.mainClass="com.java11.Main"
```

### Using Java directly
```bash
# Compile
javac -d target/classes src/main/java/com/java11/**/*.java

# Run
java -cp target/classes com.java11.Main
```

## Features in Detail

### HTTP Client
The new HTTP client provides a modern, fluent API for making HTTP requests with support for HTTP/2 and WebSockets.

### String Methods
New utility methods for string manipulation including whitespace handling and repetition.

### Collection Factory Methods
Convenient factory methods for creating immutable collections with concise syntax.

### File Operations
Simplified file reading and writing operations with new utility methods.

### Optional Enhancements
Additional methods for better Optional handling and null checking.

### Process API
Improved process handling with better control and monitoring capabilities.

## Contributing

Feel free to add more examples or improve existing ones. Each example should be self-contained and demonstrate practical usage of Java 11 features.
