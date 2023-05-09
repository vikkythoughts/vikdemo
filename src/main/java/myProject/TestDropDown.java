package myProject;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestDropDown {
	WebDriver driver = null;
	WebDriverWait wait = null;
	
	
	@Test
	public void dropdown() throws InterruptedException, MalformedURLException {
		
//		DesiredCapabilities cap = new DesiredCapabilities();
		
		ChromeOptions prefs = new ChromeOptions();
		prefs.addArguments("--remote-allow-origins=*");
//		prefs.addArguments("--incognito");
//        prefs.setBrowserVersion("111.0");
//        prefs.setAcceptInsecureCerts(true);
//        prefs.addArguments("WINDOWS");
        prefs.addArguments("chrome");
//        cap.setPlatform(Platform.WINDOWS);
//        cap.merge(prefs);
        
        driver = new RemoteWebDriver(new URL("http://192.168.1.5:4444"),prefs);
//		driver = new ChromeDriver(prefs);
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		driver.get("file:///C:/Users/VIKASH%20GUPTA/Documents/SelectSite.html");
		driver.manage().window().maximize();
		Select select = new Select(driver.findElement(By.id("pet-select")));
		select.selectByIndex(2);
		Thread.sleep(2000);
		select.selectByValue("parrot");
		Thread.sleep(2000);
		select.selectByVisibleText("Goldfish");
	}
		
		@Test
		public void testAlertPopWindow() throws InterruptedException {
//			driver = new ChromeDriver();
//	    driver.get("file:///C:/Users/VIKASH%20GUPTA/Documents/SelectSite.html");
//	    driver.manage().window().maximize();
		driver.findElement(By.xpath("//input[@value=\"Click me\"]")).click();
		Alert alert = driver.switchTo().alert();
		String alertdisplay = alert.getText();
		Assert.assertEquals(alertdisplay, "This is an alert dialog box");
		Thread.sleep(5000);
		alert.accept();
		
		}
	

}
