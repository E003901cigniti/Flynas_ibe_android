package flynas.web.production.routes;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ctaf.accelerators.TestEngine;
import com.ctaf.support.ExcelReader;
import com.ctaf.support.HtmlReportSupport;
import com.ctaf.utilities.Reporter;

import flynas.web.testObjects.BookingPageLocators;
import flynas.web.workflows.BookingPageFlow;

public class TC124_roundTripDomesticFlexAddExtra_RUH_AHB extends BookingPageFlow{
	ExcelReader xls = new ExcelReader(configProps.getProperty("TestDataProdRoutes"),"Chrome_TestData");

	@Test(dataProvider = "testData",groups={"Flex"})
	public  void TC_124_roundTripDomesticFlexAddExtra_RUH_AHB( String bookingClass,
			String mobilenum,
			String paymentType,
			String newDate,
			String Departuredate,String rtnDate,
			String origin,
			String dest,String triptype,String adult,String child,String infant,String seatSelect,
			String Description) throws Throwable {
		try {
			TestEngine.testDescription.put(HtmlReportSupport.tc_name, Description);
			
			String deptDate = pickDate(Departuredate);
			String retrnDate = pickDate(rtnDate);
			String[] Credentials = pickCredentials("PRODcredentials");
			String username =Credentials[0];
								
			inputBookingDetails(triptype,origin, dest, deptDate , "RUH", "AMM", retrnDate,adult, child, infant,"","","");
			selectClass(bookingClass, triptype);
			inputPassengerDetails("Domestic", "2", "Afghanistan", "National ID Card", 
					"F123456", "1234567890", mobilenum, username,"","","");
			Baggage_Extra(triptype);
			addSportsEqpmnt(triptype);
			Select_A_Meal();
			Selecting_loung();
			inputExtras("12");
			selectallSeats(seatSelect,"2",triptype);
			payment(paymentType, "");
			String strpnr = getReferenceNumber();
			String strPNR = strpnr.trim();
			System.out.println(strPNR);
			
			verifyPNRforSadad();
		
			Reporter.SuccessReport("TC124_roundTripDomesticFlexAddExtra_RUH_AHB", "Pass");
			
			}
		
	catch (Exception e) {
			e.printStackTrace();
			Reporter.failureReport("TC124_roundTripDomesticFlexAddExtra_RUH_AHB", "Failed");
		}
	}
	
	@DataProvider(name="testData")
	public Object[][] createdata1() {
	    return (Object[][]) new Object[][] { 
	    		{
	    		
	    		xls.getCellValue("Booking Class", "Value2"),
	    		xls.getCellValue("Mobile", "Value"),
	    		"SADAD",
	    		xls.getCellValue("NewDate", "Value"),
	    		xls.getCellValue("Departure Date", "Value"),
	    		xls.getCellValue("Return Date", "Value"),
	    		xls.getCellValue("Origin", "Value62"),
	    		xls.getCellValue("Destination", "Value62"),
	    		xls.getCellValue("Trip Type", "Value2"),
	    		xls.getCellValue("Adults Count", "Value"),
	    		xls.getCellValue("Child Count", "Value2"),
	    		xls.getCellValue("Infant Count", "Value"),
	    		"Extra Leg Room",
	    		"Validate RoundTrip Domestic Flex AddExtra_RUH_AHB"}};
	}


}