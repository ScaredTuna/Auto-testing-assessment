package com.qa.travel;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.qa.testData.Constant;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class GuestBookingTest {
	
	WebDriver driver;
	ExtentTest testReport;
	ExtentReports report;
	
	@Before
	public void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver", "C://webDevelopment/chromedriver.exe");
		driver = new ChromeDriver();
		report = new ExtentReports("C://webDevelopment/assessment/guestBookingTestReport.html", true);
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
	}

	@Test
	public void test() throws InterruptedException {
		testReport = report.startTest("Hotel Guest Booking Test");
		testReport.log(LogStatus.INFO, "Opening browser to www.phptravels.net");
		
		driver.get(Constant.phptravels);
		
		testReport.log(LogStatus.INFO, "Enter hotel booking details");
		
		driver.findElement(By.xpath("//*[@id=\"s2id_autogen10\"]/a")).click();
		driver.findElement(By.xpath("//*[@id=\"select2-drop\"]/div/input")).sendKeys("London");
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id=\"select2-drop\"]/ul/li/ul/li[1]")).click();
		driver.findElement(By.xpath("//*[@id=\"dpd1\"]/input")).clear();
		driver.findElement(By.xpath("//*[@id=\"dpd1\"]/input")).sendKeys("24/04/2018");
		driver.findElement(By.xpath("//*[@id=\"dpd2\"]/input")).clear();
		driver.findElement(By.xpath("//*[@id=\"dpd2\"]/input")).sendKeys("26/04/2018");
		driver.findElement(By.xpath("//*[@id=\"dpd2\"]/input")).click();
		driver.findElement(By.xpath("//*[@id=\"adults\"]")).click();
		driver.findElement(By.xpath("//*[@id=\"adults\"]/option[3]")).click();
		driver.findElement(By.xpath("//*[@id=\"child\"]")).click();
		driver.findElement(By.xpath("//*[@id=\"child\"]/option[1]")).click();
		
		testReport.log(LogStatus.INFO, "Submit hotel booking search");
		
		driver.findElement(By.xpath("//*[@id=\"HOTELS\"]/form/div[3]/div[3]/button")).click();
		
		testReport.log(LogStatus.INFO, "Go to specific hotel page");
		
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"body-section\"]/div[5]/div[3]/div/table/tbody/tr/td/div[2]/div/h4/a")).click();
		
		testReport.log(LogStatus.INFO, "Submit booking details");
		
		driver.findElement(By.xpath("/html/body/div[9]/div[4]/div/div[2]/form/div[1]/div/input")).clear();
		driver.findElement(By.xpath("/html/body/div[9]/div[4]/div/div[2]/form/div[1]/div/input")).sendKeys("26/04/2018");
		driver.findElement(By.xpath("/html/body/div[9]/div[4]/div/div[2]/form/div[2]/div/input")).clear();
		driver.findElement(By.xpath("/html/body/div[9]/div[4]/div/div[2]/form/div[2]/div/input")).sendKeys("28/04/2018");
		driver.findElement(By.xpath("/html/body/div[9]/div[4]/div/div[2]/form/div[3]/div/select")).click();
		driver.findElement(By.xpath("/html/body/div[9]/div[4]/div/div[2]/form/div[3]/div/select/option[3]")).click();
		driver.findElement(By.xpath("/html/body/div[9]/div[4]/div/div[2]/form/div[4]/div/select")).click();
		driver.findElement(By.xpath("/html/body/div[9]/div[4]/div/div[2]/form/div[4]/div/select/option[1]")).click();
		driver.findElement(By.xpath("/html/body/div[9]/div[4]/div/div[2]/form/div[5]/button")).click();
		
		testReport.log(LogStatus.INFO, "Book room");
		
		driver.findElement(By.xpath("/html/body/div[9]/div[7]/div/p[1]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id=\"ROOMS\"]/div/table/tbody/tr[1]/td/div[2]/div[3]/div[3]/button")).click();
		
		testReport.log(LogStatus.INFO, "Enter guest details and book");
		
		driver.findElement(By.xpath("//*[@id=\"guestform\"]/div[1]/div/input")).sendKeys(Constant.firstName);
		driver.findElement(By.xpath("//*[@id=\"guestform\"]/div[2]/div/input")).sendKeys(Constant.surname);
		driver.findElement(By.xpath("//*[@id=\"guestform\"]/div[3]/div/input")).sendKeys(Constant.email);
		driver.findElement(By.xpath("//*[@id=\"guestform\"]/div[4]/div/input")).sendKeys(Constant.email);
		driver.findElement(By.xpath("//*[@id=\"guestform\"]/div[6]/div/input")).sendKeys(Constant.mobileNo);
		driver.findElement(By.xpath("//*[@id=\"guestform\"]/div[7]/div/input")).sendKeys(Constant.address);
		driver.findElement(By.xpath("//*[@id=\"s2id_autogen1\"]/a")).click();
		driver.findElement(By.xpath("//*[@id=\"select2-drop\"]/ul/li[213]")).click();
		driver.findElement(By.xpath("//*[@id=\"guestform\"]/div[10]/div/textarea")).sendKeys("Additional request");
		driver.findElement(By.xpath("//*[@id=\"body-section\"]/div[2]/div/div/div[1]/div[2]/div[2]/div[4]/button")).click();
		
		testReport.log(LogStatus.INFO, "Verify booking");
		
		Thread.sleep(10000);
		String result = driver.findElement(By.xpath("//*[@id=\"invoiceTable\"]/tbody/tr[4]/td/table/tbody/tr[2]/td/table[1]/tbody/tr[6]/td[1]")).getText();
		String expected = "TOTAL STAY 2";
		
		if(result.equals(expected)) {
			testReport.log(LogStatus.PASS, "Room successfully booked");
		} else {
			testReport.log(LogStatus.FAIL, "Failed to book room");
		}
		
		assertEquals(expected, result);
		
		report.endTest(testReport);
		report.flush();
	}

}
