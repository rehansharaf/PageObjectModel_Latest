package com.qa.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.compress.archivers.dump.InvalidFormatException;
import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.qa.base.TestBase;

public class TestUtil extends TestBase {

	public static String TESTDATA_SHEET_PATH = System.getProperty("user.dir")
			+ "/src/main/java/com/qa/testdata/TestData.xlsx";

	static Workbook book;
	static Sheet sheet;
	
	
	public static Object[][] getTestData(String sheetName) {
		FileInputStream file = null;
		try {
			file = new FileInputStream(TESTDATA_SHEET_PATH);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			book = WorkbookFactory.create(file);
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		sheet = book.getSheet(sheetName);
		Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		// System.out.println(sheet.getLastRowNum() + "--------" +
		// sheet.getRow(0).getLastCellNum());
		for (int i = 0; i < sheet.getLastRowNum(); i++) {
			for (int k = 0; k < sheet.getRow(0).getLastCellNum(); k++) {
				
				String value = sheet.getRow(i + 1).getCell(k).toString();
				String substring = value.substring(Math.max(value.length() - 2, 0));
				if(substring.equals(".0")){
					value = value.split("\\.", 2)[0];
					data[i][k] = value;
				}else
					data[i][k] = sheet.getRow(i + 1).getCell(k).toString();			
				// System.out.println(data[i][k]);
			}
		}
		return data;
}

	/*public static String takeScreenshotAtEndOfTest() throws IOException {

		String date = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		File scrFile = ((TakesScreenshot) driver)
				.getScreenshotAs(OutputType.FILE);
		String destination = System.getProperty("user.dir") + "/screenshots/"
				+ date + ".png";
		FileUtils.copyFile(scrFile, new File(destination));
		return destination;
	}*/
	
	public static String addScreenshot() {
	    File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	    String encodedBase64 = null;
	    FileInputStream fileInputStreamReader = null;
	    try {
	        fileInputStreamReader = new FileInputStream(scrFile);
	        byte[] bytes = new byte[(int)scrFile.length()];
	        fileInputStreamReader.read(bytes);
	        encodedBase64 = new String(Base64.encodeBase64(bytes));
	    } catch (FileNotFoundException e) {
	        e.printStackTrace();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    return "data:image/png;base64,"+encodedBase64;
	}

}
