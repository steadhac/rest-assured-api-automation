package Authentication;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasKey;

import core.BaseTest;
import org.testng.annotations.Test;
import utils.ExtentReport;

public class AuthenticationValidation extends BaseTest {

    @Test
    public void validateAPIKeyAuthentication () {
        ExtentReport.extentlog =
                ExtentReport.extentreport.
                        startTest("validateAPIKeyAuthentication", "Validate id and key ");
        int id = 2172797;
        given ().when ()
                .spec (requestSpecification)
                .queryParam ("apiKey", "e4c66f5be087d70d2ba00f3c84a067a1")
                .queryParam ("id", id)
                .get ("https://api.openweathermap.org/data/2.5/weather")
                .then ()
                .spec (responseSpecification)
                .body ("id", equalTo (id));
    }

    @Test
    public void validateBasicAuth () {
        ExtentReport.extentlog =
                ExtentReport.extentreport.
                        startTest("validateBasicAuth", "Validate postman echo");

        given ().spec (requestSpecification)
                .auth ()
                .basic ("postman", "password")
                .get ("https://postman-echo.com/basic-auth")
                .then ()
                .spec (responseSpecification)
                .body ("$", hasKey ("authenticated"))
                .body ("authenticated", equalTo (true));
    }

    @Test
    public void validatePreemptiveAuth() {
        ExtentReport.extentlog =
                ExtentReport.extentreport.
                        startTest("validatePreemptiveAuth", "Validate postman echo");

        given ().spec (requestSpecification)
                .auth ()
                .preemptive ()
                .basic ("postman", "password")
                .get ("https://postman-echo.com/basic-auth")
                .then ()
                .spec (responseSpecification)
                .body ("$", hasKey ("authenticated"))
                .body ("authenticated", equalTo (true));
    }

    @Test
    public void validateDigestAuth() {
        ExtentReport.extentlog =
                ExtentReport.extentreport.
                        startTest("validateDigestAuth", "Validate postman echo");

        given ().spec (requestSpecification)
                .auth ()
                .digest ("postman", "password")
                .get ("https://postman-echo.com/basic-auth")
                .then ()
                .spec (responseSpecification)
                .body ("$", hasKey ("authenticated"))
                .body ("authenticated", equalTo (true));
    }
}
