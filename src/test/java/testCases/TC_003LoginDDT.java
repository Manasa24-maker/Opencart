package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObject.HomePage;
import pageObject.LoginPage;
import pageObject.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;


public class TC_003LoginDDT extends BaseClass
{
	
	@Test(dataProvider="LoginData", dataProviderClass=DataProviders.class,groups="DataDriven")  //getting data providers from different class
	public void verify_loginDDT(String email, String pwd, String exp)
	{
		
		logger.info("** Starting TC_003LoginDDT**");
		
		try
		{
		//Home page
		HomePage hp=new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLogin();
		
		//login
		LoginPage lp=new LoginPage(driver);
		lp.setEmail(email);
		lp.setPassword(pwd);
		lp.clickLogin();
		
		//MyAccount
		MyAccountPage macc=new MyAccountPage(driver);
		boolean targetPage=macc.isMyAccountPageExists();
		
		
		/* Data is valid - login success - test pass - logout
		  					login failed - test fail
		 data is invalid -login success - test fail
		  					login fail - Test Pass
		 */
		
		if(exp.equalsIgnoreCase("Valid"))   	 //valid data
		{
			if(targetPage==true)				//login sucess 
			{
				macc.clickLogout();				//logout
				Assert.assertTrue(true);		//test pass
							
			}
			else
			{
				Assert.assertTrue(false);		//login fail -- test fail
			}
			
		}
		
		if(exp.equalsIgnoreCase("Invalid"))   	 //valid data
		{
			if(targetPage==true)				//login sucess 
			{
				macc.clickLogout();				//logout
				Assert.assertTrue(false);		//test fail
				
			}
			else
			{
				Assert.assertTrue(true);		//login fail -- test pass
			}
			
		}
		}catch(Exception e)
		{
			Assert.fail();
		}
		
		
		logger.info("** Finished TC_003LoginDDT**");
	}

}
