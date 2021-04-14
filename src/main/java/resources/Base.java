package resources;

import java.awt.AWTException;
import java.awt.HeadlessException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Hashtable;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter; 



public class Base {

	public static WebDriver driver;
	public Properties prop;
	 
	ExtentHtmlReporter htmlReporter;
	ExtentReports extent;
	ExtentTest test;
	String men;
	String MELGLNAME;
    String MEDBNAME ;
    String IFSC_CD;
    String QR_IFSCCD;
    String QR_ACNO;
    String MELegalName;
    String MeDBAName;
    String QRACCNO;
    String QRIFSCCODE;
    String BANKIFSCCode;
    String MERmerchantname;
    String MENAME ;
    String MEDBname ;
    String Lename;
    String MIDK;
    String outDCC ; 
    String TenureFee; 
    String TenureFeeone; 
    String TenureFeetwo; 
    String TenureFeethree;
    String creditcard_premium; 
    String creditcard_nonpremium;
    String FCMDR;
    String FCMDRFlat;
    String ComOnus; 
    String CommercialPosOffus; 
    String MID;
    String ICOV; 
	String ICOM ;
	String ICOR ;
	String IDOV;
	String IDOM;
	String IDOR ;
	String ICONR;
	String ICOVISA; 
	String IDONMAST; 
	String DCONUSP ;
	String DCOP ;
    String FERATE3;
	String FERATE6 ;
	String FERATE9 ;
	String FERATE12;
 	String FCHARGE ;
	String FCHARGEPER ;
	String MDFLAT ;
	String FCMDRR ;
	String COMONUSdb ;
	String COMOFFUSdb ;
	String business;
	String MEID;
	String MERID;
	String ADD1;
	String Meadd1;
	String MEC;
	
