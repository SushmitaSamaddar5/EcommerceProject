package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage {

    public static WebDriver driver;

    public CartPage(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//div[text()=\"Prestige Stove\"]")
    public WebElement psNameOnCart;

    @FindBy(xpath = "//div[text()=\"14500\"]")
    public WebElement psPriceOnCart;

    @FindBy(xpath = "//div[text()=\"Remove\"]")
    public WebElement removeBtn;

    @FindBy(xpath = "//div[text()=\" Your cart is empty! \"]")
    public WebElement emptyCartText;

    @FindBy(xpath = "//div[text()=\"SG Bat\"]")
    public WebElement sgBat;

    @FindBy(xpath = "//div[text()=\"25500.00\"]")
    public WebElement sgBatPrice;

    @FindBy(xpath = "//a[text()=\"Checkout\"]")
    public WebElement checkoutBtn;

    @FindBy(xpath = "//div[text()=\"s21\"]")
    public WebElement s21;

    @FindBy(xpath = "//div[text()=\"65000\"]")
    public WebElement s21Price;
}
