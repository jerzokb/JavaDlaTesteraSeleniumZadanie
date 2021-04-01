package tests;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.CreateAnAccountPage;
import pages.LoginPage;
import pages.TopMenuPage;
import utils.LoginPageUtils;

import java.time.Duration;

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

        createAnAccountPage = new CreateAnAccountPage(driver);
    }

    @Test
    public void testPageTitle() {
        Assertions.assertThat(driver.getTitle()).isEqualTo(LoginPageUtils.LOGIN_PAGE_TITLE);
    }

    @Test
    public void emailAddressShouldBeUnique() {
        loginPage.goToCreateAnAccountForm(LoginPageUtils.TEST_EXISTING_EMAIL);
        Assertions.assertThat(loginPage.isCreateAccountErrorDisplayed()).isTrue();
    }
}
