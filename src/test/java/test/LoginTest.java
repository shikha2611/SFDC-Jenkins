package test;

import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import com.aventstack.extentreports.ExtentTest;
import pages.LoginPage;
import pages.UserMenuPage;
import utils.PropertyReader;
import utils.Utilities;


public class LoginTest extends BaseTest {
	
//	(groups = {"regression"})//will not execute this test as enabled is false;
	
	@Test
	public void clearpassword_TC_01() throws IOException, InterruptedException {

		WebDriver driver = BaseTest.getDriver();
		ExtentTest test = extent.createTest("Clear Password TC01");
		LoginPage lp = new LoginPage(driver, test);
		Assert.assertTrue(lp.launchApp(driver), "Application should be launched");
		Thread.sleep(3000);
//		Assert.assertEquals(Utilities.pageTitle(driver), PropertyReader.readpageTitle("login_page_title"),
//				"login page title should be  found");
		Assert.assertTrue(lp.enterUserName(driver, PropertyReader.readLoginUsernamePassword("username")),
				"login username not entered");
		lp.clearPassword();
		Assert.assertTrue(lp.clickloginButton(driver), "login button should be clicked");
		Assert.assertEquals(lp.blankPwdErrorMsg.getText(), PropertyReader.readErrorMsg("blank_password"),
				"blank password error message should be displayed");

	}
	
//(groups = { "smoke", "regression" })
	@Test
	public void loginToSfdc_TC_02() throws IOException {
		WebDriver driver = BaseTest.getDriver();
		ExtentTest test = extent.createTest("Login to SFDC TC02");
		log.debug("loginToSfdc_TC_02():  Initiated");
		LoginPage lp = new LoginPage(driver, test);
		Assert.assertTrue(lp.launchApp(driver), "Application url should be launched");
		Assert.assertTrue(lp.enterUserName(driver, PropertyReader.readLoginUsernamePassword("username")),
				"username should be entered");
		Assert.assertTrue(lp.enterPassword(driver, PropertyReader.readLoginUsernamePassword("password")),
				"password should be entered");
		Assert.assertTrue(lp.clickloginButton(driver), "login button should be clicked");
//		Assert.assertTrue(lp.freeTrial(driver), "free trial should be displayed");
		log.debug("****************loginToSfdc_TC_02():*******  end");

	}
	
//(groups = { "integration" })
	@Test(enabled=false)
	public void rememberMeCheckbox_TC_03() throws IOException, InterruptedException {

		WebDriver driver = BaseTest.getDriver();
		ExtentTest test = extent.createTest("Remember me checkbox TC03");
		LoginPage lp = new LoginPage(driver, test);
		UserMenuPage ump = new UserMenuPage(driver, test);
		Assert.assertTrue(lp.launchApp(driver), "Application should be launched");
		Assert.assertTrue(lp.enterUserName(driver, PropertyReader.readLoginUsernamePassword("username")),
				"username should be entered");
		Assert.assertTrue(lp.enterPassword(driver, PropertyReader.readLoginUsernamePassword("password")),
				"password should be entered");
		Utilities.dynamicExplicitElementWait(driver, lp.rememberMe);
		Assert.assertTrue(lp.clickRememberMe(driver), "remember me checkbox should be clicked");
		Assert.assertTrue(lp.clickloginButton(driver), "login button should be clicked");
		Thread.sleep(3000);
		Assert.assertTrue(ump.clickUserMenu(driver), "userMenu should be clicked");
		Assert.assertTrue(ump.selectUserMenuOption(driver, PropertyReader.readUserMenu("option_logout")),
				"user should be logged out");
		Utilities.dynamicExplicitElementWait(driver, lp.savedUsername);
		Assert.assertEquals(lp.getSavedUserName(), PropertyReader.readLoginUsernamePassword("username"));
		Assert.assertTrue(lp.rememberMe.isSelected());
	}

	@Test
	public void forgotPasswordTC_04A() throws IOException, InterruptedException {
		SoftAssert assert1 = new SoftAssert();
		WebDriver driver = BaseTest.getDriver();
		ExtentTest test = extent.createTest("Forgot Password TC04A");
		LoginPage lp = new LoginPage(driver, test);
		assert1.assertTrue(lp.launchApp(driver), "Application url didn't get launched");
		assert1.assertEquals(Utilities.pageTitle(driver), PropertyReader.readpageTitle("login_page_title"),
				"login page title not found");
		assert1.assertTrue(lp.clickForgotPassword(driver), "forgot password link should be clicked");
		assert1.assertTrue(lp.forgotPwdPageTitle(driver), "forgot password ppage should found");
		assert1.assertTrue(lp.enterforgorPasswordformUserName(driver),
				"forgot password form username should be entered");
		assert1.assertTrue(lp.clickforgotPasswordFormContinue(driver),
				"forgot password form countinue button should be clicked");
		assert1.assertTrue(lp.forgotPwdRestMsg(driver), "Reset password Message should be displayed");
//		assert1.assertEquals(lp.forgotPwdResetMsg.getText(), PropertyReader.readErrorMsg("forgot_pwd_reset_message"),
//				"Reset password Message should be displayed");
		assert1.assertAll();
	}

	@Test
	public void validateLoginErrorMsgTC_04B() throws IOException, InterruptedException {

		WebDriver driver = BaseTest.getDriver();
		ExtentTest test = extent.createTest("Forgot Password TC04B");
		LoginPage lp = new LoginPage(driver, test);
		Assert.assertTrue(lp.launchApp(driver), "Application should be launched");
		Assert.assertEquals(Utilities.pageTitle(driver), PropertyReader.readpageTitle("login_page_title"));
		Utilities.dynamicExplicitElementWait(driver, lp.userName);
		Assert.assertTrue(lp.enterUserName(driver, PropertyReader.readLoginUsernamePassword("wrong_username")),
				"Invalid uusername should be entered");
		Utilities.dynamicExplicitElementWait(driver, lp.pwd);
		Assert.assertTrue(lp.enterPassword(driver, PropertyReader.readLoginUsernamePassword("wrong_pwd")),
				"Invalid password should be entered");
		Utilities.dynamicExplicitElementWait(driver, lp.loginButton);
		Assert.assertTrue(lp.clickloginButton(driver), "Login button should be clicked");
		Utilities.dynamicExplicitElementWait(driver, lp.pwdErrorMsg);
		Assert.assertEquals(lp.pwdErrorMsg.getText(), PropertyReader.readErrorMsg("wrong_username"),
				"Invalid user and pwd error messgae should be displayed");
	}

	@Test(dataProviderClass = PropertyReader.class, dataProvider = "data",enabled=false)
	public void loginToSfdc_throughdataProvider_TC_02(String username, String password) throws IOException {
		WebDriver driver = BaseTest.getDriver();
		ExtentTest test = extent.createTest("Login to SFDC through dataprivderExcel TC02");
		log.debug("loginToSfdc_TC_02():  Initiated");
		LoginPage lp = new LoginPage(driver, test);
		Assert.assertTrue(lp.launchApp(driver), "Application url should be launched");
		Assert.assertTrue(lp.enterUserName(driver, username), "username should be entered");
		Assert.assertTrue(lp.enterPassword(driver, password), "password should be entered");
		Assert.assertTrue(lp.clickloginButton(driver), "login button should be clicked");
//		Assert.assertTrue(lp.freeTrial(driver), "free trial should be displayed");
		log.debug("****************loginToSfdc_TC_02():*******  end");

	}

}
