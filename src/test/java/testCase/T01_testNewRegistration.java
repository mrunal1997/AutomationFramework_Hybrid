package testCase;

import org.testng.Assert;
import org.testng.annotations.Test;

import baseClass.BaseClassPage;
import pajeObject.HomePage;  // Separate file for HomePage
import pajeObject.RegistrationPage;  // Separate file for RegistrationPage

public class T01_testNewRegistration extends BaseClassPage {

    @Test(groups={"sanity", "master"})
    public void verify_account_registration() {
        HomePage hp = new HomePage(driver);  // Interacting with HomePage object
        logger.info("Now create/register account");
        hp.clickMyAccount();
        hp.clickRegister();

        RegistrationPage regpage = new RegistrationPage(driver);  // Interacting with RegistrationPage object
        logger.info("Now user details will get filled");
        regpage.setFirstName(randomeString().toUpperCase());
        regpage.setLastName(randomeString().toUpperCase());
        regpage.setEmail(randomeString() + "@gmail.com");  // randomly generated email
//        regpage.setTelephone(randomeNumber());

        String password = randomAlphaNumeric();
        regpage.setPassword(password); 
//        regpage.setConfirmPassword(password);
        regpage.setNewsLetter();
        regpage.setPrivacyPolicy();
        regpage.clickContinue();

        String confmsg = regpage.getConfirmationMsg();
        Assert.assertEquals(confmsg, "Your Account Has Been Created!");
        logger.info("Account/Registration is get completed/filled");
    }
}
