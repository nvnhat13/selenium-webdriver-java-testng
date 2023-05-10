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

   @Test
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

   @Test
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

   @Test
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
	   driver.findElement(By.id("email")).sendKeys("Nhat@gmail.com");
	   driver.findElement(By.id("new_password")).sendKeys("123456aA@");
	   driver.findElement(By.name("marketing_newsletter")).click();
	   sleepInSecond(2);
	   driver.findElement(By.name("marketing_newsletter")).isSelected();
	   Assert.assertTrue(driver.findElement(By.name("marketing_newsletter")).isSelected());
	   driver.findElement(By.id("create-account-enabled")).click();
	   sleepInSecond(2);
	   
	   
  
	   
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