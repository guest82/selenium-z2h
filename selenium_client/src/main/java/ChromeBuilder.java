import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.ChromeDriverManager;

public class ChromeBuilder implements IBrowserBuilder {

    private MutableCapabilities mutableCapabilities = new ChromeOptions();

    public ChromeBuilder(){

    }

    public IBrowserBuilder withManagedDriver(){
        ChromeDriverManager.chromedriver().setup();
        return this;
    }

    @Override
    public IBrowserBuilder withAccepInsecureCerts(){
        ((ChromeOptions) mutableCapabilities).setAcceptInsecureCerts(true);
        return this;
    }

    @Override
    public MutableCapabilities build(){
        MutableCapabilities returnVal = this.mutableCapabilities;
        this.mutableCapabilities = new ChromeOptions();
        return returnVal;
    }
}
