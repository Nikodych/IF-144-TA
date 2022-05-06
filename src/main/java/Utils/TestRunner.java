package Utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class TestRunner {

    @Getter
    protected WebDriver driver;

    @BeforeMethod
    public final void setUp() {
        WebDriverManager.chromedriver().setup();
        ConfigurationHelper.getPropertiesFromConfigFile();

        ChromeOptions chromeOptions = new ChromeOptions();

        chromeOptions.addArguments("--start-" + ConfigurationHelper.getProperty("window.size"));

        driver = new ChromeDriver(chromeOptions);
    }

    @AfterMethod
    public final void tearDown() {
        driver.quit();
    }
}