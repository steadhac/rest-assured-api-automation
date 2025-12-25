package userManagement;

import core.BaseTest;
import org.testng.annotations.Test;
import utils.ExtentReport;
import utils.APIEndpoints;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertEquals;

public class authenticationTests extends BaseTest {

    @Test
    public void testBearerTokenAuth() {
        ExtentReport.extentlog = ExtentReport.extentreport.createTest("testBearerTokenAuth", 
            "Validate Bearer token authentication");
        
        given()
            .header("Authorization", "Bearer test-token")
            .when()
            .get(APIEndpoints.getHttpBinUrl("/bearer"))
            .then()
            .statusCode(200)
            .body("authenticated", equalTo(true))
            .body("token", equalTo("test-token"));
    }

    @Test
    public void testAPIKeyAuth() {
        ExtentReport.extentlog = ExtentReport.extentreport.createTest("testAPIKeyAuth", 
            "Validate API key authentication");
        
        given()
            .queryParam("api_key", "test-api-key")
            .when()
            .get(APIEndpoints.getHttpBinUrl("/get"))
            .then()
            .statusCode(200)
            .body("args.api_key", equalTo("test-api-key"));
    }

    @Test
    public void testUnauthorizedAccess() {
        ExtentReport.extentlog = ExtentReport.extentreport.createTest("testUnauthorizedAccess", 
            "Validate 401 response for unauthorized access");
        
        given()
            .when()
            .get(APIEndpoints.getHttpBinUrl("/bearer"))
            .then()
            .statusCode(401);
    }

    @Test
    public void testBasicAuthSuccess() {
        ExtentReport.extentlog = ExtentReport.extentreport.createTest("testBasicAuthSuccess", 
            "Validate successful basic authentication");
        
        given()
            .auth().basic("user", "passwd")
            .when()
            .get(APIEndpoints.getHttpBinUrl("/basic-auth/user/passwd"))
            .then()
            .statusCode(200)
            .body("authenticated", equalTo(true))
            .body("user", equalTo("user"));
    }
}