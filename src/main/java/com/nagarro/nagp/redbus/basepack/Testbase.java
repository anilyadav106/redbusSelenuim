package com.nagarro.nagp.redbus.basepack;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
 import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Testbase {

	protected static Properties config = new Properties();
	private static FileInputStream fis;

	private static String browser;

	protected static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
	private static final Logger logger = LogManager.getLogger(Testbase.class);

	/*
	 * This method will run before any test method and will launch the desired
	 * browser
	 */
	@BeforeMethod
	public static void launchBrowser() throws IOException {
		try {
			fis = new FileInputStream(".\\src\\test\\resources\\properties\\appConfig.properties");
			config.load(fis);
			browser = config.getProperty("browser");
		} catch (IOException e) {
			System.out.println("unable to read the config properties file");
			e.printStackTrace();
		}
         // to launch the browser based on name specified in property file
		if (browser.contains("edge")) {
			logger.info("Launching edge browser");

			WebDriverManager.edgedriver().setup();
			EdgeOptions options = new EdgeOptions();
			options.addArguments("-inprivate");

			options.addArguments("--remote-allow-origins=*");
			options.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);

			// set method is used to set the thread local driver instance

			driver.set(new EdgeDriver(options));
			logger.info("Launching base URL");

			getDriver().get(config.getProperty("baseURL"));
			getDriver().manage().window().maximize();
			String pageLoadTimeout = config.getProperty("pageLoadTimeout");

			getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Long.parseLong(pageLoadTimeout)));

		} else if (browser.contains("chrome")) {
			logger.info("Launching chrome browser");
 
			  WebDriverManager.chromedriver().setup();

			// set method is used to set the thread local driver instance

			driver.set(new ChromeDriver());
 
			logger.info("Launching base URL");
 			getDriver().get(config.getProperty("baseURL"));
 
			getDriver().manage().window().maximize();
			String pageLoadTimeout = config.getProperty("pageLoadTimeout");
			getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Long.getLong(pageLoadTimeout)));

		} else if (browser.contains("firefox")) {
			logger.info("Launching firefox browser");

			WebDriverManager.firefoxdriver().setup();

			// set method is used to set the thread local driver instance

			driver.set(new FirefoxDriver());
			logger.info("Launching base URL");

			getDriver().get(config.getProperty("baseURL"));
			getDriver().manage().window().maximize();
			String pageLoadTimeout = config.getProperty("pageLoadTimeout");
			getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Long.getLong(pageLoadTimeout)));

		}
	}

	/*
	 * method to get the thread local driver instance
	 */
	public static WebDriver getDriver() {

		return driver.get();
	}

	/*
	 * this method is used to close the browser instance after each test method
	 * completion
	 */
	@AfterMethod
	public void afterMethodTearDown() {
		logger.info("quitting browser after test method");

		getDriver().quit();
	}

}