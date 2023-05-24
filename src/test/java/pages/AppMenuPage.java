package pages;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.aventstack.extentreports.ExtentTest;

import utils.PropertyReader;
import utils.Utilities;

public class AppMenuPage extends BasePage {

	public AppMenuPage(WebDriver driver, ExtentTest test) {
		PageFactory.initElements(driver, this);
		BasePage.test = test;
	}

	@FindBy(css = "#tsidButton")
	public WebElement appMenuNavigation;

	@FindBy(xpath = "//ul[@id='tabBar']//a")
	public List<WebElement> allTabsLink;

	@FindBy(css = "#tabBar")
	public WebElement tabBar;

	@FindBy(css = "input[value=' New ']")
	public WebElement newAccountButton;

	@FindBy(css = "input#acc2")
	public WebElement newAccountnametextbox;

	@FindBy(xpath = "//td[@id='topButtonRow']/*[@value=' Save ' and @type='submit']")
	public WebElement newAccountTopSaveButton;

	@FindBy(xpath = "//div[@class='textBlock']//h2")
	public WebElement newAccountName;

	@FindBy(css = "span[class='fFooter']>a:nth-of-type(2)")
	public WebElement createNewViewLink;

//	@FindBy(css = "input#fname")
//	public WebElement VieNameTextBox;

	@FindBy(xpath = "//input[@id='fname']")
	public WebElement VieNameTextBox;

	@FindBy(xpath = "(//td[@class='pbButtonb'])[1]/input[@value=' Save ']")
	public WebElement newViewSaveButton;

	@FindBy(css = "select[name='fcf']")
	public WebElement ViewAddedDropDown;

	@FindBy(xpath = "//input[@id='devname']")
	public WebElement ViewUniqueNameTextBox;

	@FindBy(xpath = "//a[text()='Edit']")
	public WebElement editView;

	@FindBy(css = "select#fcol1")
	public WebElement filterFieldDropDown;

	@FindBy(css = "select#fop1")
	public WebElement filterOperatorDropDown;

	@FindBy(css = "input#fval1")
	public WebElement filterValueTextBox;

	@FindBy(css = "select#colselector_select_0")
	public WebElement availableFields;

	@FindBy(css = "a#colselector_select_0_right img[class='rightArrowIcon']")
	public WebElement addArrow;

	@FindBy(xpath = "//tr[@class='x-grid3-hd-row']//child::div")
	public List<WebElement> columnsTitle;

	@FindBy(xpath = "//a[text()='Merge Accounts']")
	public WebElement MergeAccountLink;

	@FindBy(css = "input#srch")
	public WebElement findMergeAccountsTextBox;

	@FindBy(css = "input[value='Find Accounts'][type='submit']")
	public WebElement findMergeAccountNextButton;

	@FindBy(xpath = "//th[@scope='row']/input")
	public List<WebElement> selectMergeAccountCheckbox;

	@FindBy(css = "div[class='pbBottomButtons']>input[value=' Next ']")
	public WebElement MergeAccountsNextButton;

	@FindBy(css = "div[class='pbTopButtons'] :nth-child(2)")
	public WebElement MergeAccountTopButton;

	@FindBy(xpath = "//tbody/tr/th/a")
	public List<WebElement> AccountsList;

	

	public boolean selectAppMenuDropDown(WebDriver driver, String option) throws IOException {
		boolean isSelected = false;
		if (appMenuNavigation.isDisplayed()) {
			driver.findElement(By.xpath("//div[@id='toolbar']//*[text()='" + option + "']")).click();
			test.pass(option + " is selected from the app Menu");
			isSelected = true;
		} else {
			test.fail(option + " not selected from the ap  menu");
			test.addScreenCaptureFromPath(Utilities.captureScreenShot(driver));
			isSelected = false;
		}
		return isSelected;
	}

	public boolean verifyAllTabsforSelectedAppMenu(WebDriver driver, String optionAvailable) throws IOException {
		boolean istotalCount = false;
		if (tabBar.isDisplayed()) {
			for (WebElement ele : allTabsLink) {
				if (ele.getText().equalsIgnoreCase(optionAvailable)) {
					test.pass(optionAvailable + " is added in the tab Bar");
					break;
				}
			} 
			istotalCount = true;
		} else {
			test.fail(optionAvailable + " is not added in the tabBar");
			test.addScreenCaptureFromPath(Utilities.captureScreenShot(driver));
			istotalCount = false;
		}
		return istotalCount;
	}

