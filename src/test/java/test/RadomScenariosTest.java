package test;

import java.io.IOException;
import java.lang.reflect.Method;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentTest;
import pages.LoginPage;
import pages.RandomScenarioPage;
import utils.PropertyReader;
import utils.Utilities;

public class RadomScenariosTest extends BaseTest {

	@Test(groups = { "system" })
	public void RadomScenarios_TC37(Method name) throws IOException, InterruptedException {

		WebDriver driver = BaseTest.getDriver();
		ExtentTest test = extent.createTest(name.getName());
		LoginPage lp = new LoginPage(driver, test);
		RandomScenarioPage rsp = new RandomScenarioPage(driver, test);
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
		Assert.assertTrue(Utilities.selectTabFromTabBar(driver, PropertyReader.readTabMenuItem("Home")),
				PropertyReader.readTabMenuItem("Home") + " should be clicked");
		Utilities.dynamicExplicitElementWait(driver, rsp.currentDateLink);
		Assert.assertTrue(
				Utilities.pageTitleVerification(driver.getTitle(), PropertyReader.readpageTitle("home_tab_page")),
				PropertyReader.readpageTitle("home_tab_page") + "Should be displayed");
		Assert.assertTrue(rsp.clickCurrentDateLink(), "current date link should be clicked");
		Utilities.dynamicExplicitElementWait(driver, rsp.shareMyCalendar);
		Assert.assertTrue(
				Utilities.pageTitleVerification(driver.getTitle(), PropertyReader.readpageTitle("calendar_page_title")),
				"Calendar page title should be there");
		Assert.assertTrue(rsp.clickTime(driver, "4:00 PM"), "Time should be selected as 4:00 PM");
		Utilities.dynamicExplicitElementWait(driver, rsp.comboNewWindow);
		Assert.assertTrue(rsp.clickComboNewWindow(), "combo New Window button should be clicked");
		Utilities.windowhandles(driver, 1);
		Utilities.dynamicExplicitElementWait(driver, rsp.other);
		Assert.assertTrue(
				Utilities.pageTitleVerification(driver.getTitle(),
						PropertyReader.readpageTitle("calender_new_event_pop_window")),
				"Page title should be" + PropertyReader.readpageTitle("calender_new_event_pop_window"));
		Assert.assertTrue(rsp.clickNewWindowOtherLink(driver), "Other link of new window should be clicked");
		Utilities.windowhandles(driver, 0);
		Utilities.dynamicExplicitElementWait(driver, rsp.comboNewWindow);

		Assert.assertTrue(rsp.selectEndTime(driver, "7:00 PM"), "Time should be selected as 7:00 PM");
		String actualtime = driver.findElement(By.xpath("//input[@id='EndDateTime_time']")).getAttribute("value");
		Assert.assertEquals(actualtime, "7:00 PM");
		Utilities.dynamicExplicitElementWait(driver, rsp.recurrenceCheckBox);
		Assert.assertTrue(rsp.clickRecurrenceCheckBox(), "recurrence Check Box should be clicked");
		Assert.assertTrue(rsp.verifySectionAvailable(driver, "Frequency"), "Frequency section should be displayed");
		Assert.assertTrue(rsp.verifySectionAvailable(driver, "Recurrence Start"),
				"Recurrence Start section should be displayed");
		Assert.assertTrue(rsp.verifySectionAvailable(driver, "Recurrence End"),
				"Recurrence End section should be displayed");
		Assert.assertTrue(rsp.selectweeklyRadioButton(), "RadioButton should be clicked");
		Utilities.dynamicExplicitElementWait(driver, rsp.recursEveryTextBox);
		Assert.assertTrue(rsp.verifyRecuresEveryWeek(driver, "1"));
		Assert.assertTrue(rsp.verifyCurrentDayCheckBox(driver, 16), "Current day checkbox should be selected");
		Assert.assertTrue(rsp.clickRecurrenceEndDate(driver), "end date drop down should be clicked");
		Assert.assertTrue(rsp.selectTwoWeeksDate(driver), "date after two weeks should be selected");
		Assert.assertTrue(rsp.verifyTheDateSelected(driver), "2 weeks from the current day should be selected");
		Assert.assertTrue(rsp.clickBottomSaveButton(driver), "Bottom save button should be clicked");
		Utilities.dynamicExplicitElementWait(driver, rsp.calendarHeader);
		Assert.assertEquals(Utilities.pageTitle(driver), PropertyReader.readpageTitle("calendar_page_title"));
		Assert.assertTrue(rsp.VerifyEventAdded(driver, "other", "4:00 PM", "7:00 PM"),
				"Other as a Event should be found for the specifies time");

	}

}
