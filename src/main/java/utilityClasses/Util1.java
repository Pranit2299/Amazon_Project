package utilityClasses;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Util1 {

	//by this method we will get --> website , credentials which are store in config.properties file

	public static String getProperty(String key) throws IOException
	{
		FileInputStream  file = new FileInputStream("Configuration\\config.properties");
		Properties p = new Properties();
		p.load(file);                      // load property will load all data from "file" to p location now data is in p
		String property = p.getProperty(key);  // getting key from p location
		return property;
	}




	// Explicit Wait by using WebElement
	public static WebElement explicitWait(WebDriver driver ,WebElement element)
	{
		WebDriverWait wait = new WebDriverWait(driver , Duration.ofSeconds(10));
		WebElement newElement =wait.until(ExpectedConditions.visibilityOf(element));
		return newElement;
	}

	// Explicit Wait by using xpath
	public static WebElement explicitWait(WebDriver driver ,String xpath)
	{
		WebDriverWait wait = new WebDriverWait(driver , Duration.ofSeconds(10));
		WebElement newElement =wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
		return newElement;
	}


	public static void scrollWindow(WebDriver driver , int x , int y )
	{
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollBy("+ x +" ," + y +")");

	}




	public static void getScreenShot(WebDriver driver, String testCaseName ) throws IOException
	{
		Date date = new Date();
		SimpleDateFormat frmt = new SimpleDateFormat("hhmmss");
		String time = frmt.format(date);
		TakesScreenshot ts = (TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File dest = new File("C:\\Users\\praful\\Amazon Project Workspace\\Screenshots"+ testCaseName+" "+ time + ".jpg");
		FileHandler.copy(source, dest);
	}

	public static String getScreenShot(WebDriver driver ) throws IOException
	{
		Date date = new Date();
		SimpleDateFormat frmt = new SimpleDateFormat("hhmmss");
		String time = frmt.format(date);
		TakesScreenshot ts = (TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File dest = new File("./Screenshots/"+ time + ".jpg");
		FileHandler.copy(source, dest);
		return dest.getAbsolutePath();
	}

	public static String captureScreenshot(WebDriver driver , String testCaseName) throws IOException
	{
		Date date = new Date();
		SimpleDateFormat frmt = new SimpleDateFormat("hhmmss");
		String time = frmt.format(date);
		TakesScreenshot ts = (TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File dest = new File("./Screenshots/"+testCaseName+" " +time + ".jpg");
		FileHandler.copy(source, dest);
		return dest.getAbsolutePath();

	}




	public static void moveToElementAndClick(WebDriver driver, WebElement element)
	{
		Actions act = new Actions(driver);
		act.moveToElement(element).perform();
	}
}
