/**
 * 
 */
package com.nagarro.nagp.redbus.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.nagarro.nagp.redbus.utilities.CommonMethods;

public class BusSearchResultPage {

	/*
	 * constructor to accept the driver instance from the test method class and then
	 * this driver is used to initialize the page objects.
	 */

	public BusSearchResultPage(WebDriver driver) {

		PageFactory.initElements(driver, this);

	}

//page elements

	@FindBy(xpath = "//*[@title=\"SLEEPER\"]")
	private WebElement sortBySleeperClassButton;

	@FindBy(xpath = "//*[@class=\"fl set-filters\"]")
	private WebElement sortBySleeperClassSelected;

	@FindBy(xpath = "(//*[text()=\"View Seats\"])[1]")
	private WebElement viewSeatsFirstButton;

	@FindBy(xpath = "//*[text()='HIDE SEATS']")
	private WebElement hideSeatButton;

	/*
	 * method to sort the sleeper class of the available buses
	 */
	public void sortBySleeperClass() {

		CommonMethods.scroll(0, 100);
		sortBySleeperClassButton.click();

	}

	/*
	 * method to return boolean result based on sorting, if able to sort then true
	 * else false
	 */
	public boolean isSortSleeperClassButtonDIsplayed() {

		CommonMethods.explicitlyWaitForWebElement(sortBySleeperClassSelected);
		return sortBySleeperClassSelected.isDisplayed();
	}

	/*
	 * method to book the first available first by checking seats
	 */
	public void bookFirstBus() {

		CommonMethods.explicitlyWaitForWebElement(viewSeatsFirstButton);
		viewSeatsFirstButton.click();
		CommonMethods.explicitlyWaitForWebElement(hideSeatButton);
		hideSeatButton.click();
	}

}
