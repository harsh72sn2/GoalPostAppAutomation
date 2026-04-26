package tests;

import Utils.AppBaseSetup;
import Utils.Assertions;
import Utils.PropertiesFile;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import page.FansPage;
import page.HomePage;
import page.LoginPage;
import page.SignUpPage;

public class FansPageTest extends AppBaseSetup {
    HomePage homePage;

    LoginPage loginPage;
    SignUpPage signUpPage;

    FansPage fansPage;
    PropertiesFile propertiesFile;
    Assertions assertions;

    @BeforeMethod
    public void FansPageObject() {
        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
        signUpPage = new SignUpPage(driver);
        fansPage = new FansPage(driver);
        propertiesFile = new PropertiesFile("data.properties");
        assertions = new Assertions();
    }

    @Test(description = "1 | Verify Landing on Fans Page", priority = 0)
    public void verifyUserLandedOnFansPage() throws InterruptedException {
        signUpPage.clickOnContinueButton();
        signUpPage.enterEmailForSignup("craig@yopmail.com");
        signUpPage.clickContinueSignUpProcess();
        signUpPage.enterOTP(true);
        signUpPage.hideKeyboard();
        signUpPage.clickContinueSignUpProcess();
        fansPage.clickFansButton();
        assertions.assertTrue(fansPage.shareButtonDisplayed());
        assertions.assertAll();
    }

    @Test(description = "2 | Verify username field", priority = 1)
    public void verifyUserNameField()
    {
        assertions.assertTrue(fansPage.getUserName().length()>0);
        assertions.assertAll();
    }

    @Test(description = "3 | Verify User ID Field", priority = 2)
    public void verifyUserIdField()
    {
        assertions.assertTrue(fansPage.getUserId().startsWith("@"));
        assertions.assertAll();
    }

    @Test(description = "4 | Verify Primary Team Field", priority = 3)
    public void verifyPrimaryTeamField()
    {
        assertions.assertEquals(fansPage.getPrimaryTeamField(),"Primary Team:");
        assertions.assertTrue(fansPage.getPrimaryTeamName().length()>0);
        assertions.assertAll();
    }

    @Test(description = "5 | Verify Bio Field", priority = 4)
    public void verifyBio()
    {
        assertions.assertTrue(fansPage.isBioDisplayed());
        assertions.assertTrue(fansPage.getBioDetails().length()>0);
        assertions.assertAll();
    }

    @Test(description = "6 | Verify Followers Field", priority = 5)
    public void verifyFollowersAndFollowing()
    {
        assertions.assertTrue(fansPage.isFollowingDisplayed());
        assertions.assertTrue(fansPage.isFollowersDisplayed());
        assertions.assertTrue(fansPage.getFollowersCountSafe()>=0);
        assertions.assertTrue(fansPage.getFollowingCountSafe()>=0);
        assertions.assertAll();
    }

    @Test(description = "7 | Verify Share Button", priority = 6)
    public void verifyShareButton()
    {
        assertions.assertTrue(fansPage.shareButtonDisplayed());
        homePage.clickShareButton();


    }

    @Test(description = "7 | Create Post", priority = 6, enabled = true)
    public void verifyPostCreation() throws InterruptedException
    {
        homePage.clickCreatePost();
        assertions.assertTrue(homePage.checkPostButton());
        homePage.enterTextPostField("Man U is a good football team");
        homePage.clickPostButton();
        assertions.assertEquals(homePage.getPostText(),"Man U is a good football team");
        assertions.assertAll();
    }

    @Test(description = "8 | Verify Cancel Three Dots Menu", priority = 7)
    public void verifyThreeDotsMenuClosed() throws InterruptedException
    {
        String postText = homePage.getPostText();
        homePage.clickPostThreeDots();
        homePage.clickCancelPostUpdate();
        assertions.assertEquals(homePage.getPostText(),postText);
        assertions.assertAll();
    }

    @Test(description = "9 | Verify Edit Post", priority = 8)
    public void verifyEditPost() throws InterruptedException
    {
        homePage.clickPostThreeDots();
        homePage.clickEditPostButton();
        homePage.enterTextPostField("Man U is a Better Team");
        homePage.clickPostButton();
        assertions.assertEquals(homePage.getPostText(), "Man U is a Better Team");
        assertions.assertAll();
    }


    @Test(description = "10 | Verify Cancel Delete Post", priority = 9)
    public void verifyCancelDeletePostProcess() throws InterruptedException
    {
        homePage.clickPostThreeDots();
        homePage.clickDeletePostButton();
        assertions.assertEquals(homePage.getDeletePostPopupText(),"Are You Sure You Want To Delete Post?");
        homePage.clickCancelDeletePopup();
        assertions.assertTrue(homePage.checkPresenceOfDeletePost());
        assertions.assertAll();
    }

    @Test(description = "11 | Verify Delete post", priority = 10)
    public void verifyDeletePost() throws InterruptedException
    {
        homePage.clickDeletePostButton();
        homePage.clickDeleteButtonDeletePopup();
        assertions.assertFalse(homePage.checkThreeDotsButtonPresence());
        assertions.assertAll();
    }
}
