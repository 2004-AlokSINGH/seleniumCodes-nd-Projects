package FrameWork_TestCase;

import java.io.File;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.DisplayOrder;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import FrameWork_PageObject.LoginPage;
import FrameWrok_EnvSetUp.EnvironmentSetup;

public class Test_MCF_PageObject extends EnvironmentSetup {

	LoginPage page;
	
	

	@BeforeClass
	public void setup() {


		setDriver("chrome", "https://www.mycontactform.com");
		driver = getDriver();
		
		page = PageFactory.initElements(driver, LoginPage.class);
		//1st it is giving value for Constructor
		//its init the webelements
		//its returning a page object
		
		driver.manage().window().maximize();
		

	}
	
	

	@Test
	public void test_MCF_PO_PF() {
      
		page.loginMCF().clickNewWizard().clickStartWizard().enterEmail();
	
		
	}
	

	@AfterClass
	public void close() {

		quitBrowser();
		 
	}

	

}
