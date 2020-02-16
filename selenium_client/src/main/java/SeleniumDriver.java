import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SeleniumDriver implements ISeleniumDriver{

    private String gridUrl = null;
    private WebDriver driver;
    private MutableCapabilities mutableCapabilities;

    private static Logger logger = LoggerFactory.getLogger(SeleniumDriver.class);

    public SeleniumDriver(){ }

    public SeleniumDriver withOptions(MutableCapabilities mutableCapabilities){
        this.mutableCapabilities = mutableCapabilities;
        return this;
    }

    public SeleniumDriver withStandalone(MutableCapabilities options){
        if (options instanceof FirefoxOptions){
            this.driver = new FirefoxDriver((FirefoxOptions)options);
        }else{
            if (options instanceof ChromeOptions){
                this.driver = new ChromeDriver((ChromeOptions)options);
            }else{
                this.driver = new ChromeDriver();
            }
        }
        return this;
    }

    public SeleniumDriver withGrid(String gridUrl){
        this.gridUrl = gridUrl;
        return this;
    }

    public SeleniumDriver withRemote(MutableCapabilities options) throws MalformedURLException {
        try {
            this.driver = new RemoteWebDriver(new URL(String.format("%s/wd/hub", this.gridUrl)), options);
        } catch (MalformedURLException e) {
            logger.error("Grid Url is Malformed, please fix before proceeding");
            throw e;
        }
        return this;
    }

    public SeleniumDriver withPageLoadTimeout(){
        if (initialized()){
            this.driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
        }
        return this;
    }

    public SeleniumDriver withImplicitlyWait(){
        if (initialized()){
            this.driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        }
        return this;
    }

    @Override
    public WebDriver getDriver() {
        if (initialized()){
            return this.driver;
        }
        return null;
    }

    public void close(){
        if (initialized()){
            this.driver.close();
            this.driver = null;
        }
    }

    private boolean initialized(){
        return this.driver != null;
    }
}
