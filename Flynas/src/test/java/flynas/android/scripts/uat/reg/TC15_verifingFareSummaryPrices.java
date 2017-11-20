package flynas.android.scripts.uat.reg;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ctaf.accelerators.TestEngine;
import com.ctaf.support.ExcelReader;
import com.ctaf.support.HtmlReportSupport;
import com.ctaf.utilities.Reporter;

import flynas.android.workflows.BookingPageFlow;
import flynas.android.workflows.Homepage;

public class TC15_verifingFareSummaryPrices extends BookingPageFlow{
	ExcelReader xls = new ExcelReader(configProps.getProperty("TestDataForAndroid"),"TC_01_oneWayDomesticEcoSADAD");

	@Test(dataProvider = "testData",groups={"Android"})
	public  void TC_15_verifingFareSummaryPrices(String tripType, String origin, String dest,String deptDate, String origin2,
			String departure2, String retdate,String Audalt,String Child,String infant, String promo,String Economy,String Flex,
			String Business,String Description
			) throws Throwable {
		try {
			
			TestEngine.testDescription.put(HtmlReportSupport.tc_name, Description);
			// Handlepopup();
			
			String depDate = pickDate(deptDate);
			String rtrndate = pickDate(retdate);
			
			Homepage homepage = new Homepage();
			homepage.select_Bookflights("Anonymous");
			inputBookingDetails(tripType, origin, dest, depDate, origin2, departure2, rtrndate,Audalt, Child, infant,promo,"");
 			verifingEcofarePrice(Economy);
 			verifingEcofarePrice(Flex);
 			verifingEcofarePrice(Business);
			
			Reporter.SuccessReport("TC15_verifingFareSummaryPrices", "Pass");
			
			}
		
		catch (Exception e) {
			e.printStackTrace();
			Reporter.failureReport("TC15_verifingFareSummaryPrices", "Failed");
		}
	}
	
	@DataProvider(name="testData")
	public Object[][] createdata1() {
	    return (Object[][]) new Object[][] { 
	    		{xls.getCellValue("Trip Type", "Value"),
	    		xls.getCellValue("Origin", "Value"),
	    		xls.getCellValue("Destination", "Value"),
	    		xls.getCellValue("Departure Date", "Value"),
	    		"",
	    		"",
	    		xls.getCellValue("Return Date", "Value"),
	    		xls.getCellValue("Adults Count", "Value"),
	    		xls.getCellValue("Child Count", "Value"),
	    		xls.getCellValue("Infant Count", "Value"),
	    		xls.getCellValue("Promo", "Value"),
	    		"Economy","Flex","Business",
	    		"verifing Fare Summary Prices"}};
	}


}
