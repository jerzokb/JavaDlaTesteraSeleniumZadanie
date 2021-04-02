package tests;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.MainPage;
import pages.SearchResultPage;
import utils.SearchPageUtils;

public class SearchTest extends BaseTest{

    private MainPage mainPage;
    private SearchResultPage searchResultPage;

    @BeforeEach
    public void setupTest() {
        driver = new ChromeDriver();
        driver.get(BASE_URL);

        mainPage = new MainPage(driver);
        mainPage.clickSearchProduct();

        searchResultPage = new SearchResultPage(driver);
    }

    @Test
    public void testPageTitle() {
        Assertions.assertThat(driver.getTitle()).isEqualTo(SearchPageUtils.SEARCH_PAGE_TITLE);
    }

    @Test
    public void shouldDisplayedSearchedProducts() {
        for (int i = 0; i < searchResultPage.getProductNamesListSize(); i++) {
            Assertions.assertThat(searchResultPage.productsNames().get(i)).isEqualTo(SearchPageUtils.PRODUCT_NAME);
        }
    }

    @Test
    public void shouldReturnOneElement() {
        Assertions.assertThat(searchResultPage.getProductNamesListSize()).isEqualTo(1);
    }
}
