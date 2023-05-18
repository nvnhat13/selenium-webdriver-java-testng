package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Topic_15_Button_Checkbox_Radio {

	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	Random random = new Random();
	WebDriverWait explicitlyWait;
	String emailAddress = "ANhat" + random.nextInt(99999) + "@gmail.com";
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
		
	}

	//@Test
	public void TC_01_Button() {
		driver.get("https://www.fahasa.com/customer/account/create");
		driver.findElement(By.cssSelector("li.popup-login-tab-login")).click();
		sleepInSecond(2);
		// Verify
		By loginButton = By.cssSelector("li.popup-login-tab-login");
		// Disabled cách 1:
		Assert.assertFalse(driver.findElement(By.cssSelector("button.fhs-btn-login")).isEnabled());
		// Disabled cách 2:		
		//Assert.assertFalse(driver.findElement(loginButton).isEnabled());
		// Verify background color màu xám
		Assert.assertEquals(driver.findElement(loginButton).getCssValue("background-color"),"rgba(0, 0, 0, 0)");
		// Nhập email/password, verify enable
		driver.findElement(By.id("login_username")).sendKeys(emailAddress);
		driver.findElement(By.id("login_password")).sendKeys(password);
		Assert.assertTrue(driver.findElement(By.cssSelector("button.fhs-btn-login")).isEnabled());
		// Login button chuyển sang background màu đỏ
		Assert.assertEquals(driver.findElement(loginButton).getCssValue("background-color"),"rgba(0, 0, 0, 0)");
		
	}
	// Thẻ input của checkbox/radio hiển thị và thao tác đc thì gọi là default
	// Thẻ input của checkbox/radio ko hiển thị hoặc ko thao tác được gọi là custom
	@Test
	public void TC_02_Default_Checkbox() {
		//Checkbox
		//Chọn
		driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");
		By dualZoneCheckbox = By.cssSelector("li input#eq5");
		
		if (!driver.findElement(dualZoneCheckbox).isSelected()) {
			driver.findElement(dualZoneCheckbox).click();
	            sleepInSecond(3);         
	        }		
		Assert.assertTrue(driver.findElement(dualZoneCheckbox).isSelected());
		
		// Bỏ chọn
		if ((driver.findElement(dualZoneCheckbox).isSelected())) {
			driver.findElement(dualZoneCheckbox).click();
	            sleepInSecond(2);         
	        }	
		Assert.assertFalse(driver.findElement(dualZoneCheckbox).isSelected());

	}

	@Test
	public void TC_03_Default_Radio() {
		//Radio
		driver.get("http://demos.telerik.com/kendo-ui/styling/radios");
		By petrol147Kw = By.cssSelector("li input#engine3");	
		if (!driver.findElement(petrol147Kw).isSelected()) {
			driver.findElement(petrol147Kw).click();
	            sleepInSecond(3); 	            
	        }	
		Assert.assertTrue(driver.findElement(petrol147Kw).isSelected());

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