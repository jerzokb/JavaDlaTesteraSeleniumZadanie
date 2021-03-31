package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

public class WomenPage extends BasePage{

    public WomenPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//span[@class='price product-price']")
    List<WebElement> productsPrices;

    public List<String> getProductsPrices() {
        return productsPrices.stream()
                .map(price -> price.getText())
                .collect(Collectors.toList());
    }

    public int getNumberOfProducts() {
        return productsPrices.size();
    }
}
