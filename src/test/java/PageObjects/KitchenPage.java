package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class KitchenPage {

    public static WebDriver driver;

    public KitchenPage(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//input[@placeholder=\"Search by name...\"]")
    public WebElement searchBar;

    @FindBy(xpath = "//div[text()=\"Prestige Stove\"]")
    public WebElement psProduct;

    @FindBy(xpath = "//div[text()=\"14500.00\"]")
    public WebElement psPrice;

}