	public boolean clickTabFromTabBar(WebDriver driver, String tab) throws IOException {
		boolean istabclicked = false;
		log.debug("clickTabFromTabBar(): Intiated");
		if (tabBar.isDisplayed()) {
			for (WebElement ele : allTabsLink) {
				if (ele.getText().equalsIgnoreCase(tab)) {
					ele.click();
					log.info("clickTabFromTabBar() " + tab + " is clicked");
					test.pass(tab + " is clicked in the tab Bar");
					break;
				}
			}
			istabclicked = true;
		} else {
			log.error("clickTabFromTabBar() " + tab + " is not clicked");
			test.fail(tab + " is not clicked in the tabBar");
			test.addScreenCaptureFromPath(Utilities.captureScreenShot(driver));
			istabclicked = false;
		}
		return istabclicked;
	}

	public String createNewAccount(WebDriver driver) throws IOException {
		String name = Utilities.randomNameGeneration();
		if (newAccountnametextbox.isDisplayed()) {
			newAccountnametextbox.sendKeys(name);
			newAccountTopSaveButton.click();
			test.pass("New Account Created");
		} else {
			test.fail("New Account not Created");
			test.addScreenCaptureFromPath(Utilities.captureScreenShot(driver));
		}
		return name;
	}

	public boolean clickCreateNewView(WebDriver driver) throws IOException {
		log.debug("createNewView(): Intiated");
		boolean isViewadded = false;
		String viewname = null;
		if (createNewViewLink.isDisplayed()) {
			createNewViewLink.click();
			log.info("createNewView link is clicked");
			Utilities.dynamicExplicitElementWait(driver, VieNameTextBox);
			viewname = Utilities.randomNameGeneration();
			VieNameTextBox.sendKeys(viewname);
			Actions action = new Actions(driver);
			action.keyDown(Keys.TAB).build().perform();
			action.keyUp(Keys.TAB).build().perform();
			newViewSaveButton.click();
			log.debug(" New View Save Button clicked");
			Utilities.dynamicExplicitElementWait(driver, ViewAddedDropDown);
			Select sel = new Select(ViewAddedDropDown);
			List<WebElement> ele = sel.getOptions();
			for (WebElement dropdown : ele) {
				if (dropdown.getText().equalsIgnoreCase(viewname)) {
					log.info(viewname + "added and can be seen in the dropdown");
					break;
				}
			}
			isViewadded = true;

			test.pass(viewname + "View is Added");
		} else {
			log.error("createNewView():createNewView link is not clicked ");
			test.fail("createNewView():" + viewname + " View is not Added in the dropdown");
			test.addScreenCaptureFromPath(Utilities.captureScreenShot(driver));
			isViewadded = false;
		}
		return isViewadded;
	}

	public String clickEditViewLink(WebDriver driver) throws IOException {
		log.debug("clickEditViewLink(): initiated");
		editView.click();
		log.debug("editView link clicked");
		Utilities.dynamicExplicitElementWait(driver, VieNameTextBox);
		String viewname = null;
		if (VieNameTextBox.isDisplayed()) {
			VieNameTextBox.clear();
			viewname = Utilities.randomNameGeneration();
			VieNameTextBox.sendKeys(viewname);
			log.debug(viewname + " entered as a view name in the view name Text box");
			Actions action = new Actions(driver);
			action.keyDown(Keys.TAB).build().perform();
			Utilities.dynamicExplicitElementWait(driver, ViewUniqueNameTextBox);
			ViewUniqueNameTextBox.clear();
			ViewUniqueNameTextBox.sendKeys(viewname);
			action.keyUp(Keys.TAB).build().perform();
			log.debug(viewname + "added in the unique name text box");
			Utilities.dynamicExplicitElementWait(driver, filterFieldDropDown);
			Utilities.selectValueFromText(filterFieldDropDown,
					PropertyReader.readAppMenuOptions("edit_view_filter_field"));
			log.debug("value selected in edit view filter Field DropDown");

			Utilities.dynamicExplicitElementWait(driver, filterOperatorDropDown);
			Utilities.selectValueFromText(filterOperatorDropDown,
					PropertyReader.readAppMenuOptions("edit_view_filter_operator"));
			log.debug("value selected in edit view filter Operator DropDown");
			filterValueTextBox.clear();

			filterValueTextBox.sendKeys(PropertyReader.readAppMenuOptions("edit_view_filter_valueText"));
			log.debug("value entered in edit view filter value Textbox");
			WebElement ele = driver.findElement(By.xpath("(//input[@value=' Save '])[2]"));
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("arguments[0].scrollIntoView(true)", ele);
			Utilities.selectValueFromText(availableFields,
					PropertyReader.readAppMenuOptions("edit_view_available_fields"));
			log.debug("value selected from edit_view_available_fields");
			addArrow.click();
			log.debug("Arrow button clicked");
			Utilities.dynamicExplicitElementWait(driver, newViewSaveButton);
			newViewSaveButton.click();
			log.debug("edit view save button clicked");

			test.pass("edit view page updated and saved");
		} else {
			log.error("clickEditViewLink(): failed");
			test.fail("clickEditViewLink(): failed and view not edited");
			test.addScreenCaptureFromPath(Utilities.captureScreenShot(driver));

		}

		return viewname;

	}

