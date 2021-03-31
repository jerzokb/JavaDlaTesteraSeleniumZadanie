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
import utils.LoginPageUtils;
import com.github.javafaker.Faker;

public class CreateAccountTest extends BaseTest{
    private TopMenuPage topMenuPage;
    private LoginPage loginPage;
    private CreateAnAccountPage createAnAccountPage;
    private PersonnalInformation personnalInformation;
    private Address address;

    Faker faker = new Faker();

    @BeforeEach
    public void setupTest() {
        driver = new ChromeDriver();
        driver.get(BASE_URL);

        topMenuPage = new TopMenuPage(driver);
        topMenuPage.clickButtonSignIn();

        loginPage = new LoginPage(driver);
        loginPage.goToCreateAnAccountForm();

        createAnAccountPage = new CreateAnAccountPage(driver);

        Person person = new Person();
        person.setFirsName(faker.name().firstName());
        person.setLastName(faker.name().lastName());

        PersonnalInformation personnalInformation = new PersonnalInformation();
        personnalInformation.setPerson(person);
        personnalInformation.setEmail(faker.internet().emailAddress());
        personnalInformation.setPassword(faker.internet().password());

        Address address = new Address();
        address.setPerson(person);
        address.setAddress(faker.address().streetName());
        address.setCity("Minneapolis");
        address.setState(States.MINNESOTA);
        address.setZipCode(faker.number().digits(5));
        address.setCountry(Countries.UNITED_STATES);
        address.setMobilePhone(faker.phoneNumber().cellPhone());
        address.setAlias("Warrot");
    }

    @Test
    public void shouldDisplayCreateAnAccountFrom() {
        Assertions.assertThat(createAnAccountPage.isFormCreateAnAccountDisplayed()).isTrue();
    }

    @Test
    public void checkEmialAddressOnCreateAnAccountFrom() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assertions.assertThat(createAnAccountPage.getEmialText()).containsIgnoringCase(LoginPageUtils.TEST_NEW_EMAIL);
    }

    @Test
    public void isErrorMessageDisplayedWhenNoDataWereProvided() {
        createAnAccountPage.clickSubmitAccountButton();
        Assertions.assertThat(createAnAccountPage.isErrorMessageDisplayed()).isTrue();
    }

    @Test
    public void firstNameIsRequired() {
        createAnAccountPage.setAccountInfo(personnalInformation, address);
        createAnAccountPage.clickSubmitAccountButton();
        Assertions.assertThat(createAnAccountPage.getMessageText()).containsIgnoringCase("firstname is required");
    }
}
