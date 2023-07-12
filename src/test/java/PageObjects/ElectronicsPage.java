package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ElectronicsPage {

    public static WebDriver driver;

    public ElectronicsPage(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//input[@placeholder=\"Search by name...\"]")
    public WebElement searchBar;

    @FindBy(xpath = "//div[text()=\"s21\"]")
    public WebElement s21Product;

    @FindBy(xpath = "//div[text()=\"65000.00\"]")
    public WebElement s21Price;

}
