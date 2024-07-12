package pomClasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilityClasses.Util1;

public class HomePage extends Util1 {

	WebDriver driver;

	@FindBy(xpath="//span[text()='Hello, Praful']")
	private WebElement profileName;
	
	@FindBy(xpath="//span[text()='Your Account']")
	private WebElement yourAccount;
	
	
	

	public HomePage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
		this.driver = driver;
	}



	public boolean getProfileName()
	{
		String pName= profileName.getText();

		if (pName.equals("Hello, Praful"))
		{
			return true;
		}
		else 
		{
			return false;
		}
	}
	
	
	public void clickOnYourAccount()
	{
		explicitWait(driver, profileName);
		moveToElementAndClick(driver, profileName);
		moveToElementAndClick(driver, yourAccount);
		yourAccount.click();
	}
	
	
	
	
}
