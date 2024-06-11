package com.nagarro.nagp.testpackage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.nagarro.nagp.redbus.basepack.Testbase;
import com.nagarro.nagp.redbus.pages.RailPage;

public class searchTrainTest extends Testbase {

	// created instance of the Logger
	private static final Logger logger = LogManager.getLogger(searchTrainTest.class);

	@Test(priority = 0, groups = "rail_regression", description = "Validate the user can search the live status of a train")
	public void searchTrain() throws InterruptedException {

		RailPage rail = new RailPage(getDriver());

		/*
		 * get journey details from config file
		 */
		logger.info("reading data from config file");

		String trainNo = config.getProperty("trainNO");

		logger.info("Going to rail page");

		rail.goToRailPage();
		logger.info("Now, searching train no");

		rail.searchRailStatus(getDriver(), trainNo);

		/*
		 * Assert whether rail status page is displayed
		 */

		logger.info("Asserting that train status is displayed");

		Assert.assertTrue(rail.getLiveRailPage(), "Some issue in getting train live status");

	}
	
	 
 
}
