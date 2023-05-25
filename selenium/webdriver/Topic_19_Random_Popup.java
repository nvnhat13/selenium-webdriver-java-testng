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

	//@Test
	public void TC_01_Random_Popup_Not_In_HTML_JavaCodeGeeks() {
		// Step 1: Mới mở page ra thì popup chưa có trong HTML --> dùng findElements
		driver.get("https://www.javacodegeeks.com/");
		sleepInSecond(15);
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
			driver.findElement(By.xpath("span[text()='OK'] or [text()='Get the Books']")).click();
			sleepInSecond(5);
			Assert.assertTrue(driver.findElement(secondStep).isDisplayed());
			sleepInSecond(10);
			Assert.assertFalse(driver.findElement(firstStep).isDisplayed());
			Assert.assertFalse(driver.findElement(secondStep).isDisplayed());
		}
		sleepInSecond(5);
		// Nếu nó không hiển thị thì qua step tiếp theo
		driver.findElement(By.cssSelector("input#search-input")).sendKeys("Agile Testing Explained");
		driver.findElement(By.cssSelector("form#search span.tie-icon-search")).click();
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.cssSelector("h1.page-title>span")).getText(), "Agile Testing Explained");	
	}

	//@Test
	public void TC_02_Random_Popup_In_HTML_VNK() {
		driver.get("https://vnk.edu.vn/");
		sleepInSecond(30);

		By popup = By.cssSelector("div#tve_editor");
		// Luôn có trong HTML --> dùng findElement
		if (driver.findElement(popup).isDisplayed()) {
			driver.findElement(By.cssSelector("div.tcb-icon-display")).click();
			sleepInSecond(2);
			Assert.assertFalse(driver.findElement(popup).isDisplayed());
		}
		// Next step
		driver.findElement(By.cssSelector("button.btn-danger")).click();
		sleepInSecond(2);
		Assert.assertEquals(driver.getCurrentUrl(), "https://vnk.edu.vn/lich-khai-giang/");

	
	}

	@Test
	public void TC_03_Random_Popup_Not_In_HTML_dehieu() {
		driver.get("https://dehieu.vn/");
		sleepInSecond(10);
		// Step 2: Kiểm tra popup trong 2 trường hợp
		// Có xuất hiện: Nhập email hợp lệ và click Get the Books
		// Ko xuất hiện: Chuyển step 3, element của popup vẫn còn trong DOM
		// List elements
		
		List<WebElement> firstStepElement= driver.findElements(By.cssSelector("section#popup"));
		// Nếu nó có hiển thị thì nhập dữ liệu vào và click Ok
		// Xử lý step tiếp theo đến khi nào đóng popup
		if(firstStepElement.size() > 0 && firstStepElement.get(0).isDisplayed()) {
			driver.findElement(By.cssSelector("button.close")).click();
			sleepInSecond(5);
		}
		// Nếu nó không hiển thị thì qua step tiếp theo
				driver.findElement(By.xpath("//a[text()='Tất cả khóa học']")).click();
				sleepInSecond(5);
				Assert.assertEquals(driver.getCurrentUrl(),"https://dehieu.vn/khoa-hoc");
			

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