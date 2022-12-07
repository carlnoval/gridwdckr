package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SecureAreaPage {
    public final WebDriver driver;

    public final By statusAlert = By.id("flash");

    public SecureAreaPage(WebDriver driver){
        this.driver = driver;
    }

    public String getAlertText() {
        // original string has multi line, desired text is on the first line
        return driver.findElement(statusAlert).getText().split("\n")[0];
    }
}