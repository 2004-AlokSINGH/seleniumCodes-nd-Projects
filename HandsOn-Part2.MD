# 🕵️‍♂️ Selenium WebDriver - Hands-on Assignments 🚀

## 1️⃣ Selenium WebDriver Program for 📝 Form Controls & ✅ Validations

### 📌 Description:
This single ☕ Java Selenium WebDriver script automates all 🎭 interactions on the **SeleniumBase demo page**, including:
- 📝 Filling text fields
- 📂 Selecting dropdown options
- 🔘 Clicking buttons
- ☑️ Handling checkboxes & 🎯 radio buttons
- 🎚 Adjusting sliders, 📊 progress bars, and 📏 meters
- 🖼 Switching frames
- 🎯 Drag and drop operations
- 🔗 Clicking on links

### **🖥 Complete Selenium WebDriver Program**
```java
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;
import org.junit.Assert;

public class SeleniumBaseDemoTest {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://seleniumbase.io/demo_page");

        // 📝 **1. Filling Text Fields**
        WebElement textBox = driver.findElement(By.id("myTextInput"));
        textBox.sendKeys("Automated Test");
        Assert.assertEquals("Automated Test", textBox.getAttribute("value"));
        
        WebElement preFilledBox = driver.findElement(By.id("myTextInput2"));
        Assert.assertEquals("Text...", preFilledBox.getAttribute("value"));
        
        WebElement placeholderBox = driver.findElement(By.id("placeholderText"));
        Assert.assertEquals("Placeholder Text Field", placeholderBox.getAttribute("placeholder"));
        
        // 📂 **2. Selecting Dropdown Options**
        Select dropdown = new Select(driver.findElement(By.id("mySelect")));
        dropdown.selectByVisibleText("Set to 50%");
        Assert.assertEquals("Set to 50%", dropdown.getFirstSelectedOption().getText());
        
        // 🔘 **3. Clicking a Button & 🎨 Validating Color Change**
        WebElement button = driver.findElement(By.id("myButton"));
        button.click();
        WebElement updatedText = driver.findElement(By.id("pText"));
        Assert.assertEquals("This Text is Purple", updatedText.getText());
        
        // ☑️ **4. Handling Checkboxes & 🎯 Radio Buttons**
        WebElement checkbox1 = driver.findElement(By.id("checkBox1"));
        checkbox1.click();
        Assert.assertTrue(checkbox1.isSelected());
        
        WebElement radioButton = driver.findElement(By.id("radioButton1"));
        radioButton.click();
        Assert.assertTrue(radioButton.isSelected());
        

        
        // 🖼 **6. Handling Frames**
        driver.switchTo().frame("myFrame1");
        WebElement frameText = driver.findElement(By.tagName("h4"));
        Assert.assertEquals("iFrame Text", frameText.getText());
        driver.switchTo().defaultContent();
        
        // 🎯 **7. Performing Drag and Drop**
        WebElement source = driver.findElement(By.id("logo"));
        WebElement target = driver.findElement(By.id("drop1"));
        Actions action = new Actions(driver);
        action.dragAndDrop(source, target).perform();
        Assert.assertTrue(target.findElement(By.tagName("img")).isDisplayed());
        
        // 🔗 **8. Clicking on Links**
        WebElement link = driver.findElement(By.id("myLink1"));
        link.click();
        Assert.assertTrue(driver.getCurrentUrl().contains("seleniumbase.com"));
        
        // ❌ Closing the browser
        driver.quit();
    }
}
```

---

## 2️⃣ 🧐 Explanation of Features Automated in the Script
### **📝 1. Text Field Handling**
- Inputs text into 📝 text fields
- Reads 📖 pre-filled & placeholder text for validation

### **📂 2. Dropdown Selection**
- Selects an option from the 📂 dropdown and validates it

### **🔘 3. Button Click Validation**
- Clicks a 🔘 button and verifies 🎨 color change & updated text

### **☑️ 4. Checkbox & 🎯 RadioButton Handling**
- Selects ☑️ checkboxes & 🎯 radio buttons, validating their states

### **🎚 5. Slider & 📊 Progress Bar Interaction**
- Adjusts 🎚 slider and verifies 📊 progress bar update

### **🖼 6. iFrame Handling**
- Switches to an 🖼 iframe, extracts text, then switches back

### **🎯 7. Drag and Drop Functionality**
- Drags an element 🖱 and drops it into a 🎯 target

### **🔗 8. Clicking Links**
- Navigates via 🔗 links and verifies redirection

---

## 3️⃣ 🎉 Conclusion
This 🕵️‍♂️ Selenium WebDriver program efficiently automates all 📝 form controls and ✅ validations on the **SeleniumBase Demo Page**. It ensures robust 🎭 UI testing while covering diverse test scenarios. 🚀



Create  a new maven project


https://seleniumbase.io/demo_page

