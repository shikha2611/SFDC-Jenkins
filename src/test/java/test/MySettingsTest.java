package test;

import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentTest;
import pages.AppMenuPage;
import pages.LoginPage;
import pages.MyProfilePage;
import pages.MySettingsPage;
import pages.UserMenuPage;
import utils.PropertyReader;
import utils.Utilities;

public class MySettingsTest extends BaseTest {

	@Test
	public void MySettings_TC07_FileDownload() throws IOException, InterruptedException {
		WebDriver driver = BaseTest.getDriver();
		ExtentTest test = extent.createTest("MySettings Personal TC07");
		LoginPage lp = new LoginPage(driver, test);
		UserMenuPage ump = new UserMenuPage(driver, test);
		MySettingsPage msp = new MySettingsPage(driver, test);
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
		Assert.assertTrue(ump.clickUserMenu(driver), "usermenu should be clicked and options should be visible ");
		Utilities.dynamicExplicitElementWait(driver, msp.mySettingNavigation);
		Assert.assertTrue(ump.selectUserMenuOption(driver, PropertyReader.readUserMenu("option_My_Settings")),
				"Option MySettings should be selected");
		Thread.sleep(3000);
		Utilities.dynamicExplicitElementWait(driver, msp.personal);
		Assert.assertTrue(msp.clickmysettingOptions(driver, PropertyReader.readMySettingsOptions("my_setting_option1")),
				PropertyReader.readMySettingsOptions("my_setting_option1") + " should be selected");
		Assert.assertTrue(msp.clickPersonalOptions(driver, PropertyReader.readMySettingsOptions("personal_option")),
				PropertyReader.readMySettingsOptions("personal_option") + " should be selected");
//		Assert.assertTrue(msp.clickLoginHistory(driver), "Login History should be clicked");
		Utilities.dynamicExplicitElementWait(driver, msp.loginHistoryFileDownload);
		Assert.assertTrue(msp.clickPersonalFileDownLoadLoginHistory(driver), "download history link shoud be clicked");

	}

	@Test
	public void MySettings_TC07_DisplayLayOut() throws IOException, InterruptedException {
		WebDriver driver = BaseTest.getDriver();
		ExtentTest test = extent.createTest("MySettings DisplayLayout TC07");
		LoginPage lp = new LoginPage(driver, test);
		UserMenuPage ump = new UserMenuPage(driver, test);
		MySettingsPage msp = new MySettingsPage(driver, test);
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
		Utilities.dynamicExplicitElementWait(driver, ump.userMenu);
		Assert.assertEquals(Utilities.pageTitle(driver), PropertyReader.readpageTitle("home_Page_title"));

		Utilities.dynamicExplicitElementWait(driver, ump.userMenu);
		Assert.assertTrue(ump.clickUserMenu(driver), "usermenu should be clicked and options should be visible ");
		Utilities.dynamicExplicitElementWait(driver, msp.mySettingNavigation);
		Assert.assertTrue(ump.selectUserMenuOption(driver, PropertyReader.readUserMenu("option_My_Settings")),
				"Option MySettings should be selected");
		Thread.sleep(3000);
		Assert.assertTrue(msp.clickmysettingOptions(driver, PropertyReader.readMySettingsOptions("my_setting_option2")),
				PropertyReader.readMySettingsOptions("my_setting_option2") + " option should be selected");
		Assert.assertTrue(
				msp.dispalyAndLayOutOption(driver, PropertyReader.readMySettingsOptions("display_layout_option")),
				PropertyReader.readMySettingsOptions("display_layout_option") + " options should be selected");
		Utilities.dynamicExplicitElementWait(driver, msp.CustomAppDropDown);
		Assert.assertTrue(
				msp.clickdisplayLayoutCustomMyTabCustomApp(driver,
						PropertyReader.readMySettingsOptions("display_layout_custom_app")),
				PropertyReader.readMySettingsOptions("display_layout_custom_app") + " should selected in dropdown");

		Assert.assertTrue(
				msp.selectAvailableTabs(driver, PropertyReader.readMySettingsOptions("display_layout_available_tabs")),
				PropertyReader.readMySettingsOptions("display_layout_available_tabs")
						+ " should be selected and save button shoud be clicked");
		Utilities.dynamicExplicitElementWait(driver, amp.appMenuNavigation);
		amp.appMenuNavigation.click();
		Assert.assertTrue(
				amp.selectAppMenuDropDown(driver, PropertyReader.readMySettingsOptions("display_layout_custom_app")),
				PropertyReader.readMySettingsOptions("display_layout_custom_app") + " should be selected");

		Utilities.dynamicExplicitElementWait(driver, amp.tabBar);
		Thread.sleep(8000);
		Assert.assertTrue(
				amp.verifyAllTabsforSelectedAppMenu(driver,
						PropertyReader.readMySettingsOptions("display_layout_available_tabs")),
				PropertyReader.readMySettingsOptions("display_layout_available_tabs")
						+ " should be added to the tabBar");

	}

