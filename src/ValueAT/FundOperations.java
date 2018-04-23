package ValueAT;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.FixMethodOrder;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import ValueATTest.ExcelReaderClass;
import ValueATTest.TestClass;

public class FundOperations {
	ExtentReports extent;
	// ExtentTest logger;
	ExcelReaderClass excelReaderClass;
	private static Logger logger = LogManager.getLogger(FundOperations.class);

	public static double aumVal;
	public static double fundooVal;

	@FindBy(id = "//*[@id=\"highcharts-0\"]/svg/g[7]/g[1]")
	private WebElement allocationValue;

	@FindBy(id = "scheme-search-textbox")
	private WebElement fundooSearchBox;

	@FindBy(xpath = "//button[contains(@class,'btn dropdown-toggle btn-default')][1]")
	private WebElement newFunddropdown;

	@FindBy(xpath = "//button[contains(@class,'btn dropdown-toggle selectpicker btn-default')]")
	private WebElement funddropdown;

	@FindBy(xpath = "//input[contains(@class,'form-control')]")
	private WebElement search;

	@FindBy(xpath = "//input[contains(@class,'input-block-level form-control')]")
	private WebElement searchbox;

	@FindBys({ @FindBy(xpath = "Birla Sun Life") })
	private List<WebElement> autosugg;

	@FindBy(xpath = "//span[contains(text(),'Birla Sun Life Advantage Fund [ADVG]')]")
	private WebElement selectfund;

	@FindBy(xpath = "//button[contains(@class,'btn dropdown-toggle btn-default btn-sm')][1]")
	private WebElement benchmarkdropdown;

	@FindBy(xpath = "//span[contains(text(),'S&P BSE 200')][1]")
	private WebElement selectbenchmark;

	@FindBy(xpath = "//i[contains(@class,'fa fa-caret-down')]")
	private WebElement selectcalender;

	@FindBy(xpath = "//li[contains(text(),'Last 30 Days')]")
	private WebElement picklast30days;

	@FindBy(xpath = "//li[contains(text(),'Month to Date')]")
	private WebElement pickmonthtodate;

	@FindBy(xpath = "//li[contains(text(),'Year to Date')]")
	private WebElement pickyeartodate;

	@FindBy(xpath = "//li[contains(text(),'1 Year')]")
	private WebElement pick1year;

	@FindBy(xpath = "//li[contains(text(),'Custom Range')]")
	private WebElement pickcustomdate;

	@FindBy(xpath = "//input[contains(@name,'daterangepicker_start')]")
	private WebElement fromdate;

	@FindBy(xpath = "//input[contains(@name,'daterangepicker_end')]")
	private WebElement todate;

	@FindBy(xpath = "//button[contains(text(),'Apply')]")
	private WebElement apply;

	@FindBy(xpath = "(//select[contains(@class,'monthselect')][1]")
	private WebElement monthdropdown1;

	@FindBy(xpath = "(//select[contains(@class,'yearselect')][1]")
	private WebElement yeardropdown1;

	@FindBy(xpath = "(//td[contains(@class,'available in-range' and contains(text(),'11')][1]")
	private WebElement pickfromdate;

	@FindBy(xpath = "(//select[contains(@class,'monthselect')][2]")
	private WebElement monthdropdown2;

	@FindBy(xpath = "(//select[contains(@class,'yearselect')][2]")
	private WebElement yeardropdown2;

	@FindBy(xpath = "(//td[contains(@class,'available in-range' and contains(text(),'16')][2]")
	private WebElement picktodate;

	@FindBy(xpath = "//span[contains(@id,'process-button')]")
	private WebElement processButton;

	// -----------------------Panel  Start----------------------------------------------------------------

	@FindBy(xpath = "//a[contains(@href,'#attributionpanel')]")
	private WebElement attribution;

	@FindBy(xpath = "//a[contains(@href,'#bottomuppanel')]")
	private WebElement bottomUp;

	@FindBy(xpath = "//a[contains(@href,'#MISpanel')]")
	private WebElement mis;

	@FindBy(xpath = "//a[contains(@href,'#stylepanel')]")
	private WebElement stylepanel;

	// -------------------Panel-closed--------------------------------------------------------------------

	@FindBy(xpath = "//span[contains(@id,'p-ret-top')]")
	private WebElement netret;

	@FindBy(xpath = "//span[contains(@id,'p-gross-top')]")
	private WebElement grossret;

	@FindBy(xpath = "//span[contains(@id,'alpha-top')]")
	private WebElement netalpha;

	@FindBy(xpath = "//td[contains(@id,'rapm-palpha')]")
	private WebElement grossalpha;

	@FindBy(xpath = "//span[contains(@id,'AUM-top')]")
	private WebElement aumValuefy;

	@FindBy(xpath = "//input[contains(@id,'scheme-search-textbox')]")
	private WebElement fundoSearchBox;

	@FindBy(xpath = "//div[contains(@id,'FC_Fund_AUM')]")
	private WebElement aumFundo;

	// --------------------check Return @0% Cash----------------------------------------------------------------------------

	@FindBy(xpath = "(//td[contains(@id,'rapm-bret-nav')])[2]")
	private static WebElement benchmark1;

	@FindBy(xpath = "//td[contains(@id,'rapm-bret-att')]")
	private static WebElement benchmark2;

	@FindBy(xpath = "(//div[contains(@class,'hm-filledText pull-left')])[2]")
	private static WebElement cashweight;

	@FindBy(xpath = "//span[contains(@id,'AIR-top')]")
	public static WebElement returnCash;

	// -------------Batting Average---------------------------

	@FindBy(xpath = "(//span[contains(@class,'pull-right')])[5]")
	private WebElement battingavg;

	@FindBy(xpath = "(//td[contains(@id,'rapm-pret-nav')])[2]")
	private WebElement netreturnfund;

	@FindBy(xpath = "(//td[contains(@id,'rapm-bret-nav')])[2]")
	private WebElement netreturnindex;

	@FindBy(xpath = "//td[contains(@id,'rapm-pret-att')]")
	private WebElement grossreturnfund;

	@FindBy(xpath = "//td[contains(@id,'rapm-bret-att')]")
	private WebElement grossreturnindex;

	// ----------Find element for Risk Adjustment--------------------------------------------------------------------
	@FindBy(xpath = ".//*[@id='rapm-Volatility']/td[1]")
	private WebElement volatility_text;

	@FindBy(xpath = ".//*[@id='rapm-beta']/td[1]")
	private WebElement beta_text;

	@FindBy(xpath = ".//*[@id='rapm-JA']/td[1]")
	private WebElement ja_text;

	@FindBy(xpath = ".//*[@id='rapm-Sharpe']/td[1]")
	private WebElement sharpe_text;

