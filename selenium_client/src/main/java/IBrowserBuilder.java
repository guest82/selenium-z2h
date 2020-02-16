import org.openqa.selenium.MutableCapabilities;

public interface IBrowserBuilder {

    MutableCapabilities build();

    IBrowserBuilder withAccepInsecureCerts();
}
