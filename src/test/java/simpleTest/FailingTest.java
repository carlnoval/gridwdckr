package simpleTest;

import baseTest.BaseTest;
import org.testng.annotations.Test;
import pages.ChallengingDOM;

import static org.testng.Assert.assertEquals;

public class FailingTest extends BaseTest {
    @Test
    public void failingTest() {
        ChallengingDOM challengingDOMPage = basePage.clickChallengingDOM();
        //Expected needs to be 'qux' for test to pass
        assertEquals(challengingDOMPage.getAlertButtonText(),"quz", "Do not fix failing test");
    }
}
