package com.nagarro.nagp.redbus.customlisterns;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {

	/*
	 * This method to be used in ExtentListeners class to create instance of
	 * extent report.
	 */
	public static ExtentReports createReport(String fileName) {

		//create ExtentHtmlReporter 
		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(fileName);
		//set some configuration to the html page
		htmlReporter.config().setTheme(Theme.DARK);
		htmlReporter.config().setDocumentTitle("Extent report Page Title");
		htmlReporter.config().setEncoding("utf-8");
		htmlReporter.config().setReportName("Test Report for RedBus Testing Framework designed by Anil");
		htmlReporter.config().setCSS(".r-img { width: 50%; }");
       //create extent report
		ExtentReports extent = new ExtentReports();
      //attach the htmlreporter to this extent report
		extent.attachReporter(htmlReporter);
		//set some properties to the extent report page
		extent.setSystemInfo("Automation Tester", "Anil Kumar");
		extent.setSystemInfo("Organization", "Nagarro");
		extent.setSystemInfo("Build no", "RedBus-1234");
		//return this extent report object
		return extent;
	}

}
