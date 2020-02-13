package builder;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;

public class BaseSeleniumDriver {

    public static final String FIREFOX = "firefox";
    public static final String CHROME = "chrome";
    public static final String BROWSER_LOCALE_DEFAULT = "en-us";
    public WebDriver driver;

    public MutableCapabilities setCapabilities(String browserType){
        MutableCapabilities capabilities = new MutableCapabilities();
        switch (browserType) {
            case FIREFOX:
                capabilities = new FirefoxOptions();
                FirefoxProfile profile = new FirefoxProfile();
                ((FirefoxOptions) capabilities).setAcceptInsecureCerts(true);
                ((FirefoxOptions) capabilities).setProfile(profile);
                break;
            default:
                capabilities = new ChromeOptions();
                ((ChromeOptions) capabilities).setAcceptInsecureCerts(true);
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