	public void verifyViewDropDown(String viewname) {
		Select sel = new Select(ViewAddedDropDown);
		List<WebElement> ele = sel.getOptions();
		for (WebElement dropdown : ele) {
			if (dropdown.getText().equalsIgnoreCase(viewname)) {
				log.info(viewname + "view edited and found in the drop down list");
				test.pass(viewname + "view edited and found in the drop down list");
				break;
			}
		}
	}

	public boolean verifyColumnsTitle(WebDriver driver, String value) throws IOException {
		log.info("verifyColumnsTitle() :Initialized");
		WebElement expectedvalue = driver
				.findElement(By.xpath("//tr[@class='x-grid3-hd-row']//child::div[@title='" + value + "']"));
		if (columnsTitle.contains(expectedvalue)) {
			log.info(value + "column found in the header ");
			test.pass(value + "column found in the header ");
			return true;
		} else
			log.error(value + "column not found in the header ");
		test.fail(value + "column not found in the header ");
		test.addScreenCaptureFromPath(Utilities.captureScreenShot(driver));
		return false;
	}

	public boolean verifySequenceCharactersinAccounts(WebDriver driver, String sequence) {
		log.info("verifySequenceCharactersinAccounts() :Initialized");
		List<WebElement> accountvisiblelist = driver
				.findElements(By.xpath("//div[@class='x-grid3-cell-inner x-grid3-col-Name']/a"));
		boolean isContain = false;
		for (WebElement ele2 : accountvisiblelist) {
			if (ele2.getText().contains(sequence)) {
				isContain = true;
			} else {
				isContain = false;
			}
		}
		return isContain;

	}

	public boolean mergeAccounts(WebDriver driver, int numberOfAccountsToBeMerged, String accountSearchkeyword)
			throws IOException, InterruptedException {
		log.info("mergeAccounts(): inititaed");
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0, 700);");

		boolean isMerged = false;

		if (MergeAccountLink.isDisplayed()) {
			MergeAccountLink.click();
			Utilities.dynamicExplicitElementWait(driver, findMergeAccountsTextBox);
			findMergeAccountsTextBox.sendKeys(accountSearchkeyword);
			findMergeAccountNextButton.click();
			log.info("Find Merge Account Next button clicked");
			// selectMergeAccountCheckbox

			Iterator<WebElement> itr = selectMergeAccountCheckbox.iterator();
			while (itr.hasNext()) {
				itr.next().click();
			}
			log.info("Checkbox unchecked");
			Thread.sleep(1000);
			for (int i = 0; i < numberOfAccountsToBeMerged; i++) {
				selectMergeAccountCheckbox.get(i).click();
			}
			log.info("Required checkbox Checked");
			Thread.sleep(1000);
			MergeAccountsNextButton.click();
			Utilities.dynamicExplicitElementWait(driver, MergeAccountTopButton);
			log.info("Next Button for the Accounts to be Merged is clicked");
			MergeAccountTopButton.click();
			Thread.sleep(1000);
			driver.switchTo().alert().accept();
			log.info("Alert accepted to Merge the accounts");
			test.pass("Accounts Merged");
			isMerged = true;
		} else {
			isMerged = false;
			test.fail("Accounts not merged");
			test.addScreenCaptureFromPath(Utilities.captureScreenShot(driver));
		}
		return isMerged;

	}

	public boolean verifyAccountList(WebDriver driver, String accountSearchkeyword) throws IOException {
		boolean isavailable = false;
		if (createNewViewLink.isDisplayed()) {
			Iterator<WebElement> itr1 = AccountsList.iterator();
			while (itr1.hasNext()) {
				String accountname = itr1.next().getText();
				if (accountname.contains(accountSearchkeyword)) {
					log.info("verifyAccountList(): Merged Account is in the list");
					test.pass("Account is in the list");
					break;
				}
			}
			isavailable = true;
		} else {
			isavailable = false;
			test.fail("Account is not in the list");
			log.error("verifyAccountList(): Account not available in the list");
			test.addScreenCaptureFromPath(Utilities.captureScreenShot(driver));
		}
		return isavailable;
	}

}
