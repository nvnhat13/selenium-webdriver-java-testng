package webdriver;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;



import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_13_Default_Dropdown_list {

	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	Random random = new Random();
	WebDriverWait explicitlyWait;
	Select select; // --> khai báo
	Actions action;
	String city;
	String emailAddress = "ANhat" + random.nextInt(99999) + "@gmail.com";
	String firstName = "Nguyen";
	String lastName = "Nhat";
	String companyName = "Lifesup";
	JavascriptExecutor jsExecutor;
	


	@BeforeClass
	public void beforeClass() {

		// Khi khởi tạo cần biến driver thì mới khởi tạo trong beforeClass.
		// Khởi tạo sau biến driver = new Firefox.Driver();
//Windows
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		}
//MacOS
		else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver.exe");
		}
		// Khởi tạo driver
		driver = new FirefoxDriver();
		System.out.println(driver.toString());
		// Khởi tạo các biến driver khác
		jsExecutor = (JavascriptExecutor) driver;
		action = new Actions(driver);

		// Hàm explicitlyWait dùng cho việc kiểm tra trạng thái của element: hiển thị/ko
		// hiển thị/ presence/ staleness
		explicitlyWait = new WebDriverWait(driver, 10);
		// Hàm implicitlyWait dùng cho việc tìm element (findElement/ findElements)
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	//@Test
	public void TC_01_Facebook() {
		driver.get("https://www.facebook.com/reg/");
//		// Các hàm select hay dùng
//		select.selectByIndex(3); // Thành Phố Hà Nội --> không dùng index vì nếu CRUD mới thì index sẽ thay đổi; khó khi cần reproduce lỗi
//		select.selectByValue("9806"); // TPHCM	--> không dùng khó reproduce lỗi, value không tồn tại do ko phải tham số bắt buộc	
//		select.selectByVisibleText("Thành phố Đà Nẵng"); // TP Đà Nẵng --> nên dùng do không bị cập nhật lại
//		select.getFirstSelectedOption();
//		select.getOptions(); //--> lấy ra tất cả các options---> trả về 1 list element
//		select.getAllSelectedOptions();
//		
//		// Các hàm ít dùng hoặc không dùng
//		select.deselectAll();
//		select.deselectByIndex(0);	
//		select.deselectByValue("");	
//		select.deselectByVisibleText(osName);
//		//Kiểm tra 1 dropdown là multiple
//		select.isMultiple(); 
//		Assert.assertTrue(select.isMultiple());
//		//Kiểm tra 1 dropdown là single
//		select.isMultiple(); 
//		Assert.assertFalse(select.isMultiple());
//		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Thành phố Đà Nẵng");
//		// Lấy tất cả options và kiểm tra
//		List<WebElement> city = select.getOptions();
//		Assert.assertEquals(city.size(),66);
//		// Dùng vòng lặp để in:
//		for (WebElement text:city) {
//			System.out.println(text.getText());
//		}
		// Chọn ngày
		select = new Select(driver.findElement(By.cssSelector("select#day")));
		select.selectByVisibleText("13");
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "13");
		List<WebElement> days = select.getOptions();
		System.out.println(days = select.getOptions());
		Assert.assertEquals(days.size(), 31);

		// Chọn tháng
		select = new Select(driver.findElement(By.cssSelector("select#month")));
		select.selectByVisibleText("May");
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "May");
		List<WebElement> months = select.getOptions();
		Assert.assertEquals(months.size(), 12);
		sleepInSecond(2);
		for (WebElement month : months) {
			System.out.println(month.getText());
		}
		// Chọn năm
		select = new Select(driver.findElement(By.cssSelector("select#year")));
		select.selectByVisibleText("1988");
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "1988");

		sleepInSecond(2);

	}

	@Test
	public void TC_02_() {
		driver.get("https://applitools.com/automating-tests-chrome-devtools-recorder-webinar/");
		sleepInSecond(2);
		driver.findElement(By.cssSelector("button#onetrust-accept-btn-handler")).click();
		sleepInSecond(2);
		// Input field
		driver.findElement(By.cssSelector("input#Email")).sendKeys(emailAddress);
		driver.findElement(By.cssSelector("input#FirstName")).sendKeys(firstName);
		driver.findElement(By.cssSelector("input#LastName")).sendKeys(lastName);
		driver.findElement(By.cssSelector("input#Company")).sendKeys(companyName);
		sleepInSecond(2);
		//Select HTML dropdown
		
		
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);",driver.findElement(By.cssSelector("select#Person_Role__c")));
		sleepInSecond(2);
		
		select = new Select(driver.findElement(By.cssSelector("select#Person_Role__c")));
		select.selectByVisibleText("CEO / CFO / COO");
	
		select = new Select(driver.findElement(By.cssSelector("select#Test_Framework__c")));
		select.selectByVisibleText("Selenium");
	
		select = new Select(driver.findElement(By.cssSelector("select#Self_Report_Country__c")));
		select.selectByVisibleText("Canada");
		
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