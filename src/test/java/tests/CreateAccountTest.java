package tests;

import enums.Countries;
import enums.States;
import model.Address;
import model.Person;
import model.PersonalInformation;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.CreateAnAccountPage;
import pages.LoginPage;
import pages.TopMenuPage;
import com.github.javafaker.Faker;
import utils.CreateAccountPageUtils;
import utils.LoginPageUtils;

public class CreateAccountTest extends BaseTest{
    private TopMenuPage topMenuPage;
    private LoginPage loginPage;
    private CreateAnAccountPage createAnAccountPage;
    private PersonalInformation personalInformation;
    private Address address;
    private Person person;

    Faker faker = new Faker();

    @BeforeEach
    public void setupTest() {
        driver = new ChromeDriver();
        driver.get(BASE_URL);

        person = new Person();
        person.setFirstName(faker.name().firstName());
        person.setLastName(faker.name().lastName());

        personalInformation = new PersonalInformation();
        personalInformation.setPerson(person);
        personalInformation.setEmail(faker.internet().emailAddress());
        personalInformation.setPassword(faker.internet().password());

        address = new Address();
        address.setPerson(person);
        address.setAddress(faker.address().streetName());
        address.setCity("Minneapolis");
        address.setState(States.MINNESOTA);
        address.setZipCode(faker.number().digits(5));
        address.setCountry(Countries.UNITED_STATES);
        address.setMobilePhone(faker.phoneNumber().cellPhone());
        address.setAlias("Warrot");

        topMenuPage = new TopMenuPage(driver);
        topMenuPage.clickButtonSignIn();

        loginPage = new LoginPage(driver);
        loginPage.goToCreateAnAccountForm(personalInformation.getEmail());

        createAnAccountPage = new CreateAnAccountPage(driver);
    }

    @Test
    public void shouldDisplayCreateAnAccountFrom() {
        Assertions.assertThat(createAnAccountPage.isFormCreateAnAccountDisplayed()).isTrue();
    }

    @Test
    public void shouldDisplayMessageWhenNoDataWereProvided() {
        createAnAccountPage.clickSubmitAccountButton();
        Assertions.assertThat(createAnAccountPage.isErrorMessageDisplayed()).isTrue();
    }

    @Test
    public void shouldDisplayMessageWhenFirstNameWasNotProvided() {
        createAnAccountPage.setAccountInfo(personalInformation, address, CreateAccountPageUtils.MISSING_CUSTOMER_FIRST_NAME);
        createAnAccountPage.clickSubmitAccountButton();
        System.out.println(createAnAccountPage.textDisplayed());
        Assertions.assertThat(createAnAccountPage.textDisplayed()).containsIgnoringCase(CreateAccountPageUtils.EXPECTED_TEXT_FIRST_NAME_REQUIRED);
    }

    @Test
    public void shouldDisplayMessageWhenLastNameWasNotProvided() {
        createAnAccountPage.setAccountInfo(personalInformation, address, CreateAccountPageUtils.MISSING_CUSTOMER_LAST_NAME);
        createAnAccountPage.clickSubmitAccountButton();
        System.out.println(createAnAccountPage.textDisplayed());
        Assertions.assertThat(createAnAccountPage.textDisplayed()).containsIgnoringCase(CreateAccountPageUtils.EXPECTED_TEXT_LAST_NAME_REQUIRED);
    }

    @Test
    public void shouldDisplayMessageWhenPasswordWasNotProvided() {
        createAnAccountPage.setAccountInfo(personalInformation, address, CreateAccountPageUtils.MISSING_PASSWD);
        createAnAccountPage.clickSubmitAccountButton();
        System.out.println(createAnAccountPage.textDisplayed());
        Assertions.assertThat(createAnAccountPage.textDisplayed()).containsIgnoringCase(CreateAccountPageUtils.EXPECTED_TEXT_PASSWD_REQUIRED);
    }

    @Test
    public void shouldDisplayMessageWhenAddressWasNotProvided() {
        createAnAccountPage.setAccountInfo(personalInformation, address, CreateAccountPageUtils.MISSING_ADDRESS1);
        createAnAccountPage.clickSubmitAccountButton();
        System.out.println(createAnAccountPage.textDisplayed());
        Assertions.assertThat(createAnAccountPage.textDisplayed()).containsIgnoringCase(CreateAccountPageUtils.EXPECTED_TEXT_ADDRESS1_REQUIRED);
    }

    @Test
    public void shouldDisplayMessageWhenCityWasNotProvided() {
        createAnAccountPage.setAccountInfo(personalInformation, address, CreateAccountPageUtils.MISSING_CITY);
        createAnAccountPage.clickSubmitAccountButton();
        System.out.println(createAnAccountPage.textDisplayed());
        Assertions.assertThat(createAnAccountPage.textDisplayed()).containsIgnoringCase(CreateAccountPageUtils.EXPECTED_TEXT_CITY_REQUIRED);
    }

    @Test
    public void shouldDisplayMessageWhenPostalCodeWasNotProvided() {
        createAnAccountPage.setAccountInfo(personalInformation, address, CreateAccountPageUtils.MISSING_POST_CODE);
        createAnAccountPage.clickSubmitAccountButton();
        System.out.println(createAnAccountPage.textDisplayed());
        Assertions.assertThat(createAnAccountPage.textDisplayed()).containsIgnoringCase(CreateAccountPageUtils.EXPECTED_TEXT_POST_CODE_REQUIRED);
    }

    @Test
    public void shouldDisplayMessageWhenPhoneWasNotProvided() {
        createAnAccountPage.setAccountInfo(personalInformation, address, CreateAccountPageUtils.MISSING_PHONE_MOBILE);
        createAnAccountPage.clickSubmitAccountButton();
        System.out.println(createAnAccountPage.textDisplayed());
        Assertions.assertThat(createAnAccountPage.textDisplayed()).containsIgnoringCase(CreateAccountPageUtils.EXPECTED_TEXT_PHONE_MOBILE_REQUIRED);
    }

    @Test
    public void shouldDisplayMessageWhenPostalCodeIsInvalid() {
        createAnAccountPage.setAccountInfo(personalInformation, address, CreateAccountPageUtils.POST_CODE_FORMAT_INCORRECT);
        createAnAccountPage.clickSubmitAccountButton();
        System.out.println(createAnAccountPage.textDisplayed());
        Assertions.assertThat(createAnAccountPage.textDisplayed()).containsIgnoringCase(CreateAccountPageUtils.EXPECTED_TEXT_POST_CODE_FORMAT_INCORRECT);
    }

    @Test
    public void accountShouldBeCreated() {
        createAnAccountPage.setAccountInfo(personalInformation, address, CreateAccountPageUtils.MISSING_NONE);
        createAnAccountPage.clickSubmitAccountButton();
        System.out.println(driver.getTitle());
        Assertions.assertThat(driver.getTitle()).isEqualTo(CreateAccountPageUtils.EXPECTED_PAGE_TITLE);
    }
}
