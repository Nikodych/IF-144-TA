package Elements;

import Utils.Wait;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractElement {

    protected final Wait wait;
    private WebDriver driver;
    private final By locator;

    public AbstractElement(final WebDriver driver, final By locator) {
        this.driver = driver;
        this.locator = locator;
        wait = new Wait(driver, locator);
    }

    private List<WebElement> getPresentElements() {
        wait.getPresentElement();

        return driver.findElements(locator);
    }

    public boolean areElementsDisplayed() {
        List<WebElement> elements;

        try {
            elements = getPresentElements();
        } catch (TimeoutException e) {

            return false;
        }

        return !elements.isEmpty();
    }

    public WebElement getClickableElement() {

        return wait.getClickableElement();
    }

    public void clickElement() {
        var element = getClickableElement();
        element.click();
    }

    public String getAttribute(String attribute) {
        var element = wait.getPresentElement();

        return element.getAttribute(attribute);
    }

    public String getText() {
        var element = wait.getPresentElement();

        return element.getText();
    }

    public List<String> getTextOfElements() {
        var elements = getPresentElements();

        return elements.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }
}
