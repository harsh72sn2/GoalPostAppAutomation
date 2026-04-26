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

    public String invalidOTPErrorText()
    {
        TestListener.test.log(Status.INFO, "Getting error for otp invalid");
        return getText(wrongOTPError);
    }


}
