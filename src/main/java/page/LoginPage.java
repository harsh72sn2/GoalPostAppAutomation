package page;

import Listeners.TestListener;
import Utils.AppActionDriver;
import Utils.PropertiesFile;
import com.aventstack.extentreports.Status;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends AppActionDriver {

    AndroidDriver driver;
    Logger logger= LogManager.getLogger(LoginPage.class);
    PropertiesFile propertiesFile;
    public LoginPage(AndroidDriver driver) {
        super(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        propertiesFile= new PropertiesFile("data.properties");
        this.driver = driver;
    }

    @AndroidFindBy(xpath = "(//android.widget.TextView[@text='My Feed'])[1]")
    private WebElement myFeedText;
    @AndroidFindBy(xpath = "//android.widget.TextView[contains(@text,'want to log out?')]")
    private WebElement logoutScreenText;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Menu']")
    private WebElement menuFooterOption;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='Logout']")
    private WebElement logoutButton;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='No, Continue']")
    private WebElement cancelLogout;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Yes, Logout']")
    private WebElement confirmLogout;

    @AndroidFindBy(xpath = "//android.widget.TextView[contains(@text,'Welcome')]")
    private WebElement homeScreenWelcome;



    public String getMyFeedText()
    {
        TestListener.test.log(Status.INFO, "Getting text of Home page header");
        return getText(myFeedText);
    }

    public LoginPage clickMenu()
    {
        TestListener.test.log(Status.INFO, "Clicking Menu in Footer");
        click(menuFooterOption);
        return this;
    }

    public LoginPage clickLogout()
    {
        TestListener.test.log(Status.INFO,"click logout button");
        click(logoutButton);
        return this;
    }

    public String getLogoutCheckText()
    {
        TestListener.test.log(Status.INFO,"Get text of logout sure check");
        return getText(logoutScreenText);
    }

    public LoginPage clickCancelLogout()
    {
        TestListener.test.log(Status.INFO,"Cancelling logout");
        click(cancelLogout);
        return this;
    }

    public boolean checkLogoutButtonInMenu()
    {
        TestListener.test.log(Status.INFO,"checking presence of logout button");
        return isDisplayed(logoutButton);
    }

    public LoginPage clickConfirmLogoutButton()
    {
        TestListener.test.log(Status.INFO, "confirm logout");
        click(confirmLogout);
        return this;
    }

    public String getMainScreenWelcomeText()
    {
        TestListener.test.log(Status.INFO,"get Welcome text main screen");
        return getText(homeScreenWelcome);
    }

}
