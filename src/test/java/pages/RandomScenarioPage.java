package pages;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;

import utils.Utilities;

public class RandomScenarioPage extends BasePage {

	public RandomScenarioPage(WebDriver driver, ExtentTest test) {
		PageFactory.initElements(driver, this);
		BasePage.test = test;
	}

	@FindBy(xpath = "//span[@class='pageDescription']/a")
	public WebElement currentDateLink;

	@FindBy(xpath = "//a[text()='[Share My Calendar]']")
	public WebElement shareMyCalendar;

	@FindBy(xpath = "//a[contains(text(),'6:00 AM')]")
	public WebElement timeLink;

	@FindBy(xpath = "//a[@Title='Combo (New Window)']")
	public WebElement comboNewWindow;

	@FindBy(xpath = "//a[contains(text(),'Other')]")
	public WebElement other;

	@FindBy(xpath = "//input[@id='EndDateTime_time']")
	public WebElement endTime;

	@FindBy(css = "#IsRecurrence")
	public WebElement recurrenceCheckBox;

	@FindBy(css = "#rectypeftw")
	public WebElement weeklyRadioButton;

	@FindBy(xpath = "//input[@id='wi' and @value='1']")
	public WebElement recursEveryTextBox;

	@FindBy(xpath = "//input[@id='8']")
	public WebElement weekDayCheckBox;

	@FindBy(css = "#RecurrenceEndDateOnly")
	public WebElement RecurrenceEndDate;

	@FindBy(xpath = "//table[@class='calDays']//td[@class='weekday' or @class='weekday todayDate selectedDate' or @class='weekend']")
	public WebElement currentMonthDays;

	@FindBy(xpath = "//h1[text()='Calendar for Shikha Pandey - Day View']")
	public WebElement calendarHeader;

	@FindBy(xpath = "//td[@id='bottomButtonRow']/input[1]")
	public WebElement bottomSaveButton;

	@FindBy(xpath = "//a[@title='Month View - Selected']/img")
	public WebElement monthViewIcon;

	int parsedcurrentdate;

	public boolean clickCurrentDateLink() {
		log.info("clickCurrentDateLink()....Initialized");
		boolean isclicked = false;
		if (currentDateLink.isDisplayed()) {
			currentDateLink.click();
			isclicked = true;
			test.pass("currentDateLink()..is clicked");
			log.info("currentDateLink()..is clicked");
		} else {
			log.error("currentDateLink()..is not clicked");
			test.fail("currentDateLink()..is not clicked");
			isclicked = false;
		}
		log.info("clickCurrentDateLink()....Ended");
		return isclicked;
	}

	public boolean clickTime(WebDriver driver, String time) throws IOException {
		log.info("clickTime()....Initialized");
		boolean isTimeDisplayed = false;
		if (timeLink.isEnabled()) {
			driver.findElement(By.xpath("//a[contains(text(),'" + time + "')]")).click();
			isTimeDisplayed = true;
			log.info("Correct time clicked");
			test.pass("Correct time clicked");
		} else {
			isTimeDisplayed = false;
			log.error("Time not clicked");
			test.fail("Time not clicked");
			test.addScreenCaptureFromPath(Utilities.captureScreenShot(driver));
		}
		return isTimeDisplayed;
	}

	public boolean clickComboNewWindow() {
		log.info("clickComboNewWindow().....Initialized");
		boolean isButtonClicked = false;
		if (comboNewWindow.isEnabled()) {
			log.info("clickComboNewWindow clicked.....");
			comboNewWindow.click();
			test.pass("clickComboNewWindow clicked.....");
			isButtonClicked = true;
		} else {
			isButtonClicked = false;
			test.fail("clickComboNewWindow is not clicked.....");
		}
		return isButtonClicked;
	}

	public boolean clickNewWindowOtherLink(WebDriver driver) throws IOException {
		log.info("clickNewWindowOtherLink()....Initialized");
		boolean isOtherLinkclicked = false;
		if (other.isEnabled()) {
			other.click();
			isOtherLinkclicked = true;
			test.pass("Other link Clicked");
			log.info("Other link clicked");
		} else {
			isOtherLinkclicked = false;
			test.fail("Other link not clicked");
			log.error("clickNewWindowOtherLink()");
			test.addScreenCaptureFromPath(Utilities.captureScreenShot(driver));
		}
		return isOtherLinkclicked;
	}

	public boolean selectEndTime(WebDriver driver, String time) {
		log.info("selectEndTime()....Initialized");
		boolean isclicked = false;
		if (endTime.isDisplayed()) {
			endTime.click();
			driver.findElement(By.xpath("//div[text()='" + time + "']")).click();
			isclicked = true;
			test.pass("Time selected as " + time);
			log.info("Time selected as " + time);
		} else {
			isclicked = false;
			test.fail("Time didn't get selected as " + time);
			log.error("Time didn't get selected as " + time);
		}
		return isclicked;
	}

	public boolean clickRecurrenceCheckBox() {
		boolean isclicked = false;
		if (recurrenceCheckBox.isDisplayed()) {
			recurrenceCheckBox.click();
			isclicked = true;
			test.pass("Recurrence Check Box is Clicked");
		} else {
			isclicked = false;
			test.fail("Recurrence Check Box is not clicked");
		}
		return isclicked;
	}

