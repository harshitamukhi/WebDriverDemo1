package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class RegistrationPage extends BasePage{
	
	public RegistrationPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath="//input[@id='input-firstname']")
	WebElement txtfirstName;

	@FindBy(xpath="//input[@id='input-lastname']")
	WebElement txtlastName;
	
	@FindBy(xpath="//input[@id='input-email']")
	WebElement txtemail;
	
	@FindBy(xpath="//input[@id='input-password']")
	WebElement txtpassword;
	
	@FindBy(xpath="//button[text()='Continue']")
	WebElement btnContinue;
	
	@FindBy(xpath="//input[@name='agree']")
	WebElement chAgree;
	
	@FindBy(xpath="//div[@id='common-success']//h1[text()='Your Account Has Been Created!']")
	public WebElement successMessage;
	
	public void setFirstName(String fname) {
		txtfirstName.sendKeys(fname);
	}
	
	public void setLastName(String lname) {
		txtlastName.sendKeys(lname);
	}
	
	public void setEmail(String emailId) {
		txtemail.sendKeys(emailId);
	}
	
	public void setPassword(String pwd) {
		txtpassword.sendKeys(pwd);
	}
	
	public void clickContinueBtn() {
		
		btnContinue.submit();
	}
	
	public void clickAgreeCheckbox() {
		Actions act = new Actions(driver);
		act.moveToElement(chAgree).click().build().perform();
	}

}
