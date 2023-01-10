package utils.testListeners;

import baseTest.BaseTest;
import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.ByteArrayInputStream;

public class AllureFailedTestListener implements ITestListener {
    @Override
    public void onTestFailure(ITestResult testResult) {

        //Retrieve webdriver for screenshot
        Object testClass = testResult.getInstance();
        WebDriver driver = ((BaseTest) testClass).getDriver();

        TakesScreenshot ss = (TakesScreenshot)driver;                       // take AllureFailedTestListener screenshot if test failed
        String ssFileName = ss.getScreenshotAs(OutputType.FILE).getName();  // getting name of the file

        try {
            Allure.addAttachment(ssFileName, new ByteArrayInputStream(ss.getScreenshotAs(OutputType.BYTES)));
            Allure.step("Test failed, screenshot taken");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
