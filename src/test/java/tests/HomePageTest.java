package tests;
import Utils.AppBaseSetup;
import Utils.Assertions;
import Utils.PropertiesFile;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import page.*;
public class HomePageTest extends AppBaseSetup {

    HomePage homePage;

    LoginPage loginPage;
    SignUpPage signUpPage;
    PropertiesFile propertiesFile;
    Assertions assertions;


    @BeforeMethod
    public void HomePageObject() {
        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
        signUpPage = new SignUpPage(driver);
        propertiesFile = new PropertiesFile("data.properties");
        assertions = new Assertions();
    }


    @Test(description = "1 | verifying search icon on home tab", priority = 0, enabled = true)
    public void verifySearchIcon() throws InterruptedException {
        signUpPage.clickOnContinueButton();
        signUpPage.enterEmailForSignup("craig@yopmail.com");
        signUpPage.clickContinueSignUpProcess();
        signUpPage.enterOTP(true);
        signUpPage.hideKeyboard();
        signUpPage.clickContinueSignUpProcess();
        assertions.assertTrue(homePage.checkSearchField());
        assertions.assertAll();
    }

    @Test(description = "2 | Check My Feed Header", priority = 1, enabled = true)
    public void verifyHomePage() throws InterruptedException
    {
        assertions.assertEquals("My Feed", loginPage.getMyFeedText());
        assertions.assertAll();
    }

    @Test(description = "3 | Create Post No football context", priority = 2, enabled = true)
    public void verifyPostCreationWithUnrelatedFootballContent() throws InterruptedException
    {
        homePage.clickCreatePost();
        assertions.assertTrue(homePage.checkPostButton());
        homePage.enterTextPostField("Tets No");
        homePage.clickPostButton();
        assertions.assertTrue(homePage.getTextErrorNoFootBallContextMessage().contains("Content is not relevant to football"));
        assertions.assertAll();
    }

    @Test(description = "4 | Create Post", priority = 3, enabled = true)
    public void verifyPostCreation() throws InterruptedException
    {
        homePage.clearPostField();
        homePage.enterTextPostField("Man U is a good football team");
        homePage.clickPostButton();
        assertions.assertEquals(homePage.getPostText(),"Man U is a good football team");
        assertions.assertAll();
    }

    @Test(description = "5 | Check Created Post Username",priority = 4, enabled = true)
    public void checkCreatedPostUserName() throws InterruptedException
    {
        assertions.assertEquals(homePage.getCreatedPostUserName(),"@craig");
        assertions.assertAll();
    }

    @Test(description = "6 | Check Created Post Community", priority = 5, enabled = true)
    public void checkCreatedPostCommunity() throws InterruptedException
    {
        assertions.assertEquals(homePage.getCreatedPostCommunity(),"My Page");
        assertions.assertAll();
    }

    @Test(description = "7 | Like post", priority = 6, enabled = true)
    public void verifyPostLiking() throws InterruptedException
    {
        int initialLikeCount = Integer.parseInt(homePage.getLikeCount());
        homePage.clickLike();
        int finalLikeCount = Integer.parseInt(homePage.getLikeCount());
        assertions.assertEquals(initialLikeCount + 1, finalLikeCount);
        assertions.assertAll();
    }

    @Test(description = "8 | Remove Like from post", priority = 7)
    public void removeLikeFromPost() throws InterruptedException
    {
        int initialLikeCount = Integer.parseInt(homePage.getLikeCount());
        homePage.clickLike();
        Thread.sleep(3000);
        int finalLikeCount = Integer.parseInt(homePage.getLikeCount());
        assertions.assertEquals(initialLikeCount, finalLikeCount + 1);
        assertions.assertAll();
    }

    @Test(description = "9 | Verify an empty comment on post", priority = 8)
    public void verifyEmptyComment() throws InterruptedException
    {
        homePage.clickCommentButton();
        Thread.sleep(3000);
        assertions.assertTrue(homePage.checkCommentFieldPresence());
        homePage.inputComment("    ");
        assertions.assertTrue(homePage.isSendCommentEnabled());
        assertions.assertAll();
    }

