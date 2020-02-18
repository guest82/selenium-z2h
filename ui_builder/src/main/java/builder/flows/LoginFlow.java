package builder.flows;

import builder.ISeleniumDriver;
import builder.pages.LoginFluentPage;
import builder.pages.LoginPage;

public class LoginFlow {



    public static void basicLoginFlow(ISeleniumDriver seleniumDriver, String username, String password ) {
        LoginPage page = new LoginPage(seleniumDriver);
        page.inputUsername(username);
        page.inputPassword(password);
        page.clickLoginBtn();
    }

    public static void basicLoginFluentFlow(ISeleniumDriver seleniumDriver, String username, String password ) {
        new LoginFluentPage(seleniumDriver.getDriver())
            .inputUsername(username)
            .inputPassword(password)
            .clickLoginBtn();
    }

}
