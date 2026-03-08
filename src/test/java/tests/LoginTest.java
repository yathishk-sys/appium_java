
package tests;

import base.BaseTest;
import io.appium.java_client.AppiumBy;
// import io.appium.java_client.android.UiSelector;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.interactions.Pause;

import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class LoginTest extends BaseTest {

    @Test
    public void launchAppTest() {

        System.out.println("App launched successfully on BrowserStack");
        // Using the driver instance
        // String pageSource = driver.getPageSource();
        // System.out.println("Page source length: " + pageSource.length());
        driver.findElement(AppiumBy.xpath("//android.widget.EditText[@content-desc=\"test-Username\"]"))
      .sendKeys("standard_user");

driver.findElement(AppiumBy.xpath("//android.widget.EditText[@content-desc=\"test-Password\"]"))
      .sendKeys("secret_sauce");

driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text=\"LOGIN\"]"))
      .click();
      WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
      String title = wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.TextView[@text=\"PRODUCTS\"]")))
                     .getText();

System.out.println("Page title: " + title);
            driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"Sauce Labs Backpack\")"))
      .click();
      wait.until(
          ExpectedConditions.presenceOfElementLocated(AppiumBy.androidUIAutomator("new UiSelector().text(\"Sauce Labs Backpack\")")))     ;
      
          PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
          Sequence sequence = new Sequence(finger, 0)
                    .addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), 0, 0))
                    .addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                    .addAction(new Pause(finger, Duration.ofSeconds(1)))
                    .addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), 100, 100))
                    .addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
      //     ((AppiumDriver<?>) driver).perform(Arrays.asList(sequence));
      }
}