package test;

import java.io.IOException;
import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;

import pages.AppMenuPage;
import pages.ContactsPage;
import pages.LoginPage;
import utils.PropertyReader;
import utils.Utilities;

public class ContactsTest extends BaseTest {

	@Test(groups= {"smoke", "regression", "integration"})
	public void createNewContact_TC25(Method name) throws IOException, InterruptedException {
		WebDriver driver = BaseTest.getDriver();
		ExtentTest test = extent.createTest(name.getName());
		LoginPage lp = new LoginPage(driver, test);
		AppMenuPage amp = new AppMenuPage(driver, test);
		ContactsPage cp = new ContactsPage(driver, test);
		lp.windowMaximize(driver);
		Assert.assertTrue(lp.launchApp(driver), "Application should be launched");
		Utilities.dynamicExplicitElementWait(driver, lp.userName);
		Assert.assertEquals(Utilities.pageTitle(driver), PropertyReader.readpageTitle("login_page_title"));
		Assert.assertTrue(lp.enterUserName(driver, PropertyReader.readLoginUsernamePassword("username")),
				"username should be entered");
		Assert.assertTrue(lp.enterPassword(driver, PropertyReader.readLoginUsernamePassword("password")),
				"password should be entered");
		Utilities.dynamicExplicitElementWait(driver, lp.loginButton);
		Assert.assertTrue(lp.clickloginButton(driver), "login button should be clicked");
		Assert.assertTrue(amp.clickTabFromTabBar(driver, PropertyReader.readKeywordLookup("contacts_tab")),
				PropertyReader.readKeywordLookup("contacts_tab") + "shoubd be clicked");
		Utilities.dynamicExplicitElementWait(driver, cp.newContactButton);
		Assert.assertTrue(cp.createNewContact(driver), "new contact should be created");

	}
}