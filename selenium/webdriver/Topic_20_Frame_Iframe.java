package webdriver;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Topic_20_Frame_Iframe {

	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	Random random = new Random();
	WebDriverWait explicitlyWait;
	JavascriptExecutor jsExecutor;
	Random rand = new Random();
	String emailAddress = "Anhat" + rand.nextInt(9999) + "@gmail.com";;
	Select select;
	@BeforeClass
	public void beforeClass() {
////Windows
//		if (osName.contains("Windows")) {
//			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
//		}
////MacOS
//		else {
//			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver.exe");
//		}
//
//		driver = new FirefoxDriver();
		
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		}
		// MacOS
		else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/chromedriver.exe");
		}
		driver = new ChromeDriver();
		// Hàm explicitlyWait dùng cho việc kiểm tra trạng thái của element: hiển thị/ko hiển thị/ presence/ staleness
		explicitlyWait = new WebDriverWait(driver,10);
		// Hàm implicitlyWait dùng cho việc tìm element (findElement/ findElements)
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		jsExecutor = (JavascriptExecutor) driver;

	}

	@Test
	public void TC_01_Kyna() {
		// Step 1: Mới mở page ra thì popup chưa có trong HTML --> dùng findElements
		driver.get("https://skills.kynaenglish.vn/");
		sleepInSecond(2);
		WebElement facebookIframe = driver.findElement(By.cssSelector("div.fanpage iframe"));
		// verify iframe hiển thị
		Assert.assertTrue(facebookIframe.isDisplayed());
		// Cần phải switch qua frame/iframe  bằng 3 cách sau, nên dùng cách 3 nhiều hơn
		//driver.switchTo().frame(0); // cách 1
		
		//driver.switchTo().frame(""); // cách 2, bắt buộc iframe phải có thuộc tính ID hoặc NAME, nếu ko thì có thể lấy từ các node cha
		
		driver.switchTo().frame(driver.findElement(By.cssSelector("div.fanpage iframe"))); // cách 3
		sleepInSecond(2);
		
		String facebookLike =driver.findElement(By.xpath("//a[@title='Kyna.vn']/parent::div/following-sibling::div")).getText();
		System.out.println(facebookLike);
	
		// swicth về page trước đó
		driver.switchTo().defaultContent();
		sleepInSecond(2);
		driver.findElement(By.cssSelector("input#live-search-bar")).sendKeys("Excel");
		sleepInSecond(2);
		// swicth về page trước đó
		driver.switchTo().defaultContent();
		driver.switchTo().frame("cs_chat_iframe");
		driver.findElement(By.cssSelector("div.border_overlay")).click();
		sleepInSecond(2);
		driver.findElement(By.cssSelector("input.input_name")).sendKeys("MrNhat");
		driver.findElement(By.cssSelector("input.input_phone")).sendKeys("123456789");
		select = new Select(driver.findElement(By.cssSelector("select#serviceSelect")));
		select.selectByVisibleText("TƯ VẤN TUYỂN SINH");
		driver.findElement(By.cssSelector("textarea[name='message']")).sendKeys("Excel");
		sleepInSecond(2);
		driver.switchTo().defaultContent();
		driver.findElement(By.cssSelector("input#live-search-bar")).sendKeys("Excel");


		
			}

	//@Test
	public void TC_02_Random_Popup_In_HTML_VNK() {
		driver.get("https://vnk.edu.vn/");
	
	
	}

	//@Test
	public void TC_03_Random_Popup_Not_In_HTML_dehieu() {
		driver.get("https://dehieu.vn/");
		
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