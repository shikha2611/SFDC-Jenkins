package pages;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;

import utils.PropertyReader;
import utils.Utilities;

public class MyProfilePage extends BasePage {

	public MyProfilePage(WebDriver driver, ExtentTest test) {
		PageFactory.initElements(driver, this);
		BasePage.test = test;

	}

	@FindBy(css = "#contactInfoTitle")
	public WebElement editWindowPopUp;

	@FindBy(xpath = "//*[text()='My Profile']")
	public WebElement MyProfile;

	@FindBy(xpath = "//div[@class='contactInfo profileSection']//img[@alt='Edit Profile']")
	public WebElement editProfile;

	@FindBy(css = "#contactInfoContentId")
	public WebElement editProfilePopUpFrameWindow;

	@FindBy(xpath = "//div[@class='zen-header']//li")
	public List<WebElement> editProfileOptions;

	@FindBy(xpath = "//div[@class='zen-header']//li[1]")
	public WebElement editProfileAboutTab;

	@FindBy(xpath = "//div[@class='zen-header']//li[2]")
	public WebElement editProfileContactTab;

	@FindBy(css = "#lastName")
	public WebElement editProfileAboutTabLastName;

	@FindBy(xpath = "//input[@value='Save All']")
	public WebElement editProfileAboutTabSaveAll;

	@FindBy(css = "ul.publisherFeedItemTypeChoices li.publisherFeedItemTypeChoice")
	public List<WebElement> publisherFeedItemTypeChoices;

	@FindBy(css = "ul.publisherFeedItemTypeChoices")
	public WebElement publisherFeed;

	@FindBy(xpath = "//iframe[@class='cke_wysiwyg_frame cke_reset']")
	public WebElement postTextiFrame;

	@FindBy(xpath = "//*[@id='publishersharebutton']")
	public WebElement sharebutton;

	@FindBy(xpath = "//body[text()='Share an update, @mention someone...']")
	public WebElement postWriteText;

	@FindBy(xpath = "//a[@id='chatterUploadFileAction']")
	public WebElement uploadfilecomputer;

	@FindBy(xpath = "//*[@id='chatterFile']")
	public WebElement chooseFile;

	@FindBy(xpath = "//a[@id='uploadLink']")
	public WebElement photoUpload;

	public boolean VerifyMyProfilePageTitle(WebDriver driver, String text) {
		boolean isChanged = false;
		String expected = driver.findElement(By.xpath("(//span[contains(text(),'" + text + "')])[2]")).getText();
		String title = driver.getTitle();
		if (title.contains(expected)) {
			isChanged = true;
			test.pass("Page Title changed");
		} else {
			isChanged = false;
			test.fail("Page Title not changed");
		}
		return isChanged;
	}

//	public void selectEditProfileOption(WebDriver driver) throws InterruptedException {
//		Thread.sleep(5000);
//		driver.findElement(By.xpath("//div[@class='editPen']//a[@class='contactInfoLaunch editLink']//img")).click();
//	
//	
//	
//	}

	public boolean VerifyEditProfileOptions(WebDriver driver) throws IOException {
		boolean isOptions = true;
		Utilities.dynamicExplicitElementWait(driver, editProfilePopUpFrameWindow);
		driver.switchTo().frame(editProfilePopUpFrameWindow);
		test.pass("Switched to frame");
		String[] options = { "About", "Contact" };
		for (int i = 0; i < editProfileOptions.size(); i++) {
			String actual = editProfileOptions.get(i).getText();
			if (actual.contains(options[i])) {
				test.pass(options[i] + " found");
				System.out.println(options[i] + " found");
			} else {
				test.fail("didnot switch to frame");
				test.fail(options[i] + " not found");
				test.addScreenCaptureFromPath(Utilities.captureScreenShot(driver));
				System.out.println(options[i] + " not found");
				isOptions = false;
			}
		}
		return isOptions;

	}

	public boolean clickEditProfileAboutTab(WebDriver driver) throws IOException {
		boolean isAbout = false;
		if (editProfileAboutTab.isDisplayed()) {
			editProfileAboutTab.click();
			test.pass("About tab of edit profile is clicked");
			isAbout = true;
		} else {
			test.fail("About tab of edit profile is not clicked");
			test.addScreenCaptureFromPath(Utilities.captureScreenShot(driver));
			isAbout = false;
		}
		return isAbout;
	}

