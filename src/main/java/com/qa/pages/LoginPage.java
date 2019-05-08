package com.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.base.TestBase;

public class LoginPage extends TestBase {
	
	@FindBy(name="email")
	WebElement username;
	
	
	@FindBy(name="password")
	WebElement password;
	
	@FindBy(xpath="//div[@class='ui fluid large blue submit button']")
	WebElement submitBtn;
	

	public LoginPage() {
		
		PageFactory.initElements(driver, this);
	}
	
	
	public HomePage successfulLogin(String username, String password){
		
		this.username.clear();
		this.username.sendKeys(username);
		this.password.clear();
		this.password.sendKeys(password);
		submitBtn.click();
		
		return new HomePage();
		
		
	}

}
