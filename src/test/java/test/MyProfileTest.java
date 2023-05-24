package test;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;

import pages.LoginPage;
import pages.MyProfilePage;
import pages.UserMenuPage;
import utils.PropertyReader;
import utils.Utilities;

public class MyProfileTest extends BaseTest {

	@Test(groups= {"system"})
	public void MyProfileTC_06() throws IOException, InterruptedException {
		WebDriver driver = BaseTest.getDriver();
		ExtentTest test = extent.createTest("MyProfile TC06");
		LoginPage lp = new LoginPage(driver, test);
		UserMenuPage ump = new UserMenuPage(driver, test);
		MyProfilePage myp = new MyProfilePage(driver, test);
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
		Utilities.dynamicExplicitElementWait(driver, myp.MyProfile);
		Assert.assertTrue(ump.selectUserMenuOption(driver, PropertyReader.readUserMenu("option_to_be_selected")),
				"Correct option should be selected");
		Thread.sleep(5000);
		Utilities.dynamicExplicitElementWait(driver, myp.editProfile);
		Assert.assertTrue(myp.clickEditProfilePen(driver), "edit profile pen should be clicked");
		Utilities.dynamicExplicitElementWait(driver, myp.editWindowPopUp);
		Assert.assertTrue(myp.VerifyEditProfileOptions(driver), "Both the options should be displayed ");
		Utilities.dynamicExplicitElementWait(driver, myp.editProfileAboutTab);
		Assert.assertTrue(myp.clickEditProfileAboutTab(driver), "About Tab of edit profile should be clicked");
		Assert.assertTrue(myp.enterEditProfileLastName(driver, PropertyReader.readUserMenu("last_name")),
				"Last name should be entered");
		Utilities.dynamicExplicitElementWait(driver, myp.editProfileAboutTabSaveAll);
		myp.editProfileAboutTabSaveAll.click();
		driver.switchTo().parentFrame();
		Utilities.dynamicExplicitElementWait(driver, ump.userMenu);
		Assert.assertTrue(myp.VerifyMyProfilePageTitle(driver, PropertyReader.readUserMenu("last_name")),
				"Page tile should be changed");
		Utilities.dynamicExplicitElementWait(driver, myp.publisherFeed);
		Assert.assertTrue(
				myp.selectpublisherFeedItemChoices(driver, PropertyReader.readPublisherFeedChoices("choice1")),
				"Post as a choice should be selected");
		Utilities.dynamicExplicitElementWait(driver, myp.sharebutton);

		// PostShare
		Assert.assertTrue(myp.doPostShare(driver, PropertyReader.readPublisherFeedChoices("post_to_text")),
				"Text for the post should be shared");
		Thread.sleep(5000);
		Assert.assertEquals(myp.verifyPostText(driver, PropertyReader.readPublisherFeedChoices("post_to_text")),
				PropertyReader.readPublisherFeedChoices("post_to_text"));
		Utilities.dynamicExplicitElementWait(driver, myp.publisherFeed);
		Assert.assertTrue(
				myp.selectpublisherFeedItemChoices(driver, PropertyReader.readPublisherFeedChoices("choice2")),
				"File Choice from the feed shoud be selected");
		Utilities.dynamicExplicitElementWait(driver, myp.publisherFeed);
		Thread.sleep(5000);

		// FileUpload
		Assert.assertTrue(myp.doUploadFile(driver, PropertyReader.readPublisherFeedChoices("file_path"),
				PropertyReader.readPublisherFeedChoices("file_name")), "File should be uploaded");
		Utilities.dynamicExplicitElementWait(driver, myp.publisherFeed);
		Thread.sleep(3000);
       //photoUpload
		Assert.assertTrue(myp.doPhotoUpload(driver, PropertyReader.readPublisherFeedChoices("photo_file_path")),
				"photo should be uploaded");
	}

}
