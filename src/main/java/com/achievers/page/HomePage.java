package com.achievers.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
	WebDriver driver;
	WebDriverWait wait;
	
	public HomePage(WebDriver driver) {
		this.driver=driver;
		
	}
	
	
	public void fillPopRegistrationForm() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("color-dark font-weight-700 text-center undarline-style mx-4")));
		driver.findElement(By.className("color-dark font-weight-700 text-center undarline-style mx-4"));
		
	}
	
	public void enrollNow(String Fullname,String mail,String mobilenumber,String date,String timeSlot,String mode,String branch) {
		driver.findElement(By.name("full_name")).sendKeys("Fullname");
		driver.findElement(By.name("email")).sendKeys("mail");
		driver.findElement(By.xpath("(//input[@name=\"mobile_number\"])[2]")).sendKeys("mobilenumber");
		Select select = new Select(driver.findElement(By.name("scheduled_date")));
		select.selectByVisibleText("scheduled_date");
	    select = new Select(driver.findElement(By.name("scheduled_time_slot")));
	    select.selectByVisibleText("scheduled_time_slot");
	    select = new Select(driver.findElement(By.name("mode")));
	    select.selectByVisibleText("mode");
	    select = new Select(driver.findElement(By.name("branch_name")));
	    select.selectByVisibleText("branch_name");
	    
	    System.out.println("mynameisvikas1");
	    System.out.println("mynameisvikas2");
	    
	    driver.findElement(By.xpath("(//button[@type=\"submit\"])[2]")).click();
	 	
	}
	public String successfullySubmitted() {
		String msg = driver.findElement(By.xpath("//body[text()=\"schedule_demo_class_acknowledgement\"]")).getText();
		return msg;
	}

}
