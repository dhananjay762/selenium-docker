package com.tests;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

public class BaseTest {
	
	protected WebDriver driver;
	
	@BeforeTest
	@Parameters({"headless"})
	public void setupDriver(String headless) throws MalformedURLException {
		//Browser type -> Chrome/Firefox
		//Hub_host -> localhost / 10.11.12.13 / hostname
		remoteDriver();
//		localDriver(headless);
		
	}
	
	public void localDriver(String headless) {
		System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");
		if(headless.equalsIgnoreCase("true")) {
			ChromeOptions options = new ChromeOptions();
			options.addArguments("headless");
			this.driver = new ChromeDriver(options);
		}else {
			this.driver = new ChromeDriver();
		}
	}
	
	public void remoteDriver() throws MalformedURLException {
		String host = "localhost";
		DesiredCapabilities dc;
		
		if(System.getProperty("BROWSER")!=null && System.getProperty("BROWSER").equalsIgnoreCase("firefox")) {
			dc = DesiredCapabilities.firefox();
		}else {
			dc = DesiredCapabilities.chrome();
		}
		
		if(System.getProperty("HUB_HOST")!=null) {
			host = System.getProperty("HUB_HOST");
		}
		
		String completeUrl = "http://" +host+ ":4444/wd/hub";
		this.driver = new RemoteWebDriver(new URL(completeUrl), dc);
	}
	
	@AfterTest
	public void closeBrowser() {
		driver.quit();
	}

}
