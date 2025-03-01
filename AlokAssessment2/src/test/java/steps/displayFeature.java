package steps;

import configppt.ConfigureProperties;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class displayFeature {
	ConfigureProperties config = new ConfigureProperties();
	@Given("ConfigFile Content")
	public void config_file_content() {
	}

	@Then("Display all the content")
	public void display_all_the_content() {
	    config.displayAllProperties();
	}

}
