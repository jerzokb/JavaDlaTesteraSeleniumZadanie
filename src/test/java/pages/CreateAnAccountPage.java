package pages;

import enums.Countries;
import enums.States;
import model.Address;
import model.PersonnalInformation;
import org.assertj.core.api.AbstractBooleanAssert;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
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

    @FindBy(id = "phone_mobile")
    WebElement phoneMobile;

    @FindBy(id = "alias")
    WebElement alias;
    

    public boolean isFormCreateAnAccountDisplayed() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return formCreateAnAccount.isDisplayed();
    }

    public String getEmialText() {
        return inputEmial.getAttribute("value");
    }

    public void clickSubmitAccountButton() {
        buttonSumbitAccount.click();
    }

    public boolean isErrorMessageDisplayed() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return errorMessage.isDisplayed();
    }

    public String textDisplayed() {
        return errorMessage.getText();
    }

    public void clickTitleMr() {
        wait.until(ExpectedConditions.visibilityOf(radioButtonMr));
        radioButtonMr.click();
    }

    public void clickTitleMrs() {
        wait.until(ExpectedConditions.visibilityOf(radioButtonMr));
        radioButtonMrs.click();
    }

    public void setAccountInfo(PersonnalInformation personnalInformation, Address address, int switcher) {
        clickTitleMr();
        customerFirstName.sendKeys(personnalInformation.getPerson().getFirstName());
        customerLastName.sendKeys(personnalInformation.getPerson().getLastName());
        passwd.sendKeys(personnalInformation.getPassword());
        address1.sendKeys(address.getAddress());
        city.sendKeys(address.getCity());
        idState.sendKeys(address.getState().getValue());
        postCode.sendKeys(address.getZipCode());
        phoneMobile.sendKeys(address.getMobilePhone());
        alias.sendKeys(address.getAlias());
        switch (switcher) {
            case 1: // all data
                break;
            case 2: // no first name
                customerFirstName.clear();
                break;
            case 3: // no last name
                customerLastName.clear();
                break;
            case 4: // no password
                passwd.clear();
            case 5: // no address
                address1.clear();
            case 6: // no city
                city.clear();
            case 7: // no zip code
                postCode.clear();
            case 8: // no phone
                phoneMobile.clear();
            case 9: // invalid postal code number
                postCode.click();
                postCode.sendKeys("ABCDE");
        }

    }
}
