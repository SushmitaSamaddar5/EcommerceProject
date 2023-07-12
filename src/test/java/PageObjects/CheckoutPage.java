package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPage {

    public static WebDriver driver;

    public CheckoutPage(WebDriver driver)
    {
        this.driver =driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//div[text()=\"Confirm Payment\"]")
    public WebElement confirmPayment;

    @FindBy(xpath = "//div[text()=\"Order Confirmed\"]")
    public WebElement orderConfirmed;
}
