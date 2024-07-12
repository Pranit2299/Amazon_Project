package testClasses;

import java.io.IOException;
import java.lang.reflect.Method;

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
import pomClasses.HomePage;
import pomClasses.LoginPage;
import utilityClasses.Util1;

public class VerifyUserCanLogin extends BaseTest {

	 private static WebDriver driver;
	 private LoginPage lp;
	 private HomePage hp ; 
	//	ExtentReports extentReports ;
	//	ExtentSparkReporter sparkReporter;
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
		lp =new LoginPage(driver);
		hp = new HomePage(driver);
		extentTest = extentReports.createTest(method.getName());

	}

	@Test
	public void verifyUserCanLoginn() throws IOException
	{
		lp.clickAccountBtn();
		lp.enterEmail();
		lp.clickContinueBtn();
		lp.enterPassword();
		lp.clickSignUpBtn();

		HomePage hp= new HomePage(driver);
		Assert.assertTrue(hp.getProfileName());  //Hard Assert ---> if result match then it will execute the case

	}

	@AfterMethod
	public void logTestResult(Method m , ITestResult result) throws IOException
	{
		if(result.getStatus() == ITestResult.FAILURE)
		{
			extentTest.log(Status.FAIL, MarkupHelper.createLabel(result.getName() +" FAILED", ExtentColor.RED));
			extentTest.fail(result.getThrowable());
			String screenshotPath =Util1.captureScreenshot(driver, result.getName());
			extentTest.addScreenCaptureFromPath(screenshotPath); 
		}
		else if (result.getStatus() == ITestResult.SUCCESS) 
		{
			extentTest.log(Status.PASS, MarkupHelper.createLabel(result.getName()+ " PASSED" , ExtentColor.GREEN));
		}
		else if(result.getStatus() == ITestResult.SKIP)
		{
			extentTest.log(Status.SKIP, MarkupHelper.createLabel(result.getName() + " SKIP", ExtentColor.YELLOW));
		}

	}

	@AfterClass
	public void tearDown() throws InterruptedException
	{

	}
}
