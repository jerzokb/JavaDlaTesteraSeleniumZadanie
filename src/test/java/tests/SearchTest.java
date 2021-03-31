package tests;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.MainPage;
import pages.SearchResulPage;
import pages.SubMenuPage;
import utils.SearchPageUtils;
import utils.WomenPageUtils;

public class SearchTest extends BaseTest{

    private MainPage mainPage;
    private SearchResulPage searchResulPage;

    @BeforeEach
    public void setupTest() {
        driver = new ChromeDriver();
        driver.get(BASE_URL);

        mainPage = new MainPage(driver);
        mainPage.clickSearchProduct();

        searchResulPage = new SearchResulPage(driver);
    }

    @Test
    public void testPageTitle() {
        Assertions.assertThat(driver.getTitle()).isEqualTo(SearchPageUtils.SEARCH_PAGE_TITLE);
    }

    @Test
    public void shouldDisplayedSearchedProducts() {
        for (int i = 0; i < searchResulPage.getProductNamesListSize(); i++) {
            Assertions.assertThat(searchResulPage.productsNames().get(i)).isEqualTo(SearchPageUtils.PRODUCT_NAME);
        }
    }

    @Test
    public void shouldReturnOneElement() {
        Assertions.assertThat(searchResulPage.getProductNamesListSize()).isEqualTo(1);
    }
}
