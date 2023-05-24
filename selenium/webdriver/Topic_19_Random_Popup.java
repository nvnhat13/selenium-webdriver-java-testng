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
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Topic_19_Random_Popup {

	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	Random random = new Random();
	WebDriverWait explicitlyWait;
	JavascriptExecutor jsExecutor;
	Random rand = new Random();
	String emailAddress = "Anhat" + rand.nextInt(9999) + "@gmail.com";;

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

	@Test
	public void TC_01_Random_Popup_In_DOM_Java() {
		// Step 1:
		driver.get("https://www.javacodegeeks.com/");
		// Step 2: Kiểm tra popup trong 2 trường hợp
		// Có xuất hiện: Nhập email hợp lệ và click Get the Books
		// Ko xuất hiện: Chuyển step 3, element của popup vẫn còn trong DOM
		// List elements
		By firstStep = By.cssSelector("div[data-title='Newsletter Free eBooks']:not([data-page='confirmation'])");
		By secondStep = By.cssSelector("div[data-title='Newsletter Free eBooks'][data-page='confirmation']");
		List<WebElement> firstStepElement= driver.findElements(firstStep);
		// Nếu nó có hiển thị thì nhập dữ liệu vào và click Ok
		// Xử lý step tiếp theo đến khi nào đóng popup
		if(firstStepElement.size() > 0 && firstStepElement.get(0).isDisplayed()) {
			driver.findElement(By.cssSelector("input[placeholder='Your Email']")).sendKeys(emailAddress);
			sleepInSecond(5);
			driver.findElement(By.cssSelector("a[data-label='OK']")).click();
			Assert.assertTrue(driver.findElement(secondStep).isDisplayed());
			sleepInSecond(5);
			
		}
		
		
	
		

		
	}

	//@Test
	public void TC_02_Random_Popup_In_DOM_vnk() {
		driver.get("https://vnk.edu.vn/");
		// Luôn có trong HTML dù có hiển thị hoặc ko
		// Click button Đăng nhập

		sleepInSecond(2);
		// Check hiển thị popup đăng nhập
		
		// Nhập thông tin vào username/password
		
		// Click đăng nhập và verify message hiển thị: Tài khoản không tồn tại!

		sleepInSecond(2);

		// Đóng popup
		sleepInSecond(2);
		// Kiểm tra popup đăng nhập không hiển thị
	
	}

	//@Test
	public void TC_03_Random_Popup_In_DOM_dehieu() {
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