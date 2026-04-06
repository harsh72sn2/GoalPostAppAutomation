package Utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Optional;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class Utilities {

    //generate Option from A,B,C,D,E. Take input options 4 or 5.
    public static String getOption(int numberOfOptions) {
        String []fourOptions={"A","B","C","D"};
        String []fiveOptions={"A","B","C","D","E"};
        Random random= new Random();
        if(numberOfOptions==4) {
            return fourOptions[random.nextInt(fourOptions.length)];
        } else if(numberOfOptions==5){
            return fiveOptions[random.nextInt(fiveOptions.length)];
        } else
            return "INVALID INPUT";
    }

    //generate Option from A,B,C,D,E. Take input options 4 or 5.
    public static int getOptionNumber(int numberOfOptions) {
        String []fourOptions={"A","B","C","D"};
        String []fiveOptions={"A","B","C","D","E"};
        Random random= new Random();
        if(numberOfOptions==4) {
            return random.nextInt((4 -1) + 1) + 1;
        } else if(numberOfOptions==5){
            return random.nextInt((5 -1) + 1) + 1;
        } else
            return -1;
    }


    public static void takeScreenshot(WebDriver driver, String scpath) {
        File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        try {
            String fileLocation = System.getProperty("user.dir") + "/TestReport/";
            FileUtils.copyFile(screenshot,  new File(fileLocation+scpath+".png"));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void takeScreenshot(WebDriver driver) {
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        String strDate = dateFormat.format(date);
        takeScreenshot(driver, "Debug ScreenShot" + strDate);
    }

}
