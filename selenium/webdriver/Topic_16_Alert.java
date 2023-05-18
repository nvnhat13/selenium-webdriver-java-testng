package webdriver;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_16_Alert {

	WebDriver driver;
	
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	Random random = new Random();
	WebDriverWait explicitlyWait;
	String emailAddress = "ANhat" + random.nextInt(99999) + "@gmail.com";
	String password = "123456789";
	JavascriptExecutor jsExecutor;
	
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
		// Hàm explicitlyWait dùng cho việc kiểm tra trạng thái của element: hiển thị/ko
		// hiển thị/ presence/ staleness
		explicitlyWait = new WebDriverWait(driver, 10);
		// Hàm implicitlyWait dùng cho việc tìm element (findElement/ findElements)
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		jsExecutor = (JavascriptExecutor) driver;
	}

	// Alert không inspect được vì nó là của browser
	// Có 4 loại alert: Accept Alert,  Confirm Alert,  Promt Alert, Authentication Alert
	// @Test
	public void TC_01_Accept_Alert() {
//			Alert alert = driver.switchTo().alert();
//			alert.accept(); = click Ok
//			alert.dismiss(); = click Cancel
//			alert.getText(); = Verify
//			alert.sendKeys(emailAddress); =  nhập nội dung
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
		sleepInSecond(1);
		Alert alert = explicitlyWait.until(ExpectedConditions.alertIsPresent());	
		Assert.assertEquals(alert.getText(), "I am a JS Alert");
		alert.accept();
		Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(),"You clicked an alert successfully");

	}
	 //@Test
	public void TC_02_Confirm_Alert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();

		sleepInSecond(1);
		Alert alert = explicitlyWait.until(ExpectedConditions.alertIsPresent());	
		Assert.assertEquals(alert.getText(), "I am a JS Confirm");
		alert.dismiss();	
		Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(),"You clicked: Cancel");		
	}	
	//@Test
	public void TC_03_Promt_Alert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();

		sleepInSecond(1);
		Alert alert = explicitlyWait.until(ExpectedConditions.alertIsPresent());	
		Assert.assertEquals(alert.getText(), "I am a JS prompt");
		alert.sendKeys("Mr Nhat Nguyen");	
		alert.accept();
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(),"You entered: Mr Nhat Nguyen");	
		sleepInSecond(2);
		
	}
		
	@Test
	public void TC_04_Authen_Alert() {
		// Ko dùng thư viện alert để xử lý được
		// Selenium support: http://username:password@domain
		driver.get("http://admin:admin@the-internet.herokuapp.com/basic_auth");
		Assert.assertEquals(driver.findElement(By.cssSelector("div.example p")).getText(), "Congratulations! You must have the proper credentials.");
		sleepInSecond(2);
	}
	@Test
	public void TC_05_Authen_Alert() {
		driver.get("http://the-internet.herokuapp.com");
		
		
	}

	private void sleepInSecond(long timeout) {
		try {
			Thread.sleep(timeout * 1000);
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