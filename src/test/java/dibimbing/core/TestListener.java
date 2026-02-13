package dibimbing.core;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class TestListener implements ITestListener {
    private static final Logger log = LogManager.getLogger(TestListener.class);

    private static ExtentReports extent;
    private static final ThreadLocal<ExtentTest> testThread = new ThreadLocal<>();

    @Override
    public void onStart(ITestContext context) {
        ExtentSparkReporter reporter = new ExtentSparkReporter("./reports/extent-report.html");
        reporter.config().setReportName("TestNG Report");
        reporter.config().setDocumentTitle("Extent Report");

        extent = new ExtentReports();
        extent.attachReporter(reporter);
    }

    @Override
    public void onTestStart(ITestResult result) {

        String browser = result
                .getTestContext()
                .getCurrentXmlTest()
                .getParameter("browser");

        ExtentTest test = extent.createTest(
                "[" + browser.toUpperCase() + "] " + result.getMethod().getMethodName(),
                result.getMethod().getDescription()
        );

        // Category dari browser
        test.assignCategory(browser.toUpperCase());

        // Category dari TestNG groups
        for (String group : result.getMethod().getGroups()) {
            test.assignCategory(group.toUpperCase());
        }

        testThread.set(test);
    }


    @Override
    public void onTestSuccess(ITestResult result) {
        testThread.get().pass("Test passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        WebDriver driver = DriverManager.getDriver();
        ExtentTest test = testThread.get();

        String testName = result.getMethod().getMethodName().replaceAll("[^a-zA-Z0-9._-]", "_");
        Path screenshotDir = Path.of(System.getProperty("user.dir"), "reports", "screenshots");
        Path screenshotPath = screenshotDir.resolve(testName + ".png");
        String relativePath = "screenshots/" + testName + ".png";

        try {
            Files.createDirectories(screenshotDir);
        } catch (Exception e) {
            log.error("Failed to create screenshot directory", e);
        }

        if (driver != null) {
            try {
                File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                Files.copy(src.toPath(), screenshotPath, StandardCopyOption.REPLACE_EXISTING);

                if (test != null) {
                    test.fail("Test Failed: " + result.getThrowable())
                            .addScreenCaptureFromPath(relativePath);
                }
            } catch (Exception e) {
                log.error("Failed to capture/copy screenshot", e);
            }
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        testThread.get().skip("Test skipped");
    }

    @Override
    public void onFinish(ITestContext context) {
        if (extent != null) extent.flush();
        testThread.remove();
    }
}
