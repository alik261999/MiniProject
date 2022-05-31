package com.cucumber.OpenLibrary;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;


public class Main {
	
	public WebDriver driver;
	
	public Main(WebDriver driver) {
		this.driver = driver;
	}
	
	public static WebDriver createDriver(String browser) {

		if(browser.equalsIgnoreCase("Chrome"))
			return SetupWebDriver.getWebDriverChrome();
		else if(browser.equalsIgnoreCase("Firefox"))
			return SetupWebDriver.getWebDriverFfox();
		else
			return SetupWebDriver.getWebDriverChrome();

	}
	
	public void showCatelog() {
		String bkNum;
		String bkName;
		JavascriptExecutor js = (JavascriptExecutor) driver;
		System.out.println("Books available in library");
		int i=0;
		while(true) {
			try {
				bkName = (String) js.executeScript("return document.getElementsByClassName('category-title')["+i+"].innerHTML");
				bkNum = (String) js.executeScript("return document.getElementsByClassName('category-count')["+i+"].innerHTML");
				bkNum = bkNum.replace(",", "");
				System.out.println(bkName+": "+bkNum);
				i++;
			}catch(Exception e) {
				break;
			}
		}
	}

	public String getElement() throws InterruptedException {

		driver.findElement(By.xpath("//header[1]/ul[1]/li[1]/div[1]/details[1]/summary[1]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//header[1]/ul[1]/li[1]/div[1]/details[1]/div[1]/ul[1]/li[1]/a[1]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//a[contains(text(),'See more...')]")).click();
		Thread.sleep(1000);
		return driver.findElement(By.xpath("//a[contains(text(),'Tamil')]/following::span[1]/b")).getText();

	}
	
	public int countBook(String st) {
		// TODO Auto-generated method stub
		String str = "";
		int num = 0;
		str = st.split(" ")[0];
		str = String.join("", str.split(","));
		num = Integer.parseInt(str);
		return num;
	}


}
