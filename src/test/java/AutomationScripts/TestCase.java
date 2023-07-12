package AutomationScripts;

import Context.TestContext;
import DataProvider.ConfigFileReader;
import DataProvider.ReadWriteExcel;
import ObjectManager.DriverManager;
import PageObjects.*;
import Utils.Logging;
import Utils.Utility;
import extentReport.ExtentReport;
import org.apache.log4j.PropertyConfigurator;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.util.concurrent.Callable;

public class TestCase {

    WebDriver driver;
    ExtentReport extentReport;
    TestContext testContext;
    ReadWriteExcel readWriteExcel;
    LoginPage loginPage;
    HomePage homePage;
    ElectronicsPage electronicsPage;
    KitchenPage kitchenPage;
    SportsPage sportsPage;
    ProductDetailsPage productDetailsPage;
    CartPage cartPage;
    CheckoutPage checkoutPage;
    SoftAssert softAssert;

    @BeforeSuite
    public void beforeSuiteSetup() throws IOException
    {
        driver = DriverManager.getDriver();
        driver.get(ConfigFileReader.getUrl());
        extentReport = new ExtentReport();
        testContext = new TestContext();
        readWriteExcel = new ReadWriteExcel();
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
        electronicsPage = new ElectronicsPage(driver);
        kitchenPage = new KitchenPage(driver);
        sportsPage = new SportsPage(driver);
        productDetailsPage = new ProductDetailsPage(driver);
        cartPage = new CartPage(driver);
        checkoutPage = new CheckoutPage(driver);
        softAssert = new SoftAssert();
        PropertyConfigurator.configure("src/main/resources/log4j.properties");
    }

    @AfterSuite
    public void afterSuiteTearDown()
    {
        softAssert.assertAll();
        extentReport.flush();
    }

    @BeforeMethod
    public void startTest()
    {
        Logging.info("Stating the test case execution");
    }

    @AfterMethod
    public void closeTest()
    {
        Logging.info("Ending the test case execution");
        if((driver.findElements(By.xpath("//div[text()=\"Logout\"]"))).size()>0)
        {
            loginPage.logoutBtn.click();
            Logging.info("Clicked on Logout Button");
        }
        else
        {
           Logging.info("Logout Button is not displayed");
        }
    }

    @Test(description = "TC01-verify the login flow with blank username and password",priority = 1)
    public void loginTestWithBlankCredentials() throws IOException
    {
        extentReport.createTest("TC01-verify the login flow with blank username and password");
        homePage.loginLink.click();
        extentReport.info("User clicked on the login link");
        Logging.info("User clicked on the login link");
        loginPage.loginBtn.click();
        extentReport.info("Clicked on login button present in login page");
        Logging.info("Clicked on login button present in login page");

        if((driver.findElements(By.xpath("//div[text()=\"Username and Password are required!!\"]")).size())>0)
        {
            String errorText=loginPage.errorMessage.getText();
            extentReport.info("Error message : Username and Password are required !! is displayed");
            Logging.info("Error message : Username and Password are required !! is displayed");
            softAssert.assertEquals(errorText,"Username and Password are required!!");
            extentReport.pass("Error message should be displayed when user login without username and password");
            extentReport.addScreenshot(driver);
            Logging.info("Error message should be displayed when user login without username and password");
            Logging.endTestCase();
        }
        else
        {
            extentReport.fail("Username and Password are required!! is not displayed");
            extentReport.addScreenshot(driver);
            Logging.error("Username and Password are required!! is not displayed");
            Logging.endTestCase();
        }
    }

