package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.LoginPageUtils;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "email_create")
    WebElement inputEmail;

    @FindBy(id = "SubmitCreate")
    WebElement buttonCreateAnAccount;

    public void goToCreateAnAccountForm() {
        inputEmail.sendKeys(LoginPageUtils.TEST_EMAIL);
        buttonCreateAnAccount.click();
    }
}
