import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class Listeners extends BaseTest implements ITestListener {
	
	ExtentReports reports = ExtentReportTest.reportsConfig();
	ExtentTest test;
	public void onTestStart(ITestResult result) {
		test = reports.createTest(result.getMethod().getMethodName());
	}

	public void onTestSuccess(ITestResult result) {
		test.log(Status.PASS, "Test Passed");
	}

	public void onTestFailure(ITestResult result) {
		test.fail(result.getThrowable());
		//screenshot, attach it to report
		String filePath = null;
		try {
			String filePath = getScreenshot(result.getMethod().getMethodName());
		}catch (IOException e) {
			e.printStackTrace();	
		}
		test.addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
	}

	public void onTestSkipped(ITestResult result) {

	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

	}

	public void onTestFailedWithTimeout(ITestResult result) {
		onTestFailure(result);

	}

	public void onStart(ITestContext context) {

	}

	public void onFinish(ITestContext context) {
		reports.flush();
	}

}
