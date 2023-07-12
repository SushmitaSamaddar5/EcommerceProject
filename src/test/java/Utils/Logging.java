package Utils;


import org.apache.log4j.Logger;

public class Logging {

    private static Logger log = Logger.getLogger(Logging.class.getName());


    //log statements to start the test cases
    public static void startTestCase(String testCaseName)
    {
        log.info("########################################################################################");
        log.info("$$$$$$$$$$$$$$$$ Starting the Test Case "+testCaseName+"$$$$$$$$$$$$$");
        log.info("########################################################################################");
    }

    public static void endTestCase()
    {
        log.info("############################################ Test Case Ended ############################");
    }

    public static void info(String message)
    {
        log.info(message);
    }

    public static void warn(String message)
    {
        log.warn(message);
    }

    public static void debug(String message)
    {
        log.debug(message);
    }

    public static void fatal(String message)
    {
        log.fatal(message);
    }

    public static void error(String message)
    {
        log.error(message);
    }

    public static void trace(String message)
    {
        log.trace(message);
    }
}
