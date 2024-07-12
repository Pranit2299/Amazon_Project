package pomClasses;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilityClasses.Util1;

public class AddressPage extends Util1{

	WebDriver driver;
	
	@FindBy(xpath="//img[@alt='Your Addresses']")
	private WebElement yourAddresses;
	
	@FindBy(xpath=("//h2[@class='a-color-tertiary']"))
	private WebElement addAddress;
	
	@FindBy(xpath="//h2[text()='Add a new address']")
	private WebElement veryfyOnAddressPage;
	
	
	@FindBy(xpath="//input[@type='text'] ")
	private List <WebElement> inputFieldAddres;
	//input[@type='text']     // ----> list of inpt field ---> start from 1  ignore 0th index
	
	@FindBy(xpath="(//input[@class='a-button-input'])[3]")
	private WebElement addAddressBtn;
	
	@FindBy(xpath="//h4[text()='Address saved']")
	private WebElement verifyAddressSave;

	
	public AddressPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
		this.driver = driver;
	}
	
	
	public void clickOnYourAddresses()
	{
		explicitWait(driver, yourAddresses);
		yourAddresses.click();
	}
	
	public void clickOnAddAddress()
	{
		explicitWait(driver, addAddress);
		addAddress.click();
	}
	
	public boolean verifyLandOnAddresPage()
	{
		String text = veryfyOnAddressPage.getText();
		if(text.equals("Add a new address"))
		{
			return true;
		}
		else
		{
			return false;
		}
		
	}
	
	
	public void fillAddressMethod() throws EncryptedDocumentException, IOException, InterruptedException
	{
		FileInputStream file = new FileInputStream("src\\main\\resources\\testData\\AmazonProjectData.xlsx");
		Workbook wb = WorkbookFactory.create(file);
		Sheet sheet = wb.getSheet("Sheet1");
		
		DataFormatter formatter = new DataFormatter();  //in excel sheet mix data is there String & intiger so by using this object
		                                               // it will set dataType as per given data
		
		for(int i=1; i<7; i++) 
		{
			try 
			{
			String value =formatter.formatCellValue(sheet.getRow(i).getCell(0));
			//String value =sheet.getRow(i).getCell(0).getStringCellValue();
			Thread.sleep(2000);
			inputFieldAddres.get(i).sendKeys(value);
			}
			catch(Exception e)
			{
				
				String value = formatter.formatCellValue(sheet.getRow(i).getCell(0));
				Thread.sleep(2000);
				inputFieldAddres.get(i).sendKeys(value);
			}
			
			
		}
		wb.close();
        file.close();
		
	}
	
	public void clickAddAddressBtn()
	{
		addAddressBtn.click();
		
	}
	
	public boolean verifyAddressSave()
	{
		String text = verifyAddressSave.getText();
		if(text.equals("Address saved"))
		{
			return true;
		}
		else {return false;}
	}
	

     
	
	
	
	
}
