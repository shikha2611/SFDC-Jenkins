package test;

import java.io.IOException;
import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;

import pages.AppMenuPage;
import pages.LoginPage;
import utils.PropertyReader;
import utils.Utilities;

public class AppMenuTest extends BaseTest {
//(invocationCount = 3, groups= {"smoke", "regression"})
	@Test
	public void createNewAccount_TC10(Method name) throws IOException {
		WebDriver driver = BaseTest.getDriver();
		ExtentTest test = extent.createTest(name.getName());
		LoginPage lp = new LoginPage(driver, test);
		AppMenuPage amp = new AppMenuPage(driver, test);
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
		Utilities.dynamicExplicitElementWait(driver, amp.appMenuNavigation);
		Assert.assertEquals(Utilities.pageTitle(driver), PropertyReader.readpageTitle("home_Page_title"));
		Utilities.dynamicExplicitElementWait(driver, amp.appMenuNavigation);
		amp.appMenuNavigation.click();
		Assert.assertTrue(
				amp.selectAppMenuDropDown(driver, PropertyReader.readAppMenuOptions("App_Menu_service_option")),
				PropertyReader.readAppMenuOptions("App_Menu_service_option") + " should be selected");
		Utilities.dynamicExplicitElementWait(driver, amp.tabBar);
		Assert.assertTrue(amp.clickTabFromTabBar(driver, PropertyReader.readAppMenuOptions("service_account_tab")),
				PropertyReader.readAppMenuOptions("service_account_tab") + "should be clicked");
		Utilities.dynamicExplicitElementWait(driver, amp.newAccountButton);
		amp.newAccountButton.click();
		Utilities.dynamicExplicitElementWait(driver, amp.newAccountnametextbox);
		Assert.assertEquals(Utilities.pageTitle(driver), PropertyReader.readpageTitle("new_account_creation_edit"));
//		String createdAccountName =amp.createNewAccount(driver);
		amp.createNewAccount(driver);
		Utilities.dynamicExplicitElementWait(driver, amp.newAccountName);
		String title = Utilities.pageTitle(driver);
		String expectedAccountName = amp.newAccountName.getText();
		if (title.contains(expectedAccountName)) {
			System.out.println(title);
		}
	}

	@Test
	public void createNewView_TC_11(Method name) throws IOException, InterruptedException {
		WebDriver driver = BaseTest.getDriver();
		ExtentTest test = extent.createTest(name.getName());
		LoginPage lp = new LoginPage(driver, test);
		AppMenuPage amp = new AppMenuPage(driver, test);
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
		Utilities.dynamicExplicitElementWait(driver, amp.appMenuNavigation);
		Assert.assertEquals(Utilities.pageTitle(driver), PropertyReader.readpageTitle("home_Page_title"));
		Utilities.dynamicExplicitElementWait(driver, amp.appMenuNavigation);
		Assert.assertTrue(amp.clickTabFromTabBar(driver, PropertyReader.readAppMenuOptions("Accounts_Tab")),
				PropertyReader.readAppMenuOptions("Accounts_Tab") + " should be clicked");
		Utilities.dynamicExplicitElementWait(driver, amp.createNewViewLink);
		Assert.assertTrue(amp.clickCreateNewView(driver), "New View link should be clicked");
		Utilities.dynamicExplicitElementWait(driver, amp.createNewViewLink);

//		String actual = Utilities.pageTitle(driver);
		Assert.assertEquals(Utilities.pageTitle(driver),
				PropertyReader.readpageTitle("create_new_view_accounts_home_page"));

	}

	@Test
	public void editView_TC_12(Method name) throws IOException {
		WebDriver driver = BaseTest.getDriver();
		ExtentTest test = extent.createTest(name.getName());
		LoginPage lp = new LoginPage(driver, test);
		AppMenuPage amp = new AppMenuPage(driver, test);
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
		Utilities.dynamicExplicitElementWait(driver, amp.appMenuNavigation);
		Assert.assertEquals(Utilities.pageTitle(driver), PropertyReader.readpageTitle("home_Page_title"));
		Utilities.dynamicExplicitElementWait(driver, amp.appMenuNavigation);
		Assert.assertTrue(amp.clickTabFromTabBar(driver, PropertyReader.readAppMenuOptions("Accounts_Tab")),
				PropertyReader.readAppMenuOptions("Accounts_Tab") + " should be clicked");

		Utilities.dynamicExplicitElementWait(driver, amp.ViewAddedDropDown);
//		amp.ViewAddedDropDown.click();
		Utilities.selectValueFromText(amp.ViewAddedDropDown,
				PropertyReader.readAppMenuOptions("looking_for_view_to_edit"));
		String newlyAddedViewName = amp.clickEditViewLink(driver);
		amp.verifyViewDropDown(newlyAddedViewName);
		Assert.assertTrue(
				amp.verifyColumnsTitle(driver, PropertyReader.readAppMenuOptions("edit_view_available_fields")),
				PropertyReader.readAppMenuOptions("edit_view_available_fields")
						+ " should be displayed in the columns header");
		Assert.assertTrue(
				amp.verifySequenceCharactersinAccounts(driver,
						PropertyReader.readAppMenuOptions("edit_view_filter_valueText")),
				PropertyReader.readAppMenuOptions("edit_view_filter_valueText")
						+ " all the accounts should contain this sequence");
		test.info("************end of editView_TC_12***********");

	}

	@Test
	public void merge_Accounts_TC13(Method name) throws IOException, InterruptedException {
		WebDriver driver = BaseTest.getDriver();
		ExtentTest test = extent.createTest(name.getName());
		LoginPage lp = new LoginPage(driver, test);
		AppMenuPage amp = new AppMenuPage(driver, test);
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
		Assert.assertTrue(amp.clickTabFromTabBar(driver, PropertyReader.readAppMenuOptions("Accounts_Tab")),
				PropertyReader.readAppMenuOptions("Accounts_Tab") + " should be clicked");
		Utilities.dynamicExplicitElementWait(driver, amp.MergeAccountLink);
		Assert.assertTrue(amp.mergeAccounts(driver, 2, PropertyReader.readKeywordLookup("account_search_keyword")),
				PropertyReader.readKeywordLookup("account_search_keyword") + " should only be displayed");
		Utilities.dynamicExplicitElementWait(driver, amp.createNewViewLink);
		Assert.assertEquals(Utilities.pageTitle(driver), PropertyReader.readpageTitle("accounts_home_page"));
		Assert.assertTrue(amp.verifyAccountList(driver, PropertyReader.readKeywordLookup("account_search_keyword")),
				PropertyReader.readKeywordLookup("account_search_keyword") + "should be available in the account list");

	}

}
