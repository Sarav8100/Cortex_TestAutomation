import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

public class Gmail_NoUserName extends base {
    @BeforeTest
    public void launchBrowser()
    {
        startBrowser("chrome");
    }

    //Test verifies error message when Username field is blank
    @Test
    public void verifyNoUser()
    {
        driver.navigate().to(TestInput.URL_GMAIL.getValue());

        GmailPage page = new GmailPage(driver);
        page.login(TestInput.USERNAME_BLANK.getValue(), TestInput.PASSWORD.getValue());

        Assertion assertion = new Assertion();
        assertion.assertEquals(page.errorMessage(), "Enter an email or phone number");
    }
    @AfterTest
    public void terminateBrowser()
    {
        driver.close();
    }
}
