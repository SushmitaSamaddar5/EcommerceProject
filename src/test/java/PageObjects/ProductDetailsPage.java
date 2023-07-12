package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductDetailsPage {

    public static WebDriver driver;

    public  ProductDetailsPage(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy (xpath = "//div[text()=\"Prestige Stove\"]")
    public WebElement psName;

    @FindBy(xpath = "//div[text()=\"14500\"]")
    public WebElement psPrice;

    @FindBy(xpath = "//div[text()=\"Add To Cart\"]")
    public WebElement addToCartBtn;

    @FindBy(xpath = "//a[text()=\"Go To Cart\"]")
    public WebElement goToCartBtn;

    @FindBy(xpath = "//div[text()=\"SG Bat\"]")
    public WebElement sgBat;

    @FindBy(xpath = "//div[text()=\"25500\"]")
    public WebElement sgBatPrice;

    @FindBy(xpath = "//div[text()=\"s21\"]")
    public WebElement s21;

    @FindBy(xpath = "//div[text()=\"65000\"]")
    public WebElement s21Price;
}
