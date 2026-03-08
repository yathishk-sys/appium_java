package tests;

import framework.driver.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BrowserStackSmokeTest extends BaseTest {

    @Test
    void shouldStartAppSessionOnBrowserStack() {
        Assertions.assertNotNull(driver.getSessionId(), "Session ID must be present");
        Assertions.assertFalse(driver.getPageSource().isBlank(), "Page source should not be blank");
    }
}
