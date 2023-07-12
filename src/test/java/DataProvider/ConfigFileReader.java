package DataProvider;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigFileReader {

    public static Properties prop;

    private static final String ConfigFilePath = "src/main/resources/config.properties";

    static
    {
        BufferedReader bufferedReader;
        try
        {
            bufferedReader = new BufferedReader(new FileReader(ConfigFilePath));

            prop=new Properties();

            try {
                prop.load(bufferedReader);
                bufferedReader.close();
            }
            catch (IOException ioException)
            {
                System.out.println("Exception while reading the file : "+ioException);
            }
        }
        catch (FileNotFoundException fileNotFoundException)
        {
            System.out.println("file is not present at the defined location : "+fileNotFoundException);
        }
    }

    public static String getUrl()
    {
        String siteUrl = prop.getProperty("url");
        if(siteUrl!=null)
            return siteUrl;
        else
            throw new RuntimeException("url is not defined in the config.properties file");
    }

    public static String getMode()
    {
        String headlessModeValue = prop.getProperty("headless");
        if(headlessModeValue!=null)
            return headlessModeValue;
        else
            throw new RuntimeException("Headless mode is not defined into config.properties file");
    }

    public static String getBrowser()
    {
        String browser = prop.getProperty("browser");
        if(browser!=null)
            return browser;
        else
            throw new RuntimeException("Browser value is not defined into config.properties file");
    }

    public static String getImplicitWait()
    {
        String implicitWait = prop.getProperty("implicitWait");
        if(implicitWait!=null)
            return implicitWait;
        else
            throw new RuntimeException("ImplicitWait is not defined into config.propertied file");
    }

    public static String getPageLoadTimeout()
    {
        String pageLoadTimeOut = prop.getProperty("pageLoadTimeout");
        if(pageLoadTimeOut!=null)
            return pageLoadTimeOut;
        else
            throw new RuntimeException("PageLoadTimeout is not defined into config.properties file");
    }

    public static String getTestDataFilePath()
    {
        String testDataFilePath = prop.getProperty("testDataFiePath");
        if(testDataFilePath!=null)
            return testDataFilePath;
        else
            throw new RuntimeException("TestDataFilePath is not defined into config.properties file");
    }

    public static String getUsername()
    {
        String username = prop.getProperty("username");
        if(username!=null)
            return username;
        else
            throw new RuntimeException("username is not defined into config.properties file");
    }

    public static String getPassword()
    {
        String password = prop.getProperty("password");
        if(password!=null)
            return password;
        else
            throw new RuntimeException("password is not defined into config.properties file");
    }

}