	@Test
	public void mySettingsEmail_TC07() throws IOException, InterruptedException {
		WebDriver driver = BaseTest.getDriver();
		ExtentTest test = extent.createTest("MySettings Email TC07");
		LoginPage lp = new LoginPage(driver, test);
		UserMenuPage ump = new UserMenuPage(driver, test);
		MySettingsPage msp = new MySettingsPage(driver, test);
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
		Assert.assertTrue(ump.clickUserMenu(driver), "usermenu should be clicked and options should be visible ");
		Utilities.dynamicExplicitElementWait(driver, msp.mySettingNavigation);
		Assert.assertTrue(ump.selectUserMenuOption(driver, PropertyReader.readUserMenu("option_My_Settings")),
				"Option MySettings should be selected");
		Thread.sleep(3000);
		Assert.assertTrue(msp.clickmysettingOptions(driver, PropertyReader.readMySettingsOptions("my_setting_option3")),
				PropertyReader.readMySettingsOptions("my_setting_option3") + " option should be selected");
		Assert.assertTrue(
				msp.selecteMySettingEmailOptions(driver, PropertyReader.readMySettingsOptions("email_option1")),
				PropertyReader.readMySettingsOptions("email_option1") + "should be selected as an option");
		Utilities.dynamicExplicitElementWait(driver, msp.emailName);
		Assert.assertTrue(
				msp.setOutgoingEmailSettings(driver, PropertyReader.readMySettingsOptions("outgoing_email_name")),
				PropertyReader.readMySettingsOptions("outgoing_email_name") + " should be displayed in the email name");

		Assert.assertTrue(
				msp.selectBCCRadioButton(driver, PropertyReader.readMySettingsOptions("radiobutton_yes_num1")),
				PropertyReader.readMySettingsOptions("radiobutton_yes_num1")
						+ " should be selected as a bcc radio button");
		msp.OutGoingEmailSave();
		Thread.sleep(3000);
		Assert.assertEquals(driver.getTitle(), PropertyReader.readpageTitle("My_Email_Settings"));
		Assert.assertEquals(msp.verifyEmailSettingSaveSuccessMsg(driver),
				PropertyReader.readErrorMsg("success_email_settings_saved"));
		Assert.assertTrue(false, null);
		
		msp.verifyEmailNameAndEmailAddress(driver, PropertyReader.readMySettingsOptions("email_name"),
				PropertyReader.readMySettingsOptions("email_address"));

	}

	@Test
	public void MySettingsCalendarandReminders_TC07() throws IOException, InterruptedException {
		WebDriver driver = BaseTest.getDriver();
		ExtentTest test = extent.createTest("MySettings CalendarReminder TC07");
		LoginPage lp = new LoginPage(driver, test);
		UserMenuPage ump = new UserMenuPage(driver, test);
		MySettingsPage msp = new MySettingsPage(driver, test);
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
		Assert.assertTrue(ump.clickUserMenu(driver), "usermenu should be clicked and options should be visible ");
		Utilities.dynamicExplicitElementWait(driver, msp.mySettingNavigation);
		Assert.assertTrue(ump.selectUserMenuOption(driver, PropertyReader.readUserMenu("option_My_Settings")),
				"Option MySettings should be selected");
		Thread.sleep(3000);
		Assert.assertTrue(
				msp.clickmysettingOptions(driver, PropertyReader.readMySettingsOptions("my_setting_options4")),
				PropertyReader.readMySettingsOptions("my_setting_options4") + " option should be selected");
		Assert.assertTrue(
				msp.clickCalendarReminderOption(driver,
						PropertyReader.readMySettingsOptions("calendar_reminder_option2")),
				PropertyReader.readMySettingsOptions("calendar_reminder_option2") + " should be selected as an option");
		Utilities.dynamicExplicitElementWait(driver, msp.openTestReminderButton);
		msp.testReminderButton(driver);

	}

}
