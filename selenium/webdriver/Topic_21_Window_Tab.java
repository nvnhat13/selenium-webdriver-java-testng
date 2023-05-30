package webdriver;

import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Topic_21_Window_Tab {

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

	//@Test
	public void TC_01_Google() {
		//
		driver.get("https://automationfc.github.io/basic-form/index.html");
		// Lấy ID của window 1
		String gitHubId = driver.getWindowHandle();	
		System.out.println(gitHubId);
		System.out.println(driver.getCurrentUrl());
		System.out.println(driver.getTitle());
		driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
		sleepInSecond(2);	
		switchToWindowById(gitHubId);
		// Lấy ID của window 2		
		
		System.out.println(driver.getCurrentUrl());
		System.out.println(driver.getTitle());
		Assert.assertEquals(driver.getTitle(),"Google");
		// Switch về page cũ
		String googleID = driver.getWindowHandle();	
		switchToWindowById(googleID);

		driver.findElement(By.xpath("//a[text()='FACEBOOK']")).click();
		sleepInSecond(2);	
		switchToWindowByTitle("Facebook – log in or sign up");
		sleepInSecond(2);
		System.out.println(driver.getCurrentUrl());
		System.out.println(driver.getTitle());
		
		sleepInSecond(2);			
		driver.switchTo().window(gitHubId);
		sleepInSecond(2);
		driver.findElement(By.xpath("//a[text()='TIKI']")).click();
		sleepInSecond(2);
		switchToWindowByTitle("Tiki - Mua hàng online giá tốt, hàng chuẩn, ship nhanh");
		sleepInSecond(2);
		System.out.println(driver.getCurrentUrl());
		System.out.println(driver.getTitle());
		sleepInSecond(2);
		
		closeAllWindowWithoutExpectedID(gitHubId);
			}

	//@Test
		public void TC_02_KynaEnglish() {
			//
		driver.get("https://skills.kynaenglish.vn/");
		driver.findElement(By.xpath("//div[@class='hotline']//img[@alt='facebook']")).click();
		sleepInSecond(2);
		switchToWindowByTitle("Kyna.vn | Ho Chi Minh City | Facebook");
		sleepInSecond(2);
		By popup = By.cssSelector("div[role='dialog']");
		// Luôn có trong HTML --> dùng findElement
		if (driver.findElement(popup).isDisplayed()) {
			driver.findElement(By.cssSelector("div[aria-label='Close']")).click();
			sleepInSecond(2);
		}
		switchToWindowByTitle("Kyna.vn - Học online cùng chuyên gia");
		sleepInSecond(2);
	}
		@Test
		public void TC_03_techPanda() {
			//
		driver.get("http://live.techpanda.org/");
		driver.findElement(By.xpath("//div[@id='header-nav']//a[text()='Mobile']")).click();
		sleepInSecond(2);
		driver.findElement(By.xpath("//a[@title='Sony Xperia']/parent::h2/following-sibling::div[@class='actions']//a[@class='link-compare']")).click();
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(),"The product Sony Xperia has been added to comparison list.");
		
		driver.findElement(By.xpath("//a[@title='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']//a[@class='link-compare']")).click();
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(),"The product Samsung Galaxy has been added to comparison list.");
		driver.findElement(By.xpath("//span[text()='Compare']")).click();
		sleepInSecond(2);
		}
		
		
		
		
	private void switchToWindowByTitle(String facebookID) {
		// TODO Auto-generated method stub		
	}

	private void sleepInSecond(long timeout) {
		   try {
				Thread.sleep(timeout*1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	   };
	   // Chuyển đến tab ID
   public void switchToWindowById(String windowId) {
	   Set<String> allWindowIds = driver.getWindowHandles();
		for (String id : allWindowIds){
			if (!id.equals(windowId)) {
				driver.switchTo().window(id);
				break;
			}
		}
	   }
   // Đóng tất cả các tab ngoại trừ tab parent
	public void closeAllWindowWithoutExpectedID(String expectedID) {
		 Set<String> allWindowIds = driver.getWindowHandles();
		 for (String id : allWindowIds){
			 if (!id.equals(expectedID)) {
					driver.switchTo().window(id);
					driver.close();
			 }		
		 }	 
		 driver.switchTo().window(expectedID);
	}
	@AfterClass
	public void afterClass() {
		driver.quit();
	}


}