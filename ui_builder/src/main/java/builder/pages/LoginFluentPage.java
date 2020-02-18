package builder.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import builder.BasePage2;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginFluentPage extends BasePage2 {

    private By usernameFld = By.id("login_field");

    private By passwordFld = By.id("password");

    private By loginBtn = By.name("commit");

    public LoginFluentPage(WebDriver driver) {
        super(driver);
    }

    public LoginFluentPage go(String uri){
        goToUrl(uri);
        return this;
    }

    public LoginFluentPage inputUsername(String username) {
        writeText(usernameFld, username);
        return this;
    }

    public LoginFluentPage inputPassword(String password) {
        writeText(passwordFld, password);
        return this;
    }

    public LoginFluentPage clickLoginBtn(){
        click(loginBtn);
        return this;
    }

}
