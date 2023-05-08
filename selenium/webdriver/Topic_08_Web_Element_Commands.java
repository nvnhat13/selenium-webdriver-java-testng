package webdriver;
import java.util.concurrent.TimeUnit;

import javax.print.DocFlavor.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.logging.LogType;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import net.bytebuddy.implementation.bytecode.StackManipulation.Size;

public class Topic_08_Web_Element_Commands {
	
	// Khi viết hàm có thể biết ngay chức năng của hàm đó --> di chuột vào hàm cần kiểm tra

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
   public void TC_01_WebElement() {
	   // 1. Chỉ tương tác lên element 1 lần (ko khai báo biến)
	   driver.get("http://live.techpanda.org/index.php/customer/account/login/");
	   driver.findElement(By.id("send2")).click();
	   driver.quit();
	   // 2. Tương tác lên element nhiều lần ở trang hiện tại --> cần khai báo biến 
	   
//	   WebElement loginButton = driver.findElement(By.id("send2"));
	   
	   
   }

   @Test
   public void TC_02_Element() {
	  
	   
   }

   @Test
   public void TC_03_Tips() {
	   
	   
      
   }

   @AfterClass
   public void afterClass() {
      driver.quit();
   }

}