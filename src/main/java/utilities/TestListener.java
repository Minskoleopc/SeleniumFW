package utilities;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import base.BaseTest;
import org.openqa.selenium.WebDriver;

public class TestListener extends BaseTest implements ITestListener {
    private static ExtentReports extent = new ExtentReports();
    private static ExtentSparkReporter spark = new ExtentSparkReporter(System.getProperty("user.dir") + "/reports/ExtentReport.html");
    private ExtentTest test;

    static {
        extent.attachReporter(spark);
    }

    @Override
    public void onStart(ITestContext context) {
        System.out.println("Test Execution Started");
    }

    @Override
    public void onTestStart(ITestResult result) {
        test = extent.createTest(result.getMethod().getMethodName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        WebDriver driver = ((BaseTest) result.getInstance()).driver;
        String screenshotPath = ScreenshotUtil.captureScreenshot(driver, result.getMethod().getMethodName());
        test.fail("Test Failed: " + result.getThrowable());
        test.addScreenCaptureFromPath(screenshotPath);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.pass("Test Passed");
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }
}
