package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountsPage extends BasePage {
	public MyAccountsPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath="//h2[text()='My Account']")
	WebElement title;
	
	@FindBy(xpath="//div[@class='list-group mb-3']/a[text()='Logout']")
	WebElement logoutOpt;
	
	public boolean isMyAccountExists() {
		try {
			return (title.isDisplayed());
		}
		catch(Exception e) {
			return false;
		}
	}

	public void clickLogoutOpt() {
		logoutOpt.click();
	}
}
