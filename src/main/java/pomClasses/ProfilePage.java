package pomClasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilityClasses.Util1;

public class ProfilePage extends Util1{

	WebDriver driver; 
	
	@FindBy(xpath="//div[@class='a-row a-spacing-base']//h1")
	private WebElement profile;
	
	
	public ProfilePage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
		this.driver = driver;
	}
	
	
	public boolean profileVerification()
	{
		explicitWait(driver, profile);
		
		String pHeading =profile.getText();
		
		if(pHeading.equals("Your Account"))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
}
