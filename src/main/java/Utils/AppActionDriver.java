package Utils;

import Listeners.TestListener;
import com.aventstack.extentreports.Status;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.touch.TapOptions;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.function.Function;

import static io.appium.java_client.touch.LongPressOptions.longPressOptions;
import static io.appium.java_client.touch.offset.PointOption.point;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

public class AppActionDriver extends Waits {

    private AndroidDriver driver;
    private WebDriverWait wait;
    private static Logger log = LogManager.getLogger(AppActionDriver.class);

    public AppActionDriver(AndroidDriver driver) {
        super(driver);
        this.driver = driver;
    }

    protected void click(WebElement element) {
        log.info("Clicking on element : " + element);
        waitForElementToBeClickable(element);
        element.click();
    }

    protected void type(WebElement element, String value) {
        log.info("typing text to the element: " + element + ", and value: " + value);
        waitForElementToBeClickable(element);
        element.sendKeys(value);
    }

    protected boolean verifyText(WebElement element, String value) {
        log.info("typing text to the element: " + element + ", and value: " + value);
        waitForElementVisible(element);
        return element.getText().equals(value);
    }

    protected Dimension getSearchArea() {
        Dimension windowArea = driver.manage().window().getSize();
        log.info("getting dimensions of window");
        return windowArea;
    }

    protected boolean isDisplayed(WebElement element) {

        log.info("Waiting for element to be displayed " + element);
        try {
            waitForElementVisible(element);
            if (element.isDisplayed()) {
                return true;
            }
        } catch (Exception e) {
            log.info("Element is not present, please check locator");
        }
        return false;
    }

    protected void scroll(int yAxis) {
        log.info("Scrolling");
        PointOption start = PointOption.point(getSearchArea().height / 2, getSearchArea().width / 2);
        PointOption end = PointOption.point(getSearchArea().height / 2, yAxis);

        WaitOptions waitOption = WaitOptions.waitOptions(Duration.ofSeconds(2));

        new TouchAction(driver)
                .press(start)
                .waitAction(waitOption)
                .moveTo(end)
                .release().perform();
    }

    public void hideKeyboard() throws InterruptedException {
        log.info("Hiding the keyboard");
        Thread.sleep(5000);
        driver.hideKeyboard();
    }

    protected String getText(WebElement element) {
        log.info("Getting text : " + element);
        waitForElementVisible(element);
        return element.getText();
    }

    protected void selectDropDownValue(WebElement element, List<WebElement> elements, String value) {
        log.info("selecting value from the dropdown: " + value);
        waitForElementToBeClickable(element);
        element.click();
        for (WebElement val : elements) {
            if (val.getText().equals(value)) {
                val.click();
                break;
            }
        }
    }

    protected boolean isSelected(WebElement element) {
        log.info("checking whether element is selected or not");
        boolean select = false;
        try {
            waitForElementVisible(element);
            element.isSelected();
            select = true;
        } catch (Exception e) {
            log.info("Element is not present, please check locator");
        }
        return select;
    }

    protected boolean isEnabled(WebElement element) {
        log.info("checking whether element is enabled or not");
        boolean enable = false;
        try {
            waitForElementVisible(element);
            element.isEnabled();
            enable = true;
        } catch (Exception e) {
            log.info("Element is not present, please check locator");
        }
        return enable;
    }

