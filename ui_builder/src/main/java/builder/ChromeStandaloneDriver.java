package builder;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ChromeStandaloneDriver implements ISeleniumDriver {
    public static final String BROWSER_LOCALE_DEFAULT = "en-us";
    public static final int PAGE_LOAD_TIME_SEC = 60;
    public WebDriver driver;
    private static Logger logger = LoggerFactory.getLogger(ChromeStandaloneDriver.class);

    public ChromeStandaloneDriver() { }

    private ChromeOptions setChromeCapabilities(){
        ChromeOptions chromeOptions = new ChromeOptions();
        logger.info("Set capability to accept SSL Connections");
        chromeOptions.setAcceptInsecureCerts(true);
        logger.info("Set capability to work with local " + BROWSER_LOCALE_DEFAULT);
        chromeOptions.addArguments("--lang=" + BROWSER_LOCALE_DEFAULT);
        return chromeOptions;
    }

    public void start() {
        logger.info("Will Start Selenium Chrome Driver/Client");
        ChromeOptions chromeOptions = setChromeCapabilities();
        logger.info("Setup Chrome Driver");
        ChromeDriverManager.chromedriver().setup();
        logger.info("Set Chrome with capabilities");
        this.driver = new ChromeDriver(chromeOptions);
        logger.info("Set page load time to be " + PAGE_LOAD_TIME_SEC + " seconds");
        this.driver.manage().timeouts().pageLoadTimeout(PAGE_LOAD_TIME_SEC, TimeUnit.SECONDS);
    }

    @Override
    public WebDriver getDriver() {
        if (this.driver != null){
            return this.driver;
        }
        return null;
    }
}
