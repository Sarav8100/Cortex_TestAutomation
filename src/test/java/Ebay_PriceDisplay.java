import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

import java.util.List;

public class Ebay_PriceDisplay extends base{

    @BeforeTest
    public void launchBrowser()
    {
        startBrowser("chrome");
    }

    //Test verifies search results
    @Test(priority = 0)
    public void verifySearchResult()
    {
        driver.navigate().to(TestInput.EBAY_URL.getValue());

        EbayPage page = new EbayPage(driver);
        Assertion assertion = new Assertion();
        assertion.assertTrue(page.verifySearch(TestInput.EBAY_SEARCH_ITEM.getValue()));
    }

    //Test to get price of the first item from search result
    @Test(priority = 1)
    public void displayPrice()
    {
        EbayPage page = new EbayPage(driver);

        List<String> itemPrices = page.itemPrice();
        Assertion assertion = new Assertion();
        assertion.assertTrue(itemPrices.size() == 2);

        System.out.println("Item price in US currency: "+itemPrices.get(0));
        System.out.println("Item price in Indian currency: "+itemPrices.get(1));
    }
    @AfterClass
    public void terminateBrowser()
    {
        driver.close();
    }
}