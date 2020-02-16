import java.net.MalformedURLException;

public class Test {

    public static void main(String[] args) throws MalformedURLException {
        SeleniumDriver seleniumDriver = new SeleniumDriver().withStandalone(new FirefoxBuilder().withManagedDriver().withAccepInsecureCerts().build());
        seleniumDriver.close();

        seleniumDriver = new SeleniumDriver().withStandalone(new ChromeBuilder().withManagedDriver().withAccepInsecureCerts().build());
        seleniumDriver.close();
        seleniumDriver = new SeleniumDriver().withRemote(new FirefoxBuilder().withAccepInsecureCerts().build());
        seleniumDriver.close();
    }
}
