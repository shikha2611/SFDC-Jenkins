package test;

import java.io.IOException;
import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;

import pages.LoginPage;
import pages.UserMenuPage;
import utils.PropertyReader;
import utils.Utilities;

public class UserMenuTest extends BaseTest {

	@Test(groups= {"smoke"})
	public void userMenuDropDown_TC05() throws IOException, InterruptedException {
		WebDriver driver = BaseTest.getDriver();
		ExtentTest test = extent.createTest("User Menu DropDown TC05");
		LoginPage lp = new LoginPage(driver, test);
		UserMenuPage ump = new UserMenuPage(driver, test);
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
		Assert.assertEquals(Utilities.pageTitle(driver), PropertyReader.readpageTitle("home_Page_title"));
		Thread.sleep(2000);
		Utilities.dynamicExplicitElementWait(driver, ump.userMenu);
		Assert.assertEquals(ump.userMenu.getText(), PropertyReader.readUserMenu("user_name_label"));
		Assert.assertTrue(ump.clickUserMenu(driver), "usermenu should be clicked and options should be visible ");
		Assert.assertTrue(ump.verifyUserMenuOptions(), "All the options should be available");
	}

	@Test
	public void userMenuDropDownDeveloperConsole_TC08(Method name) throws IOException, InterruptedException {
		WebDriver driver = BaseTest.getDriver();
		ExtentTest test = extent.createTest(name.getName());
		LoginPage lp = new LoginPage(driver, test);
		UserMenuPage ump = new UserMenuPage(driver, test);
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
		Utilities.dynamicExplicitElementWait(driver, ump.userMenu);
		Assert.assertEquals(Utilities.pageTitle(driver), PropertyReader.readpageTitle("home_Page_title"));
		Utilities.dynamicExplicitElementWait(driver, ump.userMenu);
		Assert.assertEquals(ump.userMenu.getText(), PropertyReader.readUserMenu("user_name_label"));
		Assert.assertTrue(ump.clickUserMenu(driver), "usermenu should be clicked and options should be visible ");
		Assert.assertTrue(ump.selectUserMenuOption(driver, PropertyReader.readUserMenu("Option_Developer_Console")),
				PropertyReader.readUserMenu("Option_Developer_Console") + " should be selected as an option");
		Assert.assertEquals(ump.developerConsoleWindow(driver),
				PropertyReader.readpageTitle("developer_console_window_handle"),
				PropertyReader.readpageTitle("developer_console_window_handle") + "should be displayed");

	}

	@Test(groups = {"smoke", "regression"})
	public void userMenuLogout_Tc_09(Method name) throws IOException {
		WebDriver driver = BaseTest.getDriver();
		ExtentTest test = extent.createTest(name.getName());
		LoginPage lp = new LoginPage(driver, test);
		UserMenuPage ump = new UserMenuPage(driver, test);
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
		Utilities.dynamicExplicitElementWait(driver, ump.userMenu);
		Assert.assertEquals(Utilities.pageTitle(driver), PropertyReader.readpageTitle("home_Page_title"));
		Utilities.dynamicExplicitElementWait(driver, ump.userMenu);
		Assert.assertEquals(ump.userMenu.getText(), PropertyReader.readUserMenu("user_name_label"));
		Assert.assertTrue(ump.clickUserMenu(driver), "usermenu should be clicked and options should be visible ");
		Assert.assertTrue(ump.selectUserMenuOption(driver, PropertyReader.readUserMenu("option_logout")),
				PropertyReader.readUserMenu("Option_Developer_Console") + " should be selected as an option");
		Utilities.dynamicExplicitElementWait(driver, lp.loginButton);
		Assert.assertEquals(Utilities.pageTitle(driver), PropertyReader.readpageTitle("login_page_title"),
				PropertyReader.readpageTitle("login_page_title") + " should be displayed");

	}

}
