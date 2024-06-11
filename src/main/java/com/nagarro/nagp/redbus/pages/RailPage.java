/**
 * 
 */
package com.nagarro.nagp.redbus.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.nagarro.nagp.redbus.basepack.Testbase;
import com.nagarro.nagp.redbus.utilities.CommonMethods;

public class RailPage extends Testbase {

	/*
	 * constructor to accept the driver instance from the test method class and then
	 * this driver is used to initialize the page objects.
	 */
	public RailPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	// elements
	@FindBy(xpath = "//*[text()='Live Train Status']")
	private WebElement liveRailRadionbutton;
	@FindBy(xpath = "//*[@class='pnr-input-text']")
	private WebElement enterTrainNoInputBox;

	@FindBy(xpath = "//*[text()='Train Tickets']")
	private WebElement railPage;

	@FindBy(xpath = "//input[@id='src']")
	private WebElement srcStationRail;
	@FindBy(xpath = "//*[@for='dst']")
	private WebElement destStationRail;

	@FindBy(xpath = "//*[@alt='calendar_icon']")
	private WebElement dateOfJourneyTrain;

	@FindBy(xpath = "//button[text()='search trains']")
	private WebElement searchRailButton;

	@FindBy(xpath = "//button[text()='Check Status']")
	private WebElement searchLiveRailButton;
	@FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/div/div[1]/div[1]/span")
	private static WebElement railStatusBar;
	@FindBy(xpath = "//*[@class='lts_solr_wrap_main']")
	private static WebElement dropdownAutoSuggestion;

	/*
	 * method to got to rail page
	 */
	public void goToRailPage() {

		railPage.click();

	}

	/*
	 * method to search the rail status.
	 * 
	 * @Params- driver object, trainNo string
	 */
	public void searchRailStatus(WebDriver driver, String trainNo) throws InterruptedException {
		CommonMethods.scroll(0, 100);

		liveRailRadionbutton.click(); // to click on live train status button

		enterTrainNoInputBox.sendKeys(trainNo);

		CommonMethods.explicitlyWaitForElementClick(dropdownAutoSuggestion); // to wait for source station auto
																				// suggestions to
		// appears
		CommonMethods.explicitlyWaitForElementClick(
				dropdownAutoSuggestion.findElement(By.xpath("//*[@id=\"root\"]/div/section/div[2]/div[4]/div/div")));
		Thread.sleep(3000);
		dropdownAutoSuggestion.findElement(By.xpath("//*[@id=\"root\"]/div/section/div[2]/div[4]/div/div")).click();

		searchLiveRailButton.click(); // click on the search rail button
		CommonMethods.scroll(100, 0);
	}

	/*
	 * method to display live rail status page, returns true in case desired train
	 * page is displayed else false
	 */
	public boolean getLiveRailPage() {
		CommonMethods.explicitlyWaitForWebElement(railStatusBar);
		String trainName = config.getProperty("trainName");

		return railStatusBar.getText().contains(trainName);
	}

}
