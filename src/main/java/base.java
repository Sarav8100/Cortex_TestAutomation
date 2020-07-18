import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.util.concurrent.TimeUnit;
public class base {
    static WebDriver driver;

    /**
     * Method to start browser
     */
    public void startBrowser(String browserName) {
        try {
            switch(browserName)
            {
                case "chrome" :
                    String path = System.getProperty("user.dir")+"/src/main/resources/chromedriver.exe";
                    System.setProperty("webdriver.chrome.driver", path.replace("\\","/"));
                    ChromeOptions chromeOptions = new ChromeOptions();
                    chromeOptions.addArguments("start-maximized");
                    chromeOptions.addArguments("--dom-automation");
                    chromeOptions.addArguments("--safebrowsing-disable-download-protection");
                    driver = new ChromeDriver(chromeOptions);
                    break;

                case "IE" :
                    path = System.getProperty("user.dir")+"/src/main/resources/IEDriverServer.exe";
                    System.setProperty("webdriver.ie.driver", path.replace("\\","/"));
                    driver = new InternetExplorerDriver();
                    driver.manage().window().maximize();
                    break;

                case "firefox" :
                    path = System.getProperty("user.dir")+"/src/main/resources/geckodriver.exe";
                    System.setProperty("webdriver.gecko.driver",  path.replace("\\","/"));
                    driver = new FirefoxDriver();
                    driver.manage().window().maximize();
                    break;
                    default: path = System.getProperty("user.dir")+"/src/main/resources/chromedriver.exe";
                        System.setProperty("webdriver.chrome.driver", path.replace("\\","/"));
                        driver = new ChromeDriver();
                        driver.manage().window().maximize();
            }
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}