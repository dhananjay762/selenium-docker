package com.newtours.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class RegistrationPage{
	
	public WebDriver driver;
	public WebDriverWait wait;
	
	@FindBy(name="firstName")
	private WebElement firstNameTxt;
	
	@FindBy(name="lastName")
	private WebElement lastNameTxt;
	
	@FindBy(name="email")
	private WebElement usernameTxt;
	
	@FindBy(name="password")
	private WebElement passwordTxt;
	
	@FindBy(name="confirmPassword")
	private WebElement cnfrmPswdTxt;
	
	@FindBy(name="submit")
	private WebElement submit;
	
	public RegistrationPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new	WebDriverWait(driver, 30);
		PageFactory.initElements(driver, this);
	}
	
	public void goTo(String URL) {
		this.driver.navigate().to(URL);
		this.wait.until(ExpectedConditions.visibilityOf(this.firstNameTxt));
		
	}
	
	public void enterUserDetails(String firstName, String lastName) {
		this.firstNameTxt.sendKeys(firstName);
		this.lastNameTxt.sendKeys(lastName);
	}
	
	public void enterUserCredentials(String username, String password) {
		this.usernameTxt.sendKeys(username);
		this.passwordTxt.sendKeys(password);
		this.cnfrmPswdTxt.sendKeys(password);
	}
	
	public void submit() {
		this.submit.click();
	}

}
