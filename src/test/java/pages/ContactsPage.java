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
import org.testng.Assert;
import com.aventstack.extentreports.ExtentTest;
import utils.PropertyReader;
import utils.Utilities;


public class ContactsPage extends BasePage {

	public ContactsPage(WebDriver driver, ExtentTest test) {
		PageFactory.initElements(driver, this);
		BasePage.test = test;

	}

	@FindBy(css = "#name_lastcon2")
	public WebElement contactLastName;

	@FindBy(css = "#con4")
	public WebElement accountNameTextBox;

	@FindBy(xpath = "//a[@title='Account Name Lookup (New Window)']")
	public WebElement accountNameLookup;

	@FindBy(xpath = "//frame[@title='Search']")
	public WebElement frameSearch;

	@FindBy(xpath = "//a[@title='Account Name Lookup (New Window)']")
	public WebElement goButton;

	@FindBy(xpath = "//frame[@title='Results']")
	public WebElement frameaccountLookupResult;

	@FindBy(xpath = "//table[@class='list']//tr//a")
	public List<WebElement> accountList;

	@FindBy(xpath = "//input[@title='New']")
	public WebElement newContactButton;

	@FindBy(xpath = "//td[@id='topButtonRow']//input[@title='Save']")
	public WebElement topSaveButton;

	@FindBy(xpath = "//th[@class=' dataCell  ']/a")
	public List<WebElement> contactList;

	public boolean createNewContact(WebDriver driver) throws IOException, InterruptedException {
		log.info("createNewContact(): is inititated");
		boolean isContactCreated = false;
		if (newContactButton.isDisplayed()) {

			newContactButton.click();
			log.debug("new contact button is clicked");
			Utilities.dynamicExplicitElementWait(driver, contactLastName);
			String lastname = Utilities.randomNameGeneration();
			contactLastName.sendKeys(lastname);
			log.info("last name sent as" + lastname);

			accountNameTextBox.sendKeys(PropertyReader.readKeywordLookup("particular_account_to_be_looked"));
			log.info("entered account name as :" + PropertyReader.readKeywordLookup("particular_account_to_be_looked"));
			goButton.click();
			log.info("goButton is clicked");
			Set<String> windows = driver.getWindowHandles();
			Iterator<String> itr = windows.iterator();
			List<String> str = new ArrayList<>();
			while (itr.hasNext()) {
				str.add(itr.next());
			}
			driver.switchTo().window(str.get(1));

//			Utilities.dynamicExplicitElementWait(driver,
//					driver.findElement(By.xpath("//input[@placeholder='Search...']")));
			Assert.assertEquals(driver.getTitle(), PropertyReader.readpageTitle("account_lookup_search_page"));
			log.info("driver switched to account_lookup_search_page");

			driver.switchTo().frame(frameaccountLookupResult);
			for (WebElement ele : accountList) {
				if (ele.getText().equals(PropertyReader
						.readKeywordLookup(PropertyReader.readKeywordLookup("particular_account_to_be_looked")))) {
					ele.click();

					break;
				}
			}
			log.info("account is selected as per the criteria");
			driver.switchTo().window(str.get(0));
			Utilities.dynamicExplicitElementWait(driver, driver.findElement(By.xpath("//h2[text()=' New Contact']")));
			Assert.assertTrue(Utilities.pageTitleVerification(driver.getTitle(),
					PropertyReader.readpageTitle("new_contact_page")));
			log.info("driver switched to parent window");
			WebElement ele = driver.findElement(By.cssSelector("#con4"));
			Assert.assertEquals(ele.getAttribute("value"),
					PropertyReader.readKeywordLookup("particular_account_to_be_looked"));
			log.info(PropertyReader.readKeywordLookup("particular_account_to_be_looked")
					+ " is selected in account name");
			Utilities.dynamicExplicitElementWait(driver, topSaveButton);
			topSaveButton.click();
			Thread.sleep(2000);

			for (WebElement contact : contactList) {
				if (contact.getText().equals(lastname)) {
					log.info("new contact created with the last name as " + lastname);
					break;
				}
			}

			log.info("New Contact created and saved");
			test.pass("New Contact created and saved");
			isContactCreated = true;

		} else {
			isContactCreated = false;
			test.fail("New Contact not created");
			test.addScreenCaptureFromPath(Utilities.captureScreenShot(driver));
		}
		return isContactCreated;
	}

}
