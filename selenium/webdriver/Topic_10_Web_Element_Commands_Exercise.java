package webdriver;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_10_Web_Element_Commands_Exercise {

   WebDriver driver;
   String projectPath = System.getProperty("user.dir");
   String osName = System.getProperty("os.name");
   @BeforeClass
   public void beforeClass() {
//Windows
   if (osName.contains("Windows")){
	   System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
	   }
//MacOS
   else { 		  
		   System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver.exe");
	   }
	 
      driver = new FirefoxDriver();
      driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
      driver.manage().window().maximize();
     
   }
   // Có 3 cách để chỉ định TC ko đc chạy
   // 1. Comment dòng @Test như ở dưới. hoặc xoá @Test đi --> ưu tiên dùng
   // 2. Thêm hàm @Test(enabled, false)
   // 3. Quản lý trong file XML, chạy dưới dạng method
   // Trên IntelliJ sẽ tiện hơn, chỉ cần chạy case mình cần
//@Test
   public void TC_01_Displayed() {
	   driver.get("https://automationfc.github.io/basic-form/index.html"); 
	   
	  if( driver.findElement(By.xpath("//div[@class='container']//input[@id='mail']")).isDisplayed()) {
		  driver.findElement(By.xpath("//div[@class='container']//input[@id='mail']")).sendKeys("Automation Testing");
		  sleepInSecond(2);
		  System.out.println("Email is displayed");
	  }else {
		  System.out.println("Email is not displayed");
	  }
	   
	  if( driver.findElement(By.xpath("//div[@class='container']//label[@for='under_18']")).isDisplayed()){
		  driver.findElement(By.xpath("//div[@class='container']//label[@for='under_18']")).click();
		   System.out.println("Age under 18 is displayed");
	  }else {
		  System.out.println("Age under 18 is not displayed");
	  }
	  if(driver.findElement(By.xpath("//div[@class='container']//textarea[@id='edu']")).isDisplayed()) {
		 driver.findElement(By.xpath("//div[@class='container']//textarea[@id='edu']")).sendKeys("Automation Testing");
		 sleepInSecond(2);
		 System.out.println("Education is displayed");
	  }else {
		  System.out.println("Education is not displayed");
	  }
	  if(driver.findElement(By.xpath("//h5[text()='Name: User5']")).isDisplayed()) {
		  System.out.println("Name User5 is displayed");
	  }else {
		  System.out.println("Name User5 is not displayed");
	  }

   }
  
 
//@Test 
   public void TC_02_Enabled() {
	   driver.get("https://automationfc.github.io/basic-form/index.html"); 
	   
		  if( driver.findElement(By.xpath("//div[@class='container']//input[@id='mail']")).isEnabled()) {
			  System.out.println("Email is enabled");
		  }else {
			  System.out.println("Email is disabled");
		  }
		   
		  if( driver.findElement(By.xpath("//div[@class='container']//label[@for='under_18']")).isEnabled()){
			   System.out.println("Age under 18 is enabled");
		  }else {
			  System.out.println("Age under 18 is disabled");
		  }
		  if(driver.findElement(By.xpath("//div[@class='container']//textarea[@id='edu']")).isEnabled()) {
			 			 System.out.println("Education is enabled");
		  }else {
			  System.out.println("Education is disabled");
		  }
		  
   }

//@Test
   public void TC_03_Selected() {
	   driver.get("https://automationfc.github.io/basic-form/index.html");  
	   driver.findElement(By.id("under_18")).click();
	   driver.findElement(By.id("java")).click();
	   //dùng để kiểm tra 1 element được chọn thành công
	   Assert.assertTrue(driver.findElement(By.id("under_18")).isSelected());
	   Assert.assertTrue(driver.findElement(By.id("java")).isSelected());
	   sleepInSecond(2);
	   driver.findElement(By.id("java")).click();
	   Assert.assertFalse(driver.findElement(By.id("java")).isSelected());

	   //dùng để kiểm tra 1 element được chọn ko thành công
	   //Assert.assertFalse(driver.findElement(By.id("under_18")).isSelected());
	   // isSelected(): nếu 1 element được chọn thành công nó sẽ trả về True, còn chưa chọn thì sẽ trả về False	    
   }
   @Test
   public void TC_04_Mailchimp() {
	   driver.get("https://login.mailchimp.com/signup/");
	   driver.findElement(By.id("create-account-enabled")).click();	 
	   sleepInSecond(5);	
	   // Verify button Signup is enabled
	   Assert.assertTrue(driver.findElement(By.id("create-account-enabled")).isEnabled());	 

	   // Có 2 cách verify text error message là getText() và isDisplayed()	  	  
	   // 1.Verify getText()
	   // hàm getText() chỉ dùng đc với findElement, không dùng với findElements
	   // 2.Verify isDisplayed()
	   Assert.assertEquals(driver.findElement(By.xpath("//input[@id='email']/following-sibling::span")).getText(),"An email address must contain a single @.");
	   Assert.assertEquals(driver.findElement(By.xpath("//input[@id='new_username']/following-sibling::span")).getText(),"Please enter a value");
	   Assert.assertEquals(driver.findElement(By.xpath("//li[@class='lowercase-char not-completed']/span")).getText(),"One lowercase character");
	   Assert.assertEquals(driver.findElement(By.xpath("//li[@class='uppercase-char not-completed']/span")).getText(),"One uppercase character");
	   Assert.assertEquals(driver.findElement(By.xpath("//li[@class='number-char not-completed']/span")).getText(),"One number");
	   Assert.assertEquals(driver.findElement(By.xpath("//li[@class='special-char not-completed']/span")).getText(),"One special character");
	   Assert.assertEquals(driver.findElement(By.xpath("//li[@class='8-char not-completed']/span")).getText(),"8 characters minimum");
	   driver.findElement(By.id("email")).sendKeys("Nhat@gmail.com");
	   sleepInSecond(5);
	   // Case 1: Number
	   driver.findElement(By.id("new_password")).sendKeys("123");
	   driver.findElement(By.id("create-account-enabled")).click();	 
	   sleepInSecond(5);
	  	    
	   Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.completed")).isDisplayed());
	   Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.not-completed")).isDisplayed());
	   Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.not-completed")).isDisplayed());
	   Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.not-completed")).isDisplayed());
	   Assert.assertTrue(driver.findElement(By.cssSelector("li[class='8-char not-completed']")).isDisplayed());
	   
	// Case 2: Lowercase
	   driver.findElement(By.id("new_password")).clear();	
	   driver.findElement(By.id("new_password")).sendKeys("abc");	
	   driver.findElement(By.id("create-account-enabled")).click();	 
	   sleepInSecond(5);
	   Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.not-completed")).isDisplayed());
	   Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.completed")).isDisplayed());
	   Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.not-completed")).isDisplayed());
	   Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.not-completed")).isDisplayed());
	   Assert.assertTrue(driver.findElement(By.cssSelector("li[class='8-char not-completed']")).isDisplayed());
	// Case 3: Uppercase
	   driver.findElement(By.id("new_password")).clear();	
	   driver.findElement(By.id("new_password")).sendKeys("ABC");	
	   driver.findElement(By.id("create-account-enabled")).click();	 
	   sleepInSecond(5);
	   Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.not-completed")).isDisplayed());
	   Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.not-completed")).isDisplayed());
	   Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.completed")).isDisplayed());
	   Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.not-completed")).isDisplayed());
	   Assert.assertTrue(driver.findElement(By.cssSelector("li[class='8-char not-completed']")).isDisplayed());
	// Case 4: Special character
	   driver.findElement(By.id("new_password")).clear();	
	   driver.findElement(By.id("new_password")).sendKeys("@");	
	   driver.findElement(By.id("create-account-enabled")).click();	 
	   sleepInSecond(5);
	   Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.not-completed")).isDisplayed());
	   Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.not-completed")).isDisplayed());
	   Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.not-completed")).isDisplayed());
	   Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.completed")).isDisplayed());
	   Assert.assertTrue(driver.findElement(By.cssSelector("li[class='8-char not-completed']")).isDisplayed());
	// Case 5: 8 character
	   driver.findElement(By.id("new_password")).clear();	
	   driver.findElement(By.id("new_password")).sendKeys("12345678");	
	   driver.findElement(By.id("create-account-enabled")).click();	 
	   sleepInSecond(5);
	   Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.completed")).isDisplayed());
	   Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.not-completed")).isDisplayed());
	   Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.not-completed")).isDisplayed());
	   Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.not-completed")).isDisplayed());
	   Assert.assertTrue(driver.findElement(By.cssSelector("li[class='8-char completed']")).isDisplayed());
	// Case 6: Valid value
	   driver.findElement(By.id("new_password")).clear();	
	   driver.findElement(By.id("new_password")).sendKeys("AerereEEE!@2aB@");	
	   driver.findElement(By.id("marketing_newsletter")).click();	
	   sleepInSecond(5);
	   Assert.assertTrue(driver.findElement(By.id("marketing_newsletter")).isSelected());
	   driver.findElement(By.id("create-account-enabled")).click();	 
	   sleepInSecond(5);
	   Assert.assertFalse(driver.findElement(By.cssSelector("li.number-char.completed")).isDisplayed());
	   Assert.assertFalse(driver.findElement(By.cssSelector("li.lowercase-char.completed")).isDisplayed());
	   Assert.assertFalse(driver.findElement(By.cssSelector("li.uppercase-char.completed")).isDisplayed());
	   Assert.assertFalse(driver.findElement(By.cssSelector("li.special-char.completed")).isDisplayed());
	   Assert.assertFalse(driver.findElement(By.cssSelector("li[class='8-char completed']")).isDisplayed());
	  
   
   }
   
   private void sleepInSecond(long timeout) {
	   try {
			Thread.sleep(timeout*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
   };
   
   
   
   
   @AfterClass
   public void afterClass() {
     driver.quit();
   }

}