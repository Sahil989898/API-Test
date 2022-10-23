package com.api.utils;

import org.testng.ITestListener;
import org.testng.ITestResult;

import com.api.Helpers.UserServiceHelper;

public class ListenerClass extends UserServiceHelper implements ITestListener{
	public static GenerateReport report;

	// GenerateReports report = GenerateReports.getInstance();
	
	public void onTestFailure(ITestResult result) {
		report.logTestfailed("Test case Failed"+ result.getName());
		//String pathOfScreenshot = gescreenshotdriver();
		//report.logscreenshotCApture(pathOfScreenshot, pathOfScreenshot);		
	}
	public void onTestSuccess(ITestResult result) {
		report.logTestInfo("Test case pass for "+ result.getName());
	
	}	
}