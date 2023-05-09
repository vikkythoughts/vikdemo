package myProject;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class FileUpload {

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
		
		driver.get("https://demo.guru99.com/test/upload/");
		driver.manage().window().maximize();
		
		wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		
	}
	
	@Test
	public void testFileUpload() throws IOException {
//		driver.findElement(By.name("uploadfile_0")).sendKeys("C:\\Users\\VIKASH GUPTA\\Downloads\\rest-assured-5.3.0.jar");
		driver.findElement(By.name("uploadfile_0")).sendKeys("C:\\Users\\VIKASH GUPTA\\Pictures\\Screenshots\\Screenshot (58).png");
		
		driver.findElement(By.xpath("//input[@id=\"terms\"]")).click();
		driver.findElement(By.xpath("//button[@id=\"submitbutton\"]")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//center[text()=\"has been successfully uploaded.\"]")));
//		boolean dis = driver.findElement(By.xpath("//center[text()=\"has been successfully uploaded.\"]")).isDisplayed();
//        Assert.assertEquals(dis, true);
		System.out.println(driver.findElement(By.xpath("//center[text()=\"has been successfully uploaded.\"]")).getText());
		
//		TakesScreenshot srcShot =  ((TakesScreenshot)driver);
//		
//		File srcFile =srcShot.getScreenshotAs(OutputType.FILE);
//		
//		String filePath = "./Screenshot/upload-image.jpeg";
//		
//		File destFile = new File(filePath);
//		
//		FileUtils.copyFile(srcFile, destFile);
		
		File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(srcFile, new File("./Screenshot/upload-image2.jpeg"));
	
	}

}
