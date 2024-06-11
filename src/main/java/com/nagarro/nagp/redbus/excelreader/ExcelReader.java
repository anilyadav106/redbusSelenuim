package com.nagarro.nagp.redbus.excelreader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {
	/*
	 * This method is used to read the excel data and return the 2D object array
	 */
	 public static Object[][]  readExcelData () {
		 
	        String filePath = "./src/test/resources/Excel/RedBusTestData.xlsx";  

 
	        FileInputStream fileInputStream = null;
	        Workbook workbook = null;

	        Object[][] testData = null;
	        
            DataFormatter dataFormatter = new DataFormatter();


	        try {
	            fileInputStream = new FileInputStream(new File(filePath));

	            // Create a workbook instance from the Excel file (for .xlsx files)
	            workbook = new XSSFWorkbook(fileInputStream); 

	            // Get the first sheet from the workbook
	            Sheet sheet = workbook.getSheetAt(0); 

	            // Get the number of rows and columns in the sheet
	            int rowCount = sheet.getPhysicalNumberOfRows();
	            int colCount = sheet.getRow(0).getLastCellNum();

	            // Initialize the test data array, row count is 1 less because 1st row is considered as header of test data
	            testData = new Object[rowCount - 1][colCount];

	            // Iterate through each row in the sheet (skipping the header row)
	            for (int i = 1; i < rowCount; i++) {
	                Row row = sheet.getRow(i);

	                // Iterate through each cell in the row
	                for (int j = 0; j < colCount; j++) {
	                    Cell cell = row.getCell(j);

	                    // Format cell value based on cell type
	                    String cellValue = dataFormatter.formatCellValue(cell);

	                    testData[i - 1][j] = cellValue; // Assuming all values are strings
	                }
	            }
	        } catch (IOException e) {
	            System.err.println("Error reading Excel file: " + filePath);
	            e.printStackTrace();
	        } finally {
	            // Close the workbook and file input stream in finally block to ensure cleanup
	            if (workbook != null) {
	                try {
	                    workbook.close();
	                } catch (IOException e) {
	                    e.printStackTrace();
	                }
	            }
	            if (fileInputStream != null) {
	                try {
	                    fileInputStream.close();
	                } catch (IOException e) {
	                    e.printStackTrace();
	                }
	            }
	        }

	        return testData;
	    }
	    }
 
