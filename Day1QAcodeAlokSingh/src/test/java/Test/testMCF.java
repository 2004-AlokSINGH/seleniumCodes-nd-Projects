

package Test;

import org.openqa.selenium.support.PageFactory;
import org.testng.ITestNGListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.Assert;

import Base.BaseClass;
import page.StimulatePage;



@Listeners(listeners.NGListener.class)
public class testMCF  extends BaseClass implements ITestNGListener {
	
	
	@Test
	public void test() {
	 
	 StimulatePage page= PageFactory.initElements(driver, StimulatePage.class);
	 page.login("demo_user", "secret_pass");
	 
	 
	 //Adding Assert keyword
	 
	 
	 //Checking for positive test case
	 String hometxt=page.getHometxt();
	 Assert.assertEquals(hometxt, "Welcome!");
	 
	 try {
		Thread.sleep(3000);
	} catch (InterruptedException e) {
		e.printStackTrace();
	}
	 
	 
	 
	 
	 
	 
		
	 
	}
	
	
	
	
	

}
