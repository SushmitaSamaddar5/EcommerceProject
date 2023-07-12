package DataProvider;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ReadWriteExcel {

    FileInputStream fis;
    XSSFWorkbook xssfWorkbook;
    XSSFSheet xssfSheet;

    public ReadWriteExcel() throws IOException {
        fis = new FileInputStream(new File(ConfigFileReader.getTestDataFilePath()));
        xssfWorkbook =new XSSFWorkbook(fis);

    }

    public XSSFSheet getXssfSheet(String sheetName)
    {
        xssfSheet = xssfWorkbook.getSheet(sheetName);
        return xssfSheet;
    }
}
