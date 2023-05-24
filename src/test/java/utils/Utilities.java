package utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;

import com.aventstack.extentreports.ExtentTest;

import pages.BasePage;

public class Utilities extends BasePage {

	public static boolean dynamicExplicitElementWait(WebDriver driver, WebElement element) {
		boolean isElementClickable = false;
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		try {
			wait.until(ExpectedConditions.visibilityOf(element));
			isElementClickable = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isElementClickable;
	}

	public static String captureScreenShot(WebDriver driver) throws IOException  {

		TakesScreenshot screenShot = (TakesScreenshot) driver;
		File sourcefile = screenShot.getScreenshotAs(OutputType.FILE);
		File destfilePAth = new File("src/../images/screenshot"+System.currentTimeMillis()+".png");
		String absPathLocation=destfilePAth.getAbsolutePath();
		FileUtils.copyFile(sourcefile, destfilePAth);
		return absPathLocation;
	}

	public static String pageTitle(WebDriver driver) {
		String title = driver.getTitle();
		return title;

	}

	public static boolean javaScriptButtonClick(WebDriver driver, WebElement element) {
		boolean isClick = false;
		if (element.isDisplayed()) {
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("arguments[0].click();", element);
			isClick = true;

		} else {
			isClick = false;
		}
		return isClick;

	}

	public static String randomNameGeneration() throws IOException {
		String fix = PropertyReader.readKeywordLookup("random_String_Prefix");
		String randomString = RandomStringUtils.randomAlphanumeric(5);
		return fix.concat(randomString);

	}

	public static boolean pageTitleVerification(String actual, String expected) {
		if (actual.contains(expected)) {
			return true;
		} else {
			return false;
		}

	}

	public static void selectValueFromText(WebElement ele, String value) {
		Select sel = new Select(ele);
		sel.selectByVisibleText(value);
	}

	public static boolean selectTabFromTabBar(WebDriver driver, String tabMenu) throws IOException {
		log.info("selectTabFromTabBar()....Initialized");
		boolean isTabMenuClicked = false;
		WebElement tabBar = driver.findElement(By.xpath("//ul[@id='tabBar']"));
		if (tabBar.isDisplayed()) {
			driver.findElement(By.xpath("//ul[@id='tabBar']/li/a[text()='" + tabMenu + "']")).click();
			isTabMenuClicked = true;
			log.info("tab menu clicked...");
		} else {
			isTabMenuClicked = false;
			log.error("tab menu not clicked...");
		}
		return isTabMenuClicked;
	}

	public static WebDriver windowhandles(WebDriver driver, int winnum) throws InterruptedException {
		Thread.sleep(2000);
		Set<String> wins = driver.getWindowHandles();
		ArrayList<String> list = new ArrayList<>();
		Iterator<String> itr = wins.iterator();
		while (itr.hasNext()) {
			list.add(itr.next());
		}
		return driver.switchTo().window(list.get(winnum));
	}

	
	//reusable method for is element is displayed or not.
//	public static boolean isDisplayed(WebDriver driver, WebElement ele) {
//		log.info("isDisplayed().... Initialized");
//		boolean isPresent = false;
//		if (ele.isDisplayed()) {
//			isPresent = true;
//			log.info(ele + "isDisplayed().... returned true");
//			test.pass(ele + " isDisplayed() returned true");
//			
//		} else {
//			isPresent = false;
//			log.info(ele + "isDisplayed().... returned false");
//			test.fail(ele + " isDisplayed() returned false");
//		}
//
//		return isPresent;
//
//	}

}
