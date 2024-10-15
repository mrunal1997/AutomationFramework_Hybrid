package testCase;

import org.testng.Assert;
import org.testng.annotations.Test;

import baseClass.BaseClassPage;
import pajeObject.HomePage;
import pajeObject.LoginPage;
import utilities.DataProviders;

public class T03_testLoginDDT extends BaseClassPage{

	@Test(groups={"datadriven", "master"}, dataProvider="LoginData", dataProviderClass=DataProviders.class)
	public void testLgoinDDT(String email, String password, String exp) {
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLogin();
		
		LoginPage login = new LoginPage(driver);
		login.setEmail(email);
		login.setPassword(password);
		
		boolean myacc = login.isMyAccountPageExists();
		
		if(exp.equalsIgnoreCase("Valid"))
		{
			if(myacc == true) {
				login.clickLogout();
				Assert.assertTrue(true);
			}
			else
			{
				Assert.assertTrue(false);
			}
		}
		if(exp.equalsIgnoreCase("Invalid"))
		{
			if(myacc == true) {
				login.clickLogout();
				Assert.assertTrue(false);
			}
			else {
				Assert.assertTrue(true);
			}
		}	
	}
}
