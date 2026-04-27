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

import java.time.Year;
import java.util.List;

public class SignUpPage extends AppActionDriver {
    AndroidDriver driver;
    Logger logger= LogManager.getLogger(SignUpPage.class);
    PropertiesFile propertiesFile;
    public SignUpPage(AndroidDriver driver) {
        super(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        propertiesFile= new PropertiesFile("data.properties");
        this.driver = driver;
    }

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='Continue']")
    private WebElement continueButtonStartPage;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Email/Phone']/following-sibling::android.widget.EditText")
    private WebElement emailField;

    @AndroidFindBy(xpath = "//android.widget.EditText[contains(@resource-id,'otp_input')]")
    List<WebElement> otpField;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Continue']")
    private WebElement continueButton;

    @AndroidFindBy(xpath = "//android.widget.EditText[@text='First Name']")
    private WebElement firstNameField;

    @AndroidFindBy(xpath = "//android.widget.EditText[@text='Last Name']")
    private WebElement lastNameField;

    @AndroidFindBy(xpath = "//android.widget.EditText[@text='Username']")
    private WebElement userNameField;

    @AndroidFindBy(xpath = "//android.widget.EditText[@text='Bio']")
    private WebElement bioField;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Destination is required']")
    private WebElement destinationError;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Must be a valid email or phone number']")
    private WebElement invalidEmailError;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='Close']")
    private WebElement closeAlert;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='OTP must be exactly 6 digits']")
    private WebElement wrongOTPError;

    @AndroidFindBy(xpath = "(//android.view.ViewGroup[@content-desc='Edit'])[1]/android.widget.ImageView")
    private WebElement uploadProfilePicture;

    @AndroidFindBy(xpath = "(//android.view.ViewGroup[@content-desc='Edit'])[2]/android.widget.ImageView")
    private WebElement uploadCoverPicture;

    @AndroidFindBy(xpath = "//android.widget.TextView[contains(@text,'Open Camera')]")
    private WebElement openCameraButton;

    @AndroidFindBy(xpath = "//android.widget.ImageButton[@content-desc='Take photo']")
    private WebElement clickPhoto;

    @AndroidFindBy(xpath = "//android.widget.ImageButton[@content-desc='Done']")
    private WebElement uploadPhoto;

    @AndroidFindBy(xpath = "//android.widget.Button[@content-desc='Crop']")
    private WebElement completeUpload;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='Location']")
    private WebElement locationField;

    @AndroidFindBy(xpath = "//android.widget.EditText[@text='Type a place']")
    private WebElement searchLocation;

    @AndroidFindBy(xpath = "(//android.widget.TextView[@text='London'])[1]")
    private WebElement selectLondon;

    @AndroidFindBy(xpath = "//android.widget.EditText[@text='Phone Number']")
    private WebElement phoneNumberField;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='mm/dd/yyyy']")
    private WebElement dobField;

    @AndroidFindBy(xpath = "//android.widget.LinearLayout[@resource-id='com.football.goalpost:id/pickerWrapper']")
    private WebElement dobPickerWrapper;

    @AndroidFindBy(xpath = "//android.widget.LinearLayout[@resource-id='com.football.goalpost:id/pickerWrapper']//android.widget.NumberPicker")
    private List<WebElement> dobPickerColumns;

    @AndroidFindBy(xpath = "//android.widget.Button[@resource-id='android:id/button1']")
    private WebElement confirmDOBButton;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"Men's Club\"]")
    private WebElement menClubSelection;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"Women's Club\"]")
    private WebElement womenClubSelection;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"Men's National Team\"]")
    private WebElement menNationalSelection;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"Women's National Team\"]")
    private WebElement womenNationalSelection;

    @AndroidFindBy(xpath = "//android.widget.EditText[@text='Search']")
    private WebElement searchTeam;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='Manchester United']")
    private WebElement manchesterUnitedSelection;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='Manchester City']")
    private WebElement manchesterCitySelection;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='Arsenal W']")
    private WebElement arsenalWSelection;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='Aston Villa W']")
    private WebElement astonVillaWSelection;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='Australia U17']")
    private WebElement australiaSelection;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='England']")
    private WebElement englandSelection;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='England W']")
    private WebElement englandWSelection;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='Japan U20 W']")
    private WebElement japanWSelection;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Complete Profile']")
    private WebElement completeProfileButton;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='CHOOSE YOUR TEAMS']")
    private WebElement chooseTeams;





    public SignUpPage clickOnContinueButton() {
        TestListener.test.log(Status.INFO, "click on continue button app start page");
        click(continueButtonStartPage);
        return this;
    }

    public SignUpPage enterEmailForSignup(String email) {
        TestListener.test.log(Status.INFO, "entering email for sign up");
        type(emailField, email);
        return this;
    }

    public SignUpPage clickContinueSignUpProcess() {
        TestListener.test.log(Status.INFO, "clicking continue in sign up process");
        click(continueButton);
        return this;
    }

    public SignUpPage enterFirstName(String firstName)
    {
        TestListener.test.log(Status.INFO, "Entering first Name for signup");
        type(firstNameField, firstName);
        return this;
    }

    public SignUpPage enterLastName(String lastName)
    {
        TestListener.test.log(Status.INFO, "Entering last Name for signup");
        type(lastNameField, lastName);
        return this;
    }

    public SignUpPage enterUsername(String userName)
    {
        TestListener.test.log(Status.INFO, "Entering user Name for signup");
        type(userNameField, userName);
        return this;
    }

    public SignUpPage enterBio(String bio)
    {
        TestListener.test.log(Status.INFO, "Entering bio for signup");
        type(bioField, bio);
        return this;
    }

    public SignUpPage clickUploadProfilePicture()
    {
        TestListener.test.log(Status.INFO, "Clicking upload profile picture");
        click(uploadProfilePicture);
        return this;
    }

    public SignUpPage clickUploadCoverPicture()
    {
        TestListener.test.log(Status.INFO, "Clicking upload cover picture");
        click(uploadCoverPicture);
        return this;
    }

    public SignUpPage clickOpenCameraButton()
    {
        TestListener.test.log(Status.INFO, "Clicking open camera button");
        click(openCameraButton);
        return this;
    }

    public SignUpPage clickPhoto()
    {
        TestListener.test.log(Status.INFO, "Clicking photo");
        click(clickPhoto);
        return this;
    }

    public SignUpPage clickUploadPhoto()
    {
        TestListener.test.log(Status.INFO, "Clicking upload photo");
        click(uploadPhoto);
        return this;
    }

    public SignUpPage clickCompleteUpload()
    {
        TestListener.test.log(Status.INFO, "Clicking complete upload");
        click(completeUpload);
        return this;
    }

    public SignUpPage clickLocationField()
    {
        TestListener.test.log(Status.INFO, "Clicking location field");
        click(locationField);
        return this;
    }

    public SignUpPage enterLocationSearchData(String location)
    {
        TestListener.test.log(Status.INFO,"Searching Location");
        type(searchLocation,"London");
        return this;
    }

    public SignUpPage selectCity()
    {
        TestListener.test.log(Status.INFO,"Select location city");
        click(selectLondon);
        return this;
    }

    public SignUpPage enterPhoneNumber(String phoneNumber)
    {
        TestListener.test.log(Status.INFO, "Entering phone number for signup");
        type(phoneNumberField, phoneNumber);
        return this;
    }

    public SignUpPage clickDobField()
    {
        TestListener.test.log(Status.INFO, "Clicking dob field");
        click(dobField);
        return this;
    }

    public SignUpPage scrollDobYearTo2002() throws InterruptedException {
        TestListener.test.log(Status.INFO, "Scrolling dob year to 2002");
        waitForElementVisible(dobPickerWrapper);

        WebElement yearColumn = dobPickerWrapper;
        double xPercent = 0.83;
        if (dobPickerColumns.size() > 0) {
            yearColumn = dobPickerColumns.get(dobPickerColumns.size() - 1);
            xPercent = 0.50;
        }

        int yearsToScroll = Math.max(0, Year.now().getValue() - 2002);
        for (int i = 0; i < yearsToScroll; i++) {
            swipeVerticalOnElement(yearColumn, xPercent, 0.42, 0.58);
            Thread.sleep(120);
        }

        Thread.sleep(300);
        return this;
    }

    public SignUpPage selectMenClub()
    {
        TestListener.test.log(Status.INFO, "Selecting men's club");
        click(menClubSelection);
        return this;
    }

    public SignUpPage selectWomenClub()
    {
        TestListener.test.log(Status.INFO, "Selecting women's club");
        click(womenClubSelection);
        return this;
    }

    public SignUpPage selectMenNationalTeam()
    {
        TestListener.test.log(Status.INFO, "Selecting men's national team");
        click(menNationalSelection);
        return this;
    }

    public SignUpPage selectWomenNationalTeam()
    {
        TestListener.test.log(Status.INFO, "Selecting women's national team");
        click(womenNationalSelection);
        return this;
    }

    public SignUpPage searchTeam(String teamName)
    {
        TestListener.test.log(Status.INFO, "Searching team");
        type(searchTeam, teamName);
        return this;
    }

    public SignUpPage selectManchesterUnited()
    {
        TestListener.test.log(Status.INFO, "Selecting Manchester United");
        click(manchesterUnitedSelection);
        return this;
    }

    public SignUpPage selectManchesterCity()
    {
        TestListener.test.log(Status.INFO, "Selecting Manchester City");
        click(manchesterCitySelection);
        return this;
    }

    public SignUpPage selectArsenalW()
    {
        TestListener.test.log(Status.INFO, "Selecting Arsenal W");
        click(arsenalWSelection);
        return this;
    }

    public SignUpPage selectAstonVillaW()
    {
        TestListener.test.log(Status.INFO, "Selecting Aston Villa W");
        click(astonVillaWSelection);
        return this;
    }

    public SignUpPage selectAustraliaU17()
    {
        TestListener.test.log(Status.INFO, "Selecting Australia U17");
        click(australiaSelection);
        return this;
    }

    public SignUpPage selectEngland()
    {
        TestListener.test.log(Status.INFO, "Selecting England");
        click(englandSelection);
        return this;
    }

    public SignUpPage selectEnglandW()
    {
        TestListener.test.log(Status.INFO, "Selecting England W");
        click(englandWSelection);
        return this;
    }

    public SignUpPage selectJapanU20W()
    {
        TestListener.test.log(Status.INFO, "Selecting Japan U20 W");
        click(japanWSelection);
        return this;
    }

    public String blankEmailErrorText()
    {
        TestListener.test.log(Status.INFO, "Getting error for destination blank");
        return getText(destinationError);
    }

    public String invalidEmailErrorText()
    {
        TestListener.test.log(Status.INFO, "Getting error for email invalid");
        return getText(invalidEmailError);
    }

    public SignUpPage closeAlert()
    {
        TestListener.test.log(Status.INFO,"closing alert");
        click(closeAlert);
        return this;
    }

    public SignUpPage clearEmailField()
    {
        TestListener.test.log(Status.INFO, "Clear Email Field");
        clearField(emailField);
        return this;
    }

    public SignUpPage enterOTP(boolean complete)
    {
        TestListener.test.log(Status.INFO,"Entering otp");
        if(complete) {
            for (int i = 0; i < otpField.size(); i++) {
                type(otpField.get(i), "0");
            }
        }
        else {
            for (int i = 0; i < otpField.size()-2; i++) {
                type(otpField.get(i), "0");
            }
        }
        return this;
    }

    public SignUpPage clickDOBConfirm()
    {
        TestListener.test.log(Status.INFO, "Confirm DOB");
        click(confirmDOBButton);
        return this;
    }

    public String invalidOTPErrorText()
    {
        TestListener.test.log(Status.INFO, "Getting error for otp invalid");
        return getText(wrongOTPError);
    }

    public SignUpPage clickCompleteProfile()
    {
        TestListener.test.log(Status.INFO,"Click Complete Profile");
        click(completeProfileButton);
        return this;
    }

    public SignUpPage clickChooseTeams()
    {
        TestListener.test.log(Status.INFO,"Click choose teams");
        click(chooseTeams);
        return this;
    }

}
