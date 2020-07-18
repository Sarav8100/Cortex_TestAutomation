import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

public class Gmail_InvalidUser extends base{
    @BeforeTest
    public void launchBrowser()
    {
        startBrowser("chrome");
    }

    //Test verifies error message for invalid user
    @Test
    public void verifyInvalidUser()
    {
        driver.navigate().to(TestInput.URL_GMAIL.getValue());
        GmailPage page = new GmailPage(driver);
        page.login(TestInput.INVALID_USERNAME.getValue(), TestInput.PASSWORD.getValue());

        Assertion assertion = new Assertion();
        assertion.assertEquals(page.errorMessage(), "Couldn't find your Google Account");
    }
    @AfterTest
    public void terminateBrowser()
    {
        driver.close();
    }
}