    @Test(description = "TC02-validate the login functionality with valid user name and password",priority = 2)
    public void loginTestWithValidCredentials() throws IOException
    {
        extentReport.createTest("TC02-validate the login functionality with valid user name and password");
        homePage.loginLink.click();
        extentReport.info("user clicked on login link");
        Logging.info("user clicked on login link");
        loginPage.userNameField.sendKeys(ConfigFileReader.getUsername());
        extentReport.info("user entered the user name");
        Logging.info("user entered the user name");
        loginPage.passwordField.sendKeys(ConfigFileReader.getPassword());
        extentReport.info("user entered the password");
        Logging.info("user entered the password");
        loginPage.loginBtn.click();
        ConfigFileReader.getImplicitWait();
        ConfigFileReader.getPageLoadTimeout();
        extentReport.info("waiting for page to load completely");
        Logging.info("waiting for page to load completely");
        extentReport.info("user clicked on login button");
        Logging.info("user clicked on login button");

        if(loginPage.logoutBtn.isDisplayed())
        {
            extentReport.info("logout button is displayed");
            Logging.info("logout button is displayed");
            extentReport.pass("user logged in successfully");
            Logging.info("user logged in successfully");
        }
        else
        {
            extentReport.fail("logout button is not displayed , user is not logged in");
            Logging.error("logout button is not displayed , user is not logged in");
        }
        extentReport.addScreenshot(driver);
        Logging.endTestCase();
    }

    @Test(description = "TC03-validate the login functionality with valid data that is taken from excel sheet",priority = 3)
    public void loginTestWithExcelData() throws IOException
    {
        extentReport.createTest("TC03-validate the login functionality with valid data that is taken from excel sheet");
        XSSFSheet sheet1 = readWriteExcel.getXssfSheet("Sheet2");
        for(int i=1;i<=sheet1.getLastRowNum();i++)
        {
            homePage.loginLink.click();
            extentReport.info("clicked on login link");
            Logging.info("clicked on login link");
            loginPage.userNameField.sendKeys(sheet1.getRow(i).getCell(0).getStringCellValue());
            extentReport.info("user entered the username");
            Logging.info("user entered the username");
            loginPage.passwordField.sendKeys(sheet1.getRow(i).getCell(1).getStringCellValue());
            extentReport.info("user entered the password");
            Logging.info("user entered the password");
            loginPage.loginBtn.click();
            extentReport.info("user clicked on login button");
            Logging.info("user clicked on login button");
            ConfigFileReader.getImplicitWait();
            ConfigFileReader.getPageLoadTimeout();

            if(loginPage.logoutBtn.isDisplayed())
            {
                extentReport.pass("user logged in successfully");
                Logging.info("user logged in successfully");
                extentReport.addScreenshot(driver);
            }
            else
            {
                extentReport.fail("user is not logged in");
                Logging.info("user is not logged in");
                extentReport.addScreenshot(driver);
            }
        }
    }

