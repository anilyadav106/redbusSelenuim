package com.nagarro.nagp.testpackage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.nagarro.nagp.redbus.basepack.Testbase;
import com.nagarro.nagp.redbus.pages.HomePage;
 
public class LandingPageTest extends Testbase {
	
	//created instance of the Logger 
    private static final Logger logger = LogManager.getLogger(LandingPageTest.class);


	@Test(enabled = true, groups="smoke",description = "Validate the landing page of the application")
	public void displayLandingPage() throws InterruptedException {
  
		HomePage home = new HomePage(getDriver());

         logger.info("Getting title of landing page");

		String title = home.getLandingPageTitle(); 
		/*
		 * Assert whether title is as expected
		 */
        logger.info("Asserting title of landing page");

		Assert.assertTrue(title.equals("Book Bus Tickets Online, Easy & Secure Booking, Top Operators - redBus"),
				"Landing page title mismatches");
	}

	@Test(enabled = true,groups="smoke",description = "Validate that the help page of the application is displayed")
	public void displayHelpPage() throws InterruptedException {

		HomePage home = new HomePage(getDriver());

         logger.info("Going to help page from landing page");

		home.goToHelpPage();

		/*
		 * Assert whether title is as expected
		 */
        logger.info("Asserting help page title");

		Assert.assertTrue(home.getHelpPageTitle().equals("red:Care"), "Help page title mismatches");
	}
	
	@Test(enabled = true,groups="smoke",description = "Validate the show ticket page of the application")
	public void displayShowTicketsPage() throws InterruptedException {

		HomePage home = new HomePage(getDriver());

         logger.info("Going to show my tickets page from landing page");

		home.goToShowMyTicketPage();

		/*
		 * Assert whether ticket no message is as expected
		 */
        logger.info("Asserting valid ticket no message");

		Assert.assertTrue(home.getValidTicketMessage().equals("Please enter a valid ticket number."), "Valid ticket no message mismatches");
	}

	@Test(enabled = true,groups="site_regression",description = "Validate that login page of the application is displayed")
	public void displayLoginForm() throws InterruptedException {

		HomePage home = new HomePage(getDriver());
        logger.info("Going to login page from landing page");

		home.goToLoginPage();

		/*
		 * Assert whether login page form is displayed
		 */
        logger.info("Asserting mobile no field on login page");

		Assert.assertTrue(home.isMobileInputFieldDisplayed(), "Mobile not input field is not displayed");
	}

}
