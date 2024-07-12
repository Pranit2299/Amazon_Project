package pomClasses;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilityClasses.Util1;

public class LoginPage extends Util1{

	WebDriver driver;
	
	@FindBy(xpath="//span[text()='Account & Lists']")
	private WebElement accountBtn;
	
	@FindBy(xpath="//input[@type='email']")
	private WebElement enterEmail;
	
	@FindBy(xpath="//input[@type='submit']")
	private WebElement continueBtn;

	@FindBy(xpath="//input[@type='password']")
	private WebElement enterPassword;

	@FindBy(xpath="//input[@id='signInSubmit']")
	private WebElement clickSignUp;
	
	
	
	public LoginPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
		this.driver = driver;
	}
	
	
	
	public void clickAccountBtn()
	{
		accountBtn.click();
	}
	
	public void enterEmail() throws IOException
	{
		enterEmail.sendKeys(getProperty("username"));
	}
	
	public void clickContinueBtn()
	{
		continueBtn.click();
	}
	
	public void enterPassword() throws IOException
	{
		explicitWait(driver, enterPassword);
		enterPassword.sendKeys(getProperty("password"));
	}
	
	public void clickSignUpBtn()
	{
		clickSignUp.click();
	}
	
	public static void scrollWindow(WebDriver driver , int x, int y )
	{
		scrollWindow(driver, x, y);
	}
	
}
