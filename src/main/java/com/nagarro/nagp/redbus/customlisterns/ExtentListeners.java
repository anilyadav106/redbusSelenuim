package com.nagarro.nagp.redbus.customlisterns;

import java.io.IOException;

import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.nagarro.nagp.redbus.utilities.CommonMethods;
import com.nagarro.nagp.redbus.utilities.TestResultHandler;

public class ExtentListeners implements ITestListener, ISuiteListener {

	static String fileName = "Extent_Report_RedBus" + ".html";

	public static ExtentReports extent = ExtentManager
			.createReport(System.getProperty("user.dir") + "\\Current test results\\" + fileName);
	
	/* To make our report thread safe */
	
	public static ThreadLocal<ExtentTest> testReport = new ThreadLocal<ExtentTest>();
	public static ExtentTest test;

	public void onTestStart(ITestResult result) {  
		System.out.println("Test case  :" +result.getMethod().getMethodName() + " started");
		/* to create a test */
		test = extent.createTest("Test case : " + result.getMethod().getDescription());
 
		/*
		 * to add above test objects to threadlocal, so that correct test object is
		 * assigned in case of parallel executions.
		 */
		testReport.set(test);

	}

	public void onTestSuccess(ITestResult result) {
		
 		String methodName = result.getMethod().getMethodName();
		String logText = "<b>" + "Test case :- " + methodName.toUpperCase() + " is PASSED" + "</b>";
		Markup m = MarkupHelper.createLabel(logText, ExtentColor.GREEN);

		testReport.get().pass(m);

	}

	public void onTestFailure(ITestResult result) {
 
		String exceptionMessage = result.getThrowable().getMessage();

		testReport.get()
				.fail("<details>" + "<summary>" + "<b>" + "<font color=" + "red>"
						+ "An exception Occured: Click here to see" + "</font>" + "</b >" + "</summary>"
						+ exceptionMessage.replaceAll(",", "<br>") + "</details>" + " \n");

		/*
		 * to capture screen shot also for test failure and add to test report
		 */
		
		String path = CommonMethods.captureScreeshot(result.getMethod().getMethodName());

		try {
			test.fail(result.getThrowable().getMessage(), MediaEntityBuilder.createScreenCaptureFromPath(path).build());
		} catch (IOException e) {

			e.printStackTrace();
		}
		String logText = "TEST CASE FAILED";
		Markup m = MarkupHelper.createLabel(logText, ExtentColor.RED);

		testReport.get().log(Status.FAIL, m);

	}

	public void onTestSkipped(ITestResult result) {
 
		String methodName = result.getMethod().getMethodName();
		String logText = "<b>" + "Test case:- " + methodName + " is skipped" + "</b>";
		Markup m = MarkupHelper.createLabel(logText, ExtentColor.YELLOW);
		testReport.get().skip(m);

	}

	public void onFinish(ITestContext context) {

		/* to flush the extent report */
		if (extent != null) {
			TestResultHandler.storeTestResults();

			extent.flush();
		}

	}

	/*
	 * on start of any test case, first it will move the Current test results
	 * content into Archived test results folder.
	 */	
	public void onStart(ISuite suite) {
		Reporter.log("test suite started");
		TestResultHandler.archivePreviousResults();

	}

	
	public void onFinish(ISuite suite) {

		Reporter.log("test suite ended");
 
	}

}
