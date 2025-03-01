package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import page.LoginPage;



public class UrlValidate {

	
	LoginPage loginPage = new LoginPage();
	
	
	@Given("store urls from page")
	public void store_urls_from_page() {
		loginPage.getAlllink();
		
	}

	@Then("validate the status code")
	public void validate_the_status_code() {
		loginPage.validateUrls();
	}

}
