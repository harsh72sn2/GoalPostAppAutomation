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
import org.testng.annotations.Test;

public class FansPage extends AppActionDriver {

    AndroidDriver driver;
    Logger logger= LogManager.getLogger(HomePage.class);
    PropertiesFile propertiesFile;
    public FansPage(AndroidDriver driver) {
        super(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        propertiesFile= new PropertiesFile("data.properties");
        this.driver = driver;
    }

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='Fans']/android.widget.ImageView")
    private WebElement fansButton;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Share']")
    private WebElement shareButton;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Invite']")
    private WebElement inviteButton;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='Share']/following-sibling::android.widget.TextView[1]")
    private WebElement userName;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='Share']/following-sibling::android.widget.TextView[2]")
    private WebElement userId;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='Share']/following-sibling::android.widget.TextView[3]")
    private WebElement primaryTeamField;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='Share']/following-sibling::android.widget.TextView[4]")
    private WebElement primaryTeamName;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='Share']/following-sibling::android.widget.TextView[5]")
    private WebElement bioDetails;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='Share']/following-sibling::android.widget.TextView[6]")
    private WebElement followingCount;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='Share']/following-sibling::android.widget.TextView[7]")
    private WebElement followingField;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='Share']/following-sibling::android.widget.TextView[8]")
    private WebElement followersCount;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='Share']/following-sibling::android.widget.TextView[9]")
    private WebElement followersField;

    public FansPage clickFansButton()
    {
        TestListener.test.log(Status.INFO,"clicking fans button in footer");
        click(fansButton);
        return this;
    }

    public boolean shareButtonDisplayed()
    {
        TestListener.test.log(Status.INFO, "share button displayed on fans tab");
        return isDisplayed(shareButton);
    }

    public void clickShareButton() {
        TestListener.test.log(Status.INFO, "click share button");
        click(shareButton);
    }

    public void clickInviteButton() {
        TestListener.test.log(Status.INFO, "click invite button");
        click(inviteButton);
    }

    public String getUserName() {
        TestListener.test.log(Status.INFO, "get username text");
        return getText(userName);
    }

    public String getUserId() {
        TestListener.test.log(Status.INFO, "get userid text");
        return getText(userId);
    }

    public String getPrimaryTeamField() {
        TestListener.test.log(Status.INFO, "get primary team field text");
        return getText(primaryTeamField);
    }

    public String getPrimaryTeamName() {
        TestListener.test.log(Status.INFO, "get primary team name text");
        return getText(primaryTeamName);
    }

    public String getBioDetails() {
        TestListener.test.log(Status.INFO, "get bio details text");
        return getText(bioDetails);
    }

    public String getFollowingCount() {
        TestListener.test.log(Status.INFO, "get following count");
        return getText(followingCount);
    }

    public String getFollowingField() {
        TestListener.test.log(Status.INFO, "get following field");
        return getText(followingField);
    }

    public String getFollowersCount() {
        TestListener.test.log(Status.INFO, "get followers count");
        return getText(followersCount);
    }

    public String getFollowersField() {
        TestListener.test.log(Status.INFO, "get followers field");
        return getText(followersField);
    }

    public boolean isUserNameDisplayed() {
        return isDisplayed(userName);
    }

    public boolean isUserIdDisplayed() {
        return isDisplayed(userId);
    }

    public boolean isPrimaryTeamDisplayed() {
        return isDisplayed(primaryTeamName);
    }

    public boolean isBioDisplayed() {
        return isDisplayed(bioDetails);
    }

    public boolean isFollowingDisplayed() {
        return isDisplayed(followingCount);
    }

    public boolean isFollowersDisplayed() {
        return isDisplayed(followersCount);
    }

    public int getFollowingCountSafe() {
        TestListener.test.log(Status.INFO, "get following count");
        String text = getText(followingCount).replaceAll("[^0-9]", "");
        return text.isEmpty() ? 0 : Integer.parseInt(text);
    }

    public int getFollowersCountValue() {
        return Integer.parseInt(getText(followersCount));
    }

    public int getFollowersCountSafe() {
        TestListener.test.log(Status.INFO, "get followers count");
        String text = getText(followersCount).replaceAll("[^0-9]", "");
        return text.isEmpty() ? 0 : Integer.parseInt(text);
    }

}
