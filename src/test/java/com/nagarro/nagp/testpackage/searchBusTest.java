package com.nagarro.nagp.testpackage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.nagarro.nagp.redbus.basepack.Testbase;
import com.nagarro.nagp.redbus.excelreader.ExcelReader;
import com.nagarro.nagp.redbus.pages.BusSearchResultPage;
import com.nagarro.nagp.redbus.pages.HomePage;
import com.nagarro.nagp.redbus.utilities.CommonMethods;

public class searchBusTest extends Testbase {
	
	//created instance of the Logger 
    private static final Logger logger = LogManager.getLogger(searchBusTest.class);

	 
	@Test(enabled = true,groups="smoke",description = "Validate the no bus serach result page is displayed")
	public void searchBusNoResult() throws InterruptedException {
		
		HomePage home= new HomePage(getDriver());
		 
   
 		/*
		 * get journey details from config file
		 */
        logger.info("Reading data from config file for searchBusNoResult test method");

		String fromLocation = config.getProperty("busFrom_1");
		String toLocation = config.getProperty("busTo_1");
		String journeyMonth = config.getProperty("busJourneyMonth_1");
		String journeyYear = config.getProperty("busJourneyYear_1");
		String JourneyDay = config.getProperty("busJourneyDay_1");
        logger.info("Now, searching with data of " +fromLocation + "," + toLocation + "," +""+journeyMonth+ "," +journeyYear+","+JourneyDay);

		home.searchBus(getDriver(),fromLocation, toLocation, journeyMonth,journeyYear, JourneyDay);
		CommonMethods.explicitlyWaitForWebElement(home.getNoBusFoundMsg());

		/*
		 * Assert whether no bus message is displayed in case of no search result
		 */
        logger.info("Asserting that no bus is found");

		Assert.assertTrue(home.getNoBusFoundMsg().isDisplayed(),
				"NO bus found message is NOT displayed, seems buses are available now");
	}
	@Test(enabled = true,groups="smoke",description = "Validate that the  bus results can be sorted")
	public void sortBusBySleeperClass() throws InterruptedException {
		HomePage home= new HomePage(getDriver());
 
		BusSearchResultPage busSearch= new BusSearchResultPage(getDriver());
		/*
		 * get journey details from config file
		 */
        logger.info("Reading data from config file for sortBusBySleeperClass test method");

		String fromLocation = config.getProperty("busFrom_2");
		String toLocation = config.getProperty("busTo_2");
		String journeyMonth = config.getProperty("busJourneyMonth_2");
		String journeyYear = config.getProperty("busJourneyYear_2");
		String JourneyDay = config.getProperty("busJourneyDay_2");
        logger.info("Now, searching with data of " +fromLocation + "," + toLocation + "," +""+journeyMonth+ "," +journeyYear+","+JourneyDay);

		home.searchBus(getDriver(), fromLocation, toLocation, journeyMonth,journeyYear, JourneyDay);
		CommonMethods.explicitlyWaitForWebElement(home.getViewSeatsFirstResult());

  	 	busSearch.sortBySleeperClass( );
 
		/*
		 * Assert whether no bus message is displayed in case of no search result
		 */
        logger.info("Asserting that buses are found");

		Assert.assertTrue(busSearch.isSortSleeperClassButtonDIsplayed(),
				"Sort is NOT done");
	}

	@Test(enabled = true ,dataProvider="excelData",groups="bus_regression",description = "Validate the bus search results are displayed")
	public void searchBusResultFound(String param1, String param2, String param3, String param4 ,String param5) throws InterruptedException {
		
		HomePage home= new HomePage(getDriver());
		 
 		/*
		 * get journey details from config file
		 */
        logger.info("reading data from excel file");

       
		String fromLocation = param1;
		String toLocation = param2;
		String journeyMonth = param3;
		String journeyYear = param4;
		String JourneyDay = param5;
		
        logger.info("searching buses for test data from excel sheet");
        logger.info("Now, searching with excel data of " +fromLocation + "," + toLocation + "," +""+journeyMonth+ "," +journeyYear+","+JourneyDay);

		home.searchBus(getDriver(), fromLocation, toLocation, journeyMonth,journeyYear, JourneyDay);
		CommonMethods.explicitlyWaitForWebElement(home.getViewSeatsFirstResult());

		/*
		 * Assert whether first bus result is displayed
		 */
        logger.info("Asserting that view seats is displayed");

		Assert.assertTrue(home.getViewSeatsFirstResult().isDisplayed(),
				"Bus results are NOT displayed, seems buses are NOT available now");
	}
	
	
	/*
	 * method to try to book an available bus, it will click on view seats button
	 * and then click on hide seats
	 */	
	@Test(enabled = true ,dataProvider="excelData",groups="bus_regression",description = "Validate that user can try to book the bus by checking seats avialibility")
	public void bookBus(String param1, String param2, String param3, String param4 ,String param5) throws InterruptedException {
		
		HomePage home= new HomePage(getDriver());
		 
 		/*
		 * get journey details from config file
		 */
        logger.info("reading data from excel file");

       
		String fromLocation = param1;
		String toLocation = param2;
		String journeyMonth = param3;
		String journeyYear = param4;
		String JourneyDay = param5;
		
        logger.info("searching buses for test data from excel sheet");
        logger.info("Now, searching with excel data of " +fromLocation + "," + toLocation + "," +""+journeyMonth+ "," +journeyYear+","+JourneyDay);

		home.searchBus(getDriver(), fromLocation, toLocation, journeyMonth,journeyYear, JourneyDay);
		CommonMethods.explicitlyWaitForWebElement(home.getViewSeatsFirstResult());

		BusSearchResultPage busSearch = new BusSearchResultPage(getDriver());
		busSearch.bookFirstBus();
		
		/*
		 * Assert whether first bus result is displayed
		 */
        logger.info("Asserting that view seats is displayed");

		Assert.assertTrue(home.getViewSeatsFirstResult().isDisplayed(),
				"Bus results are NOT displayed, seems buses are NOT available now");
	}
	
	
	
	/*
	 *  Data provider testng method to read excel values and return the 2D
	 * object array object
	 */	
	
	@DataProvider(name = "excelData")
	    public Object[][] getData() { 
	        logger.info("Trying to read data from excel file");

	        return ExcelReader.readExcelData();
	    }
	

}
