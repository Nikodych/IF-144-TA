package Utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DriverWait {

    private static WebDriverWait driverWait;

    private static final int timeWait = Integer.parseInt(UtilsHelper.getProperty("time.wait"));

    public static WebElement waitElement(By locator) {
        createWebDriverWait();

        return driverWait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    private static void createWebDriverWait() {
        WebDriver driver = InstanceDriver.getDriver();
        if (driverWait == null)
            driverWait = new WebDriverWait(driver, timeWait);
    }

    public static void resetDriverWait() {
        driverWait = null;
    }
}
