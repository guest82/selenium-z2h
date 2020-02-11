package builder.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import builder.BasePage;
import builder.ISeleniumDriver;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginPage extends BasePage {

    @FindBy(id = "login_field")
    private WebElement usernameFld;

    @FindBy(id = "password")
    private WebElement passwordFld;

    @FindBy(name = "commit")
    private WebElement loginBtn;

    public LoginPage(ISeleniumDriver seleniumDriver) {
        super(seleniumDriver.getDriver());
    }

    public void inputUsername(String username) {
        usernameFld.clear();
        usernameFld.sendKeys(username);
    }

    public void inputPassword(String password) {
        passwordFld.clear();
        sendKeys(passwordFld, password);
    }

    public void clickLoginBtn(){
        click(loginBtn);
    }

}
