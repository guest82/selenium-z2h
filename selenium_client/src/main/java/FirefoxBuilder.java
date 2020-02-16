import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;

import io.github.bonigarcia.wdm.FirefoxDriverManager;

public class FirefoxBuilder implements IBrowserBuilder {

    private MutableCapabilities mutableCapabilities = new FirefoxOptions();
    private FirefoxProfile firefoxProfile = new FirefoxProfile();;

    public FirefoxBuilder(){
    }

    public IBrowserBuilder withManagedDriver(){
        FirefoxDriverManager.firefoxdriver().setup();
        return this;
    }

    @Override
    public IBrowserBuilder withAccepInsecureCerts(){
        ((FirefoxOptions) mutableCapabilities).setAcceptInsecureCerts(true);
        ((FirefoxOptions) mutableCapabilities).setProfile(firefoxProfile);
        return this;
    }

    @Override
    public MutableCapabilities build(){
        MutableCapabilities returnVal = this.mutableCapabilities;
        this.mutableCapabilities = new FirefoxOptions();
        this.firefoxProfile = new FirefoxProfile();
        return returnVal;
    }
}
