package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

// Các phím tắt trong Eclipse:
// Duplicate code: bôi tất cả dòng --> Ctrl+Alt+Up
// Xoá dòng code: Ctrl+D
// Format code: Ctrl+Shift+F
// Collapse code: Ctrl+Shift+Down
// Expand code: Ctrl+Shift+Up
public class Topic_00_Template {

	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	Random random = new Random();
	WebDriverWait explicitlyWait;
	
	String emailAddress = "ANhat" + random.nextInt(99999) + "@gmail.com";
	String firstName = "Nguyen";
	String lastName = "Nhat";
	String fullName = firstName + " " + lastName;
	String password = "123456789";
	@BeforeClass
	public void beforeClass() {
//Windows
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		}
//MacOS
		else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver.exe");
		}

		driver = new FirefoxDriver();
		// Hàm explicitlyWait dùng cho việc kiểm tra trạng thái của element: hiển thị/ko hiển thị/ presence/ staleness
		explicitlyWait = new WebDriverWait(driver,10);
		// Hàm implicitlyWait dùng cho việc tìm element (findElement/ findElements)
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://live.techpanda.org/index.php/customer/account/login/");
	}

	@Test
	public void TC_01_() {
		String benefitText = driver.findElement(By.cssSelector("ul.benefits")).getText();
		System.out.println(benefitText);
	}

	@Test
	public void TC_02_() {
	}

	@Test
	public void TC_03_() {

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
	// Ý nghĩa của hàm Assert.assertTrue và Assert.assertFalse
//   int a = 5;
//   int b = 7;
//   Assert.assertTrue(a < b); // Test sẽ thành công vì 5 < 7
//   Assert.assertFalse(a > b); // Test sẽ thành công vì 5 không lớn hơn 7

}