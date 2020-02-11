package builder;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.CapabilityType;

public class BaseSeleniumDriver {

    public static final String EDGE_DRIVER_VERSION = "3.14393";
    public static final String FIREFOX = "firefox";
    public static final String CHROME = "chrome";
    public static final String EDGE = "edge";
    public static final String BROWSER_LOCALE_DEFAULT = "en-us";
    public static final int PAGE_LOAD_TIME_SEC = 60;
    public WebDriver driver;

    public MutableCapabilities setCapabilities(String browserType){
        MutableCapabilities capabilities = new MutableCapabilities();
        switch (browserType) {
            case FIREFOX:
                capabilities = new FirefoxOptions();
                FirefoxProfile profile = new FirefoxProfile();
                profile.setPreference("intl.accept_languages", BROWSER_LOCALE_DEFAULT.toLowerCase());
                ((FirefoxOptions) capabilities).setAcceptInsecureCerts(true);
                ((FirefoxOptions) capabilities).setProfile(profile);
                break;
            case EDGE:
                capabilities = new EdgeOptions();
                capabilities.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
                break;
            default:
                capabilities = new ChromeOptions();
                ((ChromeOptions) capabilities).setAcceptInsecureCerts(true);
                ((ChromeOptions) capabilities).addArguments("--lang=" + BROWSER_LOCALE_DEFAULT.toLowerCase());
                break;
            }
            return capabilities;
    }

    public String takeScreenshot(WebDriver driver, String screenshotFileName) throws IOException {
        String newFilePath = String.format("%s%sScreenShots%s%s.%s", this.getClass().getResource("/").getFile(),
            File.separatorChar, File.separatorChar, screenshotFileName,"png");
        File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(srcFile, new File(newFilePath));
        FileUtils.forceDelete(srcFile);
        return newFilePath;
    }

}
