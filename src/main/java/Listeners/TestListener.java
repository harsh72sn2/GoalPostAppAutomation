package Listeners;

import Utils.*;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import io.appium.java_client.android.AndroidStartScreenRecordingOptions;
import io.appium.java_client.screenrecording.CanRecordScreen;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.annotations.BeforeMethod;

import java.io.*;
import java.lang.reflect.Method;
import java.util.Base64;


public class TestListener implements ITestListener
{
    //Extent Report Declarations
    public static ExtentReports extent = ExtentReport.createInstance();
    public static ExtentTest test;
    public static ExtentTest classLevelLog;
    private static Logger Log= LogManager.getLogger(TestListener.class);
    AppBaseSetup base = new AppBaseSetup();

    public synchronized void onStart(ITestContext context) {
        Log.info("Extent Reports Version 3 Test Suite started!");
    }
    
    public synchronized void onFinish(ITestContext context) {
        Log.info(("Extent Reports Version 3  Test Suite is ending!"));
        extent.flush();
    }

    public synchronized void onTestStart(ITestResult result) {
        Log.info((result.getMethod().getMethodName() + " started!"));
        ITestNGMethod testNGMethod = result.getMethod();
        Method method = testNGMethod.getConstructorOrMethod().getMethod();
        String scpth = "";
        Object testClass = result.getInstance();
        WebDriver webDriver;
        webDriver = ((AppBaseSetup) testClass).getDriver();

    }

    public synchronized void onTestSuccess(ITestResult result) {
        ITestNGMethod testNGMethod = result.getMethod();
        Method method = testNGMethod.getConstructorOrMethod().getMethod();
        String scpth = "";
        Log.info((result.getMethod().getMethodName() + " passed!"));
        test.pass("Test passed");
        Object testClass = result.getInstance();
        WebDriver webDriver;
        webDriver = ((AppBaseSetup) testClass).getDriver();
        File screenshot = ((TakesScreenshot)webDriver).getScreenshotAs(OutputType.FILE);

        //Take base64Screenshot screenshot.
        InputStream is = null;
        try {
            is = new FileInputStream(screenshot);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        byte[] imageBytes = new byte[0];
        try {
            imageBytes = IOUtils.toByteArray(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String base64Screenshot = Base64.getEncoder().encodeToString(imageBytes);

        test.log(Status.PASS, "Snapshot below: Please click base64 image", MediaEntityBuilder.createScreenCaptureFromBase64String(base64Screenshot).build());
        markBrowserStackSession(webDriver, "passed", "Test passed");
        Log.info((result.getMethod().getMethodName() + " passed!"));
    }

    public synchronized void onTestFailure(ITestResult result) {

        ITestNGMethod testNGMethod = result.getMethod();
        Method method = testNGMethod.getConstructorOrMethod().getMethod();
        String scpth = "";
        Log.info((result.getMethod().getMethodName() + " failed!"));
        test.fail(result.getThrowable());
        Object testClass = result.getInstance();
        WebDriver webDriver;
        webDriver = ((AppBaseSetup) testClass).getDriver();

        File screenshot = ((TakesScreenshot)webDriver).getScreenshotAs(OutputType.FILE);


        //Take base64Screenshot screenshot.
        InputStream is = null;
        try {
            is = new FileInputStream(screenshot);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        byte[] imageBytes = new byte[0];
        try {
            imageBytes = IOUtils.toByteArray(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String base64Screenshot = Base64.getEncoder().encodeToString(imageBytes);

        test.log(Status.FAIL, "Snapshot below: Please click base64 image", MediaEntityBuilder.createScreenCaptureFromBase64String(base64Screenshot).build());
        markBrowserStackSession(webDriver, "failed", getFailureReason(result));
        Log.info((result.getMethod().getMethodName() + " failed!"));
        test.fail(result.getThrowable());
    }

    public synchronized void onTestSkipped(ITestResult result) {
        Log.info((result.getMethod().getMethodName() + " skipped!"));
        test.skip(result.getThrowable());
        Object testClass = result.getInstance();
        WebDriver webDriver = ((AppBaseSetup) testClass).getDriver();
        markBrowserStackSession(webDriver, "failed", "Test skipped: " + getFailureReason(result));
    }


    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
    }

    private void markBrowserStackSession(WebDriver webDriver, String status, String reason) {
        if(!(webDriver instanceof JavascriptExecutor)) {
            return;
        }
        try {
            String escapedReason = escapeJson(reason == null ? "" : reason);
            ((JavascriptExecutor) webDriver).executeScript(
                    "browserstack_executor: {\"action\":\"setSessionStatus\",\"arguments\":{\"status\":\""
                            + status + "\",\"reason\":\"" + escapedReason + "\"}}");
        } catch (Exception e) {
            Log.warn("Unable to mark BrowserStack session status: " + e.getMessage());
        }
    }

    private String getFailureReason(ITestResult result) {
        if(result.getThrowable() == null || result.getThrowable().getMessage() == null) {
            return result.getMethod().getMethodName();
        }
        return result.getThrowable().getMessage();
    }

    private String escapeJson(String value) {
        return value
                .replace("\\", "\\\\")
                .replace("\"", "\\\"")
                .replace("\b", "\\b")
                .replace("\f", "\\f")
                .replace("\n", "\\n")
                .replace("\r", "\\r")
                .replace("\t", "\\t");
    }
}
