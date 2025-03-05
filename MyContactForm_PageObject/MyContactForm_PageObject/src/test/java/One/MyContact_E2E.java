package One;


import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class MyContact_E2E {

	public static void main(String[] args) {
		WebDriver driver = setWebDriver();
		logintoMCF(driver);
		// NEW WIZARD
		driver.findElement(By.cssSelector("#user_bar > ul > li:nth-child(2) > a")).click();
		// START WIZARD
		driver.findElement(By.cssSelector("#center_col > form > div > input")).click();
		// BASIC FORM INFO
		basicforminfo(driver);
		// SENDTO ADDRESS
		driver.findElement(By.cssSelector("#Submit")).click();
		// EMAIL SUJECT(STEP-3)
		emailsubj(driver);
		// AUTO RESPONDER(STEP-4)
		autoresponder(driver);
		// FORM QUESTIONS(STEP-5)
		questionsetup(driver);
		driver.findElement(By.cssSelector("#Submit")).click();
		driver.findElement(By.cssSelector("#Submit")).click();
		driver.findElement(By.cssSelector("#Submit")).click();
		driver.findElement(By.cssSelector("#Submit")).click();
		//FORM APPERANCE(STEP-8)
		apperance(driver);
		driver.findElement(By.cssSelector("#Submit")).click();
		driver.findElement(By.cssSelector("#Submit")).click();
		//FORM BUTTONS AND SITE(STEP-12,13)
		formsite(driver);
		
	}

	/**
	 * @param driver
	 */
	public static void autoresponder(WebDriver driver) {
		List<WebElement> EmailRadio = driver.findElements(By.name("autores"));
		EmailRadio.get(1).click();
		driver.findElement(By.cssSelector("#Submit")).click();
	}

	/**
	 * @param driver
	 */
	public static void formsite(WebDriver driver) {
		driver.findElement(By.id("resbut")).click();
		driver.findElement(By.cssSelector("#Submit")).click();
		Select SiteDropdown = new Select(driver.findElement(By.id("sitecat")));
		SiteDropdown.selectByIndex(0);
		driver.findElement(By.cssSelector("#Submit")).click();
	}

	/**
	 * @param driver
	 */
	public static void apperance(WebDriver driver) {
		Select FontsizeDropdown = new Select(driver.findElement(By.id("fontsize")));
		FontsizeDropdown.selectByIndex(3);
		// RADIO BUTTON
		driver.findElement(By.id("italic")).click();
		driver.findElement(By.cssSelector("#Submit")).click();
		driver.findElement(By.cssSelector("#Submit")).click();
		Select FormlookDropdown = new Select(driver.findElement(By.id("customfields")));
		FormlookDropdown.selectByIndex(1);
		driver.findElement(By.cssSelector("#Submit")).click();
	}

	/**
	 * @param driver
	 */
	public static void questionsetup(WebDriver driver) {
		Select Dropdown1 = new Select(driver.findElement(By.id("questionsetup")));
		Dropdown1.selectByIndex(1);
		driver.findElement(By.cssSelector("#Submit")).click();
		// CHECKBOX
		driver.findElement(By.id("cat[]")).click();
		driver.findElement(By.cssSelector("#Submit")).click();
		List<WebElement> TemplateRadio = driver.findElements(By.name("tempid"));
		TemplateRadio.get(0).click();
		driver.findElement(By.cssSelector("#Submit")).click();
	}

	/**
	 * @param driver
	 */
	public static void emailsubj(WebDriver driver) {
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

	/**
	 * @param driver
	 */
	public static void basicforminfo(WebDriver driver) {
		WebElement formname_MCF = driver.findElement(By.id("formname"));
		formname_MCF.sendKeys(formNameGenerator());
		// DROPDOWN LIST

		Select dropdown = new Select(driver.findElement(By.id("format")));
		dropdown.selectByIndex(1);
		driver.findElement(By.cssSelector("#Submit")).click();
	}

	/**
	 * @param driver
	 */
	public static void logintoMCF(WebDriver driver) {
		driver.get("https://www.mycontactform.com/index.php");
		WebElement username_MCF = driver.findElement(By.id("user"));
		username_MCF.sendKeys("Prabhu123");

		driver.findElement(By.xpath("//*[@id='pass']")).sendKeys("12345");
		// Password_MCF.sendKeys("12345");

		driver.findElement(By.cssSelector("#right_col_top > form > div > input")).click();
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