package Elements;

import Utils.InstanceDriver;
import Utils.DriverWait;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractBaseElement {

    private final By element;

    public AbstractBaseElement(By element) {
        this.element = element;
    }

    private List<WebElement> waitAndFindElements() {
        DriverWait.waitElement(element);

        return InstanceDriver
                .getDriver()
                .findElements(element);
    }

    public WebElement waitAndGetElement() {

        return DriverWait.waitElement(element);
    }

    public boolean isDisplayedElements() {
        List<WebElement> elements;

        try {
            elements = waitAndFindElements();
        } catch (TimeoutException e) {

            return false;
        }

        return !elements.isEmpty();
    }

    public void clickElement() {
        WebElement element = waitAndGetElement();
        element.click();
    }

    public String getAttribute(String attribute) {
        WebElement element = waitAndGetElement();

        return element.getAttribute(attribute);
    }

    public String getText() {
        WebElement element = waitAndGetElement();

        return element.getText();
    }

    public List<String> getTextOfElements() {
        List<WebElement> elements = waitAndFindElements();

        return elements.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }
}