    @Test(description = "TC04-search the product category wise and compare the price",priority = 4)
    public void searchProductTest() throws IOException
    {
        Double expectedPrice,actualPrice;
        extentReport.createTest("TC04-search the product category wise and compare the price");
        XSSFSheet sheet = readWriteExcel.getXssfSheet("Sheet1");
        for(int i=1;i<= sheet.getLastRowNum();i++)
        {
            switch (sheet.getRow(i).getCell(0).getStringCellValue())
            {
                case "Electronics":
                    homePage.electronicsLink.click();
                    extentReport.info("Clicked on electronics category");
                    Logging.info("Clicked on electronics category");
                    ConfigFileReader.getPageLoadTimeout();

                    electronicsPage.searchBar.sendKeys(sheet.getRow(i).getCell(1).getStringCellValue());
                    extentReport.info("Searched the electronic product");
                    Logging.info("searched the electronic product");
                    electronicsPage.searchBar.click();

                    String actualProductName = electronicsPage.s21Product.getText();
                    String expectedProductName = sheet.getRow(i).getCell(1).getStringCellValue();
                    actualPrice = Double.parseDouble(electronicsPage.s21Price.getText());
                    expectedPrice = sheet.getRow(i).getCell(2).getNumericCellValue();

                    if(actualProductName.equals(expectedProductName))
                    {
                        extentReport.info("product name is similar");
                        Logging.info("product name is similar");

                        softAssert.assertEquals(expectedPrice, actualPrice, "prices are not equal");
                        if(actualPrice.equals(expectedPrice))
                        {
                            extentReport.info("compared the prices , it is equal");
                            Logging.info("compared the prices , it is equal");
                            extentReport.pass("able to search electronics product");
                            extentReport.addScreenshot(driver);
                            Logging.info("able to search electronics product");
                        }
                    }
                    else
                    {
                        extentReport.info("compared the prices , it is not equal");
                        Logging.info("compared the prices , it is not equal");
                        extentReport.fail("not able to search electronics product");
                        extentReport.addScreenshot(driver);
                        Logging.info("not able to search electronics product");
                    }
                    break;

                case "Kitchen Items":
                    homePage.kitchenItems.click();
                    extentReport.info("Clicked on kitchen items link");
                    Logging.info("Clicked on kitchen items link");
                    ConfigFileReader.getPageLoadTimeout();
                    kitchenPage.searchBar.sendKeys(sheet.getRow(i).getCell(1).getStringCellValue());
                    kitchenPage.searchBar.click();
                    extentReport.info("searched the required product");
                    Logging.info("searched the required product");

                    String actualProduct = kitchenPage.psProduct.getText();
                    String expectedProduct = sheet.getRow(i).getCell(1).getStringCellValue();
                    Double actualPrice1 = Double.parseDouble(kitchenPage.psPrice.getText());
                    Double expectedPrice1 =sheet.getRow(i).getCell(2).getNumericCellValue();

                    if(actualProduct.equals(expectedProduct))
                    {
                        extentReport.info("product name is similar");
                        Logging.info("product name is similar");
                        softAssert.assertEquals(actualPrice1, expectedPrice1, "prices not equal for kitchen product");
                        if(actualPrice1.equals(expectedPrice1))
                        {
                            extentReport.info("actual price and expected price are same");
                            Logging.info("actual price and expected price are same");
                            extentReport.pass("able to search kitchen items");
                            extentReport.addScreenshot(driver);
                            Logging.info("able to search kitchen items");
                        }
                    }
                    else
                    {
                        extentReport.info("compared the prices , that is not equal");
                        Logging.info("compared the prices , that is not equal");
                        extentReport.fail("unable to search kitchen items");
                        extentReport.addScreenshot(driver);
                        Logging.error("unable to search kitchen items");
                    }
                    break;

                case "Sports":
                    homePage.sports.click();
                    extentReport.info("clicked on sports link");
                    Logging.info("clicked on sports link");
                    ConfigFileReader.getPageLoadTimeout();
                    sportsPage.searchBar.sendKeys(sheet.getRow(i).getCell(1).getStringCellValue());
                    sportsPage.searchBar.click();
                    extentReport.info("searched the sports product");
                    Logging.info("searched the sports product");

                    String actualSportProduct = sportsPage.sgBatProduct.getText();
                    String expectedSportProduct = sheet.getRow(i).getCell(1).getStringCellValue();
                    Double actualProductPrice = Double.parseDouble(sportsPage.sgBatPrice.getText());
                    Double expectedProductPrice = sheet.getRow(i).getCell(2).getNumericCellValue();

                    if(actualSportProduct.equals(expectedSportProduct))
                    {
                        extentReport.info("actual and expected product name are same");
                        Logging.info("actual and expected product name are same");
                        softAssert.assertEquals(actualProductPrice, expectedProductPrice, "product prices are not same");
                        if(actualProductPrice.equals(expectedProductPrice))
                        {
                            extentReport.info("compared the prices , it is equal");
                            Logging.info("compared the prices , it is equal");
                            extentReport.pass("able to search the sports product");
                            extentReport.addScreenshot(driver);
                            Logging.info("able to search the sports product");
                        }
                    }
                    else
                    {
                        extentReport.info("compared the prices , it is not equal");
                        Logging.info("compared the prices , it is  not equal");
                        extentReport.fail("not able to search the sports product");
                        extentReport.addScreenshot(driver);
                        Logging.info("not able to search the sports product");
                    }
                    break;

                default:
                    extentReport.info("no matching category is found");
                    Logging.info("no matching category is found");
                    extentReport.fail("matching category is not found , unable to search the product");
                    Logging.info("matching category is not found , unable to search the product");
                    extentReport.addScreenshot(driver);
            }

            driver.get(ConfigFileReader.getUrl());
        }
        Logging.endTestCase();
    }

