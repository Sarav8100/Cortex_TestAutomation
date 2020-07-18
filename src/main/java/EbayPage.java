import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class EbayPage extends base{

    private WebDriver driver;

    @FindBy(css = "input[placeholder='Search for anything']")
    private WebElement ebaySearch;

    @FindBy(css = "input[type='submit']")
    private WebElement ebaySubmit;

    @FindBy(css = "li[class='s-item    s-item--watch-at-corner']")
    private List<WebElement> ebayItemList;

    @FindBy(id = "prcIsum")
    private WebElement ebayPriceUS;

    @FindBy(id = "convbinPrice")
    private WebElement ebayPriceINR;


    public EbayPage(){};
    public EbayPage(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /**
     * Method to verify search results
     * @param item item to be searched
     * @return true if search results are available
     */
    public boolean verifySearch(String item)
    {
        try
        {
            WebDriverWait wait = new WebDriverWait(driver, 20);
            wait.until(ExpectedConditions.
                    visibilityOfElementLocated(By.cssSelector(Locators.EBAY_SEARCH.getValue())));

            ebaySearch.sendKeys(item);
            ebaySubmit.click();

            wait.until(ExpectedConditions.
                    visibilityOfElementLocated(By.cssSelector(Locators.EBAY_ITEM_LIST.getValue())));

            return !ebayItemList.isEmpty();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Method to get price of the first item
     */
    List<String> itemPrice( )
    {
        List<String> itemPrices  = new ArrayList<>();
        try{
            if(!ebayItemList.isEmpty())
            {
                ebayItemList.get(0).findElement(By.cssSelector(Locators.EBAY_ITEM_SELECT.getValue())).click();

                WebDriverWait wait = new WebDriverWait(driver, 20);
                wait.until(ExpectedConditions.
                        visibilityOfElementLocated(By.id(Locators.EBAY_ITEM_PRICE_US.getValue())));

                String itemUS = ebayPriceUS.getText();
                String priceINR = ebayPriceINR.getText();

                itemPrices.add(itemUS);
                itemPrices.add(priceINR);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return itemPrices;
    }
}
