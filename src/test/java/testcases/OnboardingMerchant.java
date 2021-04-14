package testcases;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
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

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement; 



public class OnboardingMerchant extends Base{
	
	String newappNum;
	String MerchantCode;
	ExtentHtmlReporter htmlReporter;
	ExtentReports extent;
	ExtentTest test;
	String testName;
	String men;
	String errorpath; 
	
	@BeforeClass
     public  void startTest()
	
	{
		
	System.out.println("entering before class");	
	
	}

	@BeforeSuite
	public void testsetup() throws Throwable{
		System.out.println("entering before suite");
		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") +"//test-output//AxisReport.html");
	
		
        
        //initialize ExtentReports and attach the HtmlReporter
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
         
        //To add system or environment info by using the setSystemInfo method.
        htmlReporter.config().setChartVisibilityOnOpen(true);
        htmlReporter.config().setDocumentTitle("Extent Report Demo");
        htmlReporter.config().setReportName("Test Report");
        htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
        htmlReporter.config().setTheme(Theme.STANDARD);
        htmlReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
        
		
      
	}
	
	@AfterSuite
	public void testend() throws Throwable{
		System.out.println("Entered after suite method.");
		
		driver.quit();
		
		extent.flush();
		
		Thread.sleep(4000);
		
		  copyFile();
		  
		  // this code is for auto mail from our outlook.
			/*	try {
				//String script = "E:\\MMS\\MMS\\sendmail.vbs";
				String script = System.getProperty("user.dir")+"\\sendmailaxis.vbs";
				// search for real path:
				String executable = "C:\\windows\\System32\\wscript.exe"; 
				String cmdArr [] = {executable, script};
				Runtime.getRuntime ().exec (cmdArr);
				
				
		    } catch (Exception ex) {
		        ex.printStackTrace();
		    } */
		   
	}
	
	
	
	
	@BeforeMethod
	
