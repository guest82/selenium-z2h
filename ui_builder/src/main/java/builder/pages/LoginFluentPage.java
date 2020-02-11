package builder.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import builder.BasePage;
import builder.ISeleniumDriver;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginFluentPage extends BasePage {

    @FindBy(id = "login_field")
    private WebElement usernameFld;

    @FindBy(id = "password")
    private WebElement passwordFld;

    @FindBy(name = "commit")
    private WebElement loginBtn;

    public LoginFluentPage(ISeleniumDriver seleniumDriver) {
        super(seleniumDriver.getDriver());
    }

    public LoginFluentPage inputUsername(String username) {
        usernameFld.clear();
        usernameFld.sendKeys(username);
        return this;
    }

    public LoginFluentPage inputPassword(String password) {
        passwordFld.clear();
        sendKeys(passwordFld, password);
        return this;
    }

    public LoginFluentPage clickLoginBtn(){
        click(loginBtn);
        return this;
    }

}
