package tests;

import enums.Countries;
import enums.States;
import model.Address;
import model.Person;
import model.PersonnalInformation;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.CreateAnAccountPage;
import pages.LoginPage;
import pages.TopMenuPage;
import com.github.javafaker.Faker;

public class CreateAccountTest extends BaseTest{
    private TopMenuPage topMenuPage;
    private LoginPage loginPage;
    private CreateAnAccountPage createAnAccountPage;
    private PersonnalInformation personnalInformation;
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

        personnalInformation = new PersonnalInformation();
        personnalInformation.setPerson(person);
        personnalInformation.setEmail(faker.internet().emailAddress());
        personnalInformation.setPassword(faker.internet().password());

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
        loginPage.goToCreateAnAccountForm(personnalInformation.getEmail());

        createAnAccountPage = new CreateAnAccountPage(driver);
    }

    @Test
    public void shouldDisplayCreateAnAccountFrom() {
        Assertions.assertThat(createAnAccountPage.isFormCreateAnAccountDisplayed()).isTrue();
    }

    @Test
    public void checkEmialAddressOnCreateAnAccountFrom() {
        Assertions.assertThat(createAnAccountPage.getEmialText()).containsIgnoringCase(personnalInformation.getEmail());
    }

    @Test
    public void noDataWereProvided() {
        createAnAccountPage.clickSubmitAccountButton();
        Assertions.assertThat(createAnAccountPage.isErrorMessageDisplayed()).isTrue();
    }

    @Test
    public void firstNameIsRequired() {
        createAnAccountPage.setAccountInfo(personnalInformation, address, 2);
        createAnAccountPage.clickSubmitAccountButton();
        System.out.println(createAnAccountPage.textDisplayed());
        Assertions.assertThat(createAnAccountPage.textDisplayed()).containsIgnoringCase("firstname is required");
    }

    @Test
    public void lastNameIsRequired() {
        createAnAccountPage.setAccountInfo(personnalInformation, address, 3);
        createAnAccountPage.clickSubmitAccountButton();
        System.out.println(createAnAccountPage.textDisplayed());
        Assertions.assertThat(createAnAccountPage.textDisplayed()).containsIgnoringCase("lastname is required");
    }

    @Test
    public void passwordIsRequired() {
        createAnAccountPage.setAccountInfo(personnalInformation, address, 4);
        createAnAccountPage.clickSubmitAccountButton();
        System.out.println(createAnAccountPage.textDisplayed());
        Assertions.assertThat(createAnAccountPage.textDisplayed()).containsIgnoringCase("passwd is required");
    }

    @Test
    public void addressIsRequired() {
        createAnAccountPage.setAccountInfo(personnalInformation, address, 5);
        createAnAccountPage.clickSubmitAccountButton();
        System.out.println(createAnAccountPage.textDisplayed());
        Assertions.assertThat(createAnAccountPage.textDisplayed()).containsIgnoringCase("address1 is required");
    }

    @Test
    public void cityIsRequired() {
        createAnAccountPage.setAccountInfo(personnalInformation, address, 6);
        createAnAccountPage.clickSubmitAccountButton();
        System.out.println(createAnAccountPage.textDisplayed());
        Assertions.assertThat(createAnAccountPage.textDisplayed()).containsIgnoringCase("city is required");
    }

    @Test
    public void postalCodeIsRequired() {
        createAnAccountPage.setAccountInfo(personnalInformation, address, 7);
        createAnAccountPage.clickSubmitAccountButton();
        System.out.println(createAnAccountPage.textDisplayed());
        Assertions.assertThat(createAnAccountPage.textDisplayed()).containsIgnoringCase("The Zip/Postal code you've entered is invalid. It must follow this format: 00000");
    }

    @Test
    public void phoneIsRequired() {
        createAnAccountPage.setAccountInfo(personnalInformation, address, 8);
        createAnAccountPage.clickSubmitAccountButton();
        System.out.println(createAnAccountPage.textDisplayed());
        Assertions.assertThat(createAnAccountPage.textDisplayed()).containsIgnoringCase("You must register at least one phone number");
    }

    @Test
    public void postalCodeIsInvalid() {
        createAnAccountPage.setAccountInfo(personnalInformation, address, 9);
        createAnAccountPage.clickSubmitAccountButton();
        System.out.println(createAnAccountPage.textDisplayed());
        Assertions.assertThat(createAnAccountPage.textDisplayed()).containsIgnoringCase("The Zip/Postal code you've entered is invalid. It must follow this format: 00000");
    }

    @Test
    public void accountShouldBeCreated() {
        createAnAccountPage.setAccountInfo(personnalInformation, address, 1);
        createAnAccountPage.clickSubmitAccountButton();
        System.out.println(driver.getTitle());
        Assertions.assertThat(driver.getTitle()).isEqualTo("My account - My Store");
    }
}
