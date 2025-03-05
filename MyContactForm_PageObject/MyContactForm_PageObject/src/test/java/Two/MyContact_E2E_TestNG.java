package Two;



import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class MyContact_E2E_TestNG {
	
	
	WebDriver driver;

	@BeforeClass
	public void setup(){
		
		 driver = setWebDriver();
		
	}
	
	@AfterClass
	public void tearDown(){
		
		driver.quit();
		
	}
	
	@Test(priority=0)
	public void logintoMCF(){
		
		driver.get("https://www.mycontactform.com/index.php");
		WebElement username_MCF = driver.findElement(By.id("user"));
		username_MCF.sendKeys("Prabhu123");

		driver.findElement(By.xpath("//*[@id='pass']")).sendKeys("12345");
		// Password_MCF.sendKeys("12345");

		driver.findElement(By.cssSelector("#right_col_top > form > div > input")).click();
		
	}
	@Test(priority=1)
	public void newWizard(){
		
		// NEW WIZARD
		driver.findElement(By.cssSelector("#user_bar > ul > li:nth-child(2) > a")).click();
	}
	@Test(priority=2)
	public void startWizard(){
		

		// START WIZARD
		driver.findElement(By.cssSelector("#center_col > form > div > input")).click();
		
	}
	@Test(priority=3)
	public void basicFromInfo(){
		
		WebElement formname_MCF = driver.findElement(By.id("formname"));
		formname_MCF.sendKeys(formNameGenerator());
		// DROPDOWN LIST

		Select dropdown = new Select(driver.findElement(By.id("format")));
		dropdown.selectByIndex(1);
		driver.findElement(By.cssSelector("#Submit")).click();
	}
	@Test(priority=4)
	public void sendToAddress(){
		

		// SENDTO ADDRESS
		driver.findElement(By.cssSelector("#Submit")).click();
		
	}
	@Test(priority=5)
	public void emailSubject_Step3(){
		
		Select emaillist = new Select(driver.findElement(By.id("eadd")));
		emaillist.selectByIndex(1);
		driver.findElement(By.cssSelector("#Submit")).click();
		// driver.findElement(By.cssSelector("#center_col > form > div.bottom >
		// input")).click();
		driver.findElement(By.cssSelector("#Submit")).click();
		// driver.findElement(By.cssSelector("#Submit")).click();
		WebElement subjectvalue_MCF = driver.findElement(By.id("subjectvalue"));
		subjectvalue_MCF.sendKeys("My Contact Details");
		driver.findElement(By.cssSelector("#Submit")).click();
		
	}
	@Test(priority=6)
	public void autoResponder_step4(){
		List<WebElement> EmailRadio = driver.findElements(By.name("autores"));
		EmailRadio.get(1).click();
		driver.findElement(By.cssSelector("#Submit")).click();
	}
	
	@Test(priority=7)
	public void formQuestion_step5(){
		
		Select Dropdown1 = new Select(driver.findElement(By.id("questionsetup")));
		Dropdown1.selectByIndex(1);
		driver.findElement(By.cssSelector("#Submit")).click();
		// CHECKBOX
		driver.findElement(By.id("cat[]")).click();
		driver.findElement(By.cssSelector("#Submit")).click();
		List<WebElement> TemplateRadio = driver.findElements(By.name("tempid"));
		TemplateRadio.get(0).click();
		driver.findElement(By.cssSelector("#Submit")).click();
		
		
		
		driver.findElement(By.cssSelector("#Submit")).click();
		driver.findElement(By.cssSelector("#Submit")).click();
		driver.findElement(By.cssSelector("#Submit")).click();
		driver.findElement(By.cssSelector("#Submit")).click();
		
	}
	@Test(priority=8)
	public void formApperance_Step8(){
		
		Select FontsizeDropdown = new Select(driver.findElement(By.id("fontsize")));
		FontsizeDropdown.selectByIndex(3);
		// RADIO BUTTON
		driver.findElement(By.id("italic")).click();
		driver.findElement(By.cssSelector("#Submit")).click();
		driver.findElement(By.cssSelector("#Submit")).click();
		Select FormlookDropdown = new Select(driver.findElement(By.id("customfields")));
		FormlookDropdown.selectByIndex(1);
		driver.findElement(By.cssSelector("#Submit")).click();
		
		
		driver.findElement(By.cssSelector("#Submit")).click();
		driver.findElement(By.cssSelector("#Submit")).click();
		
	}
	
	@Test(priority=9)
	public void formButtonsAndSite(){
		
		driver.findElement(By.id("resbut")).click();
		driver.findElement(By.cssSelector("#Submit")).click();
		Select SiteDropdown = new Select(driver.findElement(By.id("sitecat")));
		SiteDropdown.selectByIndex(0);
		driver.findElement(By.cssSelector("#Submit")).click();
		
		
	}




	public static WebDriver setWebDriver() {
		System.setProperty("webdriver.chrome.driver", "/Users/jarvis/Downloads/chromedriver");
		WebDriver driver = new ChromeDriver();
		return driver;
	}

	public static String formNameGenerator() {

		Random rd = new Random();
		int num = rd.nextInt(100);
		String formName = "MCf" + num;

		return formName;

	}

}