package FrameWrok_EnvSetUp;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;




public class EnvironmentSetup extends Pathutility {
	
	


	public WebDriver driver;

	protected void setDriver(String browserName, String URL) {
		switch (browserName) {
		case "chrome":
			System.out.println("Chrome Opened ");
//			System.setProperty("webdriver.chrome.driver", Pathutility.DRIVER_PATH);
//			System.out.println(" PAth Done ");
			driver = new ChromeDriver();
			System.out.println(" Launching the URL ");
			launchURL(URL);
			break;
		case "iexplorer":
			System.setProperty("webdriver.iexplorer.driver", Pathutility.DRIVER_PATH + "IEDriverServer.exe");
			driver = new InternetExplorerDriver();
			launchURL(URL);
			break;
		case "firefox":
			driver = new FirefoxDriver();
			launchURL(URL);
			break;
		default:
			System.out.println("Invalid Browser Name");
		}
	}

	private void launchURL(String applicationURL) {
		driver.get(applicationURL);
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	public WebDriver getDriver() {
		return driver;
	}
	
	public void quitBrowser(){
		driver.quit();
	}
	
	
	public String getMethodName(){
		
		 String threadNAme = Thread.currentThread().getStackTrace()[3].getMethodName();
		return threadNAme;
		
        
	}
}