    @Test(description = "10 | Write a comment on post", priority = 9)
    public void writeCommentOnPost() throws InterruptedException
    {
        assertions.assertTrue(homePage.checkCommentFieldPresence());
        homePage.inputComment("Test Comment");
        homePage.sendComment();
        homePage.navigateBack();
        assertions.assertTrue(Integer.parseInt(homePage.getCommentCount())>0);
        assertions.assertAll();
    }

    @Test(description = "11 | Verify View Count" , priority = 10)
    public void verifyViewCount() throws InterruptedException
    {
        assertions.assertTrue(Integer.parseInt(homePage.getViewCount())>0);
        assertions.assertAll();
    }

    @Test(description = "12 | Verify Report Button", priority = 11)
    public void verifyReportButton() throws InterruptedException
    {
        homePage.clickReportButton();
        assertions.assertTrue(homePage.checkPresenceOfReportButton());
        assertions.assertAll();
    }

    @Test(description = "13 | Verify Share Button", priority = 12)
    public void verifyShareButton() throws InterruptedException
    {
        homePage.clickShareButton();
        assertions.assertEquals(homePage.verifySharePopUp(), "Sharing text");
        assertions.assertAll();
    }

    @Test(description = "14 | Verify Share URL and Platform", priority = 13)
    public void verifySharePostUrlAndPlatformOption() throws InterruptedException
    {
        assertions.assertEquals(homePage.getSharedPostPlatformText(),"Messages");
        assertions.assertTrue(homePage.getSharedPostUrl().contains("goalpostapp"));
        assertions.assertTrue(homePage.getSharedPostUrl().contains("invite"));
        assertions.assertAll();
    }

    @Test(description = "15 | Verify Post Three dots options", priority = 14)
    public void verifyThreeDotsEditAndDeleteOption() throws InterruptedException
    {
        homePage.navigateBack();
        homePage.clickPostThreeDots();
        assertions.assertTrue(homePage.checkPresenceOfEditPost());
        assertions.assertTrue(homePage.checkPresenceOfDeletePost());
        homePage.clickCancelPostUpdate();
        assertions.assertAll();
    }

    @Test(description = "16 | Verify Cancel Three Dots Menu", priority = 15)
    public void verifyThreeDotsMenuClosed() throws InterruptedException
    {
        String postText = homePage.getPostText();
        homePage.clickPostThreeDots();
        homePage.clickCancelPostUpdate();
        assertions.assertEquals(homePage.getPostText(),postText);
        assertions.assertAll();
    }

    @Test(description = "17 | Verify Edit Post with No football context post", priority = 16)
    public void verifyEditPostNoFootBallContext() throws InterruptedException
    {
        homePage.clickPostThreeDots();
        homePage.clickEditPostButton();
        homePage.enterTextPostField("Tets No");
        homePage.clickPostButton();
        assertions.assertEquals(homePage.getTextErrorNoFootBallContextMessage(),"Content is not relevant to football. The content 'Tets No' does not contain any recognizable football terms or context.");
        assertions.assertAll();
    }

    @Test(description = "18 | Verify Edit Post", priority = 17)
    public void verifyEditPost() throws InterruptedException
    {
        homePage.clearPostField();
        homePage.enterTextPostField("Man U is a Better Team");
        homePage.clickPostButton();
        assertions.assertEquals(homePage.getPostText(), "Man U is a Better Team");
        assertions.assertAll();
    }


    @Test(description = "19 | Verify Cancel Delete Post", priority = 18)
    public void verifyCancelDeletePostProcess() throws InterruptedException
    {
        homePage.clickPostThreeDots();
        homePage.clickDeletePostButton();
        assertions.assertEquals(homePage.getDeletePostPopupText(),"Are You Sure You Want To Delete Post?");
        homePage.clickCancelDeletePopup();
        assertions.assertTrue(homePage.checkPresenceOfDeletePost());
        assertions.assertAll();
    }

    @Test(description = "20 | Verify Delete post", priority = 19)
    public void verifyDeletePost() throws InterruptedException
    {
        homePage.clickDeletePostButton();
        homePage.clickDeleteButtonDeletePopup();
        assertions.assertFalse(homePage.checkThreeDotsButtonPresence());
        assertions.assertAll();
    }



}
