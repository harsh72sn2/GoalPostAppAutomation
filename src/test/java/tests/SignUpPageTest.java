package tests;

import Utils.AppBaseSetup;
import Utils.Assertions;
import Utils.PropertiesFile;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import page.HomePage;
import page.SignUpPage;

public class SignUpPageTest extends AppBaseSetup {

    SignUpPage signUpPage;
    PropertiesFile propertiesFile;
    Assertions assertions;
    WebDriver webDriver;


    @BeforeMethod
    public void SignUpPageObject() {
        signUpPage = new SignUpPage(driver);
        propertiesFile = new PropertiesFile("data.properties");
        assertions = new Assertions();
    }

    @Test(description = "1 | sign up with blank email on GoalPost app", priority = 0 ,enabled = true)
    public void signUpBlankEmail()
    {
        signUpPage.clickOnContinueButton();
        signUpPage.enterEmailForSignup("");
        signUpPage.clickContinueSignUpProcess();
        assertions.assertEquals("Destination is required", signUpPage.blankEmailErrorText());
        assertions.assertAll();
    }

    @Test(description = "2 | sign up with invalid email on GoalPost app", priority = 1 ,enabled = true)
    public void signUpInvalidEmail()
    {
        signUpPage.closeAlert();
        signUpPage.enterEmailForSignup("hippo@mail");
        signUpPage.clickContinueSignUpProcess();
        assertions.assertEquals("Must be a valid email or phone number", signUpPage.invalidEmailErrorText());
        assertions.assertAll();
    }


    @Test(description = "3 | sign up without OTP on GoalPost app", priority = 2 ,enabled = true)
    public void checkSignUpWithBlankOtp() throws InterruptedException {
        signUpPage.closeAlert();
        signUpPage.clearEmailField();
        signUpPage.enterEmailForSignup("hippo@yopmail.com");
        signUpPage.clickContinueSignUpProcess();
        signUpPage.hideKeyboard();
        signUpPage.clickOnContinueButton();
        assertions.assertEquals(signUpPage.invalidOTPErrorText(),"OTP must be exactly 6 digits");
        assertions.assertAll();
    }


    @Test(description = "4 | sign up with incomplete OTP on GoalPost app", priority = 3 ,enabled = true)
    public void checkSignUpWithIncompleteOtp() throws InterruptedException {
        signUpPage.closeAlert();
        signUpPage.enterOTP(false);
        signUpPage.hideKeyboard();
        signUpPage.clickOnContinueButton();
        assertions.assertEquals(signUpPage.invalidOTPErrorText(),"OTP must be exactly 6 digits");
        assertions.assertAll();
    }

    @Test(description = "5 | sign up on GoalPost app", priority = 4 ,enabled = true)
    public void checkSignUp()
    {
//        signUpPage.quitApp();
    }

}
