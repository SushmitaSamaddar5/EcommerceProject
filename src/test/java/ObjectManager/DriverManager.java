package ObjectManager;

import DataProvider.ConfigFileReader;
import Utils.Logging;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.util.concurrent.TimeUnit;

public class DriverManager {

    public static WebDriver driver;

    public static WebDriver getDriver()
    {
        if(driver==null)
            createDriver();
        return driver;
    }

    public static void createDriver()
    {
        switch (ConfigFileReader.getBrowser().toUpperCase())
        {
            case "CHROME":
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                Logging.info("Chrome driver is created");
                break;
            case "FIREFOX":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                Logging.info("Firefox driver is created");
                break;
            case "EDGE":
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                Logging.info("Edge driver is created");
                break;
            case "SAFARI":
                WebDriverManager.safaridriver().setup();
                driver = new SafariDriver();
                Logging.info("Safari driver is created");
                break;
            default:
                System.out.println("no matching browser is found");
                Logging.info("no matching browser is found , we are closing the test execution run");
                System.exit(0);
        }

        driver.manage().timeouts().implicitlyWait(Long.parseLong(ConfigFileReader.getImplicitWait()), TimeUnit.SECONDS);
        Logging.info("waiting for page to load completely");
        driver.manage().timeouts().pageLoadTimeout(Long.parseLong(ConfigFileReader.getPageLoadTimeout()),TimeUnit.SECONDS);
        Logging.info("Page is loaded");
        driver.manage().window().maximize();
        Logging.info("Browser window is maximised");
    }
}
