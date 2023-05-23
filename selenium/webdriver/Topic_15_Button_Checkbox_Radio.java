package webdriver;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.Color;
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

	 @Test
	public void TC_01_Button() {
		driver.get("https://www.fahasa.com/customer/account/create");
		driver.findElement(By.cssSelector("li.popup-login-tab-login")).click();
		sleepInSecond(2);
		// Verify
		By loginButton = By.cssSelector("li.popup-login-tab-login");
		// Disabled cách 1:
		Assert.assertFalse(driver.findElement(By.cssSelector("button.fhs-btn-login")).isEnabled());
		// Disabled cách 2:
		// Assert.assertFalse(driver.findElement(loginButton).isEnabled());
		// Verify background color màu xám
		Assert.assertEquals(driver.findElement(loginButton).getCssValue("background-color"), "rgba(0, 0, 0, 0)");
		// Nhập email/password, verify enable
		driver.findElement(By.id("login_username")).sendKeys(emailAddress);
		driver.findElement(By.id("login_password")).sendKeys(password);
		Assert.assertTrue(driver.findElement(By.cssSelector("button.fhs-btn-login")).isEnabled());
		sleepInSecond(2);
		// Login button chuyển sang background màu đỏ
		String loginButtonBackgroundColor = driver.findElement(loginButton).getCssValue("background-color");
		Color loginButtnColor = Color.fromString(loginButtonBackgroundColor);
		String loginButtnHexa = loginButtnColor.asHex().toUpperCase();
		Assert.assertEquals(loginButtnHexa, "#000000");
		
	}

	// Thẻ input của checkbox/radio hiển thị và thao tác đc thì gọi là default
	// Thẻ input của checkbox/radio ko hiển thị hoặc ko thao tác được gọi là custom
	// @Test
	public void TC_02_Default_Checkbox() {
		// Checkbox
		// Chọn
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

	// @Test
	public void TC_03_Default_Radio() {
		// Radio
		driver.get("http://demos.telerik.com/kendo-ui/styling/radios");
		By petrol147Kw = By.cssSelector("li input#engine3");
		if (!driver.findElement(petrol147Kw).isSelected()) {
			driver.findElement(petrol147Kw).click();
			sleepInSecond(3);
		}
		Assert.assertTrue(driver.findElement(petrol147Kw).isSelected());

	}

	// @Test
	public void TC_04_SelectAll_Checkbox() {
		driver.get("https://automationfc.github.io/multiple-fields/");
		// Dùng 1 list element để chứa hết checkbox
		List<WebElement> allCheckBoxes = driver
				.findElements(By.cssSelector("div.form-single-column input.form-checkbox"));
		for (WebElement checkbox : allCheckBoxes) {
			checkbox.click();
//			sleepInSecond(1);
		}
		// Verify toàn bộ checkbox đã được chọn
		for (WebElement checkbox : allCheckBoxes) {
			Assert.assertTrue(checkbox.isSelected());
//			sleepInSecond(1);
		}
	}

	// @Test
	public void TC_05_Select_Checkbox_Radio_ByCondition() {
		// Kiểm tra checkbox đúng đk mới được chọn
		driver.get("https://automationfc.github.io/multiple-fields/");
		List<WebElement> allCheckBoxes = driver
				.findElements(By.cssSelector("div.form-single-column input.form-checkbox"));
		for (WebElement checkbox : allCheckBoxes) {
			if (!checkbox.isSelected() && checkbox.getAttribute("value").equals("Gallstones")) {
				checkbox.click();
			}
			;
		}
		// Verify chỉ có checkbox Gallstones được chọn
		for (WebElement checkbox : allCheckBoxes) {
			if (checkbox.getAttribute("value").equals("Gallstones")) {
				Assert.assertTrue(checkbox.isSelected());
			}
			;
		}
		// Lưu hết elements của radio Exercise về
		List<WebElement> exerciseRadios = driver.findElements(
				By.xpath("//label[contains(text(),'Exercise')]/following-sibling::div//input[@type='radio']"));
		for (WebElement radio : exerciseRadios) {
			if (!radio.isSelected() && radio.getAttribute("value").equals("3-4 days")) {
				radio.click();
			}
			sleepInSecond(2);
		}
		// Verify chỉ có radio "3-4 days" được chọn
		for (WebElement radio : exerciseRadios) {
			if (radio.getAttribute("value").equals("3-4 days")) {
				Assert.assertTrue(radio.isSelected());
			}
			;
		}
		sleepInSecond(2);
	}

	// @Test
	public void TC_06_Custom_Checkbox_Radion() {
		driver.get("https://tiemchungcovid19.gov.vn/portal/register-person");
//		//Case 1: thẻ input ko click được nhưng lại verify được
//		By registerRadio = By.xpath("//div[text()='Đăng ký cho người thân']/preceding-sibling::div/input");		
//		driver.findElement(registerRadio).click();
//		Assert.assertTrue((driver.findElement(registerRadio)).isSelected());
		// Case 2: Dùng thẻ khác thì click được nhưng ko verify được do thẻ div không
		// thể có trạng thái selected
//		By registerRadio = By.xpath("//div[text()='Đăng ký cho người thân']/preceding-sibling::div/div[@class='mat-radio-outer-circle']");		
//		driver.findElement(registerRadio).click();
//		sleepInSecond(2);
//		Assert.assertTrue((driver.findElement(registerRadio)).isSelected());
		// Case 3: Dùng thẻ khác input để click, dùng thẻ input để verify--> thực tế
		// không dùng cách này
//		driver.findElement(By.xpath("//div[text()='Đăng ký cho người thân']/preceding-sibling::div/div[@class='mat-radio-outer-circle']")).click();
//		Assert.assertTrue((driver.findElement(By.xpath("//div[text()='Đăng ký cho người thân']/preceding-sibling::div/input"))).isSelected());	

		// Case 4: Dùng JS click, JS không quan tâm element có bị che hay không mà vẫn
		// click được
		By registerRadio = By.xpath("//div[text()='Đăng ký cho người thân']/preceding-sibling::div/input");
		jsExecutor.executeScript("arguments[0].click();", driver.findElement(registerRadio));
		sleepInSecond(2);
	}

	// @Test
	public void TC_07_Custom_Checkbox_Radio() {
		driver.get(
				"https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");
		By radioCanTho = By.xpath("//div[@id='i14']");
		Assert.assertEquals(driver.findElement(radioCanTho).getAttribute("aria-checked"), "false");
		driver.findElement(radioCanTho).click();
		Assert.assertEquals(driver.findElement(radioCanTho).getAttribute("aria-checked"), "true");
		sleepInSecond(2);

		By checkboxQuangNam = By.xpath("//div[@id='i31']");
		Assert.assertEquals(driver.findElement(checkboxQuangNam).getAttribute("aria-checked"), "false");
		driver.findElement(checkboxQuangNam).click();
		Assert.assertEquals(driver.findElement(checkboxQuangNam).getAttribute("aria-checked"), "true");
		sleepInSecond(2);

		List<WebElement> allCheckBoxes = driver.findElements(By.cssSelector("div[role='checkbox']"));
		for (WebElement checkbox : allCheckBoxes) {
			if (checkbox.getAttribute("aria-checked").equals("false")) {
				checkbox.click();
				sleepInSecond(1);
			}
		}
		for (WebElement checkbox : allCheckBoxes) {
			Assert.assertEquals(checkbox.getAttribute("aria-checked"), "true");

		}
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