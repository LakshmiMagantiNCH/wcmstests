package listenerClasses;

import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class ExtentReportListener {
		
		private static ExtentSparkReporter sparkReporter; //UI of the report
		private static ExtentReports extent; //populate common info on the report
		private static ExtentTest test; //creating test case entries in the report and update status of the test methods
	
		@Before
		public static void setup() {
			
			sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "\\test-output\\testReport.html");
			
			sparkReporter.config().setOfflineMode(true);
			sparkReporter.config().setDocumentTitle("Automation Tests"); //Title of the report
			sparkReporter.config().setReportName("TestReport"); //name of the report
			sparkReporter.config().setEncoding("UTF-8");
			sparkReporter.config().setTimeStampFormat("EEE, MMMM dd,YYYY, hh:mm a '('zzz')'");
			sparkReporter.config().setTheme(Theme.STANDARD);
			
			extent = new ExtentReports();
			extent.attachReporter(sparkReporter);
			extent.setSystemInfo("Environment", "QA");
			extent.setSystemInfo("Browser name", "Chrome");
		}
		
		@Before
		public static void startTest(Scenario result) {
			test = extent.createTest(result.getName());
			test.log(Status.PASS, "Test case PASSED is:" + result.getName()); //created a new entry in the report
			
		}
		
		public static void onTestFailure(ITestResult result) {
			test = extent.createTest(result.getName());
			test.log(Status.FAIL, "Test case FAILED is:" + result.getName());
			test.log(Status.FAIL, "Test case FAILURE cause:" + result.getThrowable());
			
		}
		
		public static void onTestSkipped(Scenario result) {
			test = extent.createTest(result.getName());
			test.log(Status.SKIP, "Test case SKIPPED is:" + result.getName());
			
		}
		
		public static ExtentTest getTest() {
	        return test;
	    }
		
		@After
		public static void tearDown(Scenario result) {
			extent.flush();
			
		}
	


}
