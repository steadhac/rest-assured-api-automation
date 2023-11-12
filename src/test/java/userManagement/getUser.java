package userManagement;


import core.BaseTest;
import core.StatusCode;
import io.restassured.RestAssured;
import io.restassured.http.Cookie;
import io.restassured.http.Cookies;
import io.restassured.response.Response;

import java.io.IOException;

import java.util.*;

import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;
import utils.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertEquals;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class getUser extends BaseTest {
    String serverAddress = PropertyReader.propertyReader("config.properties", "server");
    String endpoint = getUrl("endpoint");
    String URL = serverAddress + endpoint;
    //SoftAssertionUtil softAssertion = new SoftAssertionUtil();
    public String getUrl(String key) {
        String endpoint = null;
        try {
            endpoint = JsonReader.getTestData(key);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return endpoint;
    }
    @Test
    public void getUserData() {
        ExtentReport.extentlog =
                ExtentReport.extentreport.
                        startTest("getUserData", "Validate 200 ");
        given().
                when().get("https://reqres.in/api/users?page=2").
                then().
                assertThat().
                statusCode(200);
    }

    @Test()
    public void validateGetResponseBody() {
        ExtentReport.extentlog =
                ExtentReport.extentreport.
                        startTest("validateGetResponseBody", "Send a GET request and validate the response body using 'then' Validate title equal to delectus aut autem userId 1, 200 ");

        // Set base URI for the API
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";

        // Send a GET request and validate the response body using 'then'
        given()
                .when()
                .get("/todos/1")
                .then()
                .assertThat()
                .statusCode(200)
                .body(not(isEmptyString()))
                .body("title", equalTo("delectus aut autem"))
                .body("userId", equalTo(1));
    }
    @Test(description = "validateResponseHasItems" )
    public void validateResponseHasItems() {
        ExtentReport.extentlog =
                ExtentReport.extentreport.
                        startTest("validateResponseHasItems", "Use Hamcrest to check that the response body has a specific size");

        // Set base URI for the API
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";

        // Send a GET request and store the response in a variable
        Response response =
                given()
                        .when()
                        .get("/posts")
                        .then()
                        .extract()
                        .response();

        // Use Hamcrest to check that the response body contains specific items
        assertThat(response.jsonPath().getList("title"), hasItems("sunt aut facere repellat provident occaecati excepturi optio reprehenderit", "qui est esse"));
    }

    @Test
    public void validateResponseHasSize() {
        ExtentReport.extentlog =
                ExtentReport.extentreport.
                        startTest("validateResponseHasSize", " Use Hamcrest to check that the response body contains specific items");

        // Set base URI for the API
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";

        // Send a GET request and store the response in a variable
        Response response = given()
                .when()
                .get("/comments")
                .then()
                .extract()
                .response();

        // Use Hamcrest to check that the response body has a specific size
        assertThat(response.jsonPath().getList(""), hasSize(500));
    }

    @Test
    public void testGetUserList() {
        ExtentReport.extentlog =
                ExtentReport.extentreport.
                        startTest("testGetUserList", " check that the response body contains specific email");

        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
        given()
                .when()
                .get("/comments?postId=1")
                .then()
                .statusCode(200)
                .body("email[0]", containsString("Eliseo@gardner.biz"));
    }

    @Test
    public void testGetUsersWithQueryParameters() {
        ExtentReport.extentlog =
                ExtentReport.extentreport.
                        startTest("testGetUsersWithQueryParameters", " check that the response body contains size of users and the information of user 3");

        RestAssured.baseURI = "https://reqres.in/api";
        Response response = given()
                .queryParam("page", 2)
                .when()
                .get("/users")
                .then()
                .statusCode(200)
                .extract()
                .response();

        // Assert that the response contains 6 users
        response.then().body("data", hasSize(6));

        // Assert that the third user in the list has the correct values
        response.then().body("data[2].id", is(9));
        response.then().body("data[2].email", is("tobias.funke@reqres.in"));
        response.then().body("data[2].first_name", is("Tobias"));
        response.then().body("data[2].last_name", is("Funke"));
        response.then().body("data[2].avatar", is("https://reqres.in/img/faces/9-image.jpg"));
    }
    @Test()
    public void validateStatusCodeGetUser() {
        ExtentReport.extentlog =
                ExtentReport.extentreport.
                        startTest("validateStatusCodeGetUser", " Validate status code with testNG");

        System.out.println("*****************");
        Response resp =
                given()
                        .queryParam("page", 2)
                        .when()
                        .get("https://reqres.in/api/users");
        int actualStatusCode = resp.statusCode();  //RestAssured
        assertEquals(actualStatusCode, 200); //Testng
    }
    @Test
    public void testGetUsersWithMultipleQueryParams() {
        ExtentReport.extentlog =
                ExtentReport.extentreport.
                        startTest("testGetUsersWithMultipleQueryParams", " Validate status code with multiple params");

        Response response =
                given()
                        .queryParam("page", 2)
                        .queryParam("per_page", 3)
                        .queryParam("rtqsdr", 4)
                        .when()
                        .get("https://reqres.in/api/users")
                        .then()
                        .statusCode(200)
                        .extract()
                        .response();
    }
    @Test
    public void testCreateUserWithFormParam() {
        ExtentReport.extentlog =
                ExtentReport.extentreport.
                        startTest("testCreateUserWithFormParam", " Validate that create user with form");

        Response response = given()
                .contentType("application/x-www-form-urlencoded")
                .formParam("name", "John Doe")
                .formParam("job", "Developer")
                .when()
                .post("https://reqres.in/users")
                .then()
                .statusCode(201)
                .extract()
                .response();

        // Assert that the response contains the correct name and job values
        response.then().body("name", equalTo("John Doe"));
        response.then().body("job", equalTo("Developer"));
    }
    @Test
    public void testFetchCookies() {
        Response response = given()
                .when()
                .get(URL + "?page=2")
                .then()
                .extract().response();

        Map<String, String> cookies = response.getCookies();
        Cookies cookies1 = response.getDetailedCookies();
        cookies1.getValue("server");
        assertEquals(cookies1.getValue("server"), "cloudflare");
        assertThat(cookies, hasKey("JSESSIONID"));
        assertThat(cookies, hasValue("ABCDEF123456"));
    }
    @Test(groups = {"SmokeSuite", "RegressionSuite"})
    public void verifyStatusCodeDelete() {
        ExtentReport.extentlog =
                ExtentReport.extentreport.
                        startTest("verifyStatusCodeDelete", "Validate 204 status code for DELETE Method");
        Response resp = given()
                .delete("https://reqres.in/api/users/2");
        assertEquals(resp.getStatusCode(), 204);
        System.out.println("verifyStatusCodeDelete executed successfully");
    }

}
