package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    public static WebDriver driver;

    public LoginPage(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//h2[text()=\"Welcome to InstaShop\"]")
    public WebElement welcomeText;

    @FindBy(xpath = "//input[@id=\"username\"]")
    public WebElement userNameField;

    @FindBy(xpath = "//input[@id=\"password\"]")
    public WebElement passwordField;

    @FindBy(xpath = "//input[@value=\"Log In\"]")
    public WebElement loginBtn;

    @FindBy(xpath = "//div[text()=\"Username and Password are required!!\"]")
    public WebElement errorMessage;

    @FindBy(xpath = "//div[text()=\"Logout\"]")
    public WebElement logoutBtn;

}
