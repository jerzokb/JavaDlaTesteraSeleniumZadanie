package pages;

import org.awaitility.Awaitility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class BasePage {

    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    public static void awaitUntilElementIsDisplayed(WebElement element) {
        Awaitility.await().atMost(30, TimeUnit.SECONDS).ignoreExceptions().until(() -> element.isDisplayed());
    }

    public static void awaitUntilElementHasValue(WebElement element) {
        Awaitility.await().atMost(30, TimeUnit.SECONDS).ignoreExceptions().until(() -> !element.getAttribute("value").isBlank());
    }
}
