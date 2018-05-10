package flynas.web.uat.routes;

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

public class TC186_roundTripDomesticBusinessAddExtra_RUH_AHB extends BookingPageFlow{
	ExcelReader xls = new ExcelReader(configProps.getProperty("TestDataIBEUATRoutes"),"AllRoutes");

	@Test(dataProvider = "testData",groups={"Business"})
	public  void TC_186_roundTripDomesticBusinessAddExtra_RUH_AHB( String bookingClass, String bundle,
			String mobilenum,String paymentType,String newDate,String depDate,String rtnDate,	String origin,
			String dest,String triptype,String adult,String child,String infant,String seatSelect,
			String Description) throws Throwable {
		try {
			TestEngine.testDescription.put(HtmlReportSupport.tc_name, Description);
			String[] Credentials = pickCredentials("UserCredentials");
			String username =Credentials[0];
			String password =Credentials[1];			
			String deptDate = pickDate(depDate);
			String retrnDate = pickDate(rtnDate);			
			inputBookingDetails(triptype,origin, dest, deptDate , "RUH", "AMM", retrnDate,adult, child, infant,"","","");
			selectClass(bookingClass, bundle); 
			clickContinueBtn();
			upSellPopUpAction("Continue");
			inputPassengerDetails("Domestic", "2", "Afghanistan", "National ID Card", 
					"F123456", "1234567890", mobilenum, username,"","","");
			Baggage_Extra(triptype);
			addSportsEqpmnt(triptype);
			Select_A_Meal();
			Select_lounge();
			inputExtras("12");
			selectallSeats(seatSelect,"2",triptype);
			payment(paymentType, "");
			String strpnr = getReferenceNumber();
			String strPNR = strpnr.trim();
			System.out.println(strPNR);
			
			validate_ticketStatus(strPNR);
		
			Reporter.SuccessReport("TC186_roundTripDomesticBusinessAddExtra_RUH_AHB", "Pass");
			
			}
		
	catch (Exception e) {
			e.printStackTrace();
			Reporter.failureReport("TC186_roundTripDomesticBusinessAddExtra_RUH_AHB", "Failed");
		}
	}
	
	@DataProvider(name="testData")
	public Object[][] createdata1() {
	    return (Object[][]) new Object[][] { 
	    		{
	    		xls.getCellValue("Booking Class", "Value2"),
	    		"",
	    		xls.getCellValue("Mobile", "Value"),
	    		xls.getCellValue("Payment Type", "Value"),
	    		xls.getCellValue("NewDate", "Value"),
	    		xls.getCellValue("Departure Date", "Value"),
	    		xls.getCellValue("Return Date", "Value"),
	    		xls.getCellValue("Origin", "Value62"),
	    		xls.getCellValue("Destination", "Value62"),
	    		xls.getCellValue("Trip Type", "Value2"),
	    		xls.getCellValue("Adults Count", "Value"),
	    		xls.getCellValue("Child Count", "Value2"),
	    		xls.getCellValue("Infant Count", "Value"),
	    		xls.getCellValue("Select Seat", "Value3"),
	    		"Validate RoundTrip Domestic Business AddExtra_RUH_AHB"}};
	}


}
