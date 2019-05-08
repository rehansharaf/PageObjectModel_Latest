package com.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.base.TestBase;

public class ContactPage extends TestBase {
	
	@FindBy(xpath="//div[@class='ui header item mb5 light-black']")
	WebElement contactHeading;
	
	@FindBy(xpath="//button[@class='ui linkedin button' and text()='New']")
	WebElement newContactBtn;
	
	
	public ContactPage() {
		
		PageFactory.initElements(driver, this);
	}
	
	public String verifyContactPageHeading() {
		
		return contactHeading.getText();
	}
	
	public NewContactPage verifyNewContactPage() {
		
		newContactBtn.click();
		return new NewContactPage();
	}
	
	
}
