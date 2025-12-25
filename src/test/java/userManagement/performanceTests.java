package userManagement;

import core.BaseTest;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import utils.ExtentReport;
import utils.APIEndpoints;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.testng.Assert.*;

public class performanceTests extends BaseTest {

    @Test
    public void testResponseTimeUnder2Seconds() {
        ExtentReport.extentlog = ExtentReport.extentreport.createTest("testResponseTimeUnder2Seconds", 
            "Validate API response time is under 2 seconds");
        
        given()
            .when()
            .get(APIEndpoints.getJsonPlaceholderUrl("/posts"))
            .then()
            .statusCode(200)
            .time(lessThan(2000L));
    }

    @Test
    public void testConcurrentRequests() {
        ExtentReport.extentlog = ExtentReport.extentreport.createTest("testConcurrentRequests", 
            "Test API with multiple concurrent requests");
        
        int numberOfRequests = 5;
        List<CompletableFuture<Response>> futures = new ArrayList<>();
        
        // Send concurrent requests
        for (int i = 0; i < numberOfRequests; i++) {
            CompletableFuture<Response> future = CompletableFuture.supplyAsync(() ->
                given()
                    .when()
                    .get(APIEndpoints.getJsonPlaceholderUrl("/posts/1"))
            );
            futures.add(future);
        }
        
        // Wait for all requests to complete and validate
        futures.forEach(future -> {
            try {
                Response response = future.get();
                assertEquals(response.getStatusCode(), 200);
                assertTrue(response.getTimeIn(TimeUnit.MILLISECONDS) < 5000);
            } catch (Exception e) {
                fail("Concurrent request failed: " + e.getMessage());
            }
        });
    }

    @Test
    public void testLoadWithMultipleEndpoints() {
        ExtentReport.extentlog = ExtentReport.extentreport.createTest("testLoadWithMultipleEndpoints", 
            "Test load across multiple API endpoints");
        
        String[] endpoints = {
            "https://jsonplaceholder.typicode.com/posts/1",
            "https://jsonplaceholder.typicode.com/users/1",
            "https://jsonplaceholder.typicode.com/comments/1",
            "https://jsonplaceholder.typicode.com/albums/1"
        };
        
        long totalTime = 0;
        int successCount = 0;
        
        for (String endpoint : endpoints) {
            long startTime = System.currentTimeMillis();
            
            Response response = given()
                .when()
                .get(endpoint);
            
            long endTime = System.currentTimeMillis();
            totalTime += (endTime - startTime);
            
            if (response.getStatusCode() == 200) {
                successCount++;
            }
        }
        
        double averageTime = totalTime / (double) endpoints.length;
        double successRate = (successCount / (double) endpoints.length) * 100;
        
        assertTrue(averageTime < 3000, "Average response time should be under 3 seconds");
        assertEquals(successRate, 100.0, "All requests should be successful");
    }

    @Test
    public void testMemoryUsageWithLargeResponse() {
        ExtentReport.extentlog = ExtentReport.extentreport.createTest("testMemoryUsageWithLargeResponse", 
            "Test API response handling with large data sets");
        
        Runtime runtime = Runtime.getRuntime();
        long memoryBefore = runtime.totalMemory() - runtime.freeMemory();
        
        Response response = given()
            .when()
            .get(APIEndpoints.getJsonPlaceholderUrl("/posts"));
        
        long memoryAfter = runtime.totalMemory() - runtime.freeMemory();
        long memoryUsed = memoryAfter - memoryBefore;
        
        assertEquals(response.getStatusCode(), 200);
        assertTrue(response.jsonPath().getList("").size() > 0);
        
        // Memory usage should be reasonable (less than 10MB for this test)
        assertTrue(memoryUsed < 10 * 1024 * 1024, 
            "Memory usage should be reasonable, but used: " + memoryUsed + " bytes");
    }

    @Test
    public void testTimeoutHandling() {
        ExtentReport.extentlog = ExtentReport.extentreport.createTest("testTimeoutHandling", 
            "Test API timeout handling");
        
        try {
            given()
                .config(io.restassured.config.RestAssuredConfig.config()
                    .httpClient(io.restassured.config.HttpClientConfig.httpClientConfig()
                        .setParam("http.connection.timeout", 1000)
                        .setParam("http.socket.timeout", 1000)))
                .when()
                .get(APIEndpoints.getHttpBinUrl("/delay/5")) // 5 second delay
                .then()
                .statusCode(200);
            
            fail("Request should have timed out");
        } catch (Exception e) {
            // Expected timeout exception
            assertTrue(e.getMessage().contains("timeout") || e.getMessage().contains("timed out"));
        }
    }
}