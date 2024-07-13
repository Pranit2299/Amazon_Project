package testClasses;

import java.io.IOException;
import java.lang.reflect.Method;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import baseClasses.BaseClass1;
import baseClasses.BaseTest;
import pomClasses.AddressPage;
import pomClasses.HomePage;
import pomClasses.ProfilePage;
import utilityClasses.Util1;

public class VerifyUserCanAddNewAddress  extends  BaseTest{

	 private static WebDriver driver;
	 private HomePage hp;
	 private ProfilePage pp;
	 private AddressPage ap;
	 private ExtentTest extentTest;

	@Parameters("browser")
	@BeforeClass
	public void setup(String value) throws IOException
	{
		driver = BaseClass1.getDriver(value);
	}

	@BeforeMethod
	public void initialize(Method method)
	{
		hp=new HomePage(driver);
		pp=new ProfilePage(driver);
		ap =new AddressPage(driver);
		extentTest = extentReports.createTest(method.getName());
	}

	@Test(priority = 1)
	public void VerifyUserCanGotoProfilePage()
	{
		hp.clickOnYourAccount();
		//ProfilePage pp= new ProfilePage(driver);
		Assert.assertTrue(pp.profileVerification());
	}

	@Test(priority = 2)
	public void verifyUserCanGotoAddressPage() throws EncryptedDocumentException, IOException, InterruptedException 
	{
		ap.clickOnYourAddresses();
		ap.clickOnAddAddress();
		Assert.assertTrue(ap.verifyLandOnAddresPage());
	}


	@Test(priority = 3)
	public void verfiyUserCanAddAddress() throws EncryptedDocumentException, IOException, InterruptedException
	{
		ap.fillAddressMethod();
		ap.clickAddAddressBtn();
		
		Assert.assertTrue(ap.verifyAddressSave());
	}


	@AfterMethod
	public void logTestResult(ITestResult result) throws IOException
	{
		if(result.getStatus()  == ITestResult.FAILURE)
		{
			extentTest.log(Status.FAIL, MarkupHelper.createLabel(result.getName() +" FAILED", ExtentColor.RED ));
			extentTest.fail(result.getThrowable());
			String screenshotPath =Util1.captureScreenshot(driver, result.getName());
			extentTest.addScreenCaptureFromPath(screenshotPath);
		}
		else if (result.getStatus() == ITestResult.SUCCESS)
		{
			extentTest.log(Status.PASS, MarkupHelper.createLabel(result.getName() + " PASED", ExtentColor.GREEN));
		}
		else if(result.getStatus() == ITestResult.SKIP)
		{
			extentTest.log(Status.SKIP, MarkupHelper.createLabel(result.getName()+ " SKIP", ExtentColor.YELLOW));
		}

	}

	@AfterClass
	public void tearDown()
	{
		driver.quit();
	}
}
