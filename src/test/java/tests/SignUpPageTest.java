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

    @Test(description = "5 | sign up without first name on GoalPost app", priority = 4 ,enabled = true)
    public void checkSignUpNoFirstName() throws InterruptedException {
        signUpPage.closeAlert();
        signUpPage.navigateBack();
        signUpPage.clearEmailField();
        signUpPage.enterEmailForSignup("kk@yopmail.com");
        signUpPage.clickContinueSignUpProcess();
        signUpPage.enterOTP(true);
        signUpPage.hideKeyboard();
        signUpPage.clickContinueSignUpProcess();
        Thread.sleep(3000);
        signUpPage.clickOnContinueButton();
        assertions.assertEquals(signUpPage.firstNameValidationErrorText(),"First name is required");
        assertions.assertAll();
    }

    @Test(description = "6 | sign up without last name on GoalPost app", priority = 5 ,enabled = true)
    public void checkSignUpNoLastName() throws InterruptedException {
        signUpPage.enterFirstName("Kay");
        signUpPage.clickOnContinueButton();
        assertions.assertEquals(signUpPage.lastNameValidationErrorText(),"Last name is required");
        assertions.assertAll();
    }

    @Test(description = "7 | sign up without username on GoalPost app", priority = 6 ,enabled = true)
    public void checkSignUpNoUserName() throws InterruptedException {
        signUpPage.enterLastName("Kya");
        signUpPage.clickOnContinueButton();
        signUpPage.clickOnContinueButton();
        assertions.assertEquals(signUpPage.userNameValidationErrorText(),"Username is required");
        assertions.assertAll();
    }

    @Test(description = "8 | sign up without bio on GoalPost app", priority = 7 ,enabled = true)
    public void checkSignUpNoBio() throws InterruptedException {
        signUpPage.enterUsername("kaykay");
        signUpPage.clickOnContinueButton();
        assertions.assertEquals(signUpPage.bioValidationErrorText(),"Bio is required");
        assertions.assertAll();
    }

    @Test(description = "9 | sign up without profile picture on GoalPost app", priority = 8 ,enabled = true)
    public void checkSignUpNoProfilePicture() throws InterruptedException {
        signUpPage.enterBio("I am strong");
        signUpPage.clickOnContinueButton();
        signUpPage.clickOnContinueButton();
        assertions.assertEquals(signUpPage.profilePictureValidationErrorText(),"Profile picture is required");
        assertions.assertAll();
    }

    @Test(description = "10 | sign up without cover picture on GoalPost app", priority = 9 ,enabled = true)
    public void checkSignUpNoCoverPicture() throws InterruptedException {
        signUpPage.clickUploadProfilePicture();
        signUpPage.clickOpenCameraButton();
        signUpPage.clickAllowCameraButton();
        signUpPage.clickPhoto();
        signUpPage.clickUploadPhoto();
        signUpPage.clickCompleteUpload();
        signUpPage.clickOnContinueButton();
        assertions.assertEquals(signUpPage.coverPictureValidationErrorText(),"Cover image is required");
        assertions.assertAll();
    }

    @Test(description = "11 | sign up without phone number on GoalPost app", priority = 10 ,enabled = true)
    public void checkSignUpNoPhoneNumber() throws InterruptedException {
        signUpPage.clickUploadCoverPicture();
        signUpPage.clickOpenCameraButton();
        signUpPage.clickAllowCameraButton();
        signUpPage.clickPhoto();
        signUpPage.clickUploadPhoto();
        signUpPage.clickCompleteUpload();
        signUpPage.clickOnContinueButton();
        signUpPage.clickCompleteProfile();
        assertions.assertEquals(signUpPage.phoneNumberValidationErrorText(),"Phone number is required");
        assertions.assertAll();
    }

    @Test(description = "12 | sign up without DOB on GoalPost app", priority = 11 ,enabled = true)
    public void checkSignUpNoDOB() throws InterruptedException {
        signUpPage.clickLocationField();
        signUpPage.enterLocationSearchData("London");
        signUpPage.selectCity();
        signUpPage.enterPhoneNumber("2342345456");
        signUpPage.clickCompleteProfile();
        assertions.assertEquals(signUpPage.dobValidationErrorText(),"Date of birth is required");
        assertions.assertAll();
    }

    @Test(description = "13 | sign up with DOB less than 13 on GoalPost app", priority = 12 ,enabled = true)
    public void checkSignUpRestrictedDOB() throws InterruptedException {
        signUpPage.clickDobField();
        signUpPage.clickDOBConfirm();
        signUpPage.clickCompleteProfile();
        assertions.assertEquals(signUpPage.ageCheckValidationErrorText(),"You must be at least 13 years old and date of birth must be valid");
        assertions.assertAll();
    }

    @Test(description = "14 | Complete SignUp", priority = 13)
    public void completeSignUp() throws InterruptedException {
        signUpPage.closeAlert();
        signUpPage.clickDobField();
        signUpPage.selectDobYear2("2002");
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