	public boolean enterEditProfileLastName(WebDriver driver, String lastName) throws IOException {

		boolean isLastName = false;
		if (editProfileAboutTabLastName.isDisplayed()) {
			editProfileAboutTabLastName.clear();
			editProfileAboutTabLastName.sendKeys(lastName);
			test.pass("last name send as " + lastName);
			isLastName = true;
		} else {
			test.fail("Last Name not sent");
			test.addScreenCaptureFromPath(Utilities.captureScreenShot(driver));
			isLastName = false;
		}
		return isLastName;
	}

	public boolean clickEditProfilePen(WebDriver driver) throws IOException {
		boolean isedit = false;
		if (editProfile.isDisplayed()) {
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("arguments[0].click();", editProfile);
			test.pass("edit profile pen clicked");
			isedit = true;

		} else {
			test.fail("edit profile pen not clicked");
			test.addScreenCaptureFromPath(Utilities.captureScreenShot(driver));
			isedit = false;
		}
		return isedit;
	}

	public boolean selectpublisherFeedItemChoices(WebDriver driver, String choice) throws IOException {
		boolean ischoice = false;
		String actual = driver.findElement(By.xpath(
				"//ul[@class='publisherFeedItemTypeChoices']//li[@class='publisherFeedItemTypeChoice']//span[text()='"
						+ choice + "']"))
				.getText();
		if (publisherFeed.isDisplayed()) {
			for (int i = 0; i < publisherFeedItemTypeChoices.size(); i++) {
				if (publisherFeedItemTypeChoices.get(i).getText().equals(actual)) {
					publisherFeedItemTypeChoices.get(i).click();
					test.pass("Correct choice selected");
					ischoice = true;
					break;
				}
			}
		} else {
			test.fail("Not selected the choice");
			test.addScreenCaptureFromPath(Utilities.captureScreenShot(driver));
			ischoice = false;
		}
		return ischoice;
	}

	public boolean doPostShare(WebDriver driver, String text) throws IOException, InterruptedException {
		boolean isShare = false;
		if (publisherFeed.isDisplayed()) {
			Actions action = new Actions(driver);
			action.click(postTextiFrame).build().perform();
			action.sendKeys(text);
			action.click(sharebutton).build().perform();
			isShare = true;
			test.pass("Post text is entered");
		} else {
			test.fail("Post text not entered");
			test.addScreenCaptureFromBase64String(Utilities.captureScreenShot(driver));
			isShare = false;
		}
		return isShare;
	}

	public String verifyPostText(WebDriver driver, String text) {
		return driver.findElement(By.xpath("//p[text()='" + text + "']")).getText();

	}

	public boolean doUploadFile(WebDriver driver, String filePath, String fileName) throws IOException {
		boolean isupload = false;
		if (publisherFeed.isDisplayed()) {
			uploadfilecomputer.click();
			Utilities.dynamicExplicitElementWait(driver, chooseFile);
			chooseFile.sendKeys(filePath);
			Actions action = new Actions(driver);
			action.click(sharebutton).build().perform();
			if (driver.findElement(By.xpath("(//span[@title='" + fileName + "'])[1]")).getText().equals(fileName)) {
				test.pass("File uploaded");
				isupload = true;
			}
		} else {
			test.fail("File not uploaded");
			test.addScreenCaptureFromPath(Utilities.captureScreenShot(driver));
			isupload = false;
		}
		return isupload;
	}

	public boolean doPhotoUpload(WebDriver driver, String photofilepath) throws InterruptedException, IOException {
		boolean isupload = false;
		if (publisherFeed.isDisplayed()) {
			Actions action = new Actions(driver);
			action.click(photoUpload).build().perform();
			driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='uploadPhotoContentId']")));
			Thread.sleep(3000);
			driver.findElement(By.xpath("//*[@id='j_id0:uploadFileForm:uploadInputFile']")).sendKeys(photofilepath);
			driver.findElement(By.xpath("//input[@id='j_id0:uploadFileForm:uploadBtn']")).click();
			Thread.sleep(3000);
			action.click(driver.findElement(By.xpath("//input[@id='j_id0:j_id7:save']"))).build().perform();
			Thread.sleep(2000);
//			action.contextClick(driver.findElement(By.xpath("//div[@class='photoUploadSection']//a[1]"))).build()
//					.perform();
			isupload = true;
		} else {
			isupload = false;
		}
		return isupload;

	}

}
