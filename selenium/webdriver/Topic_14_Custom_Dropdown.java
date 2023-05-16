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


public class Topic_14_Custom_Dropdown {

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
		driver.get("https://www.facebook.com/reg/");
		// JS code
		// UnexpectedTagNameException - when element is not selected
		// Thẻ div, span, ul, li...
		// Dev tùy biến từ framework họ sử dụng : JQuery/Boostrap/Angular/ReactJS/VueJS...
		
		
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


}