package com.example.assignment2;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
public class WriteDataToExcel {
    // any exceptions need to be caught
    Map<String, Object[]> furnitureStoresData
            = new TreeMap<String, Object[]>();
    public void storeData(int id,String website,String name) throws Exception
    {

        // This data needs to be written (Object[])
        furnitureStoresData.put(
                "1",
                new Object[] { "LINK WEBSITE", "PRODUCT NAME"});

        furnitureStoresData.put(String.valueOf(id), new Object[] { website, name});


    }
    public void writeData() throws IOException {
        FileOutputStream out = new FileOutputStream(
                new File("/home/david/FurnitureStoresExtraction.xlsx"));
        // workbook object
        XSSFWorkbook workbook = new XSSFWorkbook();

        // spreadsheet object
        XSSFSheet spreadsheet
                = workbook.createSheet(" Furniture Stores Data ");

        // creating a row object
        XSSFRow row;
        Set<String> keyid = furnitureStoresData.keySet();

        int rowid = 0;
        // writing the data into the sheets...

        for (String key : keyid) {

            row = spreadsheet.createRow(rowid++);
            Object[] objectArr = furnitureStoresData.get(key);
            int cellid = 0;

            for (Object obj : objectArr) {
                Cell cell = row.createCell(cellid++);
                cell.setCellValue((String)obj);
            }
        }

        // .xlsx is the format for Excel Sheets...
        // writing the workbook into the file...
        workbook.write(out);
        out.close();
    }
}
