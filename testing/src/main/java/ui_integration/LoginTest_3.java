package ui_integration;

import static ui_integration.CommonVariables.*;

import org.testng.Assert;
import org.testng.annotations.Test;

import builder.flows.LoginFlow;

public class LoginTest_3 extends BaseTest_1 {

    @Test
    public void loginPositive() throws Exception {
        LoginFlow.basicLoginFlow(chromeStandaloneDriver, USERNAME, PASSWORD);
        Assert.assertEquals(chromeStandaloneDriver.getDriver().getTitle(), WEBSITE_TITLE);
    }

}
