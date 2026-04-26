package Utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.util.Date;

public class ExtentReport {

    private static ExtentReports extent;
    public static ExtentTest test;

    private static String platform;
    private static String reportFileName = "Report.html";
    private static String fileLocation = System.getProperty("user.dir") + "/TestReport";
    private static String reportFileLoc = fileLocation + "/" + reportFileName;
    private static Logger logger= LogManager.getLogger(ExtentReport.class);

    public static ExtentReports getInstance() {
        if (extent == null)
            createInstance();
        return extent;
    }

    //Create an extent report instance
    public static ExtentReports createInstance() {
        platform = System.getProperty("os.name");
        PropertiesFile prop = new PropertiesFile("app.properties");
        AppBaseSetup.setAppVersion(prop.getValue("appPackage"));
        createReportPath(fileLocation);
	ExtentSparkReporter extentHtmlReporter = new ExtentSparkReporter(reportFileLoc);
        //extentHtmlReporter.config().setTestViewChartLocation(ChartLocation.BOTTOM);
        //extentHtmlReporter.config().setChartVisibilityOnOpen(true);
       // extentHtmlReporter.config().setTheme(Theme.STANDARD);
        extentHtmlReporter.config().setDocumentTitle(reportFileName);
        extentHtmlReporter.config().setEncoding("utf-8");
        extentHtmlReporter.config().setReportName(reportFileName);

        extent = new ExtentReports();
        extent.attachReporter(extentHtmlReporter);
        extent.setSystemInfo("OS", platform);
        extent.setSystemInfo("File", reportFileName);
	    extent.setSystemInfo("User",System.getProperty("user.name"));
	    try {
            PropertiesFile propertiesFile = new PropertiesFile("config.properties");
            logger.info("Setting the app version: "+propertiesFile.getValue("appVersion")+"   "+propertiesFile.getValue("deviceModel"));
            extent.setSystemInfo("App Version", propertiesFile.getValue("appVersion"));
            extent.setSystemInfo("Device Model", propertiesFile.getValue("deviceModel"));
        } catch (Exception e) {
	        System.out.println(e.getMessage());
        }
        return extent;
    }

    //Create the report path if it does not exist
    private static void createReportPath(String path) {
        File testDirectory = new File(path);
        if (!testDirectory.exists()) {
            if (testDirectory.mkdir()) {
                System.out.println("Directory: " + path + " is created!");
            } else {
                System.out.println("Failed to create directory: " + path);
            }
        } else {
            System.out.println("Directory already exists: " + path);
        }
    }

    private static String generateRandomTime() {
        return String.valueOf(new Date().getTime());

    }


}
