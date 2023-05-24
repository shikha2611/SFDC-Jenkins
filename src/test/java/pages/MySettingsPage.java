package pages;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;

import utils.PropertyReader;
import utils.Utilities;

public class MySettingsPage extends BasePage {

	public MySettingsPage(WebDriver driver, ExtentTest test) {
		PageFactory.initElements(driver, this);
		BasePage.test = test;
	}

	@FindBy(xpath = "//*[text()='My Settings']")
	public WebElement mySettingNavigation;

	@FindBy(id = "PersonalInfo_font")
	public WebElement personal;

	@FindBy(xpath = "//a[@href='/servlet/servlet.LoginHistory?id=005Dn000002CIAF']")
	public WebElement loginHistoryFileDownload;

	@FindBy(id = "DisplayAndLayout_font")
	public WebElement DisplayLayout;

	@FindBy(id = "p4")
	public WebElement CustomAppDropDown;

	@FindBy(xpath = "//span[@class='folderText' and text()='My Settings']")
	public WebElement mySetting;

	@FindBy(css = "#duel_select_0")
	public WebElement DisplayLayoutCutomTabsAvailableTabs;

	@FindBy(css = "#bottomButtonRow input[value=' Save ']")
	public WebElement cutomizeMyTabsSave;

	@FindBy(css = "#EmailSetup_font")
	public WebElement email;

	@FindBy(xpath = "//div[@id='EmailSetup']//div[@class='childContainer']//div//span")
	public List<WebElement> emailOptions;

	@FindBy(xpath = "//input[@id='sender_name']")
	public WebElement emailName;

	@FindBy(xpath = "//input[@id='sender_name']")
	public WebElement emailAddress;

	@FindBy(xpath = "//tbody//tr[7]/td[2]/div")
	public WebElement radioButtonRequired;

	@FindBy(xpath = "//input[@name='auto_bcc']")
	public List<WebElement> BccRadioButtonOption;

	@FindBy(xpath = "//input[@value=' Save ']")
	public WebElement outgoingEmailSaveButton;

	@FindBy(css = "#CalendarAndReminders_font")
	public WebElement calendarReminder;

	@FindBy(id = "testbtn")
	public WebElement openTestReminderButton;

	public boolean clickPersonal(WebDriver driver) throws IOException {
		boolean isPersonalclicked;
		if (personal.isDisplayed()) {
			personal.click();
			test.pass("personal link clicked of MySettings");
			isPersonalclicked = true;
		} else {
			test.fail("personal link not clicked of MySettings");
			test.addScreenCaptureFromPath(Utilities.captureScreenShot(driver));
			isPersonalclicked = false;
		}
		return isPersonalclicked;

	}

	public boolean clickPersonalFileDownLoadLoginHistory(WebDriver driver) throws IOException {
		boolean isfiledownload = false;
		if (loginHistoryFileDownload.isDisplayed()) {
			loginHistoryFileDownload.click();
			test.pass("File download link clicked");
			isfiledownload = true;
		} else {
			test.fail("file download not clicked");
			test.addScreenCaptureFromPath(Utilities.captureScreenShot(driver));
			isfiledownload = false;
		}
		return isfiledownload;
	}

	public boolean clickmysettingOptions(WebDriver driver, String option) throws IOException {
		boolean isselected = false;
		if (mySetting.isDisplayed()) {
			driver.findElement(
					By.xpath("//div[@Id='AutoNumber5']//div[@class='parent']//span[@class='folderText' and text()='"
							+ option + "']"))
					.click();
			isselected = true;
			test.pass(option + " selected.");

		} else {
			test.fail(option + " not selected.");
			test.addScreenCaptureFromPath(Utilities.captureScreenShot(driver));
			isselected = false;

		}
		return isselected;
	}

	public boolean clickPersonalOptions(WebDriver driver, String option) throws IOException {
		boolean isclicked = false;
		if (personal.isDisplayed()) {
			driver.findElement(
					By.xpath("//div[@id='PersonalInfo']//div[@class='childContainer']//span[text()='" + option + "']"))
					.click();
			test.pass(option + "clicked");
			isclicked = true;
		} else {
			test.fail("Login History not clicked");
			test.addScreenCaptureFromPath(Utilities.captureScreenShot(driver));
			isclicked = false;

		}
		return isclicked;

	}

	public boolean dispalyAndLayOutOption(WebDriver driver, String selectoption) throws IOException {
		boolean isoption = false;
		if (DisplayLayout.isDisplayed()) {
			driver.findElement(By.xpath(
					"//div[@id='DisplayAndLayout']//div[@class='childContainer']//span[text()='" + selectoption + "']"))
					.click();
			test.pass("selected options is " + selectoption);
			isoption = true;

		} else {
			test.fail(selectoption + " is not selected");
			test.addScreenCaptureFromPath(Utilities.captureScreenShot(driver));
			isoption = false;
		}
		return isoption;

	}

	public boolean clickCalendarReminderOption(WebDriver driver, String selectoption) throws IOException {
		boolean isoption = false;
		if (calendarReminder.isDisplayed()) {
			driver.findElement(By.xpath("//div[@id='CalendarAndReminders']//div[@class='childContainer']//span[text()='"
					+ selectoption + "']")).click();
			test.pass("selected options is " + selectoption);
			isoption = true;

		} else {
			test.fail(selectoption + " is not selected");
			test.addScreenCaptureFromPath(Utilities.captureScreenShot(driver));
			isoption = false;
		}
		return isoption;

	}

