package ValueATTest;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import ValueAT.FundOperations;
import ValueATVerifier.MethodsVerifier;

public class TestClass {
	ExcelReaderClass excelReaderClass;
	FundOperations fundOperations;
	WebDriver driver;
	private static Logger logger = LogManager.getLogger(TestClass.class);
	 ExtentReports extent;
	 ExtentTest logger1;
	 MethodsVerifier verifier;
	 double diff;
	 double difference;
	 double battingAvg;
	 
	@BeforeClass
	public void setUp() throws Exception{
		extent = new ExtentReports (System.getProperty("user.dir") +"/test-output/AutomationReport.html", true);
		extent
		                .addSystemInfo("Host Name", "Valuefy Solutions")
		                .addSystemInfo("Environment", "Automation Testing")
		                .addSystemInfo("User Name", "Pooja Narvekar");
		                //loading the external xml file (i.e., extent-config.xml) which was placed under the base directory
		                //You could find the xml file below. Create xml file in your project and copy past the code mentioned below
		                extent.loadConfig(new File(System.getProperty("user.dir") +"\\extent-config.xml"));
		                
		excelReaderClass=new ExcelReaderClass();
		excelReaderClass.setUp();
		Thread.sleep(10000);
		
		driver=ExcelReaderClass.getTestDriver();
		fundOperations=new FundOperations(driver);
		}
	