    @Test(description = "TC05-add item into cart and verify the details",priority = 5)
    public void addItemToCart() throws IOException
    {
        extentReport.createTest("TC05-add item into cart and verify the details");
        extentReport.info("user is giving details to login into site");
        Logging.info("user is giving details to login into site");
        Utility.login(driver);
        extentReport.info("user is logged in");
        Logging.info("user is logged in");
        extentReport.info("clicking on kitchen category");
        Logging.info("clicking on kitchen category");
        homePage.kitchenItems.click();
        kitchenPage.psProduct.click();
        extentReport.info("clicked on prestige stove");
        Logging.info("clicked on prestige stove");
        ConfigFileReader.getPageLoadTimeout();

        String productNameOnDetailPage = productDetailsPage.psName.getText();
        String productPriceOnDetailPage =productDetailsPage.psPrice.getText();
        productDetailsPage.addToCartBtn.click();
        extentReport.info("clicked on add to cart button");
        Logging.info("clicked on add to cart button");
        ConfigFileReader.getPageLoadTimeout();
        productDetailsPage.goToCartBtn.click();
        extentReport.info("clicked on go to cart button");
        Logging.info("clicked on go to cart button");
        ConfigFileReader.getPageLoadTimeout();

        String productNameOnCartPage = cartPage.psNameOnCart.getText();
        String productPriceOnCartPage = cartPage.psPriceOnCart.getText();
        if(productNameOnCartPage.equals(productNameOnDetailPage))
        {
            extentReport.info("product name are same on cart page and product details page");
            Logging.info("product name are same on cart page and product details page");

            if(productPriceOnCartPage.equals(productPriceOnDetailPage))
            {
                extentReport.info("product prices are same on product details page and cart page");
                Logging.info("product prices are same on product details page and cart page");
                extentReport.pass("product details are similar on product details page and cart page");
                Logging.info("product details are similar on product details page and cart page");
                extentReport.addScreenshot(driver);
                Logging.endTestCase();
            }
            else
            {
                extentReport.info("product prices are not same on product details page and cart page");
                Logging.info("product prices are not same on product details page and cart page");
                extentReport.fail("product details are not similar on product details page and cart page");
                Logging.info("product details are not similar on product details page and cart page");
                extentReport.addScreenshot(driver);
                Logging.endTestCase();
            }
        }
        else
        {
            extentReport.info("product name are not same on product details page and cart page");
            Logging.error("product name are not same on product details page and cart page");
            extentReport.fail("product name are not similar on product details page and cart page");
            Logging.error("product name are not similar on product details page and cart page");
            extentReport.addScreenshot(driver);
            Logging.endTestCase();
        }
    }

    @Test(description = "TC06-remove item into cart and verify the cart",priority = 6)
    public void removeItemTest() throws IOException
    {
        extentReport.createTest("TC06-remove item into cart and verify the cart");
        extentReport.info("user is entering user credentials on login page");
        Logging.info("user is entering user credentials on login page");
        Utility.login(driver);
        extentReport.info("user is logged in");
        Logging.info("user is logged in");
        homePage.sports.click();
        extentReport.info("user navigated to sports category");
        Logging.info("user navigated to sports category");
        sportsPage.sgBatProduct.click();
        extentReport.info("user clicked on sports product");
        Logging.info("user clicked on sports product");

        String productNameOnDetailPage = productDetailsPage.sgBat.getText();
        String productPriceOnDetailPage = productDetailsPage.sgBatPrice.getText();
        productDetailsPage.addToCartBtn.click();
        extentReport.info("user clicked on add to cart button");
        Logging.info("user clicked on add to cart button");
        productDetailsPage.goToCartBtn.click();
        extentReport.info("user clicked on go to cart button");
        Logging.info("user clicked on go to cart button");

        String productNameOnCartPage = cartPage.sgBat.getText();
        String productPriceOnCartPage = cartPage.sgBatPrice.getText();

        if(productNameOnDetailPage.equals(productNameOnCartPage))
        {
           extentReport.info("product name are equal on product details page and product cart page");
           Logging.info("product name are equal on product details page and product cart page");

           if(productPriceOnDetailPage.equals(productPriceOnCartPage))
           {
               extentReport.info("product price are equal on product details page and product cart page");
               Logging.info("product price are equal on product details page and product cart page");
           }
           else
           {
               extentReport.info("product prices are not equal on product details page and product cart page");
               Logging.info("product prices are not equal on product details page and product cart page");
           }
        }
        else
        {
            extentReport.info("product name are not equal on product details page and product cart page");
            Logging.info("product name are not equal on product details page and product cart page");
        }

        cartPage.removeBtn.click();
        extentReport.info("clicked on remove button");
        Logging.info("clicked on remove button");

        if(cartPage.emptyCartText.isDisplayed())
        {
            String emptyCartMessage = cartPage.emptyCartText.getText();

            softAssert.assertEquals(emptyCartMessage,"Your cart is empty!","empty cart text is not same");
            extentReport.info("cart is empty after removing the product");
            Logging.info("user is able to remove the item from cart");
            extentReport.pass("user is able to remove the item from cart");
            extentReport.addScreenshot(driver);
            Logging.endTestCase();
        }
        else
        {
            extentReport.info("cart is not empty after removing the product");
            extentReport.fail("user is not able to remove the item from cart");
            Logging.info("user is not able to remove the item from cart");
            extentReport.addScreenshot(driver);
            Logging.endTestCase();
        }
    }

