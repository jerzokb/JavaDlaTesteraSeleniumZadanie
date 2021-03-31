package pages;

import enums.Countries;
import enums.States;
import model.Address;
import model.PersonnalInformation;
import org.assertj.core.api.AbstractBooleanAssert;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class CreateAnAccountPage extends BasePage{

    public CreateAnAccountPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "account-creation_form")
    WebElement formCreateAnAccount;

    @FindBy(id = "email")
    WebElement inputEmial;

    @FindBy(id = "submitAccount")
    WebElement buttonSumbitAccount;

    @FindBy(className = "alert-danger")
    WebElement errorMessage;

    @FindBy(xpath = "//div[@class='alert-danger']//li")
    WebElement errorMessageText;

    @FindBy(id = "uniform-id_gender1")
    WebElement radioButtonMr;

    @FindBy(id = "uniform-id_gender2")
    WebElement radioButtonMrs;

    @FindBy(id = "customer_firstname")
    WebElement customerFirstName;

    @FindBy(id = "customer_lastname")
    WebElement customerLastName;

    @FindBy(id = "passwd")
    WebElement passwd;

    @FindBy(id = "address1")
    WebElement address1;

    @FindBy(id = "city")
    WebElement city;

    @FindBy(id = "id_state")
    WebElement idState;

    @FindBy(id = "postcode")
    WebElement postCode;

    @FindBy(id = "uniform-id_country")
    WebElement uniformIdCountry;

    @FindBy(id = "phone_mobile")
    WebElement phoneMobile;

    @FindBy(id = "alias")
    WebElement alias;

    public boolean isFormCreateAnAccountDisplayed() {
        return formCreateAnAccount.isDisplayed();
    }

    public String getEmialText() {
        return inputEmial.getAttribute("value");
    }

    public void clickSubmitAccountButton() {
        buttonSumbitAccount.click();
    }

    public boolean isErrorMessageDisplayed() {
        return errorMessage.isDisplayed();
    }

    public String getMessageText() {
        return errorMessageText.getText();
    }

    public void clickTitleMr() {
        wait.until(ExpectedConditions.visibilityOf(radioButtonMr));
        radioButtonMr.click();
    }

    public void clickTitleMrs() {
        wait.until(ExpectedConditions.visibilityOf(radioButtonMr));
        radioButtonMrs.click();
    }

    public void setAccountInfo(PersonnalInformation personnalInformation, Address adress) {
        this.clickTitleMr();
        customerLastName.sendKeys(personnalInformation.getPerson().getLastName());
        passwd.sendKeys(personnalInformation.getEmail());
        address1.sendKeys(adress.getAddress());
        city.sendKeys(adress.getCity());
        idState.sendKeys(adress.getState().getValue());
        postCode.sendKeys(adress.getZipCode());
        uniformIdCountry.sendKeys(adress.getCountry().getValue());
        phoneMobile.sendKeys(adress.getMobilePhone());
        alias.sendKeys(adress.getAlias());
    }
}
