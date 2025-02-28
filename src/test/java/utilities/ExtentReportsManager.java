package utilities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testCases.BaseClass;

public class ExtentReportsManager implements ITestListener {
	
	 public ExtentSparkReporter sparkReporter; //
	 public ExtentReports extent;
	 public ExtentTest test;
	 
	 String repname;
	 
	 public void onStart(ITestContext context) {
		/* SimpleDateFormat df = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
		 Date dt = new Date();
		 String currentdatetiestamp = df.format(dt);*/
		 
		 String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		 
		 repname = "Test-Report-" + timestamp + ".html";
		 sparkReporter = new ExtentSparkReporter("C:\\Selenium_Prj_1\\WebDriverDemo\\Reports\\"+repname);
		 sparkReporter.config().setDocumentTitle("Automation Report");
		 sparkReporter.config().setReportName("Functional Testing");
		 sparkReporter.config().setTheme(Theme.DARK);
		 
		 extent = new ExtentReports();
		 extent.attachReporter(sparkReporter);
		 
		 extent.setSystemInfo("Computer Name", "localhost");
		 extent.setSystemInfo("Environment", "QA");
		 extent.setSystemInfo("Application", "OpenCart");
		 extent.setSystemInfo("Tester Name", "Harshita");
		 extent.setSystemInfo("OS", "Windows10");
		 //extent.setSystemInfo("Browser Name", "Chrome");
		 
		 String browser = context.getCurrentXmlTest().getParameter("browser");
		 extent.setSystemInfo("Browser", browser);
		 
		 List<String> includedGroups = context.getCurrentXmlTest().getIncludedGroups();
		 if(!includedGroups.isEmpty()) {
			 extent.setSystemInfo("Groups", includedGroups.toString());
		 }
	 }
	 
	 public void onTestSuccess(ITestResult result) {
		 test = extent.createTest(result.getName());
		 test.assignCategory(result.getMethod().getGroups()); // to display groups in report
		 test.log(Status.PASS, "Test case passed is : "+result.getName());
	 }
		  
	 public void onTestFailure(ITestResult result) {
		 test = extent.createTest(result.getName());
		 test.assignCategory(result.getMethod().getGroups());
		 test.log(Status.FAIL, "Test case failed is : "+result.getName());
		 test.log(Status.INFO, "Test case failed cause is : "+result.getThrowable());
		 
		 try {
			 String imgPath = new BaseClass().captureScreen(result.getName());
			 test.addScreenCaptureFromPath(imgPath);
		 }
		 catch(Exception e) {
			 e.printStackTrace();
		 }
	 }
		  
	 public void onTestSkipped(ITestResult result) {
		 test = extent.createTest(result.getName());
		 test.assignCategory(result.getMethod().getGroups());
		 test.log(Status.SKIP, "Test case skipped is : "+result.getName());
		 test.log(Status.INFO, "Test case skipped cause is : "+result.getThrowable());
	 }
		  
	 

	 public void onFinish(ITestContext context) {
		 extent.flush();
	 }

}
