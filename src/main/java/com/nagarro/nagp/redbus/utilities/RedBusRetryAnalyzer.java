package com.nagarro.nagp.redbus.utilities;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RedBusRetryAnalyzer implements IRetryAnalyzer {

	private int intialCount = 0; // initial retry count is se to zero
	private static final int maxRetryCount = 2; // max retry set to 2

	/*
	 * method to keep retrying executing the failed test method till it returns true
	 */ public boolean retry(ITestResult result) {
		if (!result.isSuccess()) {
			if (intialCount < maxRetryCount) {
				intialCount++;
				return true;
			}
		}
		return false;
	}

}
