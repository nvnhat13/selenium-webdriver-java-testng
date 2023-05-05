package webdriver;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_06_Run_Browser {

   WebDriver driver;
   String projectPath = System.getProperty("user.dir");
   String osName = System.getProperty("os.name");
   @BeforeClass
   public void beforeClass() {
	  if (osName.contains("Windows")){
	   System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
	   }else {
		   System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver.exe");
	   }

      driver = new FirefoxDriver();
      driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
      driver.manage().window().maximize();
    
   }

   @Test
   public void TC_01_Firefox() { 
	   if (osName.contains("Windows")){
	   System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
	   }else {
		   System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver.exe");
	   }
	   driver= new FirefoxDriver();
	   driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	   
	   driver.get("https://tiki.vn");
   }

   @Test
   public void TC_02_Chrome() {
	   if (osName.contains("Windows")){
		   System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		   }else {
			   System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver.exe");
		   }
		   driver= new ChromeDriver();
		   driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		   
		   driver.get("https://tiki.vn");
   }

   @Test
   public void TC_03_Edge() {
	   if (osName.contains("Windows")){
		   System.setProperty("webdriver.edge.driver", projectPath + "\\browserDrivers\\edgedriver.exe");
		   }else {
			   System.setProperty("webdriver.edge.driver", projectPath + "/browserDrivers/edgedriver.exe");
		   }
		   driver= new EdgeDriver();
		   driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		   
		   driver.get("https://tiki.vn");
   }

   @AfterClass
   public void afterClass() {
      driver.quit();
   }

}