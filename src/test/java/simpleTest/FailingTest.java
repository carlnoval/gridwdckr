package simpleTest;

import baseTest.BaseTest;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import org.testng.annotations.Test;
import pages.ChallengingDOM;

import static org.testng.Assert.assertEquals;

public class FailingTest extends BaseTest {
    @Test
    @Description("This test is meant to fail. There are two assertions, first assertion fails and the second one passes.")
    public void failingTest() {
        Allure.step("clickFormAuthentication");
        ChallengingDOM challengingDOMPage = basePage.clickChallengingDOM();

        Allure.step("Verify header text");
        //Expected needs to be 'Challenging DOM' for test to pass
        assertEquals(challengingDOMPage.getHeaderText(),"Challenging DOMx", "Do not fix failing test");
    }
}
