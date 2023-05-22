
package webdriver;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_17_User_Interaction {

	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	Random random = new Random();
	WebDriverWait explicitlyWait;
	Actions action;

	@BeforeClass
	public void beforeClass() {
		// Windows
//		if (osName.contains("Windows")) {
//			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
//		}
//		// MacOS
//		else {
//			System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/geckodriver.exe");
//		}
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		}
		// MacOS
		else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/chromedriver.exe");
		}

		driver = new ChromeDriver();
		explicitlyWait = new WebDriverWait(driver, 10);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		action = new Actions(driver);
	}

	//@Test
	public void TC_01_tooltip() {
		// Bật debug hoặc dùng lệnh setTimeout(() =>{debugger;},3000); --> chạy debug
		// sau 3s
		// Khi chạy thì ko được dùng chuột
		driver.get("https://automationfc.github.io/jquery-tooltip/");
		action.moveToElement(driver.findElement(By.cssSelector("input#age"))).perform();
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.cssSelector("div.ui-tooltip-content")).getText(),
				"We ask for your age only for statistical purposes.");
		
	}

	//@Test
	public void TC_02_tooltip() {
		// Bật debug hoặc dùng lệnh setTimeout(() =>{debugger;},3000); --> chạy debug
		// sau 3s
		// Khi chạy thì ko được dùng chuột
		driver.get("http://www.healthkart.com/");

		action.moveToElement(driver.findElement(By.cssSelector("div.container-item.support"))).perform();
		sleepInSecond(1);
		action.click(driver.findElement(By.xpath("//div[@class='dropdown-container']//a[4]"))).perform();
		sleepInSecond(1);
		Assert.assertEquals(
				driver.findElement(By.xpath("//div[contains(@class,'hk-breadcrumb-cntnr')]//span[3]")).getText(),
				"Terms and Conditions");

	}

	//@Test
	public void TC_03_Click_And_hold() {
		// Bật debug hoặc dùng lệnh setTimeout(() =>{debugger;},3000); --> chạy debug
		// sau 3s
		// Khi chạy thì ko được dùng chuột
		driver.get("https://automationfc.github.io/jquery-selectable/");
		// Cách 1: thủ công
		action.clickAndHold(driver.findElement(By.xpath("//li[text()='1']"))) // click vào text 1
				.moveToElement(driver.findElement(By.xpath("//li[text()='4']"))) // move đến text 4
				.release() // nhả chuột ra
				.perform(); // thực thi các hành động trên
		sleepInSecond(2);
		// Verify text 1--> 4 đã được chọn
		Assert.assertTrue(
				driver.findElement(By.xpath("//li[text()='1' and contains(@class,'ui-selected')]")).isDisplayed());
		Assert.assertTrue(
				driver.findElement(By.xpath("//li[text()='2' and contains(@class,'ui-selected')]")).isDisplayed());
		Assert.assertTrue(
				driver.findElement(By.xpath("//li[text()='3' and contains(@class,'ui-selected')]")).isDisplayed());
		Assert.assertTrue(
				driver.findElement(By.xpath("//li[text()='4' and contains(@class,'ui-selected')]")).isDisplayed());
		// Cách 2: Chọn cả list lưu rồi lôi số cần thao tác với số mình muốn
		List<WebElement> allNumbers = driver.findElements(By.cssSelector("ol#selectable li"));
		action.clickAndHold(allNumbers.get(0)).moveToElement(allNumbers.get(3)).release().perform();
		sleepInSecond(2);
		List<WebElement> selectedNumber = driver.findElements(By.cssSelector("ol#selectable li.ui-selected"));
		Assert.assertEquals(selectedNumber.size(), 4);
	}

	//@Test
	public void TC_04_Click_And_hold_random() {
		// Bật debug hoặc dùng lệnh setTimeout(() =>{debugger;},3000); --> chạy debug
		// sau 3s
		// Khi chạy thì ko được dùng chuột
		driver.get("https://automationfc.github.io/jquery-selectable/");
		List<WebElement> allNumbers = driver.findElements(By.cssSelector("ol#selectable li"));
		// Nhấn phím control xuống
		action.keyDown(Keys.CONTROL).perform(); // Control--> chỉ chạy được trên window
		// Click từng số
		action.click(allNumbers.get(0)).click(allNumbers.get(2)).click(allNumbers.get(5)).click(allNumbers.get(11))
				.perform();
		sleepInSecond(2);

	}

	//@Test
	public void TC_05_Double_Click_() {
		// Bật debug hoặc dùng lệnh setTimeout(() =>{debugger;},3000); --> chạy debug
		// sau 3s
		// Khi chạy thì ko được dùng chuột
		// Với Firefox thì nếu element ko nằm trong view port thì sẽ ko click đc --> cần scroll đến để view
		// Lỗi này ko bị trên Chromium browsers
		driver.get("https://automationfc.github.io/basic-form/index.html");
		action.doubleClick(driver.findElement(By.xpath("//button[text()='Double click me']"))).perform();
		sleepInSecond(2);
		Assert.assertEquals((driver.findElement(By.cssSelector("div.container p#demo")).getText()),
				"Hello Automation Guys!");
		sleepInSecond(2);
	}
@Test
	public void TC_05_Right_Click_() {
		
		driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
		// right click vào button
		action.contextClick(driver.findElement(By.cssSelector("span.context-menu-one"))).perform();
		sleepInSecond(2);
		Assert.assertEquals((driver.findElement(By.cssSelector("li.context-menu-icon-quit span")).getText()),
				"Quit");
		action.click(driver.findElement(By.cssSelector("li.context-menu-icon-quit span"))).perform();
		sleepInSecond(2);
		driver.switchTo().alert().accept();
		sleepInSecond(2);
		Assert.assertFalse(driver.findElement(By.cssSelector("li.context-menu-icon-quit span")).isDisplayed());
}

	private void sleepInSecond(long timeout) {
		try {
			Thread.sleep(timeout * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
