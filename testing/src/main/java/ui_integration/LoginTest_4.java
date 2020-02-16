package ui_integration;

import static ui_integration.CommonVariables.*;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import builder.flows.LoginFlow;
import test_infra.test_flow.BddExecutor;
import test_infra.test_flow.IBdd;
import test_infra.test_flow.Step;

public class LoginTest_4 extends BaseTest_2 {

    @Test
    public void loginPositive() throws Exception {
        LoginFlow.basicLoginFlow(seleniumDriver, USERNAME, PASSWORD);
        Assert.assertEquals(seleniumDriver.getDriver().getTitle(), WEBSITE_TITLE);
    }

    @DataProvider
    public Object[][] loginDataProvider() {
        return new Object[][]{
            {"Positive Login Scenario", USERNAME, PASSWORD, WEBSITE_TITLE}
//            {"Positive Login Scenario", USERNAME, PASSWORD, WEBSITE_TITLE},
//            {"Positive Login Scenario", "username", "password", "I will be successful"},
//            {"Negative Login Scenario", null, "password", "I will FAIL"},
        };
    }

    @Test(dataProvider = "loginDataProvider", groups = {"ui-integration"})
    public void loginTest(String testScenario, String username, String password, String excpectedPageTitle) throws Exception {
        BddExecutor.run(new IBdd() {

            @Step(description = "")
            @Override public void given() throws Exception {
                //TODO: Nothing to do here
            }

            @Step(description = "Basic Login Flow")
            @Override public void when() throws Exception {
                LoginFlow.basicLoginFlow(seleniumDriver, USERNAME, PASSWORD);
            }

            @Step(description = "Login Validation")
            @Override public void then() throws Exception {
                Assert.assertEquals(seleniumDriver.getDriver().getTitle(), WEBSITE_TITLE);
            }
        });
    }


}