	public WebDriver initiatebrowser() throws Exception
	{
		
		
		 prop = new Properties();
		//File src = new File("/Users/venkateshwarlu/Documents/workspace/E2Eproject/src/main/java/resources/OR.properties");
		//File src = new File("D://Rohan_Prj//axispankaj//E2Esample-master//E2Esample//E2Eproject//OR.properties");
		 String path= System.getProperty("user.dir");
			
			String orpath= path+"\\OR.properties";
		//File src = new File("D://Rohan_Prj//axispankaj//E2Esample-master//E2Esample//E2Eproject//OR.properties");
		File src = new File(orpath);
		
		FileInputStream fis = new FileInputStream(src);
		prop.load(fis);
		
		String browsername = prop.getProperty("browser");
		System.out.println(browsername);
		if(browsername.equalsIgnoreCase("firefox")){
			
			System.setProperty("webdriver.gecho.driver", "Users/venkateshwarlu/Documents/workspace/E2Eproject/geckodriver");
			driver = new FirefoxDriver();
			driver.get(prop.getProperty("url"));
		}
		
		if(browsername.equalsIgnoreCase("chrome")){
			
			System.setProperty("webdriver.chrome.driver",
					"Drivers\\chromedriver.exe");
			DesiredCapabilities capabilities = new DesiredCapabilities();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("test-type");
	    	 capabilities.setCapability(ChromeOptions.CAPABILITY, options);	
	    	 
	    	 // added by rohan  16/7
	    	 capabilities.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.IGNORE);
			
	    	 driver = new ChromeDriver();
			
			
		}
		driver.get(prop.getProperty("url"));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		return driver;
		
}
	
	public void getScreenshot(String result) throws IOException
	{
		File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime()); 
		
		 String path= System.getProperty("user.dir");
			
		String path1= path+"\\Screenshot\\";
		
		String finalpath= path1+timeStamp+"screenshot.png";
		
		//FileUtils.copyFile(src, new File("D:\\Rohan_Prj\\axispankaj\\E2Esample-master\\E2Esample\\E2Eproject\\Screenshot\\"+timeStamp+result+"screenshot.png"));
		
		FileUtils.copyFile(src, new File(finalpath));
		
	}
	
	
	public static void switchToFrame(){
		driver.switchTo().frame(0);
		
	}
	
	public static void waitForPageLoaded() throws HeadlessException, AWTException,
	IOException {
		ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver)
				.executeScript("return document.readyState").toString()
				.equals("complete");
	}
};
try {
	Thread.sleep(1000);
	WebDriverWait wait = new WebDriverWait(driver, 30);
	wait.until(expectation);
} catch (Throwable error) {
	// takeScreenShotOnError(error.toString());
	Assert.fail("Timeout waiting for Page Load Request to complete.");
}
}
	
	/**
	 * This method wait selenium until visibility of Elements on WebPage.
	 * 
	 * @param by
	 *            : Action to be performed on element (Get it from Object
	 *            repository)
	 * @throws Throwable
	 * 
	 */

	public static boolean waitForVisibilityOfElement(WebElement wb, String locator)
			throws Throwable {
		boolean flag = false;
		WebDriverWait wait = new WebDriverWait(driver, 60);
		try {
			wait.until(ExpectedConditions.visibilityOf(wb));
			flag = true;
			return true;
		} catch (Exception e) {
			Assert.assertTrue(flag, " Element " + locator + " is not visible");
			return false;
		}
	}
	
	public static String takescreenshot(WebDriver driver, String testcasename) throws Throwable
    {
      
           String timeStamp;
           File screenShotName;
           File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
           //The below method will save the screen shot in d drive with name "screenshot.png"
           timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime()); 
           //screenShotName = new File("D:\\TestingDen\\Screenshots\\"+timeStamp+".png");
           
           String path= System.getProperty("user.dir");
                  
                  String path1= path+"\\Screenshot\\";
                  
                  String finalpath= path1+"final.png";
           
           //screenShotName = new File("D:\\Rohan_Prj\\axispankaj\\E2Esample-master\\E2Esample\\E2Eproject\\Screenshot\\"+timeStamp+".png");
           screenShotName = new File(finalpath);
           FileUtils.copyFile(scrFile, screenShotName);
           
           return finalpath;
    
    }

	
	public static void waitForAjax(WebDriver driver) {
	    new WebDriverWait(driver, 180).until(new ExpectedCondition<Boolean>() {
	        public Boolean apply(WebDriver driver) {
	            JavascriptExecutor js = (JavascriptExecutor) driver;
	            return (Boolean) js.executeScript("return jQuery.active == 0");
	        }
	    });
	}
	
	public static void waitForSpinner(WebDriver driver, int timeout) throws Throwable {
		// added try catch
		try{
			
			WebDriverWait wait = new WebDriverWait(driver,10);

			wait.until(new ExpectedCondition<Boolean>() {
			    public Boolean apply(WebDriver driver) {
			        WebElement button = driver.findElement(By.xpath("//*[@id='divloader']"));
			        String enabled = button.getAttribute("style");
			        if(enabled.contains("none")) 
			            return true;
			        else
			            return false;
			    }
			});
			
		}catch(Exception e){
			
		}
		
		
		
	}
	
	public static WebElement waitforElementToBeVisible(WebElement element,int timeout) {
	    return new WebDriverWait(driver,timeout).until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOf(element)));
	}
	
	public static void scrollUp() throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		
		
		js.executeScript("window.scrollTo(0, -document.body.scrollHeight);");
		// 
	}
	
	public static void scrollDown() throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		//js.executeScript(javaScript, mo);
		//js.executeScript("arguments[0].scrollIntoView(true);",element);
		
	//	((JavascriptExecutor) driver).executeScript("window.scrollBy(0,300)");+
		js.executeScript("scroll(0, 250);");
		// 
	}
	
	public static void scrolltoElement(WebElement element) throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		
		
		js.executeScript("arguments[0].scrollIntoView(true);", element);
		// 
	}
	//D:\Rohan_Prj\axispankaj\E2Esample-master\E2Esample\E2Eproject\Screenshot
	public static void screenshot(WebDriver driver, String testcasename) throws Throwable
	{
	  
		String timeStamp;
		File screenShotName;
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		//The below method will save the screen shot in d drive with name "screenshot.png"
		timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime()); 
		//screenShotName = new File("D:\\TestingDen\\Screenshots\\"+timeStamp+".png");
		
		 String path= System.getProperty("user.dir");
			
			String path1= path+"\\Screenshot\\";
			
			String finalpath= path1+timeStamp+testcasename+".png";
		
		//screenShotName = new File("D:\\Rohan_Prj\\axispankaj\\E2Esample-master\\E2Esample\\E2Eproject\\Screenshot\\"+timeStamp+".png");
		screenShotName = new File(finalpath);
		FileUtils.copyFile(scrFile, screenShotName);
		
	
	}
	
	public static void screenshotname(WebDriver driver, String name) throws Throwable
	{
		String timeStamp;
		File screenShotName;
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		//The below method will save the screen shot in d drive with name "screenshot.png"
		timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime()); 
		//screenShotName = new File("D:\\TestingDen\\Screenshots\\"+timeStamp+".png");
		//D:\all automation\AxisRevamp\Results\Screenshots
		
		String path= System.getProperty("user.dir");
		
		String path1= path+"\\Screenshot\\";
		
		String finalpath= path1+timeStamp+name+".png";
	
	//screenShotName = new File("D:\\Rohan_Prj\\axispankaj\\E2Esample-master\\E2Esample\\E2Eproject\\Screenshot\\"+timeStamp+".png");
	screenShotName= new File(finalpath);
		
		
		
		
		
		//screenShotName = new File("D:\\Rohan_Prj\\axispankaj\\E2Esample-master\\E2Esample\\E2Eproject\\Screenshot\\"+name+timeStamp+".png");
		//screenShotName = new File(screenShotName);
		FileUtils.copyFile(scrFile, screenShotName);
		
	
	}
	
	public static void copyFile() throws IOException{ 
		File from1 = new File("programming.txt"); 
		File to1 = new File("java6");
		
		String token= new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());;
		
		String path= System.getProperty("user.dir");
		
		String from2= path+"\\test-output\\AxisReport.html";
		String to2=path+"\\Results\\"+token+"TestResult.html";
		
		File actfrom= new File(from2);
		File actto= new File(to2);
		
		

		System.out.println("Copying file to another directory using Apache commons IO"); 
		 

		 FileUtils.copyFile(actfrom, actto);
		
		}

	
	public void OnboardingInsightDBvalidation(Hashtable <String,String > data, ExtentTest test2) throws SQLException
	{	
		Properties prop = new Properties();
		
		 MELegalName = data.get("MELegalName");
		 MeDBAName = data.get("MeDBAName");
		 QRACCNO = data.get("QRACCNO");
		 QRIFSCCODE = data.get("QRIFSCCODE");
		 BANKIFSCCode = data.get("BANKIFSCCode");
		 ADD1=data.get("Address1");
		 
		 
  
		try {
		    prop.load(new FileInputStream("OR.properties"));
		}
		catch (IOException e) {
		    e.printStackTrace();
		}
		
		ResultSet rs = null ;
		ResultSet rs1 = null ;
		ResultSet rs2 = null ;
		ResultSet rs3 = null ;
		int Applicationid = 5;
		String Installationmaster= null;
		String Username=prop.getProperty("dbusername");
		String Password= prop.getProperty("dbpassword");
		//String mid = prop.getProperty("MID");
		String mid = data.get("NEWMID");
		String INSTurl = prop.getProperty("INSIGHTDBURL");
		
		String query = "SELECT * FROM MERCHANTPROFILE WHERE MERCHANTCODE ='"+mid+ "'"; 
		String MEMASTER = "SELECT * FROM MERCHANTMASTER WHERE MERCHANTCODE ='"+mid+ "'";
		System.out.println("query1 is "+query);
		System.out.println("query2 is "+MEMASTER);
		
		try
		{
            Class.forName("oracle.jdbc.driver.OracleDriver");
        }
		catch (ClassNotFoundException e) {
            System.out.println("Where is your Oracle JDBC Driver?");
            e.printStackTrace();
          }
		
		System.out.println("Oracle JDBC Driver Registered!");

        Connection con = null;
        con = null;
        
        try
        {
        	 con = DriverManager.getConnection(INSTurl,Username,Password);
        	 
        		if (con != null) {
                    System.out.println("You made it, take control your database now!");
                	}
                	else {
                    System.out.println("Failed to make connection!");
                	}
        		Statement stmt = con.createStatement();

            	rs= stmt.executeQuery(query);
        	
        }
        catch(SQLException e)
        {
        	System.out.println("Connection Failed! Check output console");
        }
                 System.out.println("Op is:"+rs);
                 
                 while(rs.next()) 
                 {
                	 
                	  MID = rs.getString("MERCHANTCODE");
            	     String INSTID = rs.getString("INSTITUTIONID");	
            	     String MEPROFID  = rs.getString("MERCHANTPROFILEID");
            	      MELGLNAME  = rs.getString("MERLAGALNAME");
            	      MEDBNAME = rs.getString("MERDBANAME");
            	     String MEACNO = rs.getString("MEACNO");
            	     String MEDCC = rs.getString("DCCMID");
            	      QR_ACNO = rs.getString("QR_ACCNO");
            	      QR_IFSCCD = rs.getString("QR_IFSCCODE");
            	      IFSC_CD = rs.getString("IFSC_CODE");
            	     String EMIFG = rs.getString("EMIFLAG");
            	     String EMIGRPCD = rs.getString("EMIGROUPCODE");
            	     String EMIPG1 = rs.getString("EMIPRG1");
            	     String EMIPG2 = rs.getString("EMIPRG2");
            	     String EMIPG3 = rs.getString("EMIPRG3");
            	     String EMIPG4 = rs.getString("EMIPRG4");
            	     String EMIPG5 = rs.getString("EMIPRG5");
            	     String EMIPG6 = rs.getString("EMIPRG6");
            	     String MEAP = rs.getString("MEAPP");
            	     String MEPOR = rs.getString("MEPORTAL");
            	     
            	     //Print values from database.
            	     System.out.println("MID is "+MID);
            	     System.out.println("INSTID is "+INSTID);
            	     System.out.println("MEPROID "+MEPROFID);
            	     System.out.println("MELEGALNAME "+MELGLNAME);
            	     System.out.println("MEDBNAME "+MEDBNAME);
            	     System.out.println("MEACNO "+MEACNO);
            	     System.out.println("MEDCC "+MEDCC);
            	     System.out.println("QR_ACNO "+QR_ACNO);
            	     System.out.println("QR_IFSCCD "+QR_IFSCCD);
            	     System.out.println("IFSC_CD "+IFSC_CD);
            	     System.out.println("EMIFG "+EMIFG);
            	     System.out.println("EMIGRPCD "+EMIGRPCD);
            	     System.out.println("EMIPG1 "+EMIPG1);
            	     System.out.println("EMIPG2 "+EMIPG2);
            	     System.out.println("EMIPG3 "+EMIPG3);
            	     System.out.println("EMIPG4 "+EMIPG4);
            	     System.out.println("EMIPG5 "+EMIPG5);
            	     System.out.println("EMIPG6 "+EMIPG6);
            	     System.out.println("MEAP "+MEAP);
            	     //System.out.println("MEPOR "+MEPOR);
                	
            	}
                 
                 if (MELegalName.equalsIgnoreCase(MELGLNAME) && MeDBAName.equalsIgnoreCase(MEDBNAME) && QRACCNO.equalsIgnoreCase(QR_ACNO)&&
        				 QRIFSCCODE.equalsIgnoreCase(QR_IFSCCD) && BANKIFSCCode.equalsIgnoreCase(IFSC_CD))
                 {
                	 System.out.println("Merchant name from DB:-" + MELGLNAME);
        	    	 System.out.println("Merchant name from Screen:-" + MELegalName);
        	    	 
        	    	 System.out.println("Merchant name from DB:-" + MEDBNAME);
        	    	 System.out.println("Merchant name from Screen:-" + MeDBAName);
        	    	 
        	    	 System.out.println("Merchant name from DB:-" + QR_ACNO);
        	    	 System.out.println("Merchant name from Screen:-" + QRACCNO);
	
        	    	 System.out.println("Merchant name from DB:-" + QR_IFSCCD);
        	    	 System.out.println("Merchant name from Screen:-" + QRIFSCCODE);
        	    	 
        	    	 System.out.println("Merchant name from DB:-" + IFSC_CD);
        	    	 System.out.println("Merchant name from Screen:-" + BANKIFSCCode);
                	 
                 }
                 test2.log(Status.INFO, "Validation complete for MERCHANTPROFILE table");
                 
                 System.out.println("One table complect");
                 
                 if(rs!=null){
                 	try {
         				rs.close();
         			} catch (Exception e) {
         				// TODO Auto-generated catch block
         				e.printStackTrace();
         			}
                 }
                 
          
                 
                 System.out.println("Second table Start");
                
                 Statement stmt1 = con.createStatement();
                 rs1= stmt1.executeQuery(MEMASTER);
                 
                 while(rs1.next())
                 {
                	  MEID = rs1.getString("MERCHANTID");
               	      MENAME = rs1.getString("MERCHANTNAME");
               	      business = rs1.getString("BUSINESSAS");
               	   Meadd1 = rs1.getString("MERADDRESS1");
               	     String MEDC = rs1.getString("DCCMID");
               	     String MEVPA = rs1.getString("VPA");
               	     String UPIFG = rs1.getString("UPIFLAG");
               	     String EMIFLG = rs1.getString("EMIFLAG");
               	     String EMIGRPCOD = rs1.getString("EMIGROUPCODE");
               	     String EMPG1 = rs1.getString("EMIPRG1");
               	     String EMPG2 = rs1.getString("EMIPRG2");
               	     String EMPG3 = rs1.getString("EMIPRG3");
               	     String EMPG4 = rs1.getString("EMIPRG4");
               	     String EMPG5 = rs1.getString("EMIPRG5");
               	     String EMPG6 = rs1.getString("EMIPRG6");
               	     String MAP =rs1.getString("MEAPP");
               	     String MEPORT = rs1.getString("MEPORTAL");
               	     String IC = rs1.getString("ICA");
               	     
               	     System.out.println("MERCHANTID is:- "+ MEID);
            	     Installationmaster = "SELECT * FROM Installationreqmaster WHERE MERCHANTID = '"+MEID+"'";
            	     System.out.println("query3 is "+Installationmaster);
                 }
                 
                 if (MELegalName.equalsIgnoreCase(MELGLNAME) && business.equalsIgnoreCase(MEDBNAME))
                 {
                	 System.out.println("Merchant name from DB:-" + business);
        	    	 System.out.println("Merchant name from Screen:-" + MeDBAName);
                }
                 
                 test2.log(Status.INFO, "Validation complete for MerchantMaster table");
                 
                 System.out.println("Second table complect");
                 
                 if(rs1!=null){
                  	try {
          				rs1.close();
          			} catch (Exception e) {
          				// TODO Auto-generated catch block
          				e.printStackTrace();
          			}
                  }
                 
                 System.out.println("Third table Start");
                
                 Statement stmt2 = con.createStatement();
                 rs2= stmt2.executeQuery(Installationmaster);
                 
                 while (rs2.next())
                 {
                	 String INSTAREQID = rs2.getString("INSTALLATIONREQID");
                     String REQID = rs2.getString("REQUESTID");
                      MERID = rs2.getString("MERCHANTID");
                     String MVISA = rs2.getString("MVISAPAN");
                     String QR_CD = rs2.getString("QR_CODE");
                     String WALLENAME = rs2.getString("WALLETNAME");
                     String ECHARGESP = rs2.getString("ECHARGESLIP");
                     String PN = rs2.getString("PLAN");
                     String FREECHGE = rs2.getString("FREECHARGE");
                     String testEMI = rs2.getString("EMI");
                     String testDCC = rs2.getString("DCC");
                     String testDCCMID = rs2.getString("DCCMID");
                     
                     System.out.println("REQUESTID is:- "+ INSTAREQID);
                     
                     String Installid = "SELECT * FROM INSTALLATIONREQDETAILS WHERE INSTALLATIONREQID = '"+INSTAREQID+"' and APPLICATIONID ="+Applicationid+ "" ;
                     
                     Statement stmt3 = con.createStatement();
                     rs3= stmt3.executeQuery(Installid);
                     
                     while (rs3.next())
                     {
                    	 String INSTAREQIDD = rs3.getString("INSTALLATIONREQID");
                    	 String CHARGESLIP = rs3.getString("ECHARGESLIP");
                    	 
                    	 System.out.println("REQUESTID is:- "+ INSTAREQIDD);
                    	 System.out.println("CHARGESLIP is:- "+ CHARGESLIP);
                     }
                }
                 
                 if (MERID.equalsIgnoreCase(MEID)) 
                 {
        	    	 
        	    	 System.out.println("Merchant ID:-" + MERID);
        	    	 
        	    	 System.out.println("OnboardingINSIGHT Validations successfully done..");
        	     }
                 else {
                	 
                	 System.out.println("Onboarding Validations Fail.");
			        	}
                 
                
                 test2.log(Status.INFO, "Validation complete for Installationreqmaster table");
                 
                 test2.log(Status.PASS, "DB Validation for Insight successfully done");
                 
             }
	
