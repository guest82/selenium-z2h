package builder;

import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Page initiator: Static elements : built according to @By if defined
 *                 Dynamic elements : built dynamicly on each page implementation (Not built here)
 */
public class BasePage {
    public static final int WEBDRIVE_WAIT_TIME_SEC = 120;
    public WebDriverWait wait;
    public WebDriver driver;
    private static Logger logger = LoggerFactory.getLogger(BasePage.class);

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(this.driver, WEBDRIVE_WAIT_TIME_SEC);
        PageFactory.initElements(this.driver, this);
    }

    public WebElement waitForElement(WebElement element, int timeoutInSeconds)
    {
        wait.pollingEvery(250,  TimeUnit.MILLISECONDS);
        wait.withTimeout(timeoutInSeconds, TimeUnit.SECONDS);
        wait.ignoring(NoSuchElementException.class, java.util.NoSuchElementException.class);
        Function<WebDriver, WebElement> function = arg0 -> element;
        wait.until(function);
        return element;
    }

    public void click(WebElement webElement){
        WebElement webElement1 = waitForElement(webElement, 30);
        webElement1.click();
    }

    public void sendKeys(WebElement webElement, String input){
        WebElement webElement1 = waitForElement(webElement, 30);
        webElement1.sendKeys(input);
    }

}
