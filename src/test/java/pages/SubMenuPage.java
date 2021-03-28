package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class SubMenuPage extends BasePage{

    public SubMenuPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(className = "sf-with-ul")
    List<WebElement> subMenuOptions;

    public void clickWomenMenuOption() {
        subMenuOptions.get(0).click();
    }
}
