package tests;

import Utils.AppBaseSetup;
import Utils.Assertions;
import Utils.PropertiesFile;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import page.LoginPage;
import page.SignUpPage;

public class LoginPageTest extends AppBaseSetup {
    LoginPage loginPage;
    SignUpPage signUpPage;
    PropertiesFile propertiesFile;
    Assertions assertions;
    WebDriver webDriver;


    @BeforeMethod
    public void LoginPageObject() {
        loginPage = new LoginPage(driver);
        signUpPage = new SignUpPage(driver);
        propertiesFile = new PropertiesFile("data.properties");
        assertions = new Assertions();
    }

    @Test(description = "1 | login to GoalPost app with blank email", priority = 0 ,enabled = true)
    public void loginToAppWithBlankEmail()
    {
        signUpPage.clickOnContinueButton();
        signUpPage.enterEmailForSignup("");
        signUpPage.clickContinueSignUpProcess();
        assertions.assertEquals("Destination is required", signUpPage.blankEmailErrorText());
        assertions.assertAll();
    }

    @Test(description = "2 | login to GoalPost app with invalid email", priority = 1 ,enabled = true)
    public void loginToAppWithInvalidEmail()
    {
        signUpPage.closeAlert();
        signUpPage.enterEmailForSignup("harsh.sn727254@gmail");
        signUpPage.clickContinueSignUpProcess();
        assertions.assertEquals("Must be a valid email or phone number", signUpPage.invalidEmailErrorText());
        assertions.assertAll();
    }

    @Test(description = "3 | login to GoalPost app", priority = 2 ,enabled = true)
    public void loginToApp() throws InterruptedException {
        signUpPage.closeAlert();
        signUpPage.clearEmailField();
        signUpPage.enterEmailForSignup("craig@yopmail.com");
        signUpPage.clickContinueSignUpProcess();
        signUpPage.enterOTP(true);
        signUpPage.hideKeyboard();
        signUpPage.clickContinueSignUpProcess();
        assertions.assertEquals("My Feed", loginPage.getMyFeedText());
        assertions.assertAll();
    }

    @Test(description = "4 | logout from GoalPost app", priority = 3 ,enabled = true)
    public void logoutFromApp()
    {
        loginPage.clickMenu();
        loginPage.clickLogout();
        assertions.assertEquals("Are you sure you want to log out?", loginPage.getLogoutCheckText());
        loginPage.clickCancelLogout();
        assertions.assertTrue(loginPage.checkLogoutButtonInMenu());
        loginPage.clickLogout();
        loginPage.clickConfirmLogoutButton();
        assertions.assertEquals("Welcome to\n" +
                "Goal Post", loginPage.getMainScreenWelcomeText());
        assertions.assertAll();

    }


}
