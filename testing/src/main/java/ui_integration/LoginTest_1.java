package ui_integration;

import static ui_integration.CommonVariables.*;

import org.testng.Assert;
import org.testng.annotations.Test;

import builder.ChromeStandaloneDriver;
import builder.flows.LoginFlow;

public class LoginTest_1 {

    @Test
    public void loginPositive() throws Exception {
        ChromeStandaloneDriver chromeStandaloneDriver = new ChromeStandaloneDriver();
        chromeStandaloneDriver.start();
        chromeStandaloneDriver.getDriver().get(BASE_URL);
        LoginFlow.basicLoginFlow(chromeStandaloneDriver, USERNAME, PASSWORD);
        Assert.assertEquals(chromeStandaloneDriver.getDriver().getTitle(), WEBSITE_TITLE);
        chromeStandaloneDriver.getDriver().close();
    }

}
