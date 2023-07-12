package Context;

import DataProvider.ReadWriteExcel;
import extentReport.ExtentReport;

import java.io.IOException;

public class TestContext {

    private ExtentReport extentReport;

    private ReadWriteExcel readWriteExcel;

    public TestContext() throws IOException
    {
       extentReport = new ExtentReport();
       readWriteExcel = new ReadWriteExcel();
    }

    public ExtentReport getExtentReport()
    {
        return extentReport;
    }

    public ReadWriteExcel getReadWriteExcel()
    {
        return readWriteExcel;
    }



}
