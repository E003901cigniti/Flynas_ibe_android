package flynas.androidchrome.scripts;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ctaf.accelerators.TestEngine;
import com.ctaf.support.ExcelReader;
import com.ctaf.support.HtmlReportSupport;
import com.ctaf.utilities.Reporter;

import flynas.web.testObjects.BookingPageLocators;
import flynas.web.workflows.BookingPageFlow;

public class TC29_redemptionBookingWithGoldMember extends BookingPageFlow{
	ExcelReader xls = new ExcelReader(configProps.getProperty("TestData"),"FL_WEB_29");

	@Test(dataProvider = "testData",groups={"Chrome"})
	public  void TC_29_redemptionBookingWithGoldMember(String tripType, String origin, String dest,String deptDate, String origin2,
			String departure2, String retdate,String Audalt,String Child,String infant, String promo, String strBookingClass,String FlightType,String totalpass,String nationality,String Doctypr,String docNumber,
			String naSmiles,String Mobile,String email ,String SelectSeat,String paymenttype,String bookingtype,String charity,String Currency, String gold,String Description
			) throws Throwable {
		try {
			//System.out.println(paymenttype);
			TestEngine.testDescription.put(HtmlReportSupport.tc_name, Description);
			String deptdate = pickDate(deptDate);
		
			inputBookingDetails(tripType, origin, dest, deptdate, origin2, departure2, retdate,Audalt, Child, infant,promo,Currency,paymenttype);
			
			selectClass(strBookingClass, tripType);
			inputPassengerDetails(FlightType,totalpass,nationality,Doctypr,docNumber, naSmiles,Mobile,email,"gold","member",paymenttype);
			waitforElement(BookingPageLocators.baggagetittle);
			if(isElementDisplayedTemp(BookingPageLocators.baggagetittle)==true){
			click(BookingPageLocators.continueBtn, "Continue");
			}
			waitforElement(BookingPageLocators.selectseattittle);
			if(isElementDisplayedTemp(BookingPageLocators.selectseattittle)==true){
				click(BookingPageLocators.continueBtn, "Continue");
				if(isElementDisplayedTemp(BookingPageLocators.ok)){
					click(BookingPageLocators.ok, "OK");
			}
			}
			payment(paymenttype,gold);
			String PNR=getReferenceNumber();
			validate_ticketStatus(PNR);
			
			Reporter.SuccessReport("TC29_redemptionBookingWithGoldMember", "Pass");
			}
		
	catch (Exception e) {
			e.printStackTrace();
			Reporter.SuccessReport("TC29_redemptionBookingWithGoldMember", "Failed");
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
	    		xls.getCellValue("Booking Class", "Value"),
	    		xls.getCellValue("Flight Type", "Value"),
	    		xls.getCellValue("Total Passenger", "Value"),
	    		xls.getCellValue("Nationality", "Value"),
	    		xls.getCellValue("Document Type", "Value"),
	    		xls.getCellValue("Doc Number", "Value"),
	    		xls.getCellValue("naSmile", "Value"),
	    		xls.getCellValue("Mobile", "Value"),
	    		xls.getCellValue("Email Address", "Value"),
	    		xls.getCellValue("Select Seat", "Value"),
	    		xls.getCellValue("Payment Type", "Value"),
	    		"",
    			xls.getCellValue("Charity Donation", "Value"),
    			xls.getCellValue("Currency", "Value"),
    			xls.getCellValue("gold", "Value"),
	    		"Validate Redemption Booking with Gold  Member"}};
	}

}
