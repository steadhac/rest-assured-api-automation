package core;

import com.aventstack.extentreports.Status;
import helper.BaseTestHelper;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import utils.ExtentReport;

import java.io.IOException;

/**
 * Base test class that provides common setup and teardown functionality for all API tests.
 * This class handles ExtentReport initialization, test result logging, and cleanup operations.
 * 
 * All test classes should extend this class to inherit the reporting and lifecycle management capabilities.
 * 
 * @author API Automation Team
 * @version 2.0
 * @since 1.0
 */
public class BaseTest {
    /**
     * Configures the test environment before the entire test suite execution.
     * This method runs once before all tests and sets up:
     * - Creates timestamped report directory
     * - Initializes ExtentReport with HTML report configuration
     * - Sets up system information for reporting
     * 
     * @throws IOException if report directory creation or ExtentReport initialization fails
     * @see ExtentReport#initialize(String)
     * @see BaseTestHelper#Timestamp()
     */
    @BeforeSuite(alwaysRun = true)
    public void config() throws IOException {
        String subfolderpath = System.getProperty("user.dir") + "/reports/" + BaseTestHelper.Timestamp();
        BaseTestHelper.CreateFolder(subfolderpath);
        ExtentReport.initialize(subfolderpath + "/" + "rest-assured-api-automation-report.html");
    }

    /**
     * Processes and logs test execution results after each test method.
     * This method runs after every test method and performs the following:
     * - Captures test execution status (PASS/FAIL/SKIP)
     * - Logs appropriate messages to ExtentReport
     * - Handles error details for failed tests
     * - Provides graceful error handling for logging failures
     * 
     * Error Handling: If ExtentReport logging fails, errors are logged to System.err 
     * to ensure test execution continues without interruption.
     * 
     * @param result the TestNG ITestResult containing test execution details
     *               including status, name, and throwable information
     * @see ITestResult#getStatus()
     * @see ITestResult#getName()
     * @see ITestResult#getThrowable()
     */
    @AfterMethod(alwaysRun = true)
    public void getResult(ITestResult result) {
        try {
            if (ExtentReport.extentlog != null) {
                if (result.getStatus() == ITestResult.SUCCESS) {
                    ExtentReport.extentlog.log(Status.PASS, "Test Case: " + result.getName() + " is passed");
                } else if (result.getStatus() == ITestResult.FAILURE) {
                    ExtentReport.extentlog.log(Status.FAIL, "Test case: " + result.getName() + " is failed");
                    String errorMessage = result.getThrowable() != null ? result.getThrowable().getMessage() : "Unknown error";
                    ExtentReport.extentlog.log(Status.FAIL, "Test case failed due to: " + errorMessage);
                } else if (result.getStatus() == ITestResult.SKIP) {
                    ExtentReport.extentlog.log(Status.SKIP, "Test case is skipped: " + result.getName());
                }
            }
        } catch (Exception e) {
            System.err.println("Error logging test result: " + e.getMessage());
        }
    }

    /**
     * Finalizes and flushes the ExtentReport after all tests complete.
     * This method runs once after the entire test suite execution and ensures:
     * - All test results are written to the HTML report
     * - Report resources are properly released
     * - Final report generation is completed
     * 
     * Note: This method is critical for report generation. 
     * Without calling flush(), the HTML report may be incomplete or corrupted.
     * 
     * @see ExtentReport#flush()
     */
    @AfterSuite(alwaysRun = true)
    public void endReport() {
        ExtentReport.flush();
    }
}