	public void openbrowser(Method method) throws Throwable{
		
		testName = method.getName();
		driver = initiatebrowser();
		String baseUrl = "https://testipg.eazy2pay.com/InsightAxisVP/Login.aspx";
		String strFileName="C:\\demo\\E2Eproject\\OR.properties";
	    String newappNum =null;
	    try {
	    	File f = new File(strFileName);
			if (f.exists()) {
				FileInputStream in = new FileInputStream(f);
				 Properties props = new Properties();
				//System.out.println("load"+in);
				props.load(in);
				 String newappNum1 =prop.getProperty("ApplicationNo");
				long newNum= Long.parseLong(newappNum1);
				 long latestNm= newNum +1;
				 newappNum1= Long.toString(latestNm);
				 
				 props.setProperty("ApplicationNo", newappNum1);
				props.store(new FileOutputStream(strFileName), null);
				
				in.close();
			} else {
				System.out.println("File not found!");
				System.out.println("File not found! For github");
			}
	    } catch (FileNotFoundException ex) {
	        // file does not exist
	    }
		
		
		
		 System.out.println("newapp no from prop file  is: "+prop.getProperty("ApplicationNo"));
	}
	
	
	
	
	@Test
	public void OnboardingMerchant() throws Throwable{
		
		Hashtable<String,String> data=new Hashtable<String,String>();
		
		
		 try {
			test = extent.createTest(testName, "Merchant Onboard execution start");
			
			 
			 test.log(Status.INFO, "Terst Value Proposition: Smart+Smart Plus;PSTN+GPRS");

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
			    
			    Thread.sleep(3000);
			    JavascriptExecutor js = (JavascriptExecutor) driver;  
			    js.executeScript("window.scrollBy(0,1000)");
			    
			    Thread.sleep(3000);
			    driver.findElement(By.linkText("Add Merchant Profile VP")).click();
			    test.log(Status.INFO, "Add Merchant Profile VP link clicked");
			    // app no logic
			   
			   
			    switchToFrame();
			    waitForPageLoaded();
			    Thread.sleep(3000);
			    // ERROR: Caught exception [ERROR: Unsupported command [selectFrame |  | ]]
			    String strFileName="C:\\demo\\E2Eproject\\OR.properties";
			    String newappNum =null;
			    // logic for app number
			    try {
					File configFile = new File(strFileName);
					 
					try {
					    FileReader reader = new FileReader(configFile);
					    Properties props = new Properties();
					    props.load(reader);
					 
					    newappNum = props.getProperty("ApplicationNo");
					 
					    System.out.print("newappNum is: " + newappNum);
					    reader.close();
					} catch (FileNotFoundException ex) {
					    // file does not exist
					} catch (IOException ex) {
					    // I/O error
					}
				} catch (Exception e) {
					System.out.println(e);
				}
			 
			    
			    //---------------------MERCHANT DETAILS-------------------------------------------
			    test.log(Status.INFO, "***MERCHANT DETAILS SECTION STARTED***");
			    waitForAjax(driver);
			    new Select(driver.findElement(By.id("ContentPlaceHolder1_drp_merchantIdentification"))).selectByVisibleText("New");
			    test.log(Status.INFO, "Merchant Identification selected: New");
			    waitForSpinner(driver,8);
			    waitforElementToBeVisible(driver.findElement(By.xpath("//option[@value='1']")), 4);
			    driver.findElement(By.xpath("//option[@value='1']")).click();
			    waitForSpinner(driver,8);
			    waitforElementToBeVisible(driver.findElement(By.id("ContentPlaceHolder1_txt_ApplicationNo")), 4);
			    driver.findElement(By.id("ContentPlaceHolder1_txt_ApplicationNo")).clear();
			    waitForSpinner(driver,8);
			    waitforElementToBeVisible(driver.findElement(By.id("ContentPlaceHolder1_txt_ApplicationNo")), 4);
			    driver.findElement(By.id("ContentPlaceHolder1_txt_ApplicationNo")).sendKeys(newappNum);
			    data.put("ApplicationNo","newappNum");  
			    test.log(Status.INFO, "Application No.: "+newappNum);
			    
			    waitForSpinner(driver,8);
			    Thread.sleep(2000);
			    try {
			    	driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
			    	if(driver.findElements(By.id("ContentPlaceHolder1_lbl_ApplicationNo")).size()>0) {
			    		System.out.println("Application number duplicate:"+newappNum);
			    	}
			    }catch(Exception e) {
			    	
			    }finally {
			    	driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			    }
			    	
			    waitforElementToBeVisible(driver.findElement(By.id("drp_merchantType")), 4);
			    driver.findElement(By.id("drp_merchantType")).click();
			    Thread.sleep(2000);
			    waitForSpinner(driver,8);
			    waitforElementToBeVisible(driver.findElement(By.id("drp_merchantType")), 4);
			    new Select(driver.findElement(By.id("drp_merchantType"))).selectByVisibleText("Normal");
			    data.put("merchantType","Normal");
			    waitForSpinner(driver,8);
			    driver.findElement(By.xpath("//option[@value='NRM']")).click();
			    waitForSpinner(driver,8);
			    Thread.sleep(2000);
			    test.log(Status.INFO, "Merchant Type selected: Normal");
			    waitforElementToBeVisible(driver.findElement(By.id("ContentPlaceHolder1_drp_merchantSegment")), 4);
			    new Select(driver.findElement(By.id("ContentPlaceHolder1_drp_merchantSegment"))).selectByVisibleText("Retail");
			    data.put("merchantSegment","Retail");
			    waitForSpinner(driver,8);
			    Thread.sleep(2000);
			    test.log(Status.INFO, "Merchant Segment selected: Retail");
			    waitforElementToBeVisible(driver.findElement(By.id("ContentPlaceHolder1_drp_MCC")), 4);
			    new Select(driver.findElement(By.id("ContentPlaceHolder1_drp_MCC"))).selectByVisibleText("1520-GENERAL CONTRACTORS");
			    data.put("drp_MCC","1520-GENERAL CONTRACTORS");
			    waitForSpinner(driver,8);
			    test.log(Status.INFO, "MCC: 1520-GENERAL CONTRACTORS");
			    waitforElementToBeVisible( driver.findElement(By.xpath("//option[@value='252']")), 4);
			    driver.findElement(By.xpath("//option[@value='252']")).click();
			    waitForSpinner(driver,8);
			    waitforElementToBeVisible( driver.findElement(By.id("ContentPlaceHolder1_drpTCC")), 4);
			    new Select(driver.findElement(By.id("ContentPlaceHolder1_drpTCC"))).selectByVisibleText("R-Retail Sales");
			    data.put("drpTCC","R-Retail Sales");
			    Thread.sleep(2000);
			    waitForSpinner(driver,8);
			    test.log(Status.INFO, "TCC:R-Retail Sales");
			    waitforElementToBeVisible( driver.findElement(By.id("ContentPlaceHolder1_ddlPANForm")), 4);
			    new Select(driver.findElement(By.id("ContentPlaceHolder1_ddlPANForm"))).selectByVisibleText("Form 60");
			    data.put("PANForm","Form 60");
			    waitForSpinner(driver,8);
			    driver.findElement(By.id("ContentPlaceHolder1_txt_firmPanNo")).clear();
			    waitForSpinner(driver,8);
			    waitforElementToBeVisible( driver.findElement(By.id("ContentPlaceHolder1_txt_firmPanNo")), 4);
			    driver.findElement(By.id("ContentPlaceHolder1_txt_firmPanNo")).sendKeys("Form 60");
			    waitForSpinner(driver,8);
			    data.put("firmPanNo","Form 60");
			    waitforElementToBeVisible( driver.findElement(By.xpath("//div[@id='demo-form2 ']/div[14]")), 4);
			    driver.findElement(By.xpath("//div[@id='demo-form2 ']/div[14]")).click();
			    waitForSpinner(driver,8);
			    waitforElementToBeVisible( driver.findElement(By.id("ContentPlaceHolder1_txt_MELegalName")), 4);
			    driver.findElement(By.id("ContentPlaceHolder1_txt_MELegalName")).clear();
			    waitForSpinner(driver,8);
			    waitforElementToBeVisible( driver.findElement(By.id("ContentPlaceHolder1_txt_MELegalName")), 4);
			    driver.findElement(By.id("ContentPlaceHolder1_txt_MELegalName")).sendKeys("Pankaj");
			    test.log(Status.INFO, "Merchant Legal Name: Pankaj");
			    data.put("MELegalName","Pankaj");
			    waitForSpinner(driver,8);
			    driver.findElement(By.id("ContentPlaceHolder1_txt_MeDBAName")).clear();
			    waitForSpinner(driver,8);
			    waitforElementToBeVisible( driver.findElement(By.id("ContentPlaceHolder1_txt_MeDBAName")), 4);
			    driver.findElement(By.id("ContentPlaceHolder1_txt_MeDBAName")).sendKeys("Contractor pvt ltd.");
			    test.log(Status.INFO, "Merchant DBA name: India Contractor pvt.Ltd.");
			    data.put("MeDBAName","Contractor pvt ltd.");
			    waitForSpinner(driver,8);
			    waitforElementToBeVisible( driver.findElement(By.id("ContentPlaceHolder1_drp_zoneCode")), 4);
			    new Select(driver.findElement(By.id("ContentPlaceHolder1_drp_zoneCode"))).selectByVisibleText("WEST ZONE");
			    waitForSpinner(driver,8);
			    data.put("zoneCode","WEST ZONE");
			    Thread.sleep(2000);
			    waitforElementToBeVisible(  driver.findElement(By.xpath("(//option[@value='1'])[5]")), 4);
			    driver.findElement(By.xpath("(//option[@value='1'])[5]")).click();
			    waitForSpinner(driver,8);
			    waitforElementToBeVisible(  driver.findElement(By.id("ContentPlaceHolder1_drpinstallationBranchCode")), 4);
			    new Select(driver.findElement(By.id("ContentPlaceHolder1_drpinstallationBranchCode"))).selectByVisibleText("4-Mumbai");
			    waitForSpinner(driver,8);
			    data.put("BranchCode","4-Mumbai");
			    waitforElementToBeVisible(   driver.findElement(By.xpath("(//option[@value='33'])[2]")), 4);
			    driver.findElement(By.xpath("(//option[@value='33'])[2]")).click();
			    waitForSpinner(driver,8);
			    waitforElementToBeVisible(driver.findElement(By.id("ContentPlaceHolder1_drp_businessEntity")), 4);
			    new Select(driver.findElement(By.id("ContentPlaceHolder1_drp_businessEntity"))).selectByVisibleText("Sole Proprietorship Concern");
			    waitForSpinner(driver,8);
			    data.put("businessEntity","Sole Proprietorship Concern");
			    test.log(Status.INFO, "Bisiness Entity: Sole Proprietorship Concern");
			    Thread.sleep(2000);
			   /* waitforElementToBeVisible(driver.findElement(By.id("ContentPlaceHolder1_drp_businessEntity")), 4);
			    new Select(driver.findElement(By.id("ContentPlaceHolder1_drp_businessEntity"))).selectByVisibleText("Private Ltd. Co.");
			    waitForSpinner(driver,8);
			    Thread.sleep(2000);*/
			    
			    
			    //-----------------------INSTALLATION ADDRESS--------------------------------------------------
			    test.log(Status.INFO, "***INSTALLATION ADDRESS DETAILS SECTION STARTED***");
			    waitforElementToBeVisible(driver.findElement(By.id("drpInSalutation")), 4);
			    new Select(driver.findElement(By.id("drpInSalutation"))).selectByVisibleText("Mr.");
			    waitForSpinner(driver,8);
			    driver.findElement(By.id("txtcntperson")).clear();
			    waitForSpinner(driver,8);
			    waitforElementToBeVisible(driver.findElement(By.id("txtcntperson")), 4);
			    driver.findElement(By.id("txtcntperson")).sendKeys("Pankaj");
			    waitForSpinner(driver,8);
			    data.put("person","Pankaj");
			    test.log(Status.INFO, "Contact Person: Pankaj");
			    driver.findElement(By.id("txtInstAddress1")).clear();
			    waitForSpinner(driver,8);
			    waitforElementToBeVisible(driver.findElement(By.id("txtInstAddress1")), 4);
			    driver.findElement(By.id("txtInstAddress1")).sendKeys("Navi Mumbai");
			    waitForSpinner(driver,8);
			    data.put("Address1","Navi Mumbai");
			    test.log(Status.INFO, "Installation Address: Navi Mumbai");
			    
			    driver.findElement(By.id("txtInstAddress2")).clear();
			    waitForSpinner(driver,8);
			    waitforElementToBeVisible(driver.findElement(By.id("txtInstAddress2")), 4);
			    driver.findElement(By.id("txtInstAddress2")).sendKeys("Navi Mumbai");
			    waitForSpinner(driver,8);
			    data.put("Address2","Navi Mumbai");
			    driver.findElement(By.id("txtlandmark")).clear();
			    waitForSpinner(driver,8);
			    waitforElementToBeVisible(driver.findElement(By.id("txtlandmark")), 4);
			    driver.findElement(By.id("txtlandmark")).sendKeys("Park hotel");
			    waitForSpinner(driver,8);
			    data.put("landmark","Park hotel");
			    test.log(Status.INFO, "Landmark: Pankaj");
			    waitforElementToBeVisible(driver.findElement(By.id("drpInGender")), 4);
			    new Select(driver.findElement(By.id("drpInGender"))).selectByVisibleText("Male");
			    waitForSpinner(driver,8);
			    Thread.sleep(2000);
			    driver.findElement(By.id("txtInstPincode")).clear();
			    waitForSpinner(driver,8);
			    driver.findElement(By.id("txtInstPincode")).sendKeys("400614");
			    data.put("Pincode","400614");
			    driver.findElement(By.id("txtInstPincode")).sendKeys(Keys.TAB);
			    Thread.sleep(2000);
			    waitForSpinner(driver,8);
			    test.log(Status.INFO, "Pincode: 400614");
			    waitforElementToBeVisible(driver.findElement(By.id("drpInstCity")),4);
			    new Select(driver.findElement(By.id("drpInstCity"))).selectByVisibleText("NAVI MUMBAI");
			    waitForSpinner(driver,8);
			    data.put("InstCity","NAVI MUMBAI");
			    Thread.sleep(2000);
			    	
			    test.log(Status.INFO, "Installation City: Navi Mumbai");
			    waitforElementToBeVisible(driver.findElement(By.id("drpInstLocation")),4);
			    new Select(driver.findElement(By.id("drpInstLocation"))).selectByVisibleText("CBD BELAPUR");
			    waitForSpinner(driver,8);
			    driver.findElement(By.id("txtInstMobile1")).clear();
			    waitForSpinner(driver,8);	
			    waitforElementToBeVisible(driver.findElement(By.id("txtInstMobile1")),4);
			    driver.findElement(By.id("txtInstMobile1")).sendKeys("9403425586");
			    waitForSpinner(driver,8);
			    data.put("Mobile1","9403425586");
			    test.log(Status.INFO, "Mobile No: 9403425586");	
			    driver.findElement(By.id("txtemailadd1")).clear();
			    waitForSpinner(driver,8);
			    waitforElementToBeVisible(driver.findElement(By.id("txtemailadd1")),4);
			    driver.findElement(By.id("txtemailadd1")).sendKeys("pankaj.warghade@worldline.com");
			    waitForSpinner(driver,8);
			    data.put("emailadd1","pankaj.warghade@worldline.com");
			    test.log(Status.INFO, "Email address1: pankaj.warghade@worldline.com");
			    scrolltoElement(driver.findElement(By.id("txtlandmark")));
			 //   scrollDown();
			    Thread.sleep(2000);
			   // waitforElementToBeVisible(driver.findElement(By.id("ContentPlaceHolder1_chkRegInstAdd")),4); Con	tentPlaceHolder1_chkRegInstAdd
			    driver.findElement(By.id("ContentPlaceHolder1_chkRegInstAdd")).click();
			    waitForSpinner(driver,8); 
			    test.log(Status.INFO, "Registered Address: Same as installtion address");
			    
			    scrolltoElement(driver.findElement(By.id("txtRegPincode")));
			    Thread.sleep(3000);
			    // 3 tabs   txtRegMobile2
			    driver.findElement(By.id("txtRegMobile2")).sendKeys(Keys.TAB);
			    
			    Robot robot = new Robot();
			 // Simulate key Events
			    robot.keyPress(KeyEvent.VK_TAB);
			    robot.keyRelease(KeyEvent.VK_TAB);
			 
					 
			  //  waitforElementToBeVisible(driver.findElement(By.id("ContentPlaceHolder1_chkOwnOneInstAdd")),8);
			    driver.findElement(By.id("ContentPlaceHolder1_chkOwnOneInstAdd")).click();
			    waitForSpinner(driver,8);
			    scrolltoElement(driver.findElement(By.id("txtOwnOneMobile1")));
			    Thread.sleep(2000);
			  //  waitforElementToBeVisible(driver.findElement(By.id("ContentPlaceHolder1_chkOwnTwoInstAdd")),4);
			    driver.findElement(By.id("ContentPlaceHolder1_chkOwnTwoInstAdd")).click();
			    waitForSpinner(driver,8);
			    scrolltoElement(driver.findElement(By.id("txtOwnTwoMobile1")));
			    Thread.sleep(2000);
			    //waitforElementToBeVisible(driver.findElement(By.id("ContentPlaceHolder1_chkBenfOwnOneInstAdd")),4);
			    driver.findElement(By.id("ContentPlaceHolder1_chkBenfOwnOneInstAdd")).click();
			    waitForSpinner(driver,8); 
			    scrolltoElement(driver.findElement(By.id("txtBenOwnOnePincode")));
			    Thread.sleep(3000);
			   // waitforElementToBeVisible(driver.findElement(By.id("ContentPlaceHolder1_chkBenfOwnTwoInstAdd")),4);
			    driver.findElement(By.id("ContentPlaceHolder1_chkBenfOwnTwoInstAdd")).click();
			    waitForSpinner(driver,8);
			    scrolltoElement(driver.findElement(By.id("txtBenOwnTwoPincode")));
			    
			    test.log(Status.INFO, "***GST SECTION STARTED***");
			    
			    waitforElementToBeVisible(driver.findElement(By.id("ContentPlaceHolder1_drpLOR")),4);
			    driver.findElement(By.id("ContentPlaceHolder1_drpLOR")).click();
			    waitForSpinner(driver,8);
			    waitforElementToBeVisible(driver.findElement(By.id("ContentPlaceHolder1_drpLOR")),4);
			    new Select(driver.findElement(By.id("ContentPlaceHolder1_drpLOR"))).selectByVisibleText("MAHARASHTRA");
			    Thread.sleep(2000);
			    waitForSpinner(driver,8);
			    waitforElementToBeVisible(driver.findElement(By.xpath("(//option[@value='27'])[8]")),4);
			    driver.findElement(By.xpath("(//option[@value='27'])[8]")).click();
			    waitForSpinner(driver,8);
			    
			    test.log(Status.INFO, "LOR State: Maharashtra");
			    
			    test.log(Status.INFO, "***PAYMENT DETAILS SECTION STARTED***");
			    
			    waitForSpinner(driver,8);
			    waitforElementToBeVisible(driver.findElement(By.id("ContentPlaceHolder1_ddlPaymentBy")),4);
			    new Select(driver.findElement(By.id("ContentPlaceHolder1_ddlPaymentBy"))).selectByVisibleText("A/C Credit");
			    waitForSpinner(driver,8);
			    waitforElementToBeVisible(driver.findElement(By.xpath("//option[@value='C']")),4);
			    driver.findElement(By.xpath("//option[@value='C']")).click();
			    waitForSpinner(driver,8);
			   
			    test.log(Status.INFO, "Payment By : A/C Credit");
			    
			    waitForSpinner(driver,8);
			    driver.findElement(By.id("txtACNumber")).clear();
			    waitForSpinner(driver,8);
			    waitforElementToBeVisible(driver.findElement(By.id("txtACNumber")),4);
			    driver.findElement(By.id("txtACNumber")).sendKeys("434353554546466");
			    waitForSpinner(driver,8);
			    data.put("ACNumber","434353554546466");
			    test.log(Status.INFO, "A/C Number : 434353554546466");
			    
			   /* driver.findElement(By.id("ContentPlaceHolder1_txtQRIFSCCODE")).clear();
			    waitForSpinner(driver,8);
			    waitforElementToBeVisible(driver.findElement(By.id("ContentPlaceHolder1_txtQRIFSCCODE")),4);
			    driver.findElement(By.id("ContentPlaceHolder1_txtQRIFSCCODE")).sendKeys("AXIS6666655");
			    waitForSpinner(driver,8);
			    
			    test.log(Status.INFO, "QR IFSC CODE : AXIS6666655");*/
			    
			    driver.findElement(By.id("txtQRACCNO")).clear();
			    waitForSpinner(driver,8);
			    waitforElementToBeVisible(driver.findElement(By.id("txtQRACCNO")),4);
			    driver.findElement(By.id("txtQRACCNO")).sendKeys("77665544444423244345566666");
			    waitForSpinner(driver,8);
			    data.put("QRACCNO","77665544444423244345566666");
			    test.log(Status.INFO, "QR Account No: 77665544444423244345566666");
			    
		
			    
			    new Select(driver.findElement(By.id("ContentPlaceHolder1_ddlIFSCCode"))).selectByVisibleText("UTIB0SVNS01");
			    Thread.sleep(2000);
			    waitForSpinner(driver,8);
			    waitforElementToBeVisible(driver.findElement(By.xpath("//option[@value='UTIB0SVNS01']")),4);
			    driver.findElement(By.xpath("//option[@value='UTIB0SVNS01']")).click();
			    Thread.sleep(2000);
			    waitForSpinner(driver,8);
			    data.put("BANKIFSCCode","UTIB0SVNS01");
			    test.log(Status.INFO, "Bank IFSC code: UTIB0SVNS01");
			    
			    driver.findElement(By.id("ContentPlaceHolder1_txtQRIFSCCODE")).clear();
			    waitForSpinner(driver,8);
			    waitforElementToBeVisible(driver.findElement(By.id("ContentPlaceHolder1_txtQRIFSCCODE")),4);
			    driver.findElement(By.id("ContentPlaceHolder1_txtQRIFSCCODE")).sendKeys("UTIB0SVNS01");
			    waitForSpinner(driver,8);
			    data.put("QRIFSCCODE","UTIB0SVNS01");
			    test.log(Status.INFO, "QR IFSC code: UTIB0SVNS01");
			    
			    new Select(driver.findElement(By.id("ddlNachWaive"))).selectByVisibleText("Waiver");
			   
			    waitForSpinner(driver,8);
			    waitforElementToBeVisible(driver.findElement(By.xpath("//option[@value='WAIVER']")),4);
			    driver.findElement(By.xpath("//option[@value='WAIVER']")).click();
			    Thread.sleep(2000);
			    waitForSpinner(driver,8);
			   
			    test.log(Status.INFO, "NACH-WAIVER selected"); 		 
			    
			 //   driver.findElement(By.id("txtNachRemarks")).sendKeys("None");
			   // waitForSpinner(driver,8);
			    
			       
			    
			/*    waitforElementToBeVisible(driver.findElement(By.id("chkperid")),4);
			    driver.findElement(By.id("chkperid")).click();
			    waitForSpinner(driver,8);
			    test.log(Status.INFO, "Document Checklist: Personal ID");*/
			    
			    
			    
			    
			   // waitforElementToBeVisible(driver.findElement(By.id("ContentPlaceHolder1_gvPlans_chkPlan_0")),4);
			    //.findElement(By.id("ContentPlaceHolder1_gvPlans_chkPlan_0")).click();
			   // waitForSpinner(driver,8);
			    waitforElementToBeVisible(driver.findElement(By.id("ContentPlaceHolder1_gvPlans_chkPlan_0")),4);
			    driver.findElement(By.id("ContentPlaceHolder1_gvPlans_chkPlan_0")).click();
			    waitForSpinner(driver,8);
			    waitforElementToBeVisible(driver.findElement(By.id("ContentPlaceHolder1_gvPlans_chkPlan_1")),4);
			    driver.findElement(By.id("ContentPlaceHolder1_gvPlans_chkPlan_1")).click();
			    waitForSpinner(driver,8);
			    test.log(Status.INFO, "Plan Selected: Smart");
			    waitforElementToBeVisible(driver.findElement(By.id("ContentPlaceHolder1_gvPlans_chkPlan_2")),4);
			    driver.findElement(By.id("ContentPlaceHolder1_gvPlans_chkPlan_2")).click();
			    waitForSpinner(driver,8);
			    test.log(Status.INFO, "Plan Selected: Smart Plus");
			    
			    waitforElementToBeVisible(driver.findElement(By.id("ContentPlaceHolder1_gvSmartPlusPlanDetails_chkSmartPlusPlanDetails_0")),4);
			    driver.findElement(By.id("ContentPlaceHolder1_gvSmartPlusPlanDetails_chkSmartPlusPlanDetails_0")).click();
			    waitForSpinner(driver,8);
			    waitforElementToBeVisible(driver.findElement(By.id("ContentPlaceHolder1_gvSmartPlusPlanDetails_chkSmartPlusPlanDetails_1")),4);
			    driver.findElement(By.id("ContentPlaceHolder1_gvSmartPlusPlanDetails_chkSmartPlusPlanDetails_1")).click();
			    waitForSpinner(driver,8);
			    
			    test.log(Status.INFO, "EDC ASSET MODEL SECTION STARTED");
			    waitforElementToBeVisible(driver.findElement(By.id("ContentPlaceHolder1_datalist_assetmodel_chkmodel_0")),4);
			    driver.findElement(By.id("ContentPlaceHolder1_datalist_assetmodel_chkmodel_0")).click();
			    waitForSpinner(driver,8);
			    
			    test.log(Status.INFO, "EDC asset model: PSTN");
			    
			    waitforElementToBeVisible(driver.findElement(By.id("txtcountofterminal")),4);
			    driver.findElement(By.id("txtcountofterminal")).clear();
			    waitForSpinner(driver,8);
			    waitforElementToBeVisible(driver.findElement(By.id("txtcountofterminal")),4);
			    driver.findElement(By.id("txtcountofterminal")).sendKeys("1");
			    waitForSpinner(driver,8);
			    data.put("countofterminal_0","1");
			    test.log(Status.INFO, "NO of TIDs: 1");
			    
			    driver.findElement(By.id("ContentPlaceHolder1_datalist_assetmodel_txtrental_0")).clear();
			    waitForSpinner(driver,8);
			    waitforElementToBeVisible(driver.findElement(By.id("ContentPlaceHolder1_datalist_assetmodel_txtrental_0")),4);
			    driver.findElement(By.id("ContentPlaceHolder1_datalist_assetmodel_txtrental_0")).sendKeys("100");
			    waitForSpinner(driver,8);
			    data.put("rental_0","100");
			    test.log(Status.INFO, "Monthly rental:100");
			    
			                              
			    driver.findElement(By.id("ContentPlaceHolder1_datalist_assetmodel_txtsetup_0")).clear();
			    waitForSpinner(driver,8);
			    waitforElementToBeVisible(driver.findElement(By.id("ContentPlaceHolder1_datalist_assetmodel_txtsetup_0")),4);
			    driver.findElement(By.id("ContentPlaceHolder1_datalist_assetmodel_txtsetup_0")).sendKeys("50");
			    waitForSpinner(driver,8);
			    data.put("setup_0","50");
			    test.log(Status.INFO, "Setup Fee:50");
			    
			  /*  driver.findElement(By.id("ContentPlaceHolder1_datalist_assetmodel_txtjoiningfee_0")).clear();
			    waitForSpinner(driver,8);
			    waitforElementToBeVisible(driver.findElement(By.id("ContentPlaceHolder1_datalist_assetmodel_txtjoiningfee_0")),4);
			    driver.findElement(By.id("ContentPlaceHolder1_datalist_assetmodel_txtjoiningfee_0")).sendKeys("200");
			    waitForSpinner(driver,8);
			    
			    test.log(Status.INFO, "Annual Rental Fee:200");*/
			    
			    waitforElementToBeVisible(driver.findElement(By.id("ContentPlaceHolder1_datalist_assetmodel_txtothercharges_0")),4);
			    driver.findElement(By.id("ContentPlaceHolder1_datalist_assetmodel_txtothercharges_0")).clear();
			    waitForSpinner(driver,8);
			    		    
			    waitforElementToBeVisible(driver.findElement(By.id("ContentPlaceHolder1_datalist_assetmodel_txtothercharges_0")),4);
			    driver.findElement(By.id("ContentPlaceHolder1_datalist_assetmodel_txtothercharges_0")).sendKeys("200");
			    waitForSpinner(driver,8);
			    data.put("othercharges_0","200");
			    test.log(Status.INFO, "Other Charges:200");
			    
			  //  waitforElementToBeVisible(driver.findElement(By.id("ContentPlaceHolder1_btnSave_P")),4);
			  //  driver.findElement(By.id("ContentPlaceHolder1_btnSave_P")).click();
			    waitForSpinner(driver,8);
			    waitforElementToBeVisible(driver.findElement(By.id("ContentPlaceHolder1_datalist_assetmodel_chkTablet_0")),4);
			    driver.findElement(By.id("ContentPlaceHolder1_datalist_assetmodel_chkTablet_0")).click();
			    
			    test.log(Status.INFO, "Tablet checkbox selected");
			    
			    waitForSpinner(driver,8);
			    driver.findElement(By.id("txtNoofTablet")).clear();
			    waitForSpinner(driver,8);
			    waitforElementToBeVisible(driver.findElement(By.id("txtNoofTablet")),4);
			    driver.findElement(By.id("txtNoofTablet")).sendKeys("1");
			    data.put("NoofTablet_0","1");
			    test.log(Status.INFO, "No of tablet : 1");
			    
			    waitForSpinner(driver,8);
			    waitforElementToBeVisible(driver.findElement(By.id("ContentPlaceHolder1_datalist_assetmodel_chkoffline_0")),4);
			    driver.findElement(By.id("ContentPlaceHolder1_datalist_assetmodel_chkoffline_0")).click();
			    waitForSpinner(driver,8);
			    
			    test.log(Status.INFO, "Parameter selected: Offline");
			    
			    waitforElementToBeVisible(driver.findElement(By.id("ContentPlaceHolder1_datalist_assetmodel_chkkey_0")),4);
			    driver.findElement(By.id("ContentPlaceHolder1_datalist_assetmodel_chkkey_0")).click();
			    waitForSpinner(driver,8);
			    
			    test.log(Status.INFO, "Parameter selected: Key");
			    
			    waitforElementToBeVisible(driver.findElement(By.id("ContentPlaceHolder1_datalist_assetmodel_chkautosetlrmrnt_0")),4);
			    driver.findElement(By.id("ContentPlaceHolder1_datalist_assetmodel_chkautosetlrmrnt_0")).click();
			    waitForSpinner(driver,8);
			    
			    test.log(Status.INFO, "Parameter selected: Force settlement");
			    
			    waitforElementToBeVisible(driver.findElement(By.id("ContentPlaceHolder1_datalist_assetmodel_chkrefund_0")),4);
			    driver.findElement(By.id("ContentPlaceHolder1_datalist_assetmodel_chkrefund_0")).click();
			    
			    test.log(Status.INFO, "Parameter selected: Refund");
			    
			    waitForSpinner(driver,8);
			    driver.findElement(By.id("ContentPlaceHolder1_datalist_assetmodel_txtTabletRental_0")).clear();
			    waitForSpinner(driver,8);
			    
			    waitforElementToBeVisible(driver.findElement(By.id("ContentPlaceHolder1_datalist_assetmodel_txtTabletRental_0")),4);
			    driver.findElement(By.id("ContentPlaceHolder1_datalist_assetmodel_txtTabletRental_0")).sendKeys("100");
			    waitForSpinner(driver,8);
			    data.put("TabletRental_0","100");
			    test.log(Status.INFO, "Tablet Rental Fee:100"); 
			    
			    waitforElementToBeVisible(driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_datalist_assetmodel']/tbody/tr[2]/td[14]/label/span[2]")),4);
			    driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_datalist_assetmodel']/tbody/tr[2]/td[14]/label/span[2]")).click();
			    waitForSpinner(driver,8);
			    waitforElementToBeVisible(driver.findElement(By.id("ContentPlaceHolder1_datalist_assetmodel_txtJoiningAmount_0")),4);
			    driver.findElement(By.id("ContentPlaceHolder1_datalist_assetmodel_txtJoiningAmount_0")).sendKeys("100");
			    data.put("JoiningAmount_0","100");
			    
			    waitforElementToBeVisible( driver.findElement(By.id("ContentPlaceHolder1_datalist_assetmodel_ddljoiningYears_0")), 4);
			    new Select(driver.findElement(By.id("ContentPlaceHolder1_datalist_assetmodel_ddljoiningYears_0"))).selectByVisibleText("2");
			    data.put("joiningYears_0","2");
			    
			    //------------------------GPRS-----------------------------------------------------------------------
			    
			                                                        
			    waitforElementToBeVisible(driver.findElement(By.id("ContentPlaceHolder1_datalist_assetmodel_chkmodel_3")),4);
			    driver.findElement(By.id("ContentPlaceHolder1_datalist_assetmodel_chkmodel_3")).click();
			    waitForSpinner(driver,8);
			    			    
			    test.log(Status.INFO, "EDC asset model: GPRS");
			    
			    //driver.findElement(By.id("txtcountofterminal")).clear();
			    driver.findElement(By.name("ctl00$ContentPlaceHolder1$datalist_assetmodel$ctl05$txtcountofterminal")).clear();
			    waitForSpinner(driver,8);
			    //waitforElementToBeVisible(driver.findElement(By.id("txtcountofterminal")),4);
			      waitforElementToBeVisible(driver.findElement(By.name("ctl00$ContentPlaceHolder1$datalist_assetmodel$ctl05$txtcountofterminal")),4);
			    
			    //driver.findElement(By.id("txtcountofterminal")).sendKeys("1");
			      driver.findElement(By.name("ctl00$ContentPlaceHolder1$datalist_assetmodel$ctl05$txtcountofterminal")).sendKeys("1");
			    
			    waitForSpinner(driver,8);
			    data.put("countofterminal_3","1");
			    test.log(Status.INFO, "NO of TIDs: 1");
			   
			    driver.findElement(By.id("ContentPlaceHolder1_datalist_assetmodel_txtrental_3")).clear();
			    waitForSpinner(driver,8);
			    waitforElementToBeVisible(driver.findElement(By.id("ContentPlaceHolder1_datalist_assetmodel_txtrental_3")),4);
			    driver.findElement(By.id("ContentPlaceHolder1_datalist_assetmodel_txtrental_3")).sendKeys("101");
			    waitForSpinner(driver,8);
			    data.put("rental_3","101");
			    test.log(Status.INFO, "Monthly rental:101");
			    
			    driver.findElement(By.id("ContentPlaceHolder1_datalist_assetmodel_txtsetup_3")).clear();
			    waitForSpinner(driver,8);
			    waitforElementToBeVisible(driver.findElement(By.id("ContentPlaceHolder1_datalist_assetmodel_txtsetup_3")),4);
			    driver.findElement(By.id("ContentPlaceHolder1_datalist_assetmodel_txtsetup_3")).sendKeys("51");
			    waitForSpinner(driver,8);
			    data.put("setup_3","51");
			    test.log(Status.INFO, "Setup Fee:51");
			    
			   /* waitforElementToBeVisible(driver.findElement(By.id("ContentPlaceHolder1_datalist_assetmodel_txtjoiningfee_3")),4);
			    driver.findElement(By.id("ContentPlaceHolder1_datalist_assetmodel_txtjoiningfee_3")).clear();
			    waitForSpinner(driver,8);
			    waitforElementToBeVisible(driver.findElement(By.id("ContentPlaceHolder1_datalist_assetmodel_txtjoiningfee_3")),4);
			    driver.findElement(By.id("ContentPlaceHolder1_datalist_assetmodel_txtjoiningfee_3")).sendKeys("201");
			    waitForSpinner(driver,8);
			    
			    test.log(Status.INFO, "Annual Rental Fee:201");*/
			    
			    driver.findElement(By.id("ContentPlaceHolder1_datalist_assetmodel_txtothercharges_3")).clear();
			    waitForSpinner(driver,8);
			    waitforElementToBeVisible(driver.findElement(By.id("ContentPlaceHolder1_datalist_assetmodel_txtothercharges_3")),4);
			    driver.findElement(By.id("ContentPlaceHolder1_datalist_assetmodel_txtothercharges_3")).sendKeys("202");
			    waitForSpinner(driver,8);
			    data.put("othercharges_3","202");
			    test.log(Status.INFO, "Other Charges:202");
			    
			    waitforElementToBeVisible(driver.findElement(By.id("ContentPlaceHolder1_datalist_assetmodel_chkTablet_3")),4);
			    driver.findElement(By.id("ContentPlaceHolder1_datalist_assetmodel_chkTablet_3")).click();
			    waitForSpinner(driver,8);
			    
			    test.log(Status.INFO, "Tablet checkbox selected");
			    
			    //driver.findElement(By.id("txtNoofTablet")).clear();

			    waitForSpinner(driver,8);
			    // waitforElementToBeVisible(driver.findElement(By.id("txtNoofTablet")),4);
			       waitforElementToBeVisible(driver.findElement(By.name("ctl00$ContentPlaceHolder1$datalist_assetmodel$ctl05$txtNoofTablet")),4);
			  
			      //driver.findElement(By.id("txtNoofTablet")).sendKeys("1");
			       driver.findElement(By.name("ctl00$ContentPlaceHolder1$datalist_assetmodel$ctl05$txtNoofTablet")).sendKeys("1");
			    waitForSpinner(driver,8);
			    data.put("NoofTablet_3","1");
			    test.log(Status.INFO, "No of tablet : 1");
			    
			    driver.findElement(By.id("ContentPlaceHolder1_datalist_assetmodel_txtTabletRental_3")).clear();
			    waitForSpinner(driver,8);
			    waitforElementToBeVisible(driver.findElement(By.id("ContentPlaceHolder1_datalist_assetmodel_txtTabletRental_3")),4);
			    driver.findElement(By.id("ContentPlaceHolder1_datalist_assetmodel_txtTabletRental_3")).sendKeys("150");
			    waitForSpinner(driver,8);
			    data.put("TabletRental_3","150");
			    test.log(Status.INFO, "Tablet Rental Fee:150");
			    
			    waitforElementToBeVisible(driver.findElement(By.id("ContentPlaceHolder1_datalist_assetmodel_chkoffline_3")),4);
			    driver.findElement(By.id("ContentPlaceHolder1_datalist_assetmodel_chkoffline_3")).click();
			    waitForSpinner(driver,8);
			    
			    test.log(Status.INFO, "Parameter selected: Offline");
			    
			    waitforElementToBeVisible(driver.findElement(By.id("ContentPlaceHolder1_datalist_assetmodel_chkkey_3")),4);
			    driver.findElement(By.id("ContentPlaceHolder1_datalist_assetmodel_chkkey_3")).click();
			    waitForSpinner(driver,8);
			    
			    test.log(Status.INFO, "Parameter selected: Key");
			    
			    waitforElementToBeVisible(driver.findElement(By.id("ContentPlaceHolder1_datalist_assetmodel_chkautosetlrmrnt_3")),4);
			    driver.findElement(By.id("ContentPlaceHolder1_datalist_assetmodel_chkautosetlrmrnt_3")).click();
			    waitForSpinner(driver,8);
			    
			    test.log(Status.INFO, "Parameter selected: Force settlement");
			    
			    waitforElementToBeVisible(driver.findElement(By.id("ContentPlaceHolder1_datalist_assetmodel_chkrefund_3")),4);
			    driver.findElement(By.id("ContentPlaceHolder1_datalist_assetmodel_chkrefund_3")).click();
			    waitForSpinner(driver,8);
			    
			    test.log(Status.INFO, "Parameter selected: Refund");
			    
			    
			    waitforElementToBeVisible(driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_datalist_assetmodel']/tbody/tr[5]/td[14]/label/span[2]")),4);
			    driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_datalist_assetmodel']/tbody/tr[5]/td[14]/label/span[2]")).click();
			    waitForSpinner(driver,8);
			    waitforElementToBeVisible(driver.findElement(By.id("ContentPlaceHolder1_datalist_assetmodel_txtJoiningAmount_3")),4);
			    driver.findElement(By.id("ContentPlaceHolder1_datalist_assetmodel_txtJoiningAmount_3")).sendKeys("100");
			    data.put("JoiningAmount_3","100");
			    
			    
			    waitforElementToBeVisible( driver.findElement(By.id("ContentPlaceHolder1_datalist_assetmodel_ddljoiningYears_3")), 4);
			    new Select(driver.findElement(By.id("ContentPlaceHolder1_datalist_assetmodel_ddljoiningYears_3"))).selectByVisibleText("2");
			    
			 		    
			//    test.log(Status.INFO, "NO of TIDs: 1,Monthly rental:100,Setup Fee:50,Annual Rental Fee:200,Other Charges:200,Tablet checkbox selected,No of tablet : 1,Parameter selected: Offline,Parameter selected: Key,Parameter selected: Force settlement,Parameter selected: Refund,Tablet Rental Fee:100");
			 		     //----------------MSF details------------
			    waitforElementToBeVisible(driver.findElement(By.id("ContentPlaceHolder1_DrpBusinessType")),4);
			    new Select(driver.findElement(By.id("ContentPlaceHolder1_DrpBusinessType"))).selectByVisibleText("Other Merchant (> 20 LAC)");
			    waitForSpinner(driver,8);
			    waitforElementToBeVisible(driver.findElement(By.xpath("//option[@value='O']")),4);
			    driver.findElement(By.xpath("//option[@value='O']")).click();
			    waitForSpinner(driver,8);
			    driver.findElement(By.id("ContentPlaceHolder1_CCP_OnUS")).clear();
			    waitForSpinner(driver,8);
			    waitforElementToBeVisible(waitforElementToBeVisible(driver.findElement(By.xpath("//option[@value='O']")),4),4);
			    driver.findElement(By.id("ContentPlaceHolder1_CCP_OnUS")).sendKeys("1");
			    waitForSpinner(driver,8);
			    data.put("CCP_OnUS","1");
			    test.log(Status.INFO, "Credit card-Premium : 1");
			    
			    driver.findElement(By.id("CCNonP_OnUS")).clear();
			    waitForSpinner(driver,8);
			    		    
			    waitforElementToBeVisible(driver.findElement(By.id("CCNonP_OnUS")),4);
			    driver.findElement(By.id("CCNonP_OnUS")).sendKeys("3");
			    waitForSpinner(driver,8);
			    data.put("CCNonP_OnUS","3");
			    test.log(Status.INFO, "Credit card-Non Premium :3");
			    
			    driver.findElement(By.id("txtCommercialPosOnus")).clear();
			    waitForSpinner(driver,8);
			    waitforElementToBeVisible(driver.findElement(By.id("txtCommercialPosOnus")),4);
			    driver.findElement(By.id("txtCommercialPosOnus")).sendKeys("1");
			    waitForSpinner(driver,8);
			    data.put("CommercialPosOnus","1");
			    test.log(Status.INFO, "Commercial POS ONUS  :1");
			    
			    driver.findElement(By.id("txtCommercialPosOffus")).clear();
			    waitForSpinner(driver,8);
			    waitforElementToBeVisible(driver.findElement(By.id("txtCommercialPosOffus")),4);
			    driver.findElement(By.id("txtCommercialPosOffus")).sendKeys("2");
			    waitForSpinner(driver,8);
			    data.put("CommercialPosOffus","2");
			    test.log(Status.INFO, "Commercial POS OFFUS  :2");
			    
			    
			    test.log(Status.INFO, "***EMI FEE CONFIGURATION SECTION STARTED***");
			    //start here
			    waitforElementToBeVisible(driver.findElement(By.id("ContentPlaceHolder1_gdvTenure_chkTenure_0")),4);
			    driver.findElement(By.id("ContentPlaceHolder1_gdvTenure_chkTenure_0")).click();
			    waitForSpinner(driver,8);
			    waitforElementToBeVisible(driver.findElement(By.id("ContentPlaceHolder1_gdvTenure_chkTenure_1")),4);
			    driver.findElement(By.id("ContentPlaceHolder1_gdvTenure_chkTenure_1")).click();
			    waitForSpinner(driver,8);
			    waitforElementToBeVisible(driver.findElement(By.id("ContentPlaceHolder1_gdvTenure_chkTenure_2")),4);
			    driver.findElement(By.id("ContentPlaceHolder1_gdvTenure_chkTenure_2")).click();
			    waitForSpinner(driver,8);
			    waitforElementToBeVisible(driver.findElement(By.id("ContentPlaceHolder1_gdvTenure_chkTenure_3")),4);
			    driver.findElement(By.id("ContentPlaceHolder1_gdvTenure_chkTenure_3")).click();
			    waitForSpinner(driver,8);
			    driver.findElement(By.id("ContentPlaceHolder1_gdvTenure_txtTenureFee_0")).clear();
			    waitForSpinner(driver,8);
			    waitforElementToBeVisible(driver.findElement(By.id("ContentPlaceHolder1_gdvTenure_txtTenureFee_0")),4);
			    driver.findElement(By.id("ContentPlaceHolder1_gdvTenure_txtTenureFee_0")).sendKeys("2");
			    waitForSpinner(driver,8);
			    data.put("TenureFee_0","2");
			    test.log(Status.INFO, "Credit fee rate % for tenure 3 : 2");
			    
			    driver.findElement(By.id("ContentPlaceHolder1_gdvTenure_txtTenureFee_1")).clear();
			    waitForSpinner(driver,8);
			    waitforElementToBeVisible(driver.findElement(By.id("ContentPlaceHolder1_gdvTenure_txtTenureFee_1")),4);
			    driver.findElement(By.id("ContentPlaceHolder1_gdvTenure_txtTenureFee_1")).sendKeys("3");
			    data.put("TenureFee_1","3");
			    
			    test.log(Status.INFO, "Credit fee rate % for tenure 6 : 3");
			    waitForSpinner(driver,8);
			    driver.findElement(By.id("ContentPlaceHolder1_gdvTenure_txtTenureFee_2")).clear();
			    waitForSpinner(driver,8);
			    waitforElementToBeVisible(driver.findElement(By.id("ContentPlaceHolder1_gdvTenure_txtTenureFee_2")),4);
			    driver.findElement(By.id("ContentPlaceHolder1_gdvTenure_txtTenureFee_2")).sendKeys("3");
			    waitForSpinner(driver,8);
			    data.put("TenureFee_2","3");
			    test.log(Status.INFO, "Credit fee rate % for tenure 9 : 3");
			    
			    driver.findElement(By.id("ContentPlaceHolder1_gdvTenure_txtTenureFee_3")).clear();
			    waitForSpinner(driver,8);
			    waitforElementToBeVisible(driver.findElement(By.id("ContentPlaceHolder1_gdvTenure_txtTenureFee_3")),4);
			    driver.findElement(By.id("ContentPlaceHolder1_gdvTenure_txtTenureFee_3")).sendKeys("4");
			    waitForSpinner(driver,8);
			    data.put("TenureFee_3","4");
			    test.log(Status.INFO, "Credit fee rate % for tenure 12 : 4");
			    
			    
			    test.log(Status.INFO, "End of EMI fee configuration & OFFICE USE SECTION STARTED");
			    
			    
			    waitforElementToBeVisible(driver.findElement(By.id("ContentPlaceHolder1_txtSECode")),4);
			    driver.findElement(By.id("ContentPlaceHolder1_txtSECode")).clear();
			    waitForSpinner(driver,8);
			    waitforElementToBeVisible(driver.findElement(By.id("ContentPlaceHolder1_txtSECode")),4);
			    driver.findElement(By.id("ContentPlaceHolder1_txtSECode")).sendKeys("776664444");
			    waitForSpinner(driver,8);
			    data.put("SECode","776664444");
			    test.log(Status.INFO, "SE CODE : 776664444");
			    
			    driver.findElement(By.id("ContentPlaceHolder1_txtBMCode")).clear();
			    waitForSpinner(driver,8);
			    waitforElementToBeVisible(driver.findElement(By.id("ContentPlaceHolder1_txtBMCode")),4);
			    driver.findElement(By.id("ContentPlaceHolder1_txtBMCode")).sendKeys("545465757");
			    waitForSpinner(driver,8);
			    data.put("BMCode","545465757");

			    test.log(Status.INFO, "BM CODE : 545465757");
			    
			    driver.findElement(By.id("ContentPlaceHolder1_txtASMCode")).clear();
			    waitForSpinner(driver,8);
			    waitforElementToBeVisible(driver.findElement(By.id("ContentPlaceHolder1_txtASMCode")),4);
			    driver.findElement(By.id("ContentPlaceHolder1_txtASMCode")).sendKeys("4466657577");
			    waitForSpinner(driver,8);
			    data.put("ASMCode","4466657577");
			    test.log(Status.INFO, "ASM CODE : 4466657577");
			    
			    waitforElementToBeVisible(driver.findElement(By.id("ContentPlaceHolder1_drpprofitstatus")),4);
			    new Select(driver.findElement(By.id("ContentPlaceHolder1_drpprofitstatus"))).selectByVisibleText("Branch Prefer Price");
			    waitForSpinner(driver,8);
			    
			    test.log(Status.INFO, "Pricing Category : Branch Prefer Price");
			    
			    driver.findElement(By.id("txtMonthlyCardV")).clear();
			    waitForSpinner(driver,8);
			    waitforElementToBeVisible(driver.findElement(By.id("txtMonthlyCardV")),4);
			    driver.findElement(By.id("txtMonthlyCardV")).sendKeys("1000000");
			    waitForSpinner(driver,8);
			    data.put("MonthlyCardV","1000000");
			    test.log(Status.INFO, "Monthly card vallume : 1000000");
			    
			    driver.findElement(By.id("ContentPlaceHolder1_txtdipcode")).clear();
			    waitForSpinner(driver,8);
			    waitforElementToBeVisible(driver.findElement(By.id("ContentPlaceHolder1_txtdipcode")),4);
			    driver.findElement(By.id("ContentPlaceHolder1_txtdipcode")).sendKeys("4545454555");
			    waitForSpinner(driver,8);
			    data.put("ipcode","4545454555");
			    test.log(Status.INFO, "DIP code : 1000000");
			    
			    driver.findElement(By.id("ContentPlaceHolder1_txttelecode")).clear();
			    waitForSpinner(driver,8);
			    waitforElementToBeVisible(driver.findElement(By.id("ContentPlaceHolder1_txttelecode")),4);
			    driver.findElement(By.id("ContentPlaceHolder1_txttelecode")).sendKeys("5656565657777");
			    waitForSpinner(driver,8);
			    data.put("telecode","5656565657777");
			    test.log(Status.INFO, "Tele verification Code : 5656565657777");
			    
			    driver.findElement(By.id("txtSGCode")).clear();
			    waitForSpinner(driver,8);
			    waitforElementToBeVisible(driver.findElement(By.id("txtSGCode")),4);
			    driver.findElement(By.id("txtSGCode")).sendKeys("545454");
			    waitForSpinner(driver,8);
			    data.put("SGCode","545454");
			    test.log(Status.INFO, "SG CODE : 545454");
			    
			    driver.findElement(By.id("ContentPlaceHolder1_txtsalesremark")).clear();
			    waitForSpinner(driver,8);
			   // waitforElementToBeVisible(driver.findElement(By.id("txtCommercialPosOffus")),4);
			    driver.findElement(By.id("ContentPlaceHolder1_txtsalesremark")).sendKeys("Onboarding");
			    waitForSpinner(driver,8);
			    data.put("salesremark","Onboarding");
			    test.log(Status.INFO, "Remarks : Onboarding");
			    
			    //waitforElementToBeVisible(driver.findElement(By.id("ContentPlaceHolder1_btnsubmit")),4);
			 //   driver.findElement(By.id("ContentPlaceHolder1_btnsubmit")).click();
			   
			   // waitforElementToBeVisible(driver.findElement(By.id("ContentPlaceHolder1_btnsubmit")),4);
			   // driver.findElement(By.id("ContentPlaceHolder1_btnsubmit")).click();
			    waitForSpinner(driver,8);
			  //  waitforElementToBeVisible(driver.findElement(By.xpath("(//button[@type='button'])[3]")),4);
			  //  driver.findElement(By.xpath("(//button[@type='button'])[3]")).click();
			  //  waitForSpinner(driver,8);
			    waitforElementToBeVisible(driver.findElement(By.id("ContentPlaceHolder1_datalist_assetmodel_chkoffline_0")),4);
			    driver.findElement(By.id("ContentPlaceHolder1_datalist_assetmodel_chkoffline_0")).click();
			    waitForSpinner(driver,8);
			    waitforElementToBeVisible(driver.findElement(By.id("ContentPlaceHolder1_datalist_assetmodel_chkrefund_0")),4);
			    driver.findElement(By.id("ContentPlaceHolder1_datalist_assetmodel_chkrefund_0")).click();
			    waitForSpinner(driver,8);
			    waitforElementToBeVisible(driver.findElement(By.id("ContentPlaceHolder1_datalist_assetmodel_chkoffline_3")),4);
			    driver.findElement(By.id("ContentPlaceHolder1_datalist_assetmodel_chkoffline_3")).click();
			    waitForSpinner(driver,8);
			    waitforElementToBeVisible(driver.findElement(By.id("txtFCMDR")),4);
			    driver.findElement(By.id("txtFCMDR")).clear();
			    waitForSpinner(driver,8);
			    waitforElementToBeVisible(driver.findElement(By.id("txtFCMDR")),4);
			    driver.findElement(By.id("txtFCMDR")).sendKeys("2");
			    waitForSpinner(driver,8);
			    data.put("FCMDR","2");
			    waitforElementToBeVisible(driver.findElement(By.id("txtFCMDRFlat")),4);
			    driver.findElement(By.id("txtFCMDRFlat")).clear();
			    waitForSpinner(driver,8);
			    waitforElementToBeVisible(driver.findElement(By.id("txtFCMDRFlat")),4);
			    driver.findElement(By.id("txtFCMDRFlat")).sendKeys("3");
			    waitForSpinner(driver,8);
			    data.put("FCMDRFlat","3");
			   // waitforElementToBeVisible(driver.findElement(By.id("ContentPlaceHolder1_btnsubmit")),4);
			 //   driver.findElement(By.id("ContentPlaceHolder1_btnsubmit")).click();
			    waitForSpinner(driver,8);
			//    waitforElementToBeVisible(driver.findElement(By.xpath("(//button[@type='button'])[3]")),4);
			//    driver.findElement(By.xpath("(//button[@type='button'])[3]")).click();
		
			    waitforElementToBeVisible(driver.findElement(By.id("ContentPlaceHolder1_gvSmartPlanDetails_chkSmartPlanDetails_6")),4);
			    driver.findElement(By.id("ContentPlaceHolder1_gvSmartPlanDetails_chkSmartPlanDetails_6")).click();
			    waitForSpinner(driver,8);
			    driver.findElement(By.id("txtOutSideIssueDCC")).clear();
			    waitForSpinner(driver,8);
			    waitforElementToBeVisible(driver.findElement(By.id("txtOutSideIssueDCC")),4);
			    driver.findElement(By.id("txtOutSideIssueDCC")).sendKeys("2");
			    waitForSpinner(driver,8);
			    data.put("OutSideIssueDCC","2");
			    //  waitforElementToBeVisible(driver.findElement(By.id("ContentPlaceHolder1_btnsubmit")),4);
			 //   driver.findElement(By.id("ContentPlaceHolder1_btnsubmit")).click();
			    waitForSpinner(driver,8);
			 //   waitforElementToBeVisible(driver.findElement(By.xpath("(//button[@type='button'])[3]")),4);
			//    driver.findElement(By.xpath("(//button[@type='button'])[3]")).click();
			 //   waitForSpinner(driver,8);
			   /* driver.findElement(By.id("ContentPlaceHolder1_datalist_assetmodel_txtNoofTablet_3")).clear();
			    waitForSpinner(driver,8);
			    waitforElementToBeVisible(driver.findElement(By.id("ContentPlaceHolder1_datalist_assetmodel_txtNoofTablet_3")),4);
			    driver.findElement(By.id("ContentPlaceHolder1_datalist_assetmodel_txtNoofTablet_3")).sendKeys("1");
			    waitForSpinner(driver,8);*/
			    waitforElementToBeVisible(driver.findElement(By.id("ContentPlaceHolder1_btnsubmit")),4);
			    driver.findElement(By.id("ContentPlaceHolder1_btnsubmit")).click();
			    waitForSpinner(driver,8);
			    waitforElementToBeVisible(driver.findElement(By.xpath("(//button[@type='button'])[2]")),4);
			    // get text ContentPlaceHolder1_lblmsgsuccess
			     MerchantCode=driver.findElement(By.id("ContentPlaceHolder1_lblmsgsuccess")).getText();
			   // driver.findElement(By.xpath("(//button[@type='button'])[2]")).click();
			    waitForSpinner(driver,8);
			  //  waitforElementToBeVisible(driver.findElement(By.xpath("//button[@type='button']")),4);
			    // ERROR: Caught exception [ERROR: Unsupported command [selectWindow | name=xbty52x4qrrdkeyr2blcdgmg02042019014948 | ]]
			    //window switching
			    screenshotname(driver,"onboarding_message");
			    System.out.println("msg from app is :"+MerchantCode);
			    
			    String[] splited = MerchantCode.split(":");
			    String actMerchantCode= splited[1];
			    
			    // setting propery file
			    
			   
	/*		    
			    
			    try {
			    	File f = new File(strFileName);
					if (f.exists()) {
						FileInputStream in = new FileInputStream(f);
						 Properties props = new Properties();
						//System.out.println("load"+in);
						props.load(in);
						long newNum= Long.parseLong(newappNum);
						 long latestNm= newNum +1;
						 newappNum= Long.toString(latestNm);
						 
						 props.setProperty("ApplicationNo", newappNum);
						props.store(new FileOutputStream(strFileName), null);
						
						in.close();
					} else {
						System.out.println("File not found!");
					}
			    } catch (FileNotFoundException ex) {
			        // file does not exist
			    } 
			   
*/
			    driver.findElement(By.xpath("(//button[@type='button'])[2]")).click();
			    
			    test.log(Status.INFO, "Merchant ID Created : "+ actMerchantCode);
			   
			    test.log(Status.PASS, "Merchant added succesfully.");
			   
                System.out.println(men);
			    
			    
			    // add the app number
			    			//    waitForSpinner(driver,8);
			    			    
			    			   driver.switchTo().defaultContent();
			    			   driver.findElement(By.tagName("Button")).click();			   
		    				    
			    			    test.log(Status.INFO, "Checker login");

			    				 driver.findElement(By.id("txtUserID")).clear();
			    				 String checkerusername=prop.getProperty("checkerusername");
			    				 
			    				 String checkerpassword=prop.getProperty("checkerpassword");
			    				 
			    				 
			    				    driver.findElement(By.id("txtUserID")).sendKeys(checkerusername);
			    				    test.log(Status.INFO, "checkerusername is "+checkerusername);
			    				    driver.findElement(By.id("txtPassword")).clear();
			    				    driver.findElement(By.id("txtPassword")).sendKeys(checkerpassword);
			    				   test.log(Status.INFO, "checkerpassword is "+checkerpassword);
			    				    driver.findElement(By.id("btnLogin")).click();
			    				    
			    				    test.log(Status.INFO, "Checker user login successfully");
			    				    
			    				    driver.findElement(By.linkText("Merchant Onboarding")).click();
			    				    test.log(Status.INFO, "Merchant Onboarding link clicked");
			    				    driver.findElement(By.linkText("Dashboard")).click();
			    				    test.log(Status.INFO, "Dashboard link clicked");
			    				    
			    				    switchToFrame();
			    				    waitForPageLoaded();
			    				    Thread.sleep(3000);
			    				    
			    				    
			    				    //---------------------Checker Dashboard-------------------------------------------
			    				    test.log(Status.INFO, "Checker dashboard");
			    				    
			    				    waitforElementToBeVisible(driver.findElement(By.id("ContentPlaceHolder1_txtRequestId")),4);
			    				    driver.findElement(By.id("ContentPlaceHolder1_txtRequestId")).sendKeys(actMerchantCode);
			    				    
			    				    
			    				    waitforElementToBeVisible(driver.findElement(By.id("ContentPlaceHolder1_btn_search")),4).click();
			    				    Thread.sleep(3000);
			    				    screenshotname(driver,"Checker Dashboard");
			    				    
			    				    test.log(Status.INFO, "MID searched successfully on checker dashboard");
			    				    
			    				    driver.findElement(By.id("ContentPlaceHolder1_gv_searchMEdetails_aTag_0")).click();
			    				    waitForPageLoaded();
			    				    
			    				 //   switchToFrame();
			    				    
			    				    JavascriptExecutor js1 = (JavascriptExecutor) driver;  
			    				    js1.executeScript("window.scrollBy(0,1000)");
			    				    
			    				    //waitForSpinner(driver,8);
			    				    //waitforElementToBeVisible(driver.findElement(By.id("ContentPlaceHolder1_drpCheckerStatus")),4).click();
			    				    
			    				    waitForSpinner(driver,8);
			    				    waitforElementToBeVisible( driver.findElement(By.id("ContentPlaceHolder1_drpCheckerStatus")), 4);
			    				    new Select(driver.findElement(By.id("ContentPlaceHolder1_drpCheckerStatus"))).selectByVisibleText("Approve");
			    				    Thread.sleep(3000);
			    				    test.log(Status.INFO, "Action selected : Approve");
			    				    waitForSpinner(driver,8);
			    				    waitforElementToBeVisible(driver.findElement(By.id("ContentPlaceHolder1_btnsubmit")),4).click();
			    				    waitForSpinner(driver,8);		    
			    				    Thread.sleep(3000);
			    				    screenshotname(driver,"Checker message");
			    				    
			    				    waitforElementToBeVisible(driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_divSuccess']/div/div/div[3]/button")),4).click();
			    				    waitForSpinner(driver,8);		    
			    				    
			    				    		    				    
			    				    test.log(Status.PASS, "Merchant Code : "+ actMerchantCode + " Approved successfully");
			    				    data.put("NEWMID",actMerchantCode);
			    				    String newmid = data.get("NEWMID");
			    				    System.out.println("new mid using data:" +newmid);
			    				    
			    				    
			    				    driver.findElement(By.tagName("Button")).click();
			    				    
			    				    //---------------VP login and checker-----------------
			    				    
			    				    driver.close();
			    				    
			    				    
			    				   // driver.findElement(By.id("txtUserID")).clear();
				    				
			    				    
			    				    String url = prop.getProperty("urlchecker");
			    				    String checkrusername=prop.getProperty("checkerusername");
				    			    String checkrpassword=prop.getProperty("checkerpassword");
				    				 
				    			        driver =  new ChromeDriver();
				    			        driver.get(url);
				    				     
				    				    driver.findElement(By.id("txtUserID")).sendKeys(checkerusername);
				    				    test.log(Status.INFO, "checkerusername is "+checkerusername);
				    				    driver.findElement(By.id("txtPassword")).clear();
				    				    driver.findElement(By.id("txtPassword")).sendKeys(checkerpassword);
				    				    test.log(Status.INFO, "checkerpassword is "+checkerpassword);
				    				    driver.findElement(By.id("btnLogin")).click();
				    				    Thread.sleep(5000);
			    				    
			    				    test.log(Status.INFO, "***Institution Approval started***");
			    				    
			    				    driver.switchTo().defaultContent();
			    				    
			    				    waitforElementToBeVisible(driver.findElement(By.xpath("//*[@id='2']/a")),4).click();
			    					    	    				    
                                    test.log(Status.INFO, "Asset Management link clicked successfullu");
			    				    
			    				    driver.findElement(By.linkText("INST MERCHANT APPROVAL [NEW]")).click();
			    				    test.log(Status.INFO, "INST Merchant Approval [New] link clicked successfully");
			    				    
			    				    switchToFrame();
			    				    
			    				    waitforElementToBeVisible(driver.findElement(By.id("txt_mid")),0);
			    				    driver.findElement(By.id("txt_mid")).sendKeys(actMerchantCode);
			    				    
			    				    
			    				    waitforElementToBeVisible(driver.findElement(By.id("ContentPlaceHolder1_drp_Institution")), 1);
			    				    new Select(driver.findElement(By.id("ContentPlaceHolder1_drp_Institution"))).selectByVisibleText("AXIS Bank");
			    				   
			    				    
			    				    waitforElementToBeVisible(driver.findElement(By.id("ContentPlaceHolder1_drp_Approvalstatus")), 1);
			    				    new Select(driver.findElement(By.id("ContentPlaceHolder1_drp_Approvalstatus"))).selectByVisibleText("Pending for Approval");
			    				   				    
			    				    driver.findElement(By.id("ContentPlaceHolder1_btn_search")).click();
			    				    test.log(Status.INFO, "MID search successfully for Institution approval");
			    			
			    				    screenshotname(driver,"Institution approval");
			    				    
			    				    driver.findElement(By.id("ContentPlaceHolder1_gv_searchMEdetails_chkmercode_0")).click();
			    				    
			    				    Thread.sleep(3000);
			    				    driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_btn_Submit']")).click();
			    				    
			    				    test.log(Status.INFO, "Institution approval done successfully");
			    				    waitForSpinner(driver,8);
			    				    Thread.sleep(3000);
			    				    
			    				    String path2= takescreenshot(driver, "success");

			    				    //---------------------database validation-------------------------------------------
			    				    OnboardingInsightDBvalidation(data,test);
			    				    OnboardingKalkulusDBvalidation(data,test);
			    				    
		} catch (Exception e) {
            // TODO Auto-generated catch block
            System.out.println("TestCase failed");
            screenshot(driver,testName);
            test.log(Status.FAIL, "Merchant addition failed.");
            //logger.log(LogStatus.FAIL, logger.addScreenCapture(screenshotPath));
            errorpath = takescreenshot(driver, "MerchantAddition");
            
            //test.fail("MerchantAddition Failed", MediaEntityBuilder.createScreenCaptureFromPath(errorpath).build());
            // test.addScreenCaptureFromPath(errorpath);
            // test.addScreenCaptureFromPath("screenshots\\final.png");

		}
		    
		
		
	}
		 
	
	@AfterMethod
	public void complete(){
		System.out.println("Entered after test method.");
		driver.close();
		
	}

}
