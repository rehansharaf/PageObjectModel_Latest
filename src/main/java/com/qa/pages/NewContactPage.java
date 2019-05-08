package com.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.qa.base.TestBase;

public class NewContactPage extends TestBase {
	
	
	@FindBy(xpath="//div[@class='ui header item mb5 light-black']")
	WebElement newContactPageHeading;
	
	@FindBy(name="first_name")
	WebElement firstName;
	
	@FindBy(name="last_name")
	WebElement lastName;
	
	@FindBy(xpath="//input[@name='do_not_text']")
	WebElement doNotText;
	
	@FindBy(name="day")
	WebElement birthDay;
	
	@FindBy(name="month")
	WebElement birthMonth;
	
	@FindBy(name="year")
	WebElement birthYear;
	
	@FindBy(xpath="//button[@class='ui linkedin button']")
	WebElement saveBtn;
	
	@FindBy(xpath="//div[@class='ui header item mb5 light-black']")
	WebElement contactName;
	
	
	public NewContactPage() {
		
		PageFactory.initElements(driver, this);
	}
	
	public boolean createNewcontact(String firstName, String lastName, String birthDay, String birthMonth, String birthYear) {
		
		JavascriptExecutor js = (JavascriptExecutor)driver;
		
		this.firstName.clear();
		this.firstName.sendKeys(firstName);
		this.lastName.clear();
		this.lastName.sendKeys(lastName);
		//this.doNotText.click();
		
		js.executeScript("arguments[0].click();", doNotText); 
		
		this.birthDay.clear();
		this.birthDay.sendKeys(birthDay);
		this.birthMonth.click();
		driver.findElement(By.xpath("//div[@role='option']/span[@class='text' and text()='"+birthMonth+"']")).click();
		this.birthYear.clear();
		this.birthYear.sendKeys(birthYear);
		saveBtn.click();
		
		try {
			
			return	wait.until(ExpectedConditions.textToBePresentInElement(contactName, firstName+""+lastName));			 
		
		}catch(Exception e) {
			
			return false;
		}
		
	}
	
	
	
	
	

}
