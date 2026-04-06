package Utils;

import Listeners.TestListener;
import com.aventstack.extentreports.ExtentTest;
import com.opencsv.CSVReader;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
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

    public static String DEVICE_NAME;

    @BeforeClass
    @Parameters({"deviceName", "platformVersion", "appPackage", "appActivity", "platform","bsApp"})
    public void openApp(@Optional("emulator-5554") String deviceName,
                        @Optional("14") String platformVersion,
                        @Optional("com.football.goalpost") String appPackage,
                        @Optional("com.football.goalpost.MainActivity") String appActivity,
                        @Optional("app") String platform,
                        @Optional("") String bsApp) throws Exception {

        PropertiesFile prop = new PropertiesFile("app.properties");

        ExtentTest classLevelTest = TestListener.extent.createTest(getClass().getSimpleName());
        TestListener.classLevelLog = (classLevelTest);

        // Appium 3 / Appium 2 Server start
        service = AppiumDriverLocalService.buildService(
                new AppiumServiceBuilder().withIPAddress("127.0.0.1").usingAnyFreePort());
        service.start();

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
            // Cloud/BrowserStack Logic maintained
            String localIdentifier = "local" + Math.random();
            HashMap<String, String> bsLocalArgs = new HashMap<>();
            bsLocalArgs.put("key", "sHcASytm5csko3btVAAN");
            bsLocalArgs.put("forcelocal", "true");
            bsLocalArgs.put("localIdentifier", localIdentifier);

            if(bsLocal.isRunning()) bsLocal.stop();
            bsLocal.start(bsLocalArgs);

            String dpf[] = deviceName.split(":");
            cap.setCapability("browserstack.user", "sanjeevkumar_OjfJti");
            cap.setCapability("browserstack.key", "sHcASytm5csko3btVAAN");
            cap.setCapability("browserstack.local", "true");
            cap.setCapability("browserstack.localIdentifier", localIdentifier);
            cap.setCapability("deviceName", dpf[0]);
            cap.setCapability("app", bsApp);
        }

        if(platform.equals("cloud")) {
            driver = new AndroidDriver(new URL("http://hub.browserstack.com/wd/hub"), cap);
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
        if(driver != null) driver.quit();
        if(bsLocal.isRunning()) bsLocal.stop();
        if(service != null) service.stop();
    }

    @BeforeMethod(alwaysRun = true)
    public void clickOnRegisterButton(ITestResult result) {
        ExtentTest extentTest = TestListener.classLevelLog
                .createNode(result.getMethod().getMethodName(),
                        result.getMethod().getDescription());
        TestListener.test = (extentTest);
        TestListener.test.info("Started execution");
    }
}
