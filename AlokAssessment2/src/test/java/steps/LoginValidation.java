package steps;

import org.openqa.selenium.WebDriver;
import base.WebDriverManager;
import configppt.ConfigureProperties;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import page.LoginPage;

public class LoginValidation {
	WebDriver driver = WebDriverManager.getDriver();
	ConfigureProperties config = new ConfigureProperties();
	LoginPage loginPage = new LoginPage();
	
	
	
	
	@Given("go to the webpage")
	public void go_to_the_webpage() {
	    driver.get(config.getProperty("URL"));	    
	}

	
	
	
	@Then("I enter the username and password")
	public void i_enter_the_username_and_password() {		
		loginPage.entercredentials();
		

	}

	@Then("I click login button")
	public void i_click_login_button() {
		loginPage.clickLogin();
	}


}
