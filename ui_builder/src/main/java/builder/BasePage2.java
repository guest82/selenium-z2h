package builder;

import static builder.UiConsts.BASE_URL;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Page initiator: Static elements : built according to @By if defined
 *                 Dynamic elements : built dynamicly on each page implementation (Not built here)
 */
public class BasePage2 {
    public static final int WEBDRIVE_WAIT_TIME_SEC = 120;
    public WebDriverWait wait;
    public WebDriver driver;

    public BasePage2(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(this.driver, WEBDRIVE_WAIT_TIME_SEC);
    }

    public void goToUrl(String uri){
        driver.get(BASE_URL + uri);
    }

    //Wait
    public void waitVisibility(By by){
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    //Click Method
    public void click(By elementLocation) {
        waitVisibility(elementLocation);
        driver.findElement(elementLocation).click();
    }

    //Write Text
    public void writeText(By elementLocation, String text) {
        waitVisibility(elementLocation);
        driver.findElement(elementLocation).clear();
        driver.findElement(elementLocation).sendKeys(text);
    }

    //Read Text
    public String readText(By elementLocation) {
        waitVisibility(elementLocation);
        return driver.findElement(elementLocation).getText();
    }

}
