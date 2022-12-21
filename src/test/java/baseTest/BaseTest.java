package baseTest;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.Browser;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BaseTest {
    private WebDriver driver;
    protected BasePage basePage;    // protected so subclass and package can access

    // using selenium grid
    @BeforeClass                    // always executes before the class of the test
    @Parameters({"browser"})        // "browser" will be set from testng.xml, gets stored in desiredBrowser
    public void webDriverInit(String desiredBrowser) throws MalformedURLException {
        MutableCapabilities browser = configureBrowser(desiredBrowser);
        driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), browser);
        basePage = new BasePage(driver);
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
            String browserName = ((RemoteWebDriver) driver).getCapabilities().getBrowserName(); // specify which browser ss came from
            System.out.println("########### Getting browser name: " + browserName);


            //WebDriver augmentedDriver = new Augmenter().augment(driver);
            //File screenshot = ((TakesScreenshot)augmentedDriver).getScreenshotAs(OutputType.FILE);
            //FileUtils.copyFile(screenshot, new File("D:\\screenshots\\remotescreenshot1.jpg"));

            WebDriver augmentedDriver = new Augmenter().augment(driver);
            System.out.println("########### Able to crete augmentedDriver");
            TakesScreenshot ss = (TakesScreenshot)augmentedDriver;          // take a screenshot
            File ssFile = ss.getScreenshotAs(OutputType.FILE);              // declaring screenshot as a file object
            System.out.println("########### Able to take a screenshot");
            try {
                System.out.println("########### Attempting to copy screenshot");
                // moving screenshot to desired path
                String desiredSSPath = "resources/screenshots/" + testResults.getName() + browserName +  ".png";
                //Files.move(ssFile.toPath(), Path.of(desiredSSPath));
                //Files.copy(ssFile.toPath(), Path.of(desiredSSPath));
                FileUtils.copyFile(ssFile, new File(desiredSSPath));
                System.out.println("########### Able to copy screenshot");
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

    private MutableCapabilities configureBrowser(String desiredBrowser) {
        MutableCapabilities browser = new MutableCapabilities();

        switch (desiredBrowser) {
            case "chrome" -> {
                String browserName = Browser.CHROME.browserName();
                browser.setCapability("browserName", browserName);
                browser.setCapability("goog:chromeOptions", getChromeOptions());
                System.out.println("########## Test will now run on: " + browserName + " ##########");
            }
            case "firefox" -> {
                String browserName = Browser.FIREFOX.browserName();
                browser.setCapability("browserName", browserName);
                browser.setCapability("moz:firefoxOptions", getFirefoxOptions());
                System.out.println("########## Test will now run on: " + browserName + " ##########");
            }
            default -> System.out.println("########## there is something wrong with the provided `desiredBrowser` from the .xml file ##########");
        }

        return browser;
    }

    private ChromeOptions getChromeOptions() {
        ChromeOptions desiredChromeOptions = new ChromeOptions();

        /*
        use any one of the next two lines when headless is needed

        desiredChromeOptions.setHeadless(true);
        desiredChromeOptions.addArguments("headless");
        */

        desiredChromeOptions.addArguments("start-maximized");
        desiredChromeOptions.setExperimentalOption("excludeSwitches", List.of("enable-automation"));    // removes banner: "Chrome is being controlled by automated test software."

        //Disable chrome from offering to save passwords
        Map<String, Object> preferences = new HashMap<>();
        preferences.put("credentials_enable_service", false);
        desiredChromeOptions.setExperimentalOption("prefs", preferences);

        return desiredChromeOptions;
    }

    private FirefoxOptions getFirefoxOptions() {
        FirefoxOptions desiredFirefoxOptions = new FirefoxOptions();

        /*
        use any one of the next two lines when headless is needed

        desiredFirefoxOptions.setHeadless(true);
        desiredFirefoxOptions.addArguments("-headless");
        */

        return desiredFirefoxOptions;
    }
}
