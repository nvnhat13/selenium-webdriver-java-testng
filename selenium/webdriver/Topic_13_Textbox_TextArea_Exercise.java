package webdriver;

import static org.testng.Assert.assertEquals;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import net.bytebuddy.agent.builder.AgentBuilder.RedefinitionStrategy.DiscoveryStrategy.Explicit;

// Các phím tắt trong Eclipse:
// Duplicate code: bôi tất cả dòng --> Ctrl+Alt+Up
// Xoá dòng code: Ctrl+D
// Format code: Ctrl+Shift+F
// Collapse code: Ctrl+Shift+A
// Expand code: Ctrl+Shift+Q
public class Topic_13_Textbox_TextArea_Exercise {

	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	Random rand = new Random();
	WebDriverWait explicitlyWait;
	 

	String firstName, lastName, employeeID, userName, password, passportID, issueDate, expireDate, comment;
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
		firstName = "ANhat";
		lastName = "Mr";
		userName = "MrANhat" + rand.nextInt(99999) ;
		password = "AutomationFC123@";
		passportID = "123123123";
		issueDate = "2018-04-17";
		expireDate = "2028-04-16";
		comment = "comment bt";
	}

	@Test
	public void TC_01_OrangeHRM() {
	// Orange HRM
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		driver.findElement(By.name("username")).sendKeys("Admin");
		driver.findElement(By.name("password")).sendKeys("admin123");
		driver.findElement(By.cssSelector("button.orangehrm-login-button")).click();
		sleepInSecond(2);
		driver.findElement(By.xpath("//span[text()='PIM']")).click();
		sleepInSecond(2);
		driver.findElement(By.xpath("//a[text()='Add Employee']")).click();
		sleepInSecond(2);
		driver.findElement(By.name("firstName")).sendKeys(firstName);
		driver.findElement(By.name("lastName")).sendKeys(lastName);
		employeeID = driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input")).getAttribute("value");
		driver.findElement(By.xpath("//p[text()='Create Login Details']/following-sibling::div//span")).click();
		sleepInSecond(2);
		driver.findElement(By.xpath("//label[text()='Username']/parent::div/following-sibling::div/input")).sendKeys("userName");
		driver.findElement(By.xpath("//label[text()='password']/parent::div/following-sibling::div/input")).sendKeys("password");
		driver.findElement(By.xpath("//label[text()='Confirm password']/parent::div/following-sibling::div/input")).sendKeys("password");
		driver.findElement(By.xpath("//button[contains(string(),'Save')]")).click();
		sleepInSecond(8);
		Assert.assertEquals(driver.findElement(By.name("firstName")).getAttribute("value"), firstName);
		Assert.assertEquals(driver.findElement(By.name("lastName")).getAttribute("value"), lastName);
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input")).getAttribute("value"), employeeID);

		driver.findElement(By.xpath("//a[text()='Immigration']")).click();
		sleepInSecond(2);
		driver.findElement(By.xpath("//h6[text()='Assigned Immigration Records']/following-sibling::button")).click();
		sleepInSecond(2);
		
		
		
	}

	@Test
	public void TC_03_() {

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