public void OnboardingKalkulusDBvalidation(Hashtable <String,String > data, ExtentTest test2) throws SQLException
{
	         outDCC =data.get("OutSideIssueDCC");	
	         creditcard_premium =data.get("CCP_OnUS");
	         creditcard_nonpremium =data.get("CCP_OnUS");
	         
	         TenureFee =data.get("TenureFee_0");
	         TenureFeeone =data.get("TenureFee_1");
	         TenureFeetwo =data.get("TenureFee_2");
	         TenureFeethree =data.get("TenureFee_3");
	          
	         FCMDR=data.get("FCMDR");
	         FCMDRFlat=data.get("FCMDRFlat");
	         
	         ComOnus =data.get("CommercialPosOnus");
	         CommercialPosOffus =data.get("CommercialPosOffus");
	
		Properties prop = new Properties();

		try {
		    prop.load(new FileInputStream("OR.properties"));
		}
		catch (IOException e) {
		    e.printStackTrace();
		}
		
		ResultSet rs = null ;
		ResultSet rs1 = null ;
		String kalUsername=prop.getProperty("dbkalusername");
		String kalPassword= prop.getProperty("dbkalpassword");
		//String mid = prop.getProperty("MID");
		String mid = data.get("NEWMID");
		String KalkuURL = prop.getProperty("KALDBURL");
		
		
		
		String kalquery ="select * from ACQ_MERCHANT_MASTER where TERMINAL_ID ='"+mid+"'";
		String RAFkalquery ="select * from RAF_MERCHANT_MSFMASTER where MERCHANT_CODE ='"+mid+"'";
		
		
		
		System.out.println("query is "+kalquery);
		
		
	
		try {

            Class.forName("oracle.jdbc.driver.OracleDriver");

         } 
		catch (ClassNotFoundException e) {

            System.out.println("Where is your Oracle JDBC Driver?");
            e.printStackTrace();
           
          }
		
		System.out.println("Oracle JDBC Driver Registered!");

       
        Connection con1 = null;
      
        con1 = null;
        try {
        	 
        	
         
            con1 = DriverManager.getConnection(KalkuURL,kalUsername,kalPassword);
            
            	if ( con1 != null) {
                System.out.println("You made it, take control your database now!");
            	}
            	else {
                System.out.println("Failed to make connection!");
            	}
       
            	//Statement stmt = con1.createStatement();
               //rs= stmt.executeQuery(kalquery);
            	
            	Statement stmt4 = con1.createStatement();
                rs1= stmt4.executeQuery(RAFkalquery);
        	} 
        catch (SQLException e) {

                System.out.println("Connection Failed! Check output console");

            }
        //System.out.println("Op is:"+rs);
        System.out.println("Op is:"+rs1);
        
   /*     while(rs.next())
   	 {
       	  MIDK = rs.getString("MERCHANT_CODE");
   	     String EMALK = rs.getString("EMAIL");	
   	      MEDBname  = rs.getString("DBANAME");
   	     Lename = rs.getString("LEGALNAME");
   	     String ADD1 = rs.getString("ADDRESS_1");
   	     String ADD2 = rs.getString("ADDRESS_2");
   	     String Cityn = rs.getString("CITY");
   	     String State = rs.getString("STATE");
   	     String pin = rs.getString("PIN");
   	     String PCC = rs.getString("PAYMENT_CURR_CODE");
   	     String TCC = rs.getString("TRANS_CURR_CODE");
   	     String EFLAG = rs.getString("EMIME_FLAG");
   	  
   	  
   	  

   	     System.out.println("MID is "+MIDK);
   	     System.out.println("I "+EMALK);
   	     System.out.println("M "+MEDBname);
   	     System.out.println("M "+Lename);
   	     System.out.println("M "+ADD1);
   	     System.out.println("M "+ADD2);
   	     System.out.println("M "+Cityn);
   	     System.out.println("M "+State);
   	     System.out.println("M "+pin);
   	     System.out.println("M "+PCC);
   	     System.out.println("M "+TCC);
   	     System.out.println("M "+EFLAG);
   	     
   	  System.out.println("First table Complected");
   	 }
        
        if(rs!=null){
        	try {
				rs.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	
        }
     */  
        System.out.println("SEcond table for kalkulus Start");
       
             
        
        while(rs1.next())
        {
        	String MMI = rs1.getString("MSF_ME_ID");
        	 MEC = rs1.getString("MERCHANT_CODE");
        	String DDMOU1 = rs1.getString("DOM_DEBIT_MSF_OFFUS_UPI_1");
        	String DDMOU2 = rs1.getString("DOM_DEBIT_MSF_OFFUS_UPI_2");
        	String DDMONU1 = rs1.getString("DOM_DEBIT_MSF_ONUS_UPI_1");
        	String DDMONU2 = rs1.getString("DOM_DEBIT_MSF_ONUS_UPI_2");
        	String DCOV = rs1.getString("DOM_CREDIT_OFFUS_VISA");
        	String DCOM = rs1.getString("DOM_CREDIT_OFFUS_MASTER");
        	String DCOR = rs1.getString("DOM_CREDIT_OFFUS_RUPAY");
        	 ICOV = rs1.getString("INTL_CREDIT_OFFUS_VISA");
        	 ICOM = rs1.getString("INTL_CREDIT_OFFUS_MASTER");
        	 ICOR = rs1.getString("INTL_CREDIT_OFFUS_RUPAY");
        	 IDOV = rs1.getString("INTL_DEBIT_OFFUS_VISA");
        	 IDOM = rs1.getString("INTL_DEBIT_OFFUS_MASTER");
        	 IDOR = rs1.getString("INTL_DEBIT_OFFUS_RUPAY");
        	String INRR = rs1.getString("INR_RUPAY");
        	String INRO = rs1.getString("INR_ONUS");
        	String DCCV = rs1.getString("DCC_VISA");
        	String DCCM = rs1.getString("DCC_MASTER");
        	String DCCO = rs1.getString("DCC_ONUS");
        	 ICONR = rs1.getString("INTL_CREDIT_ONUS_RUPAY");
        	 ICOVISA = rs1.getString("INTL_CREDIT_ONUS_VISA");
        	 IDONMAST = rs1.getString("INTL_DEBIT_ONUS_MASTER");
        	 DCONUSP = rs1.getString("DOM_CREDIT_ONUS_PREMIUM");
        	 DCOP = rs1.getString("DOM_CREDIT_OFFUS_PREMIUM");
        	String POSONR = rs1.getString("POSONUSRATE");
        	String POSOFR = rs1.getString("POSOFFUSRATE");
        	String PSCAP = rs1.getString("POSONUSCAP");
        	String PFUSCAP = rs1.getString("POSOFFUSCAP");
        	String QRATE = rs1.getString("QRONUSRATE");
        	String QRORATE = rs1.getString("QROFFUSRATE");
        	String PQONSCAP = rs1.getString("PQRONUSCAP");
        	String PQOFFSCAP = rs1.getString("PQROFFUSCAP");
        	 FERATE3 = rs1.getString("FEERATE3");
        	 FERATE6 = rs1.getString("FEERATE6");
        	 FERATE9 = rs1.getString("FEERATE9");
        	 FERATE12 = rs1.getString("FEERATE12");
         	String FERATE18 = rs1.getString("FEERATE18");
         	String FERATE24 = rs1.getString("FEERATE24");
         	String FERATE36 = rs1.getString("FEERATE36");
        	 FCHARGE = rs1.getString("FREECHARGE_FLAT");
        	 FCHARGEPER = rs1.getString("FREECHARGE_PERCENT");
        	 MDFLAT = rs1.getString("FCMDRFLAT");
        	 FCMDRR = rs1.getString("FCMDR");
        	 COMONUSdb = rs1.getString("COMMERCIALPOSONUS");
        	 COMOFFUSdb = rs1.getString("COMMERCIALPOSOFFUS");
        }
        
        			//this is for both table of kalkulus.   jitu 21-05-2019 
          /* if(MIDK.equalsIgnoreCase(MID) && outDCC.equalsIgnoreCase(ICOV) && outDCC.equalsIgnoreCase(ICOM) && outDCC.equalsIgnoreCase(ICOR) && outDCC.equalsIgnoreCase(IDOV) && outDCC.equalsIgnoreCase(IDOM) 
       		 && outDCC.equalsIgnoreCase(IDOR)  && outDCC.equalsIgnoreCase(ICONR) && outDCC.equalsIgnoreCase(ICOVISA)&& outDCC.equalsIgnoreCase(IDONMAST) 
       		 && creditcard_premium.equalsIgnoreCase(DCONUSP) && creditcard_premium.equalsIgnoreCase(DCOP) 
       		 && TenureFee.equalsIgnoreCase(FERATE3) && TenureFeeone.equalsIgnoreCase(FERATE6) && TenureFeetwo.equalsIgnoreCase(FERATE9) && TenureFeethree.equalsIgnoreCase(FERATE12)
       		 && FCMDRFlat.equalsIgnoreCase(FCHARGE) && FCMDRR.equalsIgnoreCase(FCHARGEPER) && FCMDRFlat.equalsIgnoreCase(MDFLAT) && FCMDR.equalsIgnoreCase(FCMDR)
       		 && ComOnus.equalsIgnoreCase(COMONUSdb) && CommercialPosOffus.equalsIgnoreCase(COMOFFUSdb))
        */
        
		 if (mid.equalsIgnoreCase(MEC)&& ComOnus.equalsIgnoreCase(COMONUSdb) && CommercialPosOffus.equalsIgnoreCase(COMOFFUSdb))
        {
        	 System.out.println("OnboardingKalkulus Validations successfully done.");
        	 
        	 test2.log(Status.INFO, "Merchant code value from Screen:"+mid);
        	 test2.log(Status.INFO, "Merchant code value from RAF_MERCHANT_MSFMASTER:"+MEC);
        	 
        	 test2.log(Status.INFO, "COMMERCIAL POS ONUS value from Screen:"+ComOnus);
        	 test2.log(Status.INFO, "COMMERCIAL POS ONUS value from RAF_MERCHANT_MSFMASTER:"+COMONUSdb);
        	 
        	 test2.log(Status.INFO, "COMMERCIAL POS OFFUS value from Screen:"+CommercialPosOffus);
        	 test2.log(Status.INFO, "COMMERCIAL POS OFFUS value from RAF_MERCHANT_MSFMASTER:"+COMOFFUSdb);
        	 
        	 
        	 test2.log(Status.INFO, "Validation complete for RAF_MERCHANT_MSFMASTER table");
             test2.log(Status.PASS, "DB Validation for kalkulus successfully done");
        	 
	    }
        else
        {
        	System.out.println("OnboardingKalkulus Validations Failed.");
        	test2.log(Status.INFO, "Validation Not complete for RAF_MERCHANT_MSFMASTER table");
            test2.log(Status.PASS, "DB Validation for kalkulus Unsuccessfully done");
		}
		 
		 
        
        if(rs1!=null){
        	try {
				rs1.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	
        }
    

	}
}

