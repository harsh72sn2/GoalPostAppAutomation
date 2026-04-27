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
    HomePage homePage;
    PropertiesFile propertiesFile;
    Assertions assertions;
    WebDriver webDriver;


    @BeforeMethod
    public void SignUpPageObject() {
        signUpPage = new SignUpPage(driver);
        homePage = new HomePage(driver);
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
    public void checkSignUp() throws InterruptedException {
        signUpPage.closeAlert();
        signUpPage.navigateBack();
        signUpPage.clearEmailField();
        signUpPage.enterEmailForSignup("kk@yopmail.com");
        signUpPage.clickContinueSignUpProcess();
        signUpPage.enterOTP(true);
        signUpPage.hideKeyboard();
        signUpPage.clickContinueSignUpProcess();
        signUpPage.enterFirstName("Kay");
        signUpPage.enterLastName("Kya");
        signUpPage.clickOnContinueButton();
        signUpPage.enterUsername("kaykay");
        signUpPage.enterBio("I am strong");
        signUpPage.clickOnContinueButton();
        signUpPage.clickUploadProfilePicture();
        signUpPage.clickOpenCameraButton();
        signUpPage.clickPhoto();
        signUpPage.clickUploadPhoto();
        signUpPage.clickCompleteUpload();
        signUpPage.clickUploadCoverPicture();
        signUpPage.clickOpenCameraButton();
        signUpPage.clickPhoto();
        signUpPage.clickUploadPhoto();
        signUpPage.clickCompleteUpload();
        signUpPage.clickOnContinueButton();
        signUpPage.clickLocationField();
        signUpPage.enterLocationSearchData("London");
        signUpPage.selectCity();
        signUpPage.enterPhoneNumber("1234567891");
        signUpPage.clickDobField();
        signUpPage.scrollDobYearTo2002();
        signUpPage.clickDOBConfirm();
        signUpPage.clickCompleteProfile();
        signUpPage.clickChooseTeams();
        signUpPage.selectMenClub();
        signUpPage.searchTeam("Manchester");
        signUpPage.selectManchesterUnited();
        signUpPage.clickOnContinueButton();
        signUpPage.selectManchesterCity();
        signUpPage.clickOnContinueButton();
        signUpPage.selectArsenalW();
        signUpPage.clickOnContinueButton();
        signUpPage.selectAstonVillaW();
        signUpPage.clickOnContinueButton();
        signUpPage.selectAustraliaU17();
        signUpPage.clickOnContinueButton();
        signUpPage.selectEngland();
        signUpPage.clickOnContinueButton();
        signUpPage.selectEnglandW();
        signUpPage.clickOnContinueButton();
        signUpPage.selectJapanU20W();
        signUpPage.clickOnContinueButton();
        signUpPage.clickOnContinueButton();
        assertions.assertTrue(homePage.checkSearchField());
        assertions.assertAll();
    }

}
