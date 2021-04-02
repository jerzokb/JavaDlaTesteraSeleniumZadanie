package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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

    public void goToCreateAnAccountForm(String email) {
        awaitUntilElementIsDisplayed(inputEmail);
        inputEmail.sendKeys(email);
        buttonCreateAnAccount.click();
    }

    public boolean isCreateAccountErrorDisplayed() {
        awaitUntilElementIsDisplayed(createAccountError);
        return createAccountError.isDisplayed();
    }
}
