package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.RegistrationPage;

public class RegistrationTest extends BaseClass {

	@Test(groups= {"Sanity"})
	public void verify_registration() throws InterruptedException{
		
		//logger.info("***** Starting Registration Test ******");
		try
		{
		HomePage hp = new HomePage(driver);
		RegistrationPage rp = new RegistrationPage(driver);
		hp.clickMyAccounts();
		//logger.info("Clicked My Accoutns");
		
		hp.clickRegisterOpt();
		//logger.info("Clicked Register option");
		
		//logger.info("Providing custor details");
		rp.setFirstName(randomString());
		rp.setLastName(randomString());
		rp.setEmail(randomAlphaNumeric()+"@gmail.com");
		rp.setPassword(randomString()+"@");
		
		rp.clickAgreeCheckbox();
		rp.clickContinueBtn();
		Thread.sleep(3000);
		
		//logger.info("Validating expected message");
		String msg = rp.successMessage.getText();
		if(msg.equals("Your Account Has Been Created!")) 
		{
			Assert.assertTrue(true);
		}
		else {
			//logger.error("Test failed..");
			//logger.debug("Debug logs..");
			Assert.assertTrue(false);
		}
		//Assert.assertEquals(msg,"Your Account Has Been Created!");
		}
		catch(Exception e)
		{
			
			Assert.fail();
			
		}
		
		//logger.info("***** Finished verifying Registration test *****");
	}
	
	
}
