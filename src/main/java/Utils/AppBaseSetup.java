package Utils;

import Listeners.TestListener;
import com.aventstack.extentreports.ExtentTest;
import com.opencsv.CSVReader;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.*;
import java.net.URL;
import java.util.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.browserstack.local.Local;
import org.testng.annotations.Optional;

@Listeners({TestListener.class})
public class AppBaseSetup {

    protected AndroidDriver driver;
    private AppiumDriverLocalService service;
    private static Logger logger = LogManager.getLogger(AppBaseSetup.class);
    Local bsLocal = new Local();
    private String currentPlatform;

    public static String DEVICE_NAME;

    @BeforeClass
    @Parameters({"deviceName", "platformVersion", "appPackage", "appActivity", "platform","bsApp"})
    public void openApp(@Optional("Samsung Galaxy S26:16.0") String deviceName,
                        @Optional("16") String platformVersion,
                        @Optional("com.football.goalpost") String appPackage,
                        @Optional("com.football.goalpost.MainActivity") String appActivity,
                        @Optional("cloud") String platform,
                        @Optional("bs://11f9818fba27ff8abced0a944513cb4d89a82e1f") String bsApp) throws Exception {

        PropertiesFile prop = new PropertiesFile("app.properties");
        currentPlatform = platform;

        ExtentTest classLevelTest = TestListener.extent.createTest(getClass().getSimpleName());
        TestListener.classLevelLog = (classLevelTest);

        if(!platform.equals("cloud")) {
            // Appium 3 / Appium 2 Server start
            service = AppiumDriverLocalService.buildService(
                    new AppiumServiceBuilder().withIPAddress("127.0.0.1").usingAnyFreePort());
            service.start();
        }

        DesiredCapabilities cap = new DesiredCapabilities();

        if(platform.equals("app")) {
            // Appium 3 requires 'appium:' prefix for vendor capabilities
            cap.setCapability("platformName", "Android");
            cap.setCapability("appium:automationName", "UiAutomator2");
            cap.setCapability("appium:deviceName", deviceName);
            cap.setCapability("appium:udid", deviceName); // Using the device ID provided
            cap.setCapability("appium:platformVersion", platformVersion);

            // Priority: Code Parameters > Properties File
            String pkg = (appPackage != null) ? appPackage : prop.getValue("appPackage");
            String act = (appActivity != null) ? appActivity : prop.getValue("appActivity");

            cap.setCapability("appium:appPackage", pkg);
            cap.setCapability("appium:appActivity", act);

            cap.setCapability("appium:noReset", true); // Set to true to keep app state
            cap.setCapability("appium:autoGrantPermissions", true);
            cap.setCapability("appium:newCommandTimeout", 300);
            cap.setCapability("appium:androidInstallTimeout", 200000);

            DEVICE_NAME = deviceName;

            String apkPath = System.getProperty("apkPath");
            if(apkPath != null && !apkPath.isEmpty()){
                cap.setCapability("appium:app", apkPath);
            }

            try {
                setAppPermission(pkg, "app_permissions.csv");
            } catch (Exception e) {
                logger.error("Permission setup failed: " + e.getMessage());
            }
        }
        else if(platform.equals("m-web")) {
            cap.setCapability("platformName", "Android");
            cap.setCapability("appium:automationName", "UiAutomator2");
            cap.setCapability("appium:deviceName", deviceName);
            cap.setCapability("browserName", "Chrome");
        }
        else if (platform.equals("cloud")) {
//            String browserStackUser = getRequiredBrowserStackConfig("browserstack.user", "BROWSERSTACK_USERNAME");
//            String browserStackKey = getRequiredBrowserStackConfig("browserstack.key", "BROWSERSTACK_ACCESS_KEY");
            String localIdentifier = "local-" + UUID.randomUUID();
            HashMap<String, String> bsLocalArgs = new HashMap<>();
            bsLocalArgs.put("key", "a8aXhysEavrVRWGxBquA");
            bsLocalArgs.put("forcelocal", "true");
            bsLocalArgs.put("localIdentifier", localIdentifier);

            if(bsLocal.isRunning()) bsLocal.stop();
            bsLocal.start(bsLocalArgs);

            String dpf[] = deviceName.split(":");
            MutableCapabilities bstackOptions = new MutableCapabilities();
            String projectName = getOptionalConfig("browserstack.projectName", "BROWSERSTACK_PROJECT_NAME", "GoalPost App");
            String buildName = getOptionalConfig("browserstack.buildName", "BROWSERSTACK_BUILD_NAME", "GoalPost App Automation");
            String sessionName = getOptionalConfig("browserstack.sessionName", "BROWSERSTACK_SESSION_NAME", getClass().getSimpleName());
            String buildIdentifier = getOptionalConfig("runId", "BROWSERSTACK_BUILD_IDENTIFIER", "");
            String buildTag = getOptionalConfig("browserstack.buildTag", "BROWSERSTACK_BUILD_TAG", "");

            cap.setCapability("platformName", "Android");
            cap.setCapability("appium:automationName", "UiAutomator2");
            cap.setCapability("appium:deviceName", dpf[0].trim());
            cap.setCapability("appium:platformVersion", dpf.length > 1 ? dpf[1].trim() : platformVersion);
            cap.setCapability("appium:app", bsApp);

            bstackOptions.setCapability("userName", "craig_eSiPW8");
            bstackOptions.setCapability("accessKey", "a8aXhysEavrVRWGxBquA");
            bstackOptions.setCapability("projectName", projectName);
            bstackOptions.setCapability("buildName", buildName);
            bstackOptions.setCapability("sessionName", sessionName);
            bstackOptions.setCapability("debug", true);
            bstackOptions.setCapability("video", true);
            bstackOptions.setCapability("networkLogs", true);
            bstackOptions.setCapability("appiumLogs", true);
            bstackOptions.setCapability("local", true);
            bstackOptions.setCapability("localIdentifier", localIdentifier);
            if(!buildIdentifier.isEmpty()) {
                bstackOptions.setCapability("buildIdentifier", buildIdentifier);
            }
            if(!buildTag.isEmpty()) {
                bstackOptions.setCapability("buildTag", buildTag);
            }
            cap.setCapability("bstack:options", bstackOptions);
        }

        if(platform.equals("cloud")) {
            driver = new AndroidDriver(new URL("https://hub.browserstack.com/wd/hub"), cap);
        } else {
            driver = new AndroidDriver(service.getUrl(), cap);
        }
    }

