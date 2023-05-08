package webdriver;
import java.util.concurrent.TimeUnit;

import javax.print.DocFlavor.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.logging.LogType;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import net.bytebuddy.implementation.bytecode.StackManipulation.Size;

public class Topic_07_WebBrowser_Commands {
	
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
      driver.quit(); //Không đóng tab mà đóng luôn browser     
   }
 
   @Test
   public void TC_01_Browser() {
	   //Các command(hàm) để tương tác với browser thì nó thông báo qua biến driver
	   //driver. --> có đuôi là WebDriver thì sẽ dùng đc
	   //Dùng để đóng tab or trình duyệt(nếu chỉ có 1 tab)
	   driver.close();
	   //Không đóng tab mà đóng luôn browser  
	   driver.quit();  
	   // Tìm 1 element với 1 locator nào đó
	   driver.findElement(By.id("")); 
	  // Tìm nhiều element với 1 locator nào đó
	   driver.findElements(By.xpath("//a[@href")); 
	   // Mở ra 1 page url nào đó (string)
	   driver.get("https://www.youtube.com/watch?v=XSb29GyR-Vc&ab_channel=AutomationFC");
	   // Đang đứng ở page nào thì lấy URL của page đó
	   driver.getCurrentUrl();
	   // Cách 1: Dùng cho 1 step
	   Assert.assertEquals(driver.getCurrentUrl(), "https://www.youtube.com/watch?v=XSb29GyR-Vc&ab_channel=AutomationFC");
	   // Cách 2: Dùng cho nhiều hơn 1 step thì mới khai báo biến
	   // Code rườm rà, tốn bộ nhớ
	   String homeUrl = driver.getCurrentUrl();
	   Assert.assertEquals(driver.getCurrentUrl(), "https://www.youtube.com/watch?v=XSb29GyR-Vc&ab_channel=AutomationFC");
	   // Lấy ra code HTML/ CSS/ JS của page hiện tại để verify
	   driver.getPageSource(); //--> ít dùng
	   
	   // Lấy title của Page hiện tại
	   Assert.assertEquals(driver.getTitle(),"Mobile");
	   // Dùng cho Window/ tab
	   driver.getWindowHandle(); // --> lấy ID của window hiện tại	 
	   driver.getWindowHandles(); // --> lấy ID của tất cả window/tab 
	   //Cookies
	   driver.manage().getCookies();
	   //Log của browser
	   driver.manage().logs().get(LogType.BROWSER);
	   // Để chờ cho element xuất hiện trong bao lâu
	   // Dùng ở 2 hàm: findElement và findElements
	   driver.manage().timeouts().implicitlyWait(1, TimeUnit.DAYS);
	   driver.manage().timeouts().implicitlyWait(1, TimeUnit.MINUTES);
	   driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
	   driver.manage().timeouts().implicitlyWait(1, TimeUnit.HOURS);
	   driver.manage().timeouts().implicitlyWait(1, TimeUnit.MILLISECONDS);
	  // CHờ cho page đc load xong trong vòng bao lâu 
	   driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.MINUTES);
	  // CHờ cho 1 đoạn script được thực thi xong trong vòng bao lâu 
	   // JavascriptExecutor
	   driver.manage().timeouts().setScriptTimeout(15, TimeUnit.SECONDS);
	   
	  // Windows
	   driver.manage().window().fullscreen(); // = với việc nhấn F11
	   
	   driver.manage().window().maximize(); // bằng với việc nhấn icon Maximaize
	   
	   // Vị trí trình duyệt khi mở ra so với độ phân giải màn hình, tính từ điểm 0,0 (góc trên bên phải)
	   Point point= driver.manage().window().getPosition();
	   point.getX();
	   point.getY();
	   // Cài đặt vị trí khi mở trình duyệt, test GUI
	   driver.manage().window().setPosition(new Point(1920, 1080));
	   // Size: là kích thước của trình duyệt (chiều rộng, cao) khi mở ra, dùng để test responsive
	   Dimension dimension = driver.manage().window().getSize();
	   dimension.getWidth();
	   dimension.getHeight();
	   driver.manage().window().setSize(new Dimension(1900,1200));
	   // Navigation --> chuyển hướng, tương đương action trên 3 button ở browser
	   driver.navigate().back();
	   driver.navigate().forward();
	   driver.navigate().refresh();
	   // Chuyển đến trang được chỉ định
	   driver.navigate().to("https://facebook.com");
	   
	   // Swicth to: window/tab, frame/iframe, alert
	   
	   driver.switchTo().alert();
	   driver.switchTo().frame(1);
	   driver.switchTo().window("");

	   
	

	   
   }

   @Test
   public void TC_02_Element() {
	   //Các command(hàm) để tương tác với element thì nó thông báo qua việc findElement
	   //driver.findElement(By.xpath("")); --> vừa là kiểu webDriver, vừa là kiểu findElement
	   
	   
   }

   @Test
   public void TC_03_Tips() {
	   // Chia ra gồm 3 nhóm chính
	   // Nhóm 1: Các hàm để tương tác(action): click, sendKeys, select.... 
	   //--> tên hàm sẽ thể hiện rõ chức năng của hàm đó mà ko trả về dữ liệu gì hết (ko có return)
	   driver.findElement(By.xpath("")).click();
	   
	   // Nhóm 2: Lấy dữ liệu cho 1 mục đích nào đó 
	   // --> trả về dữ liệu, đã có kiểu dữ liệu trong suggestion, hầu như là String
	   // Sẽ bắt đầu bằng tiền tố là getXXX (getText, getTitlte, getAttribute....)
	   // Dùng để kiểm tra tính đúng đắn của dữ liệu (validate/true/false/equals....) --> đi kèm với hàm Assert(có thư viện riêng trong java)
	   // Assert: JUnit/TestNG/AssertJ....

	   
	   // Nhóm 3: Kiểm tra dữ liệu
	   // Dùng để kiểm tra tính đúng đắn của dữ liệu (true/false/equals) --> hàm assert
	   // Bắt đầu bằng tiền tố "is": isDisplayed/ isEnable/ isMultiple/... 
	   // --> trả về dữ liệu (hầu hết là boolean)
	   
	   
      
   }

   @AfterClass
   public void afterClass() {
      driver.quit();
   }

}