package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage{
	public LoginPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath="//input[@id='input-email']")
	WebElement username;
	
	@FindBy(xpath="//input[@id='input-password']")
	WebElement password;
	
	@FindBy(xpath="//button[text()='Login']")
	WebElement loginbtn;
	
	public void setUsername(String name) {
		username.sendKeys(name);
	}

	public void setPassword(String pwd) {
		password.sendKeys(pwd);
	}
	
	public void clickLoginBtn() {
		loginbtn.click();
	}

}