    public static void setAppVersion(String appPackage) {
        String cmd = "adb shell dumpsys package '" + appPackage + "' | grep versionName";
        String deviceModel = "adb -s " + DEVICE_NAME + " shell getprop ro.product.model";
        Process process = null;
        try {
            process = Runtime.getRuntime().exec(cmd);
            String line = new BufferedReader(new InputStreamReader(process.getInputStream())).readLine();
            String appVersion = (line != null && line.contains("=")) ? line.split("=")[1] : "unknown";

            process = Runtime.getRuntime().exec(deviceModel);
            String device = new BufferedReader(new InputStreamReader(process.getInputStream())).readLine();

            logger.info("Setting the app version: " + appVersion + ", and device model: " + device);
//            PropertiesFile propertiesFile = new PropertiesFile("config.properties");
//            propertiesFile.setValue("config.properties", "appVersion", appVersion);
//            propertiesFile.setValue("config.properties", "deviceModel", device);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void setAppPermission(String appPackage, String filename) {
        try {
            CSVReader csvReader = new CSVReader(new FileReader(System.getProperty("user.dir") + "/src/main/resources/" + filename));
            List<String[]> permissionsArray = csvReader.readAll();
            for (String[] s1 : permissionsArray) {
                String cmd = "adb shell pm grant " + appPackage + " " + s1[0];
                Runtime.getRuntime().exec(cmd);
            }
        } catch (Exception e1) {
            logger.error("File :" + filename + " Not Found !!! " + e1.getMessage());
        }
    }

    public AndroidDriver getDriver() {
        return driver;
    }

    @AfterClass
    public void stopServer() throws Exception {
        logger.info("Quit Driver");
        if(driver != null) {
            if(!"cloud".equals(currentPlatform)) {
                driver.terminateApp("com.football.goalpost");
            }
            driver.quit();
        }

        if(bsLocal.isRunning()) bsLocal.stop();
        if(service != null) service.stop();
        Thread.sleep(60000);
    }

    private static String getRequiredBrowserStackConfig(String systemPropertyName, String environmentVariableName) {
        String value = System.getProperty(systemPropertyName);
        if(value == null || value.trim().isEmpty()) {
            value = System.getenv(environmentVariableName);
        }
        if(value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException("Missing BrowserStack config. Set -D" + systemPropertyName
                    + " or environment variable " + environmentVariableName);
        }
        return value.trim();
    }

    private static String getOptionalConfig(String systemPropertyName, String environmentVariableName, String defaultValue) {
        String value = System.getProperty(systemPropertyName);
        if(value == null || value.trim().isEmpty()) {
            value = System.getenv(environmentVariableName);
        }
        return value == null || value.trim().isEmpty() ? defaultValue : value.trim();
    }

    @BeforeMethod(alwaysRun = true)
    public void clickOnRegisterButton(ITestResult result) {
        String testName = result.getTestClass().getRealClass().getSimpleName()
                + "." + result.getMethod().getMethodName();
        updateBrowserStackTestName(testName);

        ExtentTest extentTest = TestListener.classLevelLog
                .createNode(result.getMethod().getMethodName(),
                        result.getMethod().getDescription());
        TestListener.test = (extentTest);
        TestListener.test.info("Started execution");
    }

    private void updateBrowserStackTestName(String testName) {
        if(!"cloud".equals(currentPlatform) || driver == null) {
            return;
        }
        try {
            String escapedTestName = escapeJson(testName);
            ((JavascriptExecutor) driver).executeScript(
                    "browserstack_executor: {\"action\":\"annotate\",\"arguments\":{\"data\":\"Started test: "
                            + escapedTestName + "\",\"level\":\"info\"}}");
        } catch (Exception e) {
            logger.warn("Unable to update BrowserStack test name: " + e.getMessage());
        }
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

    @AfterMethod(alwaysRun = true)
    public void logFinalStatus(ITestResult result) {
        if (driver != null && "cloud".equals(currentPlatform)) {
            String testName = result.getMethod().getMethodName();
            String status = result.isSuccess() ? "PASSED" : "FAILED";

            // This adds a final entry to the log and video timeline
            String finalMarker = "COMPLETED: " + testName + " | Status: " + status;
            ((JavascriptExecutor) driver).executeScript(
                    "browserstack_executor: {\"action\": \"annotate\", \"arguments\": {\"data\": \"" + escapeJson(finalMarker) + "\", \"level\": \"" + (result.isSuccess() ? "info" : "error") + "\"}}"
            );

            // Optional: If one test fails, mark the entire session badge as failed
            if (!result.isSuccess()) {
                ((JavascriptExecutor) driver).executeScript(
                        "browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\": \"failed\", \"reason\": \"Test " + testName + " failed.\"}}");
            }
        }
    }
}
