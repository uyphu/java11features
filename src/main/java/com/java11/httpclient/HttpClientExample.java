package com.java11.httpclient;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * Demonstrates HTTP Client API introduced in Java 11
 */
public class HttpClientExample {
    
    public static void runExamples() {
        try {
            // 1. Basic HTTP GET request
            System.out.println("1. Basic HTTP GET request:");
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://httpbin.org/get"))
                    .build();
            
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("Status code: " + response.statusCode());
            System.out.println("Response body length: " + response.body().length() + " characters");
            System.out.println("Response headers: " + response.headers());
            System.out.println();
            
            // 2. Asynchronous HTTP request
            System.out.println("2. Asynchronous HTTP request:");
            CompletableFuture<HttpResponse<String>> futureResponse = client.sendAsync(
                    request, 
                    HttpResponse.BodyHandlers.ofString()
            );
            
            // Do other work while waiting
            System.out.println("Request sent asynchronously, doing other work...");
            Thread.sleep(1000);
            
            HttpResponse<String> asyncResponse = futureResponse.get();
            System.out.println("Async response status: " + asyncResponse.statusCode());
            System.out.println();
            
            // 3. HTTP POST request with JSON
            System.out.println("3. HTTP POST request with JSON:");
            String jsonBody = "{\"name\":\"John Doe\",\"email\":\"john@example.com\",\"age\":30}";
            
            HttpRequest postRequest = HttpRequest.newBuilder()
                    .uri(URI.create("https://httpbin.org/post"))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                    .build();
            
            HttpResponse<String> postResponse = client.send(postRequest, HttpResponse.BodyHandlers.ofString());
            System.out.println("POST Status code: " + postResponse.statusCode());
            System.out.println("POST Response preview: " + postResponse.body().substring(0, Math.min(200, postResponse.body().length())) + "...");
            System.out.println();
            
            // 4. HTTP request with custom configuration
            System.out.println("4. HTTP request with custom configuration:");
            HttpClient customClient = HttpClient.newBuilder()
                    .connectTimeout(Duration.ofSeconds(10))
                    .followRedirects(HttpClient.Redirect.NORMAL)
                    .build();
            
            HttpRequest customRequest = HttpRequest.newBuilder()
                    .uri(URI.create("https://httpbin.org/delay/2"))
                    .timeout(Duration.ofSeconds(5))
                    .build();
            
            try {
                HttpResponse<String> customResponse = customClient.send(customRequest, HttpResponse.BodyHandlers.ofString());
                System.out.println("Custom request completed: " + customResponse.statusCode());
            } catch (Exception e) {
                System.out.println("Custom request failed (expected due to timeout): " + e.getMessage());
            }
            System.out.println();
            
            // 5. Multiple concurrent requests
            System.out.println("5. Multiple concurrent requests:");
            HttpRequest[] requests = {
                HttpRequest.newBuilder().uri(URI.create("https://httpbin.org/delay/1")).build(),
                HttpRequest.newBuilder().uri(URI.create("https://httpbin.org/delay/1")).build(),
                HttpRequest.newBuilder().uri(URI.create("https://httpbin.org/delay/1")).build()
            };
            
            CompletableFuture<HttpResponse<String>>[] futures = new CompletableFuture[requests.length];
            for (int i = 0; i < requests.length; i++) {
                futures[i] = client.sendAsync(requests[i], HttpResponse.BodyHandlers.ofString());
            }
            
            // Wait for all to complete
            CompletableFuture.allOf(futures).join();
            
            for (int i = 0; i < futures.length; i++) {
                HttpResponse<String> concurrentResponse = futures[i].get();
                System.out.println("Request " + (i + 1) + " completed: " + concurrentResponse.statusCode());
            }
            System.out.println();
            
            // 6. Error handling
            System.out.println("6. Error handling:");
            HttpRequest errorRequest = HttpRequest.newBuilder()
                    .uri(URI.create("https://httpbin.org/status/404"))
                    .build();
            
            try {
                HttpResponse<String> errorResponse = client.send(errorRequest, HttpResponse.BodyHandlers.ofString());
                System.out.println("Error response status: " + errorResponse.statusCode());
                System.out.println("Error response body: " + errorResponse.body());
            } catch (Exception e) {
                System.out.println("Request failed: " + e.getMessage());
            }
            System.out.println();
            
            // 7. Request with headers
            System.out.println("7. Request with custom headers:");
            HttpRequest headerRequest = HttpRequest.newBuilder()
                    .uri(URI.create("https://httpbin.org/headers"))
                    .header("User-Agent", "Java11-HttpClient-Demo")
                    .header("Accept", "application/json")
                    .header("X-Custom-Header", "CustomValue")
                    .build();
            
            HttpResponse<String> headerResponse = client.send(headerRequest, HttpResponse.BodyHandlers.ofString());
            System.out.println("Headers response status: " + headerResponse.statusCode());
            System.out.println("Headers response preview: " + headerResponse.body().substring(0, Math.min(300, headerResponse.body().length())) + "...");
            System.out.println();
            
            // 8. Comparison with old way (URLConnection)
            System.out.println("8. Comparison with old way:");
            System.out.println("Old way (URLConnection):");
            System.out.println("  - More verbose code");
            System.out.println("  - Manual stream handling");
            System.out.println("  - No built-in async support");
            System.out.println("  - Limited timeout control");
            System.out.println();
            System.out.println("New way (HttpClient):");
            System.out.println("  - Fluent API");
            System.out.println("  - Built-in async support");
            System.out.println("  - Better timeout handling");
            System.out.println("  - HTTP/2 support");
            System.out.println("  - Modern Java features integration");
            System.out.println();
            
            // 9. Performance comparison
            System.out.println("9. Performance comparison:");
            
            // Time synchronous request
            long start = System.currentTimeMillis();
            HttpResponse<String> syncResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
            long syncTime = System.currentTimeMillis() - start;
            
            // Time asynchronous request
            start = System.currentTimeMillis();
            CompletableFuture<HttpResponse<String>> perfFuture = client.sendAsync(request, HttpResponse.BodyHandlers.ofString());
            HttpResponse<String> asyncPerfResponse = perfFuture.get();
            long asyncTime = System.currentTimeMillis() - start;
            
            System.out.println("Synchronous request time: " + syncTime + " ms");
            System.out.println("Asynchronous request time: " + asyncTime + " ms");
            System.out.println("Both requests completed successfully");
            System.out.println();
            
            // 10. Benefits of new HTTP Client
            System.out.println("10. Benefits of new HTTP Client:");
            System.out.println("- Modern, fluent API design");
            System.out.println("- Built-in support for HTTP/2");
            System.out.println("- Asynchronous and synchronous modes");
            System.out.println("- Better timeout and connection management");
            System.out.println("- Improved security defaults");
            System.out.println("- Better integration with Java 11 features");
            System.out.println("- More efficient resource usage");
            System.out.println("- Enhanced error handling");
            
        } catch (Exception e) {
            System.err.println("Error in HTTP client operations: " + e.getMessage());
            e.printStackTrace();
        }
    }
} 