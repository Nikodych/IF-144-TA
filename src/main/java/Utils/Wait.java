package Utils;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static java.lang.Integer.parseInt;

public class Wait {

    private WebDriver driver;
    private By locator;

    public Wait(final WebDriver driver, final By locator) {
        this.driver = driver;
        this.locator = locator;
    }

    public final WebElement getPresentElement() {
        var timeout = parseInt(ConfigurationHelper.getProperty("time.wait"));

        return new WebDriverWait(driver, timeout)
                .ignoring(NoSuchElementException.class)
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public final WebElement getClickableElement() {
        var timeout = parseInt(ConfigurationHelper.getProperty("time.wait"));

        return new WebDriverWait(driver, timeout)
                .ignoring(NoSuchElementException.class)
                .until(ExpectedConditions.elementToBeClickable(locator));
    }
}
