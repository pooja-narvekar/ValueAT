package ValueATVerifier;

import java.util.HashMap;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ValueAT.FundOperations;
import ValueATTest.TestClass;

public class MethodsVerifier {
	private static final Logger logger = LogManager.getLogger(MethodsVerifier.class.getName());
	TestClass testClass;
	public static HashMap<String,Boolean> verifyRiskAdjustedPerformanceMap=new HashMap<String,Boolean>();
	private static boolean isVerifyRiskAdjustedPerformance=true;
	
	public static boolean isVerifyRiskAdjustedPerformance() {
		return isVerifyRiskAdjustedPerformance;
	}

	public static void setVerifyRiskAdjustedPerformance(boolean isVerifyRiskAdjustedPerformance) {
		MethodsVerifier.isVerifyRiskAdjustedPerformance = isVerifyRiskAdjustedPerformance;
	}

	public boolean verifyGrossAndNet(double diff) {
		return diff<=2.5;
	}
	
	public boolean verifyNetAlphaAndGrossAlpha(double diff) {
		return diff>0;
	
	}
	
	public boolean verifyGrossAndReturnCash(double[] values) {
		return values[0]>0 && values[1]>0 && values[2]<0;
	}
	
	public boolean verifyBattingAverage(double battingAvg) {
		return battingAvg>10.00 && battingAvg<90.00;
	}
	
	public boolean verifyalphaCalulation(double[] values1) {
		/*boolean result;
		if(values1[0]==values1[1] && values1[2]==values1[3]) {
			result=true;
		}
		else {
			result=false;
		}
		
		return result;*/
		
		return values1[0]==values1[1] && values1[2]==values1[3];
	}
	
	public void verifyRiskAdjustedPerformanceNumbers(String key, double value) {
		boolean isVerified=false;
		switch(key) {
		case "Sortino Ratio":
			isVerified=value>=-2 && value<=7;
			isVerifyRiskAdjustedPerformance=isVerifyRiskAdjustedPerformance && isVerified;
			verifyRiskAdjustedPerformanceMap.put(key, isVerified);
			break;
		case "Volatility":
			isVerified=value>=5 && value<=25;
			isVerifyRiskAdjustedPerformance=isVerifyRiskAdjustedPerformance && isVerified;
			verifyRiskAdjustedPerformanceMap.put(key, isVerified);
			break;
		case "Sharpe Ratio":
			isVerified=value>=-2 && value<=7;
			isVerifyRiskAdjustedPerformance=isVerifyRiskAdjustedPerformance && isVerified;
			verifyRiskAdjustedPerformanceMap.put(key, isVerified);
			break;
		case "Upside Capture":
			isVerified=value>=0.8 && value<=1.5;
			isVerifyRiskAdjustedPerformance=isVerifyRiskAdjustedPerformance && isVerified;
			verifyRiskAdjustedPerformanceMap.put(key, isVerified);
			break;
		case "Information Ratio":
			isVerified=value>=0 && value<=2;
			isVerifyRiskAdjustedPerformance=isVerifyRiskAdjustedPerformance && isVerified;
			verifyRiskAdjustedPerformanceMap.put(key, isVerified);
			break;
		case "Treynor Ratio":
			isVerified=value>=0;
			isVerifyRiskAdjustedPerformance=isVerifyRiskAdjustedPerformance && isVerified;
			verifyRiskAdjustedPerformanceMap.put(key, isVerified);
			break;
		case "Beta":
			isVerified=value>=0.8 && value<=1.5;
			isVerifyRiskAdjustedPerformance=isVerifyRiskAdjustedPerformance && isVerified;
			verifyRiskAdjustedPerformanceMap.put(key, isVerified);
			break;
		/*case "Jensen's Alpha":
			isVerified=value;
			isVerifyRiskAdjustedPerformance=isVerifyRiskAdjustedPerformance && isVerified;
			verifyRiskAdjustedPerformanceMap.put(key, isVerified);
			break;*/
		case "Downside Capture":
			isVerified=value>=0.8 && value<=1.5;
			isVerifyRiskAdjustedPerformance=isVerifyRiskAdjustedPerformance && isVerified;
			verifyRiskAdjustedPerformanceMap.put(key, isVerified);
			break;
		}
		}
		
		
	 public boolean verifySectorHeatMap(double alpha,double sum) {
		return alpha==sum;
	 }
	 
	 public boolean verifyUnknownSector(String sector_name)
	 {
		return sector_name.equals("Unknown");
	 }
	 
	 
	 public boolean verifyAUM(double val1,double val2) {
			return val1==val2;
	 }
	 
	 
       public boolean verifyPerformanceCal(double sum, double alpha)
      {
	     return sum==alpha;
       }
	
	public boolean verifySecuritySliceTotalAlpha(double total_alpha, double alpha)
	{
		return total_alpha==alpha;
	}
	
	public boolean verifySecuritySliceTotalContribution(double total_contribution,double gross_ret)
	{
		return total_contribution==gross_ret;
	}
	
	public boolean verifySecuritySliceTotalSelTimDer(double[] values5)
	{
		return values5[0]==values5[1];
	}
	
	public boolean verifyTotalFundWeight(double[] values6)
	{
		return values6[0]==100 && values6[1]==100 && values6[2]==100;
	}
	
	public boolean verifyAumValues(double[] fundoo)
	{
		return fundoo[2]<=20;
	}
	
}
