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

        womenPage = new WomenPage(driver);
    }

    @Test
    public void testPageTitle() {
        Assertions.assertThat(driver.getTitle()).isEqualTo(WomenPageUtils.WOMEN_PAGE_TITLE);
    }

    @Test
    public void pricesShouldNotBeZero1() {
        boolean isPriceZero = false;
        for(int i = 0; i < womenPage.getNumberOfProducts(); i++) {
            if (womenPage.getProductsPrices().get(i) == WomenPageUtils.PRICE_NOT_VALID) {
                isPriceZero = true;
            }
        }
        Assertions.assertThat(isPriceZero).isEqualTo(false);
    }

    @Test
    public void pricesShouldNotBeZero2() {
        int nonZeroPrices = 0;
        for(int i = 0; i < womenPage.getNumberOfProducts(); i++) {
            if (womenPage.getProductsPrices().get(i) != WomenPageUtils.PRICE_NOT_VALID) {
                nonZeroPrices = nonZeroPrices + 1;
            }
        }
        Assertions.assertThat(womenPage.getNumberOfProducts()).isEqualTo(nonZeroPrices);
    }
}
