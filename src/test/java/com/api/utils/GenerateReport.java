package com.api.utils;

import org.testng.ITestListener;

import com.api.constants.SourcePath;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class GenerateReport implements ITestListener{
	ExtentHtmlReporter htmlReporter;
	public static ExtentReports extent;
	public static ExtentTest extentTest;
	public static GenerateReport ob;
	
	private GenerateReport() {                     														
}
	public static GenerateReport getInstance() {
		if(ob==null) {
			ob=new GenerateReport();
		}
		return ob;
	}

	public void startExtentReport(){
		
		htmlReporter = new ExtentHtmlReporter(SourcePath.GENERATE_REPORT_PATH);
		extent = new ExtentReports();
		
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Host Name", "Salesforce");
		extent.setSystemInfo("Environment", "Automation Testing");
		extent.setSystemInfo("User Name", "Sahil");
		
		htmlReporter.config().setDocumentTitle("Test Execution Report");
		htmlReporter.config().setReportName("salesforce regression tests");
		htmlReporter.config().setTheme(Theme.STANDARD);
	}
	    public void startSingleTestReport(String testcasename) {
	    	extentTest = extent.createTest(testcasename);
	    }
	    public void logTestInfo(String message) {
	    	extentTest.log(Status.INFO, message);
	    }
	    public void logTestpassed(String testcaseName) {
	    	extentTest.log(Status.PASS, MarkupHelper.createLabel(testcaseName + "ispassTest", ExtentColor.GREEN));
	    }
	    public void logTestfailed(String testcaseName) {
	    	extentTest.log(Status.FAIL, MarkupHelper.createLabel(testcaseName + "isfailedTest", ExtentColor.RED));
	    }
		public void logTestFailedWithException(Exception e) {
			extentTest.log(Status.ERROR, e);
		}
		public void logTestSkipped(String testcaseName) {
			extentTest.log(Status.SKIP, MarkupHelper.createLabel(testcaseName + "skipped the test", ExtentColor.YELLOW));
		}
		public void endReport() {
			extent.flush();
		}
}

