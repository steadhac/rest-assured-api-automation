package userManagement;

import core.BaseTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.ExtentReport;
import utils.APIEndpoints;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertEquals;

public class dataDrivenTests extends BaseTest {

    @DataProvider(name = "userIds")
    public Object[][] getUserIds() {
        return new Object[][] {
            {1, "Leanne Graham"},
            {2, "Ervin Howell"},
            {3, "Clementine Bauch"},
            {4, "Patricia Lebsack"},
            {5, "Chelsey Dietrich"}
        };
    }

    @DataProvider(name = "postIds")
    public Object[][] getPostIds() {
        return new Object[][] {
            {1, 1, "sunt aut facere repellat provident occaecati excepturi optio reprehenderit"},
            {2, 1, "qui est esse"},
            {3, 1, "ea molestias quasi exercitationem repellat qui ipsa sit aut"},
            {4, 1, "eum et est occaecati"},
            {5, 1, "nesciunt quas odio"}
        };
    }

    @DataProvider(name = "httpStatusCodes")
    public Object[][] getHttpStatusCodes() {
        return new Object[][] {
            {200, "OK"},
            {201, "Created"},
            {404, "Not Found"},
            {500, "Internal Server Error"}
        };
    }

    @Test(dataProvider = "userIds")
    public void testMultipleUsers(int userId, String expectedName) {
        ExtentReport.extentlog = ExtentReport.extentreport.createTest("testMultipleUsers_" + userId, 
            "Validate user data for user ID: " + userId);
        
        given()
            .when()
            .get(APIEndpoints.getJsonPlaceholderUrl("/users/" + userId))
            .then()
            .statusCode(200)
            .body("id", equalTo(userId))
            .body("name", equalTo(expectedName));
    }

    @Test(dataProvider = "postIds")
    public void testMultiplePosts(int postId, int expectedUserId, String expectedTitle) {
        ExtentReport.extentlog = ExtentReport.extentreport.createTest("testMultiplePosts_" + postId, 
            "Validate post data for post ID: " + postId);
        
        given()
            .when()
            .get(APIEndpoints.getJsonPlaceholderUrl("/posts/" + postId))
            .then()
            .statusCode(200)
            .body("id", equalTo(postId))
            .body("userId", equalTo(expectedUserId))
            .body("title", equalTo(expectedTitle));
    }

    @Test(dataProvider = "httpStatusCodes")
    public void testHttpStatusCodes(int statusCode, String statusText) {
        ExtentReport.extentlog = ExtentReport.extentreport.createTest("testHttpStatusCodes_" + statusCode, 
            "Validate HTTP status code: " + statusCode);
        
        given()
            .when()
            .get(APIEndpoints.getHttpBinUrl("/status/" + statusCode))
            .then()
            .statusCode(statusCode);
    }

    @Test
    public void testParameterizedSearch() {
        ExtentReport.extentlog = ExtentReport.extentreport.createTest("testParameterizedSearch", 
            "Test search with different parameters");
        
        String[] searchTerms = {"lorem", "dolor", "ipsum", "amet"};
        
        for (String term : searchTerms) {
            given()
                .queryParam("q", term)
                .when()
                .get(APIEndpoints.getJsonPlaceholderUrl("/posts"))
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0));
        }
    }

    @Test
    public void testBoundaryValues() {
        ExtentReport.extentlog = ExtentReport.extentreport.createTest("testBoundaryValues", 
            "Test API with boundary values");
        
        int[] boundaryValues = {0, 1, 100, 101, -1};
        
        for (int value : boundaryValues) {
            int expectedStatus = (value >= 1 && value <= 100) ? 200 : 404;
            
            given()
                .when()
                .get(APIEndpoints.getJsonPlaceholderUrl("/posts/" + value))
                .then()
                .statusCode(expectedStatus);
        }
    }
}