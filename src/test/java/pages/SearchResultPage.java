package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

public class SearchResultPage extends BasePage {

    public SearchResultPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//div[@class='right-block']//a[@class='product-name']")
    List<WebElement> productsNames;

    public int getProductNamesListSize() {
        return productsNames.size();
    }

    public List<String> productsNames() {
        return productsNames.stream()
                .map(name -> name.getText().trim())
                .collect(Collectors.toList());
    }
}
