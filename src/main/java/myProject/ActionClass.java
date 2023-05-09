package myProject;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;



public class ActionClass {
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
		driver.get("https://jqueryui.com/");
		driver.manage().window().maximize();
		
	}
	@Test
	public void testDraggable() throws InterruptedException {
		driver.findElement(By.linkText("Draggable")).click();
//		driver.switchTo().frame(0);
		driver.switchTo().frame(driver.findElement(By.className("demo-frame")));
		
		WebElement source = driver.findElement(By.id("draggable"));
		
		String actual = source.getText();
		Assert.assertEquals(actual, "Drag me around");
		
		Actions action = new Actions(driver);
		action.dragAndDropBy(source, 150, 150).build().perform();
		
		
	}
	
	@Test
	public void testDroppable() throws InterruptedException {

		driver.findElement(By.linkText("Droppable")).click();
		
		driver.switchTo().frame(0);
		
		WebElement source = driver.findElement(By.id("draggable"));
		WebElement target = driver.findElement(By.id("droppable"));
		
		String actual = source.getText();
		Assert.assertEquals(actual, "Drag me to my target");
		
		Actions action = new Actions(driver);
		action.dragAndDrop(source, target).build().perform();
		
	}
	
	@Test
	public void testResizable() {
		driver.findElement(By.linkText("Resizable")).click();
		
		driver.switchTo().frame(driver.findElement(By.className("demo-frame")));
		
		WebElement resize = driver.findElement(By.xpath("//div[@class=\"ui-resizable-handle ui-resizable-se ui-icon ui-icon-gripsmall-diagonal-se\"]"));
		Actions action = new Actions(driver);
		
		action.clickAndHold(resize).moveByOffset(100, 100).release().perform();
		
	}
	
	@Test
	public void testSelectable() throws InterruptedException {
		driver.findElement(By.linkText("Selectable")).click();
		
		driver.switchTo().frame(0);
		WebElement one = driver.findElement(By.xpath("//li[text()=\"Item 1\"]"));
		WebElement three = driver.findElement(By.xpath("//li[text()=\"Item 3\"]"));
		WebElement five = driver.findElement(By.xpath("//li[text()=\"Item 5\"]"));
//		Thread.sleep(2000);
		WebElement seven = driver.findElement(By.xpath("//li[text()=\"Item 7\"]"));
		
		Actions action = new Actions(driver);
		
		action.keyDown(Keys.CONTROL).click(one).pause(Duration.ofSeconds(1)).click(three).pause(Duration.ofSeconds(1)).click(five).pause(Duration.ofSeconds(1)).click(seven).perform();
		boolean highlited = driver.findElement(By.xpath("//li[@class=\"ui-widget-content ui-selectee ui-selected\"]")).isEnabled();
		Assert.assertEquals(highlited, true);
	}
	
	@Test
	public void testMenu() {
		
		driver.findElement(By.linkText("Menu")).click();
		
		driver.switchTo().frame(0);
		
		WebElement mainmenu = driver.findElement(By.id("ui-id-9"));
		WebElement submenu = driver.findElement(By.id("ui-id-13"));
		WebElement submenu2 = driver.findElement(By.id("ui-id-10"));
		
		Actions action = new Actions(driver);
		
		action.moveToElement(mainmenu).pause(Duration.ofSeconds(2)).moveToElement(submenu).pause(Duration.ofSeconds(2)).moveToElement(submenu2).perform();
		
		
	}
	
	@Test
	public void testShortable() {
		driver.findElement(By.linkText("Sortable")).click();
		
		driver.switchTo().frame(0);
		
		WebElement item1 = driver.findElement(By.xpath("//li[text()=\"Item 1\"]"));
		WebElement item2 = driver.findElement(By.xpath("//li[text()=\"Item 2\"]"));
		WebElement item4 = driver.findElement(By.xpath("//li[text()=\"Item 4\"]"));
		WebElement item6 = driver.findElement(By.xpath("//li[text()=\"Item 6\"]"));
		WebElement item5 = driver.findElement(By.xpath("//li[text()=\"Item 5\"]"));
		WebElement item3 = driver.findElement(By.xpath("//li[text()=\"Item 3\"]"));
		
		
		
		
		Actions action = new Actions(driver);
		
//		action.dragAndDrop(item2, item1).dragAndDrop(item6, item4).dragAndDrop(item5, item3).perform();
		action.dragAndDrop(item2, item1).dragAndDrop(item6, item4).dragAndDrop(item5, item3).perform();
	}
	
	@Test
	public void testAccordion() {
		driver.findElement(By.linkText("Accordion")).click();
		
		driver.switchTo().frame(0);
		
//		WebElement sec1 = driver.findElement(By.id("ui-id-1"));
		WebElement sec2 = driver.findElement(By.xpath("//h3[@id=\"ui-id-3\"]"));
    	WebElement sec3 = driver.findElement(By.id("ui-id-5"));
	    WebElement sec4 = driver.findElement(By.id("ui-id-7"));
		
		Actions action = new Actions(driver);
		
		action.click(sec3).perform();
		
		
//		sec1.click();
//		sec2.click();
//	    sec3.click();
//	    sec4.click();
	}
	
	@Test
	public void tsetDatePicker() {
		
		driver.findElement(By.linkText("Datepicker")).click();
		
		driver.switchTo().frame(0);
		
		WebElement d1= driver.findElement(By.xpath("//input[@id=\"datepicker\"]"));
		Actions action = new Actions(driver);
		action.click(d1).perform();
		
		WebElement d2= driver.findElement(By.xpath("//a[text()=\"9\"]"));
		
		action.click(d2).perform();
		
		
		
	}
	
	@Test
	public void testSlider() {
		driver.findElement(By.linkText("Slider")).click();
		
		driver.switchTo().frame(0);
		
		WebElement slider = driver.findElement(By.xpath("//div[@id=\"slider\"]/span"));
		
		wait.until(ExpectedConditions.visibilityOf(slider));
		Actions action = new Actions(driver);
		
		action.clickAndHold(slider).moveToElement(slider, 400, 0).release(slider).perform();
		
	}
	@Test
	public void  testAutoComplete() throws InterruptedException {
		driver.findElement(By.linkText("Autocomplete")).click();
		
		driver.switchTo().frame(0);
		
	   driver.findElement(By.xpath("//input[@id=\"tags\"]")).sendKeys("jav");
	   
	   Thread.sleep(2000);
	   
	   WebElement auto = driver.findElement(By.xpath("//div[text()=\"JavaScript\"]"));
	   
	   Actions action = new Actions(driver);
	   
	   action.click(auto).perform();
		
	}
	
	@Test
	public void testControlGroup() throws InterruptedException {
		driver.findElement(By.linkText("Controlgroup")).click();
		
		driver.switchTo().frame(0);
		
		Actions action = new Actions(driver);
		
		WebElement car = driver.findElement(By.xpath("//span[@id=\"car-type-button\"]/span"));
		WebElement luxary =driver.findElement(By.xpath("//div[@id=\"ui-id-5\"]"));
		WebElement stand =driver.findElement(By.xpath("//label[@for=\"transmission-standard\"]"));
		WebElement auto =driver.findElement(By.xpath("//label[@for=\"transmission-automatic\"]"));
		WebElement inso =driver.findElement(By.xpath("//label[@for=\"insurance\"]"));
		
		 action.click(car).pause(1).click(luxary).pause(1).click(stand).pause(1).click(auto).perform();
		 Thread.sleep(1000);
		 action.click(inso).perform();
		 
		 WebElement numofcar = driver.findElement(By.xpath("//span[@class=\"ui-button-icon ui-icon ui-icon-triangle-1-n\"]"));
		 
		 int i;
		 for(i=0;i<=10;i++) {
			 
			 action.click(numofcar).pause(2).perform();
			 
		 }
		
	
		
		
	}

}
