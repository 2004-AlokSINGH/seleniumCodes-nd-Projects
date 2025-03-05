package FrameWork_PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class StartWizard {
	
	
	WebDriver driver;

	@FindBy(css = "#center_col > form > div > input")
	WebElement startWizard;

	public EmailPage clickStartWizard() {
		
		startWizard.click();
		return PageFactory.initElements(driver, EmailPage.class);
		
		

	}
	
	

}
