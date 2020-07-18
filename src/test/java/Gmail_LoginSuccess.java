import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

public class Gmail_LoginSuccess extends base {

    @BeforeTest
    public void launchBrowser()
    {
        startBrowser("chrome");
    }

    //Test verifies gmail login success
    @Test(priority = 0)
    public void verifyLoginSuccess()
    {
        driver.navigate().to(TestInput.URL_GMAIL.getValue());

        GmailPage page = new GmailPage(driver);
        Assertion assertion = new Assertion();
        assertion.assertTrue(page.login(TestInput.USERNAME.getValue(), TestInput.PASSWORD.getValue()));
    }

    //Test verifies if user profile icon is visible after successful login
    @Test(priority = 1)
    public void verifyProfileIcon()
    {
        GmailPage page = new GmailPage(driver);
        boolean isProfileIconVisible = page.verifyUserProfile(TestInput.USERNAME.getValue());
        Assertion assertion = new Assertion();
        assertion.assertTrue(isProfileIconVisible);

    }
    @AfterClass
    public void terminateBrowser()
    {
        driver.close();
    }
}