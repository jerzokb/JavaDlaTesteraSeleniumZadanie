package pages;

import model.Address;
import model.PersonalInformation;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.CreateAccountPageUtils;

public class CreateAnAccountPage extends BasePage{

    public CreateAnAccountPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "account-creation_form")
    WebElement formCreateAnAccount;

    @FindBy(id = "email")
    WebElement email;

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
        awaitUntilElementIsDisplayed(formCreateAnAccount);
        return formCreateAnAccount.isDisplayed();
    }

    public String getEmailText() {
        awaitUntilElementHasValue(email);
        return email.getAttribute("value");
    }

    public void clickSubmitAccountButton() {
        awaitUntilElementIsDisplayed(buttonSumbitAccount);
        buttonSumbitAccount.click();
    }

    public boolean isErrorMessageDisplayed() {
        awaitUntilElementIsDisplayed(errorMessage);
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

    public void setAccountInfo(PersonalInformation personalInformation, Address address, String missingField) {
        clickTitleMr();
        customerFirstName.sendKeys(personalInformation.getPerson().getFirstName());
        customerLastName.sendKeys(personalInformation.getPerson().getLastName());
        passwd.sendKeys(personalInformation.getPassword());
        address1.sendKeys(address.getAddress());
        city.sendKeys(address.getCity());
        idState.sendKeys(address.getState().getValue());
        postCode.sendKeys(address.getZipCode());
        phoneMobile.sendKeys(address.getMobilePhone());
        alias.sendKeys(address.getAlias());
        switch (missingField) {
            case CreateAccountPageUtils.MISSING_NONE: // all data are provided
                break;
            case CreateAccountPageUtils.MISSING_CUSTOMER_FIRST_NAME: // no first name
                customerFirstName.clear();
                break;
            case CreateAccountPageUtils.MISSING_CUSTOMER_LAST_NAME: // no last name
                customerLastName.clear();
                break;
            case CreateAccountPageUtils.MISSING_PASSWD: // no password
                passwd.clear();
            case CreateAccountPageUtils.MISSING_ADDRESS1: // no address
                address1.clear();
            case CreateAccountPageUtils.MISSING_CITY: // no city
                city.clear();
            case CreateAccountPageUtils.MISSING_POST_CODE: // no zip code
                postCode.clear();
            case CreateAccountPageUtils.MISSING_PHONE_MOBILE: // no phone
                phoneMobile.clear();
            case CreateAccountPageUtils.POST_CODE_FORMAT_INCORRECT: // invalid postal code number
                postCode.click();
                postCode.sendKeys("ABCDE");
        }

    }
}
