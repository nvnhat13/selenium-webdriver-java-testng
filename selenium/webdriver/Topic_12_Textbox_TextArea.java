package webdriver;

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
// Collapse code: Ctrl+Shift+Down
// Expand code: Ctrl+Shift+Up
public class Topic_12_Textbox_TextArea {

	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	Random rand = new Random();
	WebDriverWait explicitlyWait;

	String emailAddress = "Anhat" + rand.nextInt(9999) + "@gmail.com";
	String firstName = "Nhat";
	String lastName = "Nguyen";
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
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		firstName = "ANhat";
		lastName = "Mr";

	}

	// @Test
	public void TC_01_TechPanda_Register() {
	
		
		driver.findElement(By.xpath("//div[@class='footer-container']//a[@title='My Account']")).click();
		sleepInSecond(2);
		driver.findElement(By.xpath("//div[@class='buttons-set']//a[@title='Create an Account']")).click();
		sleepInSecond(2);
		driver.findElement(By.id("firstname")).sendKeys(firstName);
		driver.findElement(By.id("lastname")).sendKeys(lastName);
		driver.findElement(By.id("email_address")).sendKeys(emailAddress);
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.id("confirmation")).sendKeys(password);

		driver.findElement(By.xpath("//button[@title='Register']")).click();
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(),
				"Thank you for registering with Main Website Store.");
		String contactInfor = driver
				.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div/p"))
				.getText();
		System.out.println(contactInfor);

		Assert.assertTrue(contactInfor.contains(fullName));
		Assert.assertTrue(contactInfor.contains(emailAddress));
		driver.findElement(By.xpath("//div[@class='account-cart-wrapper']//a[@class='skip-link skip-account']"))
				.click();
		sleepInSecond(2);
		driver.findElement(By.cssSelector("div.skip-content.skip-active a[title='Log Out']")).click();
		sleepInSecond(2);
		Assert.assertEquals(driver.getCurrentUrl(),
				"http://live.techpanda.org/index.php/customer/account/logoutSuccess/");
		// findElement() cũng là 1 cách wait rất hay, nó sẽ tìm lại cho đến khi tìm thấy
		// element
		// Mặc định của selenium là 0.5s tìm lại 1 lần. Khi nào tìm thấy nó sẽ trả về
		// kết quả là True
		// Bởi vì nó ảnh hưởng của implicitlyWait(). Nếu không có implicitlyWait() thì
		// nó sẽ bị fail
		explicitlyWait.until(ExpectedConditions.visibilityOfElementLocated((By.cssSelector("div.page-title img"))));
		Assert.assertTrue(driver.findElement(By.cssSelector("div.page-title img")).isDisplayed());

	}

	@Test
	public void TC_02_Textbox_TextArea() {
	// Orange HRM


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