    @Test(description = "TC07-place order on electronic item",priority = 7)
    public void placeOrderTest() throws IOException
    {
        extentReport.createTest("TC07-place order on electronic item");
        extentReport.info("user is entering his credentials to login into site");
        Logging.info("user is entering his credentials to login into site");
        Utility.login(driver);
        extentReport.info("user is logged in");
        Logging.info("user is logged in");
        homePage.electronicsLink.click();
        ConfigFileReader.getPageLoadTimeout();
        extentReport.info("user clicked on electronics link");
        Logging.info("user clicked on electronics link");
        electronicsPage.s21Product.click();
        ConfigFileReader.getPageLoadTimeout();
        extentReport.info("user clicked on electronic product");
        Logging.info("user clicked on electronic product");

        String productNameOnDetailPage = productDetailsPage.s21.getText();
        String productPriceOnDetailPage = productDetailsPage.s21Price.getText();
        productDetailsPage.addToCartBtn.click();
        ConfigFileReader.getPageLoadTimeout();

        extentReport.info("user added item into cart");
        Logging.info("user added item into cart");
        productDetailsPage.goToCartBtn.click();
        ConfigFileReader.getPageLoadTimeout();

        extentReport.info("user clicked on go to cart button");
        Logging.info("user clicked on go to cart button");
        String productNameOnCartPage = cartPage.s21.getText();
        String productPriceOnCartPage = cartPage.s21Price.getText();

        if(productNameOnDetailPage.equals(productNameOnCartPage))
        {
            extentReport.info("product name are similar on product detail page and product cart page");
            Logging.info("product name are similar on product detail page and product cart page");

            if(productPriceOnDetailPage.equals(productPriceOnCartPage))
            {
                extentReport.info("product prices are same on product detail page and product cart page");
                Logging.info("product prices are same on product detail page and product cart page");
            }
            else
            {
                extentReport.info("product prices are not same on product detail page and product cart page");
                Logging.info("product prices are not same on product detail page and product cart page");
            }
        }
        else
        {
            extentReport.info("product name are not similar on product detail page and product cart page");
            Logging.info("product name are not similar on product detail page and product cart page");
        }

        cartPage.checkoutBtn.click();
        ConfigFileReader.getPageLoadTimeout();
        extentReport.info("clicked on checkout button");
        Logging.info("clicked on checkout button");

        checkoutPage.confirmPayment.click();
        ConfigFileReader.getPageLoadTimeout();
        extentReport.info("user clicked on confirm payment button");
        Logging.info("user clicked on confirm payment button");

        if(checkoutPage.orderConfirmed.isDisplayed())
        {
            String orderConfirmMessage = checkoutPage.orderConfirmed.getText();
            softAssert.assertEquals(orderConfirmMessage,"Order Confirmed","order confirmed message is not matching");
            extentReport.info("order is confirmed");
            Logging.info("order is confirmed");
            extentReport.pass("placed the order and received the confirmation message");
            extentReport.addScreenshot(driver);
            Logging.endTestCase();
        }
        else
        {
            extentReport.info("order is not confirmed or didn't receive the message");
            Logging.info("order is not confirmed or didn't receive the message");
            extentReport.fail("unable to place the order and didn't received the confirmation message");
            extentReport.addScreenshot(driver);
            Logging.endTestCase();
        }
    }
}
