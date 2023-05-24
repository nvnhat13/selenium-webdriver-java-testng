package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Topic_18_Fixed_Popup {

	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	Random random = new Random();
	WebDriverWait explicitlyWait;
	JavascriptExecutor jsExecutor;


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
	
	}

	//@Test
	public void TC_01_Fixed_Popup_In_DOM_ngoaingu() {
		driver.get("https://ngoaingu24h.vn/");
		// Luôn có trong HTML dù có hiển thị hoặc ko
		By loginPopup = By.cssSelector("div[id='modal-login-v1'][style]>div.modal-dialog");
		// Click button Đăng nhập
		driver.findElement(By.cssSelector("div#button-login-dialog>button.icon-before")).click();
		sleepInSecond(2);
		// Check hiển thị popup đăng nhập
		Assert.assertTrue(driver.findElement(loginPopup).isDisplayed());
		// Nhập thông tin vào username/password
		driver.findElement(By.xpath("//div[@class='modal fade in']//input[@placeholder='Tài khoản đăng nhập (*)']")).sendKeys("nhatnv124");
		driver.findElement(By.xpath("//div[@class='modal fade in']//input[@placeholder='Mật khẩu (*)']")).sendKeys("nhatnv124");
		sleepInSecond(2);
		
		// Click đăng nhập và verify message hiển thị: Tài khoản không tồn tại!
		driver.findElement(By.xpath("//div[@class='modal fade in']//button[text()='Đăng nhập']")).click();
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='modal fade in']//div[text()='Tài khoản không tồn tại!']")).getText(), "Tài khoản không tồn tại!");
		sleepInSecond(2);
		// Đóng popup
		driver.findElement(By.xpath("//div[@class='modal fade in']//button[@class='close']")).click();
		sleepInSecond(2);
		// Kiểm tra popup đăng nhập không hiển thị
		Assert.assertFalse(driver.findElement(loginPopup).isDisplayed());

		
	}

	//@Test
	public void TC_02_Fixed_Popup_In_DOM_Kyna() {
		driver.get("https://skills.kynaenglish.vn/");
		sleepInSecond(2);
		By loginPopup = By.cssSelector("div.k-popup-account-mb div.right");	
		// Click Đăng nhập btn
		driver.findElement(By.cssSelector("a.login-btn")).click();
		sleepInSecond(2);
		// Kiểm tra popup login hiển thị
		Assert.assertTrue(driver.findElement(loginPopup).isDisplayed());
		// Nhập thông tin: email:nhat@gmail.com; pwd: 123456
		driver.findElement(By.cssSelector("div.t-form-group input#user-login")).sendKeys("nhat@gmail.com");
		driver.findElement(By.cssSelector("div.t-form-group input#user-password")).sendKeys("123456");
		sleepInSecond(2);

		// Click button Đăng nhập và verify message hiển thị
		driver.findElement(By.cssSelector("div.button-submit")).click();
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.cssSelector("div#password-form-login-message")).getText(), "Sai tên đăng nhập hoặc mật khẩu");
		sleepInSecond(2);
		// Click Close button
		driver.findElement(By.cssSelector("div.modal-header button[aria-label='Close']")).click();
		sleepInSecond(2);
		// Kiểm tra popup login ko hiển thị
		Assert.assertFalse(driver.findElement(loginPopup).isDisplayed());

	}

	//@Test
	public void TC_03_Fixed_Popup_Not_In_DOM_Tiki() {
		driver.get("https://tiki.vn/");
		sleepInSecond(2);
		By loginPopup = By.cssSelector("div.styles__Root-sc-2hr4xa-0.jyAQAr");	
		// Click Đăng nhập/Đăng ký
		driver.findElement(By.cssSelector("div[data-view-id='header_header_account_container']")).click();
		sleepInSecond(2);
		// Kiểm tra popup login hiển thị
		Assert.assertTrue(driver.findElement(loginPopup).isDisplayed());
		// Click đăng nhập bằng email
		driver.findElement(By.cssSelector("p.login-with-email")).click();
		sleepInSecond(2);
		// Ko nhập thông tin, click đăng nhập
		driver.findElement(By.xpath("//button[text()='Đăng nhập']")).click();
		sleepInSecond(2);
		// Verify message hiển thị
		Assert.assertEquals(driver.findElement(By.xpath("//span[text()='Email không được để trống']")).getText(), "Email không được để trống");
		Assert.assertEquals(driver.findElement(By.xpath("//span[text()='Mật khẩu không được để trống']")).getText(), "Mật khẩu không được để trống");
		sleepInSecond(2);
		// Click Close button
		driver.findElement(By.cssSelector("button.btn-close")).click();
		sleepInSecond(2);
		// Kiểm tra popup login ko hiển thị, dùng findElements để tìm
		Assert.assertEquals(driver.findElements(loginPopup).size(),0);

	}
	
	@Test
	public void TC_04_Random_Popup_Not_In_DOM() {		
			driver.get("https://www.facebook.com/");
			sleepInSecond(2);
			By registerPopup = By.cssSelector("div#reg_box");	
			// Click New Account
			driver.findElement(By.cssSelector("div[class='_6ltg'] a[role='button']")).click();
			sleepInSecond(2);
			// Kiểm tra popup Register hiển thị
			Assert.assertTrue(driver.findElement(registerPopup).isDisplayed());	
			// Click Close button
			driver.findElement(By.cssSelector("img[class='_8idr img']")).click();
			sleepInSecond(2);
			// Kiểm tra popup register ko hiển thị, dùng findElements để tìm
			Assert.assertEquals(driver.findElements(registerPopup).size(),0);
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