	public boolean clickdisplayLayoutCustomMyTabCustomApp(WebDriver driver, String option) throws IOException {
		boolean isSelected = false;
		if (CustomAppDropDown.isDisplayed()) {
			Select sel = new Select(CustomAppDropDown);
			List<WebElement> actualOptions = sel.getOptions();
			for (WebElement ele : actualOptions) {
				if (ele.getText().equalsIgnoreCase(option)) {
					ele.click();
					break;
				}
			}
			test.pass(option + " selected from the dropdown custom app");
			isSelected = true;
		} else {
			test.fail(option + " not selected from the dropdown custom app");
			test.addScreenCaptureFromPath(Utilities.captureScreenShot(driver));
			isSelected = false;

		}
		return isSelected;
	}

	public boolean selectAvailableTabs(WebDriver driver, String optiontobeSelected) throws IOException {
		boolean isSelected = false;
		if (DisplayLayoutCutomTabsAvailableTabs.isDisplayed()) {
			Select sel = new Select(DisplayLayoutCutomTabsAvailableTabs);
			List<WebElement> options = sel.getOptions();
			for (WebElement ele : options) {
				if (ele.getText().equalsIgnoreCase(optiontobeSelected)) {
					ele.click();
					break;
				}
			}
			driver.findElement(By.xpath("//div[@class='zen-mbs text']//img[@alt='Add']")).click();
			cutomizeMyTabsSave.click();
			test.pass(optiontobeSelected + " is selected from the Available Tabs");
			isSelected = true;
		} else {
			test.fail(optiontobeSelected + " is not selected from the Available Tabs");
			test.addScreenCaptureFromPath(Utilities.captureScreenShot(driver));

		}
		return isSelected;

	}

	public boolean selecteMySettingEmailOptions(WebDriver driver, String option) throws IOException {
		boolean isSelected = true;
		if (email.isDisplayed()) {
			for (WebElement ele : emailOptions) {
				if (ele.getText().equalsIgnoreCase(option)) {
					ele.click();
					break;
				}
			}
			test.pass(option + " seleccted from the email options");
			isSelected = true;
		} else {
			test.fail(option + " not selected in the email");
			test.addScreenCaptureFromPath(Utilities.captureScreenShot(driver));
			isSelected = false;
		}
		return isSelected;

	}

	public boolean setOutgoingEmailSettings(WebDriver driver, String name) throws IOException {
		boolean isSetting = false;
		if (emailName.isDisplayed()) {
			emailName.clear();
			emailName.sendKeys(name);
			test.pass("changed to " + name + " in the email name field");
			isSetting = true;
		} else {
			test.fail("name not changed to " + name + " in the email name field");
			test.addScreenCaptureFromPath(Utilities.captureScreenShot(driver));
			isSetting = false;
		}
		return isSetting;
	}

	public boolean selectBCCRadioButton(WebDriver driver, String num) throws IOException {
		boolean isyes = false;
		if (radioButtonRequired.isDisplayed()) {
			driver.findElement(By.xpath("//input[@name='auto_bcc' and @value=" + num + "]")).click();
			test.pass(num + " radio button selected as an option in BCC Email ");
			isyes = true;
		} else {
			isyes = false;
			test.fail(num + " radio button not selected as an option in BCC Email ");
			test.addScreenCaptureFromPath(Utilities.captureScreenShot(driver));
		}
		return isyes;

	}

	public void OutGoingEmailSave() {
		outgoingEmailSaveButton.click();
	}

	public String verifyEmailSettingSaveSuccessMsg(WebDriver driver) {
		return driver.findElement(By.xpath("//div[@class='messageText']")).getText();
	}

	public void testReminderButton(WebDriver driver) throws IOException, InterruptedException {
		if (openTestReminderButton.isDisplayed()) {
			openTestReminderButton.click();
			Thread.sleep(3000);
			Set<String> winhdl = driver.getWindowHandles();
			ArrayList<String> al = new ArrayList<>();
			Iterator<String> itr = winhdl.iterator();
			while (itr.hasNext()) {
				al.add(itr.next());
			}
			driver.switchTo().window(al.get(1));
			Thread.sleep(6000);
			String actual = driver.getTitle();
			String extected = PropertyReader.readpageTitle("my_settings_calendarReminder_testReminder");
			System.out.println("Title is :" + actual);
			Assert.assertEquals(actual, extected);
			test.pass("Open Reminder button clicked and Reminder set");
		} 
		else {
			test.fail("Open Reminder button not clicked and Reminder not set");
			test.addScreenCaptureFromPath(Utilities.captureScreenShot(driver));
		}
	}
	
	public boolean verifyEmailNameAndEmailAddress(WebDriver driver, String name, String address) {
		boolean isVerified=false;
		WebElement emailNameEle= driver.findElement(By.xpath("//input[@value='"+name+"']"));
		String emailname=emailNameEle.toString();
		if(emailname.contains(name)){
			isVerified=true;
		}
		
		else {
			isVerified=false;
		}
		WebElement emailAddEle = driver.findElement(By.xpath("//input[@value='"+address+"']"));
		String emailAdd =emailAddEle.toString();
		if(emailAdd.contains(address)){
			isVerified=true;
		}else {
			isVerified=false;
		}
		return isVerified;
	}

}
