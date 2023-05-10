package webdriver;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_11_Login {

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
   public void TC_01_Login_Empty_Email_And_Password() {
	    driver.get("http://live.techpanda.org/");
	    driver.findElement(By.xpath("//div[@class='footer-container']//a[@title='My Account']")).click();
	    sleepInSecond(2);
	    driver.findElement(By.id("send2")).click();
	    sleepInSecond(2);
	    Assert.assertEquals(driver.findElement(By.id("advice-required-entry-email")).getText(),"This is a required field.");
	    Assert.assertEquals(driver.findElement(By.id("advice-required-entry-pass")).getText(),"This is a required field.");
	  
   }

   @Test
   public void TC_02_Invalid_Email() {
	   driver.get("http://live.techpanda.org/");
	    driver.findElement(By.xpath("//div[@class='footer-container']//a[@title='My Account']")).click();
	    sleepInSecond(2);
	    driver.findElement(By.id("email")).sendKeys("123@123");
	    driver.findElement(By.name("login[password]")).sendKeys("123456");
	    sleepInSecond(2);
	    driver.findElement(By.id("send2")).click();
	    sleepInSecond(2);
	    Assert.assertEquals(driver.findElement(By.cssSelector("div.validation-advice")).getText(),"Please enter a valid email address. For example johndoe@domain.com.");
	  
   }

   @Test
   public void TC_03_Password_5Characters() {
	   driver.get("http://live.techpanda.org/");
	    driver.findElement(By.xpath("//div[@class='footer-container']//a[@title='My Account']")).click();
	    sleepInSecond(2);
	    driver.findElement(By.id("email")).sendKeys("123@gmail.com");
	    driver.findElement(By.name("login[password]")).sendKeys("12345");
	    sleepInSecond(2);
	    driver.findElement(By.id("send2")).click();
	    sleepInSecond(2);
	    Assert.assertEquals(driver.findElement(By.cssSelector("div.validation-advice")).getText(),"Please enter 6 or more characters without leading or trailing spaces.");
	      
   }
   @Test
   public void TC_04_Incorrect_Email_Password() {
	   driver.get("http://live.techpanda.org/");
	    driver.findElement(By.xpath("//div[@class='footer-container']//a[@title='My Account']")).click();
	    sleepInSecond(2);
	    driver.findElement(By.id("email")).sendKeys("1235@gmail.com");
	    driver.findElement(By.name("login[password]")).sendKeys("123456");
	    sleepInSecond(2);
	    driver.findElement(By.id("send2")).click();
	    sleepInSecond(2);
	    Assert.assertEquals(driver.findElement(By.cssSelector("li.error-msg")).getText(),"Invalid login or password.");
	      
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
   // Ý nghĩa của hàm Assert.assertTrue và Assert.assertFalse
//   int a = 5;
//   int b = 7;
//   Assert.assertTrue(a < b); // Test sẽ thành công vì 5 < 7
//   Assert.assertFalse(a > b); // Test sẽ thành công vì 5 không lớn hơn 7

}