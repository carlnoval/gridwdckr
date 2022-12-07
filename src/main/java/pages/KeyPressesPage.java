package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class KeyPressesPage {
    private final WebDriver driver;

    private final By textField = By.id("target");
    private final By feedBackResult = By.id("result");

    public KeyPressesPage(WebDriver driver) {
        this.driver = driver;
    }

    public void sendKeyPresses(String str) {
        driver.findElement(textField).sendKeys(str);
    }

    public String getFeedbackResult() {
        return driver.findElement(feedBackResult).getText();
    }
}
