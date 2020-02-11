package ui_integration;

import static ui_integration.CommonVariables.*;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import builder.ChromeStandaloneDriver;
import builder.flows.LoginFlow;

public class LoginTest_2 {

    ChromeStandaloneDriver chromeStandaloneDriver = new ChromeStandaloneDriver();

    @BeforeClass
    public void beforeClassInit(){
        chromeStandaloneDriver.start();
        chromeStandaloneDriver.getDriver().get(BASE_URL);
    }

    @AfterClass
    public void closeBrowser(){
        chromeStandaloneDriver.getDriver().close();
    }

    @Test
    public void loginPositive() throws Exception {
        LoginFlow.basicLoginFlow(chromeStandaloneDriver, USERNAME, PASSWORD);
        Assert.assertEquals(chromeStandaloneDriver.getDriver().getTitle(), WEBSITE_TITLE);
    }


}
