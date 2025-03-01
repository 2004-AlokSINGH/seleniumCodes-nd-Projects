package steps;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

import base.WebDriverManager;
import configppt.ConfigureProperties;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class ScreenShot {
	WebDriver driver = WebDriverManager.getDriver();
	ConfigureProperties config = new ConfigureProperties();

	@Given("current page")
	public void current_page() {
	    driver.get(config.getProperty("URL"));
	}

	@Then("taking screenshot")
	public void taking_screenshot() {
	    // Write code here that turns the phrase above into concrete actions
		TakesScreenshot screenshot = ((TakesScreenshot)driver);
		File srcFile = screenshot.getScreenshotAs(OutputType.FILE);
		File destinitionFile = new File("src/test/resources/screenshot"+config.getProperty("fileName")+".png");
		try {
			FileHandler.copy(srcFile, destinitionFile);
			System.out.println("Screen Shot saved: "+destinitionFile.getAbsolutePath());
		}catch(IOException e) {
			System.out.println("Failed to take screenshot");
		}
		
	}
}
