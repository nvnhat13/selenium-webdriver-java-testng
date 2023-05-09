package webdriver;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_09_WebBrowser_Element_Exercise {

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
   public void TC_01_VerifyURL() throws InterruptedException {
	  
	   driver.get("http://live.techpanda.org"); 
	   driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
	  Thread.sleep(3000);
	   Assert.assertEquals(driver.getCurrentUrl(), "http://live.techpanda.org/index.php/customer/account/login/");
	   driver.findElement(By.xpath("//div[@class='buttons-set']//a[@title='Create an Account']")).click();
	   Thread.sleep(3000);
	   Assert.assertEquals(driver.getCurrentUrl(), "http://live.techpanda.org/index.php/customer/account/create/");

   }

   @Test
   public void TC_02_Title() {
   }

   @Test
   public void TC_03_Navigation() {
      
   }
   
   @Test
   public void TC_04_Page_Source() {
      
   }
   @AfterClass
   public void afterClass() {
      driver.quit();
   }

}