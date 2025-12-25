package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReport {
    public static ExtentReports extentreport = null;
    public static ExtentTest extentlog;
    
    public static void initialize(String reportPath) {
        if (extentreport == null) {
            ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);
            sparkReporter.config().setTheme(Theme.STANDARD);
            sparkReporter.config().setDocumentTitle(PropertyReader.propertyReader("config.properties", "report.title"));
            sparkReporter.config().setReportName(PropertyReader.propertyReader("config.properties", "report.name"));
            
            extentreport = new ExtentReports();
            extentreport.attachReporter(sparkReporter);
            extentreport.setSystemInfo("Host Name", System.getProperty("user.name"));
            extentreport.setSystemInfo("Environment", PropertyReader.propertyReader("config.properties", "environment"));
            extentreport.setSystemInfo("OS", System.getProperty("os.name"));
        }
    }
    
    public static void flush() {
        if (extentreport != null) {
            extentreport.flush();
        }
    }
}
