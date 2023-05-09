package myProject;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TestWindowHandles {
	
	WebDriver driver = null;
	WebDriverWait wait = null;
	
	@Parameters("browser")
	@BeforeClass
	public void beforeClass(@Optional("chrome") String browser) {
		if(browser.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		}
		else if(browser.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
		}
		else if(browser.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		}
		else {
 			driver = new ChromeDriver();
		}
		
		wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		driver.get("https://www.freecodecamp.org/news/how-to-use-html-to-open-link-in-new-tab/");
		driver.manage().window().maximize();
	}
	
	@Test
	public void testWindowHandles() throws InterruptedException {
		String win1 = driver.getWindowHandle();
		
		driver.findElement(By.xpath("(//a[text()=\"freeCodeCamp\"])[2]")).click();
		
		Set<String> names = driver.getWindowHandles();
		
		names.remove(win1);
		
		String win2 = (String) names.toArray()[0];
		driver.switchTo().window(win2);
		driver.findElement(By.xpath("(//span[text()=\"Sign in\"])[2]")).click();
		
		boolean display1 = driver.findElement(By.xpath("//input[@id=\"email\"]")).isDisplayed();
		Assert.assertEquals(display1, true);
		Thread.sleep(2000);
		driver.close();
		
		driver.switchTo().window(win1);
		
		boolean display2 = driver.findElement(By.id("nav-donate")).isDisplayed();
		Assert.assertEquals(display2, true);
		
		driver.close();
		
	}

}