	@FindBy(xpath = ".//*[@id='rapm-ir']/td[1]")
	private WebElement ir_text;

	@FindBy(xpath = ".//*[@id='rapm-Treynor']/td[1]")
	private WebElement treynor_text;

	@FindBy(xpath = ".//*[@id='rapm-Sortino']/td[1]")
	private WebElement sortino_text;

	@FindBy(xpath = ".//*[@id='rapm-Upside']/td[1]")
	private WebElement upside_text;

	@FindBy(xpath = ".//*[@id='rapm-Downside']/td[1]")
	private WebElement downside_text;

	@FindBy(xpath = "//td[contains(text(),' More stats... ')]")
	private WebElement moreStats;

	@FindBy(xpath = "//td[contains(@id,'rapm-pVolatility')]")
	private WebElement volatility;

	@FindBy(xpath = "//td[contains(@id,'rapm-pbeta')]")
	private WebElement beta;

	@FindBy(xpath = "//td[contains(@id,'rapm-pJA')]")
	private WebElement jensensalpha;

	@FindBy(xpath = "//td[contains(@id,'rapm-pSharpe')]")
	private WebElement sharperatio;

	@FindBy(xpath = "//td[contains(@id,'rapm-pir')]")
	private WebElement informationratio;

	@FindBy(xpath = "//td[contains(@id,'rapm-pTreynor')]")
	private WebElement treynorratio;

	@FindBy(xpath = "//td[contains(@id,'rapm-pSortino')]")
	private WebElement sortinoratio;

	@FindBy(xpath = "//td[contains(@id,'rapm-pUpside')]")
	private WebElement upsidecapture;

	@FindBy(xpath = "//td[contains(@id,'rapm-pDownside')]")
	private WebElement downsidecapture;

	@FindBy(xpath = "(//td[contains(@class,'hm-cellHeatMap col-xs-1')])[1]")
	private WebElement sector_1_alpha;

	@FindBy(xpath = "(//td[contains(@class,'hm-cellHeatMap col-xs-1')])[2]")
	private WebElement sector_1_allocation;

	@FindBy(xpath = "(//td[contains(@class,'hm-cellHeatMap col-xs-1')])[3]")
	private WebElement sector_1_selection;

	@FindBy(xpath = "(//td[contains(@class,'hm-cellHeatMap col-xs-1')])[4]")
	private WebElement sector_1_timing;

	@FindBy(xpath = "(//td[contains(@class,'hm-cellHeatMap col-xs-1')])[5]")
	private WebElement sector_1_derivative;

	@FindBy(xpath = "(//td[contains(@class,'hm-cellHeatMap col-xs-1')])[6]")
	private WebElement sector_1_interaction;

	@FindBy(xpath = "(//td[contains(@class,'hm-cellHeatMap col-xs-1')])[7]")
	private WebElement sector_2_alpha;

	@FindBy(xpath = "(//td[contains(@class,'hm-cellHeatMap col-xs-1')])[8]")
	private WebElement sector_2_allocation;

	@FindBy(xpath = "(//td[contains(@class,'hm-cellHeatMap col-xs-1')])[9]")
	private WebElement sector_2_selection;

	@FindBy(xpath = "(//td[contains(@class,'hm-cellHeatMap col-xs-1')])[10]")
	private WebElement sector_2_timing;

	@FindBy(xpath = "(//td[contains(@class,'hm-cellHeatMap col-xs-1')])[11]")
	private WebElement sector_2_derivative;

	@FindBy(xpath = "(//td[contains(@class,'hm-cellHeatMap col-xs-1')])[12]")
	private WebElement sector_2_interaction;

	@FindBy(xpath = "(//td[contains(@class,'hm-cellHeatMap col-xs-1')])[13]")
	private WebElement sector_3_alpha;

	@FindBy(xpath = "(//td[contains(@class,'hm-cellHeatMap col-xs-1')])[14]")
	private WebElement sector_3_alloacation;

	@FindBy(xpath = "(//td[contains(@class,'hm-cellHeatMap col-xs-1')])[15]")
	private WebElement sector_3_selection;

	@FindBy(xpath = "(//td[contains(@class,'hm-cellHeatMap col-xs-1')])[16]")
	private WebElement sector_3_timing;

	@FindBy(xpath = "(//td[contains(@class,'hm-cellHeatMap col-xs-1')])[17]")
	private WebElement sector_3_derivative;

	@FindBy(xpath = "(//td[contains(@class,'hm-cellHeatMap col-xs-1')])[18]")
	private WebElement sector_3_interaction;

	@FindBy(xpath = "(//td[contains(@class,'hm-cellHeatMap col-xs-1')])[19]")
	private WebElement sector_4_alpha;

	@FindBy(xpath = "(//td[contains(@class,'hm-cellHeatMap col-xs-1')])[20]")
	private WebElement sector_4_allocation;

	@FindBy(xpath = "(//td[contains(@class,'hm-cellHeatMap col-xs-1')])[21]")
	private WebElement sector_4_selection;

	@FindBy(xpath = "(//td[contains(@class,'hm-cellHeatMap col-xs-1')])[22]")
	private WebElement sector_4_timing;

	@FindBy(xpath = "(//td[contains(@class,'hm-cellHeatMap col-xs-1')])[24]")
	private WebElement sector_4_interaction;

	@FindBy(xpath = "(//td[contains(@class,'hm-cellHeatMap col-xs-1')])[25]")
	private WebElement sector_5_alpha;

	// Allocation  

	@FindBy(xpath = "(//td[contains(@class,'hm-cellHeatMap col-xs-1')])[26]")
	private WebElement sector_5_allocation;

	// selection

	@FindBy(xpath = "(//td[contains(@class,'hm-cellHeatMap col-xs-1')])[27]")
	private WebElement sector_5_selection;

	// Timing
	@FindBy(xpath = "(//td[contains(@class,'hm-cellHeatMap col-xs-1')])[28]")
	private WebElement sector_5_timing;

	// Derivative

	@FindBy(xpath = "(//td[contains(@class,'hm-cellHeatMap col-xs-1')])[29]")
	private WebElement sector_5_derivative;

	// Interaction

	@FindBy(xpath = "(//td[contains(@class,'hm-cellHeatMap col-xs-1')])[30]")
	private WebElement sector_5_interaction;

	// -----------------------------------------------------------------------------------------------------------------------------------

	// high charts

	// -------------------------------------------------------------------------------------------------------------------------------

	// for Unknown Sector or security

	@FindAll({ @FindBy(xpath = "//td[contains(@class,'hm-cellSectors col-xs-3')]") })
	private List<WebElement> sectorcolms;

	// ------------------------------------------------------------------------------------------------------------------------------

