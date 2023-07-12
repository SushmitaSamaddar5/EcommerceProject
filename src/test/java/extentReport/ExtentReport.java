package extentReport;

import Utils.Logging;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

public class ExtentReport {

    ExtentReports extentReports;
    ExtentSparkReporter spark;
    ExtentTest extentTest;

    public ExtentReport()
    {
        extentReports = new ExtentReports();
        spark = new ExtentSparkReporter("ExtentReports\\testCaseExecution.html");
        spark.config().setDocumentTitle("Ecommerce Test Execution Report");
        extentReports.attachReporter(spark);
    }

    public void pass(String message)
    {
        extentTest.log(Status.PASS,message);
    }

    public void fail(String message)
    {
        extentTest.log(Status.FAIL,message);
    }

    public void info(String message)
    {
        extentTest.log(Status.INFO,message);
    }

    public void flush()
    {
        extentReports.flush();
    }

    public ExtentTest createTest(String testcaseName)
    {
        extentTest = extentReports.createTest(testcaseName);
        Logging.startTestCase(testcaseName);
        return extentTest;
    }

    public void addScreenshot(WebDriver driver) throws IOException
    {
        extentTest.addScreenCaptureFromPath(Utils.Utility.captureScreenshot(driver));
    }
}
