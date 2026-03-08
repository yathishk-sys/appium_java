package base;

import io.appium.java_client.android.AndroidDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.DriverFactory;

public class BaseTest {

    protected AndroidDriver driver;

    @BeforeMethod
    public void setUp() throws Exception {

        driver = DriverFactory.createDriver();
    }

    @AfterMethod
    public void tearDown() {

        if (driver != null) {
            driver.quit();
        }
    }
}