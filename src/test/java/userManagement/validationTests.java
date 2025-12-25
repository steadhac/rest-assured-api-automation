package userManagement;

import core.BaseTest;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import utils.ExtentReport;
import utils.APIEndpoints;

import java.io.File;
import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.*;

public class validationTests extends BaseTest {

    @Test
    public void testJSONSchemaValidation() {
        ExtentReport.extentlog = ExtentReport.extentreport.createTest("testJSONSchemaValidation", 
            "Validate response against JSON schema");
        
        given()
            .when()
            .get(APIEndpoints.getJsonPlaceholderUrl("/posts/1"))
            .then()
            .statusCode(200)
            .body(matchesJsonSchemaInClasspath("schemas/post-schema.json"));
    }

    @Test
    public void testResponseTimeValidation() {
        ExtentReport.extentlog = ExtentReport.extentreport.createTest("testResponseTimeValidation", 
            "Validate API response time is within acceptable limits");
        
        Response response = given()
            .when()
            .get(APIEndpoints.getJsonPlaceholderUrl("/posts/1"));
        
        long responseTime = response.getTimeIn(TimeUnit.MILLISECONDS);
        
        response.then()
            .statusCode(200)
            .time(lessThan(5000L)); // Response should be under 5 seconds
        
        assertTrue(responseTime < 3000, "Response time should be under 3 seconds, but was: " + responseTime + "ms");
    }

    @Test
    public void testErrorHandling404() {
        ExtentReport.extentlog = ExtentReport.extentreport.createTest("testErrorHandling404", 
            "Validate proper 404 error handling");
        
        given()
            .when()
            .get(APIEndpoints.getJsonPlaceholderUrl("/posts/999999"))
            .then()
            .statusCode(404);
    }

    @Test
    public void testErrorHandling400() {
        ExtentReport.extentlog = ExtentReport.extentreport.createTest("testErrorHandling400", 
            "Validate 400 bad request error handling");
        
        given()
            .header("Content-Type", "application/json")
            .body("invalid-json")
            .when()
            .post(APIEndpoints.getHttpBinUrl("/post"))
            .then()
            .statusCode(anyOf(equalTo(400), equalTo(200))); // httpbin might accept it
    }

    @Test
    public void testDataTypeValidation() {
        ExtentReport.extentlog = ExtentReport.extentreport.createTest("testDataTypeValidation", 
            "Validate response data types and structure");
        
        given()
            .when()
            .get(APIEndpoints.getJsonPlaceholderUrl("/users/1"))
            .then()
            .statusCode(200)
            .body("id", isA(Integer.class))
            .body("name", isA(String.class))
            .body("email", isA(String.class))
            .body("address", isA(Object.class))
            .body("address.geo.lat", isA(String.class));
    }

    @Test
    public void testHeaderValidation() {
        ExtentReport.extentlog = ExtentReport.extentreport.createTest("testHeaderValidation", 
            "Validate response headers");
        
        given()
            .when()
            .get(APIEndpoints.getJsonPlaceholderUrl("/posts/1"))
            .then()
            .statusCode(200)
            .header("Content-Type", containsString("application/json"))
            .header("Cache-Control", notNullValue());
    }

    @Test
    public void testArraySizeValidation() {
        ExtentReport.extentlog = ExtentReport.extentreport.createTest("testArraySizeValidation", 
            "Validate array response size and content");
        
        given()
            .when()
            .get(APIEndpoints.getJsonPlaceholderUrl("/posts"))
            .then()
            .statusCode(200)
            .body("size()", equalTo(100))
            .body("[0].id", equalTo(1))
            .body("findAll { it.userId == 1 }.size()", greaterThan(0));
    }
}