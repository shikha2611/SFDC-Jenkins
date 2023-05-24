package pages;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;

import utils.PropertyReader;
import utils.Utilities;

//extends BasePage
public class UserMenuPage extends BasePage {
//
	public UserMenuPage(WebDriver driver, ExtentTest test) {
		PageFactory.initElements(driver, this);
		BasePage.test = test;

	}

	@FindBy(xpath = "//div[@id='userNavButton']/span")
	public WebElement userMenu;

	@FindBy(xpath = "//a[@title='Logout']")
	public WebElement logout;

	@FindBy(xpath = "//div[@id='userNav-menuItems']/a")
	public List<WebElement> userMenuOptions;

	@FindBy(id = "userNav-menuItems/a[1]")
	public WebElement MyProfile;

	@FindBy(id = "userNav-menuItems/a[2]")
	public WebElement MySettings;

	@FindBy(id = "userNav-menuItems/a[3]")
	public WebElement DevelopersConsole;

	@FindBy(id = "userNav-menuItems/a[4]")
	public WebElement SwitchtoLightningExperience;

	@FindBy(id = "userNav-menuItems/a[4]")
	public WebElement Logout;

	public boolean clickUserMenu(WebDriver driver) {
		boolean isUSerMenu = false;
		if (userMenu.isDisplayed()) {
			isUSerMenu = true;
			userMenu.click();
			test.pass("UserMenu is Dsipalyed");
		} else {
			isUSerMenu = false;
			test.fail("UserMenu is Dsipalyed");
			try {
				test.addScreenCaptureFromPath(Utilities.captureScreenShot(driver));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return isUSerMenu;
	}

	public boolean verifyUserMenuOptions() {
		boolean isOptions = true;
		String[] options = { "My Profile", "My Settings", "Developer Console", "Switch to Lightning Experience",
				"Logout" };

		for (int i = 0; i < userMenuOptions.size(); i++) {
			String actualOptions = userMenuOptions.get(i).getText();
			if (actualOptions.equalsIgnoreCase(options[i])) {
				System.out.println(options[i] + " found");
			} else {
				System.out.println(options[i] + "not found");
				isOptions = false;
			}
		}
		return isOptions;

	}

	public boolean selectUserMenuOption(WebDriver driver, String OptionName) {
		boolean isOption = false;
		String optionToBeSelected = driver.findElement(By.xpath("//a[text()='" + OptionName + "']")).getText();
		if (userMenu.isDisplayed()) {
			for (int i = 0; i < userMenuOptions.size(); i++) {
				if (userMenuOptions.get(i).getText().equals(optionToBeSelected)) {
					userMenuOptions.get(i).click();
					break;
				}
			}
			isOption = true;
			test.pass("Option selected is " + optionToBeSelected);
		} else {
			isOption = false;
			test.fail("option not selected" + optionToBeSelected);
			try {
				test.addScreenCaptureFromPath(Utilities.captureScreenShot(driver));
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		return isOption;

	}

	public String developerConsoleWindow(WebDriver driver) throws InterruptedException {
		Utilities.dynamicExplicitElementWait(driver, userMenu);

		Set<String> winhdl = driver.getWindowHandles();
		ArrayList<String> al = new ArrayList<>();
		Iterator<String> itr = winhdl.iterator();

		while (itr.hasNext()) {
			al.add(itr.next());
		}
		driver.switchTo().window(al.get(1));
		String actual = driver.getTitle();
		Thread.sleep(2000);
		driver.close();
		return actual;

	}

	public void newAccountCreation() {

	}

}
