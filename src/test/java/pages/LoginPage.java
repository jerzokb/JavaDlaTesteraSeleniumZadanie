package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.LoginPageUtils;

import java.time.Duration;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "email_create")
    WebElement inputEmail;

    @FindBy(id = "SubmitCreate")
    WebElement buttonCreateAnAccount;

    @FindBy(xpath = "//div[@id='create_account_error']")
    WebElement createAccountError;

    public void goToCreateAnAccountForm() {
        inputEmail.sendKeys(LoginPageUtils.TEST_NEW_EMAIL);
        buttonCreateAnAccount.click();
    }

    public void getCreateAccountErrorMessage() {
        inputEmail.sendKeys(LoginPageUtils.TEST_EXISTING_EMAIL);
        buttonCreateAnAccount.click();
    }

    public boolean isCreateAccountErrorDisplayed() {
        return createAccountError.isDisplayed();
    }
}
