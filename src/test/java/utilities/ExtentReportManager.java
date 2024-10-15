package utilities;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager implements ITestListener {

    public ExtentSparkReporter sparkReporter;
    public ExtentReports extent;
    public ExtentTest test;

    // Declare WebDriver (make sure to initialize it properly in your tests)
    WebDriver driver;

    @Override
    public void onStart(ITestContext context) {
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        String reportName = "Test-Report-" + timeStamp + "-" + context.getName() + ".html";

        sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "/reports/" + reportName);
        sparkReporter.config().setDocumentTitle("Automation Report");
        sparkReporter.config().setReportName("Functional Testing Report");
        sparkReporter.config().setTheme(Theme.DARK);

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);

        extent.setSystemInfo("Tester Name", System.getProperty("user.name"));
        extent.setSystemInfo("Operating System", System.getProperty("os.name"));
        extent.setSystemInfo("Java Version", System.getProperty("java.version"));
        extent.setSystemInfo("Browser", "Chrome");

        System.out.println("Extent Report initialized for " + context.getName());
    }

    @Override
    public void onTestStart(ITestResult result) {
        test = extent.createTest(result.getName() + " (" + result.getTestClass().getName() + ")");
        test.log(Status.INFO, "Test Started: " + result.getName() + " from " + result.getTestClass().getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.log(Status.PASS, "Test case PASSED: " + result.getName());
        test.log(Status.PASS, "Test Class: " + result.getTestClass().getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        test.log(Status.FAIL, "Test case FAILED: " + result.getName());
        test.log(Status.FAIL, "Test Class: " + result.getTestClass().getName());
        test.log(Status.FAIL, "Cause: " + result.getThrowable());

        // Capture screenshot on failure
        
        try {
	        	String screenshotPath = captureScreenshot(result.getName());
	            test.addScreenCaptureFromPath(screenshotPath);  // Attach screenshot to Extent report
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        test.log(Status.SKIP, "Test case SKIPPED: " + result.getName());
        test.log(Status.SKIP, "Test Class: " + result.getTestClass().getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
        System.out.println("Extent Report flushed for " + context.getName());
    }

    // Capture screenshot method
    public String captureScreenshot(String testName) {
        String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);  // Now driver is initialized

        String destination = System.getProperty("user.dir") + "/screenshots/" + testName + "_" + dateName + ".png";
        File finalDestination = new File(destination);
        source.renameTo(finalDestination);
        return destination;
    }

    // Setter for WebDriver (this can be called from your test setup)
    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }
}