	// Bottomup tab elements

	// portfolio and index alpha values

	// Outside Index

	@FindBy(xpath = "//span[contains(@id,'lbloutAlpha')]")
	private WebElement outsideindexalpha;

	// Common Stocks

	@FindBy(xpath = "//span[contains(@id,'lblCommonAlpha')]")
	private WebElement commonstockalpha;

	// IN INDEX, NOT IN FUND

	@FindBy(xpath = "//span[contains(@id,'lblAvoidIndexAlpha')]")
	private WebElement inindexalpha;

	// -----------------------------------------------------------------------------------------------------------------------
	// Security slice and Dice Table

	// Total alpha
	@FindBy(xpath = "(//td[contains(@class,'lastVertCell dxpgCell_Metropolis dxpgGrandTotalCell_Metropolis')])[10]")
	private WebElement securityslicetotalalpha;

	// Total contribution
	@FindBy(xpath = "(//td[contains(@class,'lastVertCell dxpgCell_Metropolis dxpgGrandTotalCell_Metropolis')])[8]")
	private WebElement securityslicetotalcontribution;

	// Total Selection

	@FindBy(xpath = "(//td[contains(@class,'lastVertCell dxpgCell_Metropolis dxpgGrandTotalCell_Metropolis')])[5]")
	private WebElement securityslicetotalselection;

	// Total Timing

	@FindBy(xpath = "(//td[contains(@class,'lastVertCell dxpgCell_Metropolis dxpgGrandTotalCell_Metropolis')])[6]")
	private WebElement securityslicetotaltiming;

	// Total Derivative

	@FindBy(xpath = "(//td[contains(@class,'lastVertCell dxpgCell_Metropolis dxpgGrandTotalCell_Metropolis')])[7]")
	private WebElement securityslicetotalderivative;

	// Fund Wt.(as on DATE)
	@FindBy(xpath = "(//td[contains(@class,'lastVertCell dxpgCell_Metropolis dxpgGrandTotalCell_Metropolis')])[1]")
	private WebElement fundwtasondate;

	// Fund Wt.(avg)

	@FindBy(xpath = "(//td[contains(@class,'lastVertCell dxpgCell_Metropolis dxpgGrandTotalCell_Metropolis')])[2]")
	private WebElement fundwtasonavg;

	// Index Wt.

	@FindBy(xpath = "(//td[contains(@class,'lastVertCell dxpgCell_Metropolis dxpgGrandTotalCell_Metropolis')])[3]")
	private WebElement indexwt;

	// -------------------------------------------------------------------------------------------------------------------------------

	// pom class constructor
	WebDriver driver;

	public FundOperations(WebDriver driver) {
		this.driver = driver;
		// This initElements method will create all WebElements
		PageFactory.initElements(driver, this);
		excelReaderClass = new ExcelReaderClass();

	}

	// ----------------------------------------------------------------------------------------------------------------------------------

	// Methods

	// click fund dropdown and select fund
	public void selectFund(String fund) {
		try {

			newFunddropdown.click();
			search.sendKeys(fund);
			newFunddropdown.sendKeys(Keys.ENTER);
	    	} catch (Exception e) 
		{
			e.printStackTrace();
		}

		
	}

	// click on benchmark dropdown and select benchmark

	public void selectBenchMark() throws InterruptedException {
		if (benchmarkdropdown.isDisplayed()) {
			Thread.sleep(5000);
			benchmarkdropdown.click();
			benchmarkdropdown.sendKeys(Keys.ARROW_DOWN);
			benchmarkdropdown.sendKeys(Keys.ENTER);
		}
	}

	// click on calender dropdown

