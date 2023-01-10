package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ChallengingDOM {
    private final WebDriver driver;

    private final By header = By.xpath("//h3");

    public ChallengingDOM(WebDriver driver) {
        this.driver = driver;
    }

    public String getHeaderText() {
        return driver.findElement(header).getText();
    }
}
