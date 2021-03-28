package tests;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.*;
import utils.LoginPageUtils;
import utils.WomenPageUtils;

public class WomenTest extends BaseTest{
    private SubMenuPage subMenuPage;
    private WomenPage womenPage;

    @BeforeEach
    public void setupTest() {
        driver = new ChromeDriver();
        driver.get(BASE_URL);

        subMenuPage = new SubMenuPage(driver);
        subMenuPage.clickWomenMenuOption();
    }

    @Test
    public void testPageTitle() {
        Assertions.assertThat(driver.getTitle()).isEqualTo(WomenPageUtils.WOMEN_PAGE_TITLE);
    }
}
