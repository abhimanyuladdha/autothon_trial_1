package com.autothon.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {

	public static FileInputStream fileinputstream(String fileName) {
		FileInputStream fileInputStream = null;
		try {
			fileInputStream = new FileInputStream(new File("./src/main/resources/TestData/" + fileName + ".xlsx"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fileInputStream;
	}

	public static String readdata(String fileName, String sheetName, int rowNum, int colNum) {
		try {
			FileInputStream fileinputstream = fileinputstream(fileName);
			Workbook w = WorkbookFactory.create(fileinputstream);
			w.getSheet(sheetName).getRow(rowNum).getCell(colNum).getStringCellValue();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static int getRowIndex(String fileName, String sheetName, int startPoint, String KeyName) {
		int rowIndex = 0;

		try {
			FileInputStream fileinputstream = fileinputstream(fileName);
			Sheet w = WorkbookFactory.create(fileinputstream).getSheet(sheetName);
			for (int i = 0; i <= w.getLastRowNum(); i++) {
				for (int j = 0; j < w.getRow(i).getLastCellNum(); j++) {
					if (w.getRow(i).getCell(j).getStringCellValue().equalsIgnoreCase(KeyName)) {
						rowIndex = i;
						break;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rowIndex;
	}

	public static int getColIndex(String fileName, String sheetName, int startPoint, String KeyName) {
		int colIndex = 0;

		try {
			FileInputStream fileinputstream = fileinputstream(fileName);
			Sheet w = WorkbookFactory.create(fileinputstream).getSheet(sheetName);
			for (int i = startPoint; i <= w.getLastRowNum(); i++) {
				for (int j = 0; j < w.getRow(i).getLastCellNum(); j++) {
					if (w.getRow(i).getCell(j).getStringCellValue().equalsIgnoreCase(KeyName)) {
						colIndex = j;
						break;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return colIndex;
	}

	public static Map<String, String> getValue(String fileName, String sheetName, int startPoint, String KeyName,
			String colName) {
		Map<String, String> value = new HashMap<String, String>();
		try {
			FileInputStream fileinputstream = fileinputstream(fileName);
			Sheet w = WorkbookFactory.create(fileinputstream).getSheet(sheetName);
			int keyNamesIndex = getColIndex(fileName, sheetName, startPoint, KeyName);
			int valueIndex = getColIndex(fileName, sheetName, startPoint, colName);
			for (int i = 1; i < w.getLastRowNum(); i++) {
				value.put(w.getRow(i).getCell(keyNamesIndex).toString(), w.getRow(i).getCell(valueIndex).toString());
			}
			try {
				value.put(w.getRow(w.getLastRowNum()).getCell(keyNamesIndex).toString(),
						w.getRow(w.getLastRowNum()).getCell(valueIndex).toString());
			} catch (NullPointerException ne) {
				ne.printStackTrace();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return value;
	}

	public static void writedata(String fileName, String sheetName, Map<String, Object[]> dataSet) {
		try {
			File file = new File("./src/test/resources/TestData/" + fileName + ".xlsx");
			boolean fileExist = file.exists();

			XSSFWorkbook workbook;
			XSSFSheet spreadsheet = null;

			if (fileExist) {
				FileInputStream inputStream = new FileInputStream(file);
				workbook = new XSSFWorkbook(inputStream);
				spreadsheet = workbook.getSheet(sheetName);
			} else {
				workbook = new XSSFWorkbook();
				spreadsheet = workbook.createSheet(sheetName);
			}

			XSSFRow row;
			Set<String> keyid = dataSet.keySet();
			int rowid = spreadsheet.getPhysicalNumberOfRows();
			for (String key : keyid) {
				row = spreadsheet.createRow(rowid++);
				Object[] objectArr = dataSet.get(key);
				int cellid = 0;

				for (Object obj : objectArr) {
					Cell cell = row.createCell(cellid++);
					cell.setCellValue(obj.toString());
				}
			}

			FileOutputStream fileOutputStream = new FileOutputStream(
					new File("./src/test/resources/TestData/" + fileName + ".xlsx"));
			workbook.write(fileOutputStream);
			fileOutputStream.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}

