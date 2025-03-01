package page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class StimulatePage {

	@FindBy(id = "username")
	WebElement username;
	
	
	@FindBy(xpath = "//*[@id=\'password\']")
	WebElement password;
	
	
	@FindBy(id = "log-in")
	WebElement loginButton;
	
	@FindBy(xpath="/html/body/h1")
	WebElement hometxt;
	
	
	
	public void login(String user, String pass) {
		username.sendKeys(user);
		password.sendKeys(pass);
		loginButton.click();
	}



	public String getHometxt() {
		return hometxt.getText();
			
		
	}
	
	

}