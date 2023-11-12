/*
 * Copyright (c) 2023. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package userManagement;

import core.BaseTest;
import core.StatusCode;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import utils.ExtentReport;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class APIChaininig extends BaseTest {

    @Test
    public void verifyBookstoreAddBooks() {
        ExtentReport.extentlog =
                ExtentReport.extentreport.
                        startTest("verifyBookstoreAddBooks", "Validate 201 Status Code for POST method");
        String authToken = generateAuthToken();
        Response response = given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer "+authToken)
                .body("{\"userId\":\"6fdcd89a-7efd-407e-b5ae-7d873cb9c16f\",\"collectionOfIsbns\":[{\"isbn\":\"9781593275846\"}]}")
                .when()
                .post("https://bookstore.toolsqa.com/BookStore/v1/Books");
        assertEquals(response.getStatusCode(), StatusCode.CREATED.code);
        System.out.println("verifyBookstoreAddBooks executed successfully");
    }

    private String generateAuthToken() {
        Response response = given()
                .header("Content-Type", "application/json")
                .body("{\"userName\":\"caro\",\"password\":\"Test@123\"}")
                .when()
                .post("https://bookstore.toolsqa.com/Account/v1/GenerateToken");
        assertEquals(response.getStatusCode(), StatusCode.SUCCESS.code);
        System.out.println("generateAuthToken executed successfully");
        String authToken = response.path("token");
        System.out.println("Token: " + authToken);
        return authToken;
    }
}

