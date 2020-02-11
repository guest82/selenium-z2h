package ui_integration;

import static ui_integration.CommonVariables.*;

import org.testng.Assert;
import org.testng.annotations.Test;

import builder.flows.LoginFlow;

public class LoginTest_4 extends BaseTest_2 {

    @Test
    public void loginPositive() throws Exception {
        LoginFlow.basicLoginFlow(seleniumDriver, USERNAME, PASSWORD);
        Assert.assertEquals(seleniumDriver.getDriver().getTitle(), WEBSITE_TITLE);
    }

}
