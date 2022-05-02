package Utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class InstanceDriver {

    private static WebDriver driver;
    private static InstanceDriver instance = null;

    private InstanceDriver() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--start-" + UtilsHelper.getProperty("window.size"));
        driver = new ChromeDriver(chromeOptions);
    }

    public static WebDriver getDriver() {
        if (instance == null) {
            instance = new InstanceDriver();
        }

        return driver;
    }

    public static void quit() {
        driver.quit();
        instance = null;
    }
}
