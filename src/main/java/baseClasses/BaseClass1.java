package baseClasses;

import java.io.IOException;
import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import utilityClasses.Util1;

public class BaseClass1 extends Util1{

	private static WebDriver driver = null;

	public static WebDriver getDriver(String browser) throws IOException
	{
		if(driver == null)
		{
			if(browser.equals("chrome"))
			{
				driver=new ChromeDriver();

			}else if(browser.equals("edge"))
			{
				driver=new EdgeDriver();
			}

			driver.manage().window().maximize();

			driver.get(Util1.getProperty("url")); // here we call getProperty method --> and take url from config.properties file

			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			return driver;
		}
		else
		{
			return driver;
		}
	}




}









//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> To run tests in parallel across different browsers (Chrome and Edge)  <<<<<<<<<<<<<<<<<<<<<<<<<<<<
//package baseClasses;
//
//import java.io.IOException;
//import java.time.Duration;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.edge.EdgeDriver;
//import org.testng.annotations.Parameters;
//import utilityClasses.Util1;
//
//public class BaseClass1 extends Util1 {
//
//    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
//
//    public static WebDriver getDriver() {
//        return driver.get();
//    }
//
//    @Parameters("browser")
//    public void initializeDriver(String browser) throws IOException {
//        if (driver.get() == null) {
//            switch (browser.toLowerCase()) {
//                case "chrome":
//                    driver.set(new ChromeDriver());
//                    break;
//                case "edge":
//                    driver.set(new EdgeDriver());
//                    break;
//                default:
//                    throw new IllegalArgumentException("Unsupported browser: " + browser);
//            }
//            driver.get().manage().window().maximize();
//            driver.get().get(Util1.getProperty("url"));
//            driver.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//        }
//    }
//
//    public void closeDriver() {
//        if (driver.get() != null) {
//            driver.get().quit();
//            driver.remove();
//        }
//    }
//}

