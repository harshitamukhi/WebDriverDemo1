package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountsPage;
import utilities.DataProviders;

public class LoginTest extends BaseClass{
	
	@Test(dataProvider="LoginData", dataProviderClass=DataProviders.class,groups= {"Sanity"})
	public void verifyLogin(String username,String password,String msg) throws InterruptedException {
		HomePage hp = new HomePage(driver);
		//Thread.sleep(3000);
		hp.clickMyAccounts();
		hp.clickLoginOpt();
		
		LoginPage lp = new LoginPage(driver);
		lp.setUsername(username);
		lp.setPassword(password);
		lp.clickLoginBtn();
		//Thread.sleep(3000);
		
		MyAccountsPage ap = new MyAccountsPage(driver);
		boolean target = ap.isMyAccountExists();
		
		if(msg.equalsIgnoreCase("Valid")) {
			if(target==true) {
				Assert.assertTrue(true);
				//hp.clickMyAccounts();
				ap.clickLogoutOpt();
			}
			else {
				Assert.assertTrue(false);
			}
		}
		
		if(msg.equalsIgnoreCase("Invalid")) {
			if(target==true) {
				ap.clickLogoutOpt();
				Assert.assertTrue(false);
			}
			else {
				Assert.assertTrue(true);
			}
		}
		
		
	}
}
