package builder;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.EdgeDriverManager;
import io.github.bonigarcia.wdm.FirefoxDriverManager;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;


@Getter
@Setter
@Slf4j
public class SeleniumDriver extends BaseSeleniumDriver implements ISeleniumDriver{

    private String hubUrl = null;
    private String browser = "chrome";

    private static Logger logger = LoggerFactory.getLogger(SeleniumDriver.class);

    public SeleniumDriver(){
    }

    public SeleniumDriver(String hubUrl) {
        this.hubUrl = hubUrl;
    }

    public void start() throws Exception {
        start(null);
    }

    public void start(String browser) throws Exception {
        if (browser != null) {
            this.browser = browser;
        }
        logger.debug(String.format("Starting UI session with: %s browser, using: %s locale.",
            this.browser, BROWSER_LOCALE_DEFAULT.toLowerCase()));

        MutableCapabilities options = setCapabilities(this.browser);

        if (this.hubUrl != null) {
            try {
                this.driver = new RemoteWebDriver(new URL(String.format("%s/wd/hub", this.hubUrl)), options);
            } catch (Exception e) {
                logger.error(String.format("can't connect to Selenium Grid Hub under: %s with error: %s"
                        , this.hubUrl, e.getMessage()));
                throw e;
            }
        } else {
            setBrowser(this.browser, options);
        }

        this.driver.manage().timeouts()
                .pageLoadTimeout(60, TimeUnit.SECONDS).implicitlyWait(60, TimeUnit.SECONDS);
    }

    private void setBrowser(String browserType, MutableCapabilities options) {
        switch (browserType) {
            case FIREFOX:
                FirefoxDriverManager.firefoxdriver().setup();
                this.driver = new FirefoxDriver((FirefoxOptions)options);
                break;
            case EDGE:
                EdgeDriverManager.edgedriver().version(EDGE_DRIVER_VERSION).setup();
                this.driver = new EdgeDriver((EdgeOptions)options);
                break;
            default:
                ChromeDriverManager.chromedriver().setup();
                this.driver = new ChromeDriver((ChromeOptions)options);
                break;
        }
    }

    @Override
    public WebDriver getDriver() {
        if (this.driver != null){
            return this.driver;
        }
        return null;
    }
}
