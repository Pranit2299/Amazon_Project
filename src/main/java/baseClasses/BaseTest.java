package baseClasses;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class BaseTest {

	protected static ExtentReports extentReports;
	private static ExtentSparkReporter SparkReporter;

	@BeforeSuite
	public void setupExtentReports()
	{
		extentReports = new ExtentReports();
		SparkReporter = new ExtentSparkReporter("src\\main\\resources\\testData\\AllReport.html");
		extentReports.attachReporter(SparkReporter);

		extentReports.setSystemInfo("user", "Praful");
		extentReports.setSystemInfo("os", "windows 10");
	}

	@AfterSuite
	public void teardownExtentReports()
	{
		if (extentReports != null)
		{
			extentReports.flush();
		}
	}
}
