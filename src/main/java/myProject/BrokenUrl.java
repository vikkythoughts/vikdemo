package myProject;

import java.time.Duration;
import java.util.List;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;


public class BrokenUrl {
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
		driver.get("https://www.google.com/");
		driver.manage().window().maximize();
		
	}
	
	@Test
	public void testBrokenUrl() {
		
		Response response = null;
		List<WebElement> anchor =driver.findElements(By.xpath("//a"));
		for (WebElement webElement : anchor) {
			String url = webElement .getAttribute("href");
//			System.out.println(url);
			response = RestAssured.given().get(url);
//			response = RestAssured.given().get(url);
			
			if(response.getStatusCode()==200) {
				System.out.println("unbroken url "+ url);
			}
			else {
				System.out.println("broken url "+ url);
				
			}
		}
		
	}

}
