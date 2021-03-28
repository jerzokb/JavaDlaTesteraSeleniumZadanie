package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.SearchPageUtils;

public class MainPage extends BasePage{
    public MainPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "search_query_top")
    WebElement inputSearch;

    @FindBy(name = "submit_search")
    WebElement buttonSubmitSearch;

    public void clickSearchProduct() {
        inputSearch.sendKeys(SearchPageUtils.PRODUCT_NAME);
        buttonSubmitSearch.click();
    }
}
