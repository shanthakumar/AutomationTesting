package Utilities;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.util.ArrayList;

public class ExcelReader {

    XSSFWorkbook workbook;

    public ExcelReader() {

        try {
            String filePath = System.getProperty("user.dir") + "/src/main/resources/TestData.xlsx";
            FileInputStream file = new FileInputStream(filePath);
            workbook = new XSSFWorkbook(file);
        } catch (Exception e) {
            System.out.println("File error: " + e.getMessage());
        }
    }

    public String[] getData(String sheet) {
        XSSFSheet ws = workbook.getSheet(sheet);
        int rowCount = ws.getLastRowNum();
        System.out.println("Row count: " + rowCount); //2
        ArrayList<String> strings = new ArrayList<>();
        for (int i = 0; i < rowCount; i++) {
            String s = ws.getRow(i+1).getCell(0).toString();
            strings.add(s);
        }

        return strings.toArray(new String[0]);
    }
}
