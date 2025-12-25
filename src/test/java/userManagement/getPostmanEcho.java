package userManagement;

import core.StatusCode;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import utils.ExtentReport;
import utils.JsonReader;
import utils.APIEndpoints;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class getPostmanEcho {
    @Test(groups = "SmokeSuite")
    public void validateWithTestDataFromJson() throws IOException {
        ExtentReport.extentlog = ExtentReport.extentreport.createTest("validateWithTestDataFromJson", 
            "Validate basic authentication with test data from JSON file");
        
        String username = JsonReader.getTestData("username");
        String password = JsonReader.getTestData("password");
        System.out.println("username from json is: " + username + "***password from json is:" + password);
        Response resp = given()
                .auth()
                .basic(username, password)
                .when()
                .get(APIEndpoints.getPostmanEchoUrl("/basic-auth"));

        int actualStatusCode = resp.statusCode();
        assertEquals(actualStatusCode, 200);
        System.out.println("validateWithTestDataFromJson executed successfully");
    }

    @Test()
    public void validateResponseBodyGetDigestAuth() {
        ExtentReport.extentlog = ExtentReport.extentreport.createTest("validateResponseBodyGetDigestAuth", 
            "Validate digest authentication response");

        Response resp = given()
                .auth()
                .digest("postman", "password")
                .when()
                .get(APIEndpoints.getPostmanEchoUrl("/digest-auth"));

        int actualStatusCode = resp.statusCode();
        assertEquals(actualStatusCode, StatusCode.SUCCESS.code);
        System.out.println(resp.body().asString());
    }

    @Test()
    public void validateResponseBodyGetBasicAuth() {
        ExtentReport.extentlog = ExtentReport.extentreport.createTest("validateResponseBodyGetBasicAuth", 
            "Validate basic authentication response");

        Response resp = given()
                .auth()
                .basic("postman", "password")
                .when()
                .get(APIEndpoints.getPostmanEchoUrl("/basic-auth"));

        int actualStatusCode = resp.statusCode();
        assertEquals(actualStatusCode, 200);
        System.out.println(resp.body().asString());
    }
}
