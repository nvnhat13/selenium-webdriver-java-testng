package webdriver;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
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
      driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
      driver.manage().window().maximize();
      driver.get("https://automationfc.github.io/basic-form/index.html"); 
   }

   @Test
   public void TC_01_Displayed() {
	    
   }

   @Test
   public void TC_02_Enabled() {
   }

   @Test
   public void TC_03_Selected() {
      
   }
   @Test
   public void TC_04_Mailchimp() {
      
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