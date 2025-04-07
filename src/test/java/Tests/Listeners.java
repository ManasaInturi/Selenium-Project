package Tests;

import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import Utils.ExtentReportTest;

public class Listeners extends BaseTest implements ITestListener {
	
	ExtentReports reports = ExtentReportTest.reportsConfig();
	ExtentTest test;
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>(); //Thread safe
	
	public void onTestStart(ITestResult result) {
		test = reports.createTest(result.getMethod().getMethodName());
		extentTest.set(test); //unique thread id for each test
	}

	public void onTestSuccess(ITestResult result) {
		extentTest.get().log(Status.PASS, "Test Passed");
	}

	public void onTestFailure(ITestResult result) {
		extentTest.get().fail(result.getThrowable());
		//screenshot, attach it to report
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String filePath = null;
		try {
			filePath = getScreenshot(result.getMethod().getMethodName(), driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		extentTest.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
	}


	public void onFinish(ITestContext context) {
		reports.flush();
	}

}
