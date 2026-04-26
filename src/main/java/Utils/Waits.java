package Utils;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

public class Waits {

    private WebDriver driver;
    private WebDriverWait wait;
    private Wait<WebDriver> fluentwait;


     public Waits(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        fluentwait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(30)).pollingEvery(Duration.ofMillis(250))
                .ignoring(NoSuchElementException.class, StaleElementReferenceException.class)
            .ignoring(ElementNotInteractableException.class, ElementClickInterceptedException.class);
    }

    public WebElement waitForElementVisible(WebElement element) {
        return wait.withMessage("element is not visible - check the locator").until(visibilityOf(element));
    }

    public boolean waitForElementInVisible(WebElement element) {
        return wait.withMessage("element is not invisible").until(ExpectedConditions.invisibilityOf(element));
    }

    public WebElement waitForElementToBeClickable(WebElement element){
        return wait.withMessage("element is not clickable - check the locator").until(ExpectedConditions.elementToBeClickable(element));
    }

    public WebElement fluentwaitForElementToBeClickable(WebElement element){
        return fluentwait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public Alert waitForAlertToBePresent(){
    return wait.withMessage("alert is not present").until(ExpectedConditions.alertIsPresent());
    }

    public Boolean waitAndClickStaleElement(WebElement element){
         return wait.withMessage("unable to wait for stale element")
                 .ignoring(StaleElementReferenceException.class)
                 .until((WebDriver d)->{
                     element.click();
                     return true;
                 });
    }

    public Boolean waitAndClickStaleElementAPP(WebElement element){
        return wait.withMessage("unable to wait for stale element")
                .ignoring(StaleElementReferenceException.class)
                .until((WebDriver d)->{
                    element.click();
                    return true;
                });
    }

    public String waitAndGetTextStaleElementAPP(WebElement element){
        return wait.withMessage("unable to wait for stale element")
                .ignoring(StaleElementReferenceException.class)
                .until((WebDriver d)->{
                    return element.getText();

                });
    }

    public void waitForWindowSizeMoreThanOne() {
         wait.withMessage("Window size is one")
                 .until(ExpectedConditions.numberOfWindowsToBe(2));
    }

    public WebElement waitForElementPresent(By element) {
        return wait.withMessage("element is not visible - check the locator").until(presenceOfElementLocated(element));
    }

    public WebElement fluentwaitForElementVisible(WebElement element) {
        return fluentwait.until(visibilityOf(element));
    }

    protected boolean isDisplayedCustomizedWait(int duration, String xpath) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(duration));
        boolean display = false;
        try {

            wait.withMessage("checking whether element is displayed or not").until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
            driver.findElement(By.xpath(xpath)).isDisplayed();
            display = true;
        } catch (Exception e) {

        }
        return display;
    }


 }
