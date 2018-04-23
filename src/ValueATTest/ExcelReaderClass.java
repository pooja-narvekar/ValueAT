package ValueATTest;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeTest;

import ValueAT.FundOperations;
import ValueAT.FundsInfo;

public class ExcelReaderClass {

	static WebDriver driver;
	public static Logger logger;
	FundOperations fundOperations=null;
//	public static ArrayList<String> fundsName;
	public static ArrayList<String> clientsList;
	public static ArrayList<FundsInfo> fundsInfo;
	static String fromDate;
	static String toDate;
	
	public static String currentFundsURl;
	
	//String filepath="E:\\Selenuim Workspace\\ValueAT\\Excel\\ValueATTestData.xlsx";
	
	@BeforeTest
	public static WebDriver getTestDriver() {
		return driver;
	}
	
	public void setUp() throws IOException, InterruptedException {
		 logger=Logger.getLogger("Automation Test");
		// configure log4j properties file
	       PropertyConfigurator.configure("Log4j.properties");
	FileInputStream myInput = new FileInputStream(System.getProperty("user.dir")+"\\ValueATTestData.xlsx");
	 XSSFWorkbook workbook = new XSSFWorkbook(myInput);
	 XSSFSheet spreadsheet = workbook.getSheetAt(0);
	 clientsList=new ArrayList<String>();
//	 fundsName=new ArrayList<String>();
	 fundsInfo=new ArrayList<FundsInfo>();
	 System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"\\exe\\geckodriver.exe");
	 driver=new FirefoxDriver();
	 
	 driver.manage().window().maximize();
	 driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	 //find all the funds with run column as "1"
	 for(int rownum=1; rownum<=spreadsheet.getLastRowNum(); rownum++) {
		 
		 //System.out.println(spreadsheet.getRow(rownum).getCell(0).getNumericCellValue());
		 if(spreadsheet.getRow(rownum).getCell(0).getNumericCellValue()==1) {
			 XSSFSheet spreadsheet1= workbook.getSheet(spreadsheet.getRow(rownum).getCell(1).getStringCellValue());
			 
			 for(int innerRownum=1;innerRownum<=spreadsheet1.getLastRowNum();innerRownum++) {
				 //System.out.println(spreadsheet1.getRow(innerRownum).getCell(1).getStringCellValue());
				 if(spreadsheet1.getRow(innerRownum).getCell(0).getNumericCellValue()==1) {
					// System.out.println(spreadsheet1.getRow(innerRownum).getCell(0).getNumericCellValue()
							// +"\t"+spreadsheet1.getRow(innerRownum).getCell(1).getStringCellValue());
					 FundsInfo fundInfo=new FundsInfo();
					 
					 fundInfo.setFromDate(spreadsheet1.getRow(innerRownum).getCell(2).getStringCellValue());
					 fundInfo.setToDate(spreadsheet1.getRow(innerRownum).getCell(3).getStringCellValue());
					 fundInfo.setFundsName(spreadsheet1.getRow(innerRownum).getCell(1).getStringCellValue());
					 fundInfo.setFundooUrl(spreadsheet1.getRow(innerRownum).getCell(4).getStringCellValue());
//					 fundsName.add(spreadsheet1.getRow(innerRownum).getCell(1).getStringCellValue());
					 // fromDate=spreadsheet1.getRow(innerRownum).getCell(2).getStringCellValue();
					 fundsInfo.add(fundInfo);
					  /*System.out.println("From date before setting "+fromDate);
					  toDate=spreadsheet1.getRow(innerRownum).getCell(3).getStringCellValue();
					  System.out.println("To date before setting "+toDate);*/
				 }
				 				
			 }
			 /*for(String s:fundsName){  
				 System.out.println(fundsName.size());  
				}  */
		 }
		 else {
			 //System.out.println("No data found");
		 }
		// System.out.println(fundsName.size());
	 }
	 
	 //System.out.println(fundsName.size());
	 //find all the clients with value as "1"
	XSSFSheet spreadsheet2=workbook.getSheetAt(0);
	 for(int rownum=1; rownum<=spreadsheet.getLastRowNum(); rownum++) {
		 ArrayList<String> clientName=new ArrayList<String>();
		 if(spreadsheet.getRow(rownum).getCell(0).getNumericCellValue()==1) {
			 clientName.add(spreadsheet.getRow(rownum).getCell(1).getStringCellValue());
			 //System.out.println(spreadsheet.getRow(rownum).getCell(1).getStringCellValue());
		 }
		 
		 for(String s1:clientName){  
			 System.out.println("Clients available in excel sheet are "+s1);  
			} 
	 }
	// System.out.println(fundsName.size());
	 
	 //find username and password with value as "1" and store it in the map for future reference
	 XSSFSheet spreadsheet3=workbook.getSheetAt(0);
	 for(int rownum=1; rownum<=spreadsheet.getLastRowNum(); rownum++) {
		 Map<String, String> map = new IdentityHashMap<String, String>();
		 if(spreadsheet.getRow(rownum).getCell(0).getNumericCellValue()==1) {
			 map.put(spreadsheet.getRow(rownum).getCell(3).getStringCellValue(),spreadsheet.getRow(rownum).getCell(4).getStringCellValue() );
			 //clientName.add(spreadsheet.getRow(rownum).getCell(1).getStringCellValue());
			 //System.out.println(spreadsheet.getRow(rownum).getCell(1).getStringCellValue());
			 
			 /*System.setProperty("webdriver.gecko.driver", "E:\\Eclipse Workspace\\geckodriver.exe");
			 driver=new FirefoxDriver();
			 
			 driver.manage().window().maximize();
			 driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);*/
			 currentFundsURl=spreadsheet.getRow(rownum).getCell(2).getStringCellValue();
			 driver.get(currentFundsURl);
			
			 driver.findElement(By.xpath("//input[contains(@id,'txtUserName')]")).sendKeys(spreadsheet.getRow(rownum).getCell(3).getStringCellValue());
			 driver.findElement(By.xpath("//input[contains(@id,'txtPassword')]")).sendKeys(spreadsheet.getRow(rownum).getCell(4).getStringCellValue());
			 String captcha;
			 Scanner src=new Scanner(System.in);
			 captcha=src.next();
			 driver.findElement(By.id("txtImage")).sendKeys(captcha);
			 driver.findElement(By.id("btnLogin")).click();
			 if(driver.findElement(By.id("btnok")).isDisplayed()) {
				 driver.findElement(By.id("btnok")).click();	 
			 }
			 else {
				 System.out.println("Popup not displayed");
			 }
			 
		 }
 }
		 
	 logger.info("Browser Opened");
	 logger.info("URL Loaded");
	 logger.info("Total number of funds available in excel sheet is " +fundsInfo.size());
		
	 }	 	
	// System.out.println("Total number of funds available in excel sheet is " +fundsName.size());
}