	public void setCalender(String from, String to) {
		try {

			selectcalender.click();

			pickcustomdate.click();

			fromdate.click();

			fromdate.sendKeys(Keys.CONTROL + "a");

			fromdate.sendKeys(Keys.BACK_SPACE);
			fromdate.sendKeys(from);
			todate.click();
			todate.sendKeys(Keys.CONTROL + "a");
			todate.sendKeys(Keys.BACK_SPACE);
			todate.sendKeys(to);
			apply.click();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// click on processbutton
	public void process() {
		processButton.click();

	}

	// click on attribution button

	public void attributionPanel()

	{

		attribution.click();

	}
	
	// -------------------------------------------------------------------------------------------------------------------------------

	public static double net_ret_value;
	public static double gross_ret_value;
	// test method by pooja for net and gross ret
	public double netAndGrossValueComparisonMethod() {
		double num;
		double diff;
		String net_ret = netret.getText();
		String new_net_value = net_ret.substring(0, net_ret.length() - 1);
		// System.out.println(new_net_value);
		net_ret_value = Double.parseDouble(new_net_value);
		// excelReaderClass = new ExcelReaderClass();
		// System.out.println("NET RET is "+net_ret_value);
		excelReaderClass.logger.info("NET RET is " + net_ret_value);
		String gross_ret = grossret.getText();
		String new_gross_value = gross_ret.substring(0, gross_ret.length() - 1);
		// System.out.println(new_gross_value);
		gross_ret_value = Double.parseDouble(new_gross_value);
		// System.out.println("GROSS RET is "+gross_ret_value);
		excelReaderClass.logger.info("GROSS RET is " + gross_ret_value);
		diff = (int) Math.round((gross_ret_value - net_ret_value) * 100) / (double) 100;
		// System.out.println("Difference between NET RET and GROSS RET is "+ diff);
		excelReaderClass.logger.info("Difference between NET RET and GROSS RET is " + diff);
		return diff;
	}

	// -------------------------------------------------------------------------

	public static double net_alpha_value;
	public static double gross_alpha_value;
	// test method by pooja for net alpha and gross alpha
	public double netAlphaAndGrossAlpaComparisonMethod() {
		double num;
		double diffBetweenGrossAlpaAndNetAlpha;
		String net_aplha = netalpha.getText();
		String new_net_alpha_value = net_aplha.substring(0, net_aplha.length() - 1);
		/*
		 * String abc=net_aplha.substring(0, net_aplha.length()-1);
		 * System.out.println("abc is "+abc);
		 */
		net_alpha_value = Double.parseDouble(new_net_alpha_value);
		excelReaderClass.logger.info("NET Alpha is " + net_alpha_value);
		// System.out.println("NET Alpha is "+net_alpha_value);

		String gross_alpha = grossalpha.getText();
		String new_gross_alpha_value = gross_alpha.substring(0, gross_alpha.length() - 1);
		// System.out.println(new_gross_alpha_value);
		gross_alpha_value = Double.parseDouble(new_gross_alpha_value);
		// System.out.println("GROSS Alpha is "+gross_alpha_value);
		excelReaderClass.logger.info("GROSS Alpha is " + gross_alpha_value);
		diffBetweenGrossAlpaAndNetAlpha = (int) Math.round((gross_alpha_value - net_alpha_value) * 100) / (double) 100;
		// System.out.println("Difference between NET Alpha and GROSS Alpha is "+
		// diffBetweenGrossAlpaAndNetAlpha);
		excelReaderClass.logger
				.info("Difference between NET Alpha and GROSS Alpha is " + diffBetweenGrossAlpaAndNetAlpha);
		return diffBetweenGrossAlpaAndNetAlpha;
	}
	
	
//---------------------------------------------------------------------------------------------------------------------------------
	
	//common method to return gross alpha,net alpha,gross return
	
	public static double roundOff_gross_alpha_value;
	public static double roundOff_net_alpha_value;
	public static double roundOff_gross_return_value;
	
	public double[] commonMethod()
	{

	String gross_alpha = grossalpha.getText();
	String new_gross_alpha_value = gross_alpha.substring(0, gross_alpha.length() - 1);
	double gross_alpha_value = Double.parseDouble(new_gross_alpha_value);
	roundOff_gross_alpha_value=(double)Math.round(gross_alpha_value*10)/10;
	
	String net_aplha = netalpha.getText();
	String new_net_alpha_value = net_aplha.substring(0, net_aplha.length() - 1);
	double net_alpha_value = Double.parseDouble(new_net_alpha_value);
	roundOff_net_alpha_value=(double)Math.round(net_alpha_value*10)/10;
	
	String grossReturn = grossret.getText();
	String new_grossReturn = grossReturn.substring(0, grossReturn.length() - 1);
	double grossReturnValue = Double.parseDouble(new_grossReturn);
	roundOff_gross_return_value=(double)Math.round(grossReturnValue*10)/10;
	
	return new double[] {roundOff_gross_alpha_value,roundOff_net_alpha_value,roundOff_gross_return_value};
	}
	
//-----------------------------------------------------------------------------------------------------------------------------	

	
	
	static double roundOff_benchMark1_Value;
	static double roundOff_benchMark2_Value;
	static double roundOff_cashWeight_Value;
	static double roundOff_returncash_Value;
	static double roundOff_grossReturnValue;

	public double[] checkGrossRetAndReturnCash() {
		String benchmark_1 = benchmark1.getText();
		String newBenchmark_1 = benchmark_1.substring(0, benchmark_1.length() - 1);
		double benchMark1_Value = Double.parseDouble(newBenchmark_1);
		roundOff_benchMark1_Value = (int) Math.round((benchMark1_Value) * 100) / (double) 100;
		// System.out.println("Benchmark 1 value is"+roundOff_benchMark1_Value);

		String benchmark_2 = benchmark2.getText();
		String newBenchmark_2 = benchmark_2.substring(0, benchmark_2.length() - 1);
		double benchMark2_Value = Double.parseDouble(newBenchmark_2);
		roundOff_benchMark2_Value = (int) Math.round((benchMark2_Value) * 100) / (double) 100;
		// System.out.println("Benchmark 2 value is"+roundOff_benchMark2_Value);

		String cashWeight = cashweight.getText();
		String newCashWeight = cashWeight.substring(0, cashWeight.length() - 1);
		double cashWeight_Value = Double.parseDouble(newCashWeight);
		roundOff_cashWeight_Value = (int) Math.round((cashWeight_Value) * 100) / (double) 100;
		// System.out.println("cash weight value is"+roundOff_cashWeight_Value);

		String return_Cash = returnCash.getText();
		String newReturnCash = return_Cash.substring(0, return_Cash.length() - 1);
		double returncash_Value = Double.parseDouble(newReturnCash);
		roundOff_returncash_Value = (int) Math.round((returncash_Value) * 100) / (double) 100;
		// System.out.println("return cash value is"+roundOff_returncash_Value);

		String grossReturn = grossret.getText();
		String new_grossReturn = grossReturn.substring(0, grossReturn.length() - 1);
		double grossReturnValue = Double.parseDouble(new_grossReturn);
		roundOff_grossReturnValue = (int) Math.round((grossReturnValue) * 100) / (double) 100;
		// System.out.println("gross value is"+roundOff_grossReturnValue);

		return new double[] { roundOff_benchMark1_Value, roundOff_benchMark2_Value, roundOff_cashWeight_Value,
				roundOff_returncash_Value, roundOff_grossReturnValue };

	}

	// ---------------------------------------------------------

	double roundOff_battingAvg_Value;
	// test method by vaibhav_s to check batting avg

	public double checkBattingAvg() {
		String batting_avg = battingavg.getText();
		String newBatting_avg = batting_avg.substring(0, batting_avg.length() - 1);
		double battingAvg_Value = Double.parseDouble(newBatting_avg);
		roundOff_battingAvg_Value = (int) Math.round((battingAvg_Value) * 100) / (double) 100;

		// System.out.println("Batting Avg is "+roundOff_battingAvg_Value);

		return roundOff_battingAvg_Value;

	}

	// ------------------------------------------------------------------------------------------------------------------------------

	// test method by vaibhav_s to check alpha calculation

	double netreturn_fund_value;
	double netreturn_index_value;
	double grossreturn_fund_value;
	double grossreturn_index_value;
	double roundOff_netalphavalue;
	double roundOff_grossalphavalue;

	// method for alpha calculation

	public double[] alphaCalulation() throws InterruptedException {
		String netreturn_fund = netreturnfund.getText();
		// System.out.println("Net return is "+netreturn_fund);
		String new_netreturn_fund = netreturn_fund.substring(0, netreturn_fund.length() - 1);
	    netreturn_fund_value = Double.parseDouble(new_netreturn_fund);
		//roundOff_netreturn_fund_value = (double) Math.round((netreturn_fund_value) * 10) / 10;
		System.out.println("Net Return_Fund value" + netreturn_fund_value);

		String netreturn_index = netreturnindex.getText();
		String new_netreturn_index = netreturn_index.substring(0, netreturn_index.length() - 1);
		netreturn_index_value = Double.parseDouble(new_netreturn_index);
		//round_Off_netreturn_index_value = (double) Math.round((netreturn_index_value) * 10) / 10;
		System.out.println("Net Return_Index value" + netreturn_index_value);

		String grossreturn_fund = grossreturnfund.getText();
		String new_grossreturn_fund = grossreturn_fund.substring(0, grossreturn_fund.length() - 1);
		 grossreturn_fund_value = Double.parseDouble(new_grossreturn_fund);
		//round_Off_grossreturn_fund_value = (double) Math.round((grossreturn_fund_value) * 10) / 10;
		System.out.println("Gross Return_Fund value" + grossreturn_fund_value);

		String grossreturn_index = grossreturnindex.getText();
		String new_grossreturn_index = grossreturn_index.substring(0, grossreturn_index.length() - 1);
	     grossreturn_index_value = Double.parseDouble(new_grossreturn_index);
		//round_Off_grossreturn_index_value = (double) Math.round((grossreturn_index_value) * 10) / 10;
		System.out.println("Gross Return_Index value" + grossreturn_index_value);

		String net_aplha = netalpha.getText();
		String new_net_aplha = net_aplha.substring(0, net_aplha.length() - 1);
		double netalphavalue = Double.parseDouble(new_net_aplha);
		roundOff_netalphavalue = (double) Math.round((netalphavalue) * 10) / 10;
		System.out.println("net alpha value is" + roundOff_netalphavalue);

		String gross_alpha = grossalpha.getText();
		System.out.println(gross_alpha);
		String new_gross_alpha = gross_alpha.substring(0, gross_alpha.length() - 1);
		double grossalphavalue = Double.parseDouble(new_gross_alpha);
		roundOff_grossalphavalue = (double) Math.round((grossalphavalue) * 10) / 10;
		System.out.println("Gross alpha value is" + roundOff_grossalphavalue);
		Thread.sleep(2000);

		double net_alpha_value =netreturn_fund_value - netreturn_index_value;
		roundOff_net_alpha_value=(double) Math.round((net_alpha_value) * 10) / 10;
		System.out.println("value of net alpha is "+ roundOff_net_alpha_value);
		Thread.sleep(2000);
		double gross_alpha_value =grossreturn_fund_value - grossreturn_index_value;
		roundOff_gross_alpha_value=(double) Math.round((gross_alpha_value) * 10) / 10;
		System.out.println("value of gross alpha is "+roundOff_gross_alpha_value);
		return new double[] { roundOff_netalphavalue, roundOff_net_alpha_value, roundOff_grossalphavalue, roundOff_gross_alpha_value };
		// return new double[]
		// {roundOff_netreturn_fund_value,round_Off_netreturn_index_value,round_Off_grossreturn_fund_value,round_Off_grossreturn_index_value,roundOff_netalphavalue,roundOff_grossalphavalue};
	}

	// ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	double roundOff_volatility_value;
	double roundOff_beta_value;
	double roundOff_jensensalpha_value;
	double roundOff_sharperatio_value;
	double roundOff_informationratio_value;
	double roundOff_treynorratio_value;
	double roundOff_sortinoratio_value;
	double roundOff_upsidecapture_value;
	double roundOff_downsidecapture_value;
	public static HashMap<String, Double> riskAdjustedPerformanceValMap;

	// method created by vaibhav to check Risk Adjusted performance numbers

	public double[] checkRiskAdjustedperformancenumbers1() throws InterruptedException {
		//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	/*	WebElement element = moreStats;
	   Actions actions = new Actions(driver);
		actions.moveToElement(element);
		actions.click().perform();*/
		/*WebElement element=driver.findElement(By.xpath("(//td[contains(@class,'expand-row')])[2]"));
		Actions actions = new Actions(driver);
		actions.moveToElement(element).click().build().perform();*/
		
		
		if(moreStats.isDisplayed()) {
			System.out.println("Element is displayed");
		    moreStats.click();
		}
		else {
			System.out.println("Element is not vsisible");
		}
		
		String s_volatility = volatility.getText();
		String new_s_volatility = s_volatility.substring(0, s_volatility.length() - 1);
		double volatility_value = Double.parseDouble(new_s_volatility);
		roundOff_volatility_value = (int) Math.round((volatility_value) * 100) / (double) 100;
		// System.out.println("Volatality ratio:"+roundOff_volatility_value);

		String s_beta = beta.getText(); // not in percent
		double beta_value = Double.parseDouble(s_beta);
		roundOff_beta_value = (int) Math.round((beta_value) * 100) / (double) 100;
		// System.out.println("Beta:"+roundOff_beta_value);

		String s_jensensalpha = jensensalpha.getText();
		String new_s_jensensalpha = s_jensensalpha.substring(0, s_jensensalpha.length() - 1);
		double jensensalpha_value = Double.parseDouble(new_s_jensensalpha);
		roundOff_jensensalpha_value = (int) Math.round((jensensalpha_value) * 100) / (double) 100;
		// System.out.println("JensenAlpha:"+roundOff_jensensalpha_value);

		String s_sharperatio = sharperatio.getText(); // not in percent
		double sharperatio_value = Double.parseDouble(s_sharperatio);
		roundOff_sharperatio_value = (int) Math.round((sharperatio_value) * 100) / (double) 100;
		// System.out.println("Sharper_Ratio:"+roundOff_sharperatio_value);

		String s_informationratio = informationratio.getText();
		String new_s_informationratio = s_informationratio.substring(0, s_informationratio.length() - 1);
		double informationratio_value = Double.parseDouble(new_s_informationratio);
		roundOff_informationratio_value = (int) Math.round((informationratio_value) * 100) / (double) 100;
		// System.out.println("Information ratio:"+roundOff_informationratio_value);

		String s_treynorratio = treynorratio.getText();
		String new_s_treynorratio = s_treynorratio.substring(0, s_treynorratio.length() - 1);
		double treynorratio_value = Double.parseDouble(new_s_treynorratio);
		roundOff_treynorratio_value = (int) Math.round((treynorratio_value) * 100) / (double) 100;
		// System.out.println("Treynnor Ratio:"+roundOff_treynorratio_value);

		String s_sortinoratio = sortinoratio.getText(); // not in percent
		double sortinoratio_value = Double.parseDouble(s_sortinoratio);
		roundOff_sortinoratio_value = (int) Math.round((sortinoratio_value) * 100) / (double) 100;
		// System.out.println("Sortino Ratio"+roundOff_sortinoratio_value);

		String s_upsidecapture = upsidecapture.getText(); // not in percent
		double upsidecapture_value = Double.parseDouble(s_upsidecapture);
		roundOff_upsidecapture_value = (int) Math.round((upsidecapture_value) * 100) / (double) 100;
		// System.out.println("Upside Capture:"+roundOff_upsidecapture_value);

		String s_downsidecapture = downsidecapture.getText(); // not in percent
		double downsidecapture_value = Double.parseDouble(s_downsidecapture);
		roundOff_downsidecapture_value = (int) Math.round((downsidecapture_value) * 100) / (double) 100;
		// System.out.println("DownSide Capture:"+roundOff_downsidecapture_value);

		riskAdjustedPerformanceValMap = new HashMap<String, Double>();
		riskAdjustedPerformanceValMap.put(volatility_text.getText(), roundOff_volatility_value);
		riskAdjustedPerformanceValMap.put(beta_text.getText(), roundOff_beta_value);
		riskAdjustedPerformanceValMap.put(ja_text.getText(), roundOff_jensensalpha_value);
		riskAdjustedPerformanceValMap.put(sharpe_text.getText(), roundOff_sharperatio_value);
		riskAdjustedPerformanceValMap.put(ir_text.getText(), roundOff_informationratio_value);
		riskAdjustedPerformanceValMap.put(treynor_text.getText(), roundOff_treynorratio_value);
		riskAdjustedPerformanceValMap.put(sortino_text.getText(), roundOff_sortinoratio_value);
		riskAdjustedPerformanceValMap.put(upside_text.getText(), roundOff_upsidecapture_value);
		riskAdjustedPerformanceValMap.put(downside_text.getText(), roundOff_downsidecapture_value);

		/*
		 * for(Entry<String, Double> m:riskAdjustedPerformanceValMap.entrySet()){
		 * System.out.println(m.getKey()+" "+m.getValue()); }
		 */

		return new double[] { roundOff_volatility_value, roundOff_beta_value, roundOff_jensensalpha_value,
				roundOff_sharperatio_value, roundOff_informationratio_value, roundOff_treynorratio_value,
				roundOff_sortinoratio_value, roundOff_upsidecapture_value, roundOff_downsidecapture_value };

	}

	// ---------------------------------------------------------------------------

	double roundOff_sector_1_alpha_value;
	double roundOff_sector_2_alpha_value;
	double roundOff_sector_3_alpha_value;
	double sector_1_allocation_value;
	double sector_2_allocation_value;
	double sector_3_allocation_value;
	double sector_1_selection_value;
	double sector_2_selection_value;
	double sector_3_selection_value;
	double sector_1_timing_value;
	double sector_2_timing_value;
	double sector_3_timing_value;
	double sector_1_derivative_value;
	double sector_2_derivative_value;
	double sector_3_derivative_value;
	double sector_1_interacion_value;
	double sector_2_interacion_value;
	double sector_3_interacion_value;
	double roundOff_sumOfSector1;
	double roundOff_sumOfSector2;
	double roundOff_sumOfSector3;

	public double[] sumArray;
	public double[] alphaArray;

	// method created by vaibhav to check sectorheatmap cal
	public double[] checkSectorheatMap() // alpha //Allocation Selection Timing Derivative Interaction
	{

		String sector1alpha = sector_1_alpha.getText();
		String new_sector1alpha = sector1alpha.substring(0, sector1alpha.length() - 1);
		double sector_1_alpha_value = Double.parseDouble(new_sector1alpha);
		roundOff_sector_1_alpha_value=(double)Math.round(sector_1_alpha_value*10)/10;
		System.out.println("Sector 1 alpha Value:" + sector_1_alpha_value);

		String sector2alpha = sector_2_alpha.getText();
		String new_sector2alpha = sector2alpha.substring(0, sector2alpha.length() - 1);
		double sector_2_alpha_value = Double.parseDouble(new_sector2alpha);
		roundOff_sector_2_alpha_value=(double)Math.round(sector_2_alpha_value*10)/10;

		System.out.println("Sector 2 alpha Value:" + sector_2_alpha_value);

		String sector3alpha = sector_3_alpha.getText();
		String new_sector3alpha = sector3alpha.substring(0, sector3alpha.length() - 1);
		double sector_3_alpha_value = Double.parseDouble(new_sector3alpha);
		roundOff_sector_3_alpha_value=(double)Math.round(sector_3_alpha_value*10)/10;

		
		System.out.println("Sector 3 alpha Value:" + sector_3_alpha_value);

		String sector1allocation = sector_1_allocation.getText();
		String new_sector1allocation = sector1allocation.substring(0, sector1allocation.length() - 1);
		sector_1_allocation_value = Double.parseDouble(new_sector1allocation);
		System.out.println("Sector 1 Allocation Value:" + sector_1_allocation_value);

		String sector2allocation = sector_2_allocation.getText();
		String new_sector2allocation = sector2allocation.substring(0, sector2allocation.length() - 1);
		sector_2_allocation_value = Double.parseDouble(new_sector2allocation);
		System.out.println("Sector 2 Allocation Value:" + sector_2_allocation_value);

		String sector3allocation = sector_3_alloacation.getText();
		String new_sector3allocation = sector3allocation.substring(0, sector3allocation.length() - 1);
		sector_3_allocation_value = Double.parseDouble(new_sector3allocation);
		System.out.println("Sector 3 Allocation Value:" + sector_3_allocation_value);
		// double sector_3_allocation_value=Double.parseDouble(sector3allocation);

		String sector1selection = sector_1_selection.getText();
		String new_sector1selection = sector1selection.substring(0, sector1selection.length() - 1);
		sector_1_selection_value = Double.parseDouble(new_sector1selection);
		System.out.println("Sector 1 Selection Value:" + sector_1_selection_value);

		String sector2selection = sector_2_selection.getText();
		String new_sector2selection = sector2selection.substring(0, sector2selection.length() - 1);
		sector_2_selection_value = Double.parseDouble(new_sector2selection);
		System.out.println("Sector 2 Selection Value:" + sector_2_selection_value);

		String sector3selection = sector_3_selection.getText();
		String new_sector3selection = sector3selection.substring(0, sector3selection.length() - 1);
		sector_3_selection_value = Double.parseDouble(new_sector3selection);
		System.out.println("Sector 3 Selection Value:" + sector_3_selection_value);

		String sector1timing = sector_1_timing.getText();
		String new_sector1timing = sector1timing.substring(0, sector1timing.length() - 1);
		sector_1_timing_value = Double.parseDouble(new_sector1timing);
		System.out.println("Sector 1 Timing Value:" + sector_1_timing_value);

		String sector2timing = sector_2_timing.getText();
		String new_sector2timing = sector2timing.substring(0, sector2timing.length() - 1);
		sector_2_timing_value = Double.parseDouble(new_sector2timing);
		System.out.println("Sector 2 Timing Value:" + sector_2_timing_value);

		String sector3timing = sector_3_timing.getText();
		String new_sector3timing = sector3timing.substring(0, sector3timing.length() - 1);
		sector_3_timing_value = Double.parseDouble(new_sector3timing);
		System.out.println("Sector 3 Timing Value:" + sector_3_timing_value);

		String sector1derivative = sector_1_derivative.getText();
		String new_sector1derivative = sector1derivative.substring(0, sector1derivative.length() - 1);
		sector_1_derivative_value = Double.parseDouble(new_sector1derivative);
		System.out.println("Sector 1 Derivative value:" + sector_1_derivative_value);

		String sector2derivative = sector_2_derivative.getText();
		String new_sector2derivative = sector2derivative.substring(0, sector2derivative.length() - 1);
		sector_2_derivative_value = Double.parseDouble(new_sector2derivative);
		System.out.println("Sector 2 Derivative value:" + sector_2_derivative_value);

		String sector3derivative = sector_3_derivative.getText();
		String new_sector3derivative = sector3derivative.substring(0, sector3derivative.length() - 1);
		sector_3_derivative_value = Double.parseDouble(new_sector3derivative);
		System.out.println("Sector 3 Derivative value:" + sector_3_derivative_value);

		String sector1interaction = sector_1_interaction.getText();
		String new_sector1interaction = sector1interaction.substring(0, sector1interaction.length() - 1);
		sector_1_interacion_value = Double.parseDouble(new_sector1interaction);
		System.out.println("Sector 1 Interaction Value" + sector_1_interacion_value);

		String sector2interaction = sector_2_interaction.getText();
		String new_sector2interaction = sector2interaction.substring(0, sector2interaction.length() - 1);
		sector_2_interacion_value = Double.parseDouble(new_sector2interaction);
		System.out.println("Sector 2 Interaction Value" + sector_2_interacion_value);

		String sector3interaction = sector_3_interaction.getText();
		String new_sector3interaction = sector2interaction.substring(0, sector3interaction.length() - 1);
		sector_3_interacion_value = Double.parseDouble(new_sector3interaction);
		System.out.println("Sector 3 Interaction Value" + sector_3_interacion_value);

		double sumOfSector1 = sector_1_allocation_value + sector_1_selection_value + sector_1_timing_value
				+ sector_1_derivative_value + sector_1_interacion_value;
		double sumOfSector2 = sector_2_allocation_value + sector_2_selection_value + sector_2_timing_value
				+ sector_2_derivative_value + sector_2_interacion_value;
		double sumOfSector3 = sector_3_allocation_value + sector_3_selection_value + sector_3_timing_value
				+ sector_3_derivative_value + sector_3_interacion_value;

		roundOff_sumOfSector1 = (double)Math.round(sumOfSector1*10)/10;
		roundOff_sumOfSector2 = (double)Math.round(sumOfSector2*10)/10;
		roundOff_sumOfSector3 = (double)Math.round(sumOfSector3*10)/10;

		System.out.println("Sum Of Sector Attribution: " + roundOff_sumOfSector1);
		System.out.println("Sum Of Sector Attribution: " + roundOff_sumOfSector2);
		System.out.println("Sum Of Sector Attribution: " + roundOff_sumOfSector3);
		sumArray = new double[3];
		sumArray[0] = roundOff_sumOfSector1;
		sumArray[1] = roundOff_sumOfSector2;
		sumArray[2] = roundOff_sumOfSector3;

		alphaArray = new double[3];
		alphaArray[0] = roundOff_sector_1_alpha_value;
		alphaArray[1] = roundOff_sector_2_alpha_value;
		alphaArray[2] = roundOff_sector_3_alpha_value;

		return new double[] { roundOff_sector_1_alpha_value, roundOff_sumOfSector1 };

	}

	// ---------------------------------------------------------------------------------------------------------------------
	

	String sectorname;
		public String  findUnknownSector()
		{
			
				for (WebElement colms: sectorcolms) 
				{
					 sectorname = colms.getText();
					// System.out.println(sectorname);
					 if(sectorname.contains("Unknown"))
							 {
						         System.out.println(sectorname);
						         break;
							 }
				
	                
	                 
				}
				
		        	return sectorname;
			
		 }
		
//--------------------------------------------------------------------------------------------------------------------------------
		//method for bottomup click
		
		public void clickOnBottomUpTab() 
		{
			bottomUp.click();
		}
		
//---------------------------------------------------------------------------------------------------------------------------------
		
		//method created by vaibhav_s for bottom up  performance source calculation
		
		  double outsideindexalpha_value;
		  double commonstock_value;
		  double inindex_value;
		 // double roundOff_grossalphavalue2;
	      double roundOff_performanceAlphaSum;	
			
			
		public double performanceSourceCal() throws InterruptedException
		
			{
			     System.out.println("method started");
			     Thread.sleep(5000);
				
				String outsideindex=outsideindexalpha.getText();
				//System.out.println(outsideindex);
				String new_outsideindex=outsideindex.substring(0, outsideindex.length()-1);
				outsideindexalpha_value=Double.parseDouble(new_outsideindex);
				//roundOff_outsideindexalpha_value=(double)Math.round(outsideindexalpha_value*10)/10;
				
				
				
				String  commonstock=commonstockalpha.getText();
				String new_commonstock=commonstock.substring(0, commonstock.length()-1);
				commonstock_value=Double.parseDouble(new_commonstock);
				//roundOff_commonstock_value=(double)Math.round(commonstock_value*10)/10;
				
				String inindex=inindexalpha.getText();
				String new_inindex=inindex.substring(0, inindex.length()-1);
				inindex_value=Double.parseDouble(new_inindex);
				//roundOff_inindex_value=(double)Math.round(inindex_value*10)/10;
				
				
				/*//gross alpha
				 String gross_alpha=grossalpha.getText();
				 String new_gross_alpha=gross_alpha.substring(0, gross_alpha.length()-1);
				 double grossalphavalue=Double.parseDouble(new_gross_alpha);
				 roundOff_grossalphavalue=(double)Math.round(grossalphavalue*10)/10;
				 
				*/
				
				 double	performanceAlphaSum=outsideindexalpha_value + commonstock_value + inindex_value;
				 
				 roundOff_performanceAlphaSum=(double) Math.round(performanceAlphaSum*10)/10;
				 System.out.println(roundOff_performanceAlphaSum);
				 
				 
				 return  roundOff_performanceAlphaSum;
				 
				
			}		
//----------------------------------------------------------------------------------------------------------------------------------		
		//Method created by vaibhav for security slice and dice total alpha comparison with gross alpha
		
		
        double roundOff_securityslicetotal_alpha_value;




	   public double checkSecuritySliseTotalAlpha()
	   {
		  
	 /*//gross alpha
			String gross_alpha=grossalpha.getText();
			String new_gross_alpha=gross_alpha.substring(0, gross_alpha.length()-1);
			double grossalphavalue=Double.parseDouble(new_gross_alpha);
			roundOff_grossalphavalue=(double)Math.round(grossalphavalue*10)/10;
	*/
		   
	//securityslicetotal_alpha
	
	String securityslicetotal_alpha=securityslicetotalalpha.getText();
	String new_securityslicetotal_alpha=securityslicetotal_alpha.substring(0, securityslicetotal_alpha.length()-1);
	double securityslicetotal_alpha_value=Double.parseDouble(new_securityslicetotal_alpha);
	roundOff_securityslicetotal_alpha_value=(double)Math.round(securityslicetotal_alpha_value*10)/10;
	
	return roundOff_securityslicetotal_alpha_value;
	  
    }
		
//--------------------------------------------------------------------------------------------------------------------------------		
		
	   //method created by vaibhav_s for compare  total contribution and gross return
	   
	   //double roundOff_grossretvalue;
	   double round_securityslicetotal_contribution_value;
	  
	   public double  checkSecuritySliceTotalContribution()
	   {
		/*   
	    String gross_ret = grossret.getText();
	    String new_gross_ret=gross_ret.substring(0, gross_ret.length()-1);
		double grossretvalue=Double.parseDouble(new_gross_ret);
		roundOff_grossretvalue=(double)Math.round(grossretvalue*10)/10;
	   */
	    String securityslicetotal_contribution=securityslicetotalcontribution.getText();
	    String new_securityslicetotal_contribution=securityslicetotal_contribution.substring(0, securityslicetotal_contribution.length()-1);
	    double securityslicetotal_contribution_value=Double.parseDouble(new_securityslicetotal_contribution);
	    round_securityslicetotal_contribution_value=(double)Math.round(securityslicetotal_contribution_value*10)/10;
	   
	    return  round_securityslicetotal_contribution_value;
	   
	       
	   }  

//---------------------------------------------------------------------------------------
 // method created by vaibhav_s to check sum of selection,timing,derivative=alpha
	   
	   //double roundOff_securityslicetotal_alpha_value;
	    public static double securityslicetotal_selection_value;
	    public static double securityslicetotal_timing_value;
	    public static double securityslicetotal_derivative_value;
	    public static double roundOff_sumSelTimDer;
	   
	public double[] checkTotalSumSelectionTimingDerivative()
	{
		
			
			String securityslicetotal_alpha=securityslicetotalalpha.getText();
			String new_securityslicetotal_alpha=securityslicetotal_alpha.substring(0, securityslicetotal_alpha.length()-1);
			double securityslicetotal_alpha_value=Double.parseDouble(new_securityslicetotal_alpha);
			roundOff_securityslicetotal_alpha_value=(double)Math.round(securityslicetotal_alpha_value*10)/10;
			
			
		    String securityslicetotal_selection=securityslicetotalselection.getText();
		    String new_securityslicetotal_selection=securityslicetotal_selection.substring(0, securityslicetotal_selection.length()-1);
		    securityslicetotal_selection_value=Double.parseDouble(new_securityslicetotal_selection);
		   // roundOff_securityslicetotal_selection_value=(double)Math.round(securityslicetotal_selection_value*10)/10;
		    
		    String securityslicetotal_timing=securityslicetotaltiming.getText();
		    String new_securityslicetotal_timing=securityslicetotal_timing.substring(0, securityslicetotal_timing.length()-1);
		    securityslicetotal_timing_value=Double.parseDouble(new_securityslicetotal_timing);
		    //roundOff_securityslicetotal_timing_value=(double)Math.round(securityslicetotal_selection_value*10)/10;
		    
		    String securityslicetotal_derivative=securityslicetotalderivative.getText();
		    String new_securityslicetotal_derivative=securityslicetotal_derivative.substring(0, securityslicetotal_derivative.length()-1);
		    securityslicetotal_derivative_value=Double.parseDouble(new_securityslicetotal_derivative);
		   // roundOff_securityslicetotal_derivative_value=(double)Math.round(securityslicetotal_derivative_value*10)/10;
		  	 
		    double sumSelTimDer=securityslicetotal_selection_value + securityslicetotal_timing_value + securityslicetotal_derivative_value;
		    
		    roundOff_sumSelTimDer=(double)Math.round(sumSelTimDer*10)/10;
		    
		    return new double[] {roundOff_sumSelTimDer,roundOff_securityslicetotal_alpha_value};
	}
		
	
//-----------------------------------------------------------------------------------------------------------------
		
	 //method created by vaibhav_s to check for Fund Wt.(as on DATE),Fund Wt.(avg),Index Wt. is 100 % or not
		
		public double roundOff_fundwtason_date_value;
		public double roundOff_fundwtason_avg_value;
		public double roundOff_index_wt_value;
		
		public double[]  checkForFundWt()
		{
			
			
				String fundwtason_date=fundwtasondate.getText();
				String new_fundwtason_date=fundwtason_date.substring(0, fundwtason_date.length()-1);
				double fundwtason_date_value=Double.parseDouble(new_fundwtason_date);
				roundOff_fundwtason_date_value=(double)Math.round(fundwtason_date_value*10)/10;
				
				String fundwtason_avg=fundwtasonavg.getText();
				String new_fundwtason_avg=fundwtason_avg.substring(0, fundwtason_avg.length()-1);
				double fundwtason_avg_value=Double.parseDouble(new_fundwtason_avg);
				roundOff_fundwtason_avg_value=(double)Math.round(fundwtason_avg_value*10)/10;
				
				String index_wt=indexwt.getText();
				String new_index_wt=index_wt.substring(0, index_wt.length()-1);
				double index_wt_value=Double.parseDouble(new_index_wt);
				roundOff_index_wt_value=(double)Math.round(index_wt_value*10)/10;
				
				 return new double[] {roundOff_fundwtason_date_value,roundOff_fundwtason_avg_value,roundOff_index_wt_value};
			
				
		}
//-------------------------------------------------------------------------------------------------------------------------			

		// Method created by vaibhav for comparing aum
		  public static double fundo_aum_value;
		  public static double valuefy_aum;
		  public static double roundOffAumDiff;

		    public double[] fundoProcess(String URL) throws InterruptedException {

		    String valuefy_Aum = aumValuefy.getText();
		    valuefy_aum = Double.parseDouble(valuefy_Aum);
			System.out.println(valuefy_aum);
			//driver.switchTo().window(URL);
			String windowHandle = driver.getWindowHandle();
		    driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL +"t");
			driver.get(URL);
			String fundo_Aum = aumFundo.getText();
			fundo_aum_value = Double.parseDouble(fundo_Aum);
			System.out.println(fundo_Aum);
			driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL +"\t");
		    driver.switchTo().defaultContent();
		    
		    //Aum  accepted Calculation
		    double  aumDiff=100 *(fundo_aum_value-valuefy_aum)/valuefy_aum;            //100*(D2-C2)/C2
		    roundOffAumDiff=Math.round(aumDiff*100)/100;
		    
		    System.out.println(roundOffAumDiff);
		    
			return new double[] {valuefy_aum,fundo_aum_value,roundOffAumDiff};
			
		}

}

