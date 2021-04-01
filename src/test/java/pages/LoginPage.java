package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
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

    @FindBy(className = "alert-danger")
    WebElement createAccountError;

    public void goToCreateAnAccountForm(String emial) {
        inputEmail.sendKeys(emial);
        buttonCreateAnAccount.click();
    }

    public boolean isCreateAccountErrorDisplayed() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return createAccountError.isDisplayed();
    }
}
