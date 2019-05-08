package com.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.base.TestBase;

public class HomePage extends TestBase {
	
	@FindBy(xpath="//span[@class='user-display']")
	WebElement loggedInUserName;
	
	@FindBy(xpath="//span[@class='item-text' and text()='Contacts']")
	WebElement contacts;
	
	
	public HomePage() {
		
		PageFactory.initElements(driver, this);
	}
	
	
	public String verifyLoggedInName() {
		
		return loggedInUserName.getText();
	}
	
	public ContactPage openContactPage() {
		
		contacts.click();
		return new ContactPage();
	}
	
	

}
