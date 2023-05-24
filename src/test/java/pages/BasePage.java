package pages;

import com.aventstack.extentreports.ExtentTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BasePage {

	public static ExtentTest test;
	protected static Logger log = LogManager.getLogger(BasePage.class.getName());

}