    protected boolean isDisplayedCustomizedWait(int duration, WebElement element) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(duration));
        log.info("checking whether element is displayed or not");
        boolean display = false;
        try {
            wait.until(visibilityOf(element));
            element.isDisplayed();
            display = true;
        } catch (Exception e) {
            log.info("Element is not present, please check locator");
        }
        return display;
    }

    protected void clearField(WebElement element) {
        log.info("Refreshing the page");
        waitForElementToBeClickable(element);
        element.clear();
    }

    protected int getElementSize(List<WebElement> locator) {
        return locator.size();
    }

    protected void scrollToElementByPosition(final List<WebElement> locator, final int position, int axis) {
        new WebDriverWait(driver, Duration.ofSeconds(30))
                .withMessage("Unable to locate element when scrolling.")
                .until(d -> {
                    scroll(axis);
                    return getElementSize(locator) == position;
                });
    }

    public void navigateBack() {
        driver.pressKey(new KeyEvent().withKey(AndroidKey.BACK));
    }

    protected void slide(int xAxis, WebElement elementContainer) {

        Point location = elementContainer.getLocation();
        Dimension size = elementContainer.getSize();

        int centerX = location.getX() + size.getWidth() / 2;
        int centerY = location.getY() + size.getHeight() / 2;

        PointOption start = PointOption.point(centerX, centerY);
        PointOption end = PointOption.point(centerX - xAxis, centerY);

        WaitOptions waitOption = WaitOptions.waitOptions(Duration.ofSeconds(2));

        new TouchAction(driver)
                .press(start)
                .waitAction(waitOption)
                .moveTo(end)
                .release().perform();
    }

    public AndroidDriver getDriver() {
        return driver;
    }

    protected void tapOnElement(WebElement element, int tapCount) {
        new TouchAction(driver).tap(new TapOptions().withElement(ElementOption.element(element)).withTapsCount(tapCount))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(250))).perform();
    }

    public static WebElement scrollToElementByText(AndroidDriver driver, String text) {
        return driver.findElement(
                AppiumBy.androidUIAutomator(
                        "new UiScrollable(new UiSelector().scrollable(true).instance(0))"
                                + ".scrollIntoView(new UiSelector().textContains(\"" + text + "\").instance(0))"
                )
        );
    }

    public static void swipeImagesToLeft(AndroidDriver driver, WebElement webElement) {

        Dimension dim = driver.manage().window().getSize();

        Point location = webElement.getLocation();
        Dimension size = webElement.getSize();

        int center_x_coord = location.getX() + size.getWidth() / 2;
        int center_y_coord = location.getY() + size.getHeight() / 2;

        int final_x_coord = location.getX();

        TouchAction ts = new TouchAction(driver);

        ts.longPress(longPressOptions().withPosition(point(center_x_coord, center_y_coord)).withDuration(Duration.ofSeconds(2)))
                .moveTo(point(final_x_coord, center_y_coord))
                .release()
                .perform();
    }

    public static void swipeImagesToRight(AndroidDriver driver, WebElement webElement) {

        Dimension dim = driver.manage().window().getSize();

        Point location = webElement.getLocation();
        Dimension size = webElement.getSize();

        int center_x_coord = location.getX() + size.getWidth() / 2;
        int center_y_coord = location.getY() + size.getHeight() / 2;

        int final_x_coord = (int) (dim.getWidth() * .95);

        TouchAction ts = new TouchAction(driver);

        ts.longPress(longPressOptions().withPosition(point(center_x_coord, center_y_coord)).withDuration(Duration.ofSeconds(2)))
                .moveTo(point(final_x_coord, center_y_coord))
                .release()
                .perform();
    }

    public void scrollToBottom()
    {
        Dimension size = driver.manage().window().getSize();

        int startX = size.width / 2;
        int startY = (int) (size.height * 0.8);
        int endY = (int) (size.height * 0.3);

        String previousPageSource = "";

        while (true)
        {
            String currentPageSource = driver.getPageSource();

            if (currentPageSource.equals(previousPageSource))
                break;

            previousPageSource = currentPageSource;

            PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
            Sequence swipe = new Sequence(finger, 1);

            swipe.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY));
            swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
            swipe.addAction(finger.createPointerMove(Duration.ofMillis(800), PointerInput.Origin.viewport(), startX, endY));
            swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

            driver.perform(Collections.singletonList(swipe));
        }
    }

    public void scrollToElement(WebElement element)
    {
        int maxScrolls = 12;

        for (int i = 0; i < maxScrolls; i++)
        {
            try {
                if (isDisplayed(element)) {
                    return;
                }
            }
            catch (StaleElementReferenceException | NoSuchElementException ignored) {}

            scrollToBottom(); // your W3C scroll
        }

        throw new NoSuchElementException("Element not found after scrolling");
    }

    public void uninstallApp(String appPackage) throws IOException, InterruptedException {
        driver.removeApp(appPackage);
    }
}