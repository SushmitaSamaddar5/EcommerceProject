package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SportsPage {

    public static WebDriver driver;

    public SportsPage(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//input[@placeholder=\"Search by name...\"]")
    public WebElement searchBar;

    @FindBy(xpath = "//div[text()=\"SG Bat\"]")
    public WebElement sgBatProduct;

    @FindBy(xpath = "//div[text()=\"25500.00\"]")
    public WebElement sgBatPrice;
}
