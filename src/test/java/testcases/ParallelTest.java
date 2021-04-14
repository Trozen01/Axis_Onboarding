package testcases;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;


import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
 

import resources.Base;
import resources.ExtentReporterNG;

public class ParallelTest extends Base{
	
	
	String newappNum;
	String MerchantCode;
	ExtentHtmlReporter htmlReporter;
	ExtentReports extent;
	ExtentTest test;
	
@BeforeTest
	
	public void openbrowser() throws Throwable{
		
		
		driver = initiatebrowser();
		String baseUrl = "https://testipg.eazy2pay.com/InsightAxisVP/Login.aspx";
		
		Properties prop = new Properties();

		try {
		    prop.load(new FileInputStream("OR.properties"));
		} catch (IOException e) {
		    e.printStackTrace();
		}
		
		String URL=prop.getProperty("url");
		driver.get(URL);
		
		String abc= prop.getProperty("ApplicationNo");
		 long newNum= Long.parseLong(abc);
		 long latestNm= newNum +1 ;
		 newappNum= Long.toString(latestNm);
		 
		
		
	}
	
	@Test
	public void Test2() throws Throwable{
		
		 try {
			test = extent.createTest("Test Case 2", "PASSED test case");
			 
			 test.log(Status.INFO, "test rohan");

			 driver.findElement(By.id("txtUserID")).clear();
			 String makerusername=prop.getProperty("makerusername");
			 
			 String makerpassword=prop.getProperty("makerpassword");
			 
			 
			    driver.findElement(By.id("txtUserID")).sendKeys(makerusername);
			    test.log(Status.INFO, "makerusername is "+makerusername);
			    driver.findElement(By.id("txtPassword")).clear();
			    driver.findElement(By.id("txtPassword")).sendKeys(makerpassword);
			   test.log(Status.INFO, "makerpassword is "+makerpassword);
			    driver.findElement(By.id("btnLogin")).click();
			    driver.findElement(By.linkText("Merchant Onboarding")).click();
			    test.log(Status.INFO, "Merchant Onboarding link clicked");
			    driver.findElement(By.linkText("Add Merchant Profile VP")).click();
			    test.log(Status.INFO, "Add Merchant Profile VP link clicked");
		 }catch(Exception e) {
			 System.out.println("TestCase failed");
				screenshot(driver,"test2dummy");
				 test.log(Status.FAIL, "test2 failed.");
		 }
			    
			    
		 }

}
