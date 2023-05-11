package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

// Các phím tắt trong Eclipse:
// Duplicate code: bôi tất cả dòng --> Ctrl+Alt+Up
// Xoá dòng code: Ctrl+D
// Format code: Ctrl+Shift+F
// Collapse code: Ctrl+Shift+Down
// Expand code: Ctrl+Shift+Up
public class Topic_12_Textbox_TextArea {

	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

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
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	@Test
	public void TC_01_TechPanda_Register() {
		
		driver.get("http://live.techpanda.org/");
		sleepInSecond(2);
	    driver.findElement(By.xpath("//div[@class='footer-container']//a[@title='My Account']")).click();
	    sleepInSecond(2);
	    driver.findElement(By.xpath("//div[@class='buttons-set']//a[@title='Create an Account']")).click();
	    sleepInSecond(2);
	    driver.findElement(By.xpath("//div[@class='input-box']//input[@id='firstname']")).sendKeys("Nguyen");
	    driver.findElement(By.xpath("//div[@class='input-box']//input[@id='lastname']")).sendKeys("Nhat");
	    driver.findElement(By.xpath("//div[@class='input-box']//input[@id='email_address']")).sendKeys("Nhat@gmail.com");
	    driver.findElement(By.xpath("//div[@class='input-box']//input[@id='password']")).sendKeys("123aB@");
	    driver.findElement(By.xpath("//div[@class='input-box']//input[@id='confirmation']")).sendKeys("123aB@");
	    sleepInSecond(2);
	    driver.findElement(By.xpath("//div[@class='buttons-set']//button[@title='Register']")).click();
	    sleepInSecond(3);
	    Assert.assertTrue(driver.findElement(By.cssSelector("li[class='success-msg']")).isDisplayed());

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
//	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	// Ý nghĩa của hàm Assert.assertTrue và Assert.assertFalse
//   int a = 5;
//   int b = 7;
//   Assert.assertTrue(a < b); // Test sẽ thành công vì 5 < 7
//   Assert.assertFalse(a > b); // Test sẽ thành công vì 5 không lớn hơn 7

}