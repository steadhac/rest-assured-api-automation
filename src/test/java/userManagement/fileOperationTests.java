package userManagement;

import core.BaseTest;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import utils.ExtentReport;
import utils.APIEndpoints;

import java.io.File;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.*;

public class fileOperationTests extends BaseTest {

    @Test
    public void testFileUpload() {
        ExtentReport.extentlog = ExtentReport.extentreport.createTest("testFileUpload", 
            "Validate file upload functionality");
        
        // Create a temporary test file
        File testFile = new File("test-upload.txt");
        
        given()
            .multiPart("file", testFile, "text/plain")
            .when()
            .post(APIEndpoints.getHttpBinUrl("/post"))
            .then()
            .statusCode(200)
            .body("files", notNullValue());
    }

    @Test
    public void testMultipartFormData() {
        ExtentReport.extentlog = ExtentReport.extentreport.createTest("testMultipartFormData", 
            "Validate multipart form data submission");
        
        given()
            .multiPart("name", "John Doe")
            .multiPart("email", "john@example.com")
            .multiPart("age", "30")
            .when()
            .post(APIEndpoints.getHttpBinUrl("/post"))
            .then()
            .statusCode(200)
            .body("form.name", equalTo("John Doe"))
            .body("form.email", equalTo("john@example.com"))
            .body("form.age", equalTo("30"));
    }

    @Test
    public void testFileDownload() {
        ExtentReport.extentlog = ExtentReport.extentreport.createTest("testFileDownload", 
            "Validate file download and content verification");
        
        Response response = given()
            .when()
            .get(APIEndpoints.getHttpBinUrl("/json"))
            .then()
            .statusCode(200)
            .header("Content-Type", containsString("application/json"))
            .extract().response();
        
        // Verify response has content
        assertNotNull(response.getBody().asString());
        assertTrue(response.getBody().asString().length() > 0);
    }

    @Test
    public void testImageDownload() {
        ExtentReport.extentlog = ExtentReport.extentreport.createTest("testImageDownload", 
            "Validate image file download");
        
        Response response = given()
            .when()
            .get(APIEndpoints.getHttpBinUrl("/image/png"))
            .then()
            .statusCode(200)
            .header("Content-Type", equalTo("image/png"))
            .extract().response();
        
        // Verify binary content exists
        byte[] imageBytes = response.getBody().asByteArray();
        assertTrue(imageBytes.length > 0);
    }
}