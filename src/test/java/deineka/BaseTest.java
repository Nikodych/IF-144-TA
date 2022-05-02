package deineka;

import Utils.InstanceDriver;
import Utils.UtilsHelper;
import Utils.DriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
    @BeforeMethod
    public void setUp() {
        UtilsHelper.getPropertiesFromConfigFile();
    }

    @AfterMethod
    public void cleanUp() {
        InstanceDriver.quit();
        DriverWait.resetDriverWait();
    }
}