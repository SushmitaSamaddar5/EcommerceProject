package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

    public static WebDriver driver;

    public HomePage(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//a[text()=\"Login\"]")
    public WebElement loginLink;

    @FindBy(xpath = "//a[text()=\"Cart\"]")
    public WebElement cartLink;

    @FindBy(xpath = "//a[text()=\"All Products\"]")
    public WebElement allProductsLink;

    @FindBy(xpath = "//a[text()=\"Electronics\"]")
    public WebElement electronicsLink;

    @FindBy(xpath = "//a[text()=\"Kitchen Items\"]")
    public WebElement kitchenItems;

    @FindBy(xpath = "//a[text()=\"Sports\"]")
    public WebElement sports;

}
