package testCases;

import java.time.Duration;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pageObject.AccountRegistrationPage;
import pageObject.HomePage;
import testBase.BaseClass;

public class TC_001AccountRegistrationTest extends BaseClass
{

	@Test(groups={"Regression","Master"})
	public void verify_account_registration()
	{
		logger.info("*** Starting TC_001AccountRegistrationTest ***");
		try
		{
		HomePage hp=new HomePage(driver);
		hp.clickMyAccount();
		logger.info("*Clicked on My Account link*");
		
		hp.clickRegister();
		logger.info("*Clicked on Register link*");
		
		AccountRegistrationPage regpage=new AccountRegistrationPage(driver);
		logger.info("*providing customer details*");
		
		//manually entering input data
	/*	regpage.setFirstName("John");
		regpage.setLastName("David");
		regpage.setEmail("abcjohndavid11@gmail.com");
		
		regpage.setTelephone("9123456789");

		regpage.setPassword("xyz123456");
		regpage.setConfirmPassword("xyz123456"); */
		
		
		//randomly generating the data
		regpage.setFirstName(randomeString().toUpperCase());
		regpage.setLastName(randomeString().toUpperCase());
		regpage.setEmail(randomeString()+"@gmail.com"); 		//randomly generated the email
		
		regpage.setTelephone("randomeNumber()");
		
		String password=randomeAlphaNumeric();
		regpage.setPassword(password);
		regpage.setConfirmPassword(password);
		
		regpage.setPrivacyPolicy();
		regpage.clickContinue();
		
		logger.info("*Validating Expected message..*");
		
		String confmsg=regpage.getConfirmationMsg();
		
		if(confmsg.equals("Your Account Has Been Created!"))
		{
			Assert.assertTrue(true);
		}
		else
		{
			logger.error("*Test Failed*");
			logger.debug("*Debug logs..*");
			Assert.assertTrue(false);
		}
		
		//Assert.assertEquals(confmsg,"Your Account Has Been Created!");
		
		}
		
		catch(Exception e)
		{
			
			Assert.fail();
		}
		
		logger.info("*** Finished TC_001AccountRegistrationTest ***");
		
	}

	

}