	@Test
	public void BirlaFunds() throws Exception {
		//Test case for Net and Gross Return
		logger1 = extent.startTest("Test Cases of Birla Funds");
	      for (int counter = 0; counter < excelReaderClass.fundsInfo.size(); counter++) { 	
	    	  logger1.log(LogStatus.INFO, "Test started for fund : " + excelReaderClass.fundsInfo.get(counter).getFundsName());
	    	  fundOperations.selectFund(excelReaderClass.fundsInfo.get(counter).getFundsName());
	    	  excelReaderClass.logger.info("Test started for fund: " +excelReaderClass.fundsInfo.get(counter).getFundsName());
	    	  //extent.startTest(Test started for fund: " +excelReaderClass.fundsInfo.get(counter).getFundsName());
	    	  //System.out.println("Test started for fund: " +excelReaderClass.fundsInfo.get(counter).getFundsName());
	    	  Thread.sleep(5000);
	    	  //for(int i=0; i<dropdownSize;i++){
	    	  fundOperations.selectBenchMark();
	    	  fundOperations.setCalender(ExcelReaderClass.fundsInfo.get(counter).getFromDate(), ExcelReaderClass.fundsInfo.get(counter).getToDate());
	    	 // System.out.println("From date after setting "+ExcelReaderClass.fromDate);
	    	  //System.out.println("To date after setting "+ExcelReaderClass.toDate);
	    	  fundOperations.process();
	    	  Thread.sleep(5000);
	    	  verifier=new MethodsVerifier();
	    	  
	    	  
	    	  diff=fundOperations.netAndGrossValueComparisonMethod();
	    	  if(verifier.verifyGrossAndNet(diff)) {
	        	  logger1.log(LogStatus.PASS, "NET RET and GROSS RET Test case for fund "+excelReaderClass.fundsInfo.get(counter).getFundsName());
	        	  fundOperations.commonMethod();
	        	  logger1.log(LogStatus.INFO, "Net return values is : "+fundOperations.net_ret_value + "&" + " Gross return value is : " +fundOperations.gross_ret_value);
	    	  }
	          else {
	        	  logger1.log(LogStatus.FAIL, "NET RET and GROSS RET Test case for fund "+excelReaderClass.fundsInfo.get(counter).getFundsName() + "failed");
	        	  logger1.log(LogStatus.INFO, "Net return values is : "+fundOperations.net_ret_value + "&" + " Gross return value is : " +fundOperations.gross_ret_value);
	          }
	          
	    	  
	          //Test case for Net Alpha and Net Index Ret
	          difference=fundOperations.netAlphaAndGrossAlpaComparisonMethod();
	          if(verifier.verifyNetAlphaAndGrossAlpha(difference)) {
	        	  logger1.log(LogStatus.PASS, "Net Alpha and Gross Alpha Test case for fund "+excelReaderClass.fundsInfo.get(counter).getFundsName());
	        	  logger1.log(LogStatus.INFO, "Net return values is : "+fundOperations.net_alpha_value + "&" + " Gross return value is : " +fundOperations.gross_alpha_value);
	          }
	          else {
	        	  logger1.log(LogStatus.FAIL, "NET Alpha and GROSS Alpha Test case for fund "+excelReaderClass.fundsInfo.get(counter).getFundsName() + "failed");
	        	  logger1.log(LogStatus.INFO, "Net return values is : "+fundOperations.net_alpha_value + "&" + " Gross return value is : " +fundOperations.gross_alpha_value);
	          }
	         // verifier.verifyNetAlphaAndGrossAlpha(difference);
	          //logger1.log(LogStatus.PASS, "NET Alpha and GROSS Alpha Test case for fund"+excelReaderClass.fundsInfo.get(counter).getFundsName()); 
	         // excelReaderClass.logger.info("Test completed for fund " +excelReaderClass.fundsInfo.get(counter).getFundsName());
	         // excelReaderClass.logger.info("*****************************************************");
	          //System.out.println("Test completed for fund " +excelReaderClass.fundsInfo.get(counter).getFundsName());
	          //System.out.println("*****************************************************");
	          Thread.sleep(5000);
	    	   
	          //Test case for Return Cash
              double values[]=fundOperations.checkGrossRetAndReturnCash();
	          if(verifier.verifyGrossAndReturnCash(values)) {
	        	  logger1.log(LogStatus.PASS, "Gross return > Return @0% Cash : ");
	        	  logger1.log(LogStatus.INFO, "Gross return : "+ values[4] + "&" +  " Return @0% Cash : "+  values[3]);
	          }
          else {
	        	  logger1.log(LogStatus.FAIL,"Gross return < Return @0% Cash : ");
	        	  logger1.log(LogStatus.INFO, "Gross return : "+ values[4] + "&" +  " Return @0% Cash : "+  values[3]);
	          }
	    	  
	          //verifier.verifyGrossAndReturnCash(values);

	          //Test case for batting Average
	          battingAvg=fundOperations.checkBattingAvg();
	          //System.out.println(battingAvg);
	          if(verifier.verifyBattingAverage(battingAvg)) {
	        	  logger1.log(LogStatus.PASS, "Batting Average is between 10% and 90% for fund "+excelReaderClass.fundsInfo.get(counter).getFundsName());
	        	  logger1.log(LogStatus.INFO, "Batting Avg Value is : "+battingAvg);
	          }
	          else {
	        	  logger1.log(LogStatus.FAIL, "Batting Average is not between 10% and 90% for fund "+excelReaderClass.fundsInfo.get(counter).getFundsName());
	        	  logger1.log(LogStatus.INFO, "Batting Avg Value is : "+battingAvg);
	          }
	         
	          
	    	  
	          //Test case for Alpha Calculations (Gross and Net)
	         double values1[]=fundOperations.alphaCalulation();
	          /*for (int i = 0; i < values1.length; i++)
		          {
					System.out.println(values1[i]);
		      }*/ 
	          if(verifier.verifyalphaCalulation(values1)) {
	        	  logger1.log(LogStatus.PASS, "Net and Gross Alpha values match for fund " + excelReaderClass.fundsInfo.get(counter).getFundsName());
	          }
	          else {
	        	  logger1.log(LogStatus.FAIL, "Net and Gross Alpha values does not match for fund "+ excelReaderClass.fundsInfo.get(counter).getFundsName());
	          }
	          
	          
	          
	          //Test case for RiskAdjustedPerformanceNumbers
	          fundOperations.checkRiskAdjustedperformancenumbers1();
	          for(Entry<String, Double> m:fundOperations.riskAdjustedPerformanceValMap.entrySet()){  
//		    	   System.out.println(m.getKey()+" "+m.getValue());  
	        	  verifier.verifyRiskAdjustedPerformanceNumbers(m.getKey(), m.getValue());
	        	 
		    	  }  
	          System.out.println("Is all data true:- "+verifier.isVerifyRiskAdjustedPerformance());
	          
	          if(verifier.isVerifyRiskAdjustedPerformance()) {
	        	  logger1.log(LogStatus.PASS, "All Risk Adjuested Performance Numbers match their criterias for fund "+ excelReaderClass.fundsInfo.get(counter).getFundsName());
	          }
	          else {
	        	  logger1.log(LogStatus.FAIL, "One or More Risk Adjusted Performance Numbers does not match their criteria for fund "+ excelReaderClass.fundsInfo.get(counter).getFundsName());
	          }
	          for(Entry<String, Boolean> m:verifier.verifyRiskAdjustedPerformanceMap.entrySet()){  
		    	   System.out.println(m.getKey()+" "+m.getValue());  
		    	   
		    	   if(m.getValue()) {
			        	 // logger1.log(LogStatus.PASS, "Net and Gross Alpha values match");
			          }
			          else {
			        	  logger1.log(LogStatus.INFO, "Risk adjusted performance number does not match for "+m.getKey());
			          }
	        	 
		    	  } 
	      
	          //Test case for Sector Heatmap
	          double values2[]=fundOperations.checkSectorheatMap();
	          for(int i=0;i<fundOperations.alphaArray.length;i++) 
	          {
	          if(verifier.verifySectorHeatMap(fundOperations.alphaArray[i],fundOperations.sumArray[i]))
	          {
	        	  logger1.log(LogStatus.PASS, "Sum of all values is equal to Alpha value for fund "+excelReaderClass.fundsInfo.get(counter).getFundsName());
	          }
	          else {
	        	  logger1.log(LogStatus.FAIL, "Sum of all values is not equal to Alpha value for fund "+excelReaderClass.fundsInfo.get(counter).getFundsName());
	               }
	          
	          }
	          
	         //Test case for to check unknown sector is present
	         String sector_name = fundOperations.findUnknownSector();
	         if(verifier.verifyUnknownSector(sector_name)) {
	        	 System.out.println(sector_name);
	        	 logger1.log(LogStatus.FAIL, "Unknown sector is present for fund "+excelReaderClass.fundsInfo.get(counter).getFundsName());
	         }
	         else {
	        	 logger1.log(LogStatus.PASS, "Unknown sector is  not present for fund "+excelReaderClass.fundsInfo.get(counter).getFundsName());
	        	 
	              }
	         
	         //Test case for to click on bottomup tab
	         fundOperations.commonMethod();
	         
	          fundOperations.clickOnBottomUpTab();
	          
	          Thread.sleep(5000);
	         
	       
	        //Test case for  Performance source calculation
	        double sum_PerformanceSourceCal=fundOperations.performanceSourceCal();
	        System.out.println("array started");
	       // fundOperations.commonMethod();
	        double alpha=fundOperations.roundOff_gross_alpha_value;
	        System.out.println("Alpha is "+alpha);
	        
	         if(verifier.verifyPerformanceCal(sum_PerformanceSourceCal,alpha))
	         {
	        	 logger1.log(LogStatus.PASS,"Sum of Performance source alpha values match with gross alpha for fund "+excelReaderClass.fundsInfo.get(counter).getFundsName());
	         }
	         else
	         {
	        	 logger1.log(LogStatus.FAIL,"Sum of Performance source alpha values does not match with gross alpha for fund "+excelReaderClass.fundsInfo.get(counter).getFundsName());
	         }
	         
 //Test Case for security slice total alpha comparision
	         
	         double securitySliseTotalAlpha=fundOperations.checkSecuritySliseTotalAlpha();
	         double alpha_val=fundOperations.roundOff_gross_alpha_value;
	         System.out.println("Alpha is "+alpha_val);
	         
	         if(verifier.verifySecuritySliceTotalAlpha(securitySliseTotalAlpha, alpha_val))
	         {
	        	 logger1.log(LogStatus.PASS,"Security slice total alpha match with alpha value for fund "+ excelReaderClass.fundsInfo.get(counter).getFundsName());
	        	 logger1.log(LogStatus.INFO, " alpha value"+alpha_val + " and total alpha value"+fundOperations.checkSecuritySliseTotalAlpha());
	         }
	         else
	         {
	        	 logger1.log(LogStatus.FAIL,"Security slice total alpha does not match with alpha value for fund "+ excelReaderClass.fundsInfo.get(counter).getFundsName());
	        	 logger1.log(LogStatus.INFO, " alpha value"+alpha_val + " and total alpha value"+fundOperations.checkSecuritySliseTotalAlpha());
	         }
	         
	     //Test Case for security slice total contribution
	         
	         double securitySliseTotalContribution=fundOperations.checkSecuritySliceTotalContribution();
	         double gross_ret_val=fundOperations.roundOff_gross_return_value;
	         System.out.println("Gross Return is"+gross_ret_val);
	         
	         if(verifier.verifySecuritySliceTotalContribution(securitySliseTotalContribution, gross_ret_val))
	         {
	        	 logger1.log(LogStatus.PASS,"Security slice total contribution match with gross return for fund "+ excelReaderClass.fundsInfo.get(counter).getFundsName());
	        	 logger1.log(LogStatus.INFO, "contribution value "+securitySliseTotalContribution +" and gross return "+gross_ret_val);
	         }
	         else
	         {
	            logger1.log(LogStatus.FAIL,"Security slice total contribution does not match with gross return for fund "+ excelReaderClass.fundsInfo.get(counter).getFundsName());
	            logger1.log(LogStatus.INFO, "contribution value "+securitySliseTotalContribution +" and gross return "+gross_ret_val);

	         } 
	
	         
	//Test Case for security slice to check Sum of Selection, timing and derivatives is equal to alpha         
	         
	         double[] values5=fundOperations.checkTotalSumSelectionTimingDerivative();
	         for (int i = 0; i < values5.length; i++) 
	         {
				System.out.println(values5[i]);
			 }
	         
	         if(verifier.verifySecuritySliceTotalSelTimDer(values5))
	         {
	        	 logger1.log(LogStatus.PASS,"Sum of selection,timing,derivative is match with total alpha for fund "+ excelReaderClass.fundsInfo.get(counter).getFundsName());
	        	 logger1.log(LogStatus.INFO, " for total selection value : "+fundOperations.securityslicetotal_selection_value+" , total timimg value : "+fundOperations.securityslicetotal_timing_value+" , total derivative value : "+fundOperations.securityslicetotal_derivative_value+ "  and total alpha value : "+fundOperations.checkSecuritySliseTotalAlpha());
	         }
	         else
	         {
	        	 logger1.log(LogStatus.FAIL,"Sum of selection,timing,derivative does not match with total alpha for fund "+ excelReaderClass.fundsInfo.get(counter).getFundsName() );
	        	 logger1.log(LogStatus.INFO, " for total selection value : "+fundOperations.securityslicetotal_selection_value+" , total timimg value : "+fundOperations.securityslicetotal_timing_value+" , total derivative value : " +fundOperations.securityslicetotal_derivative_value+ "  and total alpha value : "+fundOperations.checkSecuritySliseTotalAlpha());
	         }
	         
	          
	//Test Case for security slice to check fund weight is 100%
	      double[] values6=fundOperations.checkForFundWt();
	      for (int i = 0; i < values6.length; i++) 
	      {
	    	  System.out.println(values6[i]);
		  }
	      if(verifier.verifyTotalFundWeight(values6))
	      {
	    	  logger1.log(LogStatus.PASS,"Fund wt is 100 % for fund "+ excelReaderClass.fundsInfo.get(counter).getFundsName() );
	    	  logger1.log(LogStatus.INFO, "Fund weight as on date value : "+fundOperations.roundOff_fundwtason_date_value + " , Fund weight as on avg value : "+fundOperations.roundOff_fundwtason_date_value+ " , Index weight : "+fundOperations.roundOff_index_wt_value);
	      }
	      else
	      {
	    	  logger1.log(LogStatus.FAIL,"Fund wt is not 100 % for fund "+ excelReaderClass.fundsInfo.get(counter).getFundsName());
	    	  logger1.log(LogStatus.INFO, "Fund weight as on date value : "+fundOperations.roundOff_fundwtason_date_value + " , Fund weight as on avg value : "+fundOperations.roundOff_fundwtason_date_value+ " , Index weight : "+fundOperations.roundOff_index_wt_value);
	      }
	      
	      double[] fundooArray=fundOperations.fundoProcess(excelReaderClass.fundsInfo.get(counter).getFundooUrl());
	      for (int i = 0; i < fundooArray.length; i++) 
	      {
	    	  System.out.println(fundooArray[i]);
			
		  }
	      if(verifier.verifyAumValues(fundooArray))
	      {
	    	  logger1.log(LogStatus.PASS,"Valuefy AUM is matching with Fundoo AUM with a difference of 20 % for fund :  "+ excelReaderClass.fundsInfo.get(counter).getFundsName());
	    	  logger1.log(LogStatus.INFO,"Valuefy AUM value  :    " +fundOperations.valuefy_aum + "     and Fundoo AUM value  :       "  +fundOperations.fundo_aum_value );
	    	  logger1.log(LogStatus.INFO, "Calculated  % Difference between both AUM  : " +fundOperations.roundOffAumDiff);
	      }
	      else
	      {
	    	  logger1.log(LogStatus.FAIL,"Valuefy AUM is not matching with Fundoo AUM with a difference of 20 % for fund :  "+ excelReaderClass.fundsInfo.get(counter).getFundsName());
	    	  logger1.log(LogStatus.INFO,"Valuefy AUM value  :     " +fundOperations.valuefy_aum + "     and Fundoo AUM value  :       "  +fundOperations.fundo_aum_value);
	    	  logger1.log(LogStatus.INFO, "Calculated  % Difference between both AUM  : " +fundOperations.roundOffAumDiff);
	      }
	      
	      logger1.log(LogStatus.INFO, "Test completed for fund : " + excelReaderClass.fundsInfo.get(counter).getFundsName());
	         
	      }
	      
	          //} for loop closing for benchmark
//	         //Test case for AUM comparison (need to execute at the end)
//	          fundOperations.checkAUM(excelReaderClass.fundsName.get(counter));
//	          if(verifier.verifyAUM(FundOperations.aumVal, FundOperations.fundooVal)) {
//	        	  logger1.log(LogStatus.PASS, "AUM is matching");
//	          }
//	          else {
//	        	  logger1.log(LogStatus.FAIL, "AUM is not matching");
//	          }
	     
	
	      }
	          
	     
	
	      
	
	
	@AfterTest
    public void endTest() {
  	  extent.endTest(logger1); 
        extent.flush();
    }
	
	
}
