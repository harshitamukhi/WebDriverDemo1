package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage{
	
	public HomePage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath="//span[text()='My Account']/following-sibling::i[@class='fa-solid fa-caret-down']")
	WebElement myAccounts;
	
	@FindBy(xpath="//a[text()='Register']")
	WebElement registerOpt;
	
	@FindBy(xpath="//a[text()='Login']")
	WebElement loginOpt;
	
	public void clickMyAccounts() {
		myAccounts.click();
	}

	public void clickRegisterOpt() {
		registerOpt.click();
	}
	
	public void clickLoginOpt() {
		loginOpt.click();
	}
}