	public boolean verifySectionAvailable(WebDriver driver, String text) {
		WebElement label = driver.findElement(By.xpath("//label[text()='Frequency']"));
		Utilities.dynamicExplicitElementWait(driver, label);
		boolean isSectionAvailable = false;
		String actualText = driver.findElement(By.xpath("//label[text()='" + text + "']")).getText();
		if (actualText.equalsIgnoreCase(text)) {
			isSectionAvailable = true;
			test.pass("Frequency label found ");
		} else {
			isSectionAvailable = false;
			test.fail("Frequency label not found");
		}
		return isSectionAvailable;
	}

	public boolean selectweeklyRadioButton() {
		boolean isSelectedAndFound = false;
		if (weeklyRadioButton.isDisplayed()) {
			weeklyRadioButton.click();
			isSelectedAndFound = true;
			test.pass("weekly Radio Button selected");
		} else {
			test.fail("weekly Radio Button not selected");
			isSelectedAndFound = false;
		}
		return isSelectedAndFound;
	}

	public boolean verifyRecuresEveryWeek(WebDriver driver, String recursValueTextBox) {
		boolean isCorrect = false;
		if (recursEveryTextBox.isDisplayed()) {
			String actualvalue = recursEveryTextBox.getAttribute("value");
			if (actualvalue.equals(recursValueTextBox)) {
				test.pass("Number Found in the recurs every week as  " + recursValueTextBox);
				isCorrect = true;
			} else {
				test.fail("Number not found 1 in the recures every week text box");
				isCorrect = false;
			}
		}
		return isCorrect;
	}

	public boolean verifyCurrentDayCheckBox(WebDriver driver, int checkBoxid) {
		boolean isSelected = false;
		if (weekDayCheckBox.isDisplayed()) {
			WebElement ele = driver.findElement(By.xpath("//input[@id='" + checkBoxid + "']"));
			if (ele.isSelected()) {
				isSelected = true;
				test.pass("Correct checbox  selected");
			} else {
				isSelected = false;
				test.fail("Correct checbox not selected");
			}
		}
		return isSelected;
	}

	public boolean selectTwoWeeksDate(WebDriver driver) {
		boolean isSelected = false;
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.WEEK_OF_YEAR, 2);
		Date twoWeeksFromNow = cal.getTime();
		System.out.println("two weeks from now the date will be..... " + twoWeeksFromNow);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = dateFormat.format(twoWeeksFromNow);
		String dateOnly = dateString.split("-")[2];
		parsedcurrentdate = Integer.parseInt(dateOnly);
		List<WebElement> dates = driver.findElements(By
				.xpath("//table[@class='calDays']//td[not(@class='weekday prevMonth' or @class='weekend prevMonth')]"));
		for (WebElement ele : dates) {
			String text = ele.getText();
			int parsedTewoWeeksDate = Integer.parseInt(text);
			if (parsedcurrentdate == parsedTewoWeeksDate) {
				ele.click();
				isSelected = true;
				test.pass("date after two weeks got selected");
				break;
			}
		}
		return isSelected;

	}

	public boolean verifyTheDateSelected(WebDriver driver) {
		boolean isCorrect = false;
		driver.findElement(By.cssSelector("#RecurrenceEndDateOnly")).click();
		String text = driver.findElement(By.xpath("//*[contains(@class, 'selectedDate')]")).getText();
		int value = Integer.parseInt(text);
		if (value == parsedcurrentdate) {
			isCorrect = true;
			test.pass("End date selection validation passed");
		} else {
			isCorrect = false;
			test.fail("End date selection validation failed");
		}
		return isCorrect;

	}

	public boolean clickRecurrenceEndDate(WebDriver driver) throws IOException {
		boolean isClicked = false;
		if (RecurrenceEndDate.isDisplayed()) {
			RecurrenceEndDate.click();
			test.pass("endDate clicked..");
			log.info("");
			isClicked = true;
		} else {
			test.fail("endDate not clicked..");
			log.error("");
			test.addScreenCaptureFromPath(Utilities.captureScreenShot(driver));
			isClicked = false;
		}
		return isClicked;
	}

	public boolean clickBottomSaveButton(WebDriver driver) {
		boolean isClicked = false;
		Utilities.dynamicExplicitElementWait(driver, bottomSaveButton);
		if (bottomSaveButton.isEnabled()) {
			bottomSaveButton.click();
			isClicked = true;
			test.pass("botton save button clicked");
		} else {
			isClicked = false;
			test.fail("bottom save button not clicked");
		}
		return isClicked;
	}

	public boolean VerifyEventAdded(WebDriver driver, String expecteEvent, String expectedstartTime,
			String expectedEndTime) throws InterruptedException {
		boolean isEventAvailable = false;
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(By.xpath("//*[contains(@class,'multiLineEventBlock ')]"))).build()
				.perform();
		Thread.sleep(3000);
		String actualEvent = driver
				.findElement(By.xpath("(//div[@class='hoverOuter']//*[contains(@class,'bPageBlock ')]//td)[6]"))
				.getText();
		String actualstartTime = driver
				.findElement(By.xpath("(//div[@class='hoverOuter']//*[contains(@class,'bPageBlock ')]//td)[12]"))
				.getText();
		String actualEndTime = driver
				.findElement(By.xpath("(//div[@class='hoverOuter']//*[contains(@class,'bPageBlock ')]//td)[14]"))
				.getText();
		if (actualEvent.equalsIgnoreCase(expecteEvent) && actualstartTime.contains(expectedstartTime)
				&& actualEndTime.contains(expectedEndTime)) {
			isEventAvailable = true;
			test.pass("Evevent Available and showing the right time");
		} else {
			isEventAvailable = false;
			test.fail("Evevent not Available and not showing the right time");
		}
		return isEventAvailable;
	}

}
