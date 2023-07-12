package Utils;

import DataProvider.ConfigFileReader;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

public class Utility {

    public static String captureScreenshot(WebDriver driver) throws IOException
    {
        TakesScreenshot ts = (TakesScreenshot)driver;
        File srcfile = ts.getScreenshotAs(OutputType.FILE);
        File destfile = new File("Screenshots/"+System.currentTimeMillis()+".png");
        FileUtils.copyFile(srcfile,destfile);
        return destfile.getAbsolutePath();
    }

    public static void login(WebDriver driver)
    {
        driver.findElement(By.xpath("//a[text()=\"Login\"]")).click();
        driver.findElement(By.xpath("// input[@id=\"username\"]")).sendKeys(ConfigFileReader.getUsername());
        driver.findElement(By.xpath("// input[@id=\"password\"]")).sendKeys(ConfigFileReader.getPassword());
        driver.findElement(By.xpath("//input[@value=\"Log In\"]")).click();
    }
}
