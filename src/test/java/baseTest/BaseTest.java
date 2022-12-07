package baseTest;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import pages.BasePage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class BaseTest {
    private WebDriver driver;
    protected BasePage basePage;                                // protected so subclass and package can access

    // always executes before the class of the test
    @BeforeClass
    public void webDriverInit() {
        String SELENIUM_KEY = "webdriver.chrome.driver";        // key that selenium will look for
        String CHROME_DRIVER_PATH = "resources/chromedriver";   // chromedriver107 path from project root, chromedriver107.exe for windows
        System.setProperty(SELENIUM_KEY, CHROME_DRIVER_PATH);   // System.setProperty allows the Selenium WebDriver framework to know which driver to use for automation

        driver = new ChromeDriver(getChromeOptions());          // instantiating a new webdriver with browser options, browser options is optional

        // implicit wait, enable if needed
        // webdriver keeps X Amount Of Time allowance for the element to be found
        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        basePage = new BasePage(driver);                        // homepage object that subclasses and package can access
    }

    // always executes before every @Test annotated methods
    // every test assertion starts with this page
    @BeforeMethod
    public void goToHomePage() {
        String URL_HOME_PAGE = "https://the-internet.herokuapp.com/";
        driver.get(URL_HOME_PAGE);
    }

    // always executes after every @Test annotated methods
    @AfterMethod
    public void screenShotOnFail(ITestResult testResults) {     // ITestResult gets automatically passed to the method
        if (ITestResult.FAILURE == testResults.getStatus()) {
            TakesScreenshot ss = (TakesScreenshot)driver;       // take a screenshot if test failed
            File ssFile = ss.getScreenshotAs(OutputType.FILE);  // declaring screenshot as a file object
            try {
                // moving screenshot to desired path
                String desiredSSPath = "resources/screenshots/" + testResults.getName() + ".png";
                Files.move(ssFile.toPath(), Path.of(desiredSSPath));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // always executes before the class of the test
    @AfterClass
    public void webDriverClose() {
        driver.quit();
    }

    private ChromeOptions getChromeOptions() {
        ChromeOptions myOptions = new ChromeOptions();
        //myOptions.setHeadless(true);
        myOptions.setExperimentalOption("excludeSwitches", List.of("enable-automation"));   // removes banner: "Chrome is being controlled by automated test software."
        return myOptions;
    }
}
