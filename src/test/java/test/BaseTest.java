package test;

import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import io.github.bonigarcia.wdm.WebDriverManager;
import utils.PropertyReader;

public class BaseTest {

	protected static ThreadLocal<WebDriver> threadLocalDriver = new ThreadLocal<WebDriver>();//

	public static ExtentReports extent = null;

	protected static Logger log = LogManager.getLogger(BaseTest.class.getName());

//
//	protected static WebDriver driver;
////	cross browser testing
//	@Parameters({"browser"})  
//	@BeforeMethod
//	public void setDriver(String browser) throws IOException {
//		WebDriver driver = BaseTest.getBrowserDriver(browser, false);
//		threadLocalDriver.set(driver);
//	}
//	
	@Parameters("Browser")
	@BeforeMethod
	public void setDriver(String localbrowser) throws IOException {
		log.info("setDriver()......Initialization");
		WebDriver driver = BaseTest.getBrowserDriver(localbrowser);
		threadLocalDriver.set(driver); // sets the local copy for the browser driver.
		log.info("setDriver()......Successfully done");
	}

	public static WebDriver getDriver() {
		log.info("getDriver()......Initialization");
		return threadLocalDriver.get(); // it will give the local copy of the browser driver
	}

	@AfterMethod
	public void removeDriver() {
		getDriver().quit();
		threadLocalDriver.remove();
	}

	@BeforeSuite
	public void setUp() {
		extentReport();
	}

	@AfterSuite
	public void tearDown() {
		extent.flush();
	}

	// to add the report in the html format
	public static void extentReport() {
		extent = new ExtentReports();
		ExtentSparkReporter spark = new ExtentSparkReporter("target/" + "Spark" + System.currentTimeMillis() + ".html");
		spark.config().setTheme(Theme.DARK);
		spark.config().setDocumentTitle("MyReport");
		extent.attachReporter(spark);
	}

	public static WebDriver getBrowserDriver(String browsername) {
		WebDriver driver = null;
		if (browsername.toLowerCase().contains("chrome")) {
			driver = new ChromeDriver();
		} else if (browsername.toLowerCase().contains("microsoftedge")) {
			driver = new EdgeDriver();
		} else if (browsername.toLowerCase().contains("firefox")) {
			driver = new FirefoxDriver();
		}
		driver.manage().window().maximize();
		return driver;

		
		
//		String browser = browsername.toLowerCase();
//		WebDriver driver = null;
//		switch (browser) {
//		case "chrome":
//			WebDriverManager.chromedriver().setup();
//			driver = new ChromeDriver();
//			break;
//
//		case "Edge":
//			WebDriverManager.edgedriver().setup();
//			driver = new EdgeDriver();
//			break;
//			
//		case "firefox":
//			WebDriverManager.firefoxdriver().setup();
//			driver = new FirefoxDriver();
//			break;
//
//		default:
//			System.out.println("Please use either Chrome or edge broswer");
//
//		}
//		driver.manage().window().maximize();
//		return driver;

	}

}
