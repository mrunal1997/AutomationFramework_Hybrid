package pajeObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import baseClass.BaseClassPage;

public class LoginPage extends BaseClassPage{

    public LoginPage(WebDriver driver) {
        super(driver);
    }
    
    @FindBy(xpath = "//input[@id='input-email']")
    WebElement txtEmail;
    
    @FindBy(xpath = "//input[@id='input-password']")
    WebElement txtPassword;
    
	@FindBy(xpath = "//h2[text()='My Account']") // MyAccount Page heading
	WebElement msgHeading;
	
	@FindBy(xpath = "//div[@class='list-group']//a[text()='Logout']")
	WebElement lnkLogout;
    
    public void setEmail(String email) {
        txtEmail.sendKeys(email);
    }
    
    public void setPassword(String pwd) {
        txtPassword.sendKeys(pwd);
    }
    
	public boolean isMyAccountPageExists()   // MyAccount Page heading display status
	{
		try {
			return (msgHeading.isDisplayed());
		} catch (Exception e) {
			return (false);
		}
	}

	public void clickLogout() {
		lnkLogout.click();

	}
}
