package testCase;

import org.testng.annotations.Test;

import baseClass.BaseClassPage;
import pajeObject.HomePage;
import pajeObject.LoginPage;

public class T02_testLogin extends BaseClassPage{
	
	@Test(groups={"regression", "master"})
	public void testLogin() {
		logger.info("Starting Test T02_testLogin...");
		HomePage hp = new HomePage(driver);  // Interacting with HomePage object
        hp.clickMyAccount();
        logger.info("Logging to the account");
        hp.clickLogin();
        logger.info("");
        
        LoginPage login = new LoginPage(driver);
        login.setEmail(p.getProperty("emailID"));
        login.setPassword(p.getProperty("password"));
        
        
	}
	
}
