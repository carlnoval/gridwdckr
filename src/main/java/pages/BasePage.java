package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BasePage {
    private final WebDriver driver;

    public BasePage(WebDriver driver){
        this.driver = driver;
    }

    public LoginPage clickFormAuthentication() {
        clickLink("Form Authentication");
        return new LoginPage(driver);
    }

    public KeyPressesPage clickKeyPressesPage() {
        clickLink("Key Presses");
        return new KeyPressesPage(driver);
    }

    // helper method prevents repetition of multiple locators
    private void clickLink(String linkText) {
        driver.findElement(By.linkText(linkText)).click();
    }
}
