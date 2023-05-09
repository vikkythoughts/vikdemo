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
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class CheckUrl {

	
	WebDriver driver = null;
	WebDriverWait wait = null;

	@Parameters("browsername")
	@BeforeClass
	public void beforeClass(@Optional("chrome") String browsername) {
		
		if(browsername.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		}else if(browsername.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
		}else if(browsername.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		}
		
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		driver.get("https://www.google.com/");
		driver.manage().window().maximize();
   
	}
	
	@Test
	public void testBrokenUrl() throws InterruptedException {
		
	    Response response = null;
		// 62
		List<WebElement> anchors = driver.findElements(By.xpath("//a"));
		 // 1 2
		 for (WebElement webElement : anchors) {
			
			 String url = webElement.getAttribute("href");
			// System.out.println(url);
			 
			 response = RestAssured.given().get(url);
			 
			 if(response.getStatusCode() == 200) {
				 System.out.println("Unbroken Link: " + url);
			 }else {
				 System.out.println("Broken Url : " + url);
				 Assert.assertNotEquals(response.getStatusCode(), 200);
			 }
			 
		}
		
		
	}
	
	
	
}

