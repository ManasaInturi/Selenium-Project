package Utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportTest {
	
	public static ExtentReports reportsConfig() {
		
		String filePath = System.getProperty("user.dir")+"//ExtentReports//index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(filePath);
		reporter.config().setDocumentTitle("Automation Test Results");
		reporter.config().setReportName("Web Automation Report");
		
		ExtentReports reports = new ExtentReports();
		reports.attachReporter(reporter);
		reports.setSystemInfo("Tester", "Manasa Inturi");
		return reports;
	}

}
