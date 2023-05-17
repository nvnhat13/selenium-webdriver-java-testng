package webdriver;
import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.print.DocFlavor.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Window;
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
	   driver.findElement(By.id("")).click();
	   driver.quit();
	   
	   
	   
	   // 2. Tương tác lên element nhiều lần ở trang hiện tại --> cần khai báo biến 
	   // Biến web element của hàm findElement chỉ được dùng ngay lần đầu
	   WebElement loginButton = driver.findElement(By.id(""));
	   // Nên define thành biến By --> dùng được ở bất kỳ page nào, vì nó chỉ là 1 biến bất kỳ
	   // By loginButtonBy = By.id("");
	   loginButton.isDisplayed();
	   loginButton.getCssValue("");
	   loginButton.click();
	   List<WebElement> textboxes = driver.findElements(By.xpath("//input[@type='text']"));

	   
	   //Tìm 1 element 
	   // Xoá dữ liệu trong 1 textbox, textarea... (dạng editable)
	   driver.findElement(By.id("")).clear();
	   
	   // Nhập dữ liệu trong 1 textbox, textarea... 
	   driver.findElement(By.id("")).sendKeys("");
	   
	   // Tối ưu cách tìm element
	   //Thay vì:
	   driver.findElement(By.xpath("//div[@class='footer']")).findElement(By.xpath("//a[text()='My Account']"));
	   //Thì ta sẽ: 
	   driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']"));
	   
	   
	   // Search entire store here... (Placeholder)
	   driver.findElement(By.id("search")).getAttribute("placeholder");
	   
	   // Search "bile"
	   driver.findElement(By.id("search")).getAttribute("value");
	   
	   
	   // Test GUI
	   driver.findElement(By.id("search")).getCssValue("background-color");
	   driver.findElement(By.id("search")).getCssValue("font-size");
	   driver.findElement(By.id("search")).getCssValue("font-family");
	   
	   ((Window) driver.findElement(By.id("search"))).getPosition();
	   //Kích thước element
	   Dimension loginButtonSize = driver.findElement(By.id("search")).getSize();
	   //Lấy ra toạ độ bên ngoài của element so với độ phân giải màn hình
	   Point loginButtonLocation = driver.findElement(By.id("search")).getLocation();
	   //Lấy ra cả kích thước và vị trí
	   Rectangle loginButtonRect = driver.findElement(By.id("search")).getRect();
	   loginButtonSize = loginButtonRect.getDimension();
	   loginButtonLocation =loginButtonRect.getPoint() ;
	   
	   // Report HTML + Chụp ảnh màn hình
	   File screenshotFile= driver.findElement(By.id("search")).getScreenshotAs(OutputType.FILE);
	   String screenshotBase64 = driver.findElement(By.id("search")).getScreenshotAs(OutputType.BASE64); // ảnh có định dạng chữ
	   
	   // Lấy ra tên thẻ khi dùng các loại locator mà ko biết trước tên thẻ là gì
	   String searchTextboxTagName = driver.findElement(By.cssSelector("#search")).getTagName(); // --> thẻ input	   	 
	   driver.findElement(By.xpath("//"+ searchTextboxTagName + "[@id='email']"));
	   
	   
	   // Get text
	   driver.findElement(By.id("search")).getText();
	   // Lấy ra text của chính nó và cả các thẻ con của nó
	   String benefitText = driver.findElement(By.cssSelector("ul.benefits")).getText();
	   
	   // Áp dụng cho tất cả các element
	   // 1 element có hiển thị trên màn hình hay không
	   // Nhìn thấy được/ có kích thước cụ thể
	   driver.findElement(By.cssSelector("ul.benefits")).isDisplayed();
	   
	   // Áp dụng cho tất cả các element
	   // Check xem 1 element có thể thao tác được hay ko (ko bị disable) =  read only
	   driver.findElement(By.cssSelector("ul.benefits")).isEnabled();
	   
	   // Áp dụng cho 3 loại element: checkbox, radio, dropdown. Thường dùng cho checkbox và radio
	   // Dropdown đã có thư viện riêng là Select 
	   // Xem element đã được chọn hay chưa?
	   driver.findElement(By.cssSelector("ul.benefits")).isSelected();
	   
	   
	   // Chỉ Áp dụng cho form. Thay thế cho hành vi click vào button Login/Register/Submit/Search....
	   driver.findElement(By.cssSelector("ul.benefits")).submit();
	   
	   
	   
	   
	   
	   // Tìm nhiều elements:
	   driver.findElements(By.xpath("//input[@type='text']"));
	   

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