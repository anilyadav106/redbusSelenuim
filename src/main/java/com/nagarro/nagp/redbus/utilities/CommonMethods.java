package com.nagarro.nagp.redbus.utilities;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
 import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.nagarro.nagp.redbus.basepack.Testbase;

public class CommonMethods extends Testbase {

	//private static WebDriver driver;
	static String explicitWait=config.getProperty("explicitWait");
	

	 

	/*
	 * method to explicitly wait for the visibility of the desired web element.
	 * 
	 * @Param- accepts web element to wait for
	 */

	public static void explicitlyWaitForWebElement(WebElement we) {

		WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(Long.parseLong(explicitWait)));
		wait.until(ExpectedConditions.visibilityOf(we));

	}

	/*
	 * method to explicitly wait for the click ability of the desired web element.
	 * 
	 * @Param- accepts web element to wait for
	 */
	public static   void explicitlyWaitForElementClick(WebElement we) {

		WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(Long.parseLong(explicitWait)));
		wait.until(ExpectedConditions.elementToBeClickable(we));
	}

	/*
	 * Static Method to launch capture screenshot in case of failure of test case.
	 * Failure of TC is decided from listener class' onTestFailure method
	 * 
	 * It returns the string representation path of the screen shot upon execution
	 */

	public static String captureScreeshot(String testMethodName) {
		// log.debug("Launching the capture screen shot");
		File source = null;
		if (driver != null) {
			source = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		}
		Date d = new Date();
		String fileName = d.toString().replace(":", "-").replace(" ", "-");

		// project directory path where screen shot will be stored

		String path = System.getProperty("user.dir") + "/Current test results/" + fileName + " " + testMethodName
				+ ".png";
		File destination = new File(path);

		try {

			FileUtils.copyFile(source, destination);
		} catch (IOException e) {
			System.out.println("Error in capturing screen shot" + e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("An unknown exception occured while capturing screen shot:" + e.getMessage());
			e.printStackTrace();
		}
		return path;
	}

	/*
	 * method to select the desired date from the calendar
	 * 
	 * @Params- desired month string , desired date string
	 */

	public static void CalendarDatePicker(String desired_month, String desired_date) {

		// first click the calendar button.

		getDriver().findElement(By.xpath("//*[@id='onwardCal']/div/div")).click();

		while (true) {

			String actual_month = getDriver().findElement(By.xpath("//*[@id='onwardCal']/div/div[2]/div/div/div[1]/div[2]"))
					.getText();

			// if current month of the calendar and desired month is same, come out of the
			// while loop as no need to go to any further months.

			if (actual_month.contains(desired_month)) {

				break;

			} else {
				getDriver().findElement(By.xpath("//*[@id='onwardCal']/div/div[2]/div/div/div[1]/div[3]")).click(); // click
																												// on
																												// next
																												// month
																												// icon
																												// button

			}
		}

		int column_size = 7; // as per maximum no of days (Mon - Sun) can be 7 only in header of calendar
		int flag = 0;
		int row_size = getDriver().findElements(By.xpath("//*[@id='onwardCal']/div/div[2]/div/div/div[3]/div")).size(); // rows
																													// varies
																													// based
																													// on
																													// month

		// loop starts from second row as first is month row only

		for (int i = 2; i <= row_size; i++) { // loop for rows

			for (int j = 1; j <= column_size; j++) { // loop for columns

				// constructed the dynamic xpath based on i and j

				String actual_date = getDriver()
						.findElement(By.xpath(
								"//*[@id='onwardCal']/div/div[2]/div/div/div[3]/div[" + i + "]/span/div[" + j + "]"))
						.getText();
				// if current date of the month and desired date is same, come out of the inner
				// for loop as no need to go to any further dates.

				if (actual_date.equals(desired_date)) {
					getDriver().findElement(By
							.xpath("//*[@id='onwardCal']/div/div[2]/div/div/div[3]/div[" + i + "]/span/div[" + j + "]"))
							.click();
					flag = 1; // set flag = 1 because desired calendar date and month is found now.
					break;
				} else {
					continue;
				}
			}

			if (flag == 1) { // selection of the date is done
				break;
			}
		}
	}
 
	/*
	 * method to scroll on the page using javascriptexecutor
	 * 
	 * @Params- horizontal pixel value, vertical pixel value
	 */
	public  static void scroll(int horizontalpixel, int Verticalpixel) {

		try {
			JavascriptExecutor js = (JavascriptExecutor) getDriver();
			js.executeScript("window.scrollBy(" + horizontalpixel + "," + Verticalpixel + ")", "");
		} catch (Exception e) {
			System.out.println("Unable to scroll as required " + e.getMessage());
			e.printStackTrace();
		}
	}

}
