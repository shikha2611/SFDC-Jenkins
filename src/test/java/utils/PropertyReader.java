package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

import constants.FileConstants;

public class PropertyReader {

	public static String readAppLaunchUrl(String key) throws IOException {
		Properties prop = new Properties();
		FileReader fr = new FileReader(FileConstants.app_launch_url_path);
		prop.load(fr);
		String value = prop.getProperty(key);
		return value;
	}

	public static String readbrowserType(String key) throws IOException {
		Properties prop = new Properties();
		FileReader fr = new FileReader(FileConstants.browser_name_path);
		prop.load(fr);
		String value = prop.getProperty(key);
		return value;
	}

	public static String readErrorMsg(String key) throws IOException {
		Properties prop = new Properties();
		FileReader fr = new FileReader(FileConstants.error_msg_path);
		prop.load(fr);
		String value = prop.getProperty(key);
		return value;
	}

	public static String readLoginUsernamePassword(String key) throws IOException {
		Properties prop = new Properties();
		FileReader fr = new FileReader(FileConstants.login_data_path);
		prop.load(fr);
		String value = prop.getProperty(key);
		return value;
	}

	public static String readpageTitle(String key) throws IOException {
		Properties prop = new Properties();
		FileReader fr = new FileReader(FileConstants.page_Titles);
		prop.load(fr);
		String value = prop.getProperty(key);
		return value;
	}

	public static String readUserMenu(String key) throws IOException {
		Properties prop = new Properties();
		FileReader fr = new FileReader(FileConstants.user_Menu);
		prop.load(fr);
		String value = prop.getProperty(key);
		return value;
	}

	public static String readPublisherFeedChoices(String Key) throws IOException {
		Properties prop = new Properties();
		FileReader fr = new FileReader(FileConstants.my_profile_feed_choices);
		prop.load(fr);
		String value = prop.getProperty(Key);
		return value;
	}

	public static String readMySettingsOptions(String Key) throws IOException {
		Properties prop = new Properties();
		FileReader fr = new FileReader(FileConstants.mySetting_options);
		prop.load(fr);
		String value = prop.getProperty(Key);
		return value;
	}

	public static String readAppMenuOptions(String Key) throws IOException {
		Properties prop = new Properties();
		FileReader fr = new FileReader(FileConstants.app_Menu_Options);
		prop.load(fr);
		String value = prop.getProperty(Key);
		return value;
	}

	@DataProvider(name = "data")
	public static String[][] readlLoginDataExcel() throws EncryptedDocumentException, IOException {
		File f = new File(FileConstants.login_Test_Data);

		FileInputStream fis = new FileInputStream(f);

//		Workbook wb = WorkbookFactory.create(fis);
//		Sheet sh = wb.getSheet("login");
//		int rownum = sh.getLastRowNum();
//		int cellnum = sh.getRow(0).getLastCellNum();

		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet = wb.getSheet("login");
		int rownum = sheet.getLastRowNum();
		int cellnum = sheet.getRow(0).getLastCellNum();

		String[][] testdata = new String[rownum][cellnum];
		DataFormatter format = new DataFormatter();
		for (int i = 0; i < rownum; i++) {
			for (int j = 0; j < cellnum; j++) {
				testdata[i][j] = format.formatCellValue(sheet.getRow(i).getCell(j));
			}
		}
		return testdata;

	}

	public static String readKeywordLookup(String Key) throws IOException {
		Properties prop = new Properties();
		FileReader fr = new FileReader(FileConstants.keyword_lookup);
		prop.load(fr);
		String value = prop.getProperty(Key);
		return value;

	}
	
	public static String readTabMenuItem(String Key) throws IOException {
		Properties prop = new Properties();
		FileReader fr = new FileReader(FileConstants.tab_bar_Menu);
		prop.load(fr);
		String value = prop.getProperty(Key);
		return value;
	}

}
