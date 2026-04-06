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

public class HomePage extends AppActionDriver {

    AndroidDriver driver;
    Logger logger= LogManager.getLogger(HomePage.class);
    PropertiesFile propertiesFile;
    public HomePage(AndroidDriver driver) {
        super(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        propertiesFile= new PropertiesFile("data.properties");
        this.driver = driver;
    }

    @AndroidFindBy(xpath = "//android.widget.EditText[@text='Search Post']")
    private WebElement searchPostField;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='Share']/preceding-sibling::android.view.ViewGroup[@content-desc][2]/android.widget.ImageView")
    private WebElement likeButton;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='Share']/preceding-sibling::android.view.ViewGroup[@content-desc][2]/android.widget.TextView")
    private WebElement likeCount;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='Share']/preceding-sibling::android.view.ViewGroup[@content-desc][3]/android.widget.ImageView")
    private WebElement commentButton;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='Share']/preceding-sibling::android.view.ViewGroup[@content-desc][3]/android.widget.TextView")
    private WebElement commentCount;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='Share']/following-sibling::android.view.ViewGroup[1]/android.widget.TextView")
    private WebElement viewCount;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='Share']/android.widget.ImageView")
    private WebElement shareButton;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='Report']/android.widget.ImageView")
    private WebElement reportButton;

    @AndroidFindBy(xpath = "//android.widget.EditText")
    private WebElement commentField;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='Send']")
    private WebElement sendCommentButton;

    @AndroidFindBy(xpath = "//android.widget.EditText[contains(@text,'Write')]/preceding-sibling::android.widget.ScrollView//android.widget.TextView[3]")
    private WebElement commentTextValue;

    @AndroidFindBy(xpath = "//android.widget.TextView[contains(@text,'your mind?')]")
    private WebElement createPostFieldHome;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[contains(@content-desc,'Post')]//android.widget.EditText")
    private WebElement createPostTextField;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='Post']")
    private WebElement postButton;

    @AndroidFindBy(xpath = "((//android.widget.ScrollView)[2]//android.widget.TextView)[1]")
    private WebElement newPostUserName;

    @AndroidFindBy(xpath = "((//android.widget.ScrollView)[2]//android.widget.TextView)[2]")
    private WebElement newPostCommunity;

    @AndroidFindBy(xpath = "(//android.view.ViewGroup[@content-desc='Share']/preceding-sibling::android.view.ViewGroup/android.widget.TextView)[1]")
    private WebElement newPostText;

    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='com.android.intentresolver:id/headline']")
    private WebElement sharePopUpHeader;

    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='android:id/content_preview_text']")
    private WebElement sharedPostURL;

    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='android:id/text1' and @text='WhatsApp']")
    private WebElement sharePlatformText;

    @AndroidFindBy(xpath = "(//android.widget.ScrollView)[2]//android.view.ViewGroup[2]/android.widget.ImageView")
    private WebElement postThreeDots;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='Edit Post']")
    private WebElement editPostButton;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='Delete Post']")
    private WebElement deletePostButton;

    @AndroidFindBy(xpath = "//android.widget.TextView[contains(@text,'Delete Post')]")
    private WebElement deletePostPopupHeader;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='Cancel']")
    private WebElement cancelButtonDeletePostPopup;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='Delete']")
    private WebElement deletePostPopupDeleteButton;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='Cancel']")
    private WebElement cancelPostUpdateMenu;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[contains(@content-desc,'your mind?')]//following-sibling::android.view.ViewGroup[3]/android.widget.ImageView")
    private WebElement createPollCommunityPlusIcon;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='Create Poll']")
    private WebElement createPollButton;

    @AndroidFindBy(xpath = "//android.widget.EditText[@text='What would you like to ask?']")
    private WebElement pollQuestionField;

    @AndroidFindBy(xpath = "//android.widget.EditText[@text='Option 1']")
    private WebElement pollOption1Field;

    @AndroidFindBy(xpath = "//android.widget.EditText[@text='Option 2']")
    private WebElement pollOption2Field;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Delete Poll']")
    private WebElement deletePollButton;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Cancel']")
    private WebElement cancelPollOptionsMenu;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='Delete']")
    private WebElement deleteConfirmButtonPoll;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='Cancel']")
    private WebElement deleteCancelButtonPoll;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='Create Sub Community']")
    private WebElement createSubCommunityButton;

    public boolean checkSearchField()
    {
        TestListener.test.log(Status.INFO,"Checking presence of search field");
        return isDisplayed(searchPostField);
    }

    public HomePage searchPost(String searchString)
    {
        type(searchPostField, searchString);
        return this;
    }

    public HomePage clickLike()
    {
        TestListener.test.log(Status.INFO, "clicking like button");
        click(likeButton);
        return this;
    }

    public String getLikeCount()
    {
        TestListener.test.log(Status.INFO,"get like count");
        return getText(likeCount);
    }

    public HomePage clickCommentButton()
    {
        TestListener.test.log(Status.INFO,"clicking comment button");
        click(commentButton);
        return this;
    }

    public String getCommentCount()
    {
        TestListener.test.log(Status.INFO,"getting comment count");
        return getText(commentCount);
    }

    public boolean checkCommentFieldPresence()
    {
        TestListener.test.log(Status.INFO,"checking presence of comment field");
        return isDisplayed(commentField);
    }

    public HomePage inputComment(String comment)
    {
        TestListener.test.log(Status.INFO, "inputting comment text");
        type(commentField,comment);
        return this;
    }

    public boolean isSendCommentEnabled()
    {
        TestListener.test.log(Status.INFO, "click send comment");
        return isEnabled(sendCommentButton);
    }

    public HomePage sendComment()
    {
        TestListener.test.log(Status.INFO, "click send comment");
        click(sendCommentButton);
        return this;
    }

    public String getViewCount()
    {
        TestListener.test.log(Status.INFO,"get view count");
        return getText(viewCount);
    }

    public HomePage clickCreatePost()
    {
        TestListener.test.log(Status.INFO, "click create post");
        click(createPostFieldHome);
        return this;
    }

    public boolean checkPostButton()
    {
        TestListener.test.log(Status.INFO, "checking presence of send post button");
        return isDisplayed(postButton);
    }

    public HomePage enterTextPostField(String postText)
    {
        TestListener.test.log(Status.INFO, "enter post text");
        type(createPostTextField,postText);
        return this;
    }

    public HomePage clickPostButton()
    {
        TestListener.test.log(Status.INFO,"clicking send post button");
        click(postButton);
        return this;
    }

    public String getCreatedPostUserName()
    {
        TestListener.test.log(Status.INFO, "getting post username");
        return getText(newPostUserName);
    }

    public String getCreatedPostCommunity()
    {
        TestListener.test.log(Status.INFO,"getting post community");
        return getText(newPostCommunity);
    }

    public String getPostText()
    {
        TestListener.test.log(Status.INFO,"getting post text");
        return getText(newPostText);
    }

    public HomePage clickReportButton()
    {
        TestListener.test.log(Status.INFO, "click report button");
        click(reportButton);
        return this;
    }

    public boolean checkPresenceOfReportButton()
    {
        TestListener.test.log(Status.INFO, "check report button");
        return isDisplayed(reportButton);
    }

    public HomePage clickShareButton()
    {
        TestListener.test.log(Status.INFO,"click share button");
        click(shareButton);
        return this;
    }

    public String verifySharePopUp()
    {
        TestListener.test.log(Status.INFO, "verify share popup");
        return getText(sharePopUpHeader);
    }

    public HomePage clickPostThreeDots()
    {
        TestListener.test.log(Status.INFO, "click new psot three dots");
        click(postThreeDots);
        return this;
    }

    public String getSharedPostUrl()
    {
        TestListener.test.log(Status.INFO, "getting shared post URL");
        return getText(sharedPostURL);
    }

    public String getSharedPostPlatformText()
    {
        TestListener.test.log(Status.INFO, "getting shared platform text");
        return getText(sharePlatformText);
    }

    public boolean checkPresenceOfEditPost()
    {
        TestListener.test.log(Status.INFO,"Checking presence of Edit Post button");
        return isDisplayed(editPostButton);
    }

    public boolean checkPresenceOfDeletePost()
    {
        TestListener.test.log(Status.INFO,"Checking presence of Delete Post button");
        return isDisplayed(deletePostButton);
    }

    public HomePage clickEditPostButton()
    {
        TestListener.test.log(Status.INFO, "click edit post button");
        click(editPostButton);
        return this;
    }

    public HomePage clickDeletePostButton()
    {
        TestListener.test.log(Status.INFO, "click delete post button");
        click(deletePostButton);
        return this;
    }

    public HomePage clickCancelPostUpdate()
    {
        TestListener.test.log(Status.INFO, "click cancel post update popup");
        click(cancelPostUpdateMenu);
        return this;
    }

    public HomePage clickCancelDeletePopup()
    {
        TestListener.test.log(Status.INFO, "click cancel in delete popup");
        click(cancelButtonDeletePostPopup);
        return this;
    }

    public HomePage clickDeleteButtonDeletePopup()
    {
        TestListener.test.log(Status.INFO,"click delete button in delete popup");
        click(deletePostPopupDeleteButton);
        return this;
    }

    public String getDeletePostPopupText()
    {
        TestListener.test.log(Status.INFO,"Get Delete Post Popup Text");
        return getText(deletePostPopupHeader);
    }

    public boolean checkThreeDotsButtonPresence()
    {
        TestListener.test.log(Status.INFO, "check post exist with three dots");
        return isDisplayed(postThreeDots);
    }
}

