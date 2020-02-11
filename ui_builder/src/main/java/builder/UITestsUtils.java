/*
 *  Copyright (c) 2002-2016 EMC Corporation All Rights Reserved
 */
package builder;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class UITestsUtils {

    public static final int SLEEP_WAIT_FOR_MS = 100;
    public static final int SLEEP_AFTER_WAIT_MS = 1000;
    public static final int TIMEOUT_LIMIT_MS = 30000;
    public static final int MAX_INTERVALS = 200;
    public static final int WEBDRIVER_WAIT_TIMEOUT_SEC = 40;
    public static final String POINTER_EVENTS = "pointer-events";
    public static final String NONE_STR = "none";
    private static final int VERY_LONG_WAIT_SEC = 5000;
    private static final int LONG_WAIT_SEC = 420;

    private static Logger logger = LoggerFactory.getLogger(UITestsUtils.class);

    public static WebElement getElementIfExist(WebDriver driver, String css) {
        int counter = 0;
        while (counter <= MAX_INTERVALS && driver.findElements(By.cssSelector(css)).size() <= 0) {
            sleep(SLEEP_WAIT_FOR_MS);
            counter++;
        }
        if (counter <= MAX_INTERVALS && driver.findElements(By.cssSelector(css)).size() > 0) {
            return driver.findElement(By.cssSelector(css));
        } else {
            throw new IllegalStateException("can't find element. css: " + css);
        }
    }

    public static boolean checkIfElementIfExist(WebDriver driver, String css) {
        int counter = 0;
        while (counter <= MAX_INTERVALS && driver.findElements(By.cssSelector(css)).size() <= 0) {
            sleep(SLEEP_WAIT_FOR_MS);
            counter++;
        }
        if (counter <= MAX_INTERVALS && driver.findElements(By.cssSelector(css)).size() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public static void clickOnElementWhenClickable(WebDriver driver, String css) {
        int counter = 0;
        WebElement element;

        while (counter <= MAX_INTERVALS && driver.findElements(By.cssSelector(css)).size() <= 0) {
            sleep(SLEEP_WAIT_FOR_MS);
            counter++;
        }
        if (counter >= MAX_INTERVALS) {
            throw new IllegalStateException("can't find element. css: " + css);
        } else {
            element = driver.findElement(By.cssSelector(css));
            counter = 0;
            while (counter <= MAX_INTERVALS && element.getCssValue(POINTER_EVENTS).contains(NONE_STR)) {
                sleep(SLEEP_WAIT_FOR_MS);
                counter++;
            }
            if (counter >= MAX_INTERVALS)
                throw new IllegalStateException("The element is not displayed or enabled when trying to click. css: " + css);
            else {
                element.click();
            }

        }
    }

    public static void clickOnClassWhenClickable(WebDriver driver, String className) {
        int counter = 0;
        WebElement element;

        while (counter <= MAX_INTERVALS && driver.findElements(By.className(className)).size() <= 0) {
            sleep(SLEEP_WAIT_FOR_MS);
            counter++;
        }
        if (counter >= MAX_INTERVALS) {
            throw new IllegalStateException("can't find element. class: " + className);
        } else {
            element = driver.findElement(By.className(className));
            counter = 0;
            while (counter <= MAX_INTERVALS && element.getCssValue(POINTER_EVENTS).contains(NONE_STR)) {
                sleep(SLEEP_WAIT_FOR_MS);
                counter++;
            }
            if (counter >= MAX_INTERVALS)
                throw new IllegalStateException("The element is not displayed or enabled when trying to click. class: " + className);
            else {
                element.click();
            }

        }
    }

    public static void submitElementWhenClickable(WebElement element) {
        sleep(SLEEP_AFTER_WAIT_MS);
        int counter = 0;
        while (counter <= MAX_INTERVALS && element.getCssValue(POINTER_EVENTS).contains(NONE_STR)) {
            sleep(SLEEP_WAIT_FOR_MS);
            counter++;
        }
        if (counter >= MAX_INTERVALS)
            throw new IllegalStateException("The element is not displayed or enabled when trying to click.");
        else {
            element.submit();
        }
    }

    public static void clickOnElementWhenClickable(WebElement element) {
        sleep(SLEEP_AFTER_WAIT_MS);
        int counter = 0;
        while (counter <= MAX_INTERVALS && element.getCssValue(POINTER_EVENTS).contains(NONE_STR)) {
            sleep(SLEEP_WAIT_FOR_MS);
            counter++;
        }
        if (counter >= MAX_INTERVALS)
            throw new IllegalStateException("The element is not displayed or enabled when trying to click.");
        else {
            element.click();
        }
    }

    public static void waitForElementClose(WebDriver driver, String css) {
        int counter = 0;
        WebElement element;

        while (counter <= MAX_INTERVALS && driver.findElements(By.cssSelector(css)).size() > 0) {
            sleep(SLEEP_WAIT_FOR_MS);
            counter++;
        }
        if (counter >= MAX_INTERVALS && driver.findElements(By.cssSelector(css)).size() > 0) {
            throw new IllegalStateException("Element is not close. css: " + css);
        }
    }

    public static String getCssValueWhenDisplayed(WebDriver driver, String css, String cssValue) {
        int counter = 0;
        while (counter <= MAX_INTERVALS && driver.findElements(By.cssSelector(css)).size() <= 0) {
            sleep(SLEEP_WAIT_FOR_MS);
            counter++;
        }
        if (counter >= MAX_INTERVALS)
            throw new IllegalStateException("The element is not displayed. css: " + css);
        else if (driver.findElement(By.cssSelector(css)).isDisplayed())
            return driver.findElement(By.cssSelector(css)).getCssValue(cssValue);
        else
            throw new IllegalStateException("can't get text from element. css: " + css);
    }

    public static String getTextWhenDisplayed(WebDriver driver, String css) {
        int counter = 0;
        while (counter <= MAX_INTERVALS && driver.findElements(By.cssSelector(css)).size() <= 0) {
            sleep(SLEEP_WAIT_FOR_MS);
            counter++;
        }
        if (counter >= MAX_INTERVALS)
            throw new IllegalStateException("The element is not displayed. css: " + css + "\n counter: " + counter);

        else
            counter = 0;
        while (counter <= MAX_INTERVALS && driver.findElements(By.cssSelector(css)).size() <= 0
                && !driver.findElement(By.cssSelector(css)).isDisplayed()) {
            sleep(SLEEP_WAIT_FOR_MS);
            counter++;
        }
        if (counter >= MAX_INTERVALS)
            throw new IllegalStateException("can't get text from element. css: " + css + "\n counter: " + counter);
        else
            return driver.findElement(By.cssSelector(css)).getText();
    }

    public static void sleep(int millisecond) {
        try {
            Thread.sleep(millisecond);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void waitForElementsCount(WebDriver webDriver, String elementCssPath, int elementsCount) {
        WebDriverWait wait = new WebDriverWait(webDriver, WEBDRIVER_WAIT_TIMEOUT_SEC);
        wait.until(numberOfElementsToBe(By.cssSelector(elementCssPath), elementsCount));
        sleep(SLEEP_AFTER_WAIT_MS);
    }

    public static void waitForElement(WebDriver webDriver, String elementCssPath) {
        WebDriverWait wait = new WebDriverWait(webDriver, WEBDRIVER_WAIT_TIMEOUT_SEC);
        wait.until(presenceOfElementLocated(By.cssSelector(elementCssPath)));
        sleep(SLEEP_AFTER_WAIT_MS);
    }

    public static void waitForElement(WebDriver webDriver, String elementCssPath, int WAIT_TIMEOUT_SEC) {
        WebDriverWait wait = new WebDriverWait(webDriver, WAIT_TIMEOUT_SEC);
        wait.until(presenceOfElementLocated(By.cssSelector(elementCssPath)));
        sleep(SLEEP_AFTER_WAIT_MS);
    }

    public static void waitForClass(WebDriver webDriver, String elementName, int maxWaitTimeSec) {
        WebDriverWait wait = new WebDriverWait(webDriver, maxWaitTimeSec);
        wait.until(presenceOfElementLocated(By.className(elementName)));
        sleep(SLEEP_AFTER_WAIT_MS);
    }

    public static void waitForClass(WebDriver webDriver, String elementName) {
        WebDriverWait wait = new WebDriverWait(webDriver, WEBDRIVER_WAIT_TIMEOUT_SEC);
        wait.until(presenceOfElementLocated(By.className(elementName)));
        sleep(SLEEP_AFTER_WAIT_MS);
    }

    public static void waitForElement(WebDriver webDriver, String elementName, String condition) {
        long startTime = System.currentTimeMillis();
        final By byCss = By.cssSelector(elementName);
        while ((webDriver.findElements(byCss).size() == 0 || !webDriver.findElement(byCss).getText().contains(condition))
                && ((System.currentTimeMillis() - startTime) < TIMEOUT_LIMIT_MS)) {
            sleep(SLEEP_AFTER_WAIT_MS);
        }
        if (!webDriver.findElement(byCss).getText().contains(condition))
            throw new TimeoutException("Element with " + elementName + " was not found after: " + TIMEOUT_LIMIT_MS);
    }

    public static void waitForElementAttribute(WebDriver webDriver, String elementName, String condition) {
        long startTime = System.currentTimeMillis();
        while (!webDriver.findElement(By.cssSelector(elementName)).getAttribute("value").contains(condition)
                && ((System.currentTimeMillis() - startTime) < TIMEOUT_LIMIT_MS)) {
            sleep(SLEEP_AFTER_WAIT_MS);
        }
        if (!webDriver.findElement(By.cssSelector(elementName)).getAttribute("value").contains(condition))
            throw new TimeoutException("Element with " + elementName + " was not found after: " + TIMEOUT_LIMIT_MS);
    }

    public static void waitForClosedElement(WebDriver webDriver, String elementName) {
        WebDriverWait wait = new WebDriverWait(webDriver, WEBDRIVER_WAIT_TIMEOUT_SEC);
        wait.until(not(presenceOfAllElementsLocatedBy(By.cssSelector(elementName))));
        sleep(SLEEP_AFTER_WAIT_MS);
    }

    public static void waitForClosedElement(WebDriver webDriver, String elementName, int WAIT_TIMEOUT_SEC) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(webDriver, WAIT_TIMEOUT_SEC);
        wait.until(not(presenceOfAllElementsLocatedBy(By.cssSelector(elementName))));
        Thread.sleep(SLEEP_AFTER_WAIT_MS);
    }

    public static void waitForClosedElementContainingText(WebDriver webDriver, WebElement webElementName, String requiredText) {
        WebDriverWait wait = new WebDriverWait(webDriver, LONG_WAIT_SEC);
        wait.until(not(textToBePresentInElement(webElementName, requiredText)));
        sleep(SLEEP_AFTER_WAIT_MS);
    }

    public static void waitForClosedClass(WebDriver webDriver, String elementName) {
        WebDriverWait wait = new WebDriverWait(webDriver, WEBDRIVER_WAIT_TIMEOUT_SEC);
        wait.until(not(presenceOfAllElementsLocatedBy(By.className(elementName))));
        sleep(SLEEP_AFTER_WAIT_MS);
    }

    public static void waitForClosedClass(WebDriver webDriver, String elementName, long timeOutInSec) {
        WebDriverWait wait = new WebDriverWait(webDriver, timeOutInSec);
        wait.until(not(presenceOfAllElementsLocatedBy(By.className(elementName))));
        sleep(SLEEP_AFTER_WAIT_MS);
    }

    public static void closeErrorPopupIfExist(WebDriver webDriver, String popUpCSS) {
        try {
            Thread.sleep(VERY_LONG_WAIT_SEC);
            WebElement errorPopup = webDriver.findElement(By.cssSelector(popUpCSS));
            if (!(errorPopup == null)) {
                errorPopup.click();
                waitForClosedElement(webDriver, popUpCSS);
            }
        } catch (Exception e) {
            logger.info("no error popup exist.");
        }
    }

    public static void refreshPage(WebDriver driver, String elementName) {
        driver.navigate().refresh();
        waitForElement(driver, elementName);
    }

    public static void clickOnElementWithOffset(SeleniumDriver seleniumDriver, String elementName, int xOffsetValue, int yOffsetValue) {
        Actions action = new Actions(seleniumDriver.driver);
        action.moveToElement(seleniumDriver.driver.findElement(By.cssSelector(elementName)))
                .moveByOffset(xOffsetValue, yOffsetValue).click().build().perform();
    }

    public static void hoverOnElement(WebDriver driver, WebElement element) {
        Actions action = new Actions(driver);
        action.moveToElement(element).build().perform();
    }

    public static WebElement findSortedElementByCssContainingText(WebDriver driver, String containerElemCssPath, String textInElement) {
        return findElementsByCssContainingText(driver, containerElemCssPath, textInElement).stream()
                .sorted(Comparator.comparingInt(elem -> elem.getText().length())).findFirst().get();
    }

    public static WebElement findFirstElementByCssContainingText(WebDriver driver, String containerElemCssPath, String textInElement) {
        return findElementsByCssContainingText(driver, containerElemCssPath, textInElement).stream()
                .findFirst().get();
    }

    public static List<WebElement> findElementsByCssContainingText(WebDriver driver, String containerElemCssPath, String textInElement) {
        return driver.findElements(By.cssSelector(containerElemCssPath)).stream()
                .filter(childDr -> childDr.getText().toLowerCase().contains(textInElement.toLowerCase()))
                .collect(Collectors.toList());
    }

    public static WebElement findSortedElementByCssContainingText(WebElement elem, String containerElemCssPath, String textInElement) {
        return findElementsByCssContainingText(elem, containerElemCssPath, textInElement).get(0);
    }

    public static WebElement findElementByClassContainingText(WebDriver driver, String containerElemClassName, String textInElement) {
        return findElementsByCssContainingText(driver, "." + containerElemClassName, textInElement).get(0);
    }

    public static WebElement findElementByClassContainingText(WebElement elem, String containerElemClassName, String textInElement) {
        return findElementsByCssContainingText(elem, "." + containerElemClassName, textInElement).get(0);
    }

    public static List<WebElement> findElementsByCssContainingText(WebElement elem, String containerElemCssPath, String textInElement) {
        return elem.findElements(By.cssSelector(containerElemCssPath)).stream()
                .filter(childDr -> childDr.getText().contains(textInElement)).collect(Collectors.toList());
    }

    public static String getTextWhenPopulated(WebElement webElement) {
        int counter = 0;
        while (counter++ <= MAX_INTERVALS && webElement.getText().equals("")) {
            sleep(SLEEP_WAIT_FOR_MS);
        }
        if (counter > MAX_INTERVALS) {
            throw new IllegalStateException("The element wasn't populated! tries counter: " + counter);
        }
        return webElement.getText();
    }

    public static boolean hasClass(WebElement element, String cssClassToSearch) {
        return Arrays.stream(element.getAttribute("class").split(" "))
                .filter(className -> className.equals(cssClassToSearch)).count() > 0;
    }

    public static void clearElementText(WebElement elementToClear) {
        elementToClear.sendKeys(Keys.CONTROL + "a");
        elementToClear.sendKeys(Keys.DELETE);
        sleep(SLEEP_AFTER_WAIT_MS);
    }


    public static boolean isTableSorted(List<WebElement> uiTable, String sortFieldsQuery, int sortedField) {
        int count = uiTable.size();
        for (int i = 1; i < count; i++) {
            String currentText = uiTable.get(i).findElements(By.cssSelector(sortFieldsQuery)).get(sortedField).getText().toLowerCase();
            String prevText = uiTable.get(i - 1).findElements(By.cssSelector(sortFieldsQuery)).get(sortedField).getText().toLowerCase();
            if (prevText.compareTo(currentText) > 0) {
                return false;
            }
        }
        return true;
    }

    public static List<WebElement> filterClickableRowsFromTable(WebDriver driver, String tableRowsCssPath, String nestedRowContentCssPath) {
        return driver.findElements(By.cssSelector(tableRowsCssPath)).stream()
                .filter(subnetElem -> subnetElem.findElement(By.cssSelector(nestedRowContentCssPath)).getText().length() > 0)
                .collect(Collectors.toList());
    }
}
