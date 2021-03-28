package tests;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.CreateAnAccountPage;
import pages.LoginPage;
import pages.TopMenuPage;
import utils.LoginPageUtils;

public class LoginTest extends  BaseTest{
    private TopMenuPage topMenuPage;
    private LoginPage loginPage;
    private CreateAnAccountPage createAnAccountPage;

    @BeforeEach
    public void setupTest() {
        driver = new ChromeDriver();
        driver.get(BASE_URL);

        topMenuPage = new TopMenuPage(driver);
        topMenuPage.clickButtonSignIn();

        loginPage = new LoginPage(driver);
        loginPage.goToCreateAnAccountForm();

        createAnAccountPage = new CreateAnAccountPage(driver);
    }

    @Test
    public void testPageTitle() {
        Assertions.assertThat(driver.getTitle()).isEqualTo(LoginPageUtils.LOGIN_PAGE_TITLE);
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
        Assertions.assertThat(createAnAccountPage.getEmialText()).containsIgnoringCase(LoginPageUtils.TEST_EMAIL);
    }
}
