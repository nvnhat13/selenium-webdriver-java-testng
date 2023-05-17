package webdriver;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

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

public class Topic_14_Custom_Dropdown {

	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	Random random = new Random();
	WebDriverWait explicitWait;

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
		explicitWait = new WebDriverWait(driver, 30);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

//	@Test
	public void TC_01_JQuery() {
		// Dùng vòng lặp FOR, hàm IF, explicitlyWait, break
		
		driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");
		
		selectItemInCustomDropdown("//span[@id='number-button']", "//ul[@id='number-menu']/li/div", "2");		 
		Assert.assertEquals(driver.findElement(By.xpath("//span[@id='number-button']/span[@class='ui-selectmenu-text']")).getText(), "2");
		   			 		
		
		selectItemInCustomDropdown("//span[@id='speed-button']","//ul[@id='speed-menu']/li/div", "Slower");		
		Assert.assertEquals(driver.findElement(By.xpath("//span[@id='speed-button']/span[@class='ui-selectmenu-text']")).getText(), "Slower");	
		
		selectItemInCustomDropdown("//span[@id='files-button']","//ul[@id='files-menu']/li/div", "jQuery.js");		
		Assert.assertEquals(driver.findElement(By.xpath("//span[@id='files-button']/span[@class='ui-selectmenu-text']")).getText(), "jQuery.js");	
		
		selectItemInCustomDropdown("//span[@id='salutation-button']","//ul[@id='salutation-menu']/li/div", "Dr.");		
		Assert.assertEquals(driver.findElement(By.xpath("//span[@id='salutation-button']/span[@class='ui-selectmenu-text']")).getText(), "Dr.");	
		
		
	}
	//@Test
	public void TC_02_ReactJS() {

		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection");
		selectItemInCustomDropdown("//i[@class='dropdown icon']", "//div[@class='visible menu transition']", "Christian");
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='item']/span[text()='Christian']")).getText(), "Christian");
		}

	//@Test
	public void TC_03_VueJS() {

		driver.get("https://mikerodham.github.io/vue-dropdowns/");
		selectItemInCustomDropdown("//li[@class='dropdown-toggle']", "//ul[@class='dropdown-menu']/li/a", "First Option");
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='dropdown-toggle']")).getText(), "First Option");
	
	} 
	
	@Test
	public void TC_04_Editable() {

		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");
		sleepInSecond(3);
		selectItemInEditableDropdown("//input[@class='search']", "//div[@class='visible menu transition']", "Aland");
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='visible menu transition']")).getText(), "Aland Islands");
	
	}
	
	private void sleepInSecond(long timeout) {
		try {
			Thread.sleep(timeout * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	};

	public void selectItemInCustomDropdown(String xpathParent, String xpathChild, String expectedText) {
	    // Click vào 1 thẻ để xổ hết các item
	    driver.findElement(By.xpath(xpathParent)).click();
	    sleepInSecond(1);

	    // Chờ tất cả item được hiển thị
	    explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(xpathChild)));
	    // Lấy hết tất cả các item trong dropdown lưu vào list
	    List<WebElement> allItems = driver.findElements(By.xpath(xpathChild));
	    // Duyệt qua từng item
	    for (WebElement tempElement : allItems) {
	        // Get text của từng item
	        String itemText = tempElement.getText();
	        System.out.println(itemText);
	        // Kiểm tra text đúng với cái mình cần chọn
	        if (itemText.equals(expectedText)) {
	            // Scroll tới element cần chọn
	            ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", tempElement);	          
	            sleepInSecond(2);
	            tempElement.click();
	            sleepInSecond(2);

	            break;
	        }
	    }
	}

	public void selectItemInEditableDropdown(String xpathParent, String xpathChild, String expectedText) {
	    // Click vào 1 thẻ để xổ hết các item
	    driver.findElement(By.xpath(xpathParent)).sendKeys(expectedText);
	    sleepInSecond(1);

	    // Chờ tất cả item được hiển thị
	    explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(xpathChild)));
	    // Lấy hết tất cả các item trong dropdown lưu vào list
	    List<WebElement> allItems = driver.findElements(By.xpath(xpathChild));
	    // Duyệt qua từng item
	    for (WebElement tempElement : allItems) {
	        // Get text của từng item
	        String itemText = tempElement.getText();
	        System.out.println(itemText);
	        // Kiểm tra text đúng với cái mình cần chọn
	        if (itemText.equals(expectedText)) {
	            // Scroll tới element cần chọn
	            ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", tempElement);	          
	            sleepInSecond(2);
	            tempElement.click();
	            sleepInSecond(2);

	            break;
	        }
	    }
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}