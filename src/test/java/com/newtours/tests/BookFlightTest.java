package com.newtours.tests;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.newtours.pages.FlightDetailsPage;
import com.newtours.pages.RegistrationConfirmation;
import com.newtours.pages.RegistrationPage;
import com.tests.BaseTest;

public class BookFlightTest extends BaseTest{
	
	private String url = "https://demo.guru99.com/test/newtours/register.php";
	private String noOfPassengers;
	
	@BeforeTest
	@Parameters({"noOfPassengers"})
	public void setupParameters(String noOfPassengers) {
		this.noOfPassengers = noOfPassengers;
	}
	
	@Test
	public void registrationPage() {
		RegistrationPage registration = new RegistrationPage(driver);
		registration.goTo(url);
		registration.enterUserDetails("John", "Smith");
		registration.enterUserCredentials("john1234", "pass1234");
		registration.submit();
	}
	
	@Test(dependsOnMethods = "registrationPage")
	public void registrationConfirmationPage() {
		RegistrationConfirmation regConfirm = new RegistrationConfirmation(driver);
		regConfirm.goToFlightDetailsPage();
	}
	
	@Test(dependsOnMethods = "registrationConfirmationPage")
	public void flightDetailsPage() {
		FlightDetailsPage flightDetails = new FlightDetailsPage(driver);
		flightDetails.selectPassengers(noOfPassengers);
		flightDetails.goToFindFlightsPage();
	}
	
	@AfterTest
	public void closeBrowser() {
		driver.quit();
	}
	

}
