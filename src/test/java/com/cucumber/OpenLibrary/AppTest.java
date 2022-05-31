package com.cucumber.OpenLibrary;

import java.util.Scanner;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class AppTest {
    
	public static WebDriver driver;
	public String str;
	
	@BeforeClass
	public void startDriver() {
		try (Scanner sc = new Scanner(System.in)) {
			System.out.println("Enter Chrome or Firefox");
			String browser = sc.nextLine();
			while(!(browser.equalsIgnoreCase("Firefox") || browser.equalsIgnoreCase("Chrome"))) {
				System.out.println("Enter Chrome or Firefox");
				browser = sc.nextLine();
			}
			driver = Main.createDriver(browser);
		}
	}
	
	@Test(priority = 0)
	public void showCase() {
		Main mn = new Main(driver);
		mn.showCatelog();
	}
	
	@Test(priority = 1)
	public void getString() throws InterruptedException {
		Main mn = new Main(driver);
		str = mn.getElement();
	}
	
	@Test(priority = 2)
	public void condChk() {
		Main mn = new Main(driver);
		int num = mn.countBook(str);
		Assert.assertTrue(num>1000,"Doesn't contains Tamil books more than 1000");
		System.out.println("Contains Tamil books more than 1000");
	}
	
	@AfterClass
	public void stopDriver() {
		driver.quit();
	}
}
