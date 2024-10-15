package pajeObject;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import baseClass.BaseClassPage;

import java.time.Duration;

public class RegistrationPage extends BaseClassPage {  

    // Define an explicit wait variable
    private WebDriverWait wait;

    public RegistrationPage(WebDriver driver) {
        super(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));  // Wait for up to 10 seconds
    }

    @FindBy(xpath = "//input[@id='input-firstname']")
    WebElement txtFirstname;

    @FindBy(xpath = "//input[@id='input-lastname']")
    WebElement txtLasttname;

    @FindBy(xpath = "//input[@id='input-email']")
    WebElement txtEmail;

//    @FindBy(xpath = "//input[@id='input-telephone']")
//    WebElement txtTelephone;

    @FindBy(xpath = "//input[@id='input-password']")
    WebElement txtPassword;

//    @FindBy(xpath = "//input[@id='input-confirm']")
//    WebElement txtConfirmPassword;
//
    @FindBy(xpath = "//input[@name='agree']")
    WebElement chkdPolicy;

    @FindBy(xpath = "//input[@class='form-check-input']")
    WebElement chkdNewsLetter;

    @FindBy(xpath = "//button[@type='submit']")
    WebElement btnContinue;

    @FindBy(xpath = "//h1[normalize-space()='Your Account Has Been Created!']")
    WebElement msgConfirmation;

    // Set First Name with explicit wait
    public void setFirstName(String fname) {
        wait.until(ExpectedConditions.visibilityOf(txtFirstname));  // Wait for element to be visible
        txtFirstname.sendKeys(fname);  // Send keys after wait
    }

    // Set Last Name with explicit wait
    public void setLastName(String lname) {
        wait.until(ExpectedConditions.visibilityOf(txtLasttname));
        txtLasttname.sendKeys(lname);
    }

    // Set Email with explicit wait
    public void setEmail(String email) {
        wait.until(ExpectedConditions.visibilityOf(txtEmail));
        txtEmail.sendKeys(email);
    }

//    public void setTelephone(String tel) {
//        wait.until(ExpectedConditions.visibilityOf(txtTelephone));
//        txtTelephone.sendKeys(tel);
//    }

    // Set Password with explicit wait
    public void setPassword(String pwd) {
        wait.until(ExpectedConditions.visibilityOf(txtPassword));
        txtPassword.sendKeys(pwd);
    }

//    public void setConfirmPassword(String pwd) {
//        wait.until(ExpectedConditions.visibilityOf(txtConfirmPassword));
//        txtConfirmPassword.sendKeys(pwd);
//    }

    // Agree to Privacy Policy with explicit wait
    public void setPrivacyPolicy() {
//        wait.until(ExpectedConditions.elementToBeClickable(chkdPolicy));  // Wait for element to be clickable
//        chkdPolicy.click();
    	forceClick(chkdPolicy);
    }

    public void forceClick(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));  // Wait for element to be clickable
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);  // Click using JavaScript
    }

    
    // Select Newsletter with explicit wait
    public void setNewsLetter() {
        wait.until(ExpectedConditions.elementToBeClickable(chkdNewsLetter));  // Wait for element to be clickable
        chkdNewsLetter.click();
    }

    // Click Continue button with explicit wait
    public void clickContinue() {
        wait.until(ExpectedConditions.elementToBeClickable(btnContinue));  // Wait for element to be clickable
        btnContinue.click();
    }

    // Get Confirmation Message
    public String getConfirmationMsg() {
        try {
            wait.until(ExpectedConditions.visibilityOf(msgConfirmation));  // Wait for confirmation message to be visible
            return msgConfirmation.getText();
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
