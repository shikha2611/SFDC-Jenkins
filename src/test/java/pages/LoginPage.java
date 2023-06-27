package pages;

import java.io.IOException;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;

import utils.PropertyReader;
import utils.Utilities;

public class LoginPage  extends BasePage{

	
	public LoginPage(WebDriver driver, ExtentTest test) {
		PageFactory.initElements(driver, this);
		BasePage.test = test;

	}

	@FindBy(id = "username")
	public WebElement userName;

	@FindBy(id = "password")
	public WebElement pwd;

	@FindBy(css = "#error")
	public WebElement loginErrorMsg;

	@FindBy(css = "#error")
	public WebElement pwdErrorMsg;

	@FindBy(css = "#error")
	public WebElement blankPwdErrorMsg;

	@FindBy(id = "rememberUn")
	public WebElement rememberMe;

	@FindBy(id = "Login")
	public WebElement loginButton;

	@FindBy(css = "a#forgot_password_link")
	public WebElement forgotPwdlink;

	@FindBy(css = "a#hint_back_chooser")
	public WebElement savedUserNamelink;

	@FindBy(css = "input#un")
	public WebElement forgotPasswordFormUserName;

	@FindBy(css = "input#continue")
	public WebElement forgotPasswordFormContinue;

	@FindBy(xpath = "//input[@name='cancel']")
	public WebElement forgotPasswordFormCancel;

	@FindBy(id = "idcard-identity")
	public WebElement savedUsername;

	@FindBy(xpath = "(//p[@class='senttext mb12'])[1]")
	public WebElement forgotPwdResetMsg;

	public boolean enterUserName(WebDriver driver, String name) throws IOException {
		
		if (userName.isDisplayed()) {
			userName.sendKeys(name);
			log.info("enterUserName(): entered");
			test.pass("user name entered");
			
			return true;
		} else {
			log.error("enterUserName(): failed");
			test.fail("UserName not entered");
			test.addScreenCaptureFromPath(Utilities.captureScreenShot(driver));
			return false;
		}
	}

	public boolean enterPassword(WebDriver driver, String password) throws IOException {
		
		if (pwd.isDisplayed()) {
			pwd.sendKeys(password);
			log.info("enterPassword():  entered");
			test.pass("Password entered");
			return true;
		} else {
			log.error("enterPassword(): not entered");
			test.fail("password shopuld be entetred corrrectly");
			test.addScreenCaptureFromPath(Utilities.captureScreenShot(driver));
			return false;
		}
	}

	public void clearPassword() {
		pwd.clear();
	}

	public boolean clickloginButton(WebDriver driver) throws IOException {
		
		boolean isbutton = false;
		if (loginButton.isDisplayed()) {
			isbutton = true;
			loginButton.click();
			log.info("clickloginButton(): is clicked");
			test.pass("login button clicked");
		} else {
			test.fail("Login Button not clicked");
			test.addScreenCaptureFromPath(Utilities.captureScreenShot(driver));
			isbutton = false;
		}
		return isbutton;
	}

	public boolean clickRememberMe(WebDriver driver) throws IOException {
			boolean ischeckbox = false;
		if (rememberMe.isDisplayed()) {
			rememberMe.click();
			log.info("clickRememberMe(): remember me clicked");
			test.pass("remember me check box clicked");
			ischeckbox = true;
		} else {
			test.fail("remember me check box not clicked");
			test.addScreenCaptureFromPath(Utilities.captureScreenShot(driver));
			ischeckbox = false;
		}
		return ischeckbox;
	}

	public boolean clickForgotPassword(WebDriver driver) throws IOException {
		
		boolean isforgotpassword = false;
		if (forgotPwdlink.isDisplayed()) {
			isforgotpassword = true;
			forgotPwdlink.click();
			log.info("clickForgotPassword(): forgotPwdlink clicked");
			test.pass("clickForgotPassword(): forgotPwdlink clicked");
		} else {
			log.error("clickForgotPassword(): failed");
			test.fail("clickForgotPassword(): failed");
			test.addScreenCaptureFromPath(Utilities.captureScreenShot(driver));
			isforgotpassword = false;
		}
		return isforgotpassword;
	}

	public boolean isSavedUserNamelink(WebDriver driver) {
		boolean isSaved = false;
		if (savedUserNamelink.isDisplayed()) {
			isSaved = true;
		} else {
			isSaved = false;
		}
		return isSaved;
	}

	public boolean enterforgorPasswordformUserName(WebDriver driver) throws IOException {
		if (forgotPasswordFormUserName.isDisplayed()) {
			forgotPasswordFormUserName.sendKeys(PropertyReader.readLoginUsernamePassword("username"));
			test.pass("user name entered in the forgot password user name field");
			return true;
		} else {
			test.fail("user name not entered in the forgot password user name field");
			test.addScreenCaptureFromPath(Utilities.captureScreenShot(driver));
			return false;
		}
	}

	public boolean clickforgotPasswordFormContinue(WebDriver driver ) throws IOException {
		boolean isContinue = false;
		if (forgotPasswordFormContinue.isDisplayed()) {
			isContinue = true;
			forgotPasswordFormContinue.click();
			test.pass("clickforgotPasswordFormContinue");
		} else {
			test.fail("clickforgotPasswordFormContinue failed");
			test.addScreenCaptureFromPath(Utilities.captureScreenShot(driver));
			isContinue = false;
		}
		return isContinue;
	}

	public boolean clickforgotPasswordFormCancel(WebDriver driver ) throws IOException {
		boolean isCancel = false;
		if (forgotPasswordFormCancel.isDisplayed()) {
			isCancel = true;
			forgotPasswordFormCancel.click();
			test.pass("clickforgotPasswordFormCancel");
		} else {
			isCancel = false;
			test.fail("clickforgotPasswordFormCancel");
			test.addScreenCaptureFromPath(Utilities.captureScreenShot(driver));
		}
		return isCancel;
	}

	public boolean launchApp(WebDriver driver) throws IOException {
	
		driver.get(PropertyReader.readAppLaunchUrl("application_url"));
		test.pass("App launched successfully");
		return true;
	}

	public boolean freeTrial(WebDriver driver) throws IOException {
		test.fail("freeTrial");
		test.addScreenCaptureFromPath(Utilities.captureScreenShot(driver));
		return false;
	}

	public String getSavedUserName() {
		return savedUsername.getText();

	}

	public boolean forgotPwdPageTitle(WebDriver driver) throws IOException {
		boolean isPage = false;
		if (driver.getTitle().equals(PropertyReader.readpageTitle("forgot_password_page_title"))) {
			test.pass("Forgot password page title found");
			isPage = true;
		} else {
			test.fail("forgot password page not found");
			test.addScreenCaptureFromPath(Utilities.captureScreenShot(driver));
			isPage = false;
		}
		return isPage;
	}

	public void windowMaximize(WebDriver driver) {
		driver.manage().window().maximize();
	}

	public boolean forgotPwdRestMsg(WebDriver driver) throws IOException {
		boolean isMsg = false;
		if (forgotPwdResetMsg.getText()
				.contains("an email with a link to finish resetting your password.")) {
			test.pass("forgot password reset message displayed");
			isMsg = true;
		} else {
			test.fail("Correct Msg not displayed");
			test.addScreenCaptureFromPath(Utilities.captureScreenShot(driver));
			isMsg = false;
		}
		return isMsg;
	}

}
