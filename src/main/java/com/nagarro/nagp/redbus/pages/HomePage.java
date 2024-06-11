/**
 * 
 */
package com.nagarro.nagp.redbus.pages;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.nagarro.nagp.redbus.utilities.CommonMethods;

public class HomePage {
	private WebDriver driver;

	/*
	 * constructor to accept the driver instance from the test method class and then
	 * this driver is used to initialize the page objects.
	 */
	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}
	// page elements

	@FindBy(xpath = "//*[@id=\"help_redcare\"]/div/span")
	private WebElement helpButton;

	@FindBy(xpath = "//*[@id=\"account_dd\"]/div[1]/span[text()='Account']")
	private WebElement accountButton;
	@FindBy(xpath = "//*[text()='Show My Ticket']")
	private WebElement showMyTicketbutton;

	@FindBy(xpath = "//*[@id='searchTicketMobileno']")
	private WebElement ticketMobileNo;

	@FindBy(xpath = "//*[@id='ticketSearch']")
	private WebElement ticketSearchButton;
	@FindBy(xpath = "//*[text()='Please enter a valid ticket number.']")
	private WebElement ticketNoMessage;

	@FindBy(xpath = "//*[@id=\"user_sign_in_sign_up\"]/span")
	private WebElement loginButton;
	@FindBy(xpath = "//*[@id=\"mobileNoInp\"]")
	private WebElement mobileNoInput;

	@FindBy(xpath = "//*[@id='src']")
	private WebElement srcStationBus;
	@FindBy(xpath = "//*[@id='dest']")
	private WebElement destStationBus;

	@FindBy(xpath = "//*[@id='onwardCal']")
	private WebElement dateOfBusJourney;

	@FindBy(xpath = "//*[@id='search_button']")
	private WebElement searchButtonBus;
	@FindBy(xpath = "(//*[text()='Oops! No buses found.'])[2]")
	private static WebElement noBusFoundMsg;

	@FindBy(xpath = "(//*[text()='View Seats'])[1]")
	private static WebElement viewSeatsFirstResult;
	@FindBy(xpath = "//*[@class='sc-dnqmqq eFEVtU']")
	private static WebElement dropdownAutoSuggestion;

	/*
	 * method to search the buses.
	 * 
	 * @Params- driver object, from location, to location, desired month, desired
	 * year, desired date
	 */

	public void searchBus(WebDriver driver, String from, String to, String desired_month, String desired_year,
			String desired_date) throws InterruptedException {
 
		String desiredCombinedDateYear = desired_month + " " + desired_year;
		// wait for the calendar field
		CommonMethods.explicitlyWaitForElementClick(dateOfBusJourney);

		CommonMethods.CalendarDatePicker(desiredCombinedDateYear, desired_date);
		// enter value in source field
		srcStationBus.sendKeys(from);
		// wait for the auto suggestion values

		CommonMethods.explicitlyWaitForElementClick(dropdownAutoSuggestion);
		// select the required value only from the suggestions
		dropdownAutoSuggestion.findElement(By.xpath("//*[text()='" + from + "']")).click();

		Thread.sleep(2000);
		// enter value in destination field

		destStationBus.sendKeys(to);
		CommonMethods.explicitlyWaitForElementClick(dropdownAutoSuggestion);
		dropdownAutoSuggestion.findElement(By.xpath("//*[text()='" + to + "']")).click();

		Thread.sleep(2000);
		// finally click on search button to search the buses
		searchButtonBus.click();

	}

	/*
	 * method to return the landing page title
	 */
	public String getLandingPageTitle() {

		return driver.getTitle();
	}

	/*
	 * method to go to login page
	 */
	public void goToLoginPage() {

		accountButton.click();
		loginButton.click();
	}

	/*
	 * method to go to the help page
	 */
	public void goToHelpPage() {

		helpButton.click();
		// Get handles of all open windows/tabs
		Set<String> windowHandles = driver.getWindowHandles();

		// Switch to the new tab (assuming only two windows/tabs are open - the original
		// and the new tab)
		for (String windowHandle : windowHandles) {
			if (!windowHandle.equals(driver.getWindowHandle())) {
				driver.switchTo().window(windowHandle);
				break;
			}
		}
	}

	/*
	 * method to go to the show my tickets page
	 */
	public void goToShowMyTicketPage() {
 		accountButton.click();
		showMyTicketbutton.click();
		// Get handles of all open windows/tabs
		Set<String> windowHandles = driver.getWindowHandles();

		// Switch to the new tab (assuming only two windows/tabs are open - the original
		// and the new tab)
		for (String windowHandle : windowHandles) {
			if (!windowHandle.equals(driver.getWindowHandle())) {
				driver.switchTo().window(windowHandle);
				break;
			}
		}
		CommonMethods.explicitlyWaitForWebElement(ticketSearchButton);
		ticketSearchButton.click();
	}

	/*
	 * method to return the help page title
	 */

	public String getHelpPageTitle() {

		return driver.getTitle();
	}

	/*
	 * method to return the valid ticket no message
	 */

	public String getValidTicketMessage() {
 
		return ticketNoMessage.getText();
	}

	/*
	 * method to return boolean result based on availability of mobile no field.
	 */
	public boolean isMobileInputFieldDisplayed() {
 
		// switch to iframe first based on its ID

		driver.switchTo().frame(0);
		CommonMethods.explicitlyWaitForElementClick(mobileNoInput);

		return mobileNoInput.isDisplayed();
	}

	/*
	 * method to return the no bus message webelement in case no buses are available
	 */
	public WebElement getNoBusFoundMsg() {
		return noBusFoundMsg;
	}

	/*
	 * method to return the first bus' view seats button webelement in case buses
	 * are available
	 */
	public WebElement getViewSeatsFirstResult() {
		CommonMethods.scroll(0, 80);
		CommonMethods.explicitlyWaitForWebElement(viewSeatsFirstResult);
		return viewSeatsFirstResult;
	}

}
