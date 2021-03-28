package pages;

import org.assertj.core.api.AbstractBooleanAssert;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreateAnAccountPage extends BasePage{

    public CreateAnAccountPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "account-creation_form")
    WebElement formCreateAnAccount;

    @FindBy(id = "email")
    WebElement inputEmial;

    public boolean isFormCreateAnAccountDisplayed() {
        return formCreateAnAccount.isDisplayed();
    }

    public String getEmialText() {
        return inputEmial.getAttribute("value");
    }
}
