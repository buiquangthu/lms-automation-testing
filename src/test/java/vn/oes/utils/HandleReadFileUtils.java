package vn.oes.utils;
import org.apache.poi.ss.usermodel.*;
import org.testng.annotations.DataProvider;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class HandleReadFileUtils {

    @DataProvider(name = "loginData")
    public Object[][] getLoginData() throws IOException {
        FileInputStream fis = new FileInputStream(new File("src/test/resources/testdata/dataImport/loginData.xlsx"));
        Workbook workbook= WorkbookFactory.create(fis);
        Sheet sheet= workbook.getSheetAt(0);

        int rowCount = sheet.getPhysicalNumberOfRows();
        int colCount = sheet.getRow(0).getPhysicalNumberOfCells();

        Object[][] data = new Object[rowCount - 1][colCount];

        for(int i = 1; i < rowCount; i++){

            Row row = sheet.getRow(i);
            for(int j = 0; j < colCount; j++){
                Cell cell = row.getCell(j);
                data[i - 1][j] = (cell != null) ? cell.toString().trim() : "";
            }
        }
        workbook.close();
        fis.close();
        return data;
    }

    @DataProvider(name = "TitleQuizz")
    public Object[][] getTitleQuizz() throws IOException {
        FileInputStream fis = new FileInputStream(new File("src/main/resources/testdata/checkQuizz.xlsx"));
        Workbook workbook= WorkbookFactory.create(fis);
        Sheet sheet= workbook.getSheetAt(0);

        int rowCount = sheet.getPhysicalNumberOfRows();
        int colCount = sheet.getRow(0).getPhysicalNumberOfCells();

        Object[][] data = new Object[rowCount - 1][colCount];

        for(int i = 1; i < rowCount; i++){

            Row row = sheet.getRow(i);
            for(int j = 0; j < colCount; j++){
                Cell cell = row.getCell(j);
                data[i - 1][j] = (cell != null) ? cell.toString().trim() : "";
            }
        }
        workbook.close();
        fis.close();
        return data;
    }

}
