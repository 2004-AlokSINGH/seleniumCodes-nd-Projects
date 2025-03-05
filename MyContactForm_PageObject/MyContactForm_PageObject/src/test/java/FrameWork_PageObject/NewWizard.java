package FrameWork_PageObject;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NewWizard {

	WebDriver driver;

	
	
public NewWizard(WebDriver driver) {
		this.driver=driver;
	}

	@FindBy(css = "#user_bar > ul > li:nth-child(2) > a")
	WebElement newWizard;

	public StartWizard clickNewWizard() {
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		newWizard.click();
		return PageFactory.initElements(driver,StartWizard.class);

	}
	
	
}
