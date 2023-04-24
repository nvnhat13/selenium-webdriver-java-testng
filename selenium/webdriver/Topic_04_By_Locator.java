package webdriver;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_04_By_Locator {

   WebDriver driver;
   String projectPath = System.getProperty("user.dir");
   String osName = System.getProperty("os.name");
   @BeforeClass
   public void beforeClass() {
	   System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
      driver = new FirefoxDriver();
      driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
      driver.manage().window().maximize();
      driver.get("http://live.techpanda.org/index.php/customer/account/login/");
   }
   //Email Address Textbox
   //HTML Element
   //<input type="email" autocapitalize="off" autocorrect="off" spellcheck="false" 
   //name="login[username]" value="" id="email" class="input-text required-entry validate-email"
   //title="Email Address">
   // HTML
   // 1. Tên thẻ: Tagname, input
   // 2. Tên thuộc tính: attribute name, type/autocapitalize/ autocorrect/ spellcheck/ 
   // name/ value/ id/ class/ title
   // 3. Giá trị thuộc tính: attribute/ email...--> giá trị thuộc tính là duy nhất
   // Có 3 giá trị thuộc tính trùng giữa HTML và CSS là ID, Class, Name
   
   //Xpath:
   //Format: //tagname[@attribute_name='attribute_value']
   //input[@id='email'];
   
   //Css: Format: tagname[attribute_name='attribute_value']
   
//   @Test
   //Tìm xong nhập text
//   public void TC_01_ID() {
//	   driver.findElement(By.id("email")).sendKeys("Test Automation");;
//   }

   @Test
   //Chỉ tìm , không nhập text, muốn lấy toàn bộ text có dấu cách thì dùng thẻ a[class="String value"] hoặc dùng cách .text1.text2.text3
   public void TC_02_Class() {
	   driver.findElement(By.className("search-button"));

   }

   @Test
   public void TC_03_Name() {	  
   driver.findElement(By.name("login[username]"));
   }
   @Test
   // verify xem 1 page có bao nhiêu elements giống nhau
   public void TC_04_TagName() {  
	   driver.findElement(By.tagName("a"));
   }

   @Test
   // Chỉ search đc link, lấy tuyệt đối cả text
   public void TC_05_LinkText() {
	   driver.findElement(By.linkText("SEARCH TERMS"));
   }

   @Test
   // Chỉ search đc link, lấy tương đối  text ( bao gồm )
   public void TC_06_Partial_LinkText() {
	   driver.findElement(By.partialLinkText("ADVANCED"));
      
   }
   @Test
  //muốn lấy toàn bộ text có dấu cách thì dùng thẻ a[class="String value"] hoặc dùng cách .text1.text2.text3
   public void TC_07_CSS() 
	   //css by ID
	   { driver.findElement(By.cssSelector("input#email"));
   
   		//css by name
    driver.findElement(By.cssSelector("input[name='login[username]']"));
  
   		//css by class
    driver.findElement(By.cssSelector("div.new-users"));
  
   		//css by tagName
    driver.findElement(By.cssSelector("a"));
   
   		//css by linkText
    driver.findElement(By.cssSelector("a[title='Search Terms']"));
   
   		//css by partialLinkText
    driver.findElement(By.cssSelector("a[title*='Terms']"));
   }
   @Test
   public void TC_08_xPath()
   //xpath by ID
   { driver.findElement(By.xpath("//input[@id='email']"));

	//xpath by name
 driver.findElement(By.xpath("//input[@name='login[username]']"));

	//xpath by class
 driver.findElement(By.xpath("//div[@class='col-1 new-users']"));

	//xpath by tagName
 driver.findElement(By.xpath("//a"));

	//xpath by linkText
 driver.findElement(By.xpath("//a[@title='Search Terms']"));
 driver.findElement(By.xpath("//a[text()='Search Terms']"));
	//xpath by partialLinkText
 driver.findElement(By.xpath("//a[contains(@title,'Advanced')]"));
 driver.findElement(By.xpath("//a[contains(text(),'Advanced')]"));
	//xpath by CSS

}



   @AfterClass
   public void afterClass() {
      driver.quit();
   }

}