# Java 11 Features Summary

This document provides a comprehensive overview of the Java 11 features demonstrated in this project.

## 🚀 Key Features Covered

### 1. **HTTP Client (Standard)**
**Location**: `src/main/java/com/java11/httpclient/HttpClientExample.java`

**What's New**: Java 11 introduced a modern, built-in HTTP client that replaces the old `HttpURLConnection`.

**Key Benefits**:
- **Fluent API**: Modern, easy-to-use builder pattern
- **HTTP/2 Support**: Native support for HTTP/2 protocol
- **Asynchronous**: Built-in async/await support with `CompletableFuture`
- **Better Performance**: More efficient than the old `HttpURLConnection`
- **Modern Features**: Timeout handling, redirect policies, connection pooling

**Example Usage**:
```java
HttpClient client = HttpClient.newHttpClient();
HttpRequest request = HttpRequest.newBuilder()
    .uri(URI.create("https://api.example.com/data"))
    .build();
HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
```

### 2. **String Methods**
**Location**: `src/main/java/com/java11/strings/StringMethodsExample.java`

**New Methods**:
- `isBlank()`: Checks if string is empty or contains only whitespace
- `strip()`, `stripLeading()`, `stripTrailing()`: Removes whitespace
- `repeat(int)`: Repeats string n times
- `lines()`: Returns stream of lines

**Example Usage**:
```java
String text = "  Hello World  ";
boolean isBlank = text.isBlank();           // false
String stripped = text.strip();             // "Hello World"
String repeated = "*".repeat(5);            // "*****"
long lineCount = text.lines().count();      // 1
```

### 3. **Collection Factory Methods**
**Location**: `src/main/java/com/java11/collections/CollectionFactoryExample.java`

**New Methods**:
- `List.of()`: Creates immutable lists
- `Set.of()`: Creates immutable sets
- `Map.of()`: Creates immutable maps
- `Map.ofEntries()`: For maps with more than 10 entries

**Example Usage**:
```java
List<String> list = List.of("a", "b", "c");
Set<String> set = Set.of("x", "y", "z");
Map<String, Integer> map = Map.of("one", 1, "two", 2);
```

**Benefits**:
- **Immutability**: Thread-safe by design
- **Performance**: More efficient for small collections
- **Convenience**: Concise syntax
- **Safety**: Prevents accidental modifications

### 4. **File Methods**
**Location**: `src/main/java/com/java11/files/FileMethodsExample.java`

**New Methods**:
- `Files.readString(Path)`: Reads entire file as string
- `Files.writeString(Path, String)`: Writes string to file
- `Path.of(String...)`: Convenient Path creation

**Example Usage**:
```java
Path file = Path.of("data.txt");
String content = Files.readString(file);
Files.writeString(file, "New content");
```

**Benefits**:
- **Simplified I/O**: No need for manual stream handling
- **Better Performance**: Optimized for string operations
- **Cleaner Code**: More readable than byte array operations

### 5. **Optional Enhancements**
**Location**: `src/main/java/com/java11/optional/OptionalEnhancementsExample.java`

**New Method**:
- `isEmpty()`: Checks if Optional is empty (opposite of `isPresent()`)

**Example Usage**:
```java
Optional<String> opt = Optional.of("value");
if (opt.isEmpty()) {
    // Handle empty case
}
```

**Benefits**:
- **Better Readability**: More intuitive than `!isPresent()`
- **Consistency**: Aligns with other collection methods
- **Self-Documenting**: Clearer intent in code

### 6. **Process API Improvements**
**Location**: `src/main/java/com/java11/process/ProcessApiExample.java`

**New Features**:
- `ProcessHandle`: Rich process information and control
- `ProcessHandle.current()`: Get current process info
- `ProcessHandle.allProcesses()`: Stream of all processes
- Better process lifecycle management

**Example Usage**:
```java
ProcessHandle current = ProcessHandle.current();
System.out.println("PID: " + current.pid());
ProcessHandle parent = current.parent().orElse(null);
```

**Benefits**:
- **Rich Information**: PID, command, arguments, start time
- **Process Tree**: Navigate parent/child relationships
- **Better Control**: Improved process management
- **Monitoring**: Enhanced system monitoring capabilities

## 🛠️ Project Structure

```
java11features/
├── src/
│   ├── main/java/com/java11/
│   │   ├── Main.java                    # Main entry point
│   │   ├── httpclient/                  # HTTP Client examples
│   │   ├── strings/                     # String methods examples
│   │   ├── collections/                 # Collection factory examples
│   │   ├── files/                       # File methods examples
│   │   ├── optional/                    # Optional enhancements
│   │   └── process/                     # Process API examples
│   └── test/java/com/java11/
│       └── MainTest.java                # Unit tests
├── pom.xml                              # Maven configuration
├── README.md                            # Project documentation
├── run-examples.sh                      # Run script
└── FEATURES_SUMMARY.md                  # This file
```

## 🚀 How to Run

### Using Maven
```bash
# Run all examples
mvn exec:java -Dexec.mainClass="com.java11.Main"

# Run specific examples
mvn exec:java -Dexec.mainClass="com.java11.strings.StringMethodsExample"
mvn exec:java -Dexec.mainClass="com.java11.httpclient.HttpClientExample"
```

### Using the Script
```bash
./run-examples.sh
```

### Using Java Directly
```bash
javac -d target/classes src/main/java/com/java11/**/*.java
java -cp target/classes com.java11.Main
```

## 🧪 Testing

Run the tests to verify all features work correctly:
```bash
mvn test
```

## 📊 Performance Improvements

The examples demonstrate several performance improvements:

1. **String Operations**: `isBlank()` is ~5x faster than `trim().isEmpty()`
2. **HTTP Client**: Better performance than `HttpURLConnection`
3. **Collections**: More efficient memory usage for immutable collections
4. **File I/O**: Optimized string-based file operations

## 🔧 Requirements

- **Java 11 or higher** (required for all features)
- **Maven 3.6+** (for build management)
- **Internet connection** (for HTTP Client examples)

## 🎯 Best Practices

1. **Use `isBlank()`** instead of `trim().isEmpty()` for better performance
2. **Prefer immutable collections** (`List.of()`, `Set.of()`, `Map.of()`) when possible
3. **Use `isEmpty()`** with Optional for better readability
4. **Leverage HTTP Client** for modern HTTP operations
5. **Use `Files.readString()`/`writeString()`** for simple file operations
6. **Utilize Process API** for better process management

## 🔮 Migration Guide

### From Java 8/9/10 to Java 11

1. **Replace `HttpURLConnection`** with `HttpClient`
2. **Use `isBlank()`** instead of `trim().isEmpty()`
3. **Replace manual collection creation** with factory methods
4. **Use `Files.readString()`** instead of manual stream reading
5. **Replace `!optional.isPresent()`** with `optional.isEmpty()`
6. **Use `ProcessHandle`** for better process management

## 📚 Additional Resources

- [Java 11 Release Notes](https://openjdk.java.net/projects/jdk/11/)
- [Java 11 Documentation](https://docs.oracle.com/en/java/javase/11/)
- [HTTP Client Guide](https://openjdk.java.net/groups/net/httpclient/intro.html)
- [Collection Factory Methods](https://openjdk.java.net/jeps/269)

## 🤝 Contributing

Feel free to add more examples or improve existing ones. Each example should:
- Be self-contained and runnable
- Demonstrate practical usage
- Include performance comparisons where relevant
- Show both old and new approaches
- Include proper error handling 