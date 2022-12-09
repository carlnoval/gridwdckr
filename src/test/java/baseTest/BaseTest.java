package baseTest;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.Browser;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import pages.BasePage;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class BaseTest {
    private WebDriver driver;
    protected BasePage basePage;    // protected so subclass and package can access

    // using selenium grid
    @BeforeClass                    // always executes before the class of the test
    @Parameters({"browser"})        // "browser" will be set from testng.xml, gets stored in browserType
    public void webDriverInit(String browserType) throws MalformedURLException {
        DesiredCapabilities browserSetup = new DesiredCapabilities();

        switch (browserType) {
            case "chrome" -> {
                browserSetup.setBrowserName(Browser.CHROME.browserName());
                System.out.println("########## Test will now run on: " + Browser.CHROME.browserName() + " ##########");
            }
            case "firefox" -> {
                browserSetup.setBrowserName(Browser.FIREFOX.browserName());
                System.out.println("########## Test will now run on: " + Browser.FIREFOX.browserName() + " ##########");
            }
            case "edge" -> {
                browserSetup.setBrowserName(Browser.EDGE.browserName());
                System.out.println("########## Test will now run on: " + Browser.EDGE.browserName() + " ##########");
            }
            default -> System.out.println("########## there is something wrong with `browserType` ##########");
        }

        driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), browserSetup);
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
        System.out.println("########## Class test completed, diver.quit() has completed ##########");
    }

    private ChromeOptions getChromeOptions() {
        ChromeOptions myOptions = new ChromeOptions();
        //myOptions.setHeadless(true);
        myOptions.setExperimentalOption("excludeSwitches", List.of("enable-automation"));   // removes banner: "Chrome is being controlled by automated test software."
        return myOptions;
    }
}
