import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import org.openqa.selenium.*;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.util.List;

public class GmailPage extends base {

    private WebDriver driver;

    @FindBy(id = "identifierId")
    private WebElement userName;

    @FindBy(css = "input[name='password']")
    private WebElement passwordGmail;

    @FindBy(css = "input[class='whsOnd zHQkBf']")
    private List<WebElement> captchaField;

    @FindBy(css = "img[id='captchaimg']")
    private WebElement captchaImage;

    @FindBy(css = ".VfPpkd-vQzf8d")
    private List<WebElement> nextButton;

    @FindBy(css = "img[class='gb_Ka gbii']")
    private WebElement profileIcon;

    @FindBy(css = "div[class='gb_vb']")
    private WebElement profileName;

    @FindBy(id = "gb_71")
    private WebElement signOut;

    @FindBy(css = "div[class='o6cuMc']")
    private List<WebElement> errorFields;


    public GmailPage(){};
    public GmailPage(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    /**
     * Method to verify gmail login is success
     *
     * @param username user name to login to gmail
     * @param password password to login to gmail
     * @return true if login is success
     */
    public boolean login(String username, String password)
    {
       try
       {
           WebDriverWait wait = new WebDriverWait(driver, 20);
           wait.until(ExpectedConditions.
                   visibilityOfElementLocated(By.id(Locators.GMAIL_USERNAME.getValue())));

           userName.sendKeys(username);

           if(this.errorMessage() != null)
           {
               return false;
           }
           this.clickOnNext();

           if(captchaField.get(1) != null)
           {
               //code to enter captcha
               this.enterCaptcha();
               this.clickOnNext();
           }

           if(!passwordGmail.isDisplayed()){
               return false;
           }
           passwordGmail.sendKeys(password);
           this.clickOnNext();

           wait.until(ExpectedConditions.
                   visibilityOfElementLocated(By.cssSelector(Locators.GMAIL_PROFILE_ICON.getValue())));

           if(profileIcon != null)
            {
                profileIcon.isDisplayed();
                return true;
            }
       }
       catch(Exception e)
       {
           e.printStackTrace();
       }
        return false;
    }

    /**
     * Method to enter captcha while login to gmail
     */
    private void enterCaptcha()
    {
        try{
            //reading captha text
            File src = captchaImage.getScreenshotAs(OutputType.FILE);

            String path = System.getProperty("user.dir")+"/screenshots/captcha.png";
            FileHandler.copy(src, new File(path));

            ITesseract image = new Tesseract();
            String imageText = image.doOCR(new File(path));

            captchaField.get(1).sendKeys(imageText);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Method to click on next button while login to gmail
     */
    private void clickOnNext()
    {
        try{
            if(!nextButton.isEmpty())
            {
                for(WebElement button : nextButton)
                {
                    if(button.getAttribute("innerText").equals("Next"))
                    {
                        //click on next button
                        ((JavascriptExecutor) driver).executeScript("return arguments[0].click();", button);
                        break;
                    }
                }
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Method to verify user profile icon after login
     *
     * @param username user name to login to gmail
     * @return true if user profile is proper
     */
    public boolean verifyUserProfile(String username)
    {
        try
        {
            WebDriverWait wait = new WebDriverWait(driver, 20);
            wait.until(ExpectedConditions.
                visibilityOfElementLocated(By.cssSelector(Locators.GMAIL_PROFILE_ICON.getValue())));
            profileIcon.click();
            if(profileName.getText().equals(username))
            {
                return true;
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Method to sign out from gmail
     */
    void signOutGmail()
    {
        try{
            signOut.click();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Method to get error message
     */
    String errorMessage( )
    {
        try{
            for(WebElement error : errorFields)
            {
                if(!error.getText().equals(""))
                {
                    return error.getText();
                }
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
}