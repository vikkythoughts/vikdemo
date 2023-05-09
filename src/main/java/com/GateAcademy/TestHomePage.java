package com.GateAcademy;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.achievers.page.HomePage;

public class TestHomePage {
	WebDriver driver = null;
	WebDriverWait wait=null;
	HomePage homepage=null;
	
	
	@Parameters("browser")
	@BeforeClass
	public void beforeClass(@Optional("chrome") String browser) {
		ChromeOptions prefs = new ChromeOptions();
		prefs.addArguments("--remote-allow-origins=*");
		if(browser.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver(prefs);
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
		
		 homepage = new HomePage(driver);
		
		wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		driver.get("https://www.gateacademy.co.in/");
		driver.manage().window().maximize();
		
	}
	@Test
	public void enrollmentWindow() {
		
		homepage.fillPopRegistrationForm();
		homepage.enrollNow("vikas verma", "vikasverma089gmail.com", "8764678865", "18/Mar/2023", "1pm - 2pm", "Live Class", "Mechanical Engineering");
		
		String messg =homepage.successfullySubmitted();
		Assert.assertEquals(messg, "schedule_demo_class_acknowledgement");
	}